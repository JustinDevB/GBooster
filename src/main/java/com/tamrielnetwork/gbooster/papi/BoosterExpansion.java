package com.tamrielnetwork.gbooster.papi;

import com.tamrielnetwork.gbooster.GBooster;
import com.tamrielnetwork.gbooster.boosters.Booster;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BoosterExpansion extends PlaceholderExpansion {

	private final GBooster main = JavaPlugin.getPlugin(GBooster.class);

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public @NotNull String getAuthor() {
		return "Manu, TamrielNetwork";
	}

	@Override
	public @NotNull String getIdentifier() {
		return "gbooster";
	}

	@Override
	public @NotNull String getVersion() {
		return "1.2.4";
	}

	@Override
	public String onRequest(OfflinePlayer player, @NotNull String identifier) {

		for (Booster booster : main.getBoostersManager().getBoosters()) {
			if (identifier.equals(booster.getId())) {

				return String.valueOf(
						main.getPlayerStorage()
								.getBoosterPlayerByUUID(player.getUniqueId())
								.getBoostersStorage()
								.getOrDefault(identifier, 0));
			}
		}

		return "0";
	}
}
