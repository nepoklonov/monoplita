package components

import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.lh
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseDownFunction
import modes.Mode
import org.w3c.dom.events.MouseEvent
import react.Props
import react.dom.attrs
import react.fc
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSvg
import utils.svg.circle
import utils.geometry.Area
import utils.geometry.Point
import utils.geometry.p
import utils.svg.polygon
import utils.svg.rect
import utils.toMouseEvent

external interface ImageComponentProps : Props {
    var src: String
    var mode: Mode?
    var areas: List<Area>
    var controlPoints: List<Point>
    var click: (Point) -> Unit
    var movePoint: (pointIndex: Int, Point) -> Unit
}

val imageComponent = fc<ImageComponentProps> { props ->

    val area = props.areas.first()

    styledDiv {
        css {
            position = Position.relative
            display = Display.inlineBlock
            lineHeight = 0.px.lh
            border(1.px, BorderStyle.solid, Color("#ddd"))
        }
        styledSvg {

            polygon {
                attrs.points = area.toSvgPath()
                attrs.fill = "#aa0000aa"
            }

            rect {
                attrs {
                    width = 100.pct.toString()
                    height = 100.pct.toString()
                    onClickFunction = { event ->
                        props.click(event.toMouseEvent().run { offsetX p offsetY })
                    }
                    fill = Color.transparent.toString()
                }
            }

            css {
                zIndex = 1
                position = Position.absolute
                width = 100.pct
                height = 100.pct
            }

            area.points.forEachIndexed { index, (x, y) ->
                circle(x, y, 3) {
                    attrs.onMouseDownFunction = { event ->
                        event.preventDefault()
                        event.stopPropagation()
                        document.onmousemove = {
                            props.movePoint(index, it.offsetX p it.offsetY)
                        }
                        document.onmouseup = fun(e: MouseEvent) {
                            e.preventDefault()
                            e.stopPropagation()
                            document.onmousemove = null
                            document.onmouseup = null
                        }
                    }
                }
            }
            props.controlPoints.forEach { (x, y) -> circle(x, y, 3) { attrs.fill = "#aaffaa" } }
        }
        styledImg(src = props.src) {
            css.height = 600.px
        }
    }
}