package insulam.screens

import insulam.*

import java.awt.{BorderLayout, Color, Graphics, GridLayout}
import javax.swing.*
import javax.swing.border.EmptyBorder

object HelpScreen extends JPanel {
  private val centerPanel = new JPanel
  private val titleLabel = new JLabel("help", SwingConstants.CENTER)
  private val gridColsLabel = new JLabel("grid columns (max " + Game.gridColsLimit + ")")
  private val gridRowsLabel = new JLabel("grid rows (max " + Game.gridRowsLimit + ")")
  private val fieldsPanel = new JPanel(new GridLayout(4, 2))
  private val resetPanel = new JPanel
  private val seedLabel = new JLabel("seed")
  private val seedField = new JSpinner
  private val gridColsField = new JSpinner(new SpinnerNumberModel(Game.gridColsDefault, 1, Game.gridColsLimit, 1))
  private val gridRowsField = new JSpinner(new SpinnerNumberModel(Game.gridRowsDefault, 1, Game.gridRowsLimit, 1))
  private val titleFont = Util.getTitleFont()

  setLayout(new BorderLayout)
  setBorder(new EmptyBorder(110, 10, 10, 10))

  titleLabel.setForeground(Color.black)
  titleLabel.setFont(titleFont)

  seedField.setValue(Integer.valueOf(Game.seed))

  fieldsPanel.add(new JLabel("F1: "))
  fieldsPanel.add(new JLabel("toggle this screen"))

  fieldsPanel.add(new JLabel("F3: "))
  fieldsPanel.add(new JLabel("toggle debug"))

  fieldsPanel.add(new JLabel("- / + / mousewheel: "))
  fieldsPanel.add(new JLabel("zoom"))

  fieldsPanel.add(new JLabel("wasd / numpad / click: "))
  fieldsPanel.add(new JLabel("move"))

  centerPanel.add(fieldsPanel, BorderLayout.NORTH)

  add(titleLabel, BorderLayout.NORTH)
  add(JLabel("keys", SwingConstants.CENTER), BorderLayout.NORTH)
  add(centerPanel, BorderLayout.CENTER)

  protected override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val img = Util.getImage("/images/init.png")
    g.drawImage(img, 0, 0, getWidth, getHeight, this)
    repaint()
  }
}
