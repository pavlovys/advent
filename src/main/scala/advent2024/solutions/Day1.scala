package advent2024.solutions

import advent2024.DataReader

object Day1 extends App {
  println(partOne())
  println(partTwo())

  def partOne(): Int = {
    val (nums1, nums2) = getFormattedInput
    nums1.sorted.zip(nums2.sorted)
      .map(t => Math.abs(t._1 - t._2))
      .sum
  }

  def partTwo(): Int = {
    val (nums1, nums2) = getFormattedInput
    val multiplier = nums2.groupBy(identity).view.mapValues(_.size)
    nums1.map(num => multiplier.getOrElse(num, 0) * num).sum
  }

  def getFormattedInput: (Seq[Int], Seq[Int]) = {
    DataReader.getInput("data1.txt")
      .split("\n")
      .map(s => {
        val row = s.split(" +").map(_.toInt)
        (row(0), row(1))
      }).toSeq.unzip
    }
}
