package templates.svg

import kotlinx.browser.document
import kotlinx.html.SVG
import kotlinx.html.js.onMouseDownFunction
import org.w3c.dom.events.MouseEvent
import react.dom.RDOMBuilder
import utils.geometry.Point
import utils.geometry.p
import utils.svg.circle


const val pointSize = 3.0

fun RDOMBuilder<SVG>.draggablePoint(
    point: Point, index: Int, movePoint: (pointIndex: Int, Point) -> Unit
) = circle {
    attrs.cx = point.x
    attrs.cy = point.y
    attrs.r = pointSize
    attrs.onMouseDownFunction = { event ->
        event.preventDefault()
        event.stopPropagation()
        document.onmousemove = {
            movePoint(index, it.offsetX p it.offsetY)
        }
        document.onmouseup = fun(e: MouseEvent) {
            e.preventDefault()
            e.stopPropagation()
            document.onmousemove = null
            document.onmouseup = null
        }
    }
}
