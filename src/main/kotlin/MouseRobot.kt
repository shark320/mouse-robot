package com.vpavlov.mouse_robot

import com.vpavlov.mouse_robot.utils.CustomProperties
import org.apache.logging.log4j.kotlin.logger
import org.apache.logging.log4j.kotlin.loggerOf
import java.awt.MouseInfo
import java.awt.Point
import java.awt.Robot
import java.awt.Toolkit
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.reflect.KProperty
import kotlin.system.exitProcess

class MouseRobot {

    companion object{
        private val LOGGER = loggerOf(MouseRobot::class.java)

        private const val PROP_FILE_NAME = "config/mouse-robot.properties"

        private val IDLE_TIME_KEY = CustomProperties.PropertyLongKey("idleTime", 2000L)

        private val IDLE_CHECK_PERIOD_KEY = CustomProperties.PropertyLongKey("idleCheckPeriod", 1000L)

        private val MOVEMENT_DELAY_KEY = CustomProperties.PropertyLongKey("movementDelay", 1000L)

        private val MOVEMENT_DISTANCE_KEY = CustomProperties.PropertyIntKey("movementDistance", 100)
    }

    private val properties = CustomProperties()

    private var lastIdleTime: Long = System.currentTimeMillis()

    @Volatile
    private var lastMousePosition = getMousePosition()

    private val robot: Robot = Robot()

    private val screenSize = Toolkit.getDefaultToolkit().screenSize

    private val random = Random()

    init {
        loadProperties()
    }

    private fun loadProperties() {
        try {
            val propertiesFile = File(PROP_FILE_NAME)
            if (propertiesFile.exists()) {
                FileInputStream(propertiesFile).use { inputStream ->
                    properties.load(inputStream)
                }
                LOGGER.debug("Properties loaded successfully.")
            } else {
                LOGGER.debug("Properties file not found: $PROP_FILE_NAME. Defaults will be used.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Failed to load properties.")
        }
    }


    private fun getMousePosition(): Point {
        return MouseInfo.getPointerInfo().location
    }

    fun start() {
        try {
            lastMousePosition = getMousePosition()
            lastIdleTime = System.currentTimeMillis()

            println("Mouse movement monitoring started. Press Ctrl+C to stop.")

            Timer().scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    val currentMousePosition = getMousePosition()
                    if (currentMousePosition != lastMousePosition) {
                        LOGGER.trace("Mouse position changed.")
                        lastMousePosition = currentMousePosition
                        lastIdleTime = System.currentTimeMillis()
                    } else {
                        LOGGER.trace("Mouse is still idle.")
                    }
                }
            }, 0, IDLE_CHECK_PERIOD_KEY.get(properties)!!)

            thread {
                while (true) {
                    if (isIdleTimeout()) {
                        LOGGER.trace("Simulating mouse movement...")
                        moveMouse()
                    }
                    Thread.sleep(MOVEMENT_DELAY_KEY.get(properties)!!)
                }
            }.join()
        } catch (e: Exception) {
            e.printStackTrace()
            exitProcess(1)
        }
    }

    private fun isIdleTimeout(): Boolean {
        return System.currentTimeMillis() - lastIdleTime >= IDLE_TIME_KEY.get(properties)!!
    }

    private fun moveMouse() {
        val angle = random.nextDouble(0.0, 2 * PI)
        val currentPos = getMousePosition()
        val distance = MOVEMENT_DISTANCE_KEY.get(properties)!!
        val x = (currentPos.x + distance * cos(angle)).toInt()
        val y = (currentPos.y + distance * sin(angle)).toInt()
        robot.mouseMove(x, y)
        lastMousePosition = getMousePosition()
    }
}