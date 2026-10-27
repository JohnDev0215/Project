package com.google.gson.internal.bind;

import com.google.gson.g;
import com.google.gson.j;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.o;
import com.google.gson.w.c;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonTreeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends c {
    private static final Writer m = new a();
    private static final o n = new o("closed");
    private final List<j> o;
    private String p;
    private j q;

    /* JADX INFO: compiled from: JsonTreeWriter.java */
    static class a extends Writer {
        a() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            throw new AssertionError();
        }
    }

    public b() {
        super(m);
        this.o = new ArrayList();
        this.q = l.f4595a;
    }

    private j p0() {
        return this.o.get(r0.size() - 1);
    }

    private void q0(j jVar) {
        if (this.p != null) {
            if (!jVar.j() || T()) {
                ((m) p0()).m(this.p, jVar);
            }
            this.p = null;
            return;
        }
        if (this.o.isEmpty()) {
            this.q = jVar;
            return;
        }
        j jVarP0 = p0();
        if (!(jVarP0 instanceof g)) {
            throw new IllegalStateException();
        }
        ((g) jVarP0).m(jVar);
    }

    @Override // com.google.gson.w.c
    public c R() {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(p0() instanceof g)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // com.google.gson.w.c
    public c S() {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(p0() instanceof m)) {
            throw new IllegalStateException();
        }
        this.o.remove(r0.size() - 1);
        return this;
    }

    @Override // com.google.gson.w.c
    public c W(String str) {
        if (this.o.isEmpty() || this.p != null) {
            throw new IllegalStateException();
        }
        if (!(p0() instanceof m)) {
            throw new IllegalStateException();
        }
        this.p = str;
        return this;
    }

    @Override // com.google.gson.w.c
    public c Y() {
        q0(l.f4595a);
        return this;
    }

    @Override // com.google.gson.w.c, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.o.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.o.add(n);
    }

    @Override // com.google.gson.w.c, java.io.Flushable
    public void flush() {
    }

    @Override // com.google.gson.w.c
    public c i0(long j) {
        q0(new o(Long.valueOf(j)));
        return this;
    }

    @Override // com.google.gson.w.c
    public c j() {
        g gVar = new g();
        q0(gVar);
        this.o.add(gVar);
        return this;
    }

    @Override // com.google.gson.w.c
    public c j0(Boolean bool) {
        if (bool == null) {
            return Y();
        }
        q0(new o(bool));
        return this;
    }

    @Override // com.google.gson.w.c
    public c k0(Number number) {
        if (number == null) {
            return Y();
        }
        if (!V()) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        q0(new o(number));
        return this;
    }

    @Override // com.google.gson.w.c
    public c l0(String str) {
        if (str == null) {
            return Y();
        }
        q0(new o(str));
        return this;
    }

    @Override // com.google.gson.w.c
    public c m0(boolean z) {
        q0(new o(Boolean.valueOf(z)));
        return this;
    }

    public j o0() {
        if (this.o.isEmpty()) {
            return this.q;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.o);
    }

    @Override // com.google.gson.w.c
    public c y() {
        m mVar = new m();
        q0(mVar);
        this.o.add(mVar);
        return this;
    }
}
