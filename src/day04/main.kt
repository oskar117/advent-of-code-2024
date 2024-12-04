package day04

import java.io.File

fun main() {
    fun findChar(list: List<String>, startX: Int, startY: Int, dirX: Int, dirY: Int, step: Int): Boolean {
        val nextX = startX+(dirX * step)
        val nextY = startY+(dirY * step)
        if (nextX < 0 || nextY < 0 || nextX > list[0].length - 1 || nextY > list.size - 1) return false
        val char = list[nextY][nextX]
        return when {
            char == 'X' && step == 0 -> findChar(list, startX, startY, dirX, dirY, 1)
            char == 'M' && step == 1 -> findChar(list, startX, startY, dirX, dirY, 2)
            char == 'A' && step == 2 -> findChar(list, startX, startY, dirX, dirY, 3)
            char == 'S' && step == 3 -> true
            else -> false
        }
    }

    fun part1(input: List<String>): Int {
        val directions = mutableSetOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
        var result = 0
        for ((y, line) in input.withIndex()) {
            for (x in line.indices) {
                for (direction in directions) {
                    if (findChar(input, x, y, direction.first, direction.second, 0))
                        result++
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for ((y, line) in input.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char == 'A') {
                    val tl ="${input.getOrNull(y + 1)?.getOrNull(x - 1) ?: ' '}${input.getOrNull(y - 1)?.getOrNull(x + 1) ?: ' '}"
                    val tr ="${input.getOrNull(y + 1)?.getOrNull(x + 1) ?: ' '}${input.getOrNull(y - 1)?.getOrNull(x - 1) ?: ' '}"
                    val bl ="${input.getOrNull(y - 1)?.getOrNull(x - 1) ?: ' '}${input.getOrNull(y + 1)?.getOrNull(x + 1) ?: ' '}"
                    val br ="${input.getOrNull(y - 1)?.getOrNull(x + 1) ?: ' '}${input.getOrNull(y + 1)?.getOrNull(x - 1) ?: ' '}"
                    if (listOf(tl to tr, bl to br, tl to br, tr to bl, tl to bl, tr to br).any {it -> it.first == "MS" && it.second == "MS"})
                        result++
                }
            }
        }
        return result
    }

    val input = File("src/day04/input2.txt").readLines()

    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
