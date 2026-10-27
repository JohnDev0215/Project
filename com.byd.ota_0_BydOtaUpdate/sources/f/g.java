package f;

import java.io.IOException;

/* JADX INFO: compiled from: ForwardingSource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g implements q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q f3705c;

    public g(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f3705c = qVar;
    }

    @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f3705c.close();
    }

    @Override // f.q
    public r e() {
        return this.f3705c.e();
    }

    @Override // f.q
    public long n(c cVar, long j) throws IOException {
        return this.f3705c.n(cVar, j);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f3705c.toString() + ")";
    }
}
