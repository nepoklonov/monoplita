import components.*
import components.structure.header
import react.Props
import react.fc
import react.useState

val mainComponent = fc<Props> {
    var imageUrl: String? by useState(null)

    header()

    imageUrl?.let {
        projectComponent {
            attrs.imageUrl = it
        }
    } ?: uploadImage {
        attrs.useFile = { imageUrl = it }
    }
}
