package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RDOMBuilder<SVG>.rect(
    classes: String? = null,
    block: RDOMBuilder<RECT>.() -> Unit
): Unit = tag(block) { RECT(attributesMapOf("class", classes), it) }

open class RECT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("rect", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var width: String by StringAttribute()
    var height: String by StringAttribute()
    var fill: String by StringAttribute()
    var stroke: String by StringAttribute()
}