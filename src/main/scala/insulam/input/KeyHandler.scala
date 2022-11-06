package insulam.input

import insulam.*
import insulam.grid.Camera

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
      Game.zoomInOut(-1)
      return
    }
    if (code == KeyEvent.VK_ADD) {
      Game.zoomInOut(1)
      return
    }
    if (code == KeyEvent.VK_F3) {
      Game.debug = !Game.debug;
    }
    if (code == KeyEvent.VK_F1) {
      if (Game.appState == SCREENS.GAME) {
        Game.setAppState(SCREENS.HELP)
      } else if (Game.appState == SCREENS.HELP) {
        Game.setAppState(SCREENS.GAME)
      }
    }
    code match {
      case KeyEvent.VK_NUMPAD7 =>
        upPressed = true
        leftPressed = true

      case KeyEvent.VK_NUMPAD9 =>
        upPressed = true
        rightPressed = true

      case KeyEvent.VK_NUMPAD3 =>
        downPressed = true
        rightPressed = true

      case KeyEvent.VK_NUMPAD1 =>
        downPressed = true
        leftPressed = true

      case KeyEvent.VK_NUMPAD8 | KeyEvent.VK_W =>
        upPressed = true

      case KeyEvent.VK_NUMPAD2 | KeyEvent.VK_S =>
        downPressed = true

      case KeyEvent.VK_NUMPAD4 | KeyEvent.VK_A =>
        leftPressed = true

      case KeyEvent.VK_NUMPAD6 | KeyEvent.VK_D =>
        rightPressed = true

      case _ =>
    }

  }

  override def keyReleased(e: KeyEvent): Unit = {
    val code = e.getKeyCode
    code match {
      case KeyEvent.VK_ESCAPE =>
        if (Game.appState eq SCREENS.INIT) {
          if (JOptionPane.showConfirmDialog(Game, "Exit to DOS?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0)
          }
        } else {
          Game.setAppState(SCREENS.INIT)
        }

      case KeyEvent.VK_NUMPAD7 =>
        upPressed = false
        rightPressed = false

      case KeyEvent.VK_NUMPAD9 =>
        upPressed = false
        rightPressed = false

      case KeyEvent.VK_NUMPAD3 =>
        downPressed = false
        rightPressed = false

      case KeyEvent.VK_NUMPAD1 =>
        downPressed = false
        leftPressed = false

      case KeyEvent.VK_NUMPAD8 | KeyEvent.VK_W =>
        upPressed = false

      case KeyEvent.VK_NUMPAD2 | KeyEvent.VK_S =>
        downPressed = false

      case KeyEvent.VK_NUMPAD4 | KeyEvent.VK_A =>
        leftPressed = false

      case KeyEvent.VK_NUMPAD6 | KeyEvent.VK_D =>
        rightPressed = false

      case _ =>

    }

  }

  override def actionPerformed(e: ActionEvent): Unit = {
  }

  override def keyTyped(e: KeyEvent): Unit = {
  }

}
