package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.StringAttribute
import react.dom.RDOMBuilder
import styled.StyledDOMBuilder
import styled.styledTag

fun RDOMBuilder<SVG>.text(
    classes: String? = null,
    block: StyledDOMBuilder<TEXT>.() -> Unit = {}
) = styledTag(block) { TEXT(attributesMapOf("class", classes), it)}

open class TEXT(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>
) : HTMLTag("text", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var x: String by StringAttribute()
    var y: String by StringAttribute()
    var stroke: String by StringAttribute()
    var strokeWidth: String by StringAttribute()
}
