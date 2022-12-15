package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag

fun RDOMBuilder<SVG>.arc(
    classes: String? = null,
    block: RDOMBuilder<ARC>.() -> Unit = {}
) = tag(block) { ARC(attributesMapOf("class", classes), it) }

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