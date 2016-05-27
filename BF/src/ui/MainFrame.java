package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import rmi.RemoteHelper;
import service.ExecuteService;

public class MainFrame extends JFrame {
	private JTextArea textAreaOfCode;
	private JTextArea textAreaOfInput;
	private JTextArea textAreaOfResult;

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		JMenu logMenu = new JMenu("Login");
		JMenu fileMenu = new JMenu("File");
		JMenu runMenu = new JMenu("Run");
		JMenu VersionMenu = new JMenu("Version");
		menuBar.add(logMenu);
		menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(VersionMenu);
		JMenuItem loginMenuItem = new JMenuItem("Login");
		logMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logMenu.add(logoutMenuItem);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		runMenu.add(runMenuItem);
		frame.setJMenuBar(menuBar);

		loginMenuItem.addActionListener(new MenuItemActionListener());
		logoutMenuItem.addActionListener(new MenuItemActionListener());
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		loginMenuItem.addActionListener(new loginActionListener());

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// 初始化文本框
		textAreaOfCode = new JTextArea();
		textAreaOfInput = new JTextArea(8, 25);
		textAreaOfResult = new JTextArea(8, 25);
		textAreaOfCode.setLineWrap(true);
		textAreaOfInput.setLineWrap(true);
		textAreaOfResult.setLineWrap(true);
		textAreaOfCode.setMargin(new Insets(10, 10, 10, 10));
		textAreaOfInput.setMargin(new Insets(10, 10, 10, 10));
		textAreaOfResult.setMargin(new Insets(10, 10, 10, 10));

		// 设置文本框底色
		Color AliceBlue = new Color(240, 248, 255);
		Color Bisque = new Color(255, 228, 196);
		textAreaOfCode.setBackground(AliceBlue);
		textAreaOfInput.setBackground(Bisque);
		textAreaOfResult.setBackground(Bisque);
		panel.setBackground(AliceBlue);

//		Font f = new Font("", 0, 12);
//
//		textAreaOfCode.setFont(f);
//		textAreaOfInput.setFont(f);
//		textAreaOfResult.setFont(f);

		textAreaOfCode.setBorder(BorderFactory.createTitledBorder("BF code"));
		textAreaOfInput.setBorder(BorderFactory.createTitledBorder("Input"));
		textAreaOfResult.setBorder(BorderFactory.createTitledBorder("Result"));

		frame.add(panel, BorderLayout.SOUTH);
		frame.add(textAreaOfCode, BorderLayout.CENTER);
		panel.add(textAreaOfInput, BorderLayout.WEST);
		panel.add(textAreaOfResult, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Logout")) {
				textAreaOfCode.setText("Logout");
			} else if (cmd.equals("Open")) {
				textAreaOfCode.setText("Open");
			} else if (cmd.equals("Run")) {
				String code = textAreaOfCode.getText();
				String param = textAreaOfInput.getText() + "\n";
				try {
					String result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
					textAreaOfResult.setText(result);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textAreaOfCode.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}

	class loginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			LoginFrame loginFrame = new LoginFrame();

		}
	}

}
