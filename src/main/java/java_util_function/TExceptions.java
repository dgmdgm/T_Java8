package java_util_function;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by dgm on 2014-06-10.
 */
public class TExceptions
{

    public static void main(String[] args)
    {
        try
        {
//            t01();
            t02();
        }
        catch(Exception e)
        {
            System.out.println("main: e=" + e);
        }
    }


    private static void t01()
    {

        try
        {
            // Throw an exception from inside the try block to see what happens to it when it goes through the finally
            System.out.println("Inside try 1");
            throw new RuntimeException("Exception 1"){};
        }
        finally
        {
            System.out.println("Inside finally 1");

            try
            {
                System.out.println("Inside try 2");
                // generate another exception and see if it overrides the pending one.
                throw new RuntimeException("Exception 2"){};
            }
            finally
            {
                System.out.println("Inside finally 2");
            }
        }
    }


    private static void t02()
    {
        // My closeable that will tell if a close occurs.


        try (Closeable closeable = new Closeable() { @Override public void close() {System.out.println("closeable: close");}})
        {
            throw new RuntimeException("Exception from try"){};
        }
        catch (Exception e)
        {
            System.out.println("t01: e=" + e);
        }
        finally
        {
        }

    }
}
