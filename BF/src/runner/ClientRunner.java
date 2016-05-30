package runner;

import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.RemoteHelper;
import service.IOService;
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
		MainFrame mainFrame = new MainFrame();
		mainFrame.createMainFrame();
	}
	
	public void test(){
		try {
			System.out.println(remoteHelper.getUserService().login("admin", "123456a"));
			System.out.println(remoteHelper.getIOService().writeFile("2", "admin", "testFile"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		ClientRunner cr = new ClientRunner();
		//cr.test();
	}
}
