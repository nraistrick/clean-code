package args.marshallers;

import args.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static args.ArgsException.ErrorCode.*;

public class IntegerArgumentMarshaller implements ArgumentMarshaller
{
    private int intValue = 0;

    public void set(Iterator<String> currentArgument) throws ArgsException
    {
        String parameter = null;
        try
        {
            parameter = currentArgument.next();
            intValue = Integer.parseInt(parameter);
        }
        catch (NoSuchElementException e)
        {
            throw new ArgsException(MISSING_INTEGER);
        }
        catch (NumberFormatException e)
        {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaller am)
    {
        if (am != null && am instanceof IntegerArgumentMarshaller)
            return ((IntegerArgumentMarshaller) am).intValue;
        else
            return 0;
    }
}
