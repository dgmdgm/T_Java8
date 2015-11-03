package java_util_function;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class TCollections
{
    private static final Logger LOG = Logger.getLogger(TCollections.class);

    public static void main(String[] args)
    {
        try
        {
            if (false) t_forEach();
            if (false) t_filter();
            if (false) t_average();
            if (false) t_reduce();
            if (true) t_parallel();
        }
        catch(Exception e)
        {
            System.out.println("main: e=" + e);
        }
    }


    private static void t_forEach()
    {

        List<String> list = Arrays.asList("Pierre", "Jean", "Jacques");

        System.out.println("----------------");

//        // Iterations on collection or collection as streams
//        list.forEach(e -> System.out.println(e)); //*** lambda function
//        list.stream().forEach(e -> System.out.println(e)); //*** lambda function

        // Various syntaxes to ultimately invoke the same method
        list.stream().forEach(new Consumer<String>() { @Override public void accept(String s) {System.out.println(s); } }); //*** old fashion anonymous inner class function
        list.stream().forEach(e -> System.out.println(e)); //*** inline lambda function
        list.stream().forEach(System.out::println);  //*** reference to an instance method as a function

        //list.stream().forEach(LOG::trace);  //*** reference to an instance method as a function

        System.out.println("----------------");
        // Collection as as stream passing through a predicate filter
        list.stream().filter(e -> e.contains("J")).forEach(LOG::debug);

    }


    private static void t_filter()
    {

        List<Integer> list = Arrays.asList(1,2,3,5,7,11,13);

        /// Variations on filter

        System.out.println("----------------");
        list.stream().filter(e -> e % 2 != 0).forEach(System.out::println);

        System.out.println("----------------");
        list.stream().filter(e -> e % 2 == 0).forEach(System.out::println);
    }



    static class Averager implements IntConsumer
    {
        private int total = 0;
        private int count = 0;

        public int  count() { return count ; }
        public int total() { return total; }
        public double average() { return count > 0 ? ((double) total)/count : 0; }

        public void accept(int i) { total += i; count++; }

        public void combine(Averager other)
        {
            total += other.total;
            count += other.count;
        }
    }

    private static void t_average()
    {

        List<Integer> list = Arrays.asList(1,2,3,5,7,11,13);

        System.out.println("----------------");
        list.stream().forEach(System.out::println);

        System.out.println("----------------");
        System.out.println(list.stream().mapToInt(Integer::intValue).average());

        System.out.println("----------------");
        final Averager averageCollect = list.stream().mapToInt(Integer::intValue).collect(Averager::new, Averager::accept, Averager::combine);
        System.out.println("count=" + averageCollect.count());
        System.out.println("total=" + averageCollect.total());
        System.out.println("average=" + averageCollect.average());
    }

    private static void t_reduce()
    {

        List<Integer> list = Arrays.asList(1,2,3,5,7,11,13);

        System.out.println("----------------");
        list.stream().forEach(System.out::println);

        System.out.println("----------------");
        System.out.println(list.stream().mapToInt(Integer::intValue).average());

        System.out.println("----------------");
        System.out.println( list.stream().reduce(Integer::sum).get() );

    }


    // from https://www.readability.com/articles/3mn79l0q
    private static void t_parallel()
    {
//        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
//        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        final List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8 );

        System.out.println("listOfIntegers:");
        listOfIntegers
            .stream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("listOfIntegers sorted in reverse order:");
        final Comparator<Integer> normal = Integer::compare;
        final Comparator<Integer> reversed = normal.reversed();
        Collections.sort(listOfIntegers, reversed);
        listOfIntegers
            .stream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Parallel stream");
        listOfIntegers
            .parallelStream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Another parallel stream:");
        listOfIntegers
            .parallelStream()
            .forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("With forEachOrdered:");
        listOfIntegers
            .parallelStream()
            .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");    }

}
