package components

import components.structure.leftPanel
import kotlinx.css.Display
import kotlinx.css.display
import modes.Mode
import react.Props
import react.fc
import react.useState
import styled.css
import styled.styledDiv
import utils.geometry.Area
import utils.geometry.Point

interface ProjectProps : Props {
    var imageUrl: String
}

val projectComponent = fc<ProjectProps> { props ->
    var currentMode: Mode? by useState(null)
    var controlPoints: Pair<Point?, Point?> by useState(null to null)
    var areas: List<Area> by useState(listOf(Area()))
    var selectedArea: Int? by useState(null)
    var scale: Double by useState(1.0)

    styledDiv {
        css {
            display = Display.flex
        }

        leftPanel {
            selectKnown {
                attrs.controlPoints = controlPoints
                attrs.setScale = { scale = it }
                attrs.isActive = currentMode == Mode.SelectKnown
                attrs.setActive = { currentMode = Mode.SelectKnown }
                attrs.setDefault = {
                    controlPoints = null to null
                    currentMode = Mode.SelectKnown
                }
            }
            areaPanel {
                attrs.areas = areas
                attrs.scale = scale
                attrs.selectedArea = selectedArea.takeIf { currentMode == Mode.Area }
                attrs.selectArea = {
                    currentMode = Mode.Area
                    selectedArea = it
                }
                attrs.dropArea = { index ->
                    areas = areas.toMutableList().apply { this[index] = Area() }
                }
            }
        }

        imageComponent {
            attrs.mode = currentMode
            attrs.src = props.imageUrl
            attrs.areas = areas
            attrs.controlPoints = controlPoints.toList().filterNotNull()
            attrs.movePoint = { pointIndex: Int, point: Point ->
                console.log(pointIndex)
                areas = areas.toMutableList().also {
                    selectedArea?.let { index ->
                        it[index] = it[index].movePoint(pointIndex, point)
                    }
                }
            }
            attrs.click = { point ->
                when (currentMode) {
                    Mode.Area -> areas = areas.toMutableList().also {
                        selectedArea?.let { index ->
                            it[index] = it[index].addPoint(point)
                        }
                    }
                    Mode.SelectKnown -> controlPoints = when {
                        controlPoints.first == null -> {
                            controlPoints.copy(first = point)
                        }
                        controlPoints.second == null -> {
                            currentMode = null
                            controlPoints.copy(second = point)
                        }
                        else -> null to null
                    }
                    else -> {}
                }
            }
        }
    }
}