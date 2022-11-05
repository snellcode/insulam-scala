package insulam

import com.github.fabioticconi.tergen.HeightMap

import java.awt.{Color, Font, Graphics2D}

object Grid {

  def draw(g2: Graphics2D): Unit = {
    var col = 0
    var row = 0
    while ( {
      col < App.gridCols && row < App.gridRows
    }) {
      if (Camera.isVisible(col, row)) {
        drawTile(g2, col, row)
      }
      col += 1
      if (col == App.gridCols) {
        col = 0
        row += 1
      }
    }
  }

  def drawTile(g2: Graphics2D, col: Int, row: Int): Unit = {
    val screenX = getScreenX(col)
    val screenY = getScreenY(row)
    val value = App.grid(col)(row)
    if (App.tileSize <= 32) {
      val color = TileManager.getTileColor(value, col, row)
      g2.setColor(color)
      g2.fillRect(screenX, screenY, App.tileSize, App.tileSize)
    } else {
      val image = TileManager.getTileImage(value, col, row)
      g2.drawImage(image, screenX, screenY, App.tileSize, App.tileSize, null)
    }
    if (App.debug && App.tileSize > 32) {
      val fs = 12
      g2.setColor(Color.GREEN)
      g2.drawRect(screenX, screenY, App.tileSize, App.tileSize)
      g2.setColor(Color.WHITE)
      g2.fillRect(screenX, screenY, App.tileSize, fs)
      g2.setColor(Color.BLACK)
      g2.setFont(new Font("Courier New", Font.PLAIN, fs));
      g2.drawString(row.toString + "," + col.toString, screenX + 1, screenY + fs)
    }
  }

  private def getScreenX(col: Int) = {
    ((col - (Camera.col + 1)) * App.tileSize) + ((App.screenWidth / 2) - (App.tileSize / 2)) + App.tileSize
  }

  private def getScreenY(row: Int) = {
    ((row - (Camera.row + 1)) * App.tileSize) + ((App.screenHeight / 2) - (App.tileSize / 2)) + App.tileSize
  }

  def getGrid(): Array[Array[Float]] = {
    val heightMap = new HeightMap()
      .size(App.gridCols, App.gridRows)
      .island(0.8f)
    heightMap.fractalNoise
      .set(16, 0.5f, 3f / Math.max(App.gridCols, App.gridRows), 1f)
    heightMap.fractalNoise.seed(App.seed)
    heightMap.build()
  }

}
