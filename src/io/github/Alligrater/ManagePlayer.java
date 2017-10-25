package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class ManagePlayer implements TabExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Player) {
			if(arg3.length == 1) {
				Player player = (Player) arg0;
				if(arg0.hasPermission("SilverToolbox.Tool")){
					PatchFix.openPM(player, arg3[0]);
				}
				else {
					arg0.sendMessage("¡ìc¡ìlDon't.");
				}

			}
			else {
				return false;
			}

		}
		else {
			arg0.sendMessage("¡ìc¡ìlDon't.");
			return true;
		}
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Player) {
			if(arg3.length == 1) {
				Player player = (Player) arg0;
				if((arg0.hasPermission("SilverToolbox.Tool") || player.getUniqueId() == Bukkit.getOfflinePlayer("SilverKela").getUniqueId())){
					String name = arg3[0].toLowerCase();
					List<String> c = new ArrayList<String>();
					for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
						if(p.getName().toLowerCase().startsWith(name)) {
							if(p.isOnline()) {
								c.add(0, p.getName());
							}
							else {
								c.add(p.getName());
							}

						}
					}
		            return c;
				}
				
			}
		}
		return null;
	}

}
