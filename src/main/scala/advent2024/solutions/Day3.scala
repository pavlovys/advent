package advent2024.solutions

import advent2024.DataReader

object Day3 extends App {
  println(Part1.getSolution)
}

object Part1 {
  def getSolution: Long = {
    val input = DataReader.getInput("data1.txt")
    val regex = """mul\(\d{1,3},\d{1,3}\)""".r
    val mulExtractor = """mul\((\d{1,3}),(\d{1,3})\)""".r


    regex.findAllIn(input).toSeq
      .flatMap(s => mulExtractor.findAllMatchIn(s)
      .map(s => (s.group(1).toInt, s.group(2).toInt)).toSeq)
      .map(t => t._1 * t._2)
      .sum

  }
}
