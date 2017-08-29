package args.marshallers;

public class BooleanArgumentMarshaller implements ArgumentMarshaller
{
    private boolean booleanValue = false;

    public void set(String argument)
    {
        booleanValue = true;
    }

    public static boolean getValue(ArgumentMarshaller am)
    {
        if (am != null && am instanceof BooleanArgumentMarshaller)
            return ((BooleanArgumentMarshaller) am).booleanValue;
        else
            return false;
    }
}
