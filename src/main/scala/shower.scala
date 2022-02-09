import scala.util.Random
import scala.swing.Graphics2D
import java.awt.Color

class shower(val n: Int, speed: Double, withDepth: Boolean = false) {
  

    var arr = new Array[Meteor](n)
    val random = new Random()

    for (i <- arr.indices){
        val pix = if (withDepth) {
            new Pixel3(new Vector3(random.nextInt(Main.mainX), random.nextInt(Main.mainY), random.between(-4.0, 8.0)), 4)
        } else {
            new Pixel3(new Vector3(random.nextInt(Main.mainX), random.nextInt(Main.mainY), 0), 2)
        }

        val rgb = ColourFunctions.randomColourRGB
        val color = new Color(rgb._1, rgb._2, rgb._3, 255)
        pix.color = color
        pix.assingTrail(amountOfPixels = 100 + (pix.z * 20).toInt, fadingIntensity = 9)

        val meteor = new Meteor(pix, scala.math.max(0.2, random.between(0.5, 1.1) + pix.z/13.0))

        arr(i) = meteor
    }

    if (withDepth){
        arr = arr.sortBy(met => met.pix.z)
    }


    def advance(): Unit = {
        for (meteor <- arr){
            val pix = meteor.pix
            pix.moveBy(new Vector3(speed * meteor.speed, speed * meteor.speed, 0))


            if (pix.y > Main.mainY + 10) {
                pix.pos.y = -10.0
            }
            if (pix.x > Main.mainX + 10) {
                pix.pos.x = -10
            }
        }

    }

    def draw(g: Graphics2D): Unit = {
        for (meteor <- arr) {
            meteor.pix.draw(g, fadeWithDepth = false)
        }
    }
}

class Meteor(val pix: Pixel3, var speed: Double)
