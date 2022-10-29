package insulam

import java.awt.event.{KeyEvent, MouseWheelEvent, MouseWheelListener}
import javax.swing.JPanel

object AppContent extends JPanel {
  setFocusable(true)
  requestFocus()
  requestFocusInWindow
  grabFocus()
  addKeyListener(KeyHandler)
  addMouseWheelListener(MouseHandler)
}
