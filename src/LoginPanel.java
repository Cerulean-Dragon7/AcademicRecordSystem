import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private static final int LOGINGUI = 0;
    private JButton loginButton;
    private JButton forgetPassword;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel icon;
    private JPanel componentsPanel;

    public LoginPanel() {
        placeComponents();
        addComponents();
        setVisible(true);
    }

    private void addComponents(){
        this.setLayout(new BorderLayout());
        this.add(componentsPanel,BorderLayout.CENTER);

        icon = new JLabel("Academic Record System",SwingConstants.CENTER);
        icon.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(icon, BorderLayout.NORTH);
    }
    private void placeComponents() {

        componentsPanel = new JPanel(new GridBagLayout());

        JLabel userLabel = new JLabel("User ID");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(userLabel, gbc);

        userText = new JTextField(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(passwordLabel, gbc);

        passwordText = new JPasswordField(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(passwordText, gbc);

        loginButton = new JButton("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                System.out.println("Username: " + user + " Password: " + password);
                //GUIFrame.currentState = 1;
                //GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                //guiFrame.changePanel();
            }
        });

        forgetPassword = new JButton("forget password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 1, 1, 1);
        componentsPanel.add(forgetPassword, gbc);

        forgetPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("forgetPassword button click");
            }
        });
    }
}
