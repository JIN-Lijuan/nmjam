package chat;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import commands.CmdType;

import commands.Command;


public class EchoThread extends Thread {
	// The client socket
	protected Socket cl;
	protected Dispatcher dispatcher;
	protected ClientInfo ci;
	
	public EchoThread(Socket cl, Dispatcher dispatcher) {
		this.cl = cl;
		this.dispatcher =  dispatcher;
		this.ci = new ClientInfo(cl);
	}
	
	public void run(){
		String nickname = "";
		try {
			BufferedReader in =  new BufferedReader( 
					new InputStreamReader( cl.getInputStream()));
			DataOutputStream out =  
					new DataOutputStream(cl.getOutputStream());
			
			out.writeChars("Waiting for something to do:\n");
			String input;
			while( !(input = in.readLine()).equals("exit")){
				Command cmd = new Command(input);
				switch( cmd.getType() ){
					case CONNECT:
						out.writeChars("Connecting...\n");
						break;
					case EXIT:
						out.writeChars("EXIT...\n");
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
	
	
	
	
}
