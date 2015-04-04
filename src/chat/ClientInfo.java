package chat;

import java.net.Socket;

/**
 * Basic information for a client.
 *  
 */
public class ClientInfo {
	private Socket clientSocket;
	private static int ID;
	private String username;
	
	public ClientInfo(Socket clientSocket) {
		this.clientSocket = clientSocket;
		ID++;
		this.username =  "guest"+ID;
	
	}
	
	public Socket getSocket() {
		return clientSocket;
	}

	public int getID() {
		return ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	
	
}
