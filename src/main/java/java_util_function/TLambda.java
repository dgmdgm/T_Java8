package java_util_function;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.Supplier;

public class TLambda
{
    private static final Logger LOG = Logger.getLogger(TLambda.class);

    public static void main(final String ... args)
    {
        try
        {
            final TLambda self = new TLambda();
//            t_sort();
//            t_new();
            self.t_function_type();
        }
        catch(Exception e)
        {
            System.out.println("main: e=" + e);
        }
    }

    static private class DummyStaticInnerClass {}
    private class DummyInnerClass {}

    private void t_function_type()
    {
        LOG.trace("t_function_type: begin");

//        LOG.trace(new DummyStaticInnerClass());
//        LOG.trace(new DummyInnerClass());


        final Runnable runnable = () -> System.out.println("Hello World");
        LOG.trace("1:" + runnable);
        final Class<?>[] interfaces = runnable.getClass().getInterfaces();
        LOG.trace("2:" + Arrays.toString(interfaces));

        LOG.trace("3:" +  (Runnable)(() -> System.out.println("Hellp World")) );
//        LOG.trace( (() -> System.out.println("Hellp World")).toString() );

        final Thread thread = new Thread(runnable );
        thread.start();

        LOG.trace("t_function_type: end");
    }


    private static void t_sort()
    {

        String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        System.out.println("----------------");
        Arrays.asList(stringArray).stream().forEach(System.out::println);
    }


    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
         DEST transferElements(
             SOURCE sourceCollection,
             Supplier <DEST> collectionFactory) {

             DEST result = collectionFactory.get();
             for (T t : sourceCollection) {
                 result.add(t);
             }
             return result;
    }

    private static void t_new()
    {
        List<String> roster = Arrays.asList("Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda");

        System.out.println("----------------");

        // 3 equivalent invocations.
        Set<String> rosterSetLambda1 = transferElements(roster, () -> { return new HashSet<>(); });
        Set<String> rosterSetLambda2 = transferElements(roster, HashSet<String>::new);
        Set<String> rosterSetLambda3 = transferElements(roster, HashSet::new); //*** peculiar interpretation of construction as a Supplier.

        System.out.println("----------------");
        rosterSetLambda1.stream().forEach(System.out::println);
        System.out.println("----------------");
        rosterSetLambda2.stream().forEach(System.out::println);
    }

}
