package insulam

import java.awt.{Color, Graphics2D}

object Camera {
  var col = 0
  var row = 0
  var speed = 1

  center()

  def center(): Unit = {
    col = (Math.floor(App.gridCols / 2) + 1).toInt
    row = (Math.floor(App.gridRows / 2) + 1).toInt
  }

  def to(coords: Unit): Unit = {
    println(coords)
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
    col = Math.min(App.gridCols, Math.max(1, col))
    row = Math.min(App.gridRows, Math.max(1, row))
  }

  def draw(g2: Graphics2D): Unit = {
    g2.setColor(Color.orange)
    val borderSize = 1
    if (App.drawCamera) {
      g2.drawRect(0, 0, App.tileSize, App.tileSize)
      g2.drawRect(0, App.screenHeight - App.tileSize - borderSize, App.tileSize, App.tileSize)
      g2.drawRect(App.screenWidth - App.tileSize - borderSize, App.screenHeight - App.tileSize - borderSize, App.tileSize, App.tileSize)
      g2.drawRect(App.screenWidth - App.tileSize - borderSize, 0, App.tileSize, App.tileSize)
      g2.drawRect((App.screenWidth - App.tileSize + borderSize) / 2, (App.screenHeight - App.tileSize + borderSize) / 2, App.tileSize, App.tileSize)
    }
  }

  def isVisible(col: Int, row: Int) = {
    col >= getVisibleColMin() && col <= getVisibleColMax()
      && row >= getVisibleRowMin() && row <= getVisibleRowMax()
  }

  def getVisibleColMin() = {
    val buffer = 2;
    val screenTiles = Math.floor(App.screenWidth / App.tileSize)
    val offset = screenTiles - App.gridCols
    (col - Math.floor(screenTiles / 2)) - buffer
  }

  def getVisibleColMax() = {
    val buffer = 2;
    val screenTiles = Math.floor(App.screenWidth / App.tileSize)
    val offset = screenTiles - App.gridCols
    (col + Math.floor(screenTiles / 2)) - 2 + buffer
  }

  def getVisibleRowMin() = {
    val buffer = 2;
    val screenTiles = Math.floor(App.screenHeight / App.tileSize)
    val offset = screenTiles - App.gridRows
    (row - Math.floor(screenTiles / 2)) - buffer
  }

  def getVisibleRowMax() = {
    val buffer = 2;
    val screenTiles = Math.floor(App.screenHeight / App.tileSize)
    val offset = screenTiles - App.gridRows
    (row + Math.floor(screenTiles / 2)) - 2 + buffer
  }

}