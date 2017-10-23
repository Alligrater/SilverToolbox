package io.github.Alligrater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManagePlayer implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Player && arg0.hasPermission("SilverToolbox.Tool")) {
			if(arg3.length == 1) {
				Player player = (Player) arg0;
				PatchFix.openPM(player, arg3[0]);
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
