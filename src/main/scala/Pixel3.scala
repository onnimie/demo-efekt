import java.awt.{Color, Polygon, Rectangle}
import scala.swing.Graphics2D

class Pixel3(var pos: Vector3, width: Int) {
  
    var radius = width / 2.0
    def x = pos.x
    def y = pos.y
    def z = pos.z


    def r_draw: Double = Depth.calculateRadius(this)

    var color: Color = Color.white
    def alpha: Int = color.getAlpha()

    private var trail: Trail = null
    def hasTrail: Boolean = trail != null && trail.isInstanceOf[Trail]
    def assingTrail(amountOfPixels: Int = 80, fadingIntensity: Int = 8, indexToStartFading: Int = 0): Unit = {
        trail = new Trail(this, amountOfPixels, fadingIntensity, indexToStartFading)
    }
    def ridTrail(): Unit = trail = null


    def moveBy(vector: Vector3): Unit = {
        pos.x = pos.x + vector.x
        pos.y = pos.y + vector.y
        pos.z = pos.z + vector.z
        if (hasTrail) {
            trail.advance()
        }
    }

    def draw(g: Graphics2D, fadeWithDepth: Boolean = false, atleast_1_pix: Boolean = false): Unit = {
        if (this.hasTrail){
            trail.draw(g)
        }
        val r = r_draw

        if (fadeWithDepth) {
            val tryAlpha = (alpha - 255/2 + z*5).toInt
            val newAlpha = if (tryAlpha < 0) 0 else if (tryAlpha > 255) 255 else tryAlpha
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), newAlpha))
        } else {
            g.setColor(color)
        }

        var sideLength = (r * 2).toInt
        if (atleast_1_pix) {
            sideLength = scala.math.max((r * 2).toInt, 1)
        }
        
        g.drawOval((x-r).toInt, (y-r).toInt, sideLength, sideLength)
        g.fillOval((x-r).toInt, (y-r).toInt, sideLength, sideLength)
    }
}
//g.fillRect((x-r).toInt, (y-r).toInt, (r * 2).toInt, (r * 2).toInt)
