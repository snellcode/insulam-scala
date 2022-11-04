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
    var x = e.getX
    var y = e.getY
    var centerX = (App.screenWidth / 2) - (App.tileSize / 2)
    var centerY = (App.screenHeight / 2) - (App.tileSize / 2)
    var diffX = x - centerX
    var diffCol = Math.ceil(diffX / App.tileSize).toInt
    var diffY = y - centerY
    var diffRow = Math.ceil(diffY / App.tileSize).toInt

//    println(diffX)

    if (diffX < 0) {
      diffCol = diffCol - 1
    }

    if (diffY < 0) {
      diffRow = diffRow - 1
    }

    var absCol = Math.max(0, Math.min(App.gridCols - 1, diffCol + Math.floor(App.gridCols / 2).toInt))
    var absRow = Math.max(0, Math.min(App.gridRows - 1, diffRow + Math.floor(App.gridRows / 2).toInt))

    Camera.col = absCol + 1
    Camera.row = absRow + 1

//    Camera.col = Math.min((App.gridCols - 1), Math.max(0, diffCol + Math.floor(App.gridCols / 2).toInt + 1))
//    Camera.row = Math.min((App.gridRows - 1), Math.max(0, diffRow + Math.floor(App.gridRows / 2).toInt + 1))
  }

  override def mouseEntered(e: MouseEvent): Unit = {


  }

  override def mouseExited(e: MouseEvent): Unit = {


  }

  override def mousePressed(e: MouseEvent): Unit = {


  }

  override def mouseReleased(e: MouseEvent): Unit = {


  }

  override def mouseDragged(e: MouseEvent): Unit = {


  }

  override def mouseMoved(e: MouseEvent): Unit = {


  }


  //  override def mousePressed(e: MouseEvent): Unit = {
  //    Camera.to(getClickCoords(e))
  //  }
  //
  //  def getClickCoords(e: MouseEvent): Unit = {
  //    println(e.getX)
  //  }
}