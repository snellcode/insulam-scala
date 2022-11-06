package insulam.screens

import insulam.*

import java.awt.{BorderLayout, Color, Graphics, GridLayout}
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.event.ChangeEvent

object OptionsScreen extends JPanel {
  private val defaultsButton = new JButton("defaults")
  private val mainScreenButton = new JButton("main screen")
  private val randomSeedButton = new JButton("random seed")
  private val buttonsPanel = new JPanel
  private val centerPanel = new JPanel
  private val titleLabel = new JLabel("options", SwingConstants.CENTER)
  private val gridColsLabel = new JLabel("grid columns (max " + Game.gridColsLimit + ")")
  private val gridRowsLabel = new JLabel("grid rows (max " + Game.gridRowsLimit + ")")
  private val fieldsPanel = new JPanel(new GridLayout(3, 2))
  private val resetPanel = new JPanel
  private val seedLabel = new JLabel("seed")
  private val seedField = new JSpinner
  private val gridColsField = new JSpinner(new SpinnerNumberModel(Game.gridColsDefault, 1, Game.gridColsLimit, 1))
  private val gridRowsField = new JSpinner(new SpinnerNumberModel(Game.gridRowsDefault, 1, Game.gridRowsLimit, 1))
  private val titleFont = Util.getTitleFont()
  private val stopGameButton = new JButton("stop current game")

  setLayout(new BorderLayout)
  setBorder(new EmptyBorder(110, 10, 10, 10))

  titleLabel.setForeground(Color.black)
  titleLabel.setFont(titleFont)

  buttonsPanel.setBackground(new Color(0, 0, 0, 0))
  buttonsPanel.add(mainScreenButton)
  buttonsPanel.add(defaultsButton)
  buttonsPanel.add(randomSeedButton)

  seedField.setValue(Integer.valueOf(Game.seed))

  resetPanel.setVisible(false)
  resetPanel.add(new JLabel("Current game must be stopped to edit options."))
  resetPanel.add(stopGameButton)

  fieldsPanel.add(gridColsLabel)
  fieldsPanel.add(gridColsField)
  fieldsPanel.add(gridRowsLabel)
  fieldsPanel.add(gridRowsField)
  fieldsPanel.add(seedLabel)
  fieldsPanel.add(seedField)

  centerPanel.add(fieldsPanel)
  centerPanel.add(resetPanel)

  add(titleLabel, BorderLayout.NORTH)
  add(centerPanel, BorderLayout.CENTER)
  add(buttonsPanel, BorderLayout.SOUTH)

  seedField.addChangeListener((e: ChangeEvent) => Game.seed = seedField.getValue.asInstanceOf[Int])
  gridColsField.addChangeListener((e: ChangeEvent) => Game.gridCols = gridColsField.getValue.asInstanceOf[Int])
  gridRowsField.addChangeListener((e: ChangeEvent) => Game.gridRows = gridRowsField.getValue.asInstanceOf[Int])

  mainScreenButton.addActionListener(e => {
    Game.setAppState(SCREENS.INIT)
  })

  randomSeedButton.addActionListener(e => {
    Game.genSeed()
    seedField.setValue(Integer.valueOf(Game.seed))
  })

  defaultsButton.addActionListener(e => {
    gridColsField.setValue(Integer.valueOf(Game.gridColsDefault))
    Game.gridCols = Game.gridColsDefault
    gridRowsField.setValue(Integer.valueOf(Game.gridRowsDefault))
    Game.gridRows = Game.gridRowsDefault
    Game.genSeed()
    seedField.setValue(Integer.valueOf(Game.seed))
  })

  stopGameButton.addActionListener(e => {
    Game.stopGame()
  })

  protected override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val img = Util.getImage("/images/init.png")
    g.drawImage(img, 0, 0, getWidth, getHeight, this)

    if (Game.gameRunning) {
      fieldsPanel.setVisible(false)
      resetPanel.setVisible(true)
      defaultsButton.setVisible(false)
      randomSeedButton.setVisible(false)
    }
    else {
      fieldsPanel.setVisible(true)
      resetPanel.setVisible(false)
      defaultsButton.setVisible(true)
      randomSeedButton.setVisible(true)
    }

    repaint()
  }
}
