package androidx.core.content.c;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: GrowingArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
final class e {
    public static int[] a(int[] iArr, int i2, int i3) {
        if (i2 + 1 > iArr.length) {
            int[] iArr2 = new int[c(i2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr = iArr2;
        }
        iArr[i2] = i3;
        return iArr;
    }

    public static <T> T[] b(T[] tArr, int i2, T t) {
        if (i2 + 1 > tArr.length) {
            Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), c(i2));
            System.arraycopy(tArr, 0, objArr, 0, i2);
            tArr = (T[]) objArr;
        }
        tArr[i2] = t;
        return tArr;
    }

    public static int c(int i2) {
        if (i2 <= 4) {
            return 8;
        }
        return i2 * 2;
    }
}
