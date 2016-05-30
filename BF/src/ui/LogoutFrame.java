package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoutFrame extends JFrame {
	
	public Font font = new Font("alias", Font.PLAIN, 30);

	public LogoutFrame() {
		// 创建新窗体
		JFrame frame = new JFrame("Logout");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Logout Successfully!", JLabel.CENTER);
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
