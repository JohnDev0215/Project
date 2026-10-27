package g.a.e.d.a;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f5713a;

    public h(byte[] bArr) {
        if (bArr.length <= 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        int iE = f.e(bArr, 0);
        int iA = e.a(iE - 1);
        if (bArr.length != (iE * iA) + 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        this.f5713a = new int[iE];
        for (int i2 = 0; i2 < iE; i2++) {
            this.f5713a[i2] = f.f(bArr, (i2 * iA) + 4, iA);
        }
        if (!b(this.f5713a)) {
            throw new IllegalArgumentException("invalid encoding");
        }
    }

    public byte[] a() {
        int length = this.f5713a.length;
        int iA = e.a(length - 1);
        byte[] bArr = new byte[(length * iA) + 4];
        f.a(length, bArr, 0);
        for (int i2 = 0; i2 < length; i2++) {
            f.b(this.f5713a[i2], bArr, (i2 * iA) + 4, iA);
        }
        return bArr;
    }

    public final boolean b(int[] iArr) {
        int length = iArr.length;
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] < 0 || iArr[i2] >= length || zArr[iArr[i2]]) {
                return false;
            }
            zArr[iArr[i2]] = true;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof h) {
            return d.b(this.f5713a, ((h) obj).f5713a);
        }
        return false;
    }

    public int hashCode() {
        return this.f5713a.hashCode();
    }

    public String toString() {
        String str = "[" + this.f5713a[0];
        for (int i2 = 1; i2 < this.f5713a.length; i2++) {
            str = str + ", " + this.f5713a[i2];
        }
        return str + "]";
    }
}
