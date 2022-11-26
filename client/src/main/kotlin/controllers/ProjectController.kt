package controllers

import kotlinx.coroutines.await
import org.w3c.fetch.RequestInit
import org.w3c.files.Blob
import web.http.FormData

class ProjectController : Controller("project") {
    suspend fun uploadImage(image: Blob): String {
        val formData = FormData().apply {
            append("image", image)
            append("test", "test")
        }
        return api("upload-image", RequestInit(
            method = "POST",
            body = formData,
        )).await().text().await()
    }
}