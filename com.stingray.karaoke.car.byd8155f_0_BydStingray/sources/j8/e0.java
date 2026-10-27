package j8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends AbstractC2139n {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e0 f17330p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1556a f17331q = new C1556a(23);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f17332m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public byte f17333n;
    public int o;

    static {
        e0 e0Var = new e0();
        f17330p = e0Var;
        e0Var.f17332m = Collections.emptyList();
    }

    public e0() {
        this.f17333n = (byte) -1;
        this.o = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17333n;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f17333n = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.o;
        if (i7 != -1) {
            return i7;
        }
        int iH = 0;
        for (int i10 = 0; i10 < this.f17332m.size(); i10++) {
            iH += A2.g.h(1, (AbstractC2126a) this.f17332m.get(i10));
        }
        int size = this.l.size() + iH;
        this.o = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        C1568m c1568m = new C1568m(2);
        c1568m.o = Collections.emptyList();
        return c1568m;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1568m c1568m = new C1568m(2);
        c1568m.o = Collections.emptyList();
        c1568m.m(this);
        return c1568m;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        for (int i7 = 0; i7 < this.f17332m.size(); i7++) {
            gVar.E(1, (AbstractC2126a) this.f17332m.get(i7));
        }
        gVar.H(this.l);
    }

    public final C1568m i() {
        C1568m c1568m = new C1568m(2);
        c1568m.o = Collections.emptyList();
        c1568m.m(this);
        return c1568m;
    }

    public e0(C2130e c2130e, C2132g c2132g) {
        this.f17333n = (byte) -1;
        this.o = -1;
        this.f17332m = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        boolean z10 = false;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN != 10) {
                            if (!c2130e.q(iN, gVarT)) {
                            }
                        } else {
                            if (!z10) {
                                this.f17332m = new ArrayList();
                                z10 = true;
                            }
                            this.f17332m.add(c2130e.g(d0.f17315w, c2132g));
                        }
                    }
                    z6 = true;
                } catch (C2142q e10) {
                    e10.l = this;
                    throw e10;
                } catch (IOException e11) {
                    C2142q c2142q = new C2142q(e11.getMessage());
                    c2142q.l = this;
                    throw c2142q;
                }
            } catch (Throwable th) {
                if (z10) {
                    this.f17332m = Collections.unmodifiableList(this.f17332m);
                }
                try {
                    gVarT.n();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.l = c2128c.i();
                    throw th2;
                }
                this.l = c2128c.i();
                throw th;
            }
        }
        if (z10) {
            this.f17332m = Collections.unmodifiableList(this.f17332m);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.l = c2128c.i();
            throw th3;
        }
        this.l = c2128c.i();
    }

    public e0(C1568m c1568m) {
        this.f17333n = (byte) -1;
        this.o = -1;
        this.l = c1568m.l;
    }
}
