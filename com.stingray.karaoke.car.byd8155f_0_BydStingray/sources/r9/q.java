package r9;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class q implements J {
    public final J l;

    public q(J j3) {
        A7.m.f(j3, "delegate");
        this.l = j3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.l.close();
    }

    @Override // r9.J
    public final L e() {
        return this.l.e();
    }

    @Override // r9.J
    public long p(C2319i c2319i, long j3) {
        A7.m.f(c2319i, "sink");
        return this.l.p(c2319i, j3);
    }

    public final String toString() {
        return getClass().getSimpleName() + '(' + this.l + ')';
    }
}
