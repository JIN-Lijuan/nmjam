package commands;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Command{
	private String cmd;
	private CmdType type;
	private ArrayList<String> args;
	private String error;
	
	private final static Logger LOGGER = 
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


	public Command(String cmd) {
		this.error = "";
		this.type = CmdType.NULL;
		this.cmd = cmd;
		this.parse();
		this.args = new ArrayList<String>();

	}

	public ArrayList<String> getArgs(){
		return args;
	}

	private  void parse() {
		LOGGER.info("Parsing " + this.cmd);
		if( cmd.isEmpty() ){
			this.type = CmdType.EMPTY;
			LOGGER.finest("Empty! ");
		}else {
			if( !cmd.matches("[A-Z]*/([a-zA-Z]*/)*") ){
				// COMMAND NOT CONTAINS '/'
				this.type = CmdType.MALFORMED;
				this.error = "Malformed Command.";
				LOGGER.info("Malformed ");

			}else{
				// COMMAND CONTAINS '/'
				String[] parts = cmd.split("/");
				if ( parts[0].equals("CONNECT") ){
					parseArgs(parts, CmdType.CONNECT, 1);
					LOGGER.finest("Connect detected ");
				}else if ( parts[0].equals("EXIT") ){
					parseArgs(parts, CmdType.EXIT, 1);
					LOGGER.finest("Malformed ");
				}
			}
		}
	}



	private void parseArgs(String[] parts, CmdType type, int argsMax){
		LOGGER.finest("Start parsing.. " + this.cmd);
		if( parts.length > argsMax){
			this.type = CmdType.MALFORMED;
			this.error = type.toString() + "Require only one argument!";
			LOGGER.finest("Too many args.. " + this.cmd);
		}else{
			this.type = type;
			for (int i = 1; i < parts.length; i++)
				this.args.add(parts[1]);
			LOGGER.finest("Args stored " + this.cmd);

		}

	}



	public  CmdType getType() {
		return type;

	}

	public String getError(){
		return error;
	}

}

