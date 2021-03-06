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

package com.goncalomb.bukkit.nbteditor.nbt;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.goncalomb.bukkit.bkglib.reflect.NBTTagCompoundWrapper;
import com.goncalomb.bukkit.bkglib.reflect.NBTTagListWrapper;
import com.goncalomb.bukkit.bkglib.reflect.NBTUtils;

public abstract class MinecartContainerNBT extends MinecartNBT {
	
	protected final void internalCopyFromChest(Block block, int count) {
		Inventory inv = ((Chest) block.getState()).getBlockInventory();
		NBTTagListWrapper items = new NBTTagListWrapper();
		for (int i = 0, l = count; i < l; ++i) {
			ItemStack item = inv.getItem(i);
			if (item != null) {
				NBTTagCompoundWrapper itemNBT = NBTUtils.nbtTagCompoundFromItemStack(item);
				itemNBT.setByte("Slot", (byte) i);
				items.add(itemNBT);
			}
		}
		_data.setList("Items", items);
	}
	
	public abstract void copyFromChest(Block block);
	
	public final void copyToChest(Block block) {
		Inventory inv = ((Chest) block.getState()).getBlockInventory();
		inv.clear();
		if (_data.hasKey("Items")) {
			NBTTagListWrapper items = _data.getList("Items");
			for (int i = 0, l = items.size(); i < l; ++i) {
				NBTTagCompoundWrapper itemNBT = (NBTTagCompoundWrapper) items.get(i);
				inv.setItem(itemNBT.getByte("Slot"), NBTUtils.itemStackFromNBTTagCompound(itemNBT));
			}
		}
	}
	
}
