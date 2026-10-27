package p;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class kt1 {
    public void a(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        v03.j(th, "cause");
        v03.j(th2, "exception");
        Method method = jt1.a;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    public b02 b() {
        return new yl0();
    }
}
