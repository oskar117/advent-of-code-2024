package day03

import java.io.File

fun main() {
    fun part1(input: String): Int {
        return Regex("mul\\((\\d+),(\\d+)\\)")
            .findAll(input)
            .map { x -> x.groupValues.subList(1, x.groupValues.size).map(String::toInt) }
            .sumOf { it.reduce(Int::times) }
    }

    fun part2(input: String): Int {
        var mulEnabled = true
        var result = 0
        val instructions = Regex("mul\\((\\d+),(\\d+)\\)|don't\\(\\)|do\\(\\)").findAll(input).map { it.groupValues }.toList()
        for((full, a, b) in instructions) {
            when(full) {
                "do()" -> mulEnabled = true
                "don't()" -> mulEnabled = false
                else -> if (mulEnabled) result += a.toInt() * b.toInt()
            }
        }
        return result
    }

    val input = File("src/day03/input2.txt").readText()

    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
