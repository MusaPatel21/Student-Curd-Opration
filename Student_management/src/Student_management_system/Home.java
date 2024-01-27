package Student_management_system;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton registrationButton = new JButton("Registration");
        registrationButton.setBounds(150, 50, 150, 30);
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationForm();
            }
        });
        contentPane.add(registrationButton);

        JButton viewButton = new JButton("View");
        viewButton.setBounds(150, 100, 150, 30);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewStudentForm();
            }
        });
        contentPane.add(viewButton);
    }

    private void openRegistrationForm() {
        Registration registrationForm = new Registration();
        registrationForm.setVisible(true);
    }

    private void openViewStudentForm() {
        ViewStudent viewStudentForm = new ViewStudent();
        viewStudentForm.setVisible(true);
    }
}
