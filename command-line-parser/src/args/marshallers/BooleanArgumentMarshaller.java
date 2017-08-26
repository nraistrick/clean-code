package args.marshallers;

import args.ArgsException;

import java.util.Iterator;

public class BooleanArgumentMarshaller implements ArgumentMarshaller
{
    private boolean booleanValue = false;

    public void set(Iterator<String> currentArgument) throws ArgsException
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
