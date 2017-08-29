package args.marshallers;

import args.exceptions.ArgsException;

import static args.exceptions.ArgsException.ErrorCode.*;

public class IntegerArgumentMarshaller implements ArgumentMarshaller
{
    private int intValue = 0;

    public void set(String argument) throws ArgsException
    {
        try
        {
            intValue = Integer.parseInt(argument);
        }
        catch (NumberFormatException e)
        {
            throw new ArgsException(INVALID_INTEGER, argument);
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
