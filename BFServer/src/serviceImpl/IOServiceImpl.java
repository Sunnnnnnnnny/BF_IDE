package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

public class IOServiceImpl implements IOService {

	String filename = "";

	public boolean setFileName(String filename) {
		this.filename = filename;
		return true;
	}

	@Override
	public boolean creatFile(String userId, String filename) {
		this.filename = filename;
		File folder = new File(userId);
		folder.mkdir();// 新建对应用户的文件夹
		File myFile = new File(userId + "/" + filename);
		myFile.mkdir();// 新建文件
		return true;
	}

	@Override
	public boolean writeFile(String file, String userId, String versionName) {

		File f = new File(userId + "/" + filename + "/" + userId + "_" + versionName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		String code = "";
		File f = new File(userId + "/" + filename + "/" + fileName);
		try {
			FileReader reader = new FileReader(f);
			BufferedReader br = new BufferedReader(reader);
			for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
				code += tmp;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public String[] readFileList(String userId) {
		// TODO Auto-generated method stub
		File folder = new File(userId + "/" + filename + "/");
		return folder.list();
	}

}
