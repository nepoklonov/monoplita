package components

import kotlinx.css.Display
import kotlinx.css.display
import stages.Stage
import react.Props
import react.fc
import react.useState
import styled.css
import styled.styledDiv
import utils.geometry.Area

external interface ProjectProps : Props {
    var imageUrl: String
}

val projectComponent = fc<ProjectProps> { props ->
    var currentStage: Stage by useState(Stage.Marking)
    var areas: List<Area> by useState(listOf(Area()))
    var scale: Double by useState(0.0)

    styledDiv {
        css {
            display = Display.flex
        }
        when (currentStage) {
            Stage.Marking -> markingComponent {
                attrs.areas = areas
                attrs.setAreas = { areas = it }
                attrs.scale = scale
                attrs.setScale = { scale = it }
                attrs.imageUrl = props.imageUrl
                attrs.nextStage = { currentStage = Stage.ConcreteSlab }
            }

            Stage.ConcreteSlab -> {

            }
            Stage.Excavation -> TODO()
            Stage.Sectional -> TODO()
        }
    }
}