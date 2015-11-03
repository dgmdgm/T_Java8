package java_util_function;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by dgm on 2014-05-30.
 */
public class TPredicate
{

    interface TwoArgs
//    abstract class TwoArgs
    {
        abstract void m(String a, String b);
    }

    interface TwoArgsX
    {
//        void ms1(String a);
        void ms2(String a, String b);
//        void mi1(Integer a);
//        void mi2(Integer a, Integer b);
    }

    public static void main(String[] args)
    {
//        t01();
        t02();
    }


    private static void t01()
    {
        // Old style to instantiate an anonymous subclass
        Predicate<String> p1 = new Predicate<String>()
        {
            @Override
            public boolean test(String s)
            {
                return s.matches(".*good.*");
            }
        };

        // New style to instantiate an anonymous subclass by providing only the method as a lambda
        Predicate<String> p2 = s -> s.matches(".*good.*");

        String[] strings = {"aa","good","good1","good2","bb"};
        for (final String s : strings  )
        {
            //System.out.println(String.format("p1(%s): %b", s, p1.Bits(s)));
            System.out.println(String.format("p1(%s): %b == %b", s, p1.test(s),  p2.test(s)));
        }
    }


    private static void t02()
    {
//        TwoArgsX ts1 = (String a) -> System.out.println(String.format("a=%s", a));
        TwoArgsX ts2a = (String a, String b) -> System.out.println(String.format("a=%s, b=%s", a, b));
        TwoArgsX ts2b = (a, b) -> System.out.println(String.format("a=%s, b=%s", a, b));
//        TwoArgsX ti2 = (Integer a, Integer b) -> System.out.println(String.format("a=%d, b=%d", a, b));

        final Function<Integer, Integer> f = a -> a * a;
        System.out.println( f.apply(2) );
    }


}
