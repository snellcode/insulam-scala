package insulam.input

import insulam.*
import insulam.grid.Camera

import java.awt.Component
import java.awt.event.*

object MouseHandler extends MouseListener with MouseMotionListener {

  val handleMouseWheel = GameMouseWheelListener

  override def mouseReleased(e: MouseEvent): Unit = {
    Camera.toMouseCoords(e.getX, e.getY);
  }

  override def mouseClicked(e: MouseEvent): Unit = {

  }

  override def mouseEntered(e: MouseEvent): Unit = {

  }

  override def mouseExited(e: MouseEvent): Unit = {

  }

  override def mousePressed(e: MouseEvent): Unit = {

  }

  override def mouseDragged(e: MouseEvent): Unit = {

  }

  override def mouseMoved(e: MouseEvent): Unit = {

  }

  object GameMouseWheelListener extends MouseWheelListener {
    override def mouseWheelMoved(e: MouseWheelEvent): Unit = {
      if (e.getWheelRotation < 0) {
        Game.zoomInOut(1)
      } else {
        Game.zoomInOut(-1)
      }
    }
  }

}