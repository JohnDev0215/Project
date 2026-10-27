package p;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class k61 extends kt1 {
    @Override // p.kt1
    public final void a(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        v03.j(th, "cause");
        v03.j(th2, "exception");
        Integer num = j61.a;
        if (num == null || num.intValue() >= 19) {
            th.addSuppressed(th2);
        } else {
            super.a(th, th2);
        }
    }
}
