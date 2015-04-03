package commands;

import java.util.ArrayList;

public class Command{
	private String cmd;
	private CmdType type;
	private ArrayList<String> args;

	public Command(String cmd) {
		this.type = CmdType.NULL;
		this.cmd = cmd;
		this.parse();
	}


	private  void parse() {
		if( cmd.isEmpty() ) 
			this.type = CmdType.EMPTY;

	}

	public  CmdType getType() {
		return type;

	}

}

