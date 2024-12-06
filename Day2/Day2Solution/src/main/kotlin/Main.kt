import java.io.File

fun main(args: Array<String>) {

    var reports = mutableListOf<List<Int>>()
    var numSafeReports = 0
    var numModifiedSafeReports = 0;

    val lines = File("input.txt").useLines { it.toList() }
    lines.forEach {
        val nums = it.split(" ").mapNotNull { it.toIntOrNull() }
        var report = mutableListOf<Int>()
        for (num in nums) {
            report.add(num)
        }
        reports.add(report)

    }

    for (report in reports) {

        var isReportSafe = true;
        var isModifiedReportSafe = true;
        var levelTrendIncreasing: Boolean? = null
        var reportMadeSafeByDeletion = false;
        var levelDeleted = 0;

        if (report[1] > report[0])
            levelTrendIncreasing = true
        else if (report[1] < report[0])
            levelTrendIncreasing = false
        else if (report[1] == report[0]) {
            levelTrendIncreasing = false
            isReportSafe = false
        }

        for (level in 1..report.size - 1) {
            if (report[level] == report[level - 1])
                isReportSafe = false;
            //check if
            else if (levelTrendIncreasing == true && (report[level] - report[level - 1] > 3 || report[level] - report[level - 1] < 0))
                isReportSafe = false;
            else if (levelTrendIncreasing == false && (report[level] - report[level - 1] > 0 || report[level] - report[level - 1] < -3))
                isReportSafe = false;

        }

        if (!isReportSafe) {
            for (levelToDelete in report.indices) {
                isModifiedReportSafe = true;
                var testReport = mutableListOf<Int>()
                for (i in report.indices)
                    testReport.add(report[i])
                testReport.removeAt(levelToDelete)

                 if (testReport[1] > testReport[0])
                    levelTrendIncreasing = true
                else if (testReport[1] < testReport[0])
                    levelTrendIncreasing = false
                else if (testReport[1] == testReport[0]) {
                    levelTrendIncreasing = false
                    isModifiedReportSafe = false
                }

                for (level in 1..testReport.size - 1) {
                    if (testReport[level] == testReport[level - 1])
                        isModifiedReportSafe = false;
                    //check if
                    else if (levelTrendIncreasing == true && (testReport[level] - testReport[level - 1] > 3 || testReport[level] - testReport[level - 1] < 0))
                        isModifiedReportSafe = false;
                    else if (levelTrendIncreasing == false && (testReport[level] - testReport[level - 1] > 0 || testReport[level] - testReport[level - 1] < -3))
                        isModifiedReportSafe = false;
                }
                if (isModifiedReportSafe) {
                    reportMadeSafeByDeletion = true;
                    levelDeleted = levelToDelete
                    break
                }

            }
        }
        if (isReportSafe) {
            numSafeReports += 1
        }

        if (reportMadeSafeByDeletion) {
            println(report + " | Report Trend Increasing: " + levelTrendIncreasing.toString() + " | Report made safe by removing level" + levelDeleted)
            numModifiedSafeReports += 1
        } else {
            println(report + " | Report Trend Increasing: " + levelTrendIncreasing.toString() + " | Is Report Safe: " + isReportSafe)
        }

    }

    println("Total number of safe reports: $numSafeReports")
    println("Total number of reports made safe with deletion: " + (numSafeReports + numModifiedSafeReports))
}