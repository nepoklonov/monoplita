package utils

import kotlinx.css.Color
import kotlinx.html.*
import kotlinx.html.attributes.Attribute
import kotlinx.html.attributes.AttributeEncoder
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag
import kotlin.reflect.KProperty

private operator fun <T> Attribute<T>.setValue(tag: HTMLTag, property: KProperty<*>, value: T) {
    this[tag, property.name] = value
}

private operator fun <T> Attribute<T>.getValue(tag: HTMLTag, property: KProperty<*>): T {
    return this[tag, property.name]
}

object DoubleEncoder : AttributeEncoder<Double> {
    override fun encode(attributeName: String, value: Double) = value.toString()
    override fun decode(attributeName: String, value: String) = value.toDouble()
}

object IntEncoder : AttributeEncoder<Int> {
    override fun encode(attributeName: String, value: Int) = value.toString()
    override fun decode(attributeName: String, value: String) = value.toInt()
}

class IntAttribute : Attribute<Int>(IntEncoder)

class DoubleAttribute : Attribute<Double>(DoubleEncoder)

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

open class POLYGON(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("polygon", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var points: String by StringAttribute()
    var fill: String by StringAttribute()
    var stroke: String by StringAttribute()
}

inline fun RDOMBuilder<SVG>.line(
    classes: String? = null,
    block: RDOMBuilder<LINE>.() -> Unit
): Unit = tag(block) { LINE(attributesMapOf("class", classes), it) }

inline fun RDOMBuilder<SVG>.line(
    x1: Number,
    y1: Number,
    x2: Number,
    y2: Number,
    color: Color,
    classes: String? = null,
    block: RDOMBuilder<LINE>.() -> Unit
) = line {
    attrs.x1 = x1.toDouble()
    attrs.y1 = y1.toDouble()
    attrs.x2 = x2.toDouble()
    attrs.y2 = y2.toDouble()
    attrs.stroke = color.value
}

inline fun RDOMBuilder<SVG>.polygon(
    classes: String? = null,
    block: RDOMBuilder<POLYGON>.() -> Unit
): Unit = tag(block) { POLYGON(attributesMapOf("class", classes), it) }

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