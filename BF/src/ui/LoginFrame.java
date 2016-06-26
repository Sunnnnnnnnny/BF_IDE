package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import rmi.RemoteHelper;

public class LoginFrame extends JFrame implements ActionListener {
	// 此类为登录界面

	private JFrame loginFrame = new JFrame("Login");
	private JTextField t_username = new JTextField(20);
	private JPasswordField t_password = new JPasswordField(20);
	private JButton loginButton = new JButton("Login");
	private Color AliceBlue = new Color(240, 248, 255);
	private Font font = new Font("alias", Font.PLAIN, 30);
	private String usernow;

	public String getUsernow() {
		return usernow;
	}

	public void createLoginFrame() {

		// 创建新窗口
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel textPanel = new JPanel();
		loginFrame.setResizable(false);

		JLabel welcome = new JLabel("Welcome to use this IDE!");
		welcome.setFont(font);
		JLabel l_username = new JLabel("username:");
		l_username.setFont(new Font("alias",Font.PLAIN,18));
		JLabel l_password = new JLabel("password:");
		l_password.setFont(new Font("alias",Font.PLAIN,18));

		JButton cancelButton = new JButton("Exit");
		JButton registerButton = new JButton("Register");
		loginButton.setBounds(300, 200, 10, 10);

		panel.add(welcome);
		textPanel.add(l_username);
		textPanel.add(t_username);
		textPanel.add(l_password);
		textPanel.add(t_password);
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		buttonPanel.add(cancelButton);
		loginFrame.add(panel, BorderLayout.NORTH);
		loginFrame.add(buttonPanel, BorderLayout.SOUTH);
		loginFrame.add(textPanel, BorderLayout.CENTER);

		panel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		textPanel.setBackground(Color.WHITE);

		loginFrame.setSize(400, 250);
		loginFrame.setLocation(400, 200);
		loginFrame.setVisible(true);

		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);
		registerButton.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("Login")) {

			try {

				char[] password_c = t_password.getPassword();
				String password = "";
				for (char c : password_c) {
					password += c;
				}
				boolean result = RemoteHelper.getInstance().getUserService().login(t_username.getText(), password);
				if (result) {

					usernow = t_username.getText();
					loginFrame.setVisible(false);
					MainFrame mainFrame = new MainFrame(usernow);
					mainFrame.createMainFrame();

				} else {

					// isLogin = false;
					JFrame frame = new JFrame("Wrong!");
					JPanel panel = new JPanel();
					panel.setBackground(AliceBlue);
					JLabel label = new JLabel("Wrong name or password! Please check!", JLabel.CENTER);
					label.setFont(font);
					panel.add(label, BorderLayout.CENTER);
					frame.add(panel, BorderLayout.CENTER);
					frame.setSize(400, 200);
					frame.setLocation(400, 200);
					frame.setVisible(true);

				}

			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (cmd.equals("Exit")) {
			loginFrame.setVisible(false);
		} else if (cmd.equals("Register")) {
			RegisterFrame registerFrame = new RegisterFrame();
		}

	}

}
