package components

import controllers.ProjectController
import kotlinx.coroutines.launch
import kotlinx.css.marginLeft
import kotlinx.css.px
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import mainScope
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList
import react.Props
import react.fc
import styled.*

interface UploadImageProps : Props {
    var useFile: (String) -> Unit
}

val uploadImage = fc<UploadImageProps> { props ->
    styledForm {
        css {
            marginLeft = 10.px
        }
        styledH3 {
            +"Шаг 1. Выберите файл:"
        }
        styledInput(InputType.file) {
            attrs.onChangeFunction = {
                val target = it.target as HTMLInputElement
                val files = target.files?.asList() ?: emptyList()
                val file = files.singleOrNull() ?: error("Only one project file can be uploaded")
                mainScope.launch {
                    val url = ProjectController().uploadImage(file)
                    props.useFile(url)
                }
            }
        }
    }
}