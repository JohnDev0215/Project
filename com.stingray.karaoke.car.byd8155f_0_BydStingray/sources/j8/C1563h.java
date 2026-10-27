package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1563h extends AbstractC2135j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public List f17344A;
    public List B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public List f17345C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public List f17346D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f17347E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Q f17348F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f17349G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public List f17350H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public List f17351I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public List f17352J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public X f17353K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public List f17354L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public e0 f17355M;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17356p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17357q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17358r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17359s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17360t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f17361u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f17362v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17363w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17364x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f17365y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public List f17366z;

    public static C1563h h() {
        C1563h c1563h = new C1563h();
        c1563h.f17356p = 6;
        c1563h.f17359s = Collections.emptyList();
        c1563h.f17360t = Collections.emptyList();
        c1563h.f17361u = Collections.emptyList();
        c1563h.f17362v = Collections.emptyList();
        c1563h.f17363w = Collections.emptyList();
        c1563h.f17364x = Collections.emptyList();
        c1563h.f17365y = Collections.emptyList();
        c1563h.f17366z = Collections.emptyList();
        c1563h.f17344A = Collections.emptyList();
        c1563h.B = Collections.emptyList();
        c1563h.f17345C = Collections.emptyList();
        c1563h.f17346D = Collections.emptyList();
        c1563h.f17348F = Q.f17178E;
        c1563h.f17350H = Collections.emptyList();
        c1563h.f17351I = Collections.emptyList();
        c1563h.f17352J = Collections.emptyList();
        c1563h.f17353K = X.f17240r;
        c1563h.f17354L = Collections.emptyList();
        c1563h.f17355M = e0.f17330p;
        return c1563h;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C1565j c1565jG = g();
        if (c1565jG.b()) {
            return c1565jG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1563h c1563hH = h();
        c1563hH.i(g());
        return c1563hH;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r3, p8.C2132g r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            j8.a r1 = j8.C1565j.f17373V     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.j r1 = new j8.j     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.j r4 = (j8.C1565j) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.i(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.C1563h.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((C1565j) abstractC2139n);
        return this;
    }

    public final C1565j g() {
        C1565j c1565j = new C1565j(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1565j.o = this.f17356p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        c1565j.f17395p = this.f17357q;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        c1565j.f17396q = this.f17358r;
        if ((i7 & 8) == 8) {
            this.f17359s = Collections.unmodifiableList(this.f17359s);
            this.o &= -9;
        }
        c1565j.f17397r = this.f17359s;
        if ((this.o & 16) == 16) {
            this.f17360t = Collections.unmodifiableList(this.f17360t);
            this.o &= -17;
        }
        c1565j.f17398s = this.f17360t;
        if ((this.o & 32) == 32) {
            this.f17361u = Collections.unmodifiableList(this.f17361u);
            this.o &= -33;
        }
        c1565j.f17399t = this.f17361u;
        if ((this.o & 64) == 64) {
            this.f17362v = Collections.unmodifiableList(this.f17362v);
            this.o &= -65;
        }
        c1565j.f17401v = this.f17362v;
        if ((this.o & 128) == 128) {
            this.f17363w = Collections.unmodifiableList(this.f17363w);
            this.o &= -129;
        }
        c1565j.f17403x = this.f17363w;
        if ((this.o & 256) == 256) {
            this.f17364x = Collections.unmodifiableList(this.f17364x);
            this.o &= -257;
        }
        c1565j.f17404y = this.f17364x;
        if ((this.o & 512) == 512) {
            this.f17365y = Collections.unmodifiableList(this.f17365y);
            this.o &= -513;
        }
        c1565j.f17374A = this.f17365y;
        if ((this.o & 1024) == 1024) {
            this.f17366z = Collections.unmodifiableList(this.f17366z);
            this.o &= -1025;
        }
        c1565j.B = this.f17366z;
        if ((this.o & 2048) == 2048) {
            this.f17344A = Collections.unmodifiableList(this.f17344A);
            this.o &= -2049;
        }
        c1565j.f17375C = this.f17344A;
        if ((this.o & 4096) == 4096) {
            this.B = Collections.unmodifiableList(this.B);
            this.o &= -4097;
        }
        c1565j.f17376D = this.B;
        if ((this.o & 8192) == 8192) {
            this.f17345C = Collections.unmodifiableList(this.f17345C);
            this.o &= -8193;
        }
        c1565j.f17377E = this.f17345C;
        if ((this.o & 16384) == 16384) {
            this.f17346D = Collections.unmodifiableList(this.f17346D);
            this.o &= -16385;
        }
        c1565j.f17378F = this.f17346D;
        if ((i7 & 32768) == 32768) {
            i10 |= 8;
        }
        c1565j.f17380H = this.f17347E;
        if ((i7 & 65536) == 65536) {
            i10 |= 16;
        }
        c1565j.f17381I = this.f17348F;
        if ((i7 & 131072) == 131072) {
            i10 |= 32;
        }
        c1565j.f17382J = this.f17349G;
        if ((this.o & 262144) == 262144) {
            this.f17350H = Collections.unmodifiableList(this.f17350H);
            this.o &= -262145;
        }
        c1565j.f17383K = this.f17350H;
        if ((this.o & 524288) == 524288) {
            this.f17351I = Collections.unmodifiableList(this.f17351I);
            this.o &= -524289;
        }
        c1565j.f17385M = this.f17351I;
        if ((this.o & 1048576) == 1048576) {
            this.f17352J = Collections.unmodifiableList(this.f17352J);
            this.o &= -1048577;
        }
        c1565j.f17386N = this.f17352J;
        if ((i7 & 2097152) == 2097152) {
            i10 |= 64;
        }
        c1565j.f17388P = this.f17353K;
        if ((this.o & 4194304) == 4194304) {
            this.f17354L = Collections.unmodifiableList(this.f17354L);
            this.o &= -4194305;
        }
        c1565j.f17389Q = this.f17354L;
        if ((i7 & 8388608) == 8388608) {
            i10 |= 128;
        }
        c1565j.f17390R = this.f17355M;
        c1565j.f17394n = i10;
        return c1565j;
    }

    public final void i(C1565j c1565j) {
        e0 e0Var;
        X x8;
        Q q4;
        if (c1565j == C1565j.f17372U) {
            return;
        }
        int i7 = c1565j.f17394n;
        if ((i7 & 1) == 1) {
            int i10 = c1565j.o;
            this.o = 1 | this.o;
            this.f17356p = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = c1565j.f17395p;
            this.o = 2 | this.o;
            this.f17357q = i11;
        }
        if ((i7 & 4) == 4) {
            int i12 = c1565j.f17396q;
            this.o = 4 | this.o;
            this.f17358r = i12;
        }
        if (!c1565j.f17397r.isEmpty()) {
            if (this.f17359s.isEmpty()) {
                this.f17359s = c1565j.f17397r;
                this.o &= -9;
            } else {
                if ((this.o & 8) != 8) {
                    this.f17359s = new ArrayList(this.f17359s);
                    this.o |= 8;
                }
                this.f17359s.addAll(c1565j.f17397r);
            }
        }
        if (!c1565j.f17398s.isEmpty()) {
            if (this.f17360t.isEmpty()) {
                this.f17360t = c1565j.f17398s;
                this.o &= -17;
            } else {
                if ((this.o & 16) != 16) {
                    this.f17360t = new ArrayList(this.f17360t);
                    this.o |= 16;
                }
                this.f17360t.addAll(c1565j.f17398s);
            }
        }
        if (!c1565j.f17399t.isEmpty()) {
            if (this.f17361u.isEmpty()) {
                this.f17361u = c1565j.f17399t;
                this.o &= -33;
            } else {
                if ((this.o & 32) != 32) {
                    this.f17361u = new ArrayList(this.f17361u);
                    this.o |= 32;
                }
                this.f17361u.addAll(c1565j.f17399t);
            }
        }
        if (!c1565j.f17401v.isEmpty()) {
            if (this.f17362v.isEmpty()) {
                this.f17362v = c1565j.f17401v;
                this.o &= -65;
            } else {
                if ((this.o & 64) != 64) {
                    this.f17362v = new ArrayList(this.f17362v);
                    this.o |= 64;
                }
                this.f17362v.addAll(c1565j.f17401v);
            }
        }
        if (!c1565j.f17403x.isEmpty()) {
            if (this.f17363w.isEmpty()) {
                this.f17363w = c1565j.f17403x;
                this.o &= -129;
            } else {
                if ((this.o & 128) != 128) {
                    this.f17363w = new ArrayList(this.f17363w);
                    this.o |= 128;
                }
                this.f17363w.addAll(c1565j.f17403x);
            }
        }
        if (!c1565j.f17404y.isEmpty()) {
            if (this.f17364x.isEmpty()) {
                this.f17364x = c1565j.f17404y;
                this.o &= -257;
            } else {
                if ((this.o & 256) != 256) {
                    this.f17364x = new ArrayList(this.f17364x);
                    this.o |= 256;
                }
                this.f17364x.addAll(c1565j.f17404y);
            }
        }
        if (!c1565j.f17374A.isEmpty()) {
            if (this.f17365y.isEmpty()) {
                this.f17365y = c1565j.f17374A;
                this.o &= -513;
            } else {
                if ((this.o & 512) != 512) {
                    this.f17365y = new ArrayList(this.f17365y);
                    this.o |= 512;
                }
                this.f17365y.addAll(c1565j.f17374A);
            }
        }
        if (!c1565j.B.isEmpty()) {
            if (this.f17366z.isEmpty()) {
                this.f17366z = c1565j.B;
                this.o &= -1025;
            } else {
                if ((this.o & 1024) != 1024) {
                    this.f17366z = new ArrayList(this.f17366z);
                    this.o |= 1024;
                }
                this.f17366z.addAll(c1565j.B);
            }
        }
        if (!c1565j.f17375C.isEmpty()) {
            if (this.f17344A.isEmpty()) {
                this.f17344A = c1565j.f17375C;
                this.o &= -2049;
            } else {
                if ((this.o & 2048) != 2048) {
                    this.f17344A = new ArrayList(this.f17344A);
                    this.o |= 2048;
                }
                this.f17344A.addAll(c1565j.f17375C);
            }
        }
        if (!c1565j.f17376D.isEmpty()) {
            if (this.B.isEmpty()) {
                this.B = c1565j.f17376D;
                this.o &= -4097;
            } else {
                if ((this.o & 4096) != 4096) {
                    this.B = new ArrayList(this.B);
                    this.o |= 4096;
                }
                this.B.addAll(c1565j.f17376D);
            }
        }
        if (!c1565j.f17377E.isEmpty()) {
            if (this.f17345C.isEmpty()) {
                this.f17345C = c1565j.f17377E;
                this.o &= -8193;
            } else {
                if ((this.o & 8192) != 8192) {
                    this.f17345C = new ArrayList(this.f17345C);
                    this.o |= 8192;
                }
                this.f17345C.addAll(c1565j.f17377E);
            }
        }
        if (!c1565j.f17378F.isEmpty()) {
            if (this.f17346D.isEmpty()) {
                this.f17346D = c1565j.f17378F;
                this.o &= -16385;
            } else {
                if ((this.o & 16384) != 16384) {
                    this.f17346D = new ArrayList(this.f17346D);
                    this.o |= 16384;
                }
                this.f17346D.addAll(c1565j.f17378F);
            }
        }
        int i13 = c1565j.f17394n;
        if ((i13 & 8) == 8) {
            int i14 = c1565j.f17380H;
            this.o |= 32768;
            this.f17347E = i14;
        }
        if ((i13 & 16) == 16) {
            Q q10 = c1565j.f17381I;
            if ((this.o & 65536) != 65536 || (q4 = this.f17348F) == Q.f17178E) {
                this.f17348F = q10;
            } else {
                P pR = Q.r(q4);
                pR.i(q10);
                this.f17348F = pR.g();
            }
            this.o |= 65536;
        }
        if ((c1565j.f17394n & 32) == 32) {
            int i15 = c1565j.f17382J;
            this.o |= 131072;
            this.f17349G = i15;
        }
        if (!c1565j.f17383K.isEmpty()) {
            if (this.f17350H.isEmpty()) {
                this.f17350H = c1565j.f17383K;
                this.o &= -262145;
            } else {
                if ((this.o & 262144) != 262144) {
                    this.f17350H = new ArrayList(this.f17350H);
                    this.o |= 262144;
                }
                this.f17350H.addAll(c1565j.f17383K);
            }
        }
        if (!c1565j.f17385M.isEmpty()) {
            if (this.f17351I.isEmpty()) {
                this.f17351I = c1565j.f17385M;
                this.o &= -524289;
            } else {
                if ((this.o & 524288) != 524288) {
                    this.f17351I = new ArrayList(this.f17351I);
                    this.o |= 524288;
                }
                this.f17351I.addAll(c1565j.f17385M);
            }
        }
        if (!c1565j.f17386N.isEmpty()) {
            if (this.f17352J.isEmpty()) {
                this.f17352J = c1565j.f17386N;
                this.o &= -1048577;
            } else {
                if ((this.o & 1048576) != 1048576) {
                    this.f17352J = new ArrayList(this.f17352J);
                    this.o |= 1048576;
                }
                this.f17352J.addAll(c1565j.f17386N);
            }
        }
        if ((c1565j.f17394n & 64) == 64) {
            X x9 = c1565j.f17388P;
            if ((this.o & 2097152) != 2097152 || (x8 = this.f17353K) == X.f17240r) {
                this.f17353K = x9;
            } else {
                C1561f c1561fI = X.i(x8);
                c1561fI.l(x9);
                this.f17353K = c1561fI.h();
            }
            this.o |= 2097152;
        }
        if (!c1565j.f17389Q.isEmpty()) {
            if (this.f17354L.isEmpty()) {
                this.f17354L = c1565j.f17389Q;
                this.o &= -4194305;
            } else {
                if ((this.o & 4194304) != 4194304) {
                    this.f17354L = new ArrayList(this.f17354L);
                    this.o |= 4194304;
                }
                this.f17354L.addAll(c1565j.f17389Q);
            }
        }
        if ((c1565j.f17394n & 128) == 128) {
            e0 e0Var2 = c1565j.f17390R;
            if ((this.o & 8388608) != 8388608 || (e0Var = this.f17355M) == e0.f17330p) {
                this.f17355M = e0Var2;
            } else {
                C1568m c1568m = new C1568m(2);
                c1568m.o = Collections.emptyList();
                c1568m.m(e0Var);
                c1568m.m(e0Var2);
                this.f17355M = c1568m.i();
            }
            this.o |= 8388608;
        }
        f(c1565j);
        this.l = this.l.d(c1565j.f17393m);
    }
}
