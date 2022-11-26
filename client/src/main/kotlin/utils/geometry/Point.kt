package utils.geometry

import kotlin.math.hypot

data class Point(
    val x: Double,
    val y: Double
) {
    fun distanceTo(other: Point): Double {
        return hypot(x - other.x, y - other.y)
    }
}