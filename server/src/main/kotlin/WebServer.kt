import controllers.ProjectController
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.io.File

@Suppress("unused")
fun Application.module() {
    routing {
        get("{...}") {
            call.respondHtml {
                head {
                    style {
                        unsafe {
                            raw(".angle-text { " +
                                    "font: italic 6px sans-serif; " +
                                    "}" +
                                    ".edge-text {" +
                                    "font: italic 6px sans-serif;" +
                                    "}")
                            +GlobalStyles.inject()
                        }
                    }
                }
                body {
                    div {
                        id = "root"
                        +"Waiting"
                    }
                    script(src = "/client.js") { }
                }
            }
        }

        static("uploads") {
            files("uploads")
        }

        static("static") {
            files("static")
        }

        static("/") {
            staticRootFolder = File("static")
            file("client.js")
        }

        route("api") {
            ProjectController(this).uploadImage()
        }
    }
}