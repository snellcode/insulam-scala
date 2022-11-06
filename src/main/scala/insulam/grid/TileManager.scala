package insulam.grid

import insulam.{Game, Util}

import java.awt.Color
import java.awt.image.BufferedImage

object TileManager {
  private val clamps = Array[Float](
    0.01, // water 0
    0.18, // grassland 1
    0.4, // plains 2
    0.6, // desert 3
    1.0 // mountains 4
  )

  var tiles = Array.ofDim[String](5, 3, 2)
  tiles(0)(0) = Array("/tiles/water.png", "#193298")
  tiles(0)(1) = Array("/tiles/water_rare.png", "#193298")
  tiles(1)(0) = Array("/tiles/grassland.png", "#085c00")
  tiles(1)(1) = Array("/tiles/grassland_rare.png", "#085c00")
  tiles(2)(0) = Array("/tiles/plains.png", "#a28456")
  tiles(2)(1) = Array("/tiles/plains_rare.png", "#a28456")
  tiles(3)(0) = Array("/tiles/desert.png", "#e0bd91")
  tiles(3)(1) = Array("/tiles/desert_rare.png", "#e0bd91")
  tiles(4)(0) = Array("/tiles/mountain.png", "#584748")
  tiles(4)(1) = Array("/tiles/mountain_rare.png", "#584748")
  tiles(4)(2) = Array("/tiles/mountain_very_rare.png", "#584748")

  def getTileColor(value: Float, x: Int, y: Int): Color = {
    val index = getTileIndex(value, x, y);
    val data = tiles(index(0))(index(1))
    Color.decode(data(1))
  }

  def getTileImage(value: Float, x: Int, y: Int): BufferedImage = {
    val index = getTileIndex(value, x, y);
    val data = tiles(index(0))(index(1))
    Util.getImage(data(0))
  }

  def getTileIndex(value: Float, x: Int, y: Int): Array[Int] = {
    if (x == Math.floor(Game.gridCols / 2) && y == Math.floor(Game.gridRows / 2)) {
      return getTileIndexCenter
    }
    if (value <= clamps(0)) {
      return getTileIndexWater(value)
    }
    if (value > clamps(0) && value < clamps(1)) {
      return getTileIndexGrassland(value)
    }
    if (value > clamps(1) && value < clamps(2)) {
      return getTileIndexPlains(value)
    }
    if (value > clamps(2) && value < clamps(3)) {
      return getTileIndexDesert(value)
    }
    if (value > clamps(3) && value < clamps(4)) {
      return getTileIndexMountains(value)
    }
    null
  }

  private def getTileIndexWater(value: Float) = {
    val special = value > clamps(0) / 4
    if (special) Array(0, 1) else Array(0, 0)
  }

  private def getTileIndexGrassland(value: Float) = {
    val special = value > clamps(1) / 1.1
    if (special) Array(1, 1) else Array(1, 0)
  }

  private def getTileIndexPlains(value: Float) = {
    val special = value > clamps(2) / 1.3 && value < clamps(2) / 1.2
    if (special) Array(2, 1) else Array(2, 0)
  }

  private def getTileIndexDesert(value: Float) = {
    val special = value > clamps(3) / 1.3 && value < clamps(3) / 1.2
    if (special) Array(3, 1) else Array(3, 0)
  }

  private def getTileIndexMountains(value: Float) = {
    val special = value > clamps(3) && value < (clamps(3) + 0.1)
    if (special) Array(4, 1) else Array(4, 0)
  }

  private def getTileIndexCenter = {
    Array(4, 2)
  }

}
