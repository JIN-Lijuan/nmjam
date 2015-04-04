package chat;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

import commands.Command;


public class EchoThread extends Thread {
	// The client socket
	protected Socket cl;
	protected Dispatcher dispatcher;
	protected ClientInfo ci;
	// Server logger
	private final static Logger LOGGER = 
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public EchoThread(Socket cl, Dispatcher dispatcher) {
		this.cl = cl;
		this.dispatcher = dispatcher;
		this.ci = new ClientInfo(cl);
	}
	
	public void run(){
		try {
			BufferedReader in =  new BufferedReader( 
					new InputStreamReader( cl.getInputStream()));
			
			DataOutputStream out =  
					new DataOutputStream(cl.getOutputStream());
			

			String input;
			while( !(input = in.readLine()).equals("exit")){
				Command cmd = new Command(input);
				switch( cmd.getType() ){
					case CONNECT:
						String user = cmd.getArg1();
						LOGGER.info(ci.getSocket().getRemoteSocketAddress()+ " : " + 
									cmd.getCmd());
						this.connect(user);
						out.writeChars("WELCOME/"+user+"\n");
						break;
					case EXIT:
						this.cl.close();
						break;
					case MALFORMED:
						out.writeChars(cmd.getError());
					break;
				}
				
			}
			cl.close();
			
		} catch (IOException e) {
			System.out.println("Cannot read IO from socket");
			e.printStackTrace();
			
		}
		
		
	}
	
	public void connect(String user){
		this.ci.setUsername(user);
		this.dispatcher.addClient(this.ci);
		
		
		
	}
	
	
	
	
}
