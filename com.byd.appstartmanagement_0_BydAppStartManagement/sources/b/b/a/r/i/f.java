package b.b.a.r.i;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends b.b.a.t.b {
    public static final Writer m = new a();
    public static final b.b.a.l n = new b.b.a.l("closed");
    public final List<b.b.a.i> o;
    public String p;
    public b.b.a.i q;

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
        super(m);
        this.o = new ArrayList();
        this.q = b.b.a.j.f387a;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b A(String str) throws IOException {
        if (str == null) {
            return n();
        }
        F(new b.b.a.l(str));
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b B(boolean z) throws IOException {
        F(new b.b.a.l(Boolean.valueOf(z)));
        return this;
    }

    public b.b.a.i D() {
        if (this.o.isEmpty()) {
            return this.q;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.o);
    }

    public final b.b.a.i E() {
        return this.o.get(r0.size() - 1);
    }

    public final void F(b.b.a.i iVar) {
        if (this.p != null) {
            if (!iVar.e() || h()) {
                ((b.b.a.k) E()).i(this.p, iVar);
            }
            this.p = null;
            return;
        }
        if (this.o.isEmpty()) {
            this.q = iVar;
            return;
        }
        b.b.a.i iVarE = E();
        if (!(iVarE instanceof b.b.a.f)) {
            throw new IllegalStateException();
        }
        ((b.b.a.f) iVarE).i(iVar);
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b c() throws IOException {
        b.b.a.f fVar = new b.b.a.f();
        F(fVar);
        this.o.add(fVar);
        return this;
    }

    @Override // b.b.a.t.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.o.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.o.add(n);
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b d() throws IOException {
        b.b.a.k kVar = new b.b.a.k();
        F(kVar);
        this.o.add(kVar);
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b f() throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(E() instanceof b.b.a.f)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // b.b.a.t.b, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b g() throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(E() instanceof b.b.a.k)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b l(String str) throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(E() instanceof b.b.a.k)) {
            throw new IllegalStateException();
        }
        this.p = str;
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b n() throws IOException {
        F(b.b.a.j.f387a);
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b x(long j) throws IOException {
        F(new b.b.a.l(Long.valueOf(j)));
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b y(Boolean bool) throws IOException {
        if (bool == null) {
            return n();
        }
        F(new b.b.a.l(bool));
        return this;
    }

    @Override // b.b.a.t.b
    public b.b.a.t.b z(Number number) throws IOException {
        if (number == null) {
            return n();
        }
        if (!j()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        F(new b.b.a.l(number));
        return this;
    }
}
