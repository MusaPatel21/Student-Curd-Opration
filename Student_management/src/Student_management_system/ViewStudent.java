package Student_management_system;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewStudent frame = new ViewStudent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public ViewStudent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // table
        table = new JTable();
        table.setBounds(10, 11, 564, 339);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 46, 564, 304);
        contentPane.add(scrollPane);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 openHome();
        	}
        	 private void openHome() {
        	        Home home = new Home();
        	        home.setVisible(true);
        	        
        	    }
        });
        btnNewButton.setBounds(10, 15, 85, 21);
        contentPane.add(btnNewButton);

       
        displayStudentData();
    }

    private void displayStudentData() {
      
        String DB_URL = "jdbc:mysql://localhost:3306/student";
        String USER = "root";
        String PASSWORD = "2114";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM students";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

            
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("First Name");
                model.addColumn("Last Name");
                model.addColumn("Contact");
                model.addColumn("Address");
                model.addColumn("Class");

                while (resultSet.next()) {
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    String contact = resultSet.getString("contact");
                    String address = resultSet.getString("address");
                    int clas = resultSet.getInt("clas");

                 
                    model.addRow(new Object[] { fname, lname, contact, address, clas });
                }

                
                table.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
