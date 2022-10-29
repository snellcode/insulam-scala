package insulam

import java.awt.event.{MouseWheelEvent, MouseWheelListener}

object MouseHandler extends MouseWheelListener() {
  override def mouseWheelMoved(e: MouseWheelEvent): Unit = {
    if (e.getWheelRotation < 0) {
      App.zoomInOut(1)
    } else {
      App.zoomInOut(-1)
    }
  }
}