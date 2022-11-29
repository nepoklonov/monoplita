package utils.geometry

import kotlin.math.absoluteValue

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

    val square: Double
        get() = asPairSequence().sumOf { (p1, p2) ->
            val (x1, y1) = p1
            val (x2, y2) = p2
            x1 * y2 - y1 * x2
        }.absoluteValue / 2

    fun toSvgPath() = points.joinToString(separator = " ") { (x, y) -> "$x,$y" }

    private fun asPairSequence(): Sequence<Pair<Point, Point>> {
        if (isNotEmpty()) return (points.asSequence() + points[0]).zipWithNext()
        return emptySequence()
    }

    private fun isNotEmpty() = points.isNotEmpty()
}
