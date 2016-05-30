package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CueFrame extends JFrame {
	
	public Font font = new Font("alias", Font.PLAIN, 30);

	public void pleaseLogin() {

		// 创建新窗体
		JFrame frame = new JFrame("Wrong!");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sorry! Please login first!", JLabel.CENTER);
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label);
		frame.add(panel);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

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
}
