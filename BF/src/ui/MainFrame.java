package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import rmi.RemoteHelper;
import runner.ClientRunner;
import service.ExecuteService;

public class MainFrame extends JFrame {
	// 此类为主界面

	private JFrame frame;
	private JTextArea textAreaOfCode;
	private JTextArea textAreaOfInput;
	private JTextArea textAreaOfResult;
	private JMenu versionMenu;
	private String usernow;
	private Font font = new Font("alias", Font.PLAIN, 18);
	private int isSaved = 0;

	public MainFrame(String usernow) {
		this.usernow = usernow;
	}

	public void createMainFrame() {
		// 创建新窗口

		frame = new JFrame("BF Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		JMenu logMenu = new JMenu();
		logMenu.setIcon(new ImageIcon("image\\user.png"));
		logMenu.setFont(font);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(font);
		JMenu runMenu = new JMenu("Run");
		runMenu.setFont(font);
		versionMenu = new JMenu("Version");
		versionMenu.setFont(font);

		menuBar.add(logMenu);
		menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(versionMenu);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.setFont(font);
		logMenu.add(logoutMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setFont(font);
		fileMenu.add(openMenuItem);
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setFont(font);
		fileMenu.add(newMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setFont(font);
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		runMenuItem.setFont(font);
		runMenu.add(runMenuItem);
		versionMenu.removeAll();

		frame.setJMenuBar(menuBar);

		openMenuItem.addActionListener(new MenuItemActionListener());
		newMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		logoutMenuItem.addActionListener(new logoutActionListener());

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// 初始化文本框
		textAreaOfCode = new JTextArea();
		textAreaOfCode.setFont(font);
		textAreaOfInput = new JTextArea(8, 35);
		textAreaOfInput.setFont(font);
		textAreaOfResult = new JTextArea(8, 35);
		textAreaOfResult.setFont(font);
		textAreaOfCode.setLineWrap(true);
		textAreaOfInput.setLineWrap(true);
		textAreaOfResult.setLineWrap(true);
		textAreaOfCode.setMargin(new Insets(10, 10, 10, 10));
		textAreaOfInput.setMargin(new Insets(10, 10, 10, 10));
		textAreaOfResult.setMargin(new Insets(10, 10, 10, 10));

		// 设置背景色
		Color AliceBlue = new Color(240, 248, 255);
		Color Bisque = new Color(255, 228, 196);
		textAreaOfCode.setBackground(AliceBlue);
		textAreaOfInput.setBackground(Bisque);
		textAreaOfResult.setBackground(Bisque);
		panel.setBackground(AliceBlue);

		textAreaOfCode.setBorder(BorderFactory.createTitledBorder("Welcome, " + usernow + "!  BF code"));
		textAreaOfInput.setBorder(BorderFactory.createTitledBorder("Input"));
		textAreaOfResult.setBorder(BorderFactory.createTitledBorder("Result"));

		frame.add(panel, BorderLayout.SOUTH);
		frame.add(textAreaOfCode, BorderLayout.CENTER);
		panel.add(textAreaOfInput, BorderLayout.WEST);
		panel.add(textAreaOfResult, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 800);
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
			if (cmd.equals("Run")) {
				String code = textAreaOfCode.getText();
				String param = textAreaOfInput.getText() + "\n";
				try {
					String result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
					textAreaOfResult.setText(result);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			} else if (cmd.equals("New")) {
				isSaved = 0;
				textAreaOfCode.setText("");
				textAreaOfInput.setText("");
				textAreaOfResult.setText("");
				versionMenu.removeAll();
			} else if (cmd.equals("Open")) {
				OpenFrame openFrame = new OpenFrame(usernow);
				String content = openFrame.openFile();
				textAreaOfCode.setText(content);
				// 根据对应用户添加version版本
				try {
					String[] file = RemoteHelper.getInstance().getIOService().readFileList(usernow);
					if (file != null)
						for (String f : file) {
							JMenuItem versionMenuItem = new JMenuItem(f);
							versionMenu.add(versionMenuItem);
							versionMenuItem.setFont(font);
							versionMenuItem.addActionListener(new VersionActionListener());
						}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	class SaveActionListener implements ActionListener {
		/**
		 * 保存响应事件
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			// 获取当前日期作为文件名
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String time = format.format(date);

			String code = textAreaOfCode.getText();

			if (isSaved == 0) {
				NewFrame newFrame = new NewFrame(usernow, code, time);
				JMenuItem versionMenuItem = new JMenuItem(usernow + "_" + time);
				versionMenu.add(versionMenuItem);
				versionMenuItem.setFont(font);
				versionMenuItem.addActionListener(new VersionActionListener());
			} else {
				SaveFrame saveframe = new SaveFrame();
				boolean isSaved;
				try {
					isSaved = RemoteHelper.getInstance().getIOService().writeFile(code, usernow, time);
					if (isSaved) {
						saveframe.savedFrame();
					} else {
						saveframe.notSavedFrame();
					}
					JMenuItem versionMenuItem = new JMenuItem(usernow + "_" + time);
					versionMenu.add(versionMenuItem);
					versionMenuItem.setFont(font);
					versionMenuItem.addActionListener(new VersionActionListener());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			isSaved++;
		}

	}

	class logoutActionListener implements ActionListener {
		/**
		 * 登出响应事件
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.setVisible(false);
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.createLoginFrame();
		}

	}

	class VersionActionListener implements ActionListener {
		/**
		 * 版本响应事件
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			try {
				String code = RemoteHelper.getInstance().getIOService().readFile(usernow, cmd);
				textAreaOfCode.setText(code);
				textAreaOfInput.setText("");
				textAreaOfResult.setText("");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
