import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class DBApp1 extends JFrame{
public DBApp1(){
    setTitle("Employee table");
    setLocation(300, 200);
    setSize(500,300);
    
    Container container = getContentPane();
    FlowLayout lay =new FlowLayout();
    container.setLayout(lay);

    Vector columnNames = new Vector();
    columnNames.add("ID");
    columnNames.add("Name");
    columnNames.add("Nic Number");
    columnNames.add("Gender");

    
    Vector data = new Vector();

    DefaultTableModel dataModel =new DefaultTableModel(data,columnNames);

    JTable tblEmployee = new JTable();
    tblEmployee.setModel(dataModel);
    DefaultTableModel model = (DefaultTableModel)tblEmployee.getModel();

    JScrollPane jScrollPane =new JScrollPane();
    jScrollPane.setPreferredSize(new Dimension(450, 250));
    jScrollPane.setViewportView(tblEmployee);

    container.add(jScrollPane);



        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/euc1","root","1234");
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("Select *from employee;");

            while(resultSet.next()){
                int id = resultSet.getInt ("id");
                String name = resultSet.getString("name");
                String nic = resultSet.getString("nic");
                int genderId = resultSet.getInt("gender_id");

                Vector row =new Vector();
                row.add(id);
                row.add(name);
                row.add(nic);
                row.add(genderId);
                model.addRow(row);
            }
        }catch(SQLException ex){
                System.out.println("Database Error " + ex.getMessage());
            

         }
        }
        public static void main(String[] args) {
            DBApp1 employeeTableUI =new DBApp1();
            employeeTableUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
            employeeTableUI.setVisible(true);
        }
    }
