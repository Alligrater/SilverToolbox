package io.github.Alligrater;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SilverToolbox extends JavaPlugin {
	
	public static HashMap<UUID, Boolean> notvisible = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> cmode = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, String> target = new HashMap<UUID, String>();
	public static HashMap<UUID, Boolean> silentcmd = new HashMap<UUID, Boolean>();
	
	@Override
	public void onEnable() {
		this.getCommand("toolbox").setExecutor(new PatchFix());
		this.getCommand("manage").setExecutor(new ManagePlayer());
		getServer().getPluginManager().registerEvents(new PatchFix(), this);
		
		
				
		BukkitRunnable showontoolbar = new BukkitRunnable() {

			@Override
			public void run() {
				for(UUID u : cmode.keySet()) {
					if(cmode.get(u) == true && Bukkit.getPlayer(u) != null) {
						TextComponent tx = new TextComponent("¡ìc¡ìlImpost Mode Active.");
						Bukkit.getPlayer(u).spigot().sendMessage(ChatMessageType.ACTION_BAR, tx);
					}
				}
				
				for(UUID u : notvisible.keySet()) {
					if(notvisible.get(u) == true && Bukkit.getPlayer(u) != null) {
						TextComponent tx = new TextComponent("¡ìc¡ìlYou Are Now Invisible.");
						Bukkit.getPlayer(u).spigot().sendMessage(ChatMessageType.ACTION_BAR, tx);
					}
				}
				
				for(UUID u : silentcmd.keySet()) {
					if(silentcmd.get(u) == true && Bukkit.getPlayer(u) != null) {
						TextComponent tx = new TextComponent("¡ìc¡ìlSilent Command Mode Active.");
						Bukkit.getPlayer(u).spigot().sendMessage(ChatMessageType.ACTION_BAR, tx);
					}
				}
				
			}
			
		};
		
		showontoolbar.runTaskTimer(this, 0, 40);
	}
	
	@Override
	public void onDisable() {
		
	}
}
