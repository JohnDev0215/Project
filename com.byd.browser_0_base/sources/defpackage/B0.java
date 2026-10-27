package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public class B0 {
    public void a(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        C0186g0.e(th, "cause");
        C0186g0.e(th2, "exception");
        Method method = A0.a;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [G0, Y] */
    public G0 b() {
        return new Y();
    }
}
