package chat;

import java.util.logging.Logger;


/**
 * Jam session informations.
 *
 */
public class SessionInfo {
	
	private String style;
	private int tempo;
	private int nbUsers;
	private boolean initState;
	private final static Logger LOGGER = 
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public SessionInfo() {
		this.initState = false;
		this.style = "";
		this.tempo = -1;
		this.nbUsers = 0;
		
	}
	
	public boolean initialized(){
		return this.initState;
		
	}
	
	public boolean setInitState(boolean state){
		return this.initState=state;
		
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		LOGGER.finer("Setting style.");
		this.style = style;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		LOGGER.finer("Setting tempo.");
		this.tempo = tempo;
	}
	
	public int getNbUsers() {
		return nbUsers;
	}
	
	public void addUser(){
		LOGGER.info("Adding user to session.");
		this.nbUsers++;
		
	}
	
	public void removeUser(){
		LOGGER.info("Removing user to session.");
		this.nbUsers--;
		
	}
	
	
	
	
}
