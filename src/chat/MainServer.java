package chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {
	
	public static void main(String[] args) {
		
		NetworkService networkService;
		networkService = new NetworkService(9999, 1);
		networkService.run();
		
		
		
		
	}

}
