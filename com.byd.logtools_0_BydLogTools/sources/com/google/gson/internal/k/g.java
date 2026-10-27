package com.google.gson.internal.k;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends com.google.gson.stream.b {
    private static final Writer m = new a();
    private static final com.google.gson.l n = new com.google.gson.l("closed");
    private final List<com.google.gson.i> o;
    private String p;
    private com.google.gson.i q;

    /* JADX INFO: compiled from: JsonTreeWriter.java */
    class a extends Writer {
        a() {
        }

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

    public g() {
        super(m);
        this.o = new ArrayList();
        this.q = com.google.gson.j.f4149a;
    }

    private com.google.gson.i D() {
        return this.o.get(r0.size() - 1);
    }

    private void E(com.google.gson.i iVar) {
        if (this.p != null) {
            if (!iVar.e() || h()) {
                ((com.google.gson.k) D()).h(this.p, iVar);
            }
            this.p = null;
            return;
        }
        if (this.o.isEmpty()) {
            this.q = iVar;
            return;
        }
        com.google.gson.i iVarD = D();
        if (!(iVarD instanceof com.google.gson.f)) {
            throw new IllegalStateException();
        }
        ((com.google.gson.f) iVarD).h(iVar);
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b A(boolean z) throws IOException {
        E(new com.google.gson.l(Boolean.valueOf(z)));
        return this;
    }

    public com.google.gson.i C() {
        if (this.o.isEmpty()) {
            return this.q;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.o);
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b c() throws IOException {
        com.google.gson.f fVar = new com.google.gson.f();
        E(fVar);
        this.o.add(fVar);
        return this;
    }

    @Override // com.google.gson.stream.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.o.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.o.add(n);
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b d() throws IOException {
        com.google.gson.k kVar = new com.google.gson.k();
        E(kVar);
        this.o.add(kVar);
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b f() throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(D() instanceof com.google.gson.f)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // com.google.gson.stream.b, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b g() throws IOException {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(D() instanceof com.google.gson.k)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b k(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(D() instanceof com.google.gson.k)) {
            throw new IllegalStateException();
        }
        this.p = str;
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b m() throws IOException {
        E(com.google.gson.j.f4149a);
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b w(long j) throws IOException {
        E(new com.google.gson.l(Long.valueOf(j)));
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b x(Boolean bool) throws IOException {
        if (bool == null) {
            return m();
        }
        E(new com.google.gson.l(bool));
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b y(Number number) throws IOException {
        if (number == null) {
            return m();
        }
        if (!j()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        E(new com.google.gson.l(number));
        return this;
    }

    @Override // com.google.gson.stream.b
    public com.google.gson.stream.b z(String str) throws IOException {
        if (str == null) {
            return m();
        }
        E(new com.google.gson.l(str));
        return this;
    }
}
