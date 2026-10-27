package com.google.zxing;

/* JADX INFO: compiled from: InvertedLuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f4189c;

    public c(d dVar) {
        super(dVar.d(), dVar.a());
        this.f4189c = dVar;
    }

    @Override // com.google.zxing.d
    public byte[] b() {
        byte[] bArrB = this.f4189c.b();
        int iD = d() * a();
        byte[] bArr = new byte[iD];
        for (int i = 0; i < iD; i++) {
            bArr[i] = (byte) (255 - (bArrB[i] & 255));
        }
        return bArr;
    }

    @Override // com.google.zxing.d
    public byte[] c(int i, byte[] bArr) {
        byte[] bArrC = this.f4189c.c(i, bArr);
        int iD = d();
        for (int i2 = 0; i2 < iD; i2++) {
            bArrC[i2] = (byte) (255 - (bArrC[i2] & 255));
        }
        return bArrC;
    }

    @Override // com.google.zxing.d
    public boolean f() {
        return this.f4189c.f();
    }

    @Override // com.google.zxing.d
    public d g() {
        return new c(this.f4189c.g());
    }
}
