package kotlin.collections;

import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public class f extends e {
    public static final <T> boolean b(T[] tArr, T t) {
        kotlin.jvm.internal.h.d(tArr, "$this$contains");
        return e(tArr, t) >= 0;
    }

    public static double c(double[] dArr) {
        kotlin.jvm.internal.h.d(dArr, "$this$first");
        if (dArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return dArr[0];
    }

    public static final int d(double[] dArr) {
        kotlin.jvm.internal.h.d(dArr, "$this$lastIndex");
        return dArr.length - 1;
    }

    public static final <T> int e(T[] tArr, T t) {
        kotlin.jvm.internal.h.d(tArr, "$this$indexOf");
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
            if (kotlin.jvm.internal.h.a(t, tArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static double f(double[] dArr) {
        kotlin.jvm.internal.h.d(dArr, "$this$last");
        if (dArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return dArr[d(dArr)];
    }

    public static char g(char[] cArr) {
        kotlin.jvm.internal.h.d(cArr, "$this$single");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }
}
