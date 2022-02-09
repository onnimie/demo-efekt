import scala.math.sqrt

class Vector3(var x: Double, var y: Double, var z: Double) {
  
    def copyInto(vector: Vector3): Unit = {
        this.x = vector.x
        this.y = vector.y
        this.z = vector.z
    }

    def copy(): Vector3 = {
        new Vector3(x, y, z)
    }

    def magnitude: Double = sqrt(this.x * this.x + this.y * this.y)

    def normalizeXY(): Unit = {
        val mag = this.magnitude
        this.x = this.x / mag
        this.y = this.y / mag
    }

    def normalize(): Unit = {
        val mag = this.magnitude
        this.x = this.x / mag
        this.y = this.y / mag
        this.z = this.z / mag
    }

    override def toString(): String = s"Vector3($x, $y, $z)"
}
