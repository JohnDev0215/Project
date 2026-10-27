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
import p8.C2143r;

/* JADX INFO: loaded from: classes.dex */
public final class E extends AbstractC2136k {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final E f17089u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C1556a f17090v = new C1556a(11);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17091m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17092n;
    public L o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public K f17093p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C f17094q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f17095r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public byte f17096s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17097t;

    static {
        E e10 = new E();
        f17089u = e10;
        e10.o = L.f17147p;
        e10.f17093p = K.f17143p;
        e10.f17094q = C.f17075v;
        e10.f17095r = Collections.emptyList();
    }

    public E(D d) {
        super(d);
        this.f17096s = (byte) -1;
        this.f17097t = -1;
        this.f17091m = d.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17089u;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17096s;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17092n & 2) == 2 && !this.f17093p.b()) {
            this.f17096s = (byte) 0;
            return false;
        }
        if ((this.f17092n & 4) == 4 && !this.f17094q.b()) {
            this.f17096s = (byte) 0;
            return false;
        }
        for (int i7 = 0; i7 < this.f17095r.size(); i7++) {
            if (!((C1565j) this.f17095r.get(i7)).b()) {
                this.f17096s = (byte) 0;
                return false;
            }
        }
        if (i()) {
            this.f17096s = (byte) 1;
            return true;
        }
        this.f17096s = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17097t;
        if (i7 != -1) {
            return i7;
        }
        int iH = (this.f17092n & 1) == 1 ? A2.g.h(1, this.o) : 0;
        if ((this.f17092n & 2) == 2) {
            iH += A2.g.h(2, this.f17093p);
        }
        if ((this.f17092n & 4) == 4) {
            iH += A2.g.h(3, this.f17094q);
        }
        for (int i10 = 0; i10 < this.f17095r.size(); i10++) {
            iH += A2.g.h(4, (AbstractC2126a) this.f17095r.get(i10));
        }
        int size = this.f17091m.size() + j() + iH;
        this.f17097t = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return D.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        D dH = D.h();
        dH.i(this);
        return dH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17092n & 1) == 1) {
            gVar.E(1, this.o);
        }
        if ((this.f17092n & 2) == 2) {
            gVar.E(2, this.f17093p);
        }
        if ((this.f17092n & 4) == 4) {
            gVar.E(3, this.f17094q);
        }
        for (int i7 = 0; i7 < this.f17095r.size(); i7++) {
            gVar.E(4, (AbstractC2126a) this.f17095r.get(i7));
        }
        c1789g.H(200, gVar);
        gVar.H(this.f17091m);
    }

    public E() {
        this.f17096s = (byte) -1;
        this.f17097t = -1;
        this.f17091m = AbstractC2129d.l;
    }

    public E(C2130e c2130e, C2132g c2132g) {
        this.f17096s = (byte) -1;
        this.f17097t = -1;
        this.o = L.f17147p;
        this.f17093p = K.f17143p;
        this.f17094q = C.f17075v;
        this.f17095r = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        char c = 0;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        B bH = null;
                        C1568m c1568m = null;
                        C1568m c1568m2 = null;
                        if (iN == 10) {
                            if ((this.f17092n & 1) == 1) {
                                L l = this.o;
                                l.getClass();
                                c1568m = new C1568m(3);
                                c1568m.o = C2143r.f20990m;
                                c1568m.l(l);
                            }
                            L l10 = (L) c2130e.g(L.f17148q, c2132g);
                            this.o = l10;
                            if (c1568m != null) {
                                c1568m.l(l10);
                                this.o = c1568m.h();
                            }
                            this.f17092n |= 1;
                        } else if (iN == 18) {
                            if ((this.f17092n & 2) == 2) {
                                K k10 = this.f17093p;
                                k10.getClass();
                                c1568m2 = new C1568m(1);
                                c1568m2.o = Collections.emptyList();
                                c1568m2.k(k10);
                            }
                            K k11 = (K) c2130e.g(K.f17144q, c2132g);
                            this.f17093p = k11;
                            if (c1568m2 != null) {
                                c1568m2.k(k11);
                                this.f17093p = c1568m2.g();
                            }
                            this.f17092n |= 2;
                        } else if (iN == 26) {
                            if ((this.f17092n & 4) == 4) {
                                C c10 = this.f17094q;
                                c10.getClass();
                                bH = B.h();
                                bH.i(c10);
                            }
                            C c11 = (C) c2130e.g(C.f17076w, c2132g);
                            this.f17094q = c11;
                            if (bH != null) {
                                bH.i(c11);
                                this.f17094q = bH.g();
                            }
                            this.f17092n |= 4;
                        } else if (iN != 34) {
                            if (!n(c2130e, gVarT, c2132g, iN)) {
                            }
                        } else {
                            int i7 = (c == true ? 1 : 0) & '\b';
                            c = c;
                            if (i7 != 8) {
                                this.f17095r = new ArrayList();
                                c = '\b';
                            }
                            this.f17095r.add(c2130e.g(C1565j.f17373V, c2132g));
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
                if (((c == true ? 1 : 0) & '\b') == 8) {
                    this.f17095r = Collections.unmodifiableList(this.f17095r);
                }
                try {
                    gVarT.n();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.f17091m = c2128c.i();
                    throw th2;
                }
                this.f17091m = c2128c.i();
                m();
                throw th;
            }
        }
        if (((c == true ? 1 : 0) & '\b') == 8) {
            this.f17095r = Collections.unmodifiableList(this.f17095r);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17091m = c2128c.i();
            throw th3;
        }
        this.f17091m = c2128c.i();
        m();
    }
}
