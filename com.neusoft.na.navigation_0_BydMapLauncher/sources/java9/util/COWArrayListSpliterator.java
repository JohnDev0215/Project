package java9.util;

import java.util.concurrent.CopyOnWriteArrayList;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class COWArrayListSpliterator {
    private static final long ARRAY_OFF;
    private static final Unsafe U = UnsafeAccess.unsafe;

    private COWArrayListSpliterator() {
    }

    static <T> Spliterator<T> spliterator(CopyOnWriteArrayList<T> copyOnWriteArrayList) {
        return Spliterators.spliterator(getArray(copyOnWriteArrayList), 1040);
    }

    static <T> Object[] getArray(CopyOnWriteArrayList<T> copyOnWriteArrayList) {
        return (Object[]) U.getObject(copyOnWriteArrayList, ARRAY_OFF);
    }

    static {
        ARRAY_OFF = fieldOffset(Spliterators.IS_ANDROID ? "elements" : "array", true);
    }

    static long fieldOffset(String str, boolean z) {
        try {
            return U.objectFieldOffset(CopyOnWriteArrayList.class.getDeclaredField(str));
        } catch (Exception e) {
            if (z && (e instanceof NoSuchFieldException) && Spliterators.IS_ANDROID && !Spliterators.IS_HARMONY_ANDROID) {
                return fieldOffset("array", false);
            }
            throw new Error(e);
        }
    }
}
