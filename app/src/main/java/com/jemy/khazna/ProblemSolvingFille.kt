package com.jemy.khazna

fun main() {
    val list = listOf(1, 3, 7, 7, 5, 2, 4)
    println("${getMissingAndRepeatingElement(list, list.size - 1)}")
}

private fun getMissingAndRepeatingElement(list: List<Int>, n: Int): List<Int> {
    val resultList = mutableListOf<Int>()
    var repeated = 0
    var missing = 0
    val sortedList = list.sorted()
    for (i in 0 until n) {
        if (sortedList[i] == sortedList[i + 1]) {
            println("Repeated element is:  ${sortedList[i]}")
            repeated = sortedList[i]
        }
        if ((sortedList[i + 1] - sortedList[i]) > 1) {
            println("Missing element is: ${(sortedList[i] + 1)}")
            missing = (sortedList[i] + 1)
        }
    }
    resultList.add(repeated)
    resultList.add(missing)
    return resultList
}