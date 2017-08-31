package args.schema;

import args.exceptions.ArgsException;

import java.util.*;

import static args.exceptions.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

public class ArgumentSchema
{
    public static final String delimiter = ",";

    private Map<Character, SchemaElement> elements;

    public ArgumentSchema(String schema) throws ArgsException
    {
        this.elements = parse(schema);
    }

    private static Map<Character, SchemaElement> parse(String schema) throws ArgsException
    {
        Map<Character, SchemaElement> elements = new HashMap<>();
        for (String element : schema.split(ArgumentSchema.delimiter))
            elements.put(element.charAt(0), parseElement(element));

        return elements;
    }

    private static SchemaElement parseElement(String element) throws ArgsException
    {
        String whitespaceTrimmedElement = element.trim();
        return new SchemaElement(whitespaceTrimmedElement);
    }

    public SchemaElement getElement(char argumentCharacter) throws ArgsException
    {
        SchemaElement element = elements.get(argumentCharacter);
        if (element == null)
            throw new ArgsException(INVALID_ARGUMENT_NAME, argumentCharacter, null);

        return element;
    }

    public Collection<SchemaElement> getElements()
    {
        return elements.values();
    }
}
