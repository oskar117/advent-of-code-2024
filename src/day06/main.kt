package day06

import java.io.File

fun main() {

    fun part1(map: List<List<Char>>): Int {
        val guardPosition: Pair<Int, Int> = fun(): Pair<Int, Int> {
            for ((lineIndex, line) in map.withIndex()) {
                for ((tileIndex, tile) in line.withIndex()) {
                    if (tile == '^')
                        return lineIndex to tileIndex
                }
            }
            throw IllegalArgumentException()
        }()
        val direction = listOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        var directionChange = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        var currentPosition = guardPosition.copy()
        visited.add(guardPosition)
        while (true) {
            val nextPosition = currentPosition.copy(
                first = currentPosition.first + direction[directionChange % direction.size].first,
                second = currentPosition.second + direction[directionChange % direction.size].second,
            )
            if (nextPosition.first >= map.size || nextPosition.first < 0 || nextPosition.second < 0 || nextPosition.second >= map[0].size) {
                break
            }
            if (map[nextPosition.first][nextPosition.second] == '#') {
                directionChange++
                continue
            }
            visited.add(nextPosition)
            currentPosition = nextPosition
        }
        return visited.size
    }

    fun part2(map: List<List<Char>>): Int {
        val guardPosition: Pair<Int, Int> = fun(): Pair<Int, Int> {
            for ((lineIndex, line) in map.withIndex()) {
                for ((tileIndex, tile) in line.withIndex()) {
                    if (tile == '^')
                        return lineIndex to tileIndex
                }
            }
            throw IllegalArgumentException()
        }()
        fun checkForLoop(map: List<List<Char>>): Boolean {
            val direction = listOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
            var directionChange = 0
            val visited = mutableSetOf<Triple<Int, Int, Pair<Int, Int>>>()
            var currentPosition = guardPosition.copy()
            visited.add(Triple(guardPosition.first, guardPosition.second, direction[directionChange]))
            while (true) {
                val nextPosition = currentPosition.copy(
                    first = currentPosition.first + direction[directionChange % direction.size].first,
                    second = currentPosition.second + direction[directionChange % direction.size].second,
                )
                if (nextPosition.first >= map.size || nextPosition.first < 0 || nextPosition.second < 0 || nextPosition.second >= map[0].size) {
                    return false
                }
                if (map[nextPosition.first][nextPosition.second] == '#') {
                    directionChange++
                    continue
                }
                val nextVisited = Triple(nextPosition.first, nextPosition.second, direction[directionChange % direction.size])
                if (visited.contains(nextVisited)) {
                    return true
                }
                visited.add(nextVisited)
                currentPosition = nextPosition
            }
        }
        var result = 0
        for ((lineIndex, line) in map.withIndex()) {
            for ((tileIndex, tile) in line.withIndex()) {
                if (tile == '.') {
                    val mutatedList = map.toMutableList().apply {
                        val tempLine = this[lineIndex].toMutableList().apply { this[tileIndex] = '#' }
                        this[lineIndex] = tempLine
                    }
                    if (checkForLoop(mutatedList)) result++
                }
            }
        }
        return result
    }

    val input = File("src/day06/input2.txt").readLines().map { it.toCharArray().toList() }

    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
