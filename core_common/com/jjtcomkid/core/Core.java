package com.jjtcomkid.core;

import com.jjtcomkid.core.handler.LogHandler;

import cpw.mods.fml.common.Mod;

/**
 * Core
 * 
 * @author jjtcomkid
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(modid = "JjtCore", name = "Jjt Core", version = "@VERSION@")
public class Core {

    public static final LogHandler logger = new LogHandler("Core");

}
