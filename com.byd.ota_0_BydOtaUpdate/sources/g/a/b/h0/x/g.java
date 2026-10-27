package g.a.b.h0.x;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class g implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[][] f4992a;

    @Override // g.a.b.h0.x.a
    public void a(long[] jArr) {
        long[] jArr2 = new long[4];
        c.b(this.f4992a[((int) (jArr[3] >>> 56)) & 255], jArr2);
        for (int i2 = 30; i2 >= 0; i2--) {
            c.e(jArr2, jArr2);
            c.a(this.f4992a[((int) (jArr[i2 >>> 3] >>> ((i2 & 7) << 3))) & 255], jArr2, jArr2);
        }
        c.b(jArr2, jArr);
    }

    @Override // g.a.b.h0.x.a
    public void b(long[] jArr) {
        long[][] jArr2 = this.f4992a;
        if (jArr2 == null) {
            this.f4992a = (long[][]) Array.newInstance((Class<?>) long.class, 256, 4);
        } else if (c.c(jArr, jArr2[1])) {
            return;
        }
        c.b(jArr, this.f4992a[1]);
        for (int i2 = 2; i2 < 256; i2 += 2) {
            long[][] jArr3 = this.f4992a;
            c.d(jArr3[i2 >> 1], jArr3[i2]);
            long[][] jArr4 = this.f4992a;
            c.a(jArr4[i2], jArr4[1], jArr4[i2 + 1]);
        }
    }
}
