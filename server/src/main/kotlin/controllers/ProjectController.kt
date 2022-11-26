package controllers

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

const val uploads = "uploads"

class ProjectController(api: Route) : Controller(api, "project") {
    fun uploadImage() = route {
        post("upload-image") {
            var fileName = ""

            val multipartData = call.receiveMultipart()

            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        File("$uploads/$fileName").writeBytes(fileBytes)
                    }
                    else -> {}
                }
                part.dispose()
            }

            call.respondText("$uploads/$fileName")
        }
    }
}