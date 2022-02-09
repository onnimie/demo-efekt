
import scala.swing._
import java.awt.{Color, Polygon, Rectangle}
import java.awt.event.{ActionListener, ActionEvent}
import javax.swing.Timer

object Main extends SimpleSwingApplication {
  for (i <- 0 to 5) {
    println("Hello, running!")
  }

  val test1 = new Vector3(10,10,5)
  val test2 = new Vector3(0,0,10)
  println(test1)
  println(test2)
  test1.copyInto(test2)
  println(test1)
  

  val (mainX, mainY) = (2000,1000)

  def top = new MainFrame {
    title = "Back to school, flock of boids!"
    contents = panel
    size = new Dimension(mainX,mainY)
    resizable = true
    

    // the timer (or something else) doesn't seem to quit without this
    // the application seemingly continues to run when MainFrame is closed without this
    override def closeOperation() = {
      timer.stop()
      Main.quit()
    }
  }

  val panel = new Panel() {

    val pix = new Pixel3(new Vector3(3, 3, 0), 1)
    pix.assingTrail()

    val shower = new shower(51, 1.3, withDepth = false)
    val fireflies = new fireflies(400)
    val lightspeed = new lightspeed(10)

    override def paintComponent(g: Graphics2D) = {

      // clear the area, paint over the previous boids
      g.setColor(Color.black)
      g.fillRect(0,0,mainX,mainY)

      //g.setColor(Color.red)
      //g.fillRect(timerCount, timerCount, 10,10)

      //pix.moveBy(new Vector3(1,1, 0))
      //pix.draw(g)

      //g.setColor(Color.white)
      //g.drawOval(50, 50, 1, 1)

      shower.advance()
      shower.draw(g)

      //fireflies.advance()
      //fireflies.draw(g)

      //lightspeed.advance()
      //lightspeed.draw(g)
    }
  }






  // ActionListener that calls its actionPerformed each time the timer sends an ActionEvent
  val listener = new ActionListener(){
    def actionPerformed(e: ActionEvent) = {

      // action to be done every timer call
      timerCount += 1.0
      panel.repaint()

    }
  }
  // timer that sends ActionEvents to ActionListener (right above)
  // sends the signal every 10 ms
  var timerCount = 0.0
  val timer = new Timer(10, listener)
  timer.start()


}