package utils.svg

import kotlinx.css.Color
import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RDOMBuilder<SVG>.line(
    x1: Number,
    y1: Number,
    x2: Number,
    y2: Number,
    color: Color,
    classes: String? = null,
    block: RDOMBuilder<LINE>.() -> Unit
) = line(classes) {
    attrs.x1 = x1.toDouble()
    attrs.y1 = y1.toDouble()
    attrs.x2 = x2.toDouble()
    attrs.y2 = y2.toDouble()
    attrs.stroke = color.value
    block()
}

inline fun RDOMBuilder<SVG>.line(
    classes: String? = null,
    block: RDOMBuilder<LINE>.() -> Unit
): Unit = tag(block) { LINE(attributesMapOf("class", classes), it) }

open class LINE(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("line", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var x1: Double by DoubleAttribute()
    var y1: Double by DoubleAttribute()
    var x2: Double by DoubleAttribute()
    var y2: Double by DoubleAttribute()
    var stroke: String by StringAttribute()
}