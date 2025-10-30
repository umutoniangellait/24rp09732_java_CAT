import java.awt.*;
import java.awt.event.*;

public class AWTMenuPractice extends Frame implements ActionListener {

    MenuItem loginItem, studentItem;
    Panel mainPanel;
    CardLayout cardLayout;
    TextField usernameField, passwordField;

    public AWTMenuPractice() {
        // Frame setup
        setTitle("AWT MENU Practice");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY); // Frame background

        // ===== MENU BAR =====
        MenuBar menuBar = new MenuBar();

        Menu pagesMenu = new Menu("Pages");
        loginItem = new MenuItem("Login");
        studentItem = new MenuItem("Student");
        loginItem.addActionListener(this);
        studentItem.addActionListener(this);
        pagesMenu.add(loginItem);
        pagesMenu.add(studentItem);

        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");

        menuBar.add(pagesMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setMenuBar(menuBar);

        // ===== MAIN CONTENT AREA =====
        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        // --- LOGIN PAGE ---
        Panel loginPanel = new Panel(new GridBagLayout());
        loginPanel.setBackground(new Color(173, 216, 230)); // Light blue
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Label title = new Label("LOGIN PAGE", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        loginPanel.add(new Label("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new TextField(15);
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        loginPanel.add(new Label("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new TextField(15);
        passwordField.setEchoChar('*');
        loginPanel.add(passwordField, gbc);

        gbc.gridy++;
        Button loginButton = new Button("LOGIN");
        loginButton.addActionListener(this);
        loginPanel.add(loginButton, gbc);

        // --- STUDENT PAGE ---
        Panel studentPanel = new Panel(new GridLayout(3, 1));
        studentPanel.setBackground(new Color(240, 230, 140)); // Khaki
        studentPanel.add(new Label("STUDENT PAGE", Label.CENTER));
        studentPanel.add(new Label("Name: Umutoni Angella", Label.CENTER));
        studentPanel.add(new Label("Reg No: 24RP09732", Label.CENTER));

        // Add both pages to mainPanel
        mainPanel.add(loginPanel, "login");
        mainPanel.add(studentPanel, "student");

        add(mainPanel, BorderLayout.CENTER);

        // ===== WINDOW CLOSE EVENT =====
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Menu clicks
        if (e.getSource() == loginItem) {
            cardLayout.show(mainPanel, "login");
        } else if (e.getSource() == studentItem) {
            cardLayout.show(mainPanel, "student");
        }
        // Login button
        else if (e.getActionCommand().equals("LOGIN")) {
            if (username.equals("angella") && password.equals("12345")) {
                Dialog successDialog = new Dialog(this, "Login Successful", true);
                successDialog.setLayout(new FlowLayout());
                successDialog.add(new Label("Welcome, Umutoni Angella!"));
                Button ok = new Button("OK");
                ok.addActionListener(ae -> {
                    successDialog.setVisible(false);
                    cardLayout.show(mainPanel, "student");
                });
                successDialog.add(ok);
                successDialog.setSize(250, 120);
                successDialog.setLocationRelativeTo(this);
                successDialog.setVisible(true);
            } else {
                Dialog errorDialog = new Dialog(this, "Login Failed", true);
                errorDialog.setLayout(new FlowLayout());
                errorDialog.add(new Label("Invalid username or password!"));
                Button ok = new Button("OK");
                ok.addActionListener(ae -> errorDialog.setVisible(false));
                errorDialog.add(ok);
                errorDialog.setSize(250, 120);
                errorDialog.setLocationRelativeTo(this);
                errorDialog.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        new AWTMenuPractice();
    }
}
