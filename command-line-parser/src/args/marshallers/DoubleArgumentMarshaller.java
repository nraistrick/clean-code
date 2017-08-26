package args.marshallers;

import args.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static args.ArgsException.ErrorCode.INVALID_DOUBLE;
import static args.ArgsException.ErrorCode.MISSING_DOUBLE;

public class DoubleArgumentMarshaller implements ArgumentMarshaller
{
    private double doubleValue = 0;

    public void set(Iterator<String> currentArgument) throws ArgsException
    {
        String parameter = null;
        try
        {
            parameter = currentArgument.next();
            doubleValue = Double.parseDouble(parameter);
        }
        catch (NoSuchElementException e)
        {
            throw new ArgsException(MISSING_DOUBLE);
        }
        catch (NumberFormatException e)
        {
            throw new ArgsException(INVALID_DOUBLE, parameter);
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
