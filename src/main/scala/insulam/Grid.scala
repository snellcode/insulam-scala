package insulam

import com.github.fabioticconi.tergen.HeightMap

import java.awt.{Color, Font, Graphics2D}

object Grid {

  def draw(g2: Graphics2D): Unit = {
    var col = 0
    var row = 0
    while ( {
      col < Game.gridCols && row < Game.gridRows
    }) {
      if (Camera.isVisible(col, row)) {
        drawTile(g2, col, row)
      }
      col += 1
      if (col == Game.gridCols) {
        col = 0
        row += 1
      }
    }
  }

  def drawTile(g2: Graphics2D, col: Int, row: Int): Unit = {
    val screenX = getScreenX(col)
    val screenY = getScreenY(row)
    val value = Game.grid(col)(row)
    if (Game.tileSize <= 32) {
      val color = TileManager.getTileColor(value, col, row)
      g2.setColor(color)
      g2.fillRect(screenX, screenY, Game.tileSize, Game.tileSize)
    } else {
      val image = TileManager.getTileImage(value, col, row)
      g2.drawImage(image, screenX, screenY, Game.tileSize, Game.tileSize, null)
    }
    if (Game.debug && Game.tileSize > 32) {
      val fs = 12
      g2.setColor(Color.GREEN)
      g2.drawRect(screenX, screenY, Game.tileSize, Game.tileSize)
      g2.setColor(Color.WHITE)
      g2.fillRect(screenX, screenY, Game.tileSize, fs)
      g2.setColor(Color.BLACK)
      g2.setFont(new Font("Courier New", Font.PLAIN, fs));
      g2.drawString(row.toString + "," + col.toString, screenX + 1, screenY + fs)
    }
  }

  private def getScreenX(col: Int) = {
    ((col - (Camera.col + 1)) * Game.tileSize) + ((Game.screenWidth / 2) - (Game.tileSize / 2)) + Game.tileSize
  }

  private def getScreenY(row: Int) = {
    ((row - (Camera.row + 1)) * Game.tileSize) + ((Game.screenHeight / 2) - (Game.tileSize / 2)) + Game.tileSize
  }

  def getGrid(): Array[Array[Float]] = {
    val heightMap = new HeightMap()
      .size(Game.gridCols, Game.gridRows)
      .island(0.8f)
    heightMap.fractalNoise
      .set(16, 0.5f, 3f / Math.max(Game.gridCols, Game.gridRows), 1f)
    heightMap.fractalNoise.seed(Game.seed)
    heightMap.build()
  }

}
