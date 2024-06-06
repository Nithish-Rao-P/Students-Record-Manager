package package1;

import java.sql.*;

import javax.swing.*;

public class entermarks {
    public void entermarks_frame(){
        JFrame entermarks_frame = new JFrame("Enter Marks Manager");
        JLabel StudentID = new JLabel("Student ID:");
        JLabel Mark1 = new JLabel("Subject 1:");
        JLabel Mark2 = new JLabel("Subject 2");
        JLabel Mark3 = new JLabel("Subject 3");
        JLabel Mark4 = new JLabel("Subject 4");
        JLabel Mark5 = new JLabel("Subject 5");
        JTextField studentIDTextField = new JTextField();
        JTextField mark1JTextField = new JTextField();
        JTextField mark2JTextField = new JTextField();
        JTextField mark3JTextField = new JTextField();
        JTextField mark4JTextField = new JTextField();
        JTextField mark5JTextField = new JTextField();
        JButton loginButton = new JButton("Enter Mark");
        StudentID.setBounds(50, 70, 80, 30);
        studentIDTextField.setBounds(130,70,80,30);
        Mark1.setBounds(50, 110, 80, 30);
        Mark2.setBounds(50, 150, 80, 30);
        Mark3.setBounds(50, 190, 80, 30);
        Mark4.setBounds(50, 230, 80, 30);
        Mark5.setBounds(50, 270, 80, 30);
        studentIDTextField.setBounds(130, 70, 150, 30);
        mark1JTextField.setBounds(130, 110, 150, 30);
        mark2JTextField.setBounds(130, 150, 150, 30);
        mark3JTextField.setBounds(130, 190, 150, 30);
        mark4JTextField.setBounds(130, 230, 150, 30);
        mark5JTextField.setBounds(130, 270, 150, 30);
        loginButton.setBounds(130,310,150,30);


        loginButton.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://127.0.0.1:3306/student_login";
                String user = "root";
                String password1 = "";
                PreparedStatement preparedStatement = null;
                System.out.println("before connection");
                Connection connection = DriverManager.getConnection(url, user, password1);
                System.out.println("after connection");
                if (connection != null) {
                    String sql = "INSERT INTO studentmark (StudentID, Subject1Marks, Subject2Marks, Subject3Marks, Subject4Marks, Subject5Marks) VALUES (?, ?, ?, ?, ?, ?)";
                    int stuid = Integer.parseInt(studentIDTextField.getText());
                    int m1 = Integer.parseInt(mark1JTextField.getText());
                    int m2 = Integer.parseInt(mark2JTextField.getText());
                    int m3 = Integer.parseInt(mark3JTextField.getText());
                    int m4 = Integer.parseInt(mark4JTextField.getText());
                    int m5 = Integer.parseInt(mark5JTextField.getText());
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, stuid);
                    preparedStatement.setInt(2, m1);
                    preparedStatement.setInt(3, m2);
                    preparedStatement.setInt(4, m3);
                    preparedStatement.setInt(5, m4);
                    preparedStatement.setInt(6, m5);
                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(entermarks_frame, "Updated");
                    } else {
                        JOptionPane.showMessageDialog(entermarks_frame, "Some error occured");
                    }
                    connection.close(); 
                } else {
                    
                }
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Error connecting to the database: " + ex.getMessage());
            }
        });

        entermarks_frame.add(StudentID);
        entermarks_frame.add(studentIDTextField);
        entermarks_frame.add(Mark1);
        entermarks_frame.add(Mark2);
        entermarks_frame.add(Mark3);
        entermarks_frame.add(Mark4);
        entermarks_frame.add(Mark5);
        entermarks_frame.add(mark1JTextField);
        entermarks_frame.add(mark2JTextField);
        entermarks_frame.add(mark3JTextField);
        entermarks_frame.add(mark4JTextField);
        entermarks_frame.add(mark5JTextField);

        entermarks_frame.add(loginButton);
        
        entermarks_frame.setSize(1000, 1000);
        entermarks_frame.setLayout(null);
        entermarks_frame.setVisible(true);
        entermarks_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
    

