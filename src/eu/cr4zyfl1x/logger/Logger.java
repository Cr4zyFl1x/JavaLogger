package eu.cr4zyfl1x.logger;

import eu.cr4zyfl1x.logger.exception.InvalidLoggerException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger for logging messages in console and/or logfiles
 */
public class Logger {

    /**
     * Logger version string
     */
    private static final String version = "1.0.0";

    /**
     * Loaded logger
     */
    private static Logger logger;

    /**
     * Logger creation timestamp
     */
    private final Date createdAt;

    /**
     * Logger name
     */
    private final String name;


    /**
     * Directory path of the logfile
     */
    private String directory;

    /**
     * Name of the logfile
     */
    private String logfilename;

    /**
     * Logfile
     */
    private File logfile;

    /**
     * Creates a new Logger instance
     * @param name Name of Logger
     * @param createdAt Creation Date of Logger
     */
    public Logger(String name, Date createdAt)
    {
        this.createdAt = createdAt;
        this.name = name;
    }

    /**
     * Creates a new Logger instance
     * @param name Name of Logger
     * @param createdAt Creation Date of Logger
     * @param directory Path to directory to save logfile in
     */
    public Logger(String name, Date createdAt, String directory)
    {
        this.createdAt = createdAt;
        this.name = name;
        this.directory = directory;
        this.logfilename = this.name + "_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(createdAt) + ".log";
        this.logFileCheck();
    }


    /**
     * Checks if a logfile and its directory exists.
     * Otherwise they will be created
     */
    private void logFileCheck()
    {
        File logfileDir = new File(directory);
        if (!logfileDir.exists()) {
            if (!logfileDir.mkdirs()) {
                System.out.println("CRITICAL ERROR: Logfile directory does not exist and can not be created!");
            } else {
                System.out.println("INFORMATION: Logfile directory does not exist but was created!");
            }
        }

        File logfile = new File(directory + "/" + this.logfilename);
        if (logfile.exists()) {
            try {
                logfile.renameTo(new File(directory + "/" + this.logfilename + ".old"));
                logfile.delete();
                if (logfile.createNewFile()) {
                    this.logfile = logfile;
                    System.out.println("INFORMATION: Logfile '" + logfilename + "' was created in '" + this.directory + "'");
                }
            } catch (IOException e) {
                System.out.println("INFORMATION: Logfile '" + logfilename + "' can not be created in '" + this.directory + "'");
            }
        } else {
            try {
                if (logfile.createNewFile()) {
                    System.out.println("INFORMATION: Logfile '" + logfilename + "' was created in '" + this.directory + "'");
                    this.logfile = logfile;
                }
            } catch (IOException e) {
                System.out.println("INFORMATION: Logfile '" + logfilename + "' can not be created in '" + this.directory + "'");
            }
        }
    }

    /**
     * Loads the object as current logger
     */
    public void load()
    {
        try {
            Logger.load(this);
        } catch (InvalidLoggerException e) {
            System.out.println("CRITICAL ERROR: Logger can not be loaded!");
            e.printStackTrace();
        }
    }

    /**
     * Writes a log entry to the logfile
     * @param logEntry Log entry to be written
     */
    private void writeEntryToLogfile(LogEntry logEntry)
    {
        try {
            Writer w = new BufferedWriter(new FileWriter(logfile, true));
            w.write(logEntry.getLogEntry() + "\n");
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a Logger object as current logger
     * @param logger Valid logger object
     * @throws InvalidLoggerException Thrown if Logger is invalid
     */
    public static void load(Logger logger) throws InvalidLoggerException {
        if (logger == null) throw new InvalidLoggerException("Can not load load Logger: Invalid logger instance 'null'");
        Logger.logger = logger;
    }

    /**
     * Logs a log entry into console and logfile if directory for logfiles is defined
     * @param l LogType Object
     * @param message Message to log
     */
    public static void log(LogType l, String message)
    {
        Logger.log(l, message, true);
    }

    /**
     * Logs a log entry into console and logfile if wanted and if directory for logfiles is defined
     * @param l LogType Object
     * @param message Message to log
     * @param logInFile Defines if entry also should be logged in logfile (Only if directory for logfiles is defined)
     */
    public static void log(LogType l, String message, boolean logInFile) {
        if (Logger.logger == null) {
            System.out.println("CRITICAL ERROR: No Logger was loaded before logging record. Please load Logger first.");
            return;
        }
        LogEntry logEntry = new LogEntry(new Date(), l, message);
        System.out.print(logEntry.getLogEntry() + "\n");
        if (logInFile && (Logger.logger.logfile != null)) {
            Logger.logger.writeEntryToLogfile(logEntry);
        }
    }

    /**
     * Gets the Version of the Logger
     * @return Version string
     */
    public static String getVersion() {
        return version;
    }
}
