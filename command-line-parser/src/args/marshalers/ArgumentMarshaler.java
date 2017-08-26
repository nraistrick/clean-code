package args.marshalers;

import args.ArgsException;

import java.util.Iterator;

public interface ArgumentMarshaler
{
    void set(Iterator<String> currentArgument) throws ArgsException;
}
