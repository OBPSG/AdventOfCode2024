import java.io.File

fun main(args: Array<String>) {

    val inputLines = File("input.txt").readLines()

    for (element in inputLines)
        println(element)

    println("**********************************************************************************************************************************")

    val columns = generateColumns(inputLines)

    println("Columns: ")
    for (element in columns)
        println(element)

    println("**********************************************************************************************************************************")

    val rightDiagonals = generateRightDiagonals(inputLines)
    println("Right Diagonals")
    for (element in rightDiagonals)
        println(element)

    println("**********************************************************************************************************************************")

    val leftDiagonals = generateLeftDiagonals(inputLines)
    println("Left Diagonals")
    for (element in leftDiagonals)
        println(element)

    val xmasPattern = Regex("XMAS")
    val reversePattern = Regex("SAMX")

    var lineMatchCount = 0
    for(string in inputLines)
    {
        val matches = xmasPattern.findAll(string)
        val reverseMatches = reversePattern.findAll(string)
        println("Matches found in " + string + ": " + (matches.count() + reverseMatches.count()) )
        lineMatchCount += matches.count() + reverseMatches.count()
    }
    println("Line matches: $lineMatchCount")

    var columnMatchCount = 0
    for(string in columns)
    {
        val matches = xmasPattern.findAll(string)
        val reverseMatches = reversePattern.findAll(string)
        println("Matches found in " + string + ": " + (matches.count() + reverseMatches.count()) )
        columnMatchCount += matches.count() + reverseMatches.count()
    }
    println("Column matches: $columnMatchCount")

    var rightDiagonalMatchCount = 0
    for(string in rightDiagonals)
    {
        val matches = xmasPattern.findAll(string)
        val reverseMatches = reversePattern.findAll(string)
        println("Matches found in " + string + ": " + (matches.count() + reverseMatches.count()) )
        rightDiagonalMatchCount += matches.count() + reverseMatches.count()
    }
    println("Right Diagonal matches: $rightDiagonalMatchCount")

    var leftDiagonalMatchCount = 0
    for(string in leftDiagonals)
    {
        val matches = xmasPattern.findAll(string)
        val reverseMatches = reversePattern.findAll(string)
        println("Matches found in " + string + ": " + (matches.count() + reverseMatches.count()) )
        leftDiagonalMatchCount += matches.count() + reverseMatches.count()
    }
    println("Right Diagonal matches: $leftDiagonalMatchCount")

    var totalCount = lineMatchCount + columnMatchCount + rightDiagonalMatchCount + leftDiagonalMatchCount

    println("Total match count: $tot   alCount");

}

fun generateColumns(input: List<String>): List<String> {
    var result = mutableListOf<String>()
    for(col in input[0].indices)
    {
        var intermediateResult = ""
        for(row in input.indices)
        {
            intermediateResult += input[row][col]
        }
        result.add(intermediateResult)
    }
    return result
}

fun generateRightDiagonals(input: List<String>): List<String> {
    var result = mutableListOf<String>()
    for(row in input.indices.reversed())
    {
        var intermediateResult = ""
        var col = 0
        while(col + row < input[row].length)
        {
            intermediateResult += input[row + col][col]
            col += 1
        }
        result.add(intermediateResult)
    }
    var col = 1
    while(col < input[0].length)
    {
        var intermediateResult = ""
        var row = 0
        while(col + row < input[row].length)
        {
            intermediateResult += input[row][col + row]
            row += 1
        }
        result.add(intermediateResult)
        col += 1
    }
    return result
}

fun generateLeftDiagonals(input: List<String>): List<String> {
    var result = mutableListOf<String>()
    for(col in input[0].indices){
        var intermediateResult = ""
        var row = 0
        while(col - row >= 0)
        {
            intermediateResult += input[row][col - row]
            row += 1
        }
        result.add(intermediateResult)
    }
    var row = 1
    while(row <= input.indices.max()) // 1 -> 9
    {
        var intermediateResult = ""
        var col = input[0].indices.max()// 9 -> 0
        while(col - row >= 0)
        {
            intermediateResult += input[ input.indices.max()- (col - row)][col] //(1, 9), (2, 8), (3, 7), (4, 6), ... (9, 1)
            col -= 1
        }

        result.add(intermediateResult)
        row += 1
    }
    return result;
}