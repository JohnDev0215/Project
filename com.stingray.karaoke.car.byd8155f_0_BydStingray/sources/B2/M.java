package B2;

import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f519a = new byte[10];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f520b;
    public int c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f522f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f523g;

    public final void a(L l, K k10) {
        if (this.c > 0) {
            l.b(this.d, this.f521e, this.f522f, this.f523g, k10);
            this.c = 0;
        }
    }

    public final void b(L l, long j3, int i7, int i10, int i11, K k10) {
        AbstractC1346c.i("TrueHD chunk samples must be contiguous in the sample queue.", this.f523g <= i10 + i11);
        if (this.f520b) {
            int i12 = this.c;
            int i13 = i12 + 1;
            this.c = i13;
            if (i12 == 0) {
                this.d = j3;
                this.f521e = i7;
                this.f522f = 0;
            }
            this.f522f += i10;
            this.f523g = i11;
            if (i13 >= 16) {
                a(l, k10);
            }
        }
    }

    public final void c(s sVar) {
        if (this.f520b) {
            return;
        }
        byte[] bArr = this.f519a;
        int i7 = 0;
        sVar.a(0, 10, bArr);
        sVar.h();
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111) {
            byte b10 = bArr[7];
            if ((b10 & 254) == 186) {
                i7 = 40 << ((bArr[(b10 & 255) == 187 ? '\t' : '\b'] >> 4) & 7);
            }
        }
        if (i7 == 0) {
            return;
        }
        this.f520b = true;
    }
}
