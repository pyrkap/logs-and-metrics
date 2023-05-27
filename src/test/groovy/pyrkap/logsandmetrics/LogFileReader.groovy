package pyrkap.logsandmetrics

trait LogFileReader {

    private static String logDir = "src/test/resources/logs"
    
    List<String> readAllLinesOf(String filename) {
        return new File("$logDir/$filename").readLines()
    }
}