package utils.svg

import kotlinx.html.*
import kotlinx.html.attributes.Attribute
import kotlinx.html.attributes.AttributeEncoder
import kotlin.reflect.KProperty

operator fun <T> Attribute<T>.setValue(tag: HTMLTag, property: KProperty<*>, value: T) {
    this[tag, property.name] = value
}

operator fun <T> Attribute<T>.getValue(tag: HTMLTag, property: KProperty<*>): T {
    return this[tag, property.name]
}

object DoubleEncoder : AttributeEncoder<Double> {
    override fun encode(attributeName: String, value: Double) = value.toString()
    override fun decode(attributeName: String, value: String) = value.toDouble()
}

object IntEncoder : AttributeEncoder<Int> {
    override fun encode(attributeName: String, value: Int) = value.toString()
    override fun decode(attributeName: String, value: String) = value.toInt()
}

class IntAttribute : Attribute<Int>(IntEncoder)

class DoubleAttribute : Attribute<Double>(DoubleEncoder)