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
public final class Q extends AbstractC2136k {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final Q f17178E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final C1556a f17179F = new C1556a(16);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f17180A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public byte f17181C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f17182D;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17183m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17184n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f17185p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17186q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q f17187r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17188s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17189t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17190u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17191v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17192w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Q f17193x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f17194y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Q f17195z;

    static {
        Q q4 = new Q();
        f17178E = q4;
        q4.q();
    }

    public Q(P p10) {
        super(p10);
        this.f17181C = (byte) -1;
        this.f17182D = -1;
        this.f17183m = p10.l;
    }

    public static P r(Q q4) {
        P pH = P.h();
        pH.i(q4);
        return pH;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17178E;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17181C;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            if (!((O) this.o.get(i7)).b()) {
                this.f17181C = (byte) 0;
                return false;
            }
        }
        if ((this.f17184n & 4) == 4 && !this.f17187r.b()) {
            this.f17181C = (byte) 0;
            return false;
        }
        if ((this.f17184n & 256) == 256 && !this.f17193x.b()) {
            this.f17181C = (byte) 0;
            return false;
        }
        if ((this.f17184n & 1024) == 1024 && !this.f17195z.b()) {
            this.f17181C = (byte) 0;
            return false;
        }
        if (i()) {
            this.f17181C = (byte) 1;
            return true;
        }
        this.f17181C = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17182D;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17184n & 4096) == 4096 ? A2.g.f(1, this.B) : 0;
        for (int i10 = 0; i10 < this.o.size(); i10++) {
            iF += A2.g.h(2, (AbstractC2126a) this.o.get(i10));
        }
        if ((this.f17184n & 1) == 1) {
            iF += A2.g.l(3) + 1;
        }
        if ((this.f17184n & 2) == 2) {
            iF += A2.g.f(4, this.f17186q);
        }
        if ((this.f17184n & 4) == 4) {
            iF += A2.g.h(5, this.f17187r);
        }
        if ((this.f17184n & 16) == 16) {
            iF += A2.g.f(6, this.f17189t);
        }
        if ((this.f17184n & 32) == 32) {
            iF += A2.g.f(7, this.f17190u);
        }
        if ((this.f17184n & 8) == 8) {
            iF += A2.g.f(8, this.f17188s);
        }
        if ((this.f17184n & 64) == 64) {
            iF += A2.g.f(9, this.f17191v);
        }
        if ((this.f17184n & 256) == 256) {
            iF += A2.g.h(10, this.f17193x);
        }
        if ((this.f17184n & 512) == 512) {
            iF += A2.g.f(11, this.f17194y);
        }
        if ((this.f17184n & 128) == 128) {
            iF += A2.g.f(12, this.f17192w);
        }
        if ((this.f17184n & 1024) == 1024) {
            iF += A2.g.h(13, this.f17195z);
        }
        if ((this.f17184n & 2048) == 2048) {
            iF += A2.g.f(14, this.f17180A);
        }
        int size = this.f17183m.size() + j() + iF;
        this.f17182D = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return P.h();
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17184n & 4096) == 4096) {
            gVar.C(1, this.B);
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            gVar.E(2, (AbstractC2126a) this.o.get(i7));
        }
        if ((this.f17184n & 1) == 1) {
            boolean z6 = this.f17185p;
            gVar.N(3, 0);
            gVar.G(z6 ? 1 : 0);
        }
        if ((this.f17184n & 2) == 2) {
            gVar.C(4, this.f17186q);
        }
        if ((this.f17184n & 4) == 4) {
            gVar.E(5, this.f17187r);
        }
        if ((this.f17184n & 16) == 16) {
            gVar.C(6, this.f17189t);
        }
        if ((this.f17184n & 32) == 32) {
            gVar.C(7, this.f17190u);
        }
        if ((this.f17184n & 8) == 8) {
            gVar.C(8, this.f17188s);
        }
        if ((this.f17184n & 64) == 64) {
            gVar.C(9, this.f17191v);
        }
        if ((this.f17184n & 256) == 256) {
            gVar.E(10, this.f17193x);
        }
        if ((this.f17184n & 512) == 512) {
            gVar.C(11, this.f17194y);
        }
        if ((this.f17184n & 128) == 128) {
            gVar.C(12, this.f17192w);
        }
        if ((this.f17184n & 1024) == 1024) {
            gVar.E(13, this.f17195z);
        }
        if ((this.f17184n & 2048) == 2048) {
            gVar.C(14, this.f17180A);
        }
        c1789g.H(200, gVar);
        gVar.H(this.f17183m);
    }

    public final boolean p() {
        return (this.f17184n & 16) == 16;
    }

    public final void q() {
        this.o = Collections.emptyList();
        this.f17185p = false;
        this.f17186q = 0;
        Q q4 = f17178E;
        this.f17187r = q4;
        this.f17188s = 0;
        this.f17189t = 0;
        this.f17190u = 0;
        this.f17191v = 0;
        this.f17192w = 0;
        this.f17193x = q4;
        this.f17194y = 0;
        this.f17195z = q4;
        this.f17180A = 0;
        this.B = 0;
    }

    @Override // p8.AbstractC2126a
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public final P e() {
        return r(this);
    }

    public Q() {
        this.f17181C = (byte) -1;
        this.f17182D = -1;
        this.f17183m = AbstractC2129d.l;
    }

    public Q(C2130e c2130e, C2132g c2132g) {
        this.f17181C = (byte) -1;
        this.f17182D = -1;
        q();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        boolean z10 = false;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    C1556a c1556a = f17179F;
                    P pR = null;
                    switch (iN) {
                        case 0:
                            break;
                        case 8:
                            this.f17184n |= 4096;
                            this.B = c2130e.k();
                            continue;
                        case 18:
                            if (!z10) {
                                this.o = new ArrayList();
                                z10 = true;
                            }
                            this.o.add(c2130e.g(O.f17159t, c2132g));
                            continue;
                        case 24:
                            this.f17184n |= 1;
                            this.f17185p = c2130e.l() != 0;
                            continue;
                        case 32:
                            this.f17184n |= 2;
                            this.f17186q = c2130e.k();
                            continue;
                        case 42:
                            if ((this.f17184n & 4) == 4) {
                                Q q4 = this.f17187r;
                                q4.getClass();
                                pR = r(q4);
                            }
                            Q q10 = (Q) c2130e.g(c1556a, c2132g);
                            this.f17187r = q10;
                            if (pR != null) {
                                pR.i(q10);
                                this.f17187r = pR.g();
                            }
                            this.f17184n |= 4;
                            continue;
                        case 48:
                            this.f17184n |= 16;
                            this.f17189t = c2130e.k();
                            continue;
                        case 56:
                            this.f17184n |= 32;
                            this.f17190u = c2130e.k();
                            continue;
                        case 64:
                            this.f17184n |= 8;
                            this.f17188s = c2130e.k();
                            continue;
                        case 72:
                            this.f17184n |= 64;
                            this.f17191v = c2130e.k();
                            continue;
                        case 82:
                            if ((this.f17184n & 256) == 256) {
                                Q q11 = this.f17193x;
                                q11.getClass();
                                pR = r(q11);
                            }
                            Q q12 = (Q) c2130e.g(c1556a, c2132g);
                            this.f17193x = q12;
                            if (pR != null) {
                                pR.i(q12);
                                this.f17193x = pR.g();
                            }
                            this.f17184n |= 256;
                            continue;
                        case 88:
                            this.f17184n |= 512;
                            this.f17194y = c2130e.k();
                            continue;
                        case 96:
                            this.f17184n |= 128;
                            this.f17192w = c2130e.k();
                            continue;
                        case 106:
                            if ((this.f17184n & 1024) == 1024) {
                                Q q13 = this.f17195z;
                                q13.getClass();
                                pR = r(q13);
                            }
                            Q q14 = (Q) c2130e.g(c1556a, c2132g);
                            this.f17195z = q14;
                            if (pR != null) {
                                pR.i(q14);
                                this.f17195z = pR.g();
                            }
                            this.f17184n |= 1024;
                            continue;
                        case 112:
                            this.f17184n |= 2048;
                            this.f17180A = c2130e.k();
                            continue;
                        default:
                            if (!n(c2130e, gVarT, c2132g, iN)) {
                            }
                            break;
                    }
                    z6 = true;
                } catch (Throwable th) {
                    if (z10) {
                        this.o = Collections.unmodifiableList(this.o);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f17183m = c2128c.i();
                        throw th2;
                    }
                    this.f17183m = c2128c.i();
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
        if (z10) {
            this.o = Collections.unmodifiableList(this.o);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.f17183m = c2128c.i();
            throw th3;
        }
        this.f17183m = c2128c.i();
        m();
    }
}
