package utils

import dom.events.MouseEvent
import kotlinx.html.org.w3c.dom.events.Event

fun Event.toMouseEvent() = asDynamic().nativeEvent as MouseEvent