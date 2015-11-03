package dm;

import java.io.Console;

/**
 * My Bits utilities.
 */
public class Util
{

    static
    {
        System.out.println("main: s_console=" + Util.s_console);
    }

    // Bits Console features
    static final Console s_console = System.console();// Print to either the console or sysout

    public static void myprintf(final String format, final Object... args)
    {
        if (s_console != null)
        {
            s_console.printf(format, args);
        } else
        {
            System.out.printf(format, args);
        }
    }
}