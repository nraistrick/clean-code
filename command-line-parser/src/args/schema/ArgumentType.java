package args.schema;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ArgumentType
{
    BOOLEAN      (""         ),
    STRING       ("*",   true),
    INTEGER      ("#",   true),
    DOUBLE       ("##",  true),
    STRING_ARRAY ("[*]", true);

    private final String schemaFormat;
    private final boolean requiresValue;
    private static final Map<String, ArgumentType> lookup;

    ArgumentType(String schemaFormat)
    {
        this.schemaFormat = schemaFormat;
        this.requiresValue = false;
    }

    ArgumentType(String schemaFormat, boolean requireValue)
    {
        if (schemaFormat == null)
            throw new IllegalArgumentException("Schema format must not be null");

        this.schemaFormat = schemaFormat;
        this.requiresValue = requireValue;
    }

    public String getSchemaFormat()
    {
        return this.schemaFormat;
    }

    public boolean requiresValue()
    {
        return this.requiresValue;
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
