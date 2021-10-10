package eu.cr4zyfl1x.logger;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("This is not an executable software!\nYou're trying to execute a Java Library.\n\nLogger version -> java -jar logger.jar version\n\nMore information:\nhttps://github.com/Cr4zyFl1x/JavaLogger");
            JOptionPane.showMessageDialog(null, "This is not an executable software!\nYou're trying to execute a Java Library.\n\nMore information:\nhttps://github.com/Cr4zyFl1x/JavaLogger", "JavaLogger", JOptionPane.INFORMATION_MESSAGE);
        } else if (args[0].equals("version")) System.out.println(Logger.getVersion());
    }
}
