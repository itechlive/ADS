import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JDialog {
    private JPanel masterForm;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel bannerJpanel;
    private JPanel mainFormJpanel;
    public login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(masterForm);
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userid = textField1.getText();
                String password = String.valueOf(passwordField1.getPassword());
                user = getAuthenticatedUser(userid, password);
                if (user != null) {
                    System.out.println("Userid: "+user.id);
                    System.out.println("Name: "+user.name);
                    System.out.println("Email: "+user.email);
                    System.out.println("Role: "+user.role);
                    dispose();
                    // dashboard dashboard = new dashboard(user);
                    dashboard dashboard = new dashboard(user);
                    dashboard.setVisible(true);
                } else {
                    System.out.println("Invalid Userid or Password");
                    JOptionPane.showMessageDialog(login.this, "Invalid Userid or Password");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        setVisible(true);
    }
    public User user;
    private User getAuthenticatedUser(String userid, String password) {
        User user = null;
        final String DB_URL= "jdbc:mysql://localhost:3306/inventory";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM empleados WHERE userid = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.id = rs.getString("userid"); // Changed from "id" to "userid"
                user.name = rs.getString("primer_nombre") + " " + rs.getString("segundo_nombre"); // Assuming "name" is a combination of "primer_nombre" and "segundo_nombre"
                user.email = rs.getString("email");
                user.password = rs.getString("password");
                user.role = rs.getString("role");
            }

            preparedStatement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    public static void main(String[] args) {
        login login = new login(null);
    }
}
