package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public class k extends j {
    public static final <T> Collection<T> c(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$asCollection");
        return new b(tArr, false);
    }

    public static final <T extends Comparable<? super T>> int d(List<? extends T> list, T t, int i, int i2) {
        kotlin.jvm.internal.i.c(list, "$this$binarySearch");
        m(list.size(), i, i2);
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int iA = kotlin.k.b.a(list.get(i4), t);
            if (iA < 0) {
                i = i4 + 1;
            } else {
                if (iA <= 0) {
                    return i4;
                }
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    public static /* synthetic */ int e(List list, Comparable comparable, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = list.size();
        }
        return d(list, comparable, i, i2);
    }

    public static <T> List<T> f() {
        return EmptyList.INSTANCE;
    }

    public static kotlin.o.c g(Collection<?> collection) {
        kotlin.jvm.internal.i.c(collection, "$this$indices");
        return new kotlin.o.c(0, collection.size() - 1);
    }

    public static <T> int h(List<? extends T> list) {
        kotlin.jvm.internal.i.c(list, "$this$lastIndex");
        return list.size() - 1;
    }

    public static <T> List<T> i(T... tArr) {
        kotlin.jvm.internal.i.c(tArr, "elements");
        return tArr.length > 0 ? f.b(tArr) : f();
    }

    public static <T> List<T> j(T... tArr) {
        kotlin.jvm.internal.i.c(tArr, "elements");
        return g.l(tArr);
    }

    public static <T> List<T> k(T... tArr) {
        kotlin.jvm.internal.i.c(tArr, "elements");
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new b(tArr, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> l(List<? extends T> list) {
        kotlin.jvm.internal.i.c(list, "$this$optimizeReadOnlyList");
        int size = list.size();
        return size != 0 ? size != 1 ? list : j.b(list.get(0)) : f();
    }

    private static final void m(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex (" + i2 + ") is greater than toIndex (" + i3 + ").");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i2 + ") is less than zero.");
        }
        if (i3 <= i) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i3 + ") is greater than size (" + i + ").");
    }

    public static void n() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void o() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
