package args.marshallers;

public class StringArrayArgumentMarshaller implements ArgumentMarshaller
{
    private String[] stringArray = new String[0];

    public void set(String argument)
    {
        stringArray = argument.split(",");
    }

    public static String[] getValue(ArgumentMarshaller am)
    {
        if (am != null && am instanceof StringArrayArgumentMarshaller)
            return ((StringArrayArgumentMarshaller) am).stringArray;
        else
            return new String[0];
    }
}
