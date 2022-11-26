import kotlinx.css.CssBuilder
import kotlinx.css.fontFamily
import kotlinx.css.src

object GlobalStyles {
    fun inject(): String {
        return CssBuilder().apply {
            fontFace {
                fontFamily = "monoplita"
                src = "url('/static/fonts/lato.ttf')"
            }

            "*" {
                fontFamily = "monoplita"
            }
        }.toString()
    }
}