package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

public class IOServiceImpl implements IOService {

	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File folder = new File(userId);
		folder.mkdir();
		File f = new File(userId + "/" + userId + "_" + fileName);
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
		File f = new File(userId + "/" + fileName);
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
		File folder = new File(userId);
		return folder.list();
	}

}
