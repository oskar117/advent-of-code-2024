package day02

import java.io.File

fun main() {
    fun part1(input: List<List<Int>>): Int {
        return input.count {
            it.asSequence().windowed(2)
                .all { (a, b) -> (a > b && a.minus(b) in 1..3) } ||
                    it.asSequence().windowed(2)
                        .all { (a, b) -> (a < b && a.minus(b) in -1 downTo -3) }
        }
    }

    fun part2(input: List<List<Int>>): Int {
        return input.count { list ->
            list.indices.any { i ->
                list.filterIndexed { index, _ -> index != i }.asSequence().windowed(2)
                    .all { (a, b) -> (a > b && a.minus(b) in 1..3) }
            } ||
                    list.indices.any { i ->
                        list.filterIndexed { index, _ -> index != i }.asSequence().windowed(2)
                            .all { (a, b) -> (a < b && a.minus(b) in -1 downTo -3) }
                    }
        }
    }

    val input = File("src/day02/input2.txt")
        .readLines()
        .map { it.split(" ").map { n -> n.toInt() } }

    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
