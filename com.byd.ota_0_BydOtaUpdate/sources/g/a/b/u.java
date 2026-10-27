package g.a.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f5195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f5196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5197c;

    public static byte[] a(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(cArr.length + 1) * 2];
        for (int i2 = 0; i2 != cArr.length; i2++) {
            int i3 = i2 * 2;
            bArr[i3] = (byte) (cArr[i2] >>> '\b');
            bArr[i3 + 1] = (byte) cArr[i2];
        }
        return bArr;
    }

    public static byte[] b(char[] cArr) {
        if (cArr == null) {
            return new byte[0];
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) cArr[i2];
        }
        return bArr;
    }

    public static byte[] c(char[] cArr) {
        return cArr != null ? g.a.f.i.j(cArr) : new byte[0];
    }

    public abstract i d(int i2);

    public abstract i e(int i2);

    public abstract i f(int i2, int i3);

    public void g(byte[] bArr, byte[] bArr2, int i2) {
        this.f5195a = bArr;
        this.f5196b = bArr2;
        this.f5197c = i2;
    }
}
