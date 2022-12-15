package components

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.lh
import kotlinx.html.js.onClickFunction
import react.Props
import react.dom.attrs
import react.fc
import stages.MarkingMode
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSvg
import templates.svg.angleArc
import templates.svg.draggablePoint
import templates.svg.geometryText
import utils.geometry.Area
import utils.geometry.Point
import utils.geometry.center
import utils.geometry.p
import utils.svg.*
import utils.toMouseEvent
import kotlin.math.roundToInt

external interface ImageComponentProps : Props {
    var src: String
    var mode: MarkingMode?
    var areas: List<Area>
    var scale: Double
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

            area.asTripleSequence().forEach {
                angleArc(it, area.sign)
            }

            area.points.forEachIndexed { index, point ->
                draggablePoint(point, index, movePoint = props.movePoint)
            }

            area.asPairSequence().forEach { (a, b) ->
                geometryText(center(a, b), (a.distanceTo(b) * props.scale).roundToInt().toString())
            }

            props.controlPoints.forEach { (x, y) -> circle(x, y, 3) { attrs.fill = "#aaffaa" } }
        }
        styledImg(src = props.src) {
            css.height = 600.px
        }
    }
}