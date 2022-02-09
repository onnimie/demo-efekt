object Camera {
  
    private val distance: Double = 10
    val pos = new Vector3(Main.mainX/2, Main.mainY/2, distance)
    
    def moveBy(vector: Vector3): Unit = {
        pos.x = pos.x + vector.x
        pos.y = pos.y + vector.y
        pos.z = pos.z + vector.z
    }


}

object Depth {

    def calculateRadius(pix: Pixel3): Double = {
        val s = Camera.pos.z
        val z = pix.z
        pix.radius * (s/(s-z))
    }
}