package g.a.b.h0.x;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class f implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[][] f4991a;

    @Override // g.a.b.h0.x.a
    public void a(long[] jArr) {
        long[] jArr2 = new long[2];
        b.b(this.f4991a[((int) (jArr[1] >>> 56)) & 255], jArr2);
        for (int i2 = 14; i2 >= 0; i2--) {
            b.e(jArr2, jArr2);
            b.a(this.f4991a[((int) (jArr[i2 >>> 3] >>> ((i2 & 7) << 3))) & 255], jArr2, jArr2);
        }
        b.b(jArr2, jArr);
    }

    @Override // g.a.b.h0.x.a
    public void b(long[] jArr) {
        long[][] jArr2 = this.f4991a;
        if (jArr2 == null) {
            this.f4991a = (long[][]) Array.newInstance((Class<?>) long.class, 256, 2);
        } else if (b.c(jArr, jArr2[1])) {
            return;
        }
        b.b(jArr, this.f4991a[1]);
        for (int i2 = 2; i2 < 256; i2 += 2) {
            long[][] jArr3 = this.f4991a;
            b.d(jArr3[i2 >> 1], jArr3[i2]);
            long[][] jArr4 = this.f4991a;
            b.a(jArr4[i2], jArr4[1], jArr4[i2 + 1]);
        }
    }
}
