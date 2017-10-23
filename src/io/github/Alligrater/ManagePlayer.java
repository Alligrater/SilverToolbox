package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManagePlayer implements CommandExecutor{

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

}
