package utils.geometry

import utils.log

open class Line(
    private val a: Point,
    private val b: Point
) {
    init {
        if (a.x eq b.x && a.y eq b.y)
            throw CreationException()
    }

    /**Check if point lies on the line*/
    fun contains(point: Point): Boolean {
        if (a.x eq b.x)
            return a.x eq point.x
        if (a.y eq b.y)
            return a.y eq point.y

        return ((point.x - a.x) / (b.x - a.x)).log() eq ((point.y - a.y) / (b.y - a.y)).log()
    }

    /**Checks if line intersects vector (essentially checks if an intersecton point
     * of two lines lies on vector)*/
    open fun intersects(vector: Segment): Boolean {
        val intersection = intersection(vector.line) ?: return false
        //"||" to cover vertical and horizontal cases where it is possible that oly
        //one condition be fulfilled
        return intersection.x in vector.xRange
                || intersection.y in vector.yRange
    }

    /**Calculates point of intersection of two lines
     * @return null if no intersection */
    open fun intersection(line: Line): Point? {
        // https://ru.wikipedia.org/wiki/%D0%9F%D0%B5%D1%80%D0%B5%D1%81%D0%B5%D1%87%D0%B5%D0%BD%D0%B8%D0%B5_%D0%BF%D1%80%D1%8F%D0%BC%D1%8B%D1%85#%D0%95%D1%81%D0%BB%D0%B8_%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D1%8B_%D0%BF%D0%BE_%D0%B4%D0%B2%D0%B5_%D1%82%D0%BE%D1%87%D0%BA%D0%B8_%D0%BD%D0%B0_%D0%BA%D0%B0%D0%B6%D0%B4%D0%BE%D0%B9_%D0%BF%D1%80%D1%8F%D0%BC%D0%BE%D0%B9
        val x1 = a.x
        val x2 = b.x
        val x3 = line.a.x
        val x4 = line.b.x

        val y1 = a.y
        val y2 = b.y
        val y3 = line.a.y
        val y4 = line.b.y

        val numeratorX = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)
        val numeratorY = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)
        val denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4)

        if (denominator eq 0.0)
            return null

        return (numeratorX / denominator) p (numeratorY / denominator)
    }

    class CreationException : Exception()
}

class Ray(
    private val start: Point,
    private val end: Point
) : Line(start, end) {
    constructor(vector: Segment) : this(vector.start, vector.end)

    private val xRange: ClosedFloatingPointRange<Double>
        get() = start.x..(if ((start vec end).x > 0.0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY)


    private val yRange: ClosedFloatingPointRange<Double>
        get() = start.y..(if ((start vec end).y > 0.0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY)


    override fun intersects(vector: Segment): Boolean {
        this.intersection(vector.line) ?: return false
        return super.intersects(vector)
    }

    override fun intersection(line: Line): Point? {
        val intersection = super.intersection(line) ?: return null
        return if (intersection.x in xRange || intersection.y in yRange) intersection else null
    }
}