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
public final class W extends AbstractC2136k {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final W f17228x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C1556a f17229y = new C1556a(19);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17230m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17231n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17232p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f17233q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public V f17234r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17235s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17236t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17237u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public byte f17238v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17239w;

    static {
        W w10 = new W();
        f17228x = w10;
        w10.o = 0;
        w10.f17232p = 0;
        w10.f17233q = false;
        w10.f17234r = V.INV;
        w10.f17235s = Collections.emptyList();
        w10.f17236t = Collections.emptyList();
    }

    public W(U u4) {
        super(u4);
        this.f17237u = -1;
        this.f17238v = (byte) -1;
        this.f17239w = -1;
        this.f17230m = u4.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17228x;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17238v;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i7 = this.f17231n;
        if ((i7 & 1) != 1) {
            this.f17238v = (byte) 0;
            return false;
        }
        if ((i7 & 2) != 2) {
            this.f17238v = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f17235s.size(); i10++) {
            if (!((Q) this.f17235s.get(i10)).b()) {
                this.f17238v = (byte) 0;
                return false;
            }
        }
        if (i()) {
            this.f17238v = (byte) 1;
            return true;
        }
        this.f17238v = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17239w;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17231n & 1) == 1 ? A2.g.f(1, this.o) : 0;
        if ((this.f17231n & 2) == 2) {
            iF += A2.g.f(2, this.f17232p);
        }
        if ((this.f17231n & 4) == 4) {
            iF += A2.g.l(3) + 1;
        }
        if ((this.f17231n & 8) == 8) {
            iF += A2.g.e(4, this.f17234r.l);
        }
        for (int i10 = 0; i10 < this.f17235s.size(); i10++) {
            iF += A2.g.h(5, (AbstractC2126a) this.f17235s.get(i10));
        }
        int iG = 0;
        for (int i11 = 0; i11 < this.f17236t.size(); i11++) {
            iG += A2.g.g(((Integer) this.f17236t.get(i11)).intValue());
        }
        int iG2 = iF + iG;
        if (!this.f17236t.isEmpty()) {
            iG2 = iG2 + 1 + A2.g.g(iG);
        }
        this.f17237u = iG;
        int size = this.f17230m.size() + j() + iG2;
        this.f17239w = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return U.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        U uH = U.h();
        uH.i(this);
        return uH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17231n & 1) == 1) {
            gVar.C(1, this.o);
        }
        if ((this.f17231n & 2) == 2) {
            gVar.C(2, this.f17232p);
        }
        if ((this.f17231n & 4) == 4) {
            boolean z6 = this.f17233q;
            gVar.N(3, 0);
            gVar.G(z6 ? 1 : 0);
        }
        if ((this.f17231n & 8) == 8) {
            gVar.B(4, this.f17234r.l);
        }
        for (int i7 = 0; i7 < this.f17235s.size(); i7++) {
            gVar.E(5, (AbstractC2126a) this.f17235s.get(i7));
        }
        if (this.f17236t.size() > 0) {
            gVar.L(50);
            gVar.L(this.f17237u);
        }
        for (int i10 = 0; i10 < this.f17236t.size(); i10++) {
            gVar.D(((Integer) this.f17236t.get(i10)).intValue());
        }
        c1789g.H(1000, gVar);
        gVar.H(this.f17230m);
    }

    public W() {
        this.f17237u = -1;
        this.f17238v = (byte) -1;
        this.f17239w = -1;
        this.f17230m = AbstractC2129d.l;
    }

    public W(C2130e c2130e, C2132g c2132g) {
        V v10;
        this.f17237u = -1;
        this.f17238v = (byte) -1;
        this.f17239w = -1;
        this.o = 0;
        this.f17232p = 0;
        this.f17233q = false;
        V v11 = V.INV;
        this.f17234r = v11;
        this.f17235s = Collections.emptyList();
        this.f17236t = Collections.emptyList();
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
                            this.f17231n |= 1;
                            this.o = c2130e.k();
                        } else if (iN == 16) {
                            this.f17231n |= 2;
                            this.f17232p = c2130e.k();
                        } else if (iN == 24) {
                            this.f17231n |= 4;
                            this.f17233q = c2130e.l() != 0;
                        } else if (iN == 32) {
                            int iK = c2130e.k();
                            if (iK == 0) {
                                v10 = V.IN;
                            } else if (iK != 1) {
                                v10 = iK != 2 ? null : v11;
                            } else {
                                v10 = V.OUT;
                            }
                            if (v10 == null) {
                                gVarT.L(iN);
                                gVarT.L(iK);
                            } else {
                                this.f17231n |= 8;
                                this.f17234r = v10;
                            }
                        } else if (iN == 42) {
                            if ((i7 & 16) != 16) {
                                this.f17235s = new ArrayList();
                                i7 |= 16;
                            }
                            this.f17235s.add(c2130e.g(Q.f17179F, c2132g));
                        } else if (iN == 48) {
                            if ((i7 & 32) != 32) {
                                this.f17236t = new ArrayList();
                                i7 |= 32;
                            }
                            this.f17236t.add(Integer.valueOf(c2130e.k()));
                        } else if (iN != 50) {
                            if (!n(c2130e, gVarT, c2132g, iN)) {
                            }
                        } else {
                            int iD = c2130e.d(c2130e.k());
                            if ((i7 & 32) != 32 && c2130e.b() > 0) {
                                this.f17236t = new ArrayList();
                                i7 |= 32;
                            }
                            while (c2130e.b() > 0) {
                                this.f17236t.add(Integer.valueOf(c2130e.k()));
                            }
                            c2130e.c(iD);
                        }
                    }
                    z6 = true;
                } catch (Throwable th) {
                    if ((i7 & 16) == 16) {
                        this.f17235s = Collections.unmodifiableList(this.f17235s);
                    }
                    if ((i7 & 32) == 32) {
                        this.f17236t = Collections.unmodifiableList(this.f17236t);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f17230m = c2128c.i();
                        throw th2;
                    }
                    this.f17230m = c2128c.i();
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
        if ((i7 & 16) == 16) {
            this.f17235s = Collections.unmodifiableList(this.f17235s);
        }
        if ((i7 & 32) == 32) {
            this.f17236t = Collections.unmodifiableList(this.f17236t);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17230m = c2128c.i();
            throw th3;
        }
        this.f17230m = c2128c.i();
        m();
    }
}
