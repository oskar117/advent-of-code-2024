package day01

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val splitList = input.map { x -> x.split("\\s+".toRegex()).map { it.toInt() } }
        val left = splitList.map { it[0] }.sorted()
        val right = splitList.map { it[1] }.sorted()

        return left.mapIndexed { index, number -> number.minus(right[index]).absoluteValue}.sum()
    }

    fun part2(input: List<String>): Int {
        val splitList = input.map { x -> x.split("\\s+".toRegex()).map { it.toInt() } }
        val left = splitList.map { it[0] }
        val right = splitList.map { it[1] }.groupingBy { it }.eachCount()

        return left.sumOf { it * right.getOrDefault(it, 0) }
    }

    val input = File("src/day01/input2.txt").readLines()

    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
