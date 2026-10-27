package j8;

import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: renamed from: j8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1560e extends AbstractC2139n {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1560e f17324r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1556a f17325s = new C1556a(1);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17326m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17327n;
    public C1559d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f17328p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17329q;

    static {
        C1560e c1560e = new C1560e();
        f17324r = c1560e;
        c1560e.f17327n = 0;
        c1560e.o = C1559d.f17300A;
    }

    public C1560e() {
        this.f17328p = (byte) -1;
        this.f17329q = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17328p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i7 = this.f17326m;
        if ((i7 & 1) != 1) {
            this.f17328p = (byte) 0;
            return false;
        }
        if ((i7 & 2) != 2) {
            this.f17328p = (byte) 0;
            return false;
        }
        if (this.o.b()) {
            this.f17328p = (byte) 1;
            return true;
        }
        this.f17328p = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17329q;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17326m & 1) == 1 ? A2.g.f(1, this.f17327n) : 0;
        if ((this.f17326m & 2) == 2) {
            iF += A2.g.h(2, this.o);
        }
        int size = this.l.size() + iF;
        this.f17329q = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        C1561f c1561f = new C1561f(2);
        c1561f.o = C1559d.f17300A;
        return c1561f;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1561f c1561f = new C1561f(2);
        c1561f.o = C1559d.f17300A;
        c1561f.j(this);
        return c1561f;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17326m & 1) == 1) {
            gVar.C(1, this.f17327n);
        }
        if ((this.f17326m & 2) == 2) {
            gVar.E(2, this.o);
        }
        gVar.H(this.l);
    }

    public C1560e(C2130e c2130e, C2132g c2132g) {
        C1557b c1557bG;
        this.f17328p = (byte) -1;
        this.f17329q = -1;
        boolean z6 = false;
        this.f17327n = 0;
        this.o = C1559d.f17300A;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    try {
                        int iN = c2130e.n();
                        if (iN != 0) {
                            if (iN == 8) {
                                this.f17326m |= 1;
                                this.f17327n = c2130e.k();
                            } else if (iN != 18) {
                                if (!c2130e.q(iN, gVarT)) {
                                }
                            } else {
                                if ((this.f17326m & 2) == 2) {
                                    C1559d c1559d = this.o;
                                    c1559d.getClass();
                                    c1557bG = C1557b.g();
                                    c1557bG.h(c1559d);
                                } else {
                                    c1557bG = null;
                                }
                                C1559d c1559d2 = (C1559d) c2130e.g(C1559d.B, c2132g);
                                this.o = c1559d2;
                                if (c1557bG != null) {
                                    c1557bG.h(c1559d2);
                                    this.o = c1557bG.f();
                                }
                                this.f17326m |= 2;
                            }
                        }
                        z6 = true;
                    } catch (IOException e10) {
                        C2142q c2142q = new C2142q(e10.getMessage());
                        c2142q.l = this;
                        throw c2142q;
                    }
                } catch (C2142q e11) {
                    e11.l = this;
                    throw e11;
                }
            } catch (Throwable th) {
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
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.l = c2128c.i();
            throw th3;
        }
        this.l = c2128c.i();
    }

    public C1560e(C1561f c1561f) {
        this.f17328p = (byte) -1;
        this.f17329q = -1;
        this.l = c1561f.l;
    }
}
