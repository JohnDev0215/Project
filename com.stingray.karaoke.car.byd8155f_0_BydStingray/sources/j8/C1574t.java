package j8;

import java.io.IOException;
import l8.C1789g;
import p8.AbstractC2126a;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2136k;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: renamed from: j8.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1574t extends AbstractC2136k {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1574t f17442r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1556a f17443s = new C1556a(7);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17444m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17445n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f17446p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17447q;

    static {
        C1574t c1574t = new C1574t();
        f17442r = c1574t;
        c1574t.o = 0;
    }

    public C1574t(C1573s c1573s) {
        super(c1573s);
        this.f17446p = (byte) -1;
        this.f17447q = -1;
        this.f17444m = c1573s.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17442r;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17446p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if (i()) {
            this.f17446p = (byte) 1;
            return true;
        }
        this.f17446p = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17447q;
        if (i7 != -1) {
            return i7;
        }
        int size = this.f17444m.size() + j() + ((this.f17445n & 1) == 1 ? A2.g.f(1, this.o) : 0);
        this.f17447q = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return new C1573s();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1573s c1573s = new C1573s();
        c1573s.g(this);
        return c1573s;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17445n & 1) == 1) {
            gVar.C(1, this.o);
        }
        c1789g.H(200, gVar);
        gVar.H(this.f17444m);
    }

    public C1574t() {
        this.f17446p = (byte) -1;
        this.f17447q = -1;
        this.f17444m = AbstractC2129d.l;
    }

    public C1574t(C2130e c2130e, C2132g c2132g) {
        this.f17446p = (byte) -1;
        this.f17447q = -1;
        boolean z6 = false;
        this.o = 0;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN != 8) {
                            if (!n(c2130e, gVarT, c2132g, iN)) {
                            }
                        } else {
                            this.f17445n |= 1;
                            this.o = c2130e.k();
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
                try {
                    gVarT.n();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f17444m = c2128c.i();
                    throw th2;
                }
                this.f17444m = c2128c.i();
                m();
                throw th;
            }
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17444m = c2128c.i();
            throw th3;
        }
        this.f17444m = c2128c.i();
        m();
    }
}
