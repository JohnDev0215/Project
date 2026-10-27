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

/* JADX INFO: renamed from: j8.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1567l extends AbstractC2136k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C1567l f17409t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C1556a f17410u = new C1556a(4);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17411m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17412n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f17413p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f17414q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public byte f17415r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17416s;

    static {
        C1567l c1567l = new C1567l();
        f17409t = c1567l;
        c1567l.o = 6;
        c1567l.f17413p = Collections.emptyList();
        c1567l.f17414q = Collections.emptyList();
    }

    public C1567l(C1566k c1566k) {
        super(c1566k);
        this.f17415r = (byte) -1;
        this.f17416s = -1;
        this.f17411m = c1566k.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17409t;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17415r;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i7 = 0; i7 < this.f17413p.size(); i7++) {
            if (!((Z) this.f17413p.get(i7)).b()) {
                this.f17415r = (byte) 0;
                return false;
            }
        }
        if (i()) {
            this.f17415r = (byte) 1;
            return true;
        }
        this.f17415r = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17416s;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17412n & 1) == 1 ? A2.g.f(1, this.o) : 0;
        for (int i10 = 0; i10 < this.f17413p.size(); i10++) {
            iF += A2.g.h(2, (AbstractC2126a) this.f17413p.get(i10));
        }
        int iG = 0;
        for (int i11 = 0; i11 < this.f17414q.size(); i11++) {
            iG += A2.g.g(((Integer) this.f17414q.get(i11)).intValue());
        }
        int size = this.f17411m.size() + j() + (this.f17414q.size() * 2) + iF + iG;
        this.f17416s = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1566k.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1566k c1566kH = C1566k.h();
        c1566kH.i(this);
        return c1566kH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17412n & 1) == 1) {
            gVar.C(1, this.o);
        }
        for (int i7 = 0; i7 < this.f17413p.size(); i7++) {
            gVar.E(2, (AbstractC2126a) this.f17413p.get(i7));
        }
        for (int i10 = 0; i10 < this.f17414q.size(); i10++) {
            gVar.C(31, ((Integer) this.f17414q.get(i10)).intValue());
        }
        c1789g.H(19000, gVar);
        gVar.H(this.f17411m);
    }

    public C1567l() {
        this.f17415r = (byte) -1;
        this.f17416s = -1;
        this.f17411m = AbstractC2129d.l;
    }

    public C1567l(C2130e c2130e, C2132g c2132g) {
        this.f17415r = (byte) -1;
        this.f17416s = -1;
        this.o = 6;
        this.f17413p = Collections.emptyList();
        this.f17414q = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        int i7 = 0;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f17412n |= 1;
                            this.o = c2130e.k();
                        } else if (iN == 18) {
                            if ((i7 & 2) != 2) {
                                this.f17413p = new ArrayList();
                                i7 |= 2;
                            }
                            this.f17413p.add(c2130e.g(Z.f17253x, c2132g));
                        } else if (iN == 248) {
                            if ((i7 & 4) != 4) {
                                this.f17414q = new ArrayList();
                                i7 |= 4;
                            }
                            this.f17414q.add(Integer.valueOf(c2130e.k()));
                        } else if (iN != 250) {
                            if (!n(c2130e, gVarT, c2132g, iN)) {
                            }
                        } else {
                            int iD = c2130e.d(c2130e.k());
                            if ((i7 & 4) != 4 && c2130e.b() > 0) {
                                this.f17414q = new ArrayList();
                                i7 |= 4;
                            }
                            while (c2130e.b() > 0) {
                                this.f17414q.add(Integer.valueOf(c2130e.k()));
                            }
                            c2130e.c(iD);
                        }
                    }
                    z6 = true;
                } catch (Throwable th) {
                    if ((i7 & 2) == 2) {
                        this.f17413p = Collections.unmodifiableList(this.f17413p);
                    }
                    if ((i7 & 4) == 4) {
                        this.f17414q = Collections.unmodifiableList(this.f17414q);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f17411m = c2128c.i();
                        throw th2;
                    }
                    this.f17411m = c2128c.i();
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
        if ((i7 & 2) == 2) {
            this.f17413p = Collections.unmodifiableList(this.f17413p);
        }
        if ((i7 & 4) == 4) {
            this.f17414q = Collections.unmodifiableList(this.f17414q);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17411m = c2128c.i();
            throw th3;
        }
        this.f17411m = c2128c.i();
        m();
    }
}
