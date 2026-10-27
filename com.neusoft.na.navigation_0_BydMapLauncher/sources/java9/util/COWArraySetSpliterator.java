package java9.util;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class COWArraySetSpliterator {
    private static final long COW_ARRAY_OFF;
    private static final Unsafe U;

    private COWArraySetSpliterator() {
    }

    static <T> Spliterator<T> spliterator(CopyOnWriteArraySet<T> copyOnWriteArraySet) {
        return Spliterators.spliterator(COWArrayListSpliterator.getArray(getCowArrayList(copyOnWriteArraySet)), 1025);
    }

    private static <T> CopyOnWriteArrayList<T> getCowArrayList(CopyOnWriteArraySet<T> copyOnWriteArraySet) {
        return (CopyOnWriteArrayList) U.getObject(copyOnWriteArraySet, COW_ARRAY_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            COW_ARRAY_OFF = unsafe.objectFieldOffset(CopyOnWriteArraySet.class.getDeclaredField("al"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
