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
public final class T extends AbstractC2136k {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final C1556a f17205A = new C1556a(18);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final T f17206z;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17207m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17208n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17209p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f17210q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q f17211r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17212s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Q f17213t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17214u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f17215v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17216w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public byte f17217x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f17218y;

    static {
        T t10 = new T();
        f17206z = t10;
        t10.p();
    }

    public T(S s6) {
        super(s6);
        this.f17217x = (byte) -1;
        this.f17218y = -1;
        this.f17207m = s6.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17206z;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17217x;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17208n & 2) != 2) {
            this.f17217x = (byte) 0;
            return false;
        }
        for (int i7 = 0; i7 < this.f17210q.size(); i7++) {
            if (!((W) this.f17210q.get(i7)).b()) {
                this.f17217x = (byte) 0;
                return false;
            }
        }
        if ((this.f17208n & 4) == 4 && !this.f17211r.b()) {
            this.f17217x = (byte) 0;
            return false;
        }
        if ((this.f17208n & 16) == 16 && !this.f17213t.b()) {
            this.f17217x = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f17215v.size(); i10++) {
            if (!((C1562g) this.f17215v.get(i10)).b()) {
                this.f17217x = (byte) 0;
                return false;
            }
        }
        if (i()) {
            this.f17217x = (byte) 1;
            return true;
        }
        this.f17217x = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17218y;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17208n & 1) == 1 ? A2.g.f(1, this.o) : 0;
        if ((this.f17208n & 2) == 2) {
            iF += A2.g.f(2, this.f17209p);
        }
        for (int i10 = 0; i10 < this.f17210q.size(); i10++) {
            iF += A2.g.h(3, (AbstractC2126a) this.f17210q.get(i10));
        }
        if ((this.f17208n & 4) == 4) {
            iF += A2.g.h(4, this.f17211r);
        }
        if ((this.f17208n & 8) == 8) {
            iF += A2.g.f(5, this.f17212s);
        }
        if ((this.f17208n & 16) == 16) {
            iF += A2.g.h(6, this.f17213t);
        }
        if ((this.f17208n & 32) == 32) {
            iF += A2.g.f(7, this.f17214u);
        }
        for (int i11 = 0; i11 < this.f17215v.size(); i11++) {
            iF += A2.g.h(8, (AbstractC2126a) this.f17215v.get(i11));
        }
        int iG = 0;
        for (int i12 = 0; i12 < this.f17216w.size(); i12++) {
            iG += A2.g.g(((Integer) this.f17216w.get(i12)).intValue());
        }
        int size = this.f17207m.size() + j() + (this.f17216w.size() * 2) + iF + iG;
        this.f17218y = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return S.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        S sH = S.h();
        sH.i(this);
        return sH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17208n & 1) == 1) {
            gVar.C(1, this.o);
        }
        if ((this.f17208n & 2) == 2) {
            gVar.C(2, this.f17209p);
        }
        for (int i7 = 0; i7 < this.f17210q.size(); i7++) {
            gVar.E(3, (AbstractC2126a) this.f17210q.get(i7));
        }
        if ((this.f17208n & 4) == 4) {
            gVar.E(4, this.f17211r);
        }
        if ((this.f17208n & 8) == 8) {
            gVar.C(5, this.f17212s);
        }
        if ((this.f17208n & 16) == 16) {
            gVar.E(6, this.f17213t);
        }
        if ((this.f17208n & 32) == 32) {
            gVar.C(7, this.f17214u);
        }
        for (int i10 = 0; i10 < this.f17215v.size(); i10++) {
            gVar.E(8, (AbstractC2126a) this.f17215v.get(i10));
        }
        for (int i11 = 0; i11 < this.f17216w.size(); i11++) {
            gVar.C(31, ((Integer) this.f17216w.get(i11)).intValue());
        }
        c1789g.H(200, gVar);
        gVar.H(this.f17207m);
    }

    public final void p() {
        this.o = 6;
        this.f17209p = 0;
        this.f17210q = Collections.emptyList();
        Q q4 = Q.f17178E;
        this.f17211r = q4;
        this.f17212s = 0;
        this.f17213t = q4;
        this.f17214u = 0;
        this.f17215v = Collections.emptyList();
        this.f17216w = Collections.emptyList();
    }

    public T() {
        this.f17217x = (byte) -1;
        this.f17218y = -1;
        this.f17207m = AbstractC2129d.l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public T(C2130e c2130e, C2132g c2132g) {
        this.f17217x = (byte) -1;
        this.f17218y = -1;
        p();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            ?? N8 = 128;
            if (!z6) {
                try {
                    try {
                        int iN = c2130e.n();
                        P pR = null;
                        switch (iN) {
                            case 0:
                                z6 = true;
                                break;
                            case 8:
                                this.f17208n |= 1;
                                this.o = c2130e.k();
                                break;
                            case 16:
                                this.f17208n |= 2;
                                this.f17209p = c2130e.k();
                                break;
                            case 26:
                                if ((i7 & 4) != 4) {
                                    this.f17210q = new ArrayList();
                                    i7 |= 4;
                                }
                                this.f17210q.add(c2130e.g(W.f17229y, c2132g));
                                break;
                            case 34:
                                if ((this.f17208n & 4) == 4) {
                                    Q q4 = this.f17211r;
                                    q4.getClass();
                                    pR = Q.r(q4);
                                }
                                Q q10 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17211r = q10;
                                if (pR != null) {
                                    pR.i(q10);
                                    this.f17211r = pR.g();
                                }
                                this.f17208n |= 4;
                                break;
                            case 40:
                                this.f17208n |= 8;
                                this.f17212s = c2130e.k();
                                break;
                            case 50:
                                if ((this.f17208n & 16) == 16) {
                                    Q q11 = this.f17213t;
                                    q11.getClass();
                                    pR = Q.r(q11);
                                }
                                Q q12 = (Q) c2130e.g(Q.f17179F, c2132g);
                                this.f17213t = q12;
                                if (pR != null) {
                                    pR.i(q12);
                                    this.f17213t = pR.g();
                                }
                                this.f17208n |= 16;
                                break;
                            case 56:
                                this.f17208n |= 32;
                                this.f17214u = c2130e.k();
                                break;
                            case 66:
                                if ((i7 & 128) != 128) {
                                    this.f17215v = new ArrayList();
                                    i7 |= 128;
                                }
                                this.f17215v.add(c2130e.g(C1562g.f17339s, c2132g));
                                break;
                            case 248:
                                if ((i7 & 256) != 256) {
                                    this.f17216w = new ArrayList();
                                    i7 |= 256;
                                }
                                this.f17216w.add(Integer.valueOf(c2130e.k()));
                                break;
                            case 250:
                                int iD = c2130e.d(c2130e.k());
                                if ((i7 & 256) != 256 && c2130e.b() > 0) {
                                    this.f17216w = new ArrayList();
                                    i7 |= 256;
                                }
                                while (c2130e.b() > 0) {
                                    this.f17216w.add(Integer.valueOf(c2130e.k()));
                                }
                                c2130e.c(iD);
                                break;
                            default:
                                N8 = n(c2130e, gVarT, c2132g, iN);
                                if (N8 == 0) {
                                    z6 = true;
                                }
                                break;
                        }
                    } catch (Throwable th) {
                        if ((i7 & 4) == 4) {
                            this.f17210q = Collections.unmodifiableList(this.f17210q);
                        }
                        if ((i7 & 128) == N8) {
                            this.f17215v = Collections.unmodifiableList(this.f17215v);
                        }
                        if ((i7 & 256) == 256) {
                            this.f17216w = Collections.unmodifiableList(this.f17216w);
                        }
                        try {
                            gVarT.n();
                        } catch (IOException unused) {
                        } catch (Throwable th2) {
                            this.f17207m = c2128c.i();
                            throw th2;
                        }
                        this.f17207m = c2128c.i();
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
                if ((i7 & 4) == 4) {
                    this.f17210q = Collections.unmodifiableList(this.f17210q);
                }
                if ((i7 & 128) == 128) {
                    this.f17215v = Collections.unmodifiableList(this.f17215v);
                }
                if ((i7 & 256) == 256) {
                    this.f17216w = Collections.unmodifiableList(this.f17216w);
                }
                try {
                    gVarT.n();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f17207m = c2128c.i();
                    throw th3;
                }
                this.f17207m = c2128c.i();
                m();
                return;
            }
        }
    }
}
