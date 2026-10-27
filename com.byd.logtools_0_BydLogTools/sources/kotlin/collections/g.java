package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public class g extends f {
    public static final <T> boolean k(T[] tArr, T t) {
        kotlin.jvm.internal.i.c(tArr, "$this$contains");
        return p(tArr, t) >= 0;
    }

    public static final <T> List<T> l(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$filterNotNull");
        return (List) m(tArr, new ArrayList());
    }

    public static final <C extends Collection<? super T>, T> C m(T[] tArr, C c2) {
        kotlin.jvm.internal.i.c(tArr, "$this$filterNotNullTo");
        kotlin.jvm.internal.i.c(c2, "destination");
        for (T t : tArr) {
            if (t != null) {
                c2.add(t);
            }
        }
        return c2;
    }

    public static <T> kotlin.o.c n(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$indices");
        return new kotlin.o.c(0, o(tArr));
    }

    public static <T> int o(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$lastIndex");
        return tArr.length - 1;
    }

    public static final <T> int p(T[] tArr, T t) {
        kotlin.jvm.internal.i.c(tArr, "$this$indexOf");
        int i = 0;
        if (t == null) {
            int length = tArr.length;
            while (i < length) {
                if (tArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i < length2) {
            if (kotlin.jvm.internal.i.a(t, tArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static char q(char[] cArr) {
        kotlin.jvm.internal.i.c(cArr, "$this$single");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static <T> T r(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$singleOrNull");
        if (tArr.length == 1) {
            return tArr[0];
        }
        return null;
    }

    public static <T> List<T> s(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$toList");
        int length = tArr.length;
        return length != 0 ? length != 1 ? t(tArr) : j.b(tArr[0]) : k.f();
    }

    public static <T> List<T> t(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$toMutableList");
        return new ArrayList(k.c(tArr));
    }
}
