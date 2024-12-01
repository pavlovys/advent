package advent2024

import scala.io.Source

object DataReader {

  def getInput(file: String): String = {
    val source = Source.fromFile(s"src/main/resources/data/$file" )
    try source.getLines().mkString("\n") finally source.close()
  }

}