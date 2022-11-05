package insulam

import java.awt.{BorderLayout, CardLayout, Dimension}
import javax.imageio.ImageIO
import javax.swing.{JFrame, WindowConstants}

object App extends JFrame {
  val screenWidth = 1280
  val screenHeight = 800
  val gridColsDefault = 65
  val gridRowsDefault = 65
  val gridColsLimit = 320
  val gridRowsLimit = 320
  val zoomIncrements = 8
  val minZoom = zoomIncrements
  val maxZoom = zoomIncrements * 16
  private val card = new CardLayout
  var jFrameOffsetX = 16
  var jFrameOffsetY = 39
  var appState = APP_STATE.INIT
  var seed: Int = (Math.random * 1000).toInt
  var gridCols = gridColsDefault
  var gridRows = gridRowsDefault
  var grid: Array[Array[Float]] = null
  var tileSize = minZoom
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
    add(AppContent)
    pack
    setLocationRelativeTo(null)
    setVisible(true)
    AppContent.setLayout(card)
    AppContent.add(Init, String.valueOf(APP_STATE.INIT))
    AppContent.add(Options, String.valueOf(APP_STATE.OPTIONS))
    AppContent.add(Game, String.valueOf(APP_STATE.GAME))
    card.show(AppContent, String.valueOf(appState))
  }

  def resetGame(): Unit = {
    stopGame()
    startGame()
  }

  def startGame(): Unit = {
    grid = Grid.getGrid()
    Camera.center()
    Game.start()
    setAppState(APP_STATE.GAME)
    gameRunning = true
  }

  def setAppState(appState: APP_STATE): Unit = {
    this.appState = appState
    card.show(AppContent, String.valueOf(appState))
  }

  def stopGame(): Unit = {
    gameRunning = false
    Game.stop()
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

}
