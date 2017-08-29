package args.marshallers;

public class StringArgumentMarshaller implements ArgumentMarshaller
{
    private String stringValue = "";

    public void set(String argument)
    {
        stringValue = argument;
    }

    public static String getValue(ArgumentMarshaller am)
    {
        if (am != null && am instanceof StringArgumentMarshaller)
            return ((StringArgumentMarshaller) am).stringValue;
        else
            return "";
    }
}