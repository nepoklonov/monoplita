package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag
import utils.geometry.Angle
import utils.geometry.describeArc
import utils.geometry.polarToCartesian

const val angleRadius = 10.0

fun RDOMBuilder<SVG>.angle(
    angle: Angle,
    block: RDOMBuilder<ARC>.() -> Unit = {}
) {
    arc {
        attrs.d = describeArc(angle.center, angleRadius, angle.leftAngle, angle.rightAngle)
        attrs.stroke = "black"
        attrs.strokeOpacity = "0.7"
        attrs.fill = "green"
        attrs.fillOpacity = "0.35"
        block()
    }
    text("angle-text") {
        val point = polarToCartesian(angle.center, angleRadius, (angle.leftAngle + angle.rightAngle) / 2)
        attrs.x = point.x.toString()
        attrs.y = point.y.toString()
        +angle.measureDeg.toInt().toString()
    }
}

fun RDOMBuilder<SVG>.arc(
    classes: String? = null,
    block: RDOMBuilder<ARC>.() -> Unit = {}
) = tag(block) { ARC(attributesMapOf("class", classes), it)}

open class ARC(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("path", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var d: String by StringAttribute()
    var fill: String by StringAttribute()
    var strokeOpacity: String by StringAttribute()
    var fillOpacity: String by StringAttribute()
    var stroke: String by StringAttribute()
}

