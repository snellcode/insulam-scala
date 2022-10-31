package insulam

import java.awt.{Color, Graphics, Graphics2D}
import javax.swing.JPanel

object Game extends JPanel with Runnable {
  val nanoSecond = 1000000000
  var thread: Thread = null
  var running = false

  setBackground(Color.BLACK)

  def start(): Unit = {
    if (thread == null) {
      thread = new Thread(this)
      thread.start()
    }
    running = true
  }

  def stop(): Unit = {
    // dont know how to stop and start this yet
    //    running = false
  }

  def update(): Unit = {
    Camera.update()
  }

  override def run(): Unit = {
    val drawInterval: Double = (nanoSecond / 60).asInstanceOf[Double]
    var delta: Double = 0
    var lastTime: Long = System.nanoTime
    var currentTime: Long = 0
    var timer: Long = 0
    var drawCount: Int = 0
    while (running) {
      currentTime = System.nanoTime
      delta += ((currentTime - lastTime) / drawInterval)
      timer += (currentTime - lastTime)
      lastTime = currentTime

      if (delta >= 1) {
        if (App.gameRunning) {
          update()
          repaint()
        }
        delta -= 1
        drawCount += 1
      }

      if (timer >= nanoSecond) {
        drawCount = 0
        timer = 0
      }
    }
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val g2 = g.asInstanceOf[Graphics2D]
    Grid.draw(g2)
    Camera.draw(g2)
    g2.dispose()
  }

}