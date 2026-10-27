package u7;

import A7.m;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: u7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2528a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Method f22935a;

    static {
        Method method;
        Method[] methods = Throwable.class.getMethods();
        m.c(methods);
        int length = methods.length;
        int i7 = 0;
        while (true) {
            method = null;
            if (i7 >= length) {
                break;
            }
            Method method2 = methods[i7];
            if (m.a(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                m.e(parameterTypes, "getParameterTypes(...)");
                if (m.a(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i7++;
        }
        f22935a = method;
        int length2 = methods.length;
        for (int i10 = 0; i10 < length2 && !m.a(methods[i10].getName(), "getSuppressed"); i10++) {
        }
    }
}
