package eu.cr4zyfl1x.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log entry object
 */
public class LogEntry {

    /**
     * LogType object
     */
    private final LogType logType;

    /**
     * Message of log entry
     */
    private final String message;

    /**
     * Timestamp of log entry
     */
    private final Date timestamp;

    /**
     * Creates a new LogEntry object
     * @param timestamp Timestamp of the log entry
     * @param logType LogType object
     * @param message Message of log entry as String
     */
    public LogEntry(Date timestamp, LogType logType, String message)
    {
        this.timestamp = timestamp;
        this.logType = logType;
        this.message = message;
    }

    /**
     * Gets the timestamp of the log entry
     * @return Timestamp of log entry
     */
    public Date getTimestamp()
    {
        return timestamp;
    }

    /**
     * Gets the LogType object
     * @return LogType object
     */
    public LogType getLogType()
    {
        return logType;
    }

    /**
     * Gets the message of the log entry
     * @return Message as String
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Gets the whole log entry as String
     * @return Log entry as String
     */
    public String getLogEntry()
    {
        return "[" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(this.timestamp) + "] " + "[" + logType.getPrefix() + "]: " + message;
    }
}
