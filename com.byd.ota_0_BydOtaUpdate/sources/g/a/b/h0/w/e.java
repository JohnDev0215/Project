package g.a.b.h0.w;

import g.a.f.f;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class e implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4988a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[][] f4989b;

    @Override // g.a.b.h0.w.c
    public void a(byte[] bArr) {
        if (this.f4989b == null) {
            this.f4989b = (long[][]) Array.newInstance((Class<?>) long.class, 256, 2);
        } else if (g.a.f.a.a(this.f4988a, bArr)) {
            return;
        }
        byte[] bArrF = g.a.f.a.f(bArr);
        this.f4988a = bArrF;
        d.b(bArrF, this.f4989b[1]);
        long[][] jArr = this.f4989b;
        d.g(jArr[1], jArr[1]);
        for (int i2 = 2; i2 < 256; i2 += 2) {
            long[][] jArr2 = this.f4989b;
            d.d(jArr2[i2 >> 1], jArr2[i2]);
            long[][] jArr3 = this.f4989b;
            d.o(jArr3[i2], jArr3[1], jArr3[i2 + 1]);
        }
    }

    @Override // g.a.b.h0.w.c
    public void b(byte[] bArr) {
        long[] jArr = this.f4989b[bArr[15] & 255];
        long j = jArr[0];
        long j2 = jArr[1];
        for (int i2 = 14; i2 >= 0; i2--) {
            long[] jArr2 = this.f4989b[bArr[i2] & 255];
            long j3 = j2 << 56;
            j2 = ((j2 >>> 8) | (j << 56)) ^ jArr2[1];
            j = (((((j >>> 8) ^ jArr2[0]) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2)) ^ (j3 >>> 7);
        }
        f.q(j, bArr, 0);
        f.q(j2, bArr, 8);
    }
}
