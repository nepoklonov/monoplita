package utils.geometry

import kotlin.math.PI

open class Angle protected constructor(
    val radians: Double,
    val degrees: Double
) {
    operator fun plus(other: Angle) = Angle(radians + other.radians, degrees + other.degrees)
    operator fun minus(other: Angle) = Angle(radians - other.radians, degrees - other.degrees)
    operator fun times(coefficient: Number) = Angle(
        radians * coefficient.toDouble(),
        degrees * coefficient.toDouble()
    )

    operator fun div(coefficient: Number) = Angle(
        radians / coefficient.toDouble(),
        degrees / coefficient.toDouble()
    )
}

class Radians(value: Double) : Angle(value, value * 180 / PI)
class Degrees(value: Double) : Angle(value * PI / 180, value)

val Number.deg get() = Degrees(toDouble())
val Number.radians get() = Radians(toDouble())