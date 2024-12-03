package advent2024.solutions

import advent2024.DataReader

object Day3 extends App {
  println(Part1.getSolution)
  println(Part2.getSolution)
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

object Part2 {
  val input = DataReader.getInput("data1.txt")
  def getSolution: Long = {
    def go(s: String, acc: Long, isDo: Boolean): Long = {
      s match {
        case "" => acc
        case s"do()$rest" => go(rest, acc, true)
        case s"don't()$rest" => go(rest, acc, false)
        case s"mul(${num1},${num2})$rest"  if num1.forall(_.isDigit) &&
          num2.forall(_.isDigit) &&
          num1.length <= 3 && num2.length <= 3 &&
          num1.length >= 0 && num2.length >= 0 && isDo => go(rest, (num1.toLong * num2.toLong) + acc, isDo)
        case _ => go(s.tail, acc, isDo)
      }
    }
    go(input, 0, true)
  }
}
