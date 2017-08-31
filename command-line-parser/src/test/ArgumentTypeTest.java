package test;

import args.schema.ArgumentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentTypeTest
{
    private static final String invalidSchemaFormat = "SOME INVALID FORMAT";

    @Test
    public void validateArgumentTypeLookupDoesNotReturnNull()
    {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ArgumentType.get(invalidSchemaFormat));
    }
}
