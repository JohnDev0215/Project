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
public final class G extends AbstractC2136k {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final G f17111F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final C1556a f17112G = new C1556a(12);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f17113A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public List f17114C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public byte f17115D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f17116E;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17118n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17119p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17120q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q f17121r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17122s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17123t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Q f17124u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17125v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17126w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17127x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f17128y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Z f17129z;

    static {
        G g7 = new G();
        f17111F = g7;
        g7.q();
    }

    public G(F f10) {
        super(f10);
        this.f17128y = -1;
        this.f17115D = (byte) -1;
        this.f17116E = -1;
        this.f17117m = f10.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17111F;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17115D;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        int i7 = this.f17118n;
        if ((i7 & 4) != 4) {
            this.f17115D = (byte) 0;
            return false;
        }
        if ((i7 & 8) == 8 && !this.f17121r.b()) {
            this.f17115D = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f17123t.size(); i10++) {
            if (!((W) this.f17123t.get(i10)).b()) {
                this.f17115D = (byte) 0;
                return false;
            }
        }
        if (p() && !this.f17124u.b()) {
            this.f17115D = (byte) 0;
            return false;
        }
        for (int i11 = 0; i11 < this.f17126w.size(); i11++) {
            if (!((Q) this.f17126w.get(i11)).b()) {
                this.f17115D = (byte) 0;
                return false;
            }
        }
        if ((this.f17118n & 128) == 128 && !this.f17129z.b()) {
            this.f17115D = (byte) 0;
            return false;
        }
        if (i()) {
            this.f17115D = (byte) 1;
            return true;
        }
        this.f17115D = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17116E;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17118n & 2) == 2 ? A2.g.f(1, this.f17119p) : 0;
        if ((this.f17118n & 4) == 4) {
            iF += A2.g.f(2, this.f17120q);
        }
        if ((this.f17118n & 8) == 8) {
            iF += A2.g.h(3, this.f17121r);
        }
        for (int i10 = 0; i10 < this.f17123t.size(); i10++) {
            iF += A2.g.h(4, (AbstractC2126a) this.f17123t.get(i10));
        }
        if ((this.f17118n & 32) == 32) {
            iF += A2.g.h(5, this.f17124u);
        }
        if ((this.f17118n & 128) == 128) {
            iF += A2.g.h(6, this.f17129z);
        }
        if ((this.f17118n & 256) == 256) {
            iF += A2.g.f(7, this.f17113A);
        }
        if ((this.f17118n & 512) == 512) {
            iF += A2.g.f(8, this.B);
        }
        if ((this.f17118n & 16) == 16) {
            iF += A2.g.f(9, this.f17122s);
        }
        if ((this.f17118n & 64) == 64) {
            iF += A2.g.f(10, this.f17125v);
        }
        if ((this.f17118n & 1) == 1) {
            iF += A2.g.f(11, this.o);
        }
        for (int i11 = 0; i11 < this.f17126w.size(); i11++) {
            iF += A2.g.h(12, (AbstractC2126a) this.f17126w.get(i11));
        }
        int iG = 0;
        for (int i12 = 0; i12 < this.f17127x.size(); i12++) {
            iG += A2.g.g(((Integer) this.f17127x.get(i12)).intValue());
        }
        int iG2 = iF + iG;
        if (!this.f17127x.isEmpty()) {
            iG2 = iG2 + 1 + A2.g.g(iG);
        }
        this.f17128y = iG;
        int iG3 = 0;
        for (int i13 = 0; i13 < this.f17114C.size(); i13++) {
            iG3 += A2.g.g(((Integer) this.f17114C.get(i13)).intValue());
        }
        int size = this.f17117m.size() + j() + (this.f17114C.size() * 2) + iG2 + iG3;
        this.f17116E = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return F.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        F fH = F.h();
        fH.i(this);
        return fH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17118n & 2) == 2) {
            gVar.C(1, this.f17119p);
        }
        if ((this.f17118n & 4) == 4) {
            gVar.C(2, this.f17120q);
        }
        if ((this.f17118n & 8) == 8) {
            gVar.E(3, this.f17121r);
        }
        for (int i7 = 0; i7 < this.f17123t.size(); i7++) {
            gVar.E(4, (AbstractC2126a) this.f17123t.get(i7));
        }
        if ((this.f17118n & 32) == 32) {
            gVar.E(5, this.f17124u);
        }
        if ((this.f17118n & 128) == 128) {
            gVar.E(6, this.f17129z);
        }
        if ((this.f17118n & 256) == 256) {
            gVar.C(7, this.f17113A);
        }
        if ((this.f17118n & 512) == 512) {
            gVar.C(8, this.B);
        }
        if ((this.f17118n & 16) == 16) {
            gVar.C(9, this.f17122s);
        }
        if ((this.f17118n & 64) == 64) {
            gVar.C(10, this.f17125v);
        }
        if ((this.f17118n & 1) == 1) {
            gVar.C(11, this.o);
        }
        for (int i10 = 0; i10 < this.f17126w.size(); i10++) {
            gVar.E(12, (AbstractC2126a) this.f17126w.get(i10));
        }
        if (this.f17127x.size() > 0) {
            gVar.L(106);
            gVar.L(this.f17128y);
        }
        for (int i11 = 0; i11 < this.f17127x.size(); i11++) {
            gVar.D(((Integer) this.f17127x.get(i11)).intValue());
        }
        for (int i12 = 0; i12 < this.f17114C.size(); i12++) {
            gVar.C(31, ((Integer) this.f17114C.get(i12)).intValue());
        }
        c1789g.H(19000, gVar);
        gVar.H(this.f17117m);
    }

    public final boolean p() {
        return (this.f17118n & 32) == 32;
    }

    public final void q() {
        this.o = 518;
        this.f17119p = 2054;
        this.f17120q = 0;
        Q q4 = Q.f17178E;
        this.f17121r = q4;
        this.f17122s = 0;
        this.f17123t = Collections.emptyList();
        this.f17124u = q4;
        this.f17125v = 0;
        this.f17126w = Collections.emptyList();
        this.f17127x = Collections.emptyList();
        this.f17129z = Z.f17252w;
        this.f17113A = 0;
        this.B = 0;
        this.f17114C = Collections.emptyList();
    }

    public G() {
        this.f17128y = -1;
        this.f17115D = (byte) -1;
        this.f17116E = -1;
        this.f17117m = AbstractC2129d.l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public G(C2130e c2130e, C2132g c2132g) {
        this.f17128y = -1;
        this.f17115D = (byte) -1;
        this.f17116E = -1;
        q();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            ?? N8 = 256;
            if (!z6) {
                try {
                    try {
                        try {
                            int iN = c2130e.n();
                            P pR = null;
                            Y y10 = null;
                            P pR2 = null;
                            switch (iN) {
                                case 0:
                                    z6 = true;
                                    break;
                                case 8:
                                    this.f17118n |= 2;
                                    this.f17119p = c2130e.k();
                                    break;
                                case 16:
                                    this.f17118n |= 4;
                                    this.f17120q = c2130e.k();
                                    break;
                                case 26:
                                    if ((this.f17118n & 8) == 8) {
                                        Q q4 = this.f17121r;
                                        q4.getClass();
                                        pR = Q.r(q4);
                                    }
                                    Q q10 = (Q) c2130e.g(Q.f17179F, c2132g);
                                    this.f17121r = q10;
                                    if (pR != null) {
                                        pR.i(q10);
                                        this.f17121r = pR.g();
                                    }
                                    this.f17118n |= 8;
                                    break;
                                case 34:
                                    int i10 = (i7 == true ? 1 : 0) & 32;
                                    i7 = i7;
                                    if (i10 != 32) {
                                        this.f17123t = new ArrayList();
                                        i7 = (i7 == true ? 1 : 0) | 32;
                                    }
                                    this.f17123t.add(c2130e.g(W.f17229y, c2132g));
                                    break;
                                case 42:
                                    if ((this.f17118n & 32) == 32) {
                                        Q q11 = this.f17124u;
                                        q11.getClass();
                                        pR2 = Q.r(q11);
                                    }
                                    Q q12 = (Q) c2130e.g(Q.f17179F, c2132g);
                                    this.f17124u = q12;
                                    if (pR2 != null) {
                                        pR2.i(q12);
                                        this.f17124u = pR2.g();
                                    }
                                    this.f17118n |= 32;
                                    break;
                                case 50:
                                    if ((this.f17118n & 128) == 128) {
                                        Z z10 = this.f17129z;
                                        z10.getClass();
                                        y10 = new Y();
                                        Q q13 = Q.f17178E;
                                        y10.f17248r = q13;
                                        y10.f17250t = q13;
                                        y10.h(z10);
                                    }
                                    Z z11 = (Z) c2130e.g(Z.f17253x, c2132g);
                                    this.f17129z = z11;
                                    if (y10 != null) {
                                        y10.h(z11);
                                        this.f17129z = y10.g();
                                    }
                                    this.f17118n |= 128;
                                    break;
                                case 56:
                                    this.f17118n |= 256;
                                    this.f17113A = c2130e.k();
                                    break;
                                case 64:
                                    this.f17118n |= 512;
                                    this.B = c2130e.k();
                                    break;
                                case 72:
                                    this.f17118n |= 16;
                                    this.f17122s = c2130e.k();
                                    break;
                                case 80:
                                    this.f17118n |= 64;
                                    this.f17125v = c2130e.k();
                                    break;
                                case 88:
                                    this.f17118n |= 1;
                                    this.o = c2130e.k();
                                    break;
                                case 98:
                                    int i11 = (i7 == true ? 1 : 0) & 256;
                                    i7 = i7;
                                    if (i11 != 256) {
                                        this.f17126w = new ArrayList();
                                        i7 = (i7 == true ? 1 : 0) | 256;
                                    }
                                    this.f17126w.add(c2130e.g(Q.f17179F, c2132g));
                                    break;
                                case 104:
                                    int i12 = (i7 == true ? 1 : 0) & 512;
                                    i7 = i7;
                                    if (i12 != 512) {
                                        this.f17127x = new ArrayList();
                                        i7 = (i7 == true ? 1 : 0) | 512;
                                    }
                                    this.f17127x.add(Integer.valueOf(c2130e.k()));
                                    break;
                                case 106:
                                    int iD = c2130e.d(c2130e.k());
                                    int i13 = (i7 == true ? 1 : 0) & 512;
                                    i7 = i7;
                                    if (i13 != 512) {
                                        i7 = i7;
                                        if (c2130e.b() > 0) {
                                            this.f17127x = new ArrayList();
                                            i7 = (i7 == true ? 1 : 0) | 512;
                                        }
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17127x.add(Integer.valueOf(c2130e.k()));
                                    }
                                    c2130e.c(iD);
                                    break;
                                case 248:
                                    int i14 = (i7 == true ? 1 : 0) & 8192;
                                    i7 = i7;
                                    if (i14 != 8192) {
                                        this.f17114C = new ArrayList();
                                        i7 = (i7 == true ? 1 : 0) | 8192;
                                    }
                                    this.f17114C.add(Integer.valueOf(c2130e.k()));
                                    break;
                                case 250:
                                    int iD2 = c2130e.d(c2130e.k());
                                    int i15 = (i7 == true ? 1 : 0) & 8192;
                                    i7 = i7;
                                    if (i15 != 8192) {
                                        i7 = i7;
                                        if (c2130e.b() > 0) {
                                            this.f17114C = new ArrayList();
                                            i7 = (i7 == true ? 1 : 0) | 8192;
                                        }
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17114C.add(Integer.valueOf(c2130e.k()));
                                    }
                                    c2130e.c(iD2);
                                    break;
                                default:
                                    N8 = n(c2130e, gVarT, c2132g, iN);
                                    if (N8 == 0) {
                                        z6 = true;
                                    }
                                    break;
                            }
                        } catch (C2142q e10) {
                            e10.l = this;
                            throw e10;
                        }
                    } catch (IOException e11) {
                        C2142q c2142q = new C2142q(e11.getMessage());
                        c2142q.l = this;
                        throw c2142q;
                    }
                } catch (Throwable th) {
                    if (((i7 == true ? 1 : 0) & 32) == 32) {
                        this.f17123t = Collections.unmodifiableList(this.f17123t);
                    }
                    if (((i7 == true ? 1 : 0) & 256) == N8) {
                        this.f17126w = Collections.unmodifiableList(this.f17126w);
                    }
                    if (((i7 == true ? 1 : 0) & 512) == 512) {
                        this.f17127x = Collections.unmodifiableList(this.f17127x);
                    }
                    if (((i7 == true ? 1 : 0) & 8192) == 8192) {
                        this.f17114C = Collections.unmodifiableList(this.f17114C);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f17117m = c2128c.i();
                        throw th2;
                    }
                    this.f17117m = c2128c.i();
                    m();
                    throw th;
                }
            } else {
                if (((i7 == true ? 1 : 0) & 32) == 32) {
                    this.f17123t = Collections.unmodifiableList(this.f17123t);
                }
                if (((i7 == true ? 1 : 0) & 256) == 256) {
                    this.f17126w = Collections.unmodifiableList(this.f17126w);
                }
                if (((i7 == true ? 1 : 0) & 512) == 512) {
                    this.f17127x = Collections.unmodifiableList(this.f17127x);
                }
                if (((i7 == true ? 1 : 0) & 8192) == 8192) {
                    this.f17114C = Collections.unmodifiableList(this.f17114C);
                }
                try {
                    gVarT.n();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f17117m = c2128c.i();
                    throw th3;
                }
                this.f17117m = c2128c.i();
                m();
                return;
            }
        }
    }
}
