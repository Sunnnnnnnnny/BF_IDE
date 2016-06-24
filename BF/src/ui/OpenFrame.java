package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import rmi.RemoteHelper;

public class OpenFrame extends JFrame {
	// 此类为打开文件时显示文件列表的界面

	String usernow = "";

	public OpenFrame(String usernow) {
		this.usernow = usernow;
	}

	public String openFile() {

		JFileChooser chooser = new JFileChooser();
		File f = chooser.getSelectedFile();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setCurrentDirectory(new File("D:\\eclipse-st\\BFServer\\" + usernow));
		String file = "";
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File myfile = chooser.getSelectedFile();
			// 获取文件夹名字
			String[] dir = myfile.getAbsolutePath().split("\\\\");
			String name = dir[dir.length - 2];

			try {
				RemoteHelper.getInstance().getIOService().setFileName(name);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				FileReader fr = new FileReader(myfile);
				BufferedReader br = new BufferedReader(fr);
				for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
					file += tmp;

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return file;
	}

}
