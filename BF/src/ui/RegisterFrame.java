package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
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

public class RegisterFrame extends JFrame implements ActionListener {
	// 此类为注册新用户的界面

	private JFrame frame = new JFrame("Register");
	private Font font = new Font("alias", Font.PLAIN, 18);
	private JTextField t_username = new JTextField(20);
	private JPasswordField t_password1 = new JPasswordField(20);
	private JPasswordField t_password2 = new JPasswordField(10);

	public RegisterFrame() {

		// 创建新窗体

		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel l_username = new JLabel("username:");
		l_username.setFont(font);
		JLabel l_password1 = new JLabel("password:");
		l_password1.setFont(font);
		JLabel l_password2 = new JLabel("confirm your password:");
		l_password2.setFont(font);
		JButton register = new JButton("Register");
		JButton cancel = new JButton("Cancel");

		panel.add(l_username);
		panel.add(t_username);
		panel.add(l_password1);
		panel.add(t_password1);
		panel.add(l_password2);
		panel.add(t_password2);
		buttonPanel.add(register);
		buttonPanel.add(cancel);

		panel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		frame.add(panel);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setSize(400, 250);
		frame.setLocation(400, 200);
		frame.setVisible(true);
		frame.setResizable(false);

		register.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("Register")) {
			// 比较两次输入密码是否相同
			char[] password_c1 = t_password1.getPassword();
			char[] password_c2 = t_password2.getPassword();
			String password1 = "";
			String password2 = "";
			for (char c : password_c1) {
				password1 += c;
			}
			for (char c : password_c2) {
				password2 += c;
			}
			if (password1.equals(password2) && password1 != null) {
				// 保存用户信息
				try {
					boolean isDuplicate = RemoteHelper.getInstance().getUserService().info(t_username.getText(),
							password1);
					if (isDuplicate) {
						// 如果重名
						CueFrame cueFrame = new CueFrame();
						cueFrame.duplicate();
					} else {
						// 不重名，注册成功
						frame.setVisible(false);
						CueFrame cueFrame = new CueFrame();
						cueFrame.registerSuccessfully();
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				// 两次输入密码不符
				CueFrame cueframe = new CueFrame();
				cueframe.registerFail();
			}
		} else if (cmd.equals("Cancel")) {
			frame.setVisible(false);
		}

	}

}
