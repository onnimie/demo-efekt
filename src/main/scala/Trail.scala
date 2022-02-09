
import java.awt.{Color, Rectangle}
import scala.swing.Graphics2D

class Trail(val host: Pixel3, amountOfPixels: Int = 10, var fadingIntensity: Int = 25, indexToStartFading: Int = 0) {
  
    val n = amountOfPixels
    val pixels = new Array[Pixel3](n)
    for (i <- 0 until n){
        val pix = new Pixel3(host.pos.copy(), (host.radius * 2).toInt)
        pixels(i) = pix
    }

    def advance(): Unit = {
        for (i <- (pixels.indices.reverse)){
            if (i == 0) {
                pixels.head.pos.copyInto(host.pos)
            } else {
                pixels(i).pos.copyInto(pixels(i-1).pos)
            }
        }
    }

    // draw trail before the host pixel
    def draw(g: Graphics2D): Unit = {     
        val hostAlpha = host.alpha
        val (hostR, hostG, hostB) = (host.color.getRed(), host.color.getGreen(), host.color.getBlue())
        var count = 1

        for (i <- pixels.indices){

            g.setColor(new Color(hostR, hostG, hostB, hostAlpha))
            val pix = pixels(i)
            val r = pix.r_draw
            
            if (i >= indexToStartFading){
                
                val thisAlpha = hostAlpha - count*fadingIntensity
                val newAlpha = if (thisAlpha < 0) 0 else thisAlpha
                g.setColor(new Color(hostR, hostG, hostB, newAlpha))

                count += 1
            }
            g.fillOval((pix.x-r).toInt, (pix.y-r).toInt, (pix.r_draw * 2).toInt, (pix.r_draw * 2).toInt)
        }
    }
}
