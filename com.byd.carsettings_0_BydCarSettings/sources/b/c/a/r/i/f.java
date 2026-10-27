package b.c.a.r.i;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f extends b.c.a.t.b {
    public static final Writer l = new a();
    public static final b.c.a.l m = new b.c.a.l("closed");
    public final List<b.c.a.i> n;
    public String o;
    public b.c.a.i p;

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
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public f() {
        super(l);
        this.n = new ArrayList();
        this.p = b.c.a.j.f2198a;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b C(long j) throws IOException {
        N(new b.c.a.l(Long.valueOf(j)));
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b E(Boolean bool) throws IOException {
        if (bool == null) {
            return s();
        }
        N(new b.c.a.l(bool));
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b F(Number number) throws IOException {
        if (number == null) {
            return s();
        }
        if (!l()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        N(new b.c.a.l(number));
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b G(String str) throws IOException {
        if (str == null) {
            return s();
        }
        N(new b.c.a.l(str));
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b H(boolean z) throws IOException {
        N(new b.c.a.l(Boolean.valueOf(z)));
        return this;
    }

    public b.c.a.i L() {
        if (this.n.isEmpty()) {
            return this.p;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.n);
    }

    public final b.c.a.i M() {
        return this.n.get(r0.size() - 1);
    }

    public final void N(b.c.a.i iVar) {
        if (this.o != null) {
            if (!iVar.e() || j()) {
                ((b.c.a.k) M()).i(this.o, iVar);
            }
            this.o = null;
            return;
        }
        if (this.n.isEmpty()) {
            this.p = iVar;
            return;
        }
        b.c.a.i iVarM = M();
        if (!(iVarM instanceof b.c.a.f)) {
            throw new IllegalStateException();
        }
        ((b.c.a.f) iVarM).i(iVar);
    }

    @Override // b.c.a.t.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.n.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.n.add(m);
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b e() throws IOException {
        b.c.a.f fVar = new b.c.a.f();
        N(fVar);
        this.n.add(fVar);
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b f() throws IOException {
        b.c.a.k kVar = new b.c.a.k();
        N(kVar);
        this.n.add(kVar);
        return this;
    }

    @Override // b.c.a.t.b, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b h() throws IOException {
        if (this.n.isEmpty() || this.o != null) {
            throw new IllegalStateException();
        }
        if (!(M() instanceof b.c.a.f)) {
            throw new IllegalStateException();
        }
        this.n.remove(r0.size() - 1);
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b i() throws IOException {
        if (this.n.isEmpty() || this.o != null) {
            throw new IllegalStateException();
        }
        if (!(M() instanceof b.c.a.k)) {
            throw new IllegalStateException();
        }
        this.n.remove(r0.size() - 1);
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b o(String str) throws IOException {
        if (this.n.isEmpty() || this.o != null) {
            throw new IllegalStateException();
        }
        if (!(M() instanceof b.c.a.k)) {
            throw new IllegalStateException();
        }
        this.o = str;
        return this;
    }

    @Override // b.c.a.t.b
    public b.c.a.t.b s() throws IOException {
        N(b.c.a.j.f2198a);
        return this;
    }
}
