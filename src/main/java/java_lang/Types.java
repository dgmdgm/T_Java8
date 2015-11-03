package java_lang;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Types
{
    private static final Logger LOG = Logger.getLogger(Types.class);

    public static void main(final String ... args)
    {
        t_Arrays();
    }

    public static void t_Arrays()
    {
        final Object[] objectArray = new Object[1];
        final String[] stringArray = new String[1];
        final Integer[] integerArray = new Integer[1];
        final int[] intArray = new int[1];
        final long[] longArray = new long[1];

        LOG.info("objectArray.getClass()=" + objectArray.getClass());
        LOG.info("stringArray.getClass()=" + stringArray.getClass());

        LOG.info("subclass < superclass :");
        LOG.info("  String[] < Object[] = " + objectArray.getClass().isInstance(stringArray) );
        LOG.info("  Integer[] < Object[] = " + objectArray.getClass().isInstance(integerArray) );
        LOG.info("  Integer[] < String[] = " + stringArray.getClass().isInstance(integerArray) );
        LOG.info("  int[] < Object[] = " + objectArray.getClass().isInstance(intArray) );
        LOG.info("  int[] < long[] = " + longArray.getClass().isInstance(intArray) );

    }



}
