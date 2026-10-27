package c0;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: GrowingArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static int[] a(int[] iArr, int i3, int i4) {
        if (i3 + 1 > iArr.length) {
            int[] iArr2 = new int[c(i3)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            iArr = iArr2;
        }
        iArr[i3] = i4;
        return iArr;
    }

    public static <T> T[] b(T[] tArr, int i3, T t3) {
        if (i3 + 1 > tArr.length) {
            Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), c(i3));
            System.arraycopy(tArr, 0, objArr, 0, i3);
            tArr = (T[]) objArr;
        }
        tArr[i3] = t3;
        return tArr;
    }

    public static int c(int i3) {
        if (i3 <= 4) {
            return 8;
        }
        return i3 * 2;
    }
}
