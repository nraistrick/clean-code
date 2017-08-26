package args.marshallers;

public class MarshallerFactory
{
    public static ArgumentMarshaller GetMarshaller(String elementTail)
    {
        if (elementTail.length() == 0)
            return new BooleanArgumentMarshaller();
        else if (elementTail.equals("*"))
            return new StringArgumentMarshaller();
        else if (elementTail.equals("#"))
            return new IntegerArgumentMarshaller();
        else if (elementTail.equals("##"))
            return new DoubleArgumentMarshaller();
        else if (elementTail.equals("[*]"))
            return new StringArrayArgumentMarshaller();
        else
            return null;
    }
}

