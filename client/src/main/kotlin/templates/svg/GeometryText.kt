package templates.svg

import kotlinx.css.*
import kotlinx.html.SVG
import react.dom.RDOMBuilder
import styled.css
import utils.geometry.Point
import utils.svg.text

fun RDOMBuilder<SVG>.geometryText(point: Point, text: String) {
    text {
        attrs.x = point.x.toString()
        attrs.y = point.y.toString()
        attrs.strokeWidth = 2.px.toString()
        attrs.stroke = Color.white.toString()
        css {
            put("text-anchor", "middle")
            put("paint-order", "stroke fill")
            fontStyle = FontStyle.italic
            fontSize = 10.px
            fontFamily = "sans-serif"
        }
        +text
    }
}