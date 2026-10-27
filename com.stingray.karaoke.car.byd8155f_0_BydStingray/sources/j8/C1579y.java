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

/* JADX INFO: renamed from: j8.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1579y extends AbstractC2136k {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final C1579y f17482F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final C1556a f17483G = new C1556a(9);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public X f17484A;
    public List B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C1569n f17485C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public byte f17486D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f17487E;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17488m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17489n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17490p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17491q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q f17492r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17493s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17494t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Q f17495u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17496v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17497w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17498x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f17499y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public List f17500z;

    static {
        C1579y c1579y = new C1579y();
        f17482F = c1579y;
        c1579y.q();
    }

    public C1579y(C1578x c1578x) {
        super(c1578x);
        this.f17499y = -1;
        this.f17486D = (byte) -1;
        this.f17487E = -1;
        this.f17488m = c1578x.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17482F;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17486D;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i7 = this.f17489n;
        if ((i7 & 4) != 4) {
            this.f17486D = (byte) 0;
            return false;
        }
        if ((i7 & 8) == 8 && !this.f17492r.b()) {
            this.f17486D = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f17494t.size(); i10++) {
            if (!((W) this.f17494t.get(i10)).b()) {
                this.f17486D = (byte) 0;
                return false;
            }
        }
        if (p() && !this.f17495u.b()) {
            this.f17486D = (byte) 0;
            return false;
        }
        for (int i11 = 0; i11 < this.f17497w.size(); i11++) {
            if (!((Q) this.f17497w.get(i11)).b()) {
                this.f17486D = (byte) 0;
                return false;
            }
        }
        for (int i12 = 0; i12 < this.f17500z.size(); i12++) {
            if (!((Z) this.f17500z.get(i12)).b()) {
                this.f17486D = (byte) 0;
                return false;
            }
        }
        if ((this.f17489n & 128) == 128 && !this.f17484A.b()) {
            this.f17486D = (byte) 0;
            return false;
        }
        if ((this.f17489n & 256) == 256 && !this.f17485C.b()) {
            this.f17486D = (byte) 0;
            return false;
        }
        if (i()) {
            this.f17486D = (byte) 1;
            return true;
        }
        this.f17486D = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17487E;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17489n & 2) == 2 ? A2.g.f(1, this.f17490p) : 0;
        if ((this.f17489n & 4) == 4) {
            iF += A2.g.f(2, this.f17491q);
        }
        if ((this.f17489n & 8) == 8) {
            iF += A2.g.h(3, this.f17492r);
        }
        for (int i10 = 0; i10 < this.f17494t.size(); i10++) {
            iF += A2.g.h(4, (AbstractC2126a) this.f17494t.get(i10));
        }
        if ((this.f17489n & 32) == 32) {
            iF += A2.g.h(5, this.f17495u);
        }
        for (int i11 = 0; i11 < this.f17500z.size(); i11++) {
            iF += A2.g.h(6, (AbstractC2126a) this.f17500z.get(i11));
        }
        if ((this.f17489n & 16) == 16) {
            iF += A2.g.f(7, this.f17493s);
        }
        if ((this.f17489n & 64) == 64) {
            iF += A2.g.f(8, this.f17496v);
        }
        if ((this.f17489n & 1) == 1) {
            iF += A2.g.f(9, this.o);
        }
        for (int i12 = 0; i12 < this.f17497w.size(); i12++) {
            iF += A2.g.h(10, (AbstractC2126a) this.f17497w.get(i12));
        }
        int iG = 0;
        for (int i13 = 0; i13 < this.f17498x.size(); i13++) {
            iG += A2.g.g(((Integer) this.f17498x.get(i13)).intValue());
        }
        int iH = iF + iG;
        if (!this.f17498x.isEmpty()) {
            iH = iH + 1 + A2.g.g(iG);
        }
        this.f17499y = iG;
        if ((this.f17489n & 128) == 128) {
            iH += A2.g.h(30, this.f17484A);
        }
        int iG2 = 0;
        for (int i14 = 0; i14 < this.B.size(); i14++) {
            iG2 += A2.g.g(((Integer) this.B.get(i14)).intValue());
        }
        int size = (this.B.size() * 2) + iH + iG2;
        if ((this.f17489n & 256) == 256) {
            size += A2.g.h(32, this.f17485C);
        }
        int size2 = this.f17488m.size() + j() + size;
        this.f17487E = size2;
        return size2;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1578x.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1578x c1578xH = C1578x.h();
        c1578xH.i(this);
        return c1578xH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17489n & 2) == 2) {
            gVar.C(1, this.f17490p);
        }
        if ((this.f17489n & 4) == 4) {
            gVar.C(2, this.f17491q);
        }
        if ((this.f17489n & 8) == 8) {
            gVar.E(3, this.f17492r);
        }
        for (int i7 = 0; i7 < this.f17494t.size(); i7++) {
            gVar.E(4, (AbstractC2126a) this.f17494t.get(i7));
        }
        if ((this.f17489n & 32) == 32) {
            gVar.E(5, this.f17495u);
        }
        for (int i10 = 0; i10 < this.f17500z.size(); i10++) {
            gVar.E(6, (AbstractC2126a) this.f17500z.get(i10));
        }
        if ((this.f17489n & 16) == 16) {
            gVar.C(7, this.f17493s);
        }
        if ((this.f17489n & 64) == 64) {
            gVar.C(8, this.f17496v);
        }
        if ((this.f17489n & 1) == 1) {
            gVar.C(9, this.o);
        }
        for (int i11 = 0; i11 < this.f17497w.size(); i11++) {
            gVar.E(10, (AbstractC2126a) this.f17497w.get(i11));
        }
        if (this.f17498x.size() > 0) {
            gVar.L(90);
            gVar.L(this.f17499y);
        }
        for (int i12 = 0; i12 < this.f17498x.size(); i12++) {
            gVar.D(((Integer) this.f17498x.get(i12)).intValue());
        }
        if ((this.f17489n & 128) == 128) {
            gVar.E(30, this.f17484A);
        }
        for (int i13 = 0; i13 < this.B.size(); i13++) {
            gVar.C(31, ((Integer) this.B.get(i13)).intValue());
        }
        if ((this.f17489n & 256) == 256) {
            gVar.E(32, this.f17485C);
        }
        c1789g.H(19000, gVar);
        gVar.H(this.f17488m);
    }

    public final boolean p() {
        return (this.f17489n & 32) == 32;
    }

    public final void q() {
        this.o = 6;
        this.f17490p = 6;
        this.f17491q = 0;
        Q q4 = Q.f17178E;
        this.f17492r = q4;
        this.f17493s = 0;
        this.f17494t = Collections.emptyList();
        this.f17495u = q4;
        this.f17496v = 0;
        this.f17497w = Collections.emptyList();
        this.f17498x = Collections.emptyList();
        this.f17500z = Collections.emptyList();
        this.f17484A = X.f17240r;
        this.B = Collections.emptyList();
        this.f17485C = C1569n.f17419p;
    }

    public C1579y() {
        this.f17499y = -1;
        this.f17486D = (byte) -1;
        this.f17487E = -1;
        this.f17488m = AbstractC2129d.l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public C1579y(C2130e c2130e, C2132g c2132g) {
        this.f17499y = -1;
        this.f17486D = (byte) -1;
        this.f17487E = -1;
        q();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            ?? N8 = 1024;
            if (!z6) {
                try {
                    try {
                        int iN = c2130e.n();
                        P pR = null;
                        C1568m c1568m = null;
                        C1561f c1561fI = null;
                        P pR2 = null;
                        switch (iN) {
                            case 0:
                                z6 = true;
                                break;
                            case 8:
                                this.f17489n |= 2;
                                this.f17490p = c2130e.k();
                                break;
                            case 16:
                                this.f17489n |= 4;
                                this.f17491q = c2130e.k();
                                break;
                            case 26:
                                if ((this.f17489n & 8) == 8) {
                                    Q q4 = this.f17492r;
                                    q4.getClass();
                                    pR = Q.r(q4);
                                }
                                Q q10 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17492r = q10;
                                if (pR != null) {
                                    pR.i(q10);
                                    this.f17492r = pR.g();
                                }
                                this.f17489n |= 8;
                                break;
                            case 34:
                                int i10 = (i7 == true ? 1 : 0) & 32;
                                i7 = i7;
                                if (i10 != 32) {
                                    this.f17494t = new ArrayList();
                                    i7 = (i7 == true ? 1 : 0) | 32;
                                }
                                this.f17494t.add(c2130e.g(W.f17229y, c2132g));
                                break;
                            case 42:
                                if ((this.f17489n & 32) == 32) {
                                    Q q11 = this.f17495u;
                                    q11.getClass();
                                    pR2 = Q.r(q11);
                                }
                                Q q12 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17495u = q12;
                                if (pR2 != null) {
                                    pR2.i(q12);
                                    this.f17495u = pR2.g();
                                }
                                this.f17489n |= 32;
                                break;
                            case 50:
                                int i11 = (i7 == true ? 1 : 0) & 1024;
                                i7 = i7;
                                if (i11 != 1024) {
                                    this.f17500z = new ArrayList();
                                    i7 = (i7 == true ? 1 : 0) | 1024;
                                }
                                this.f17500z.add(c2130e.g(Z.f17253x, c2132g));
                                break;
                            case 56:
                                this.f17489n |= 16;
                                this.f17493s = c2130e.k();
                                break;
                            case 64:
                                this.f17489n |= 64;
                                this.f17496v = c2130e.k();
                                break;
                            case 72:
                                this.f17489n |= 1;
                                this.o = c2130e.k();
                                break;
                            case 82:
                                int i12 = (i7 == true ? 1 : 0) & 256;
                                i7 = i7;
                                if (i12 != 256) {
                                    this.f17497w = new ArrayList();
                                    i7 = (i7 == true ? 1 : 0) | 256;
                                }
                                this.f17497w.add(c2130e.g(Q.f17179F, c2132g));
                                break;
                            case 88:
                                int i13 = (i7 == true ? 1 : 0) & 512;
                                i7 = i7;
                                if (i13 != 512) {
                                    this.f17498x = new ArrayList();
                                    i7 = (i7 == true ? 1 : 0) | 512;
                                }
                                this.f17498x.add(Integer.valueOf(c2130e.k()));
                                break;
                            case 90:
                                int iD = c2130e.d(c2130e.k());
                                int i14 = (i7 == true ? 1 : 0) & 512;
                                i7 = i7;
                                if (i14 != 512) {
                                    i7 = i7;
                                    if (c2130e.b() > 0) {
                                        this.f17498x = new ArrayList();
                                        i7 = (i7 == true ? 1 : 0) | 512;
                                    }
                                }
                                while (c2130e.b() > 0) {
                                    this.f17498x.add(Integer.valueOf(c2130e.k()));
                                }
                                c2130e.c(iD);
                                break;
                            case 242:
                                if ((this.f17489n & 128) == 128) {
                                    X x8 = this.f17484A;
                                    x8.getClass();
                                    c1561fI = X.i(x8);
                                }
                                X x9 = (X) c2130e.g(X.f17241s, c2132g);
                                this.f17484A = x9;
                                if (c1561fI != null) {
                                    c1561fI.l(x9);
                                    this.f17484A = c1561fI.h();
                                }
                                this.f17489n |= 128;
                                break;
                            case 248:
                                int i15 = (i7 == true ? 1 : 0) & 4096;
                                i7 = i7;
                                if (i15 != 4096) {
                                    this.B = new ArrayList();
                                    i7 = (i7 == true ? 1 : 0) | 4096;
                                }
                                this.B.add(Integer.valueOf(c2130e.k()));
                                break;
                            case 250:
                                int iD2 = c2130e.d(c2130e.k());
                                int i16 = (i7 == true ? 1 : 0) & 4096;
                                i7 = i7;
                                if (i16 != 4096) {
                                    i7 = i7;
                                    if (c2130e.b() > 0) {
                                        this.B = new ArrayList();
                                        i7 = (i7 == true ? 1 : 0) | 4096;
                                    }
                                }
                                while (c2130e.b() > 0) {
                                    this.B.add(Integer.valueOf(c2130e.k()));
                                }
                                c2130e.c(iD2);
                                break;
                            case 258:
                                if ((this.f17489n & 256) == 256) {
                                    C1569n c1569n = this.f17485C;
                                    c1569n.getClass();
                                    c1568m = new C1568m(0);
                                    c1568m.o = Collections.emptyList();
                                    c1568m.j(c1569n);
                                }
                                C1569n c1569n2 = (C1569n) c2130e.g(C1569n.f17420q, c2132g);
                                this.f17485C = c1569n2;
                                if (c1568m != null) {
                                    c1568m.j(c1569n2);
                                    this.f17485C = c1568m.f();
                                }
                                this.f17489n |= 256;
                                break;
                            default:
                                N8 = n(c2130e, gVarT, c2132g, iN);
                                if (N8 == 0) {
                                    z6 = true;
                                }
                                break;
                        }
                    } catch (Throwable th) {
                        if (((i7 == true ? 1 : 0) & 32) == 32) {
                            this.f17494t = Collections.unmodifiableList(this.f17494t);
                        }
                        if (((i7 == true ? 1 : 0) & 1024) == N8) {
                            this.f17500z = Collections.unmodifiableList(this.f17500z);
                        }
                        if (((i7 == true ? 1 : 0) & 256) == 256) {
                            this.f17497w = Collections.unmodifiableList(this.f17497w);
                        }
                        if (((i7 == true ? 1 : 0) & 512) == 512) {
                            this.f17498x = Collections.unmodifiableList(this.f17498x);
                        }
                        if (((i7 == true ? 1 : 0) & 4096) == 4096) {
                            this.B = Collections.unmodifiableList(this.B);
                        }
                        try {
                            gVarT.n();
                        } catch (IOException unused) {
                        } catch (Throwable th2) {
                            this.f17488m = c2128c.i();
                            throw th2;
                        }
                        this.f17488m = c2128c.i();
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
            } else {
                if (((i7 == true ? 1 : 0) & 32) == 32) {
                    this.f17494t = Collections.unmodifiableList(this.f17494t);
                }
                if (((i7 == true ? 1 : 0) & 1024) == 1024) {
                    this.f17500z = Collections.unmodifiableList(this.f17500z);
                }
                if (((i7 == true ? 1 : 0) & 256) == 256) {
                    this.f17497w = Collections.unmodifiableList(this.f17497w);
                }
                if (((i7 == true ? 1 : 0) & 512) == 512) {
                    this.f17498x = Collections.unmodifiableList(this.f17498x);
                }
                if (((i7 == true ? 1 : 0) & 4096) == 4096) {
                    this.B = Collections.unmodifiableList(this.B);
                }
                try {
                    gVarT.n();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f17488m = c2128c.i();
                    throw th3;
                }
                this.f17488m = c2128c.i();
                m();
                return;
            }
        }
    }
}
