package chat;


import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {
	
	public static void main(String[] args) {

		NetworkService networkService;
		networkService = new NetworkService(9990, 1);
		networkService.run();
		
		
		
		
	}

}
