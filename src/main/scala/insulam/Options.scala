package insulam

import java.awt.{BorderLayout, Color, Graphics, GridLayout}
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.event.ChangeEvent

object Options extends JPanel {
  private val defaultsButton = new JButton("defaults")
  private val mainScreenButton = new JButton("main screen")
  private val randomSeedButton = new JButton("random seed")
  private val buttonsPanel = new JPanel
  private val centerPanel = new JPanel
  private val titleLabel = new JLabel("options", SwingConstants.CENTER)
  private val gridColsLabel = new JLabel("grid columns")
  private val gridRowsLabel = new JLabel("grid rows")
  private val fieldsPanel = new JPanel(new GridLayout(3, 2))
  private val resetPanel = new JPanel
  private val seedLabel = new JLabel("seed")
  private val seedField = new JSpinner
  private val gridColsField = new JSpinner(new SpinnerNumberModel(App.gridColsDefault, 1, App.gridColsLimit, 1))
  private val gridRowsField = new JSpinner(new SpinnerNumberModel(App.gridRowsDefault, 1, App.gridRowsLimit, 1))
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

  seedField.setValue(Integer.valueOf(App.seed))

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

  seedField.addChangeListener((e: ChangeEvent) => App.seed = seedField.getValue.asInstanceOf[Int])
  gridColsField.addChangeListener((e: ChangeEvent) => App.gridCols = gridColsField.getValue.asInstanceOf[Int])
  gridRowsField.addChangeListener((e: ChangeEvent) => App.gridRows = gridRowsField.getValue.asInstanceOf[Int])

  mainScreenButton.addActionListener(e => {
    App.setAppState(APP_STATE.INIT)
  })

  randomSeedButton.addActionListener(e => {
    App.genSeed()
    seedField.setValue(Integer.valueOf(App.seed))
  })

  defaultsButton.addActionListener(e => {
    gridColsField.setValue(Integer.valueOf(App.gridColsDefault))
    App.gridCols = App.gridColsDefault
    gridRowsField.setValue(Integer.valueOf(App.gridRowsDefault))
    App.gridRows = App.gridRowsDefault
    App.genSeed()
    seedField.setValue(Integer.valueOf(App.seed))
  })

  stopGameButton.addActionListener(e => {
    App.stopGame()
  })

  protected override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val img = Util.loadImage("/images/init.png")
    g.drawImage(img, 0, 0, getWidth, getHeight, this)

    if (App.gameRunning) {
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
