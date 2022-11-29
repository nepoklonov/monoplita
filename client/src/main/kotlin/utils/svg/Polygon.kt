package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RDOMBuilder<SVG>.polygon(
    classes: String? = null,
    block: RDOMBuilder<POLYGON>.() -> Unit
): Unit = tag(block) { POLYGON(attributesMapOf("class", classes), it) }

open class POLYGON(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("polygon", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var points: String by StringAttribute()
    var fill: String by StringAttribute()
    var stroke: String by StringAttribute()
}