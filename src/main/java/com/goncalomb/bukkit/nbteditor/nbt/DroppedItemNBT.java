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

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import com.goncalomb.bukkit.bkglib.reflect.NBTUtils;

public class DroppedItemNBT extends ItemsNBT {
	
	public void setItem(ItemStack item) {
		if (item == null) {
			_data.remove("Item");
		} else {
			_data.setCompound("Item", NBTUtils.nbtTagCompoundFromItemStack(item));
		}
	}
	
	public ItemStack getItem() {
		if (_data.hasKey("Item")) {
			return NBTUtils.itemStackFromNBTTagCompound(_data.getCompound("Item"));
		}
		return null;
	}
	
	public boolean isSet() {
		return _data.hasKey("Item");
	}
	
	@Override
	public Entity spawn(Location location) {
		if (_data.hasKey("Item")) {
			Entity entity = location.getWorld().dropItem(location, getItem());
			NBTUtils.setEntityNBTTagCompound(entity, _data);
			return entity;
		}
		return null;
	}
	
}
