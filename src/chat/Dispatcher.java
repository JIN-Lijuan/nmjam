package chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class Dispatcher extends Thread {
	public ArrayList<ClientInfo> clientsInfo;
	
	
	public Dispatcher(){
		this.clientsInfo = new ArrayList<ClientInfo>();
		
	}
	
	public synchronized void addClient(ClientInfo new_client){
		this.clientsInfo.add(new_client);
		
	}
	
	public synchronized void removeClient(Socket client){
		this.clientsInfo.remove(client);
		
	}
	
	public synchronized void send_message(String message){
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
