package utils.geometry

import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

infix fun Number.p(other: Number) = Point(this.toDouble(), other.toDouble())
fun center(first: Point, second: Point) = Point((first.x + second.x) / 2, (first.y + second.y) / 2)

operator fun Point.plus(polarCoordinates: PolarCoordinates): Point {
    return Point(x + (polarCoordinates.r * cos(polarCoordinates.phi.radians)), y + (polarCoordinates.r * sin(polarCoordinates.phi.radians)))
}

fun Vector.polarCoordinates(): PolarCoordinates {
    val angle = ((PointTriple(Point(x, y), Point.zero, Point.one).angle.degrees + 360) % 360).deg
    return PolarCoordinates(abs(), angle)
}

fun PointTriple.reverse() = PointTriple(third, second, first)

//will be removed after merge
data class Vector(
    val x: Double,
    val y: Double
) {
    fun abs() = hypot(x, y)
    fun normalize() = Vector(x / abs(), y / abs())
    operator fun times(n: Number) = Vector(x * n.toDouble(), y * n.toDouble())
}

operator fun Point.plus(vector: Vector) = Point(x + vector.x, y + vector.y)

fun Point.directionTo(other: Point) = Vector(other.x - x, other.y - y)