import args.Args;
import args.exceptions.ArgsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ArgsTest
{
    @Test
    public void validSchemaAndFlagsProcessedWithoutException() throws ArgsException
    {
        String booleanFlagId = "b";
        String integerFlagId = "i";
        String stringFlagId = "s";
        String doubleFlagId = "d";
        String stringArrayFlagId = "a";

        int integerValue = 1;
        String stringValue = "hello";
        Double doubleValue = 1.23;
        String[] stringArrayValue = new String[] {"abc", "def", "ghi"};

        String validSchema = booleanFlagId              + "," +
                             integerFlagId     + "#"    + "," +
                             stringFlagId      + "*"    + "," +
                             doubleFlagId      + "##"   + "," +
                             stringArrayFlagId + "[*]";

        String[] validArgs = {"-" + booleanFlagId,
                              "-" + integerFlagId, String.valueOf(integerValue),
                              "-" + stringFlagId, String.valueOf(stringValue),
                              "-" + doubleFlagId, String.valueOf(doubleValue),
                              "-" + stringArrayFlagId, String.join(",", stringArrayValue)};

        Args args = new Args(validSchema, validArgs);

        Assertions.assertEquals(true, args.getBoolean('b'));
    }

    @Test
    public void invalidSchemaThrowsException()
    {
        String invalidCharacter = "[";
        String invalidType = "a[";
        String validThenInvalidCharacter = "a,[";
        String nonDelimitedFlags = "ab";

        String[] schemas = new String[] { invalidCharacter,
                                          invalidType,
                                          validThenInvalidCharacter,
                                          nonDelimitedFlags };
        String[] noArgs = new String[0];

        for (String schema : schemas)
            Assertions.assertThrows(ArgsException.class,
                                    () -> new Args(schema, noArgs));
    }

    @Test
    public void validSchemaAndInvalidFlagsThrowsException()
    {
        Map<String, String[]> inputFlags = new TreeMap<>();

        // Boolean tests
        inputFlags.put("a", new String[] { "-b" });
        inputFlags.put("b", new String[] { "-ab" });

        // Integer tests
        inputFlags.put("a#", new String[] { "-a", "not_an_int" });
        inputFlags.put("b#", new String[] { "-b", "1.0" });

        // String tests
        inputFlags.put("a*", new String[] { "-a" });

        // Double tests
        inputFlags.put("a##", new String[] { "-a", "not_a_double" });

        // String-array tests
        inputFlags.put("a[*]", new String[] { "-a" });

        for(Map.Entry<String, String[]> entry : inputFlags.entrySet())
        {
            String schema = entry.getKey();
            String[] arguments = entry.getValue();

            System.out.println(String.format("Testing schema: %-4s with arguments: %s",
                                             schema,
                                             String.join(",", arguments)));

            Assertions.assertThrows(ArgsException.class,
                                    () -> new Args(schema, arguments));
        }
    }
}
