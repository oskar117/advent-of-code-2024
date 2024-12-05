package day05

import java.io.File

fun main() {

    fun part1(rules: List<Pair<Int, Int>>, pages: List<List<Int>>): Int {
        return pages.filter {
            rules.all { rule ->
                !it.contains(rule.second) || !it.contains(rule.second) || it.indexOf(rule.first) < it.indexOf(
                    rule.second
                )
            }
        }.sumOf { it[it.size / 2] }
    }

    fun part2(rules: List<Pair<Int, Int>>, pages: List<List<Int>>): Int {
        return pages.filter {
            !rules.all { rule ->
                !it.contains(rule.second) || !it.contains(rule.second) || it.indexOf(rule.first) < it.indexOf(
                    rule.second
                )
            }
        }.map { unsorted ->
            val list = unsorted.toMutableList()
            for (i in 0..<list.size) {
                for (j in 0..<list.size - i - 1) {
                    if (!rules.contains(list[j] to list[j + 1])) {
                        val temp = list[j]
                        list[j] = list[j + 1]
                        list[j + 1] = temp
                    }
                }
            }
            list
        }.sumOf { it[it.size / 2] }
    }

    val input = File("src/day05/input2.txt").readText().split("\n\n")
    val rules = input.first()
        .split("\n")
        .map { it.split("|") }
        .map { line -> line[0].toInt() to line[1].toInt() }
    val pages = input.last()
        .split("\n")
        .map { it.split(",").map { i -> i.toInt() } }

    println("Part1: ${part1(rules, pages)}")
    println("Part2: ${part2(rules, pages)}")
}
