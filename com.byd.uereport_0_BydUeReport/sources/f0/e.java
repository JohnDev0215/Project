package f0;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: GrowingArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static int[] a(int[] iArr, int i8, int i9) {
        if (i8 + 1 > iArr.length) {
            int[] iArr2 = new int[c(i8)];
            System.arraycopy(iArr, 0, iArr2, 0, i8);
            iArr = iArr2;
        }
        iArr[i8] = i9;
        return iArr;
    }

    public static <T> T[] b(T[] tArr, int i8, T t7) {
        if (i8 + 1 > tArr.length) {
            Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), c(i8));
            System.arraycopy(tArr, 0, objArr, 0, i8);
            tArr = (T[]) objArr;
        }
        tArr[i8] = t7;
        return tArr;
    }

    public static int c(int i8) {
        if (i8 <= 4) {
            return 8;
        }
        return i8 * 2;
    }
}
