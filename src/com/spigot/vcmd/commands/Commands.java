package com.spigot.vcmd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.spigot.vcmd.Config;
import com.spigot.vcmd.Main;

public class Commands implements CommandExecutor {

	public static Main plugin;
	
	@SuppressWarnings("static-access")
	public Commands(Main plugin) { this.plugin = plugin; }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender.hasPermission("void.command")) {
			if (args.length < 1) {
				if (sender instanceof Player) {
					sender.sendMessage("/voidcommand reload");
					sender.sendMessage("Aliases: /vcmd");
				} else {
					sender.sendMessage(plugin.cslprefix + "/voidcommand reload");
					sender.sendMessage(plugin.cslprefix + "Aliases: /vcmd");
				}
				return true;
			} else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				if (sender instanceof Player) {
					Config.reloadConfig();
					sender.sendMessage(Config.getConfig().getString("prefix").replace("&", "�")
							 + Config.getConfig().getString("reload").replace("&", "�"));
				} else {
					Config.reloadConfig();
					sender.sendMessage(plugin.cslprefix
							 + ChatColor.GREEN + "Reload config!");
				}
			} else {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.RED + "Unknow args! Please use /voidcommand for help!");
				} else {
					sender.sendMessage(plugin.cslprefix
							 + ChatColor.RED + "Unknow args! Please use /voidcommand for help!");
				}
				return true;
			}
		} else {
			sender.sendMessage(Config.getConfig().getString("prefix").replace("&", "�")
					+ Config.getConfig().getString("no-permissions").replace("&", "�"));
			return true;
		}
		return true;
	}

}
