package components

import kotlinx.css.px
import kotlinx.css.width
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.Props
import react.fc
import styled.styledButton
import styled.styledDiv
import styled.styledInput
import styled.styledSpan
import templates.style.grayTextWithAction
import utils.geometry.Point
import kotlin.math.roundToInt

interface SelectKnownProps : Props {
    var isActive: Boolean
    var setActive: () -> Unit
    var setDefault: () -> Unit
    var controlPoints: Pair<Point?, Point?>
    var setScale: (Double) -> Unit
}

val selectKnown = fc<SelectKnownProps> { props ->
    val (first, second) = props.controlPoints
    when {
        first != null && second != null -> styledDiv {
            val distance = first.distanceTo(second)
            +"${distance.roundToInt()} пикселей — "
            styledInput(InputType.text) {
                css.width = 20.px
                attrs.onChangeFunction = { event ->
                    (event.target as HTMLInputElement).value.toDoubleOrNull()?.let { newValue ->
                        props.setScale(newValue / distance)
                    }
                }
            }
            +" метров. "
            styledSpan {
                grayTextWithAction()
                attrs.onClickFunction = { props.setDefault() }
                +"(Сбросить)"
            }
        }
        !props.isActive -> styledButton {
            attrs.onClickFunction = { props.setActive() }
            +"Задать масштаб"
        }
        else -> styledDiv {
            +"Чтобы указать масштаб, выберите две точки с известным расстоянием между ними."
        }
    }
}