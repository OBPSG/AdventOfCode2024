import java.io.BufferedReader
import java.io.File

fun main(args: Array<String>) {
    var sum = 0

    val bufferedReader: BufferedReader = File("input.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    val Pattern = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")

    var matches = mutableListOf<String>()
    val matchSequence = Pattern.findAll(inputString)
    matchSequence.forEach {
        matches.add(it.value)
    }

    val numberPattern = Regex("\\d+")

    for (match in matches) {
        val numbers = numberPattern.findAll(match)
        val product = numbers.elementAt(0).value.toInt() * numbers.elementAt(1).value.toInt()
        sum += product

        println(match + " Product: $product")
    }

    println("Total: $sum")
}