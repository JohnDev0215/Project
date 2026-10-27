package j8;

import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class O extends AbstractC2139n {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final O f17158s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C1556a f17159t = new C1556a(17);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17160m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public N f17161n;
    public Q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17162p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public byte f17163q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17164r;

    static {
        O o = new O();
        f17158s = o;
        o.f17161n = N.INV;
        o.o = Q.f17178E;
        o.f17162p = 0;
    }

    public O() {
        this.f17163q = (byte) -1;
        this.f17164r = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17163q;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17160m & 2) != 2 || this.o.b()) {
            this.f17163q = (byte) 1;
            return true;
        }
        this.f17163q = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17164r;
        if (i7 != -1) {
            return i7;
        }
        int iE = (this.f17160m & 1) == 1 ? A2.g.e(1, this.f17161n.l) : 0;
        if ((this.f17160m & 2) == 2) {
            iE += A2.g.h(2, this.o);
        }
        if ((this.f17160m & 4) == 4) {
            iE += A2.g.f(3, this.f17162p);
        }
        int size = this.l.size() + iE;
        this.f17164r = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return M.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        M mG = M.g();
        mG.h(this);
        return mG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17160m & 1) == 1) {
            gVar.B(1, this.f17161n.l);
        }
        if ((this.f17160m & 2) == 2) {
            gVar.E(2, this.o);
        }
        if ((this.f17160m & 4) == 4) {
            gVar.C(3, this.f17162p);
        }
        gVar.H(this.l);
    }

    public O(C2130e c2130e, C2132g c2132g) {
        this.f17163q = (byte) -1;
        this.f17164r = -1;
        N n10 = N.INV;
        this.f17161n = n10;
        this.o = Q.f17178E;
        boolean z6 = false;
        this.f17162p = 0;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        P pR = null;
                        N n11 = null;
                        if (iN == 8) {
                            int iK = c2130e.k();
                            if (iK == 0) {
                                n11 = N.IN;
                            } else if (iK == 1) {
                                n11 = N.OUT;
                            } else if (iK == 2) {
                                n11 = n10;
                            } else if (iK == 3) {
                                n11 = N.STAR;
                            }
                            if (n11 == null) {
                                gVarT.L(iN);
                                gVarT.L(iK);
                            } else {
                                this.f17160m |= 1;
                                this.f17161n = n11;
                            }
                        } else if (iN == 18) {
                            if ((this.f17160m & 2) == 2) {
                                Q q4 = this.o;
                                q4.getClass();
                                pR = Q.r(q4);
                            }
                            Q q10 = (Q) c2130e.g(Q.f17179F, c2132g);
                            this.o = q10;
                            if (pR != null) {
                                pR.i(q10);
                                this.o = pR.g();
                            }
                            this.f17160m |= 2;
                        } else if (iN != 24) {
                            if (!c2130e.q(iN, gVarT)) {
                            }
                        } else {
                            this.f17160m |= 4;
                            this.f17162p = c2130e.k();
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

    public O(M m10) {
        this.f17163q = (byte) -1;
        this.f17164r = -1;
        this.l = m10.l;
    }
}
