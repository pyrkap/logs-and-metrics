package pyrkap.logsandmetrics

import org.slf4j.LoggerFactory
import spock.lang.Specification

class Task1_PatternTest extends Specification implements LogFileReader {

    def "log file should contain full date, time and message"() {
        given:
        def logger = LoggerFactory.getLogger("logger-test-1")
        // looking for line e.g. 2023-05-27 10:20:24,028 Test message
        def expectedPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\sTest\\smessage\$"

        when:
        logger.info("Test message")

        and:
        def logs = readAllLinesOf("file-test-1.log")

        then:
        logs.size() == 1
        logs[0] ==~ expectedPattern
    }

    def "log file should contain full date, time, log-level and message"() {
        given:
        def logger = LoggerFactory.getLogger("logger-test-2")
        // looking for line e.g. 2023-05-27 10:20:24,028 INFO Test message
        def expectedInfoPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\sINFO\\sTest\\smessage\$"
        // looking for line e.g. 2023-05-27 10:20:24,028 WARN Test message
        def expectedWarnPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\sWARN\\sTest\\smessage\$"

        when:
        logger.info("Test message")
        logger.warn("Test message")

        and:
        def logs = readAllLinesOf("file-test-2.log")

        then:
        logs.size() == 2
        logs[0] ==~ expectedInfoPattern
        logs[1] ==~ expectedWarnPattern
    }

    def "log file should contain full date, time, logger-name and message"() {
        given:
        def logger3a = LoggerFactory.getLogger("logger-test-3-a")
        def logger3b = LoggerFactory.getLogger("logger-test-3-b")
        // looking for line e.g. 2023-05-27 10:20:24,028 logger-test-3-a Test message
        def expected3aPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\slogger-test-3-a\\sTest\\smessage\$"
        // looking for line e.g. 2023-05-27 10:20:24,028 logger-test-3-b Test message
        def expected3bPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\slogger-test-3-b\\sTest\\smessage\$"

        when:
        logger3a.info("Test message")
        logger3b.info("Test message")

        and:
        def logs = readAllLinesOf("file-test-3.log")

        then:
        logs.size() == 2
        logs[0] ==~ expected3aPattern
        logs[1] ==~ expected3bPattern
    }

    def "log file should contain full date, time, thread-name and message"() {
        given:
        def logger = LoggerFactory.getLogger("logger-test-4")
        // looking for line e.g. 2023-05-27 10:20:24,028 logger-test-3-a Test message
        def expectedThreadAPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\sthread-a\\sTest\\smessage\$"
        // looking for line e.g. 2023-05-27 10:20:24,028 logger-test-3-b Test message
        def expectedThreadBPattern = ~"^[0-9\\-]{10}\\s[0-9:,]{12}\\sthread-b\\sTest\\smessage\$"

        when:
        Thread.currentThread().setName("thread-a")
        logger.info("Test message")
        Thread.currentThread().setName("thread-b")
        logger.info("Test message")

        and:
        def logs = readAllLinesOf("file-test-4.log")

        then:
        logs.size() == 2
        logs[0] ==~ expectedThreadAPattern
        logs[1] ==~ expectedThreadBPattern
    }
}
