package java9.util;

import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class ArraysArrayListSpliterator {
    private static final long ARRAY_OFF;
    private static final Unsafe U;

    private ArraysArrayListSpliterator() {
    }

    static <T> Spliterator<T> spliterator(List<T> list) {
        return Spliterators.spliterator(getArray(list), 16);
    }

    private static <T> Object[] getArray(List<T> list) {
        return (Object[]) U.getObject(list, ARRAY_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            ARRAY_OFF = unsafe.objectFieldOffset(Class.forName("java.util.Arrays$ArrayList").getDeclaredField("a"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
