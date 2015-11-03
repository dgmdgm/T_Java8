package generics;

import org.apache.log4j.Logger;

public class TGenerics
{
    private static final Logger LOG = Logger.getLogger(TGenerics.class);

    static class ArrayE<E>
    {
//        private E[] array = new E[1];
        private E[] array = (E[]) new Object[1];
    }

    // Type parameters can be be more than one character long
    static class ArrayEE<E1, E2>
    {
//        private E[] array = new E[1];
        private E1[] array1 = (E1[]) new Object[1];
        private E2[] array2 = (E2[]) new Object[1];
    }


    public static void main(final String ... args)
    {
        try
        {
            final ArrayE<Number> numbers = new ArrayE<>();
            final ArrayE<Integer> integers = new ArrayE<>();
            final ArrayEE<Integer,Double> mixed = new ArrayEE<>();

            // Because of erasure, both objects appear to be the same type
            LOG.trace(numbers.getClass().isInstance(integers));
            LOG.trace(integers.getClass().isInstance(numbers));
            LOG.trace(integers.getClass() == numbers.getClass());


        }
        catch(Exception e)
        {
            System.out.println("main: e=" + e);
        }
    }


}
