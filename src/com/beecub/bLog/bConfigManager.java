package com.beecub.bLog;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.configuration.Configuration;

public class bConfigManager {
	
	protected static bLog bLog;
    protected static Configuration conf;
    protected File confFile;
    
    static List<String> toChat = new LinkedList<String>();
    static List<String> noCommand = new LinkedList<String>(); 
	
	@SuppressWarnings("static-access")
	public bConfigManager(bLog bLog) {
    	this.bLog = bLog;

    	File f = new File(bLog.getDataFolder(), "config.yml");
    	conf = bLog.getConfig();

        if (!f.exists()){
    		conf.options().copyDefaults(true);
            bLog.saveConfig();
        }
        new File(bLog.getDataFolder() + "/ChatLog/").mkdir();
        new File(bLog.getDataFolder() + "/CommandLog/").mkdir();
    }    
    
	private static void load() {
    	toChat();
    	noCommand();
    }
	
	public static void reload() {
		load();
	}
	
	private static void toChat() {
		toChat.clear();
		toChat = conf.getStringList("toChat.");
	}
	
	private static void noCommand() {
		noCommand.clear();
		noCommand = conf.getStringList("noCommand.");
	}
	
	public static boolean isToChat(String message) {
		if(toChat.contains(message)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNoCommand(String message) {
		if(noCommand.contains(message)) {
			return true;
		}
		return false;
	}
}
