package args.marshallers;

import args.exceptions.ArgsException;

import static args.exceptions.ArgsException.ErrorCode.INVALID_DOUBLE;

public class DoubleArgumentMarshaller implements ArgumentMarshaller
{
    private double doubleValue = 0;

    public void set(String argument) throws ArgsException
    {
        try
        {
            doubleValue = Double.parseDouble(argument);
        }
        catch (NumberFormatException e)
        {
            throw new ArgsException(INVALID_DOUBLE, argument);
        }
    }

    public static double getValue(ArgumentMarshaller am)
    {
        if (am != null && am instanceof DoubleArgumentMarshaller)
            return ((DoubleArgumentMarshaller) am).doubleValue;
        else
            return 0;
    }
}
