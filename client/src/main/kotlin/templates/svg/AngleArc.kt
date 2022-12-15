package templates.svg

import kotlinx.html.SVG
import react.dom.RDOMBuilder
import utils.geometry.*
import utils.log
import utils.svg.ARC
import utils.svg.arc
import kotlin.math.roundToInt

const val angleArcRadius = 10.0

fun RDOMBuilder<SVG>.angleArc(
    pointTriple: PointTriple,
    sign: Double,
    block: RDOMBuilder<ARC>.() -> Unit = {}
) {
    arc {
        attrs.d = describeArc(pointTriple, angleArcRadius, sign)
        attrs.stroke = "black"
        attrs.strokeOpacity = "0.7"
        attrs.fill = "white"
        attrs.fillOpacity = "0.5"
        block()
    }

    val sortedTriple = if (sign == 1.0) pointTriple else pointTriple.reverse()
    val firstVector = sortedTriple.second.directionTo(sortedTriple.first).polarCoordinates()
    val angle = ((sign * pointTriple.angle.degrees + 360) % 360).deg
    firstVector.phi.degrees.log()
    val polarCoordinates = PolarCoordinates(angleArcRadius * 2, (firstVector.phi - angle / 2))
    val point = pointTriple.second + polarCoordinates

    geometryText(point, angle.degrees.roundToInt().toString())
}

fun describeArc(pointTriple: PointTriple, radius: Double, sign: Double): String {
    val (first, second, third) = if (sign == 1.0) pointTriple else pointTriple.reverse()
    val start = second + second.directionTo(first).normalize() * radius
    val end = second + second.directionTo(third).normalize() * radius

    val largeArcFlag = if (sign * pointTriple.angle.degrees > 0) "0" else "1"

    return listOf(
        "M", second,
        "L", start,
        "A", radius, radius, 0, largeArcFlag, 0, end,
        "L", second
    ).joinToString(" ")
}
