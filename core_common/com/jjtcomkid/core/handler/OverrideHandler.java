package com.jjtcomkid.core.handler;

import java.lang.reflect.Field;

import net.minecraft.block.Block;

import com.jjtcomkid.core.Core;

/**
 * Core
 *
 * @author jjtcomkid
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class OverrideHandler {
	public static String getBlockName(Block block) {
		try {
			for (Field blockField : Block.class.getDeclaredFields()) {
				if (blockField.getType() == Block.class && blockField.get(blockField).equals(block))
					return blockField.getName();
			}
		} catch (Exception e) {
		}
		Core.logger.severe("Unable to retrive object name of " + block.toString());
		return "";
	}

	public static boolean replaceBlock(Block oldBlock, Block newBlock) {
		String oldBlockName = getBlockName(oldBlock);
		try {
			Field blockField = Block.class.getDeclaredField(oldBlockName);
			blockField.setAccessible(true);
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(blockField, blockField.getModifiers() & 0xFFFFFFEF);
			blockField.set(Block.class, newBlock);
			return true;
		} catch (Exception e) {
		}
		Core.logger.severe("Unable to replace " + oldBlock.toString() + " with " + newBlock.toString());
		return false;
	}
}
