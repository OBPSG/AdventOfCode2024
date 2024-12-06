import java.io.File

fun main(args: Array<String>) {

    var list1 = mutableListOf<Int>()
    var list2 = mutableListOf<Int>()

    val lines = File("input.txt").useLines {it.toList()}
    lines.forEach {
        val nums = it.split("   ")
        list1.add(nums[0].toInt())
        list2.add(nums[1].toInt())
    }

    list1.sort()
    list2.sort()

    for( i in list1.indices) {
        println(" " + list1[i] + " | " + list2[i])
    }

    var differenceSum = 0
    for(i in list1.indices) {
        val difference = list2[i] - list1[i]

        if(difference >= 0) differenceSum += difference
        else differenceSum += difference*-1
        println("Number 1: " + list1[i] + " | Number 2: " + list2[i] + " | Difference: " + difference)
    }

    println("Sum of differences: $differenceSum")

    var score = 0
    var list2Idx = 0
    var matchCount = 0;

    for(i in list1.indices){
        if (list2[list2Idx] > list1[i] )
        {
            continue;
        }
        while(list2[list2Idx] < list1[i] && list2Idx < list2.size - 1)
            list2Idx++

        if (list2[list2Idx] == list1[i])
        {
            while(list2[list2Idx] == list1[i] && list2Idx < list2.size - 1)
            {
                matchCount++
                list2Idx++
            }
            score += list1[i]*matchCount
        }
        println("Number in list 1: " + list1[i] + " | Number of occurrenes in list 2: " + matchCount + " | Value added to score: " + list1[i]*matchCount)
        matchCount = 0;
    }

    println("Final Score: " + score)

}
