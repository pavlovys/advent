package advent2024.solutions

import advent2024.DataReader
import advent2024.solutions.PartOne.getSafeLevelsCountPart1
import advent2024.solutions.PartTwo.getSafeLevelsCountPart2

import scala.annotation.tailrec

object Day2 extends App{
    println(getSafeLevelsCountPart1)
    println(getSafeLevelsCountPart2)
}

object PartOne {
    val data = DataReader.getInput("data1.txt")
    val reports = data.split("\n").map(s => s.split(" ").map(_.toInt).toSeq)
    def getSafeLevelsCountPart1: Int = reports.count(filterUnsafe)
    def filterUnsafe(level: Seq[Int]): Boolean = {
        @tailrec
        def go(level: Seq[Int], asc: Boolean): Boolean = {
            if (level.size == 1) true
            else if (asc && (level(1) - level.head) <= 3 && (level(1) - level.head) >= 1) go(level.tail, asc)
            else if (!asc && (level.head - level(1) <= 3) && (level.head - level(1) >= 1)) go(level.tail, asc)
            else false
        }
        if (level.head < level(1)) go(level, asc = true)
        else go(level, asc = false)
    }
}

object PartTwo {
    val data = DataReader.getInput("data1.txt")
    val reports = data.split("\n").map(s => s.split(" ").map(_.toInt).toSeq)

    def getSafeLevelsCountPart2: Int = reports.count(filterUnsafe)

    def filterUnsafe(level: Seq[Int]): Boolean = {
        def isSafe(level: Seq[Int]): Boolean = {
            @tailrec
            def go(level: Seq[Int], asc: Boolean): Boolean = {
                if (level.size <= 1) true
                else if (asc && (level(1) - level.head) <= 3 && (level(1) - level.head) >= 1) go(level.tail, asc)
                else if (!asc && (level.head - level(1) <= 3) && (level.head - level(1) >= 1)) go(level.tail, asc)
                else false
            }
            if (level.head < level(1)) go(level, asc = true)
            else go(level, asc = false)
        }
        if (isSafe(level)) true
        else {
            level.indices.exists(i => isSafe(level.take(i) ++ level.drop(i + 1)))
        }
    }

}

