package args.marshallers;

import args.ArgsException;

import java.util.Iterator;

public interface ArgumentMarshaller
{
    void set(Iterator<String> currentArgument) throws ArgsException;
}
