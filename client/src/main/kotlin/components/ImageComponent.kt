package components

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.lh
import kotlinx.html.js.onClickFunction
import modes.Mode
import react.Props
import react.dom.attrs
import react.fc
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSvg
import utils.circle
import utils.geometry.Area
import utils.geometry.Point
import utils.geometry.p
import utils.polygon
import utils.toMouseEvent

interface ImageComponentProps : Props {
    var src: String
    var mode: Mode?
    var areas: List<Area>
    var controlPoints: List<Point>
    var click: (Point) -> Unit
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
            css {
                zIndex = 1
                position = Position.absolute
                width = 100.pct
                height = 100.pct
            }

            area.points.forEach { (x, y) -> circle(x, y, 3) }
            props.controlPoints.forEach { (x, y) -> circle(x, y, 3) { attrs.fill = "#aaffaa" } }

            polygon {
                attrs.points = area.toSvgPath()
                attrs.fill = "#aa0000aa"
            }

            attrs {
                onClickFunction = { event ->
                    props.click(event.toMouseEvent().run { offsetX p offsetY })
                }
            }
        }
        styledImg(src = props.src) {
            css.height = 600.px
        }
    }
}