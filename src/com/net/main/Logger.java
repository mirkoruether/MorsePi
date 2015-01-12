package com.net.main;

import java.io.*;
import java.util.Calendar;
import static java.util.Calendar.getInstance;

/**
 * Klasse, die alle Aktionen registriert und im Programm ausgibt.
 */
public class Logger
{
    /**
     * Der interne Log. Wird bei Fehlerausgaben verwendet
     */
    private static String internLog = "";

    public static void logIntern(String text)
    {
        internLog += text + "\n\n";
    }

    /**
     * Dokumentiert einen Fehler und speichert ihn.
     *
     * @param t Der Fehler
     */
    public static void error(Throwable t)
    {
        try
        {
            Calendar c = getInstance();
            File f = new File("Errors/", "err"
                                         + c.get(Calendar.HOUR_OF_DAY)
                                         + c.get(Calendar.MINUTE)
                                         + c.get(Calendar.SECOND)
                                         + ".txt");
            f.getParentFile().mkdirs();
            f.createNewFile();

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), "ISO-8859-1"));

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);

            writer.print(t.toString() + "\n\n"
                         + t.getLocalizedMessage() + "\n\n"
                         + t.getMessage() + "\n\n"
                         + sw.toString() + "\n\n"
                         + internLog);
            writer.flush();
            writer.close();

            Thread.sleep(1500);

        } catch(Exception ex)
        {
            System.err.println("Error konnte nicht dokumentiert werden");
            ex.printStackTrace();
        }
    }
}
