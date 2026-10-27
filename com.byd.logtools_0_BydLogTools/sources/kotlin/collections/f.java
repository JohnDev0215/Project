package kotlin.collections;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _ArraysJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public class f extends e {
    public static <T> List<T> b(T[] tArr) {
        kotlin.jvm.internal.i.c(tArr, "$this$asList");
        List<T> listA = h.a(tArr);
        kotlin.jvm.internal.i.b(listA, "ArraysUtilJVM.asList(this)");
        return listA;
    }

    public static byte[] c(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        kotlin.jvm.internal.i.c(bArr, "$this$copyInto");
        kotlin.jvm.internal.i.c(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
        return bArr2;
    }

    public static /* synthetic */ byte[] d(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        return c(bArr, bArr2, i, i2, i3);
    }

    public static byte[] e(byte[] bArr, int i, int i2) {
        kotlin.jvm.internal.i.c(bArr, "$this$copyOfRangeImpl");
        d.a(i2, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2);
        kotlin.jvm.internal.i.b(bArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return bArrCopyOfRange;
    }

    public static <T> T[] f(T[] tArr, int i, int i2) {
        kotlin.jvm.internal.i.c(tArr, "$this$copyOfRangeImpl");
        d.a(i2, tArr.length);
        T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i, i2);
        kotlin.jvm.internal.i.b(tArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return tArr2;
    }

    public static final void g(int[] iArr, int i, int i2, int i3) {
        kotlin.jvm.internal.i.c(iArr, "$this$fill");
        Arrays.fill(iArr, i2, i3, i);
    }

    public static <T> void h(T[] tArr, T t, int i, int i2) {
        kotlin.jvm.internal.i.c(tArr, "$this$fill");
        Arrays.fill(tArr, i, i2, t);
    }

    public static /* synthetic */ void i(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        g(iArr, i, i2, i3);
    }

    public static /* synthetic */ void j(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        h(objArr, obj, i, i2);
    }
}
