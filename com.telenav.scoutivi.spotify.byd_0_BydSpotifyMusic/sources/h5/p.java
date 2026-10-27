package h5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    public static final <T> Collection<T> e(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return new f(tArr, false);
    }

    public static final <T extends Comparable<? super T>> int f(List<? extends T> list, T t6, int i7, int i8) {
        kotlin.jvm.internal.l.e(list, "<this>");
        n(list.size(), i7, i8);
        int i9 = i8 - 1;
        while (i7 <= i9) {
            int i10 = (i7 + i9) >>> 1;
            int iA = j5.b.a(list.get(i10), t6);
            if (iA < 0) {
                i7 = i10 + 1;
            } else {
                if (iA <= 0) {
                    return i10;
                }
                i9 = i10 - 1;
            }
        }
        return -(i7 + 1);
    }

    public static /* synthetic */ int g(List list, Comparable comparable, int i7, int i8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i7 = 0;
        }
        if ((i9 & 4) != 0) {
            i8 = list.size();
        }
        return f(list, comparable, i7, i8);
    }

    public static <T> List<T> h() {
        return z.f6657e;
    }

    public static <T> int i(List<? extends T> list) {
        kotlin.jvm.internal.l.e(list, "<this>");
        return list.size() - 1;
    }

    public static <T> List<T> j(T... elements) {
        kotlin.jvm.internal.l.e(elements, "elements");
        return elements.length > 0 ? k.b(elements) : h();
    }

    public static <T> List<T> k(T... elements) {
        kotlin.jvm.internal.l.e(elements, "elements");
        return l.o(elements);
    }

    public static <T> List<T> l(T... elements) {
        kotlin.jvm.internal.l.e(elements, "elements");
        return elements.length == 0 ? new ArrayList() : new ArrayList(new f(elements, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> m(List<? extends T> list) {
        kotlin.jvm.internal.l.e(list, "<this>");
        int size = list.size();
        return size != 0 ? size != 1 ? list : o.d(list.get(0)) : h();
    }

    private static final void n(int i7, int i8, int i9) {
        if (i8 > i9) {
            throw new IllegalArgumentException("fromIndex (" + i8 + ") is greater than toIndex (" + i9 + ").");
        }
        if (i8 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i8 + ") is less than zero.");
        }
        if (i9 <= i7) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i9 + ") is greater than size (" + i7 + ").");
    }

    public static void o() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
