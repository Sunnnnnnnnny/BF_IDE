package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CueFrame extends JFrame {
	// 此类为所有提示类界面

	public Font font = new Font("alias", Font.PLAIN, 20);

	public void alreadyLogin() {
		// 登陆失败
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Already login!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label);
		frame.add(panel);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void registerFail() {
		// 注册失败
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Please enter the same password!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label);
		frame.add(panel);

		frame.setSize(500, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void registerSuccessfully() {
		// 注册成功
		JFrame frame = new JFrame("Success");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Register Successfully! Please exit to continue", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label);
		frame.add(panel);

		frame.setSize(500, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void duplicate() {
		// 注册时用户名已被占用
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sorry!The username has been registered!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label);
		frame.add(panel);

		frame.setSize(500, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void savedFrame() {

		// 保存成功
		JFrame frame = new JFrame("Save");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Save Successfully!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.CENTER);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void notSavedFrame() {
		// 保存失败
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Cannot Save Successfully!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.CENTER);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void notChangedFrame() {
		// 未修改保存
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("No Change!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.CENTER);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	public void noAuthority() {
		// 没有权限查看他人文件
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("You cannot open other user's file!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.CENTER);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}
}
