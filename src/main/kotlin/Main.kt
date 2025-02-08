package com.vpavlov.mouse_robot

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.kotlin.logger
import java.awt.Robot
import java.util.*


fun main(args: Array<String>) {
    val isLoggingEnabled = args.contains("--enable-logging")
    System.setProperty("log4j.configurationFile", "config/log4j.xml")
    if (!isLoggingEnabled) {
        Configurator.setRootLevel(Level.OFF)
    }
    val logger = logger("main")
    logger.debug("Starting mouse robot...")
    val mouseRobot = MouseRobot()
    mouseRobot.start()
}
