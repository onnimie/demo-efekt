import scala.util.Random
import scala.swing.Graphics2D
import java.awt.Color

class lightspeed(val n: Int) {
  
  val arr: Array[(Pixel3, Vector3)] = new Array[(Pixel3, Vector3)](n)
  private val random = new Random()
  private val movementModifier = 4.0
  private val zSpeed = 0.005

  private val startPos = new Vector3(Main.mainX/2, Main.mainY/2, -5)

  private def getMoveVector: Vector3 = {

    var x = 0.0
    var y = 0.0
    while (x == 0 && y == 0) {
        x = random.nextDouble() * (if (random.nextBoolean()) -1 else 1)
        y = random.nextDouble() * (if (random.nextBoolean()) -1 else 1)
    }
    
    val ret = new Vector3(x, y, zSpeed)
    ret.normalizeXY()
    ret.x = ret.x * movementModifier
    ret.y = ret.y * movementModifier
    ret
  }

  for (i <- arr.indices){
    val pix = new Pixel3(startPos, 4)

    pix.color = Color.white
    //pix.assingTrail(amountOfPixels = 60, fadingIntensity = 8)

    val dir = getMoveVector

    arr(i) = (pix, dir)
  }

  def advance(): Unit = {
      arr.foreach(pair => {
        val pix = pair._1
        val dir = pair._2
        pix.moveBy(dir)

        val offset = 0

        if (pix.x > Main.mainX + offset || pix.x < -offset || pix.y > Main.mainY + offset || pix.y < -offset || pix.z > Camera.pos.z) {

            pix.pos.copyInto(startPos)
            dir.copyInto(getMoveVector)
        }
      })
  }

  def draw(g: Graphics2D): Unit = {
      arr.foreach(pair => pair._1.draw(g, atleast_1_pix = true))
  }
  
}
