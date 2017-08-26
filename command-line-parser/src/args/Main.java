package args;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Args arg = new Args("b,i#,s*,d##,a[*]", args);
            boolean b = arg.getBoolean('b');
            int i = arg.getInt('i');
            String s = arg.getString('s');
            double d = arg.getDouble('d');
            String[] a = arg.getStringArray('a');

            return;
        }
        catch (ArgsException e)
        {
            System.out.printf("Argument error: %s\n", e.errorMessage());
        }
    }
}
