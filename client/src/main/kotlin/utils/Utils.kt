package utils

fun <T> T.log(caption: String = ""): T {
    console.log(caption + this.toString())
    return this
}