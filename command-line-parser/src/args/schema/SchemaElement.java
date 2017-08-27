package args.schema;

import args.ArgsException;
import static args.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

public class SchemaElement
{
    private char elementId;
    private String elementTail;

    public SchemaElement(String element) throws ArgsException
    {
        this(element.charAt(0), element.substring(1));
    }

    private SchemaElement(char elementId, String elementTail) throws ArgsException
    {
        this.setElementId(elementId);
        this.setElementTail(elementTail);
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

    private void setElementTail(String elementTail)
    {
        this.elementTail = elementTail;
    }

    public char getElementId()
    {
        return elementId;
    }

    public String getElementTail()
    {
        return elementTail;
    }
}
