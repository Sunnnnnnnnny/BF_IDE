package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import rmi.RemoteHelper;

public class MainFrame extends JFrame {
	// 此类为主界面

	private JFrame frame;
	private JTextArea textAreaOfCode;
	private JTextArea textAreaOfInput;
	private JTextArea textAreaOfResult;
	private JMenu versionMenu;
	private JMenuItem logoutMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem runMenuItem;
	private String usernow;
	private Font font = new Font("alias", Font.PLAIN, 18);
	private String code = "";
	private int isSaved = 0;
	private Undo undo;

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
		logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.setFont(font);
		logMenu.add(logoutMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setFont(font);
		fileMenu.add(openMenuItem);
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setFont(font);
		fileMenu.add(newMenuItem);
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setFont(font);
		fileMenu.add(saveMenuItem);
		runMenuItem = new JMenuItem("Run");
		runMenuItem.setFont(font);
		runMenu.add(runMenuItem);
		versionMenu.removeAll();

		frame.setJMenuBar(menuBar);

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
		Color Bisque = new Color(211, 211, 211);
		textAreaOfCode.setBackground(Color.WHITE);
		textAreaOfInput.setBackground(Bisque);
		textAreaOfResult.setBackground(Bisque);
		panel.setBackground(Color.WHITE);

		textAreaOfCode.setBorder(BorderFactory.createTitledBorder("Welcome, " + usernow + "!  BF code"));
		textAreaOfInput.setBorder(BorderFactory.createTitledBorder("Input"));
		textAreaOfResult.setBorder(BorderFactory.createTitledBorder("Result"));

		frame.add(panel, BorderLayout.SOUTH);
		frame.add(textAreaOfCode, BorderLayout.CENTER);
		panel.add(textAreaOfInput, BorderLayout.WEST);
		panel.add(textAreaOfResult, BorderLayout.EAST);

		openMenuItem.addActionListener(new MenuItemActionListener());
		newMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		logoutMenuItem.addActionListener(new LogoutActionListener());
		textAreaOfCode.addKeyListener(new TextKeyListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 800);
		frame.setLocation(400, 200);
		frame.setVisible(true);

		// 添加撤销操作监听
		String initialCode = textAreaOfCode.getText();
		undo = new Undo(initialCode);
		textAreaOfCode.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				// 添加超过20个字节，保存代码
				if (textAreaOfCode.getText().length() - undo.getCode().length() > 20) {
					undo.save(textAreaOfCode.getText());
				}
//				if (!textAreaOfCode.getText().equals(undo.getCode())) {
//					undo.save(textAreaOfCode.getText());
//				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

		});
	}

	// 以下字段为所有响应事件
	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Run")) {
				// 运行代码
				String code = textAreaOfCode.getText();
				String param = textAreaOfInput.getText() + "\n";
				try {
					String result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
					textAreaOfResult.setText(result);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			} else if (cmd.equals("New")) {
				// 新建文件
				isSaved = 0;
				textAreaOfCode.setText("");
				textAreaOfInput.setText("");
				textAreaOfResult.setText("");
				versionMenu.removeAll();
				undo.save("");
			} else if (cmd.equals("Open")) {
				// 打开相应文件
				isSaved = 1;
				OpenFrame openFrame = new OpenFrame(usernow);
				String content = openFrame.openFile();
				textAreaOfCode.setText(content);
				code = content;
				undo.save(content);
				// 根据对应用户添加version版本
				try {
					// 先清空版本列表
					versionMenu.removeAll();
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

			String code1 = textAreaOfCode.getText();
			if (!code1.equals(code)) {
				// 如果代码改变，保存
				if (isSaved == 0) {
					NewFrame newFrame = new NewFrame(usernow, code1, time);
					JMenuItem versionMenuItem = new JMenuItem(usernow + "_" + time);
					versionMenu.add(versionMenuItem);
					versionMenuItem.setFont(font);
					versionMenuItem.addActionListener(new VersionActionListener());
				} else {
					CueFrame saveframe = new CueFrame();
					boolean isSaved;
					try {
						isSaved = RemoteHelper.getInstance().getIOService().writeFile(code1, usernow, time);
						if (isSaved) {
							saveframe.savedFrame();
						} else {
							saveframe.notSavedFrame();
						}
						// 添加相应历史版本
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
			} else {
				CueFrame cueFrame = new CueFrame();
				cueFrame.notChangedFrame();
				isSaved++;
			}

		}

	}

	class LogoutActionListener implements ActionListener {
		/**
		 * 登出响应事件
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 回到登录界面
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
				// 获得相应版本内容
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

	class TextKeyListener implements KeyListener {
		/**
		 * 键盘响应事件
		 */

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			String initialCode = textAreaOfCode.getText();
			if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				undo.save(textAreaOfCode.getText());
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
				// Ctrl+z撤回
				// 先保存撤回前的内容
				undo.redoSave(initialCode);
				textAreaOfCode.setText(undo.undo());
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
				// Ctrl+y重做
				textAreaOfCode.setText(undo.redo());
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
				// Ctrl+s保存
				saveMenuItem.doClick();
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F11) {
				runMenuItem.doClick();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

	}
}
