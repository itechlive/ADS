import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboard extends JFrame {
    private JPanel mainJpanel;
    private JPanel menuJpanel;
    private JButton salesButton;
    private JButton purchaseButton;
    private JButton inventoryButton;
    private JButton configButton;
    private JButton logoutButton;
    private JPanel bannerJpanel;

    public dashboard(User user) {
        add(mainJpanel);
        setTitle("Dashboard");
        setSize(400,500);

        // Enable or disable the configButton based on the user's role
        if (user.role.equals("Admin")) {
            configButton.setEnabled(true);
        } else {
            configButton.setEnabled(false);
        }

        // Add action listeners to the buttons
        salesButton.addActionListener(e -> {
            // Handle salesButton click
        });
        purchaseButton.addActionListener(e -> {
            // Handle purchaseButton click
            Purchase purchase = new Purchase();
            purchase.setVisible(true);
        });
        inventoryButton.addActionListener(e -> {
            // Handle inventoryButton click
        });
        configButton.addActionListener(e -> {
            // Handle configButton click
        });
        logoutButton.addActionListener(e -> {
            // Handle logoutButton click
        });

        setVisible(true);
    }
}
