import scala.util.Random
import scala.swing.Graphics2D
import java.awt.Color


class fireflies(val n: Int) {
  val arr: Array[Pixel3] = new Array[Pixel3](n)
  val random = new Random()

  var movementModifier: Double = 0.8

  for (i <- arr.indices){
      val pix = new Pixel3(new Vector3(random.nextInt(Main.mainX), random.nextInt(Main.mainY), 0), 3)
      val rgb = ColourFunctions.randomColourRGB
      val color = new Color(rgb._1, rgb._2, rgb._3, 255)
      pix.color = color
      pix.assingTrail(amountOfPixels = 60, fadingIntensity = 7)

      arr(i) = pix
  }

  def advance(): Unit = {
      arr.foreach(pix => {
          pix.moveBy(new Vector3(random.nextGaussian() * movementModifier, random.nextGaussian() * movementModifier, 0))
      })
  }

  def draw(g: Graphics2D): Unit = {
      arr.foreach(pix => {
          pix.draw(g)
      })
  }

}
