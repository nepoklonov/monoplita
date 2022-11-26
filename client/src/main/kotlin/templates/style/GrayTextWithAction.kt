package templates.style

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationLine
import styled.StyledDOMBuilder
import styled.css

fun StyledDOMBuilder<*>.grayTextWithAction() {
    css {
        fontStyle = FontStyle.italic
        cursor = Cursor.pointer
        color = Color("#808080")
        hover { color = Color("#505050") }
        active { color = Color("#303030") }
        textDecoration = TextDecoration(setOf(TextDecorationLine.underline))
    }
}