package com.google.zxing;

/* JADX INFO: compiled from: PlanarYUVLuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final byte[] f4281c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4282d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4283e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4284f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f4285g;

    public f(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        super(i5, i6);
        if (i3 + i5 > i || i4 + i6 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f4281c = bArr;
        this.f4282d = i;
        this.f4283e = i2;
        this.f4284f = i3;
        this.f4285g = i4;
        if (z) {
            k(i5, i6);
        }
    }

    private void k(int i, int i2) {
        byte[] bArr = this.f4281c;
        int i3 = (this.f4285g * this.f4282d) + this.f4284f;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = (i / 2) + i3;
            int i6 = (i3 + i) - 1;
            int i7 = i3;
            while (i7 < i5) {
                byte b2 = bArr[i7];
                bArr[i7] = bArr[i6];
                bArr[i6] = b2;
                i7++;
                i6--;
            }
            i4++;
            i3 += this.f4282d;
        }
    }

    @Override // com.google.zxing.d
    public byte[] b() {
        int iD = d();
        int iA = a();
        int i = this.f4282d;
        if (iD == i && iA == this.f4283e) {
            return this.f4281c;
        }
        int i2 = iD * iA;
        byte[] bArr = new byte[i2];
        int i3 = (this.f4285g * i) + this.f4284f;
        if (iD == i) {
            System.arraycopy(this.f4281c, i3, bArr, 0, i2);
            return bArr;
        }
        for (int i4 = 0; i4 < iA; i4++) {
            System.arraycopy(this.f4281c, i3, bArr, i4 * iD, iD);
            i3 += this.f4282d;
        }
        return bArr;
    }

    @Override // com.google.zxing.d
    public byte[] c(int i, byte[] bArr) {
        if (i < 0 || i >= a()) {
            throw new IllegalArgumentException("Requested row is outside the image: ".concat(String.valueOf(i)));
        }
        int iD = d();
        if (bArr == null || bArr.length < iD) {
            bArr = new byte[iD];
        }
        System.arraycopy(this.f4281c, ((i + this.f4285g) * this.f4282d) + this.f4284f, bArr, 0, iD);
        return bArr;
    }

    public int h() {
        return a() / 2;
    }

    public int i() {
        return d() / 2;
    }

    public int[] j() {
        int iD = d() / 2;
        int iA = a() / 2;
        int[] iArr = new int[iD * iA];
        byte[] bArr = this.f4281c;
        int i = (this.f4285g * this.f4282d) + this.f4284f;
        for (int i2 = 0; i2 < iA; i2++) {
            int i3 = i2 * iD;
            for (int i4 = 0; i4 < iD; i4++) {
                iArr[i3 + i4] = ((bArr[(i4 << 1) + i] & 255) * 65793) | (-16777216);
            }
            i += this.f4282d << 1;
        }
        return iArr;
    }
}
