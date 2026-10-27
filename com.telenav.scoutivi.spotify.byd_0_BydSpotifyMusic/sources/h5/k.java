package h5;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _ArraysJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public class k extends j {
    public static <T> List<T> b(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        List<T> listA = m.a(tArr);
        kotlin.jvm.internal.l.d(listA, "asList(this)");
        return listA;
    }

    public static byte[] c(byte[] bArr, byte[] destination, int i7, int i8, int i9) {
        kotlin.jvm.internal.l.e(bArr, "<this>");
        kotlin.jvm.internal.l.e(destination, "destination");
        System.arraycopy(bArr, i8, destination, i7, i9 - i8);
        return destination;
    }

    public static <T> T[] d(T[] tArr, T[] destination, int i7, int i8, int i9) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        kotlin.jvm.internal.l.e(destination, "destination");
        System.arraycopy(tArr, i8, destination, i7, i9 - i8);
        return destination;
    }

    public static /* synthetic */ byte[] e(byte[] bArr, byte[] bArr2, int i7, int i8, int i9, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            i7 = 0;
        }
        if ((i10 & 4) != 0) {
            i8 = 0;
        }
        if ((i10 & 8) != 0) {
            i9 = bArr.length;
        }
        return c(bArr, bArr2, i7, i8, i9);
    }

    public static /* synthetic */ Object[] f(Object[] objArr, Object[] objArr2, int i7, int i8, int i9, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            i7 = 0;
        }
        if ((i10 & 4) != 0) {
            i8 = 0;
        }
        if ((i10 & 8) != 0) {
            i9 = objArr.length;
        }
        return d(objArr, objArr2, i7, i8, i9);
    }

    public static byte[] g(byte[] bArr, int i7, int i8) {
        kotlin.jvm.internal.l.e(bArr, "<this>");
        i.a(i8, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i7, i8);
        kotlin.jvm.internal.l.d(bArrCopyOfRange, "copyOfRange(this, fromIndex, toIndex)");
        return bArrCopyOfRange;
    }

    public static <T> T[] h(T[] tArr, int i7, int i8) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        i.a(i8, tArr.length);
        T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i7, i8);
        kotlin.jvm.internal.l.d(tArr2, "copyOfRange(this, fromIndex, toIndex)");
        return tArr2;
    }

    public static void i(int[] iArr, int i7, int i8, int i9) {
        kotlin.jvm.internal.l.e(iArr, "<this>");
        Arrays.fill(iArr, i8, i9, i7);
    }

    public static <T> void j(T[] tArr, T t6, int i7, int i8) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        Arrays.fill(tArr, i7, i8, t6);
    }

    public static /* synthetic */ void k(Object[] objArr, Object obj, int i7, int i8, int i9, Object obj2) {
        if ((i9 & 2) != 0) {
            i7 = 0;
        }
        if ((i9 & 4) != 0) {
            i8 = objArr.length;
        }
        j(objArr, obj, i7, i8);
    }

    public static <T> T[] l(T[] tArr, T t6) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        int length = tArr.length;
        T[] result = (T[]) Arrays.copyOf(tArr, length + 1);
        result[length] = t6;
        kotlin.jvm.internal.l.d(result, "result");
        return result;
    }
}
