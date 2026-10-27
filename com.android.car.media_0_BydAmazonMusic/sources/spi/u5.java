package spi;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u5 extends v5 {
    public static final a o = new a();
    public static final n5 p = new n5("closed");
    public final List<i5> l;
    public String m;
    public i5 n;

    /* JADX INFO: compiled from: JsonTreeWriter.java */
    public static class a extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public final void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public final void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    }

    public u5() {
        super(o);
        this.l = new ArrayList();
        this.n = k5.a;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.v5
    public final v5 b() {
        g5 g5Var = new g5();
        u(g5Var);
        this.l.add(g5Var);
        return this;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.v5
    public final v5 c() {
        l5 l5Var = new l5();
        u(l5Var);
        this.l.add(l5Var);
        return this;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.v5, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!this.l.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.l.add(p);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    @Override // spi.v5
    public final v5 e() {
        if (this.l.isEmpty() || this.m != null) {
            throw new IllegalStateException();
        }
        if (!(t() instanceof g5)) {
            throw new IllegalStateException();
        }
        this.l.remove(r0.size() - 1);
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    @Override // spi.v5
    public final v5 f() {
        if (this.l.isEmpty() || this.m != null) {
            throw new IllegalStateException();
        }
        if (!(t() instanceof l5)) {
            throw new IllegalStateException();
        }
        this.l.remove(r0.size() - 1);
        return this;
    }

    @Override // spi.v5, java.io.Flushable
    public final void flush() {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    @Override // spi.v5
    public final v5 g(String str) {
        if (this.l.isEmpty() || this.m != null) {
            throw new IllegalStateException();
        }
        if (!(t() instanceof l5)) {
            throw new IllegalStateException();
        }
        this.m = str;
        return this;
    }

    @Override // spi.v5
    public final v5 i() {
        u(k5.a);
        return this;
    }

    @Override // spi.v5
    public final v5 n(long j) {
        u(new n5((Number) Long.valueOf(j)));
        return this;
    }

    @Override // spi.v5
    public final v5 o(Boolean bool) {
        if (bool == null) {
            u(k5.a);
            return this;
        }
        u(new n5(bool));
        return this;
    }

    @Override // spi.v5
    public final v5 p(Number number) {
        if (number == null) {
            u(k5.a);
            return this;
        }
        if (!this.f) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        u(new n5(number));
        return this;
    }

    @Override // spi.v5
    public final v5 q(String str) {
        if (str == null) {
            u(k5.a);
            return this;
        }
        u(new n5(str));
        return this;
    }

    @Override // spi.v5
    public final v5 r(boolean z) {
        u(new n5(Boolean.valueOf(z)));
        return this;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    public final i5 t() {
        return (i5) this.l.get(r1.size() - 1);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.ArrayList, java.util.List<spi.i5>] */
    public final void u(i5 i5Var) {
        if (this.m != null) {
            Objects.requireNonNull(i5Var);
            if (!(i5Var instanceof k5) || this.i) {
                l5 l5Var = (l5) t();
                String str = this.m;
                Objects.requireNonNull(l5Var);
                l5Var.a.put(str, i5Var);
            }
            this.m = null;
            return;
        }
        if (this.l.isEmpty()) {
            this.n = i5Var;
            return;
        }
        i5 i5VarT = t();
        if (!(i5VarT instanceof g5)) {
            throw new IllegalStateException();
        }
        g5 g5Var = (g5) i5VarT;
        Objects.requireNonNull(g5Var);
        if (i5Var == null) {
            i5Var = k5.a;
        }
        g5Var.a.add(i5Var);
    }
}
