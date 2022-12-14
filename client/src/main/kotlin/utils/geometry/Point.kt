package utils.geometry

import kotlin.math.hypot

data class Point(
    val x: Double,
    val y: Double
) {
    fun distanceTo(other: Point): Double {
        return hypot(x - other.x, y - other.y)
    }

    override fun toString(): String {
        return "$x, $y"
    }

    companion object {
        val zero = Point(0.0, 0.0)
        val unit = Point(1.0, 0.0)
    }
}