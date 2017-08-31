package args.exceptions;

import static args.exceptions.ArgsException.ErrorCode.*;

import args.schema.ArgumentType;

public class ArgumentTypeExceptionMapper
{
    public static ArgsException.ErrorCode getErrorCode(ArgumentType argumentType)
    {
        switch (argumentType)
        {
            case STRING:
                return MISSING_STRING;
            case INTEGER:
                return MISSING_INTEGER;
            case DOUBLE:
                return MISSING_DOUBLE;
            case STRING_ARRAY:
                return MISSING_STRING_ARRAY;
            default:
                throw new IllegalArgumentException(
                        String.format("Received invalid argument type %s", argumentType));
        }
    }
}
