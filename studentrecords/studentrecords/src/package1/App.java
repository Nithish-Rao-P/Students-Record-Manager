
package package1;
import javax.swing.*;
public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Records Manager");
        filehandler sw = new filehandler();
        teacherlogin tl = new teacherlogin();
        studentlogin sl = new studentlogin();
		sw.printfunction();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(null); 
        JLabel l1 = new JLabel("Select known Languages");
        l1.setBounds(100, 0, 160, 80);
        JButton student_button = new JButton("Student");
        student_button.setBounds(100, 50, 100, 30); 
        JButton teacher_button = new JButton("Teacher");
        teacher_button.setBounds(100, 80, 100, 30);
        teacher_button.addActionListener(e -> {
            frame.setVisible(false);
            tl.teacherlogin_frame();
        });
        student_button.addActionListener(e -> {
            sl.studentlogin_frame();
        });
        frame.add(l1);
        frame.add(student_button);
        frame.add(teacher_button);
        frame.setVisible(true);
    }
}
