package controllers

import kotlinx.browser.window
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.Promise


open class Controller(
    private val name: String
) {
    fun api(methodName: String, requestInit: RequestInit): Promise<Response> {
        return window.fetch("api/$name/$methodName", requestInit)
    }
}