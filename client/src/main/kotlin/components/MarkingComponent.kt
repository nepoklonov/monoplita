package components

import components.structure.leftPanel
import stages.MarkingMode
import react.Props
import react.fc
import react.useState
import utils.geometry.Area
import utils.geometry.Point

external interface MarkingComponentProps : Props {
    var areas: List<Area>
    var setAreas: (List<Area>) -> Unit
    var scale: Double
    var setScale: (Double) -> Unit
    var imageUrl: String
    var nextStage: () -> Unit
}

val markingComponent = fc<MarkingComponentProps> { props ->
    var currentMode: MarkingMode? by useState(null)
    var controlPoints: Pair<Point?, Point?> by useState(null to null)
    var selectedArea: Int? by useState(null)

    leftPanel {
        selectKnown {
            attrs.controlPoints = controlPoints
            attrs.setScale = { props.setScale(it) }
            attrs.isActive = currentMode == MarkingMode.SelectKnown
            attrs.setActive = { currentMode = MarkingMode.SelectKnown }
            attrs.setDefault = {
                controlPoints = null to null
                currentMode = MarkingMode.SelectKnown
            }
        }
        areaPanel {
            attrs.areas = props.areas
            attrs.scale = props.scale
            attrs.selectedArea = selectedArea.takeIf { currentMode == MarkingMode.Area }
            attrs.selectArea = {
                currentMode = MarkingMode.Area
                selectedArea = it
            }
            attrs.dropArea = { index ->
                props.setAreas(props.areas.toMutableList().apply { this[index] = Area() })
            }
            attrs.nextStage = props.nextStage
        }
    }

    imageComponent {
        attrs.mode = currentMode
        attrs.src = props.imageUrl
        attrs.areas = props.areas
        attrs.controlPoints = controlPoints.toList().filterNotNull()
        attrs.movePoint = { pointIndex: Int, point: Point ->
            console.log(pointIndex)
            props.setAreas(props.areas.toMutableList().also {
                selectedArea?.let { index ->
                    it[index] = it[index].movePoint(pointIndex, point)
                }
            })
        }
        attrs.click = { point ->
            when (currentMode) {
                MarkingMode.Area -> props.setAreas(props.areas.toMutableList().also {
                    selectedArea?.let { index ->
                        it[index] = it[index].addPoint(point)
                    }
                })

                MarkingMode.SelectKnown -> controlPoints = when {
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