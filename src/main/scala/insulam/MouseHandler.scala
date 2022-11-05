package insulam

import java.awt.Component
import java.awt.event.*

object AppMouseWheelListener extends MouseWheelListener {
  override def mouseWheelMoved(e: MouseWheelEvent): Unit = {
    if (e.getWheelRotation < 0) {
      App.zoomInOut(1)
    } else {
      App.zoomInOut(-1)
    }
  }
}

object MouseHandler extends MouseListener with MouseMotionListener {
  val handleMouseWheel = AppMouseWheelListener


  override def mouseClicked(e: MouseEvent): Unit = {

  }

  override def mouseEntered(e: MouseEvent): Unit = {


  }

  override def mouseExited(e: MouseEvent): Unit = {


  }

  override def mousePressed(e: MouseEvent): Unit = {


  }

  override def mouseReleased(e: MouseEvent): Unit = {
    Camera.toMouseCoords(e.getX, e.getY);
  }

  override def mouseDragged(e: MouseEvent): Unit = {


  }

  override def mouseMoved(e: MouseEvent): Unit = {


  }

}