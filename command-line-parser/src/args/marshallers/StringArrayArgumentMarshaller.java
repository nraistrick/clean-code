package args.marshallers;

import args.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static args.ArgsException.ErrorCode.MISSING_STRING_ARRAY;

public class StringArrayArgumentMarshaller implements ArgumentMarshaller
{
    private String[] stringArray = new String[0];

    public void set(Iterator<String> currentArgument) throws ArgsException
    {
        try
        {
            String commaSeparatedList = currentArgument.next();
            stringArray = commaSeparatedList.split(",");
        }
        catch (NoSuchElementException e)
        {
            throw new ArgsException(MISSING_STRING_ARRAY);
        }
    }

    public static String[] getValue(ArgumentMarshaller am)
    {
        if (am != null && am instanceof StringArrayArgumentMarshaller)
            return ((StringArrayArgumentMarshaller) am).stringArray;
        else
            return new String[0];
    }
}
