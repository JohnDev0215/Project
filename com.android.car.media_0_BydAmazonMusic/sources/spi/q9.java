package spi;

import java.lang.reflect.AccessibleObject;

/* JADX INFO: compiled from: ReflectionAccessor.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class q9 {
    public static final q9 a;

    static {
        a = d5.a < 9 ? new f9() : new bc();
    }

    public abstract void a(AccessibleObject accessibleObject);
}
