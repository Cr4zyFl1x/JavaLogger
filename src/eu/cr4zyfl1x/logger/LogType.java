package eu.cr4zyfl1x.logger;

import java.awt.*;

/**
 * LogType for log entries
 */
public class LogType {

    /**
     * LogType for INFORMATION entries
     */
    private static final LogType info = new LogType("INFO", new Color(0, 153, 255));

    /**
     * LogType for INFORMATION entries
     */
    public static final LogType INFORMATION = info;


    /**
     * LogType for WARNING entries
     */
    private static final LogType warning = new LogType("WARNING", new Color(255, 204, 0));

    /**
     * LogType for WARNING entries
     */
    public static final LogType WARNING = warning;


    /**
     * LogType for ERROR entries
     */
    private static final LogType error = new LogType("ERROR", new Color(255, 102, 102));

    /**
     * LogType for ERROR entries
     */
    public static final LogType ERROR = error;


    /**
     * LogType for CRITICAL entries
     */
    private static final LogType critical = new LogType("CRITICAL", Color.RED);

    /**
     * LogType for CRITICAL entries
     */
    public static final LogType CRITICAL = critical;


    /**
     * LogType for SYSTEM entries
     */
    private static final LogType system = new LogType("SYSTEM", new Color(153, 51, 102));

    /**
     * LogType for SYSTEM entries
     */
    public static final LogType SYSTEM = system;


    /**
     * Prefix of log type
     */
    private final String prefix;

    /**
     * Color of log type
     */
    private final Color color;

    /**
     * Creates a new LogType object
     * @param prefix Prefix for log entries with that type
     * @param color Color of that log type
     */
    public LogType(String prefix, Color color)
    {
        this.prefix = prefix;
        this.color = color;
    }

    /**
     * Gets the LogType prefix
     * @return LogType prefix
     */
    public String getPrefix()
    {
        return prefix;
    }

    /**
     * Gets the color of log type as Color object
     * @return Color object
     */
    public Color getColor()
    {
        return color;
    }
}
