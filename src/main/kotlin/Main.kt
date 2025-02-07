package com.vpavlov.mouse_robot

import java.awt.Robot
import java.awt.Toolkit
import java.awt.event.InputEvent
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI
import kotlin.concurrent.thread
import kotlin.system.exitProcess

fun main() {
    try {
        val robot = Robot()
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        val centerX = screenSize.width / 2
        val centerY = screenSize.height / 2
        val radius = 100
        val steps = 360
        val delay = 10L  // Delay between movements

        println("Mouse movement started. Press Ctrl+C to stop.")

        thread {
            while (true) {
                for (i in 0 until steps) {
                    val angle = 2 * PI * i / steps
                    val x = (centerX + radius * cos(angle)).toInt()
                    val y = (centerY + radius * sin(angle)).toInt()
                    robot.mouseMove(x, y)
                    Thread.sleep(delay)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        exitProcess(1)
    }
}
