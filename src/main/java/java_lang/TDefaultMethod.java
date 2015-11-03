package java_lang;

public class TDefaultMethod
{

    interface IPerson
    {
        String getFirstName();
        String getLastName();


        // Java 8 interface default method
        default String getFullName() {return getFirstName() + " " + getLastName();}

        // Java 8 interface static method
        static String getFullName(Person person) {return person.getFirstName() + " " + person.getLastName();}
    }

    static class Person implements IPerson
    {
        @Override
        public String getFirstName()
        {
            return "Joe";
        }

        @Override
        public String getLastName()
        {
            return "Bloe";
        }
    }

    public static void main(String[] args)
    {
        System.out.println((new Person()).getFullName());
        System.out.println(IPerson.getFullName(new Person()));
    }

}
