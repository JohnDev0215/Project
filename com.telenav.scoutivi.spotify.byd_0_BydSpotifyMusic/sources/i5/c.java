package i5;

import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ListBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final <E> E[] d(int i7) {
        if (i7 >= 0) {
            return (E[]) new Object[i7];
        }
        throw new IllegalArgumentException("capacity must be non-negative.".toString());
    }

    public static final <T> T[] e(T[] tArr, int i7) {
        l.e(tArr, "<this>");
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i7);
        l.d(tArr2, "copyOf(this, newSize)");
        return tArr2;
    }

    public static final <E> void f(E[] eArr, int i7) {
        l.e(eArr, "<this>");
        eArr[i7] = null;
    }

    public static final <E> void g(E[] eArr, int i7, int i8) {
        l.e(eArr, "<this>");
        while (i7 < i8) {
            f(eArr, i7);
            i7++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> boolean h(T[] tArr, int i7, int i8, List<?> list) {
        if (i8 != list.size()) {
            return false;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            if (!l.a(tArr[i7 + i9], list.get(i9))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> int i(T[] tArr, int i7, int i8) {
        int iHashCode = 1;
        for (int i9 = 0; i9 < i8; i9++) {
            T t6 = tArr[i7 + i9];
            iHashCode = (iHashCode * 31) + (t6 != null ? t6.hashCode() : 0);
        }
        return iHashCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> String j(T[] tArr, int i7, int i8) {
        StringBuilder sb = new StringBuilder((i8 * 3) + 2);
        sb.append("[");
        for (int i9 = 0; i9 < i8; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            sb.append(tArr[i7 + i9]);
        }
        sb.append("]");
        String string = sb.toString();
        l.d(string, "sb.toString()");
        return string;
    }
}
