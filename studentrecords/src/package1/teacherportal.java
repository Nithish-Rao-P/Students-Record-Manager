package package1;


import javax.swing.*;

public class teacherportal {
        public void teacherportal_frame()
        {
            entermarks em = new entermarks();
            viewmarks vm = new viewmarks();
            failure f = new failure();
            JFrame teacherportal_frame = new JFrame("Teacherportal Manager");
            teacherportal_frame.setSize(1000, 1000);
            teacherportal_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            teacherportal_frame.setLayout(null);
            JLabel actiontextJLabel = new JLabel("Options Available for teacher");
            actiontextJLabel.setBounds(100, 0, 260, 80);
            JButton enter_mark = new JButton("Enter Mark");
            enter_mark.setBounds(100, 50, 300, 30); 
            JButton failurButton = new JButton("View Failures");
            JButton viewmarks = new JButton("View All the Student's Marks");
            failurButton.setBounds(100, 80, 300, 30);
            viewmarks.setBounds(100, 100, 400, 30);


            enter_mark.addActionListener(e -> {
                em.entermarks_frame();
            });
            failurButton.addActionListener(e -> {
                f.failure_frame();
            });
            viewmarks.addActionListener(e -> {
                vm.viewmarks_frame();
            });


            teacherportal_frame.add(actiontextJLabel);
            teacherportal_frame.add(enter_mark);
            teacherportal_frame.add(failurButton);
            teacherportal_frame.add(viewmarks);
            teacherportal_frame.setVisible(true);
        }
}