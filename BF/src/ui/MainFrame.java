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
import service.ExecuteService;

public class MainFrame extends JFrame {
	private JTextArea textAreaOfCode;
	private JTextArea textAreaOfInput;
	private JTextArea textAreaOfResult;
	public JMenu versionMenu = new JMenu("Version");
	private int indexOfVersion = 0;
	public LoginFrame loginFrame = new LoginFrame();
	public Font font = new Font("alias", Font.PLAIN, 18);

	public void createMainFrame() {
		// 创建窗体

		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		JMenu logMenu = new JMenu("Login");
		logMenu.setFont(font);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(font);
		JMenu runMenu = new JMenu("Run");
		runMenu.setFont(font);
		versionMenu.setFont(font);

		menuBar.add(logMenu);
		menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(versionMenu);
		JMenuItem loginMenuItem = new JMenuItem("Login");
		loginMenuItem.setFont(font);
		logMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.setFont(font);
		logMenu.add(logoutMenuItem);
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setFont(font);
		fileMenu.add(newMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setFont(font);
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		runMenuItem.setFont(font);
		runMenu.add(runMenuItem);
		frame.setJMenuBar(menuBar);

		newMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		loginMenuItem.addActionListener(new loginActionListener());
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

		// 设置文本框底色
		Color AliceBlue = new Color(240, 248, 255);
		Color Bisque = new Color(255, 228, 196);
		textAreaOfCode.setBackground(AliceBlue);
		textAreaOfInput.setBackground(Bisque);
		textAreaOfResult.setBackground(Bisque);
		panel.setBackground(AliceBlue);

		textAreaOfCode.setBorder(BorderFactory.createTitledBorder("BF code"));
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
				textAreaOfCode.setText("");
				textAreaOfInput.setText("");
				textAreaOfResult.setText("");
			}
		}
	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textAreaOfCode.getText();

			if (loginFrame.isLogin) {
				// 检测是否已经登录
				try {
					SaveFrame saveframe = new SaveFrame();
					boolean isSaved = RemoteHelper.getInstance().getIOService().writeFile(code, "admin",
							"code" + String.valueOf(indexOfVersion));
					JMenuItem versionMenuItem = new JMenuItem("admin" + "_" + "code" + String.valueOf(indexOfVersion));
					indexOfVersion++;
					versionMenu.add(versionMenuItem);
					versionMenuItem.setFont(font);
					versionMenuItem.addActionListener(new versionActionListener());
					if (isSaved) {
						saveframe.saveFrame();
					} else {
						saveframe.notSaveFrame();
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			} else {
				CueFrame cueFrame = new CueFrame();
				cueFrame.pleaseLogin();
			}

		}

	}

	class loginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (!loginFrame.isLogin) {
				loginFrame.createLoginFrame();
			} else {
				CueFrame cueFrame = new CueFrame();
				cueFrame.alreadyLogin();
			}

		}
	}

	class logoutActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LogoutFrame logoutFrame = new LogoutFrame();
			loginFrame.isLogin = false;
		}

	}

	class versionActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			try {
				String code = RemoteHelper.getInstance().getIOService().readFile("admin", cmd);
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
