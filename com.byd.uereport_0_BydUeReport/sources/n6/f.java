package n6;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends q6.b {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Writer f6568o = new a();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final k6.l f6569p = new k6.l("closed");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final List<k6.i> f6570q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f6571r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public k6.i f6572s;

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
        public void write(char[] cArr, int i8, int i9) {
            throw new AssertionError();
        }
    }

    public f() {
        super(f6568o);
        this.f6570q = new ArrayList();
        this.f6572s = k6.j.f5922a;
    }

    @Override // q6.b
    public q6.b B() throws IOException {
        d0(k6.j.f5922a);
        return this;
    }

    @Override // q6.b
    public q6.b R(long j8) throws IOException {
        d0(new k6.l(Long.valueOf(j8)));
        return this;
    }

    @Override // q6.b
    public q6.b T(Boolean bool) throws IOException {
        if (bool == null) {
            return B();
        }
        d0(new k6.l(bool));
        return this;
    }

    @Override // q6.b
    public q6.b V(Number number) throws IOException {
        if (number == null) {
            return B();
        }
        if (!t()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        d0(new k6.l(number));
        return this;
    }

    @Override // q6.b
    public q6.b X(String str) throws IOException {
        if (str == null) {
            return B();
        }
        d0(new k6.l(str));
        return this;
    }

    @Override // q6.b
    public q6.b Y(boolean z7) throws IOException {
        d0(new k6.l(Boolean.valueOf(z7)));
        return this;
    }

    public k6.i a0() {
        if (this.f6570q.isEmpty()) {
            return this.f6572s;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f6570q);
    }

    public final k6.i c0() {
        return this.f6570q.get(r0.size() - 1);
    }

    @Override // q6.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.f6570q.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.f6570q.add(f6569p);
    }

    public final void d0(k6.i iVar) {
        if (this.f6571r != null) {
            if (!iVar.e() || q()) {
                ((k6.k) c0()).i(this.f6571r, iVar);
            }
            this.f6571r = null;
            return;
        }
        if (this.f6570q.isEmpty()) {
            this.f6572s = iVar;
            return;
        }
        k6.i iVarC0 = c0();
        if (!(iVarC0 instanceof k6.f)) {
            throw new IllegalStateException();
        }
        ((k6.f) iVarC0).i(iVar);
    }

    @Override // q6.b, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // q6.b
    public q6.b i() throws IOException {
        k6.f fVar = new k6.f();
        d0(fVar);
        this.f6570q.add(fVar);
        return this;
    }

    @Override // q6.b
    public q6.b j() throws IOException {
        k6.k kVar = new k6.k();
        d0(kVar);
        this.f6570q.add(kVar);
        return this;
    }

    @Override // q6.b
    public q6.b n() throws IOException {
        if (this.f6570q.isEmpty() || this.f6571r != null) {
            throw new IllegalStateException();
        }
        if (!(c0() instanceof k6.f)) {
            throw new IllegalStateException();
        }
        this.f6570q.remove(r0.size() - 1);
        return this;
    }

    @Override // q6.b
    public q6.b o() throws IOException {
        if (this.f6570q.isEmpty() || this.f6571r != null) {
            throw new IllegalStateException();
        }
        if (!(c0() instanceof k6.k)) {
            throw new IllegalStateException();
        }
        this.f6570q.remove(r0.size() - 1);
        return this;
    }

    @Override // q6.b
    public q6.b w(String str) throws IOException {
        if (this.f6570q.isEmpty() || this.f6571r != null) {
            throw new IllegalStateException();
        }
        if (!(c0() instanceof k6.k)) {
            throw new IllegalStateException();
        }
        this.f6571r = str;
        return this;
    }
}
