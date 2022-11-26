package components.structure

import kotlinx.css.*
import react.PropsWithChildren
import react.fc
import styled.css
import styled.styledDiv

val leftPanel = fc<PropsWithChildren> { props ->
    styledDiv {
        css {
            width = 350.px
            minWidth = 350.px
            marginRight = 50.px
        }
        props.children()
    }
}