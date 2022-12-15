package utils.geometry

import kotlin.math.max
import kotlin.math.min

data class Segment(
    val start: Point = Point.zero,
    val end: Point
) {
    val xRange: ClosedFloatingPointRange<Double>
        get() = min(start.x, end.x)..max(start.x, end.x)

    val yRange: ClosedFloatingPointRange<Double>
        get() = min(start.y, end.y)..max(start.y, end.y)

    val coordinates: Point
        get() = start vec end

    val line = Line(start, end)

    val ray = Ray(this)
}