package c.a.a.g;

/* JADX INFO: compiled from: PrimitiveArrayUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static boolean a(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean b(byte[] bArr) {
        return !a(bArr);
    }

    public static byte[] c(byte[] bArr, int i2) {
        if (i2 < 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        if (i2 > 0 && b(bArr)) {
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i2));
        }
        return bArr2;
    }
}
