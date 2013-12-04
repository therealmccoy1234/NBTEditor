package com.goncalomb.bukkit.nbteditor.tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.material.MaterialData;

import com.goncalomb.bukkit.customitems.api.CustomItem;
import com.goncalomb.bukkit.customitems.api.PlayerDetails;

public final class EntityRemoverTool extends CustomItem {

	public EntityRemoverTool() {
		super("entity-remover", ChatColor.AQUA + "Entity Remover", new MaterialData(Material.BLAZE_ROD));
		setLore(ChatColor.YELLOW + "Right-click on entities remove them.");
	}
	
	@Override
	public void onInteractEntity(PlayerInteractEntityEvent event, PlayerDetails details) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if (entity.getType() != EntityType.PLAYER) {
			event.getRightClicked().remove();
			event.setCancelled(true);
		} else {
			player.sendMessage(ChatColor.RED + "You cannot remove players!");
		}
	}
	
	@Override
	public void onDrop(PlayerDropItemEvent event) {
		event.getItemDrop().remove();
	}

}