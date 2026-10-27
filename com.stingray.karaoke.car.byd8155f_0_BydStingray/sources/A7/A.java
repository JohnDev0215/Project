package A7;

import K7.w0;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B f443a;

    static {
        B b10 = null;
        try {
            b10 = (B) w0.class.newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (b10 == null) {
            b10 = new B();
        }
        f443a = b10;
    }

    public static H7.x a(H7.A a10) {
        B b10 = f443a;
        return b10.l(b10.b(List.class), Collections.singletonList(a10), true);
    }

    public static H7.x b(Class cls) {
        B b10 = f443a;
        return b10.l(b10.b(cls), Collections.emptyList(), true);
    }

    public static H7.x c(Class cls) {
        B b10 = f443a;
        return b10.l(b10.b(cls), Collections.emptyList(), false);
    }
}
