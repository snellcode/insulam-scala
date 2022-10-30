package insulam

import java.awt.{BorderLayout, Color, Graphics}
import javax.swing.*
import javax.swing.border.EmptyBorder

object Init extends JPanel {
  private val titleFont = Util.getTitleFont()
  private val optionsButton = new JButton("options")
  private val newGameButton = JButton("new game")
  private val buttonsPanel = new JPanel
  private val titleLabel = new JLabel("insulam", SwingConstants.CENTER)
  private val resumeGameButton: JButton = new JButton("resume game")

  setLayout(new BorderLayout)
  setBorder(new EmptyBorder(110, 10, 10, 10))

  resumeGameButton.setVisible(false)

  titleLabel.setForeground(Color.black)
  titleLabel.setFont(titleFont)

  buttonsPanel.setBackground(new Color(0, 0, 0, 0))
  buttonsPanel.add(optionsButton)
  buttonsPanel.add(resumeGameButton)
  buttonsPanel.add(newGameButton)

  add(titleLabel, BorderLayout.NORTH)
  add(buttonsPanel, BorderLayout.SOUTH)

  optionsButton.addActionListener(e => {
    App.setAppState(APP_STATE.OPTIONS)
  })

  newGameButton.addActionListener(e => {
    if (App.gameRunning) {
      if (JOptionPane.showConfirmDialog(null, "Reset to New Game?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
        App.resetGame()
      }
    } else {
      App.startGame()
    }
  })

  resumeGameButton.addActionListener(e => {
    App.setAppState(APP_STATE.GAME)
  })

  protected override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val img = Util.getImage("/images/init.png")
    g.drawImage(img, 0, 0, getWidth, getHeight, this)
    resumeGameButton.setVisible(App.gameRunning)
    repaint()
  }

}
