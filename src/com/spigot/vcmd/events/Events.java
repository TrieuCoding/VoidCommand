package com.spigot.vcmd.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import com.spigot.vcmd.Config;
import com.spigot.vcmd.Main;

public class Events implements Listener {

	public static Main plugin;

	@SuppressWarnings("static-access")
	public Events(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerFallVoid(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			String name = p.getName();

			if (p.isOp()) {
				return;
			}

			if (p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR)) {
				return;
			}

			if (e.getCause().equals(DamageCause.VOID)) {
				p.teleport(p.getLocation().getWorld().getSpawnLocation());
				for (String command : Config.getConfig().getStringList("commands.player")) {
					plugin.getServer().dispatchCommand(p, command
							.replace("{player}", name));
				}
				for (String command : Config.getConfig().getStringList("commands.console")) {
					plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command
							.replace("{player}", name));
				}
				e.setCancelled(true);
			}
		}
	}

}
