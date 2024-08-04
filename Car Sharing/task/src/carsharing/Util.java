package carsharing;

public class Util {
    private static final boolean TRACE_ENABLED = true;

    public static void trace(String text) {
        if (TRACE_ENABLED) {
            System.out.printf(">>> %s%n", text);
        }
    }
}
