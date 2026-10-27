package j8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public final class C extends AbstractC2136k {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C f17075v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C1556a f17076w = new C1556a(10);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17077m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17078n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f17079p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f17080q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public X f17081r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public e0 f17082s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public byte f17083t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17084u;

    static {
        C c = new C();
        f17075v = c;
        c.o = Collections.emptyList();
        c.f17079p = Collections.emptyList();
        c.f17080q = Collections.emptyList();
        c.f17081r = X.f17240r;
        c.f17082s = e0.f17330p;
    }

    public C(B b10) {
        super(b10);
        this.f17083t = (byte) -1;
        this.f17084u = -1;
        this.f17077m = b10.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17075v;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17083t;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            if (!((C1579y) this.o.get(i7)).b()) {
                this.f17083t = (byte) 0;
                return false;
            }
        }
        for (int i10 = 0; i10 < this.f17079p.size(); i10++) {
            if (!((G) this.f17079p.get(i10)).b()) {
                this.f17083t = (byte) 0;
                return false;
            }
        }
        for (int i11 = 0; i11 < this.f17080q.size(); i11++) {
            if (!((T) this.f17080q.get(i11)).b()) {
                this.f17083t = (byte) 0;
                return false;
            }
        }
        if ((this.f17078n & 1) == 1 && !this.f17081r.b()) {
            this.f17083t = (byte) 0;
            return false;
        }
        if (i()) {
            this.f17083t = (byte) 1;
            return true;
        }
        this.f17083t = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17084u;
        if (i7 != -1) {
            return i7;
        }
        int iH = 0;
        for (int i10 = 0; i10 < this.o.size(); i10++) {
            iH += A2.g.h(3, (AbstractC2126a) this.o.get(i10));
        }
        for (int i11 = 0; i11 < this.f17079p.size(); i11++) {
            iH += A2.g.h(4, (AbstractC2126a) this.f17079p.get(i11));
        }
        for (int i12 = 0; i12 < this.f17080q.size(); i12++) {
            iH += A2.g.h(5, (AbstractC2126a) this.f17080q.get(i12));
        }
        if ((this.f17078n & 1) == 1) {
            iH += A2.g.h(30, this.f17081r);
        }
        if ((this.f17078n & 2) == 2) {
            iH += A2.g.h(32, this.f17082s);
        }
        int size = this.f17077m.size() + j() + iH;
        this.f17084u = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return B.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        B bH = B.h();
        bH.i(this);
        return bH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            gVar.E(3, (AbstractC2126a) this.o.get(i7));
        }
        for (int i10 = 0; i10 < this.f17079p.size(); i10++) {
            gVar.E(4, (AbstractC2126a) this.f17079p.get(i10));
        }
        for (int i11 = 0; i11 < this.f17080q.size(); i11++) {
            gVar.E(5, (AbstractC2126a) this.f17080q.get(i11));
        }
        if ((this.f17078n & 1) == 1) {
            gVar.E(30, this.f17081r);
        }
        if ((this.f17078n & 2) == 2) {
            gVar.E(32, this.f17082s);
        }
        c1789g.H(200, gVar);
        gVar.H(this.f17077m);
    }

    public C() {
        this.f17083t = (byte) -1;
        this.f17084u = -1;
        this.f17077m = AbstractC2129d.l;
    }

    public C(C2130e c2130e, C2132g c2132g) {
        this.f17083t = (byte) -1;
        this.f17084u = -1;
        this.o = Collections.emptyList();
        this.f17079p = Collections.emptyList();
        this.f17080q = Collections.emptyList();
        this.f17081r = X.f17240r;
        this.f17082s = e0.f17330p;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        int i7 = 0;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 26) {
                            int i10 = (i7 == true ? 1 : 0) & 1;
                            i7 = i7;
                            if (i10 != 1) {
                                this.o = new ArrayList();
                                i7 = (i7 == true ? 1 : 0) | 1;
                            }
                            this.o.add(c2130e.g(C1579y.f17483G, c2132g));
                        } else if (iN == 34) {
                            int i11 = (i7 == true ? 1 : 0) & 2;
                            i7 = i7;
                            if (i11 != 2) {
                                this.f17079p = new ArrayList();
                                i7 = (i7 == true ? 1 : 0) | 2;
                            }
                            this.f17079p.add(c2130e.g(G.f17112G, c2132g));
                        } else if (iN != 42) {
                            C1568m c1568m = null;
                            C1561f c1561fI = null;
                            if (iN == 242) {
                                if ((this.f17078n & 1) == 1) {
                                    X x8 = this.f17081r;
                                    x8.getClass();
                                    c1561fI = X.i(x8);
                                }
                                X x9 = (X) c2130e.g(X.f17241s, c2132g);
                                this.f17081r = x9;
                                if (c1561fI != null) {
                                    c1561fI.l(x9);
                                    this.f17081r = c1561fI.h();
                                }
                                this.f17078n |= 1;
                            } else if (iN != 258) {
                                if (!n(c2130e, gVarT, c2132g, iN)) {
                                }
                            } else {
                                if ((this.f17078n & 2) == 2) {
                                    e0 e0Var = this.f17082s;
                                    e0Var.getClass();
                                    c1568m = new C1568m(2);
                                    c1568m.o = Collections.emptyList();
                                    c1568m.m(e0Var);
                                }
                                e0 e0Var2 = (e0) c2130e.g(e0.f17331q, c2132g);
                                this.f17082s = e0Var2;
                                if (c1568m != null) {
                                    c1568m.m(e0Var2);
                                    this.f17082s = c1568m.i();
                                }
                                this.f17078n |= 2;
                            }
                        } else {
                            int i12 = (i7 == true ? 1 : 0) & 4;
                            i7 = i7;
                            if (i12 != 4) {
                                this.f17080q = new ArrayList();
                                i7 = (i7 == true ? 1 : 0) | 4;
                            }
                            this.f17080q.add(c2130e.g(T.f17205A, c2132g));
                        }
                    }
                    z6 = true;
                } catch (Throwable th) {
                    if (((i7 == true ? 1 : 0) & 1) == 1) {
                        this.o = Collections.unmodifiableList(this.o);
                    }
                    if (((i7 == true ? 1 : 0) & 2) == 2) {
                        this.f17079p = Collections.unmodifiableList(this.f17079p);
                    }
                    if (((i7 == true ? 1 : 0) & 4) == 4) {
                        this.f17080q = Collections.unmodifiableList(this.f17080q);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f17077m = c2128c.i();
                        throw th2;
                    }
                    this.f17077m = c2128c.i();
                    m();
                    throw th;
                }
            } catch (C2142q e10) {
                e10.l = this;
                throw e10;
            } catch (IOException e11) {
                C2142q c2142q = new C2142q(e11.getMessage());
                c2142q.l = this;
                throw c2142q;
            }
        }
        if (((i7 == true ? 1 : 0) & 1) == 1) {
            this.o = Collections.unmodifiableList(this.o);
        }
        if (((i7 == true ? 1 : 0) & 2) == 2) {
            this.f17079p = Collections.unmodifiableList(this.f17079p);
        }
        if (((i7 == true ? 1 : 0) & 4) == 4) {
            this.f17080q = Collections.unmodifiableList(this.f17080q);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17077m = c2128c.i();
            throw th3;
        }
        this.f17077m = c2128c.i();
        m();
    }
}
