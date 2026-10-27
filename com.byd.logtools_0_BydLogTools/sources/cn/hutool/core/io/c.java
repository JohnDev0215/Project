package cn.hutool.core.io;

/* JADX INFO: compiled from: FastByteBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2919b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f2921d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2922e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2923f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f2924g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[][] f2918a = new byte[16][];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2920c = -1;

    public c(int i) {
        this.f2924g = Math.abs(i <= 0 ? 1024 : i);
    }

    private void c(int i) {
        int iMax = Math.max(this.f2924g, i - this.f2923f);
        int i2 = this.f2920c + 1;
        this.f2920c = i2;
        this.f2921d = new byte[iMax];
        this.f2922e = 0;
        byte[][] bArr = this.f2918a;
        if (i2 >= bArr.length) {
            byte[][] bArr2 = new byte[bArr.length << 1][];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.f2918a = bArr2;
        }
        this.f2918a[this.f2920c] = this.f2921d;
        this.f2919b++;
    }

    public c a(byte b2) {
        byte[] bArr = this.f2921d;
        if (bArr == null || this.f2922e == bArr.length) {
            c(this.f2923f + 1);
        }
        byte[] bArr2 = this.f2921d;
        int i = this.f2922e;
        bArr2[i] = b2;
        this.f2922e = i + 1;
        this.f2923f++;
        return this;
    }

    public c b(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        if (i < 0 || i2 < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return this;
        }
        int i4 = this.f2923f + i2;
        byte[] bArr2 = this.f2921d;
        if (bArr2 != null) {
            int iMin = Math.min(i2, bArr2.length - this.f2922e);
            System.arraycopy(bArr, i3 - i2, this.f2921d, this.f2922e, iMin);
            i2 -= iMin;
            this.f2922e += iMin;
            this.f2923f += iMin;
        }
        if (i2 > 0) {
            c(i4);
            int iMin2 = Math.min(i2, this.f2921d.length - this.f2922e);
            System.arraycopy(bArr, i3 - i2, this.f2921d, this.f2922e, iMin2);
            this.f2922e += iMin2;
            this.f2923f += iMin2;
        }
        return this;
    }

    public byte[] d() {
        byte[] bArr = new byte[this.f2923f];
        if (this.f2920c == -1) {
            return bArr;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = this.f2920c;
            if (i >= i3) {
                System.arraycopy(this.f2918a[i3], 0, bArr, i2, this.f2922e);
                return bArr;
            }
            byte[][] bArr2 = this.f2918a;
            int length = bArr2[i].length;
            System.arraycopy(bArr2[i], 0, bArr, i2, length);
            i2 += length;
            i++;
        }
    }
}
