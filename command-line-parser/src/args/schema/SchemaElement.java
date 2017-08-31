package args.schema;

import args.exceptions.ArgsException;

import static args.exceptions.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static args.exceptions.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

public class SchemaElement
{
    private char elementId;
    private ArgumentType elementType;

    public SchemaElement(String element) throws ArgsException
    {
        this(element.charAt(0), element.substring(1));
    }

    private SchemaElement(char elementId, String elementTail) throws ArgsException
    {
        this.setElementId(elementId);
        this.setElementType(elementTail);
    }

    private void setElementId(char elementId) throws ArgsException
    {
        validateElementId(elementId);
        this.elementId = elementId;
    }

    private static void validateElementId(char elementId) throws ArgsException
    {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }

    private void setElementType(String elementTail) throws ArgsException
    {
        try
        {
            this.elementType = ArgumentType.get(elementTail);
        }
        catch (IllegalArgumentException e)
        {
            throw new ArgsException(INVALID_ARGUMENT_FORMAT);
        }
    }

    public char getElementId()
    {
        return elementId;
    }

    public ArgumentType getElementType()
    {
        return elementType;
    }
}
