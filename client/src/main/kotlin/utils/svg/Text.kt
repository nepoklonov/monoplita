package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag
import utils.geometry.Point

fun RDOMBuilder<SVG>.text(
    point: Point,
    text: String,
    classes: String? = null,
    block: RDOMBuilder<TEXT>.() -> Unit = {}
) = text(classes) {
    attrs.x = point.x.toString()
    attrs.y = point.y.toString()
    block()
    +text
}

fun RDOMBuilder<SVG>.text(
    classes: String? = null,
    block: RDOMBuilder<TEXT>.() -> Unit = {}
) = tag(block) { TEXT(attributesMapOf("class", classes), it)}

open class TEXT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("text", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var x: String by StringAttribute()
    var y: String by StringAttribute()
}
