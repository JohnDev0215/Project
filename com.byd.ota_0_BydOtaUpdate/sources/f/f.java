package f;

import java.io.IOException;

/* JADX INFO: compiled from: ForwardingSink.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p f3704c;

    public f(p pVar) {
        if (pVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f3704c = pVar;
    }

    @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f3704c.close();
    }

    @Override // f.p
    public r e() {
        return this.f3704c.e();
    }

    @Override // f.p, java.io.Flushable
    public void flush() throws IOException {
        this.f3704c.flush();
    }

    @Override // f.p
    public void h(c cVar, long j) throws IOException {
        this.f3704c.h(cVar, j);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f3704c.toString() + ")";
    }
}
