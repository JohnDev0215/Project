package c.b.b.r.i;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends c.b.b.t.b {
    public static final Writer m = new a();
    public static final c.b.b.l n = new c.b.b.l("closed");
    public final List<c.b.b.i> o;
    public String p;
    public c.b.b.i q;

    /* JADX INFO: compiled from: JsonTreeWriter.java */
    public static class a extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            throw new AssertionError();
        }
    }

    public f() {
        super(m);
        this.o = new ArrayList();
        this.q = c.b.b.j.f4169a;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b O() throws IOException {
        c.b.b.f fVar = new c.b.b.f();
        r0(fVar);
        this.o.add(fVar);
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b P() throws IOException {
        c.b.b.k kVar = new c.b.b.k();
        r0(kVar);
        this.o.add(kVar);
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b R() throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(q0() instanceof c.b.b.f)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b S() throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(q0() instanceof c.b.b.k)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b X(String str) throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(q0() instanceof c.b.b.k)) {
            throw new IllegalStateException();
        }
        this.p = str;
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b Z() throws IOException {
        r0(c.b.b.j.f4169a);
        return this;
    }

    @Override // c.b.b.t.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.o.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.o.add(n);
    }

    @Override // c.b.b.t.b, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b j0(long j) throws IOException {
        r0(new c.b.b.l(Long.valueOf(j)));
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b k0(Boolean bool) throws IOException {
        if (bool == null) {
            return Z();
        }
        r0(new c.b.b.l(bool));
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b l0(Number number) throws IOException {
        if (number == null) {
            return Z();
        }
        if (!V()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        r0(new c.b.b.l(number));
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b m0(String str) throws IOException {
        if (str == null) {
            return Z();
        }
        r0(new c.b.b.l(str));
        return this;
    }

    @Override // c.b.b.t.b
    public c.b.b.t.b n0(boolean z) throws IOException {
        r0(new c.b.b.l(Boolean.valueOf(z)));
        return this;
    }

    public c.b.b.i p0() {
        if (this.o.isEmpty()) {
            return this.q;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.o);
    }

    public final c.b.b.i q0() {
        return this.o.get(r0.size() - 1);
    }

    public final void r0(c.b.b.i iVar) {
        if (this.p != null) {
            if (!iVar.e() || T()) {
                ((c.b.b.k) q0()).i(this.p, iVar);
            }
            this.p = null;
            return;
        }
        if (this.o.isEmpty()) {
            this.q = iVar;
            return;
        }
        c.b.b.i iVarQ0 = q0();
        if (!(iVarQ0 instanceof c.b.b.f)) {
            throw new IllegalStateException();
        }
        ((c.b.b.f) iVarQ0).i(iVar);
    }
}
