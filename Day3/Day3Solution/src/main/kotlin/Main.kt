import java.io.BufferedReader
import java.io.File

fun main(args: Array<String>) {
    var sum = 0

    val bufferedReader: BufferedReader = File("input.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    val mulRegex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    val doRegex = Regex("do\\(\\)")
    val dontRegex = Regex("don't\\(\\)")

    var matches = mutableListOf<String>()
    var matchSequence = mulRegex.findAll(inputString)
    matchSequence.forEach {
        matches.add(it.value)
    }

    for (match in matches) {
        val product = findProduct(match)
        println(match + " Product: $product")
        sum += product
    }

    println("Total ignoring do/don't commands: $sum")

    sum = 0
    var enabled = true;

    val doMatches = doRegex.findAll(inputString).toList()
    val dontMatches = dontRegex.findAll(inputString).toList()
    var matchSequence2 = matchSequence + doMatches + dontMatches
    matchSequence2 = matchSequence2.sortedBy { it.range.min()}
    for(match in matchSequence2){
        print(match.value)
        if(doRegex.matches(match.value)) {
            enabled = true;
            println("| Enabled set to true")
        }
        else if(dontRegex.matches(match.value))
        {
            enabled = false;
            println("| Enabled set to false")
        }
        else if(mulRegex.matches(match.value))
        {
            if(enabled)
            {
                val product = findProduct(match.value)
                println("| Product = ${product}")
                sum+= product
            }
            else
                println("| Skipped")
        }
    }
    println("Sum obeying do/don't: ${sum}")

}

fun findProduct(matchString: String): Int {
    val numberPattern = Regex("\\d+")

    val numbers = numberPattern.findAll(matchString)
    return numbers.elementAt(0).value.toInt() * numbers.elementAt(1).value.toInt()
}