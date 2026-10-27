package g.a.b.h0.x;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class e implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[][] f4990a;

    @Override // g.a.b.h0.x.a
    public void a(long[] jArr) {
        long[] jArr2 = new long[8];
        d.b(this.f4990a[((int) (jArr[7] >>> 56)) & 255], jArr2);
        for (int i2 = 62; i2 >= 0; i2--) {
            d.e(jArr2, jArr2);
            d.a(this.f4990a[((int) (jArr[i2 >>> 3] >>> ((i2 & 7) << 3))) & 255], jArr2, jArr2);
        }
        d.b(jArr2, jArr);
    }

    @Override // g.a.b.h0.x.a
    public void b(long[] jArr) {
        long[][] jArr2 = this.f4990a;
        if (jArr2 == null) {
            this.f4990a = (long[][]) Array.newInstance((Class<?>) long.class, 256, 8);
        } else if (d.c(jArr, jArr2[1])) {
            return;
        }
        d.b(jArr, this.f4990a[1]);
        for (int i2 = 2; i2 < 256; i2 += 2) {
            long[][] jArr3 = this.f4990a;
            d.d(jArr3[i2 >> 1], jArr3[i2]);
            long[][] jArr4 = this.f4990a;
            d.a(jArr4[i2], jArr4[1], jArr4[i2 + 1]);
        }
    }
}
