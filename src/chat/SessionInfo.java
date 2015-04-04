package chat;


/**
 * Jam session informations.
 *
 */
public class SessionInfo {
	
	private String style;
	private int tempo;
	private int nbUsers;
	
	public SessionInfo(String style, int tempo, int nbUsers) {
		this.style = style;
		this.tempo = tempo;
		this.nbUsers = nbUsers;
		
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public int getNbUsers() {
		return nbUsers;
	}
	public void setNbUsers(int nbUsers) {
		this.nbUsers = nbUsers;
	}
	
	public void addUser(){
		this.nbUsers++;
		
	}
	
	public void removeUser(){
		this.nbUsers--;
		
	}
	
	
	
	
}
