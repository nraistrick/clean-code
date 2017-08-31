package args.arguments;

import args.exceptions.ArgsException;
import args.exceptions.ArgumentTypeExceptionMapper;
import args.schema.ArgumentSchema;
import args.schema.SchemaElement;

import java.util.*;

import static args.exceptions.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;

public class UserArguments
{
    private Map<Character, String> argumentValues = new HashMap<>();

    public UserArguments(String[] arguments, ArgumentSchema schema) throws ArgsException
    {
        argumentValues = mapArgumentsToValues(Arrays.asList(arguments), schema);
    }

    private Map<Character, String> mapArgumentsToValues(List<String> arguments,
                                                        ArgumentSchema schema) throws ArgsException
    {
        Map<Character, String> argumentValues = new HashMap<>();

        ListIterator<String> userArguments;
        for (userArguments = arguments.listIterator(); userArguments.hasNext();)
        {
            String argumentValue;
            for (char character : getArgumentFlags(userArguments.next()))
            {
                SchemaElement element = schema.getElement(character);
                if (element.getElementType().requiresValue())
                {
                    if(!(userArguments.hasNext()))
                        throw new ArgsException(
                                ArgumentTypeExceptionMapper.getErrorCode(element.getElementType()),
                                element.getElementId(),
                                null);
                    argumentValue = userArguments.next();
                }
                else
                {
                    argumentValue = null;
                }

                argumentValues.put(character, argumentValue);
            }
        }

        return argumentValues;
    }

    private char[] getArgumentFlags(String currentArgument) throws ArgsException
    {
        if (!(currentArgument.startsWith("-")))
            throw new ArgsException(INVALID_ARGUMENT_FORMAT);

        return currentArgument.substring(1).toCharArray();
    }

    public Map<Character, String> getMappedArguments()
    {
        return argumentValues;
    }
}
