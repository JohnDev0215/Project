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

/* JADX INFO: loaded from: classes.dex */
public final class Z extends AbstractC2136k {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final Z f17252w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C1556a f17253x = new C1556a(21);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17255n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17256p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Q f17257q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17258r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Q f17259s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17260t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public byte f17261u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17262v;

    static {
        Z z6 = new Z();
        f17252w = z6;
        z6.o = 0;
        z6.f17256p = 0;
        Q q4 = Q.f17178E;
        z6.f17257q = q4;
        z6.f17258r = 0;
        z6.f17259s = q4;
        z6.f17260t = 0;
    }

    public Z(Y y10) {
        super(y10);
        this.f17261u = (byte) -1;
        this.f17262v = -1;
        this.f17254m = y10.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17252w;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17261u;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i7 = this.f17255n;
        if ((i7 & 2) != 2) {
            this.f17261u = (byte) 0;
            return false;
        }
        if ((i7 & 4) == 4 && !this.f17257q.b()) {
            this.f17261u = (byte) 0;
            return false;
        }
        if ((this.f17255n & 16) == 16 && !this.f17259s.b()) {
            this.f17261u = (byte) 0;
            return false;
        }
        if (i()) {
            this.f17261u = (byte) 1;
            return true;
        }
        this.f17261u = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17262v;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17255n & 1) == 1 ? A2.g.f(1, this.o) : 0;
        if ((this.f17255n & 2) == 2) {
            iF += A2.g.f(2, this.f17256p);
        }
        if ((this.f17255n & 4) == 4) {
            iF += A2.g.h(3, this.f17257q);
        }
        if ((this.f17255n & 16) == 16) {
            iF += A2.g.h(4, this.f17259s);
        }
        if ((this.f17255n & 8) == 8) {
            iF += A2.g.f(5, this.f17258r);
        }
        if ((this.f17255n & 32) == 32) {
            iF += A2.g.f(6, this.f17260t);
        }
        int size = this.f17254m.size() + j() + iF;
        this.f17262v = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        Y y10 = new Y();
        Q q4 = Q.f17178E;
        y10.f17248r = q4;
        y10.f17250t = q4;
        return y10;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        Y y10 = new Y();
        Q q4 = Q.f17178E;
        y10.f17248r = q4;
        y10.f17250t = q4;
        y10.h(this);
        return y10;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17255n & 1) == 1) {
            gVar.C(1, this.o);
        }
        if ((this.f17255n & 2) == 2) {
            gVar.C(2, this.f17256p);
        }
        if ((this.f17255n & 4) == 4) {
            gVar.E(3, this.f17257q);
        }
        if ((this.f17255n & 16) == 16) {
            gVar.E(4, this.f17259s);
        }
        if ((this.f17255n & 8) == 8) {
            gVar.C(5, this.f17258r);
        }
        if ((this.f17255n & 32) == 32) {
            gVar.C(6, this.f17260t);
        }
        c1789g.H(200, gVar);
        gVar.H(this.f17254m);
    }

    public Z() {
        this.f17261u = (byte) -1;
        this.f17262v = -1;
        this.f17254m = AbstractC2129d.l;
    }

    public Z(C2130e c2130e, C2132g c2132g) {
        this.f17261u = (byte) -1;
        this.f17262v = -1;
        boolean z6 = false;
        this.o = 0;
        this.f17256p = 0;
        Q q4 = Q.f17178E;
        this.f17257q = q4;
        this.f17258r = 0;
        this.f17259s = q4;
        this.f17260t = 0;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f17255n |= 1;
                            this.o = c2130e.k();
                        } else if (iN != 16) {
                            P pR = null;
                            if (iN == 26) {
                                if ((this.f17255n & 4) == 4) {
                                    Q q10 = this.f17257q;
                                    q10.getClass();
                                    pR = Q.r(q10);
                                }
                                Q q11 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17257q = q11;
                                if (pR != null) {
                                    pR.i(q11);
                                    this.f17257q = pR.g();
                                }
                                this.f17255n |= 4;
                            } else if (iN == 34) {
                                if ((this.f17255n & 16) == 16) {
                                    Q q12 = this.f17259s;
                                    q12.getClass();
                                    pR = Q.r(q12);
                                }
                                Q q13 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17259s = q13;
                                if (pR != null) {
                                    pR.i(q13);
                                    this.f17259s = pR.g();
                                }
                                this.f17255n |= 16;
                            } else if (iN == 40) {
                                this.f17255n |= 8;
                                this.f17258r = c2130e.k();
                            } else if (iN != 48) {
                                if (!n(c2130e, gVarT, c2132g, iN)) {
                                }
                            } else {
                                this.f17255n |= 32;
                                this.f17260t = c2130e.k();
                            }
                        } else {
                            this.f17255n |= 2;
                            this.f17256p = c2130e.k();
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
                    this.f17254m = c2128c.i();
                    throw th2;
                }
                this.f17254m = c2128c.i();
                m();
                throw th;
            }
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17254m = c2128c.i();
            throw th3;
        }
        this.f17254m = c2128c.i();
        m();
    }
}
