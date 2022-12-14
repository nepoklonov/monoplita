package utils.geometry

import kotlin.math.*

data class Angle(
    val left: Point,
    val center: Point,
    val right: Point
) {
    /** Angle measure in radians */
    val measureRad: Double
        get() {
            val a = center vec left
            val b = center vec right

            return acos(
                (a.x * b.x + a.y * b.y) /
                        (a.distanceTo(Point.zero) * b.distanceTo(Point.zero))
            )
        }

    val measureDeg: Double
        get() = measureRad.toDegrees()

    val leftAngle
        get() = vectorAngle(center, left)

    val rightAngle
        get() = vectorAngle(center, right)

    /** In degrees */
    val startAngle: Double
        get() = min(leftAngle, rightAngle)

    /** In degrees */
    val endAngle: Double
        get() = max(leftAngle, rightAngle)
}