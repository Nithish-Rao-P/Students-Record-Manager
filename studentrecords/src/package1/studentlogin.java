
package package1;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class studentlogin {
    public void studentlogin_frame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Student Information Fetcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        JLabel studentIdLabel = new JLabel("Enter Student ID:");
        JTextField studentIdField = new JTextField(15);
        JButton fetchButton = new JButton("Fetch");

        panel.add(studentIdLabel);
        panel.add(studentIdField);
        panel.add(fetchButton);

        JLabel resultLabel = new JLabel();
        JScrollPane scrollPane = new JScrollPane(resultLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText().trim();
                if (!studentId.isEmpty()) {
                    fetchStudentInfo(studentId, resultLabel);
                } else {
                    resultLabel.setText("Please enter a valid Student ID.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void fetchStudentInfo(String studentId, JLabel resultLabel) {
        String url = "jdbc:mysql://localhost:3306/student_login";
        String username = "root";
        String password = "";

        String studentQuery = "SELECT StudentID, Name, Email, Class, Section FROM student WHERE StudentID = ? ";
        String marksQuery = "SELECT Subject1Marks, Subject2Marks, Subject3Marks, Subject4Marks, Subject5Marks FROM studentmark WHERE StudentID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
             PreparedStatement marksStmt = conn.prepareStatement(marksQuery)) {

            studentStmt.setString(1, studentId);
            marksStmt.setString(1, studentId);

            ResultSet studentRs = studentStmt.executeQuery();
            ResultSet marksRs = marksStmt.executeQuery();

            if (studentRs.next() && marksRs.next()) {
                int subject1Marks = marksRs.getInt("Subject1Marks");
                int subject2Marks = marksRs.getInt("Subject2Marks");
                int subject3Marks = marksRs.getInt("Subject3Marks");
                int subject4Marks = marksRs.getInt("Subject4Marks");
                int subject5Marks = marksRs.getInt("Subject5Marks");

                double avgMarks = (subject1Marks + subject2Marks + subject3Marks + subject4Marks + subject5Marks) / 5.0;

                StringBuilder result = new StringBuilder("<html>");
                result.append("Student ID: ").append(studentRs.getString("StudentID")).append("<br>");
                result.append("Name: ").append(studentRs.getString("Name")).append("<br>");
                result.append("Email: ").append(studentRs.getString("Email")).append("<br>");
                result.append("Class: ").append(studentRs.getString("Class")).append("<br>");
                result.append("Section: ").append(studentRs.getString("Section")).append("<br>");
                result.append("Subject 1 Marks: ").append(subject1Marks).append("<br>");
                result.append("Subject 2 Marks: ").append(subject2Marks).append("<br>");
                result.append("Subject 3 Marks: ").append(subject3Marks).append("<br>");
                result.append("Subject 4 Marks: ").append(subject4Marks).append("<br>");
                result.append("Subject 5 Marks: ").append(subject5Marks).append("<br>");
                result.append("Average Marks: ").append(avgMarks).append("<br>");
                result.append("</html>");

                resultLabel.setText(result.toString());
            } else {
                resultLabel.setText("No student found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultLabel.setText("Error fetching data.");
        }
    }
}
