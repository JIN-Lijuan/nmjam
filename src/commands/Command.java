package commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class Command{
	private String cmd;
	private CmdType type;
	private String arg1;
	private String arg2;
	private String error;
	
	private final static Logger LOGGER = 
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


	public Command(String cmd) {
		LOGGER.setLevel(Level.FINEST);
		this.error = "";
		this.type = CmdType.NULL;
		this.cmd = cmd;
		this.parse();
		this.arg1 = null;
		this.arg1 = null;

	}



	public String getArg1() {
		return arg1;
	}


	public String getArg2() {
		return arg2;
	}


	private  void parse() {
		LOGGER.info("Parsing " + this.cmd);
		if( cmd.isEmpty() ){
			this.type = CmdType.EMPTY;
			LOGGER.finest("Empty! ");
		}else {
			if( !cmd.matches(".+/(.+/)*")){
				// COMMAND NOT CONTAINS '/'
				this.type = CmdType.MALFORMED;
				this.error = "Malformed Command.\n";
				LOGGER.info("Malformed ");

			}else{
				String[] parts = cmd.split("/");

				switch( parts[0] ){
					case "CONNECT":
						parseArgs(parts, CmdType.CONNECT, 1);
						LOGGER.finest("Connect detected ");
						break;
					case "EXIT":
						parseArgs(parts, CmdType.EXIT, 1);
						LOGGER.finest("Exit detected ");
						break;
					case "AUDIO_ACK":
						parseArgs(parts, CmdType.AUDIO_ACK, 0);
						LOGGER.finest("Audio ack detected ");
						break;
					case "AUDIO_CHUNK":
						parseArgs(parts, CmdType.AUDIO_CHUNK, 2);
						LOGGER.finest("Audio chunk detected ");
						break;
						
				}
				LOGGER.finest("Command parsed.");
				
			}
		}
	}



	private void parseArgs(String[] parts, CmdType type, int argsMax){
		LOGGER.finest("Start parsing.. " + this.cmd);
		if( parts.length != argsMax+1 ){
			this.type = CmdType.MALFORMED;
			this.error = type.toString() + " Require " + argsMax + " argument/s!\n";
			LOGGER.info("Args problem " + this.cmd);
		}else{
			this.type = type;
			if( argsMax == 1 )
				this.arg1 = parts[1];
			if( argsMax == 2)
				this.arg1 = parts[2];

		}

	}



	public  CmdType getType() {
		return type;

	}

	public String getError(){
		return error;
	}

}

