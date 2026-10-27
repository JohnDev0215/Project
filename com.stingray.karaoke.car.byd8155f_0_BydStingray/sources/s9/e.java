package s9;

import A7.m;
import java.io.IOException;
import r9.C2319i;
import r9.J;
import r9.q;

/* JADX INFO: loaded from: classes.dex */
public final class e extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22387m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f22388n;
    public long o;

    public e(J j3, long j10, boolean z6) {
        super(j3);
        this.f22387m = j10;
        this.f22388n = z6;
    }

    @Override // r9.q, r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        m.f(c2319i, "sink");
        long j10 = this.o;
        long j11 = this.f22387m;
        if (j10 > j11) {
            j3 = 0;
        } else if (this.f22388n) {
            long j12 = j11 - j10;
            if (j12 == 0) {
                return -1L;
            }
            j3 = Math.min(j3, j12);
        }
        long jP = super.p(c2319i, j3);
        if (jP != -1) {
            this.o += jP;
        }
        long j13 = this.o;
        if ((j13 >= j11 || jP != -1) && j13 <= j11) {
            return jP;
        }
        if (jP > 0 && j13 > j11) {
            long j14 = c2319i.f21932m - (j13 - j11);
            C2319i c2319i2 = new C2319i();
            c2319i2.r(c2319i);
            c2319i.t(c2319i2, j14);
            c2319i2.b();
        }
        throw new IOException("expected " + j11 + " bytes but got " + this.o);
    }
}
