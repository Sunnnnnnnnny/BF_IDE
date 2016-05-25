package serviceImpl;

import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean login(String username, String password) throws RemoteException {
		boolean result = false;
		if (username.equals("admin") && password.equals("123456a")) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

}
