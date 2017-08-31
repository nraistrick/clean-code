package args.marshallers;

import args.schema.ArgumentType;

public class MarshallerFactory
{
    public static ArgumentMarshaller GetMarshaller(ArgumentType elementType)
    {
        switch (elementType)
        {
            case BOOLEAN:
                return new BooleanArgumentMarshaller();
            case STRING:
                return new StringArgumentMarshaller();
            case INTEGER:
                return new IntegerArgumentMarshaller();
            case DOUBLE:
                return new DoubleArgumentMarshaller();
            case STRING_ARRAY:
                return new StringArrayArgumentMarshaller();
            default:
                return null;
        }
    }
}

