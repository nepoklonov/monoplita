package components.structure

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledH1
import styled.styledImg

fun RBuilder.header() {
    styledH1 {
        css {
            display = Display.flex
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }
        styledImg(src = "https://static.tildacdn.com/tild3062-3062-4463-b036-303963663934/logo-new.png") {
            css.height = 39.px
            css.marginRight = 40.px
        }
        +"Конструктор сметы"
    }
}