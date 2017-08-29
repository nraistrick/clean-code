package args.exceptions;

import args.marshallers.*;
import static args.exceptions.ArgsException.ErrorCode.*;

public class MarshallerExceptionMapper
{
    public static ArgsException.ErrorCode getErrorCode(ArgumentMarshaller marshaller)
    {
        if (marshaller instanceof StringArgumentMarshaller)
        {
            return MISSING_STRING;
        }
        else if (marshaller instanceof StringArrayArgumentMarshaller)
        {
            return MISSING_STRING_ARRAY;
        }
        else if (marshaller instanceof IntegerArgumentMarshaller)
        {
            return MISSING_INTEGER;
        }
        else if (marshaller instanceof DoubleArgumentMarshaller)
        {
            return MISSING_DOUBLE;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Do not have a defined error code for this marshaller instance");
        }
    }
}
