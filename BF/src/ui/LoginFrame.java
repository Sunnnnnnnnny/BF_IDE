package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class LoginFrame extends JFrame {

	private JTextField t_username = new JTextField(10);
	private JPasswordField t_password = new JPasswordField(10);
	private JFrame loginFrame = new JFrame("Login");
	private Color AliceBlue = new Color(240, 248, 255);

	public LoginFrame() {
		// 创建新窗体

		JPanel panel = new JPanel();
		loginFrame.setLayout(new BorderLayout());
		loginFrame.setResizable(false);

		JLabel l_username = new JLabel("username");
		JLabel l_password = new JLabel("password");
		JButton loginButton = new JButton("Login");
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

		loginButton.addActionListener(new successfulActionListener());
		cancelButton.addActionListener(new successfulActionListener());

	}

	class successfulActionListener implements ActionListener {
		@Override
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
						JFrame frame = new JFrame("Successfully!");
						JPanel panel=new JPanel();
						panel.setBackground(AliceBlue);
						JLabel label = new JLabel("Login Successfully! Please exit to continue!");
						panel.add(label);
						frame.add(panel);
						frame.setSize(400, 200);
						frame.setLocation(400, 200);
						frame.setVisible(true);
						loginFrame.setVisible(false);
					} else {
						JFrame frame = new JFrame("Wrong!");
						JPanel panel=new JPanel();
						panel.setBackground(AliceBlue);
						JLabel label = new JLabel("Wrong name or password! Please check!");
						panel.add(label);
						frame.add(panel);
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
}
