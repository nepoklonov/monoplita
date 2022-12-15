package utils.geometry

import kotlin.math.*

data class PointTriple(
    val first: Point,
    val second: Point,
    val third: Point
) {
    val angle: Angle
        get() {
            val a = second.directionTo(first)
            val b = second.directionTo(third)

            //when angle = 180, sign should not be equal to 0
            val sign = sign(a.y * b.x - a.x * b.y).takeIf { it != 0.0 } ?: 1
            return acos((a.x * b.x + a.y * b.y) / (a.abs() * b.abs())).radians * sign
        }
}