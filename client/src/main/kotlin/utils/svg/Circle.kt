package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag

fun RDOMBuilder<SVG>.circle(
    cx: Number, cy: Number, r: Number,
    block: RDOMBuilder<CIRCLE>.() -> Unit = {}
) = circle {
    attrs.cx = cx.toDouble()
    attrs.cy = cy.toDouble()
    attrs.r = r.toDouble()
    block()
}

inline fun RDOMBuilder<SVG>.circle(
    classes: String? = null,
    block: RDOMBuilder<CIRCLE>.() -> Unit
) = tag(block) { CIRCLE(attributesMapOf("class", classes), it) }

open class CIRCLE(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) :
    HTMLTag("circle", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var cx: Double by DoubleAttribute()
    var cy: Double by DoubleAttribute()
    var r: Double by DoubleAttribute()
    var fill: String by StringAttribute()
}