/*
 * GBooster is a Spigot Plugin providing Global Boosters for Jobs McMMO and Minecraft.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/GBooster/blob/main/LICENSE
 */

package com.tamrielnetwork.gbooster.papi;

import com.tamrielnetwork.gbooster.GBooster;
import com.tamrielnetwork.gbooster.boosters.Booster;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BoosterExpansion
		extends PlaceholderExpansion {

	private final GBooster main = JavaPlugin.getPlugin(GBooster.class);

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public @NotNull String getAuthor() {
		return getAuthors();
	}

	@Override
	public @NotNull String getIdentifier() {
		return main.getDescription()
		           .getName();
	}

	@Override
	public @NotNull String getVersion() {
		return main.getDescription()
		           .getVersion();
	}

	@Override
	public String onRequest(@NotNull OfflinePlayer player, @NotNull String identifier) {
		for (Booster booster : main.getBoostersManager()
		                           .getBoosters()) {
			if (identifier.equals(booster.getId())) {
				return String.valueOf(main.getPlayerStorage()
				                          .getBoosterPlayerByUUID(player.getUniqueId())
				                          .getBoostersStorage()
				                          .getOrDefault(identifier, 0));
			}
		}
		return "0";
	}

	private String getAuthors() {
		List<String> authors = main.getDescription()
		                           .getAuthors();
		StringBuilder authorBuilder = new StringBuilder();
		for (String author : authors) {
			if (author.equals(authors.get(0))) {
				authorBuilder.append(author);
				continue;
			}
			authorBuilder.append(", ")
			             .append(author);
		}
		return authorBuilder.toString();
	}
}
