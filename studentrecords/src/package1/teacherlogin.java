package package1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Vector;

public class teacherlogin {
    public void teacherlogin_frame() {
        teacherportal tp = new teacherportal();
        JFrame teacherlogin_frame = new JFrame("Teacher Login Manager");
        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel messageLabel = new JLabel("");
        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        userLabel.setBounds(50, 70, 80, 30);
        passwordLabel.setBounds(50, 110, 80, 30);
        userTextField.setBounds(130, 70, 150, 30);
        passwordField.setBounds(130, 110, 150, 30);
        loginButton.setBounds(130, 150, 80, 30);
        messageLabel.setBounds(130, 190, 200, 30);
        messageLabel.setBounds(130, 220, 400, 30);

        loginButton.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://127.0.0.1:3306/student_login";
                String user = "root";
                String password1 = "";
                System.out.println("before connection");
                Connection connection = DriverManager.getConnection(url, user, password1);
                System.out.println("after connection");
                if (connection != null) {
                    System.out.println("Connected to the database.");
                    String username = userTextField.getText();
                    String pass = new String(passwordField.getPassword());
                    String check_teacher = "SELECT * FROM teacherportal WHERE Email = '" + username + "' AND Department = '" + pass + "'";
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(check_teacher);
                    if (rs != null && rs.next())
                    tp.teacherportal_frame();
                    else
                    JOptionPane.showMessageDialog(teacherlogin_frame, "Wrong Password or Username");
                    connection.close();
                } else {
                    
                }
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Error connecting to the database: " + ex.getMessage());
            }
        });

        teacherlogin_frame.add(userLabel);
        teacherlogin_frame.add(passwordLabel);
        teacherlogin_frame.add(userTextField);
        teacherlogin_frame.add(passwordField);
        teacherlogin_frame.add(loginButton);
        teacherlogin_frame.add(messageLabel);
        
        teacherlogin_frame.setSize(400, 300);
        teacherlogin_frame.setLayout(null);
        teacherlogin_frame.setVisible(true);
        teacherlogin_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
    throws SQLException {

ResultSetMetaData metaData = rs.getMetaData();

Vector<String> columnNames = new Vector<String>();
int columnCount = metaData.getColumnCount();
for (int column = 1; column <= columnCount; column++) {
    columnNames.add(metaData.getColumnName(column));
}

// data of the table
Vector<Vector<Object>> data = new Vector<Vector<Object>>();
while (rs.next()) {
    Vector<Object> vector = new Vector<Object>();
    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
        vector.add(rs.getObject(columnIndex));
    }
    data.add(vector);
}

return new DefaultTableModel(data, columnNames);
}
}
