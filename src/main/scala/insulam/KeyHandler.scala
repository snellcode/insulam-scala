package insulam

import java.awt.event.{ActionEvent, ActionListener, KeyEvent, KeyListener}
import javax.swing.JOptionPane

object KeyHandler extends ActionListener with KeyListener {
  var upPressed = false
  var downPressed = false
  var leftPressed = false
  var rightPressed = false

  override def keyPressed(e: KeyEvent): Unit = {

    val code = e.getKeyCode

    if (code == KeyEvent.VK_C) {
      Camera.center()
      return
    }

    if (code == KeyEvent.VK_SUBTRACT) {
      zoomInOut(-1)
      return
    }
    if (code == KeyEvent.VK_ADD) {
      zoomInOut(1)
      return
    }
    code match {
      case KeyEvent.VK_ESCAPE =>

      case KeyEvent.VK_W =>
        upPressed = true

      case KeyEvent.VK_S =>
        downPressed = true

      case KeyEvent.VK_A =>
        leftPressed = true

      case KeyEvent.VK_D =>
        rightPressed = true

      case _ =>
    }

  }

  def zoomInOut(i: Int): Unit = {
    var newTileSize = App.tileSize + (i * App.zoomIncrements)
    newTileSize = Math.min(128, newTileSize)
    newTileSize = Math.max(8, newTileSize)
    App.tileSize = newTileSize
  }

  override def keyReleased(e: KeyEvent): Unit = {
    val code = e.getKeyCode
    code match {
      case KeyEvent.VK_ESCAPE =>
        if (App.appState eq APP_STATE.INIT) {
          if (JOptionPane.showConfirmDialog(AppContent, "Exit to DOS?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0)
          }
        } else {
          App.setAppState(APP_STATE.INIT)
        }

      case KeyEvent.VK_W =>
        upPressed = false

      case KeyEvent.VK_S =>
        downPressed = false

      case KeyEvent.VK_A =>
        leftPressed = false

      case KeyEvent.VK_D =>
        rightPressed = false

      case _ =>

    }

  }

  override def actionPerformed(e: ActionEvent): Unit = {
  }

  override def keyTyped(e: KeyEvent): Unit = {
  }

}
