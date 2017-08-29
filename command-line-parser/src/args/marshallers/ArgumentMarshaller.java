package args.marshallers;

import args.exceptions.ArgsException;

public interface ArgumentMarshaller
{
    void set(String argument) throws ArgsException;
}
