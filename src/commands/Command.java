package commands;

import java.util.ArrayList;

public class Command{
	private String cmd;
	private CmdType type;
	private ArrayList<String> args;
	private String error;

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
		if( cmd.isEmpty() ) 
			this.type = CmdType.EMPTY;
		else {
			if( !cmd.matches("[A-Z]*/([a-zA-Z]*/)*") ){
				// COMMAND NOT CONTAINS '/'
				this.type = CmdType.MALFORMED;
				this.error = "Malformed Command.";

			}else{
				// COMMAND CONTAINS '/'
				String[] parts = cmd.split("/");
				if ( parts[0].equals("CONNECT") )
					parseArgs(parts, CmdType.CONNECT, 2);
				else if ( parts[0].equals("EXIT") )
					parseArgs(parts, CmdType.EXIT, 2);
			}
		}
	}



	private void parseArgs(String[] parts, CmdType type, int argsMax){

		if( parts.length > argsMax){
			this.type = CmdType.MALFORMED;
			this.error = type.toString() + "Require only one argument!";
		}else{
			this.type = type;
			for (int i = 1; i < parts.length; i++)
				this.args.add(parts[1]);

		}

	}



	public  CmdType getType() {
		return type;

	}

	public String getError(){
		return error;
	}

}

