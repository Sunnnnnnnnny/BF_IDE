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

	// public void pleaseLogin() {
	//
	// // 创建新窗体
	// JFrame frame = new JFrame("Wrong!");
	// JPanel panel = new JPanel();
	// JLabel label = new JLabel("Sorry! Please login first!", JLabel.CENTER);
	// label.setFont(font);
	//
	// Color AliceBlue = new Color(240, 248, 255);
	// panel.setBackground(AliceBlue);
	// panel.add(label);
	// frame.add(panel);
	//
	// frame.setSize(400, 200);
	// frame.setLocation(400, 200);
	// frame.setVisible(true);
	// }

	public void alreadyLogin() {
		// 创建新窗体
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
		// 创建新窗体
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
		// 创建新窗体
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
		// 创建新窗体
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
}
