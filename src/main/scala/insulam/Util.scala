package insulam

import java.awt.Font
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO

object Util {
  def getTitleFont(): Font = {
    val is = getClass.getResourceAsStream("/fonts/x12y16pxMaruMonica/x12y16pxMaruMonica.ttf")
    Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 100)
  }

  def getImage(path: String): BufferedImage = {
    try return ImageIO.read(getClass.getResource(path))
    catch {
      case e: IOException =>
        e.printStackTrace()
        System.exit(1)
    }
    null
  }

}
