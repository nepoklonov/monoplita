package components

import kotlinx.css.*
import kotlinx.css.properties.borderLeft
import kotlinx.html.js.onClickFunction
import react.PropsWithChildren
import react.fc
import styled.css
import styled.styledDiv
import styled.styledH2
import styled.styledP
import templates.style.grayTextWithAction
import utils.geometry.Area
import kotlin.math.roundToInt

interface AreaPanelProps : PropsWithChildren {
    var areas: List<Area>
    var scale: Double
    var selectedArea: Int?
    var selectArea: (Int) -> Unit
    var dropArea: (Int) -> Unit
}

val areaPanel = fc<AreaPanelProps> { props ->
    styledH2 {
        +"Контуры"
    }
    props.areas.forEachIndexed { index, area ->
        val s = (area.square * props.scale * props.scale).roundToInt()
        val p = (area.perimeter * props.scale).roundToInt()

        styledDiv {
            attrs.onClickFunction = {
                props.selectArea(index)
            }
            css {
                borderLeft(3.px, BorderStyle.solid, Color("#aa0000aa"))
                padding(2.px, 20.px)
                if (index == props.selectedArea) {
                    backgroundColor = Color("#aa000055")
                } else {
                    backgroundColor = Color("#aa000022")
                    hover { backgroundColor = Color("#aa000033") }
                    active { backgroundColor = Color("#aa000044") }
                }
                cursor = Cursor.pointer
            }
            styledP { +"S = $s кв. м" }
            styledP { +"P = $p м" }
            styledP {
                attrs.onClickFunction = { props.dropArea(index) }
                grayTextWithAction()
                +"(Сбросить контур)"
            }
        }
    }
}