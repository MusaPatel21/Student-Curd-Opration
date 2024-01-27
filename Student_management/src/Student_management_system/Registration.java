package Student_management_system;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registration extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContact;
    private JTextField txtAddress;
    private JTextField txtClass;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Registration frame = new Registration();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Registration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 496, 300);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(32, 34, 86, 14);
        contentPane.add(lblFirstName);

        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(32, 70, 86, 14);
        contentPane.add(lblLastName);

        JLabel lblContact = new JLabel("Contact");
        lblContact.setBounds(32, 104, 86, 14);
        contentPane.add(lblContact);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(32, 138, 86, 14);
        contentPane.add(lblAddress);

        JLabel lblClass = new JLabel("Class");
        lblClass.setBounds(32, 172, 86, 14);
        contentPane.add(lblClass);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(128, 31, 86, 20);
        contentPane.add(txtFirstName);
        txtFirstName.setColumns(10);

        txtLastName = new JTextField();
        txtLastName.setBounds(128, 67, 86, 20);
        contentPane.add(txtLastName);
        txtLastName.setColumns(10);

        txtContact = new JTextField();
        txtContact.setBounds(128, 101, 86, 20);
        contentPane.add(txtContact);
        txtContact.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(128, 135, 86, 20);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        txtClass = new JTextField();
        txtClass.setBounds(128, 169, 86, 20);
        contentPane.add(txtClass);
        txtClass.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });
        btnSave.setBounds(32, 207, 89, 23);
        contentPane.add(btnSave);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setBounds(128, 207, 89, 23);
        contentPane.add(btnClose);
    }

    private void saveStudent() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "")) {
            String sql = "INSERT INTO students (fname, lname, contact, address, clas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, txtFirstName.getText());
                preparedStatement.setString(2, txtLastName.getText());
                preparedStatement.setString(3, txtContact.getText());
                preparedStatement.setString(4, txtAddress.getText());
                preparedStatement.setInt(5, Integer.parseInt(txtClass.getText()));

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student saved successfully");
                   // this is for clear field after save successfully
                    
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtContact.setText("");
                    txtAddress.setText("");
                    txtClass.setText("");
                } else {
                    System.out.println("Failed to save student.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
