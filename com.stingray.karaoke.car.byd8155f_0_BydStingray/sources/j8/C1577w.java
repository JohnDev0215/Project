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

/* JADX INFO: renamed from: j8.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1577w extends AbstractC2139n {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C1577w f17458w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C1556a f17459x = new C1556a(8);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17461n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public EnumC1576v f17462p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Q f17463q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17464r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17465s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17466t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public byte f17467u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17468v;

    static {
        C1577w c1577w = new C1577w();
        f17458w = c1577w;
        c1577w.f17461n = 0;
        c1577w.o = 0;
        c1577w.f17462p = EnumC1576v.TRUE;
        c1577w.f17463q = Q.f17178E;
        c1577w.f17464r = 0;
        c1577w.f17465s = Collections.emptyList();
        c1577w.f17466t = Collections.emptyList();
    }

    public C1577w() {
        this.f17467u = (byte) -1;
        this.f17468v = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17467u;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17460m & 8) == 8 && !this.f17463q.b()) {
            this.f17467u = (byte) 0;
            return false;
        }
        for (int i7 = 0; i7 < this.f17465s.size(); i7++) {
            if (!((C1577w) this.f17465s.get(i7)).b()) {
                this.f17467u = (byte) 0;
                return false;
            }
        }
        for (int i10 = 0; i10 < this.f17466t.size(); i10++) {
            if (!((C1577w) this.f17466t.get(i10)).b()) {
                this.f17467u = (byte) 0;
                return false;
            }
        }
        this.f17467u = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17468v;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17460m & 1) == 1 ? A2.g.f(1, this.f17461n) : 0;
        if ((this.f17460m & 2) == 2) {
            iF += A2.g.f(2, this.o);
        }
        if ((this.f17460m & 4) == 4) {
            iF += A2.g.e(3, this.f17462p.l);
        }
        if ((this.f17460m & 8) == 8) {
            iF += A2.g.h(4, this.f17463q);
        }
        if ((this.f17460m & 16) == 16) {
            iF += A2.g.f(5, this.f17464r);
        }
        for (int i10 = 0; i10 < this.f17465s.size(); i10++) {
            iF += A2.g.h(6, (AbstractC2126a) this.f17465s.get(i10));
        }
        for (int i11 = 0; i11 < this.f17466t.size(); i11++) {
            iF += A2.g.h(7, (AbstractC2126a) this.f17466t.get(i11));
        }
        int size = this.l.size() + iF;
        this.f17468v = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1575u.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1575u c1575uG = C1575u.g();
        c1575uG.h(this);
        return c1575uG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17460m & 1) == 1) {
            gVar.C(1, this.f17461n);
        }
        if ((this.f17460m & 2) == 2) {
            gVar.C(2, this.o);
        }
        if ((this.f17460m & 4) == 4) {
            gVar.B(3, this.f17462p.l);
        }
        if ((this.f17460m & 8) == 8) {
            gVar.E(4, this.f17463q);
        }
        if ((this.f17460m & 16) == 16) {
            gVar.C(5, this.f17464r);
        }
        for (int i7 = 0; i7 < this.f17465s.size(); i7++) {
            gVar.E(6, (AbstractC2126a) this.f17465s.get(i7));
        }
        for (int i10 = 0; i10 < this.f17466t.size(); i10++) {
            gVar.E(7, (AbstractC2126a) this.f17466t.get(i10));
        }
        gVar.H(this.l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C1577w(C2130e c2130e, C2132g c2132g) {
        EnumC1576v enumC1576v;
        this.f17467u = (byte) -1;
        this.f17468v = -1;
        boolean z6 = false;
        this.f17461n = 0;
        this.o = 0;
        EnumC1576v enumC1576v2 = EnumC1576v.TRUE;
        this.f17462p = enumC1576v2;
        this.f17463q = Q.f17178E;
        this.f17464r = 0;
        this.f17465s = Collections.emptyList();
        this.f17466t = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        int i7 = 0;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f17460m |= 1;
                            this.f17461n = c2130e.k();
                        } else if (iN != 16) {
                            Object objR = null;
                            if (iN == 24) {
                                int iK = c2130e.k();
                                if (iK != 0) {
                                    if (iK == 1) {
                                        objR = EnumC1576v.FALSE;
                                    } else if (iK == 2) {
                                        objR = EnumC1576v.NULL;
                                    }
                                    enumC1576v = objR;
                                } else {
                                    enumC1576v = enumC1576v2;
                                }
                                if (enumC1576v == 0) {
                                    gVarT.L(iN);
                                    gVarT.L(iK);
                                } else {
                                    this.f17460m |= 4;
                                    this.f17462p = enumC1576v;
                                }
                            } else if (iN == 34) {
                                if ((this.f17460m & 8) == 8) {
                                    Q q4 = this.f17463q;
                                    q4.getClass();
                                    objR = Q.r(q4);
                                }
                                P p10 = objR;
                                Q q10 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17463q = q10;
                                if (p10 != 0) {
                                    p10.i(q10);
                                    this.f17463q = p10.g();
                                }
                                this.f17460m |= 8;
                            } else if (iN != 40) {
                                C1556a c1556a = f17459x;
                                if (iN == 50) {
                                    if ((i7 & 32) != 32) {
                                        this.f17465s = new ArrayList();
                                        i7 |= 32;
                                    }
                                    this.f17465s.add(c2130e.g(c1556a, c2132g));
                                } else if (iN != 58) {
                                    if (!c2130e.q(iN, gVarT)) {
                                    }
                                } else {
                                    if ((i7 & 64) != 64) {
                                        this.f17466t = new ArrayList();
                                        i7 |= 64;
                                    }
                                    this.f17466t.add(c2130e.g(c1556a, c2132g));
                                }
                            } else {
                                this.f17460m |= 16;
                                this.f17464r = c2130e.k();
                            }
                        } else {
                            this.f17460m |= 2;
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
                if ((i7 & 32) == 32) {
                    this.f17465s = Collections.unmodifiableList(this.f17465s);
                }
                if ((i7 & 64) == 64) {
                    this.f17466t = Collections.unmodifiableList(this.f17466t);
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
        if ((i7 & 32) == 32) {
            this.f17465s = Collections.unmodifiableList(this.f17465s);
        }
        if ((i7 & 64) == 64) {
            this.f17466t = Collections.unmodifiableList(this.f17466t);
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

    public C1577w(C1575u c1575u) {
        this.f17467u = (byte) -1;
        this.f17468v = -1;
        this.l = c1575u.l;
    }
}
