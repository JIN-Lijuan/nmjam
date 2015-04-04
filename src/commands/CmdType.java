package commands;

public enum CmdType {
		// Nouvelle connection
	    CONNECT,
	    // NULL
	    NULL,
	    // COMMANDE VIDE
	    EMPTY,
	    // COMMANDE MALFORME
	    MALFORMED,
	    // DECONEXION
	    EXIT,
	    // BUFFER_AUDIO
	    AUDIO_CHUNK,
	    // BONNE RECEPTION BUFFER
	    AUDIO_ACK
	    
}
	

