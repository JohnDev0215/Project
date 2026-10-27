package d.b.a;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static <T extends Throwable> T a(T t) {
        String name = a.class.getName();
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (name.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }

    public static void b(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
        a(illegalArgumentException);
        throw illegalArgumentException;
    }
}
