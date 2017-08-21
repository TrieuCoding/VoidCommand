package com.spigot.vcmd;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private static FileConfiguration cf = null;
	
	private static File customCf = null;
	
	public static void reloadConfig() {
		if (customCf == null) {
			customCf = new File(Main.getPlugin().getDataFolder(), "config.yml");
		}
		cf = YamlConfiguration.loadConfiguration(customCf);
		try {
			Reader defaultConfig = new InputStreamReader(Main.getPlugin().getResource("config.yml"), "UTF8");
			if (defaultConfig != null) {
		        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defaultConfig);
		        cf.setDefaults(defConfig);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getConfig() {
		if (cf == null) {
			reloadConfig();
		}
		return cf;
	}
	
	public static void saveConfig() {
		if (cf == null || customCf == null) {
			return;
		}
		try {
			getConfig().save(customCf);
		} catch (IOException e) {
			System.out.println("[SecretPassword] Could not save config to " + customCf);
		}
	}
	
	public static void saveDefaultConfig() {
		if (customCf == null) {
			customCf = new File(Main.getPlugin().getDataFolder(), "config.yml");
		}
		if (!customCf.exists()) {
			Main.getPlugin().saveResource("config.yml", false);
		}
	}
	
}
