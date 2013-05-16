package com.jjtcomkid.core.handler;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class LogHandler {
	private Logger logger;

	public LogHandler(String name) {
		logger = Logger.getLogger(name);
		logger.setParent(FMLLog.getLogger());
	}

	public void info(String message) {
		logger.log(Level.INFO, message);
	}

	public void fine(String message) {
		logger.log(Level.FINE, message);
	}

	public void finer(String message) {
		logger.log(Level.FINER, message);
	}

	public void finest(String message) {
		logger.log(Level.FINEST, message);
	}

	public void warning(String message) {
		logger.log(Level.WARNING, message);
	}

	public void severe(String message) {
		logger.log(Level.SEVERE, message);
	}

}
