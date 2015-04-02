package chat;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;


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
			
			out.writeChars("Nickname: ");
			String input = in.readLine();
			nickname = input;
			out.writeChars("Bonjour " + nickname + "\n");
			ci.setNickname(nickname);
			while( !(input = in.readLine()).equals("exit")){
				dispatcher.send_message(input);
				
			}
			cl.close();
			
		} catch (IOException e) {
			System.out.println("Cannot read IO from socket");
			e.printStackTrace();
			
		}
		
		
		
	}
	
	
	
	
}
