package chat;

import java.net.Socket;

public class ClientInfo {
	protected String nickname;
	protected Socket socket;
	
	public ClientInfo(Socket socket) {
		super();

		this.socket = socket;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public Socket getSocket() {
		return socket;
	}
	
	
}
