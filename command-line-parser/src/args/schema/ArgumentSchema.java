package args.schema;

import args.exceptions.ArgsException;

import java.util.ArrayList;
import java.util.List;

public class ArgumentSchema
{
    public static final String delimiter = ",";

    private List<SchemaElement> elements;

    public ArgumentSchema(String schema) throws ArgsException
    {
        this.elements = parse(schema);
    }

    private static List<SchemaElement> parse(String schema) throws ArgsException
    {
        List<SchemaElement> elements = new ArrayList<>();
        for (String element : schema.split(ArgumentSchema.delimiter))
            elements.add(parseElement(element));

        return elements;
    }

    private static SchemaElement parseElement(String element) throws ArgsException
    {
        String whitespaceTrimmedElement = element.trim();
        return new SchemaElement(whitespaceTrimmedElement);
    }

    public List<SchemaElement> getElements()
    {
        return elements;
    }
}
