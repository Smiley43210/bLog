package com.beecub.bLog;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class bLogPlayerListener implements Listener {
	@SuppressWarnings("unused")
	private final bLog plugin;
	static Logger commlog;
	static Logger chatlog;

	public bLogPlayerListener(bLog instance) {
		plugin = instance;
	}

	@EventHandler (priority = EventPriority.LOW)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        String message = event.getMessage();
        
        int i = message.indexOf(' ');
        if(i < 0) { i = message.length() - 1; }
        
        // into chatlog?
        if (bConfigManager.isToChat((String) message.subSequence(0, i))) {
        	chatlog.info(event.getPlayer().getName() + ": " + message);
        }
        
        // into commandlog?
        if(bConfigManager.isNoCommand((String) message.subSequence(0, i))) {
        	return;
        }
        else {
        	commlog.info(event.getPlayer().getName() + ": " + message);
        }
    }

	@EventHandler (priority = EventPriority.LOW)
	public void onPlayerChat(PlayerChatEvent event) {
		if (event.isCancelled()) {
            return;
        }
        String message = event.getMessage();
        chatlog.info(event.getPlayer().getName() + ": " + message);
        bHTML.writeToFile(event.getPlayer().getName(), message);
	}
	
}