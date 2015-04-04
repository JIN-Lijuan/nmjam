package chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class Dispatcher extends Thread {
	public ArrayList<ClientInfo> clientsInfo;
	public SessionInfo sessionInfo;
	
	
	public Dispatcher(){
		this.clientsInfo = new ArrayList<ClientInfo>();
		this.sessionInfo = new SessionInfo();
	}
	
	public synchronized void addClient(ClientInfo new_client){
		this.sessionInfo.addUser();
		this.clientsInfo.add(new_client);
		
	}
	
	public synchronized SessionInfo getSessionInfo(){
		return this.sessionInfo;
		
	}
	
	public synchronized void removeClient(ClientInfo client){
		this.clientsInfo.remove(client);
		this.sessionInfo.removeUser();
		
	}
	
	public synchronized void broadcast(String message){
		for( ClientInfo c : this.clientsInfo){
			try {
				DataOutputStream out =  
						new DataOutputStream(c.getSocket().getOutputStream());
				out.writeBytes(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
