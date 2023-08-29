package Data

import Utils.toRad
import java.awt.Graphics2D
import java.awt.Point

sealed class CrosshairType {
    data object Standart: CrosshairType() {
        override val index = 0
        private val angles = arrayOf(0.0, 90.0, 90.0, 90.0)
        override fun Graphics2D.draw(center: Point, offset: Int, size: Int) {
            angles.forEach { degrees ->
                rotate(degrees.toRad(), center.x.toDouble(), center.y.toDouble())
                drawLine(
                    center.x-offset,
                    center.y,
                    (center.x-offset-size).coerceIn(0, center.x-offset),
                    center.y
                )
            }
        }
    }

    data object TShaped: CrosshairType() {
        override val index = 1
        private val angles = arrayOf(0.0, 180.0, 90.0)
        override fun Graphics2D.draw(center: Point, offset: Int, size: Int) {
            angles.forEach { degrees ->
                rotate(degrees.toRad(), center.x.toDouble(), center.y.toDouble())
                drawLine(
                    center.x-offset,
                    center.y,
                    (center.x-offset-size).coerceIn(0, center.x-offset),
                    center.y
                )
            }
        }

    }

    data object Triangle: CrosshairType() {
        override val index = 2
        private val angles = arrayOf(-30.0, 120.0, 120.0)
        override fun Graphics2D.draw(center: Point, offset: Int, size: Int) {
            angles.forEach { degrees ->
                rotate(degrees.toRad(), center.x.toDouble(), center.y.toDouble())
                drawLine(
                    center.x-offset,
                    center.y,
                    (center.x-offset-size).coerceIn(0, center.x-offset),
                    center.y
                )
            }
        }
    }

    /*class ArkSegments: CrosshairTypes() {
        private val angles = arrayOf(45.0, 90.0, 90.0, 90.0)
        override fun Graphics2D.draw(center: Point, offset: Int, size: Int) {
            angles.forEach { degrees ->
                rotate(degrees.toRad(), center.x.toDouble(), center.y.toDouble())
                drawArc(center.x-size/2, center.y-size/2, size, size, 5, 85)
            }
        }
    }*/

    data object Circle: CrosshairType() {
        override val index = 3
        override fun Graphics2D.draw(center: Point, offset: Int, size: Int) {
            val halfSize = size/2
            drawArc(center.x-halfSize, center.y-halfSize, size, size, 0, 360)
        }
    }

    abstract val index: Int
    abstract fun Graphics2D.draw(center: Point, offset: Int, size: Int)
}