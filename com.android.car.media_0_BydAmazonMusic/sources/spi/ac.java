package spi;

import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ac {
    public static void a(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            StringBuilder sbK = c.k("Interface can't be instantiated! Interface name: ");
            sbK.append(cls.getName());
            throw new UnsupportedOperationException(sbK.toString());
        }
        if (Modifier.isAbstract(modifiers)) {
            StringBuilder sbK2 = c.k("Abstract class can't be instantiated! Class name: ");
            sbK2.append(cls.getName());
            throw new UnsupportedOperationException(sbK2.toString());
        }
    }

    public abstract <T> T b(Class<T> cls);
}
