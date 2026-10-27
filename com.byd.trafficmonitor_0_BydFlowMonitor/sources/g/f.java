package g;

import java.io.IOException;

/* JADX INFO: compiled from: ForwardingSource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f5473b;

    public f(p pVar) {
        if (pVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5473b = pVar;
    }

    @Override // g.p
    public q b() {
        return this.f5473b.b();
    }

    @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5473b.close();
    }

    @Override // g.p
    public long m(c cVar, long j) throws IOException {
        return this.f5473b.m(cVar, j);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f5473b.toString() + ")";
    }
}
