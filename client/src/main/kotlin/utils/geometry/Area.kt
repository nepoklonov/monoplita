package utils.geometry

import kotlin.math.absoluteValue
//import kotlin.math.max
import kotlin.math.sign

data class Area(
    val points: List<Point> = emptyList()
) {
    fun addPoint(point: Point) = copy(points = points + point)
    fun movePoint(pointIndex: Int, newLocation: Point) = copy(
        points = points.toMutableList().also {
            it[pointIndex] = newLocation
        }
    )

    val perimeter: Double
        get() = asPairSequence().sumOf { (p1, p2) ->
            p1.distanceTo(p2)
        }

    private val signedSquare: Double
        get() = asPairSequence().sumOf { (p1, p2) ->
            val (x1, y1) = p1
            val (x2, y2) = p2
            x1 * y2 - y1 * x2
        } / 2

    val sign: Double get() = sign(signedSquare)

    val square: Double get() = signedSquare.absoluteValue

    fun toSvgPath() = points.joinToString(separator = " ")

    fun asPairSequence(): Sequence<Pair<Point, Point>> {
        if (points.size >= 2) return (points.asSequence() + points[0]).zipWithNext()
        return emptySequence()
    }

    fun asTripleSequence(): List<PointTriple> {
        if (points.size < 3)
            return listOf()

        var pointTriple = PointTriple(
            points[points.size - 3],
            points[points.size - 2],
            points[points.size - 1]
        )

        return points.map { point ->
            pointTriple = PointTriple(pointTriple.second, pointTriple.third, point)
            return@map pointTriple
        }
    }
}
