package args.schema;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ArgumentType
{
    BOOLEAN      (""),
    STRING       ("*"),
    INTEGER      ("#"),
    DOUBLE       ("##"),
    STRING_ARRAY ("[*]");

    private final String schemaFormat;
    private static final Map<String, ArgumentType> lookup;

    ArgumentType(String schemaFormat)
    {
        if (schemaFormat == null)
            throw new IllegalArgumentException("Schema format must not be null");

        this.schemaFormat = schemaFormat;
    }

    public String getSchemaFormat()
    {
        return this.schemaFormat;
    }

    static
    {
        lookup = new HashMap<>();
        for (ArgumentType s : EnumSet.allOf(ArgumentType.class))
            lookup.put(s.getSchemaFormat(), s);
    }

    public static ArgumentType get(String schemaFormat)
    {
        ArgumentType argumentType = lookup.get(schemaFormat);
        if (argumentType == null)
        {
            throw new IllegalArgumentException(
                    String.format("Did not get a valid schema format for an argument type: %s",
                                  schemaFormat));
        }

        return argumentType;
    }
}
