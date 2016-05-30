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

	public JTextField t_username = new JTextField(10);
	public JPasswordField t_password = new JPasswordField(10);
	public JButton loginButton = new JButton("Login");
	public JFrame loginFrame = new JFrame("Login");
	public Color AliceBlue = new Color(240, 248, 255);
	public Font font = new Font("alias", Font.PLAIN, 30);
	public String usernow;
	public boolean isLogin;

	public void createLoginFrame() {

		// 创建新窗体
		JPanel panel = new JPanel();
		loginFrame.setResizable(false);

		JLabel l_username = new JLabel("username");
		JLabel l_password = new JLabel("password");

		JButton cancelButton = new JButton("Cancel");

		panel.add(l_username);
		panel.add(t_username);
		panel.add(l_password);
		panel.add(t_password);
		panel.add(loginButton);
		panel.add(cancelButton);
		loginFrame.add(panel);

		panel.setBackground(AliceBlue);

		loginFrame.setSize(600, 100);
		loginFrame.setLocation(400, 200);
		loginFrame.setVisible(true);

		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);

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
					isLogin = true;
					JFrame frame = new JFrame("Successfully!");
					JPanel panel = new JPanel();
					panel.setBackground(AliceBlue);
					JLabel label = new JLabel("Login Successfully! Please exit to continue!", JLabel.CENTER);
					label.setFont(font);
					panel.add(label, BorderLayout.CENTER);
					frame.add(panel, BorderLayout.CENTER);
					frame.setSize(400, 200);
					frame.setLocation(400, 200);
					frame.setVisible(true);
					loginFrame.setVisible(false);

				} else {

					isLogin = false;
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

		} else if (cmd.equals("Cancel")) {
			loginFrame.setVisible(false);
		}

	}

}
