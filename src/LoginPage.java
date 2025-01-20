import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage {

    public LoginPage() {
        createLoginPage();
    }

    public void createLoginPage() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(25, 27, 30));



        ImageIcon logoIcon = new ImageIcon(getClass().getResource("botify_logo.png"));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(-10, -40, logoIcon.getIconWidth(), logoIcon.getIconHeight()); // Adjust position and size
        frame.add(logoLabel);
        Font robotoFont = new Font("Roboto", Font.PLAIN, 16);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(new Color(203, 203, 255));
        usernameLabel.setFont(robotoFont);
        usernameLabel.setBounds(100, 170, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Roboto", Font.PLAIN, 16)); // Example font
        usernameField.setBounds(200, 170, 200, 30);
        usernameField.setBackground(new Color(32, 30, 33));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding for typing
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(215, 147, 255));
        passwordLabel.setFont(robotoFont);
        passwordLabel.setBounds(100, 220, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0); // Show plain text instead of masking
        passwordField.setFont(robotoFont);
        passwordField.setBounds(200, 220, 200, 30);
        passwordField.setBackground(new Color(32, 30, 33));
        passwordField.setForeground(Color.WHITE); // Set text color to white
        passwordField.setCaretColor(Color.WHITE); // Set caret (cursor) color to white
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
        frame.add(passwordField);

        ImageIcon loginIcon = new ImageIcon(getClass().getResource("LOGIN.png"));
        JLabel loginButton = new JLabel(loginIcon);
        loginButton.setBounds(200, 260, loginIcon.getIconWidth(), loginIcon.getIconHeight()); // Set position and size

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String passwordStr = new String(password);

                if (checkLoginCredentials(username, passwordStr)) {
                    JOptionPane.showMessageDialog(frame, "Welcome, " + username + "!");
                    new UI();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(loginButton);

        // Display the frame
        frame.setVisible(true);

        // Add the RoundedSquarePanel
        RoundedSquarePanel loginRoundedPanel = new RoundedSquarePanel(Color.BLACK, 400);
        loginRoundedPanel.setBounds(50, 50, 400, 300); // Adjust the size and position as needed
        frame.add(loginRoundedPanel);
    }

    public boolean checkLoginCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String fileUsername = credentials[0].trim();
                    String filePassword = credentials[1].trim();

                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading info.txt file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}

