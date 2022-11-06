package insulam

import insulam.grid.{Camera, Grid}
import insulam.input.{KeyHandler, MouseHandler}
import insulam.screens.*

import java.awt.{BorderLayout, CardLayout, Dimension}
import javax.imageio.ImageIO
import javax.swing.{JFrame, JPanel, WindowConstants}

enum SCREENS:
  case INIT, OPTIONS, GAME, HELP

object Game extends JFrame {
  val screenWidth = 1280
  val screenHeight = 800
  val gridColsDefault = 100
  val gridRowsDefault = 100
  val gridColsLimit = 500
  val gridRowsLimit = 500
  val zoomIncrements = 8
  val minZoom = 1
  val maxZoom = zoomIncrements * 16
  private val card = new CardLayout
  var jFrameOffsetX = 16
  var jFrameOffsetY = 39
  var appState = SCREENS.INIT
  var seed: Int = (Math.random * 1000).toInt
  var gridCols = gridColsDefault
  var gridRows = gridRowsDefault
  var grid: Array[Array[Float]] = null
  var tileSize = 8
  var drawCamera = true
  var gameRunning = false
  var debug = false

  def main(): Unit = {
    ImageIO.setUseCache(false)
    setIconImage(Util.getImage("/images/init.png"))
    setTitle("insulam")
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setResizable(false)
    setLayout(new BorderLayout)
    setPreferredSize(new Dimension(screenWidth + jFrameOffsetX, screenHeight + jFrameOffsetY))
    add(GameContent)
    pack
    setLocationRelativeTo(null)
    setVisible(true)
    GameContent.setLayout(card)
    GameContent.add(InitScreen, String.valueOf(SCREENS.INIT))
    GameContent.add(OptionsScreen, String.valueOf(SCREENS.OPTIONS))
    GameContent.add(GameScreen, String.valueOf(SCREENS.GAME))
    GameContent.add(HelpScreen, String.valueOf(SCREENS.HELP))
    card.show(GameContent, String.valueOf(appState))
  }

  def resetGame(): Unit = {
    stopGame()
    startGame()
  }

  def startGame(): Unit = {
    grid = Grid.getGrid()
    Camera.center()
    GameScreen.start()
    setAppState(SCREENS.GAME)
    gameRunning = true
  }

  def setAppState(appState: SCREENS): Unit = {
    this.appState = appState
    card.show(GameContent, String.valueOf(appState))
  }

  def stopGame(): Unit = {
    gameRunning = false
    GameScreen.stop()
  }

  def zoomInOut(i: Int): Unit = {
    var newTileSize = tileSize + (i * zoomIncrements)
    newTileSize = Math.min(maxZoom, newTileSize)
    newTileSize = Math.max(minZoom, newTileSize)
    tileSize = newTileSize
  }

  def genSeed(): Unit = {
    seed = (Math.random * 1000).toInt
  }

  object GameContent extends JPanel {
    setFocusable(true)
    requestFocus()
    requestFocusInWindow
    grabFocus()
    addKeyListener(KeyHandler)
    addMouseWheelListener(MouseHandler.handleMouseWheel)
    addMouseListener(MouseHandler)
    addMouseMotionListener(MouseHandler)
  }

}
