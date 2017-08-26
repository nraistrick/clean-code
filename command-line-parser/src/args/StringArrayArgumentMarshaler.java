package args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static args.ArgsException.ErrorCode.MISSING_STRING_ARRAY;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler
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

    public static String[] getValue(ArgumentMarshaler am)
    {
        if (am != null && am instanceof StringArrayArgumentMarshaler)
            return ((StringArrayArgumentMarshaler) am).stringArray;
        else
            return new String[0];
    }
}
