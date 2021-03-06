/*
 * Copyright (C) 2013 - Gonçalo Baltazar <http://goncalomb.com>
 *
 * This file is part of NBTEditor.
 *
 * NBTEditor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NBTEditor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NBTEditor.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.goncalomb.bukkit.nbteditor.bos;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.goncalomb.bukkit.bkglib.Lang;
import com.goncalomb.bukkit.nbteditor.NBTEditor;
import com.goncalomb.bukkit.nbteditor.nbt.FireworkNBT;

public final class InventoryForFirework extends InventoryForSingleItem {
	
	private static HashMap<Integer, ItemStack> _placeholders = new HashMap<Integer, ItemStack>();
	
	static {
		_placeholders.put(4, createPlaceholder(Material.PAPER, Lang._(NBTEditor.class, "bos.firework.pholder")));
	}
	
	private BookOfSouls _bos;
	
	public InventoryForFirework(BookOfSouls bos, Player owner) {
		super(Lang._(NBTEditor.class, "bos.firework.title"), _placeholders, ((FireworkNBT) bos.getEntityNBT()).getFirework(), bos, owner);
		_bos = bos;
	}
	
	@Override
	protected void inventoryClick(InventoryClickEvent event) {
		super.inventoryClick(event);
		ItemStack itemToCheck = getItemToCheck(event);
		if (itemToCheck != null && itemToCheck.getType() != Material.FIREWORK) {
			((Player)event.getWhoClicked()).sendMessage(Lang._(NBTEditor.class, "bos.firework.nop"));
			event.setCancelled(true);
		}
	}
	
	@Override
	protected void inventoryClose(InventoryCloseEvent event) {
		((FireworkNBT) _bos.getEntityNBT()).setFirework(getContents()[4]);
		_bos.saveBook();
		((Player)event.getPlayer()).sendMessage(Lang._(NBTEditor.class, "bos.firework.done"));
	}

}
