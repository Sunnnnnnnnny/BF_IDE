package runner;

import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.RemoteHelper;
import service.IOService;
import ui.LoginFrame;
import ui.MainFrame;

public class ClientRunner {
	private RemoteHelper remoteHelper;

	public ClientRunner() throws MalformedURLException, RemoteException, NotBoundException {
		linkToServer();
		initGUI();
	}

	private void linkToServer() {
		try {
			System.out.println("linking...");
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
			System.out.println("linked");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void initGUI() throws MalformedURLException, RemoteException, NotBoundException {
		LoginFrame myLoginFrame = new LoginFrame();
		myLoginFrame.createLoginFrame();
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		ClientRunner cr = new ClientRunner();
	}
}
