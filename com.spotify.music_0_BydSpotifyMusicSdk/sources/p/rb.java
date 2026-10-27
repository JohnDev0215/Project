package p;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class rb extends he1 {
    public static List h0(Object[] objArr) {
        v03.j(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        v03.i(listAsList, "asList(this)");
        return listAsList;
    }

    public static void i0(int i, int i2, int i3, byte[] bArr, byte[] bArr2) {
        v03.j(bArr, "<this>");
        v03.j(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
    }

    public static void j0(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        v03.j(iArr, "<this>");
        v03.j(iArr2, "destination");
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
    }

    public static void k0(int i, int i2, int i3, Object[] objArr, Object[] objArr2) {
        v03.j(objArr, "<this>");
        v03.j(objArr2, "destination");
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static /* synthetic */ void l0(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        k0(0, i, i2, objArr, objArr2);
    }

    public static byte[] m0(byte[] bArr, int i, int i2) {
        v03.j(bArr, "<this>");
        he1.m(i2, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2);
        v03.i(bArrCopyOfRange, "copyOfRange(this, fromIndex, toIndex)");
        return bArrCopyOfRange;
    }

    public static Object[] n0(int i, int i2, Object[] objArr) {
        v03.j(objArr, "<this>");
        he1.m(i2, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i, i2);
        v03.i(objArrCopyOfRange, "copyOfRange(this, fromIndex, toIndex)");
        return objArrCopyOfRange;
    }

    public static void o0(Object[] objArr, tz2 tz2Var) {
        int length = objArr.length;
        v03.j(objArr, "<this>");
        Arrays.fill(objArr, 0, length, tz2Var);
    }
}
