package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import rmi.RemoteHelper;
import ui.MainFrame.VersionActionListener;

public class NewFrame extends JFrame implements ActionListener {
	// 此类为创建新文件时，保存文件的界面

	public Font font = new Font("alias", Font.PLAIN, 16);
	String usernow = "";
	String code = "";
	String time = "";
	JFrame frame;
	JTextField text;

	public NewFrame(String usernow, String code, String time) {
		this.code = code;
		this.usernow = usernow;
		this.time = time;

		// 创建新窗体
		frame = new JFrame("Save as");
		text = new JTextField(10);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("new filename:");
		JButton confirm = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		label.setFont(font);

		Color AliceBlue = new Color(240, 248, 255);
		panel.setBackground(AliceBlue);
		panel.add(label);
		panel.add(text);
		panel.add(confirm, BorderLayout.SOUTH);
		panel.add(cancel, BorderLayout.SOUTH);
		frame.add(panel);

		frame.setSize(400, 200);
		frame.setLocation(400, 200);
		frame.setVisible(true);

		confirm.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("OK")) {
			try {
				RemoteHelper.getInstance().getIOService().creatFile(usernow, text.getText());
				SaveFrame saveframe = new SaveFrame();
				boolean isSaved = RemoteHelper.getInstance().getIOService().writeFile(code, usernow, time);
				if (isSaved) {
					saveframe.savedFrame();
				} else {
					saveframe.notSavedFrame();
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.setVisible(false);
		} else if (cmd.equals("Cancel")) {
			frame.setVisible(false);
		}

	}
}
