package insulam.grid

import insulam.Game
import insulam.input.KeyHandler

import java.awt.{Color, Graphics2D}

object Camera {
  var col = 0
  var row = 0
  var speed = 1

  def center(): Unit = {
    col = Math.floor(Game.gridCols / 2).toInt
    row = Math.floor(Game.gridRows / 2).toInt
  }

  def toMouseCoords(mouseX: Int, mouseY: Int): Unit = {
    val centerX = (Game.screenWidth / 2) - (Game.tileSize / 2)
    val centerY = (Game.screenHeight / 2) - (Game.tileSize / 2)
    val diffX = mouseX - centerX
    val diffY = mouseY - centerY
    var diffCol = Math.ceil(diffX / Game.tileSize).toInt
    var diffRow = Math.ceil(diffY / Game.tileSize).toInt

    if (diffX < 0) {
      diffCol = diffCol - 1
    }

    if (diffY < 0) {
      diffRow = diffRow - 1
    }

    col += diffCol
    row += diffRow
  }

  def update(): Unit = {
    if (KeyHandler.upPressed) {
      row -= speed
      KeyHandler.upPressed = false
    }

    if (KeyHandler.downPressed) {
      row += speed
      KeyHandler.downPressed = false
    }

    if (KeyHandler.leftPressed) {
      col -= speed
      KeyHandler.leftPressed = false
    }

    if (KeyHandler.rightPressed) {
      col += speed
      KeyHandler.rightPressed = false
    }

    // stop camera from going out of bounds
    col = Math.min(Game.gridCols - 1, Math.max(0, col))
    row = Math.min(Game.gridRows - 1, Math.max(0, row))
  }

  def draw(g2: Graphics2D): Unit = {
    g2.setColor(Color.orange)
    val borderSize = 1
    if (Game.drawCamera) {
      g2.drawRect((Game.screenWidth - Game.tileSize + borderSize) / 2, (Game.screenHeight - Game.tileSize + borderSize) / 2, Game.tileSize, Game.tileSize)
    }
  }

  def isVisible(col: Int, row: Int) = {
    col >= getVisibleColMin() && col <= getVisibleColMax()
      && row >= getVisibleRowMin() && row <= getVisibleRowMax()
  }

  def getVisibleColMin() = {
    val buffer = 2;
    val screenTiles = Math.floor(Game.screenWidth / Game.tileSize)
    val offset = screenTiles - Game.gridCols
    (col - Math.floor(screenTiles / 2)) - buffer
  }

  def getVisibleColMax() = {
    val buffer = 2;
    val screenTiles = Math.floor(Game.screenWidth / Game.tileSize)
    val offset = screenTiles - Game.gridCols
    (col + Math.floor(screenTiles / 2)) - 2 + buffer
  }

  def getVisibleRowMin() = {
    val buffer = 2;
    val screenTiles = Math.floor(Game.screenHeight / Game.tileSize)
    val offset = screenTiles - Game.gridRows
    (row - Math.floor(screenTiles / 2)) - buffer
  }

  def getVisibleRowMax() = {
    val buffer = 2;
    val screenTiles = Math.floor(Game.screenHeight / Game.tileSize)
    val offset = screenTiles - Game.gridRows
    (row + Math.floor(screenTiles / 2)) - 2 + buffer
  }

}
