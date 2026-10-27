package p;

import java.io.IOException;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class iu0 implements a81 {
    public final a81 c;
    public final LinkedHashMap e = new LinkedHashMap();

    public iu0(a81 a81Var) {
        this.c = a81Var;
    }

    @Override // p.a81
    public final a81 A(int i) {
        this.c.A(i);
        return this;
    }

    @Override // p.a81
    public final a81 G(double d) {
        this.c.G(d);
        return this;
    }

    @Override // p.a81
    public final a81 N(String str) {
        v03.j(str, "value");
        this.c.N(str);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.c.close();
    }

    @Override // p.a81
    public final a81 f() {
        this.c.f();
        return this;
    }

    @Override // p.a81
    public final a81 h() {
        this.c.h();
        return this;
    }

    @Override // p.a81
    public final a81 i0() {
        this.c.i0();
        return this;
    }

    @Override // p.a81
    public final a81 j() {
        this.c.j();
        return this;
    }

    @Override // p.a81
    public final a81 k() {
        this.c.k();
        return this;
    }

    @Override // p.a81
    public final a81 l0(String str) {
        this.c.l0(str);
        return this;
    }

    @Override // p.a81
    public final a81 m0(o71 o71Var) {
        v03.j(o71Var, "value");
        this.c.m0(o71Var);
        return this;
    }

    @Override // p.a81
    public final a81 n0(boolean z) {
        this.c.n0(z);
        return this;
    }

    @Override // p.a81
    public final a81 z(long j) {
        this.c.z(j);
        return this;
    }
}
