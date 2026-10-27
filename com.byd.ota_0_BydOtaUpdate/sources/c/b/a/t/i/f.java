package c.b.a.t.i;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends c.b.a.v.b {
    public static final Writer K3 = new a();
    public static final c.b.a.n L3 = new c.b.a.n("closed");
    public final List<c.b.a.k> M3;
    public String N3;
    public c.b.a.k O3;

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
        super(K3);
        this.M3 = new ArrayList();
        this.O3 = c.b.a.l.f2911a;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b I() throws IOException {
        if (this.M3.isEmpty() || this.N3 != null) {
            throw new IllegalStateException();
        }
        if (!(g0() instanceof c.b.a.h)) {
            throw new IllegalStateException();
        }
        this.M3.remove(r0.size() - 1);
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b J() throws IOException {
        if (this.M3.isEmpty() || this.N3 != null) {
            throw new IllegalStateException();
        }
        if (!(g0() instanceof c.b.a.m)) {
            throw new IllegalStateException();
        }
        this.M3.remove(r0.size() - 1);
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b N(String str) throws IOException {
        if (this.M3.isEmpty() || this.N3 != null) {
            throw new IllegalStateException();
        }
        if (!(g0() instanceof c.b.a.m)) {
            throw new IllegalStateException();
        }
        this.N3 = str;
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b P() throws IOException {
        h0(c.b.a.l.f2911a);
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b Z(long j) throws IOException {
        h0(new c.b.a.n(Long.valueOf(j)));
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b a0(Boolean bool) throws IOException {
        if (bool == null) {
            return P();
        }
        h0(new c.b.a.n(bool));
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b b0(Number number) throws IOException {
        if (number == null) {
            return P();
        }
        if (!M()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        h0(new c.b.a.n(number));
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b c0(String str) throws IOException {
        if (str == null) {
            return P();
        }
        h0(new c.b.a.n(str));
        return this;
    }

    @Override // c.b.a.v.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.M3.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.M3.add(L3);
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b d0(boolean z) throws IOException {
        h0(new c.b.a.n(Boolean.valueOf(z)));
        return this;
    }

    public c.b.a.k f0() {
        if (this.M3.isEmpty()) {
            return this.O3;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.M3);
    }

    @Override // c.b.a.v.b, java.io.Flushable
    public void flush() throws IOException {
    }

    public final c.b.a.k g0() {
        return this.M3.get(r0.size() - 1);
    }

    public final void h0(c.b.a.k kVar) {
        if (this.N3 != null) {
            if (!kVar.f() || K()) {
                ((c.b.a.m) g0()).i(this.N3, kVar);
            }
            this.N3 = null;
            return;
        }
        if (this.M3.isEmpty()) {
            this.O3 = kVar;
            return;
        }
        c.b.a.k kVarG0 = g0();
        if (!(kVarG0 instanceof c.b.a.h)) {
            throw new IllegalStateException();
        }
        ((c.b.a.h) kVarG0).i(kVar);
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b s() throws IOException {
        c.b.a.h hVar = new c.b.a.h();
        h0(hVar);
        this.M3.add(hVar);
        return this;
    }

    @Override // c.b.a.v.b
    public c.b.a.v.b v() throws IOException {
        c.b.a.m mVar = new c.b.a.m();
        h0(mVar);
        this.M3.add(mVar);
        return this;
    }
}
