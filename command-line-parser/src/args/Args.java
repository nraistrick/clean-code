package args;

import args.marshallers.*;
import args.schema.SchemaElement;
import args.schema.ArgumentSchema;

import static args.ArgsException.ErrorCode.*;
import java.util.*;

public class Args
{
    private Map<Character, ArgumentMarshaller> marshallers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;

    public Args(String schema, String[] args) throws ArgsException
    {
        marshallers = new HashMap<>();
        argsFound = new HashSet<>();
        setupArgumentMarshallers(new ArgumentSchema(schema));
        parseArgumentStrings(Arrays.asList(args));
    }

    private void setupArgumentMarshallers(ArgumentSchema schema) throws ArgsException
    {
        for (SchemaElement e : schema.getElements())
        {
            ArgumentMarshaller marshaller = MarshallerFactory.GetMarshaller(e.getElementTail());
            if(marshaller == null)
            {
                throw new ArgsException(INVALID_ARGUMENT_FORMAT, e.getElementId(), e.getElementTail());
            }
            marshallers.put(e.getElementId(), marshaller);
        }
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException
    {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); )
        {
            String argString = currentArgument.next();
            if (argString.startsWith("-"))
            {
                parseArgumentCharacters(argString.substring(1));
            }
            else
            {
                currentArgument.previous();
                break;
            }
        }
    }

    private void parseArgumentCharacters(String argChars) throws ArgsException
    {
        for (int i = 0; i < argChars.length(); i++)
            parseArgumentCharacter(argChars.charAt(i));
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException
    {
        ArgumentMarshaller m = marshallers.get(argChar);
        if (m == null)
        {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
        }

        argsFound.add(argChar);
        
        try
        {
            m.set(currentArgument);
        }
        catch (ArgsException e)
        {
            e.setErrorArgumentId(argChar);
            throw e;
        }
    }

    public boolean has(char arg)
    {
        return argsFound.contains(arg);
    }

    public int nextArgument()
    {
        return currentArgument.nextIndex();
    }

    public boolean getBoolean(char arg)
    {
        return BooleanArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public String getString(char arg)
    {
        return StringArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public int getInt(char arg)
    {
        return IntegerArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public double getDouble(char arg)
    {
        return DoubleArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public String[] getStringArray(char arg)
    {
        return StringArrayArgumentMarshaller.getValue(marshallers.get(arg));
    }
}
