package package1;

import java.sql.*;

import javax.swing.*;

public class failure {
    public void failure_frame(){
            try {
            teacherlogin tl = new teacherlogin();
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://127.0.0.1:3306/student_login";
                String user = "root";
                String password1 = "";
                System.out.println("before connection");
                Connection connection = DriverManager.getConnection(url, user, password1);
                System.out.println("after connection");
                if (connection != null) {
                    System.out.println("Connected to the database.");
                    Statement st = connection.createStatement();
                    String checkfailure = "SELECT * FROM studentmark where AvgMarks <" + 50 ;
                    ResultSet cf = st.executeQuery(checkfailure);
                    JTable table = new JTable(tl.buildTableModel(cf));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                    connection.close(); 
                } else {
                    
                }
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Error connecting to the database: " + ex.getMessage());
            }
    }
    
}
