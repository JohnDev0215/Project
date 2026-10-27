package j8;

import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends AbstractC2139n {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final d0 f17314v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C1556a f17315w = new C1556a(22);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17316m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17317n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public b0 f17318p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17319q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17320r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public c0 f17321s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public byte f17322t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17323u;

    static {
        d0 d0Var = new d0();
        f17314v = d0Var;
        d0Var.f17317n = 0;
        d0Var.o = 0;
        d0Var.f17318p = b0.ERROR;
        d0Var.f17319q = 0;
        d0Var.f17320r = 0;
        d0Var.f17321s = c0.LANGUAGE_VERSION;
    }

    public d0() {
        this.f17322t = (byte) -1;
        this.f17323u = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17322t;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f17322t = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17323u;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17316m & 1) == 1 ? A2.g.f(1, this.f17317n) : 0;
        if ((this.f17316m & 2) == 2) {
            iF += A2.g.f(2, this.o);
        }
        if ((this.f17316m & 4) == 4) {
            iF += A2.g.e(3, this.f17318p.l);
        }
        if ((this.f17316m & 8) == 8) {
            iF += A2.g.f(4, this.f17319q);
        }
        if ((this.f17316m & 16) == 16) {
            iF += A2.g.f(5, this.f17320r);
        }
        if ((this.f17316m & 32) == 32) {
            iF += A2.g.e(6, this.f17321s.l);
        }
        int size = this.l.size() + iF;
        this.f17323u = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return a0.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        a0 a0VarG = a0.g();
        a0VarG.h(this);
        return a0VarG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17316m & 1) == 1) {
            gVar.C(1, this.f17317n);
        }
        if ((this.f17316m & 2) == 2) {
            gVar.C(2, this.o);
        }
        if ((this.f17316m & 4) == 4) {
            gVar.B(3, this.f17318p.l);
        }
        if ((this.f17316m & 8) == 8) {
            gVar.C(4, this.f17319q);
        }
        if ((this.f17316m & 16) == 16) {
            gVar.C(5, this.f17320r);
        }
        if ((this.f17316m & 32) == 32) {
            gVar.B(6, this.f17321s.l);
        }
        gVar.H(this.l);
    }

    public d0(C2130e c2130e) {
        this.f17322t = (byte) -1;
        this.f17323u = -1;
        boolean z6 = false;
        this.f17317n = 0;
        this.o = 0;
        b0 b0Var = b0.ERROR;
        this.f17318p = b0Var;
        this.f17319q = 0;
        this.f17320r = 0;
        c0 c0Var = c0.LANGUAGE_VERSION;
        this.f17321s = c0Var;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f17316m |= 1;
                            this.f17317n = c2130e.k();
                        } else if (iN != 16) {
                            c0 c0Var2 = null;
                            b0 b0Var2 = null;
                            if (iN == 24) {
                                int iK = c2130e.k();
                                if (iK == 0) {
                                    b0Var2 = b0.WARNING;
                                } else if (iK == 1) {
                                    b0Var2 = b0Var;
                                } else if (iK == 2) {
                                    b0Var2 = b0.HIDDEN;
                                }
                                if (b0Var2 == null) {
                                    gVarT.L(iN);
                                    gVarT.L(iK);
                                } else {
                                    this.f17316m |= 4;
                                    this.f17318p = b0Var2;
                                }
                            } else if (iN == 32) {
                                this.f17316m |= 8;
                                this.f17319q = c2130e.k();
                            } else if (iN == 40) {
                                this.f17316m |= 16;
                                this.f17320r = c2130e.k();
                            } else if (iN != 48) {
                                if (!c2130e.q(iN, gVarT)) {
                                }
                            } else {
                                int iK2 = c2130e.k();
                                if (iK2 == 0) {
                                    c0Var2 = c0Var;
                                } else if (iK2 == 1) {
                                    c0Var2 = c0.COMPILER_VERSION;
                                } else if (iK2 == 2) {
                                    c0Var2 = c0.API_VERSION;
                                }
                                if (c0Var2 == null) {
                                    gVarT.L(iN);
                                    gVarT.L(iK2);
                                } else {
                                    this.f17316m |= 32;
                                    this.f17321s = c0Var2;
                                }
                            }
                        } else {
                            this.f17316m |= 2;
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

    public d0(a0 a0Var) {
        this.f17322t = (byte) -1;
        this.f17323u = -1;
        this.l = a0Var.l;
    }
}
