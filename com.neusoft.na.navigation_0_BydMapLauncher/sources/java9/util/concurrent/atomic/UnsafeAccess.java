package java9.util.concurrent.atomic;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
class UnsafeAccess {
    static final Unsafe unsafe;

    static {
        Field declaredField;
        try {
            try {
                declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            } catch (NoSuchFieldException unused) {
                declaredField = Unsafe.class.getDeclaredField("THE_ONE");
            }
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private UnsafeAccess() {
    }
}
