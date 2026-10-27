package j8;

import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2142q;
import p8.C2143r;

/* JADX INFO: loaded from: classes.dex */
public final class L extends AbstractC2139n {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final L f17147p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1556a f17148q = new C1556a(15);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public p8.s f17149m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public byte f17150n;
    public int o;

    static {
        L l = new L();
        f17147p = l;
        l.f17149m = C2143r.f20990m;
    }

    public L() {
        this.f17150n = (byte) -1;
        this.o = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17150n;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f17150n = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.o;
        if (i7 != -1) {
            return i7;
        }
        int size = 0;
        for (int i10 = 0; i10 < this.f17149m.size(); i10++) {
            AbstractC2129d abstractC2129dI = this.f17149m.i(i10);
            size += abstractC2129dI.size() + A2.g.j(abstractC2129dI.size());
        }
        int size2 = this.l.size() + this.f17149m.size() + size;
        this.o = size2;
        return size2;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        C1568m c1568m = new C1568m(3);
        c1568m.o = C2143r.f20990m;
        return c1568m;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1568m c1568m = new C1568m(3);
        c1568m.o = C2143r.f20990m;
        c1568m.l(this);
        return c1568m;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        for (int i7 = 0; i7 < this.f17149m.size(); i7++) {
            AbstractC2129d abstractC2129dI = this.f17149m.i(i7);
            gVar.N(1, 2);
            gVar.L(abstractC2129dI.size());
            gVar.H(abstractC2129dI);
        }
        gVar.H(this.l);
    }

    public L(C2130e c2130e) {
        this.f17150n = (byte) -1;
        this.o = -1;
        this.f17149m = C2143r.f20990m;
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
                            p8.t tVarE = c2130e.e();
                            if (!z10) {
                                this.f17149m = new C2143r();
                                z10 = true;
                            }
                            this.f17149m.o(tVarE);
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
                    this.f17149m = this.f17149m.b();
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
            this.f17149m = this.f17149m.b();
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

    public L(C1568m c1568m) {
        this.f17150n = (byte) -1;
        this.o = -1;
        this.l = c1568m.l;
    }
}
