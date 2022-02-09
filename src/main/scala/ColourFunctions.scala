import scala.util.Random

object ColourFunctions {

  val random = new Random()

  def randomColourRGB: (Int, Int, Int) = {
    var (r, g, b) = (0, 0, 0)
    while ( !(r > 50 && g > 50 && b > 50 && (r + g + b) < 520) ){
      r = random.nextInt(255)
      g = random.nextInt(255)
      b = random.nextInt(255)
    }
    (r, g, b)
  }

  

}
