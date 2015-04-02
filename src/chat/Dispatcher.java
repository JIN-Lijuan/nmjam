package chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class Dispatcher extends Thread {
	public ArrayList<Socket> clientsSockets;
	public ArrayList<String> messageQueue;
	
	public Dispatcher(){
		this.clientsSockets = new ArrayList<Socket>();
		this.messageQueue = new ArrayList<String>();
		
	}
	
	public synchronized void addClient(Socket new_client){
		this.clientsSockets.add(new_client);
		
	}
	
	public synchronized void removeClient(Socket client){
		this.clientsSockets.remove(client);
		
	}
	
	public synchronized void send_message(String message){
		for( Socket s : clientsSockets){
			try {
				DataOutputStream out =  
						new DataOutputStream(s.getOutputStream());
				out.writeBytes(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
