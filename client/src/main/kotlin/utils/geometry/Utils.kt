package utils.geometry

import kotlin.math.*

const val eps = 0.0001

infix fun Number.p(other: Number) = Point(this.toDouble(), other.toDouble())

infix fun Point.vec(other: Point): Point {
    return other.x - this.x p other.y - this.y
}

infix fun Double.eq(other: Double): Boolean {
    return abs(this - other) < eps
}

fun Double.toDegrees(): Double = this * 180.0 / PI

fun vectorAngle(point1: Point, point2: Point): Double {
    val vector = point1 vec point2
    val rad = Angle(vector, Point.zero, Point.unit).measureRad * vector.y.sign
    return rad.toDegrees()
}

fun polarToCartesian(point: Point, radius: Double, angleInDegrees: Double): Point {
    val angleInRadians = (angleInDegrees) * PI / 180.0

    return Point(point.x + (radius * cos(angleInRadians)),
        point.y + (radius * sin(angleInRadians)))
}

fun describeArc(point: Point, radius: Double, startAngle: Double, endAngle: Double): String {
    val start = polarToCartesian(point, radius, endAngle)
    val end = polarToCartesian(point, radius, startAngle)

    val largeArcFlag = "1"/*if (endAngle >= startAngle) {
        if (endAngle - startAngle <= 180) "0" else "1"
    } else {
        if ((endAngle + 360.0) - startAngle <= 180) "0" else "1"
    }*/

    return listOf(
        "M", point,
        "L", start,
        "A", radius, radius, 0, largeArcFlag, 0, end,
        "L", point
    ).joinToString(" ")
}