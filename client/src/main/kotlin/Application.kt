import browser.document
import kotlinx.coroutines.MainScope
import react.*
import react.dom.client.createRoot

val mainScope = MainScope()

fun main() {
    val container = document.getElementById("root") ?: error("Root container not found")
    createRoot(container).render(Fragment.create {
        mainComponent()
    })
}