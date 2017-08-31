package args;

import args.arguments.UserArguments;
import args.exceptions.ArgsException;
import args.marshallers.*;
import args.schema.SchemaElement;
import args.schema.ArgumentSchema;

import static args.exceptions.ArgsException.ErrorCode.*;
import java.util.*;

public class Args
{
    private Map<Character, ArgumentMarshaller> marshallers;

    public Args(String schema, String[] args) throws ArgsException
    {
        ArgumentSchema parsedSchema = new ArgumentSchema(schema);
        UserArguments userArguments = new UserArguments(args, parsedSchema);

        marshallers = setupArgumentMarshallers(parsedSchema);
        marshalArguments(userArguments, marshallers);
    }

    private Map<Character, ArgumentMarshaller> setupArgumentMarshallers(ArgumentSchema schema) throws ArgsException
    {
        Map<Character, ArgumentMarshaller> marshallers = new HashMap<>();
        for (SchemaElement e : schema.getElements())
        {
            ArgumentMarshaller marshaller = MarshallerFactory.GetMarshaller(e.getElementType());
            if(marshaller == null)
            {
                throw new ArgsException(INVALID_ARGUMENT_FORMAT,
                                        e.getElementId(),
                                        e.getElementType().toString());
            }
            marshallers.put(e.getElementId(), marshaller);
        }

        return marshallers;
    }

    private void marshalArguments(UserArguments userArguments,
                                  Map<Character, ArgumentMarshaller> marshallers) throws ArgsException
    {
        for (Object mappedArgumentValues : userArguments.getMappedArguments().entrySet())
        {
            Map.Entry argumentValuePair = (Map.Entry) mappedArgumentValues;
            Character argumentFlag = (Character) argumentValuePair.getKey();
            String argumentValue = (String) argumentValuePair.getValue();

            ArgumentMarshaller marshaller = marshallers.get(argumentFlag);
            try
            {
                marshaller.set(argumentValue);
            }
            catch (ArgsException e)
            {
                e.setErrorArgumentId(argumentFlag);
                throw e;
            }
        }
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
