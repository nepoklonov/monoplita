package controllers

import io.ktor.server.routing.*

open class Controller(api: Route, name: String) {
    val route: (Route.() -> Unit) -> Unit = { routes ->
        api { route(name) { routes() } }
    }
}