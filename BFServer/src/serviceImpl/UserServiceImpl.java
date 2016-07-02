package serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean login(String username, String password) throws RemoteException {
		/**
		 * 检查用户名与密码是否对应
		 */
		File dataBase = new File("DataBase.txt");
		String truePassword = "";
		boolean result = false;
		try {
			FileReader fr = new FileReader(dataBase);
			BufferedReader br = new BufferedReader(fr);
			for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
				if (tmp.startsWith(username)) {
					truePassword = tmp.split(";")[1];
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (password.equals(truePassword)) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean info(String username, String password) throws RemoteException {
		/**
		 * 保存用户名及密码信息
		 */
		File dataBase = new File("DataBase.txt");
		boolean isDuplicate = false;
		try {
			FileWriter fw = new FileWriter(dataBase, true);
			FileReader fr = new FileReader(dataBase);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(fr);
			for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
				if (tmp.startsWith(username)) {
					// 检测是否重名
					isDuplicate = true;
					break;
				} else {
					bw.write(username + ";" + password);
					bw.write("\r\n");
					bw.flush();
					bw.close();
					fw.close();
					break;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isDuplicate;
	}

}
