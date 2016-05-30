package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SaveFrame extends JFrame {
	
	public Font font = new Font("alias", Font.PLAIN, 16);

	public void saveFrame() {

		// 创建新窗体
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

	public void notSaveFrame() {
		// 创建新窗体
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

}
