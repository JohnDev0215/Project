package p;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class jt1 {
    public static final Method a;

    static {
        Method method;
        Method[] methods = Throwable.class.getMethods();
        v03.i(methods, "throwableMethods");
        int length = methods.length;
        int i = 0;
        while (true) {
            method = null;
            if (i >= length) {
                break;
            }
            Method method2 = methods[i];
            if (v03.b(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                v03.i(parameterTypes, "it.parameterTypes");
                if (v03.b(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i++;
        }
        a = method;
        int length2 = methods.length;
        for (int i2 = 0; i2 < length2 && !v03.b(methods[i2].getName(), "getSuppressed"); i2++) {
        }
    }
}
