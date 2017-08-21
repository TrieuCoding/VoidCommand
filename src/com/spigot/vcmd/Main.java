package com.spigot.vcmd;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.spigot.vcmd.commands.Commands;
import com.spigot.vcmd.events.Events;
import com.spigot.vcmd.updater.Updater;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static Main instance;
	
	public String cslprefix = "[VoidCommand] ";
	
	@Override
	public void onEnable() {
		ConsoleCommandSender console = getServer().getConsoleSender();
		PluginDescriptionFile pdf = getDescription();
		plugin = this;
		instance = this;
		Updater.plugin = this;
		Events.plugin = this;
		Commands.plugin = this;
		Config.reloadConfig();
		Config.getConfig().options().copyDefaults(true);
		Config.saveConfig();
		registerListeners();
		registerCommands();
		console.sendMessage(cslprefix + "Plugin has been enabled!");
		if (Config.getConfig().getBoolean("check-update")) {
			Updater.print();
		}
	}
	
	private void registerListeners() { getServer().getPluginManager().registerEvents(new Events(this), this); }
	
	private void registerCommands() { getCommand("voidcommand").setExecutor(new Commands(this)); }
	
}
