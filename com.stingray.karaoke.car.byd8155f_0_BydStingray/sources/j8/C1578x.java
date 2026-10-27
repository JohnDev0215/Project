package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1578x extends AbstractC2135j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public X f17469A;
    public List B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C1569n f17470C;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17471p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17472q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17473r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Q f17474s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17475t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f17476u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Q f17477v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17478w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17479x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f17480y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public List f17481z;

    public static C1578x h() {
        C1578x c1578x = new C1578x();
        c1578x.f17471p = 6;
        c1578x.f17472q = 6;
        Q q4 = Q.f17178E;
        c1578x.f17474s = q4;
        c1578x.f17476u = Collections.emptyList();
        c1578x.f17477v = q4;
        c1578x.f17479x = Collections.emptyList();
        c1578x.f17480y = Collections.emptyList();
        c1578x.f17481z = Collections.emptyList();
        c1578x.f17469A = X.f17240r;
        c1578x.B = Collections.emptyList();
        c1578x.f17470C = C1569n.f17419p;
        return c1578x;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C1579y c1579yG = g();
        if (c1579yG.b()) {
            return c1579yG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1578x c1578xH = h();
        c1578xH.i(g());
        return c1578xH;
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
            j8.a r1 = j8.C1579y.f17483G     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.y r1 = new j8.y     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.y r4 = (j8.C1579y) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.C1578x.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((C1579y) abstractC2139n);
        return this;
    }

    public final C1579y g() {
        C1579y c1579y = new C1579y(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1579y.o = this.f17471p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        c1579y.f17490p = this.f17472q;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        c1579y.f17491q = this.f17473r;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        c1579y.f17492r = this.f17474s;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        c1579y.f17493s = this.f17475t;
        if ((i7 & 32) == 32) {
            this.f17476u = Collections.unmodifiableList(this.f17476u);
            this.o &= -33;
        }
        c1579y.f17494t = this.f17476u;
        if ((i7 & 64) == 64) {
            i10 |= 32;
        }
        c1579y.f17495u = this.f17477v;
        if ((i7 & 128) == 128) {
            i10 |= 64;
        }
        c1579y.f17496v = this.f17478w;
        if ((this.o & 256) == 256) {
            this.f17479x = Collections.unmodifiableList(this.f17479x);
            this.o &= -257;
        }
        c1579y.f17497w = this.f17479x;
        if ((this.o & 512) == 512) {
            this.f17480y = Collections.unmodifiableList(this.f17480y);
            this.o &= -513;
        }
        c1579y.f17498x = this.f17480y;
        if ((this.o & 1024) == 1024) {
            this.f17481z = Collections.unmodifiableList(this.f17481z);
            this.o &= -1025;
        }
        c1579y.f17500z = this.f17481z;
        if ((i7 & 2048) == 2048) {
            i10 |= 128;
        }
        c1579y.f17484A = this.f17469A;
        if ((this.o & 4096) == 4096) {
            this.B = Collections.unmodifiableList(this.B);
            this.o &= -4097;
        }
        c1579y.B = this.B;
        if ((i7 & 8192) == 8192) {
            i10 |= 256;
        }
        c1579y.f17485C = this.f17470C;
        c1579y.f17489n = i10;
        return c1579y;
    }

    public final void i(C1579y c1579y) {
        C1569n c1569n;
        X x8;
        Q q4;
        Q q10;
        if (c1579y == C1579y.f17482F) {
            return;
        }
        int i7 = c1579y.f17489n;
        if ((i7 & 1) == 1) {
            int i10 = c1579y.o;
            this.o = 1 | this.o;
            this.f17471p = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = c1579y.f17490p;
            this.o = 2 | this.o;
            this.f17472q = i11;
        }
        if ((i7 & 4) == 4) {
            int i12 = c1579y.f17491q;
            this.o = 4 | this.o;
            this.f17473r = i12;
        }
        if ((i7 & 8) == 8) {
            Q q11 = c1579y.f17492r;
            if ((this.o & 8) != 8 || (q10 = this.f17474s) == Q.f17178E) {
                this.f17474s = q11;
            } else {
                P pR = Q.r(q10);
                pR.i(q11);
                this.f17474s = pR.g();
            }
            this.o |= 8;
        }
        if ((c1579y.f17489n & 16) == 16) {
            int i13 = c1579y.f17493s;
            this.o = 16 | this.o;
            this.f17475t = i13;
        }
        if (!c1579y.f17494t.isEmpty()) {
            if (this.f17476u.isEmpty()) {
                this.f17476u = c1579y.f17494t;
                this.o &= -33;
            } else {
                if ((this.o & 32) != 32) {
                    this.f17476u = new ArrayList(this.f17476u);
                    this.o |= 32;
                }
                this.f17476u.addAll(c1579y.f17494t);
            }
        }
        if (c1579y.p()) {
            Q q12 = c1579y.f17495u;
            if ((this.o & 64) != 64 || (q4 = this.f17477v) == Q.f17178E) {
                this.f17477v = q12;
            } else {
                P pR2 = Q.r(q4);
                pR2.i(q12);
                this.f17477v = pR2.g();
            }
            this.o |= 64;
        }
        if ((c1579y.f17489n & 64) == 64) {
            int i14 = c1579y.f17496v;
            this.o |= 128;
            this.f17478w = i14;
        }
        if (!c1579y.f17497w.isEmpty()) {
            if (this.f17479x.isEmpty()) {
                this.f17479x = c1579y.f17497w;
                this.o &= -257;
            } else {
                if ((this.o & 256) != 256) {
                    this.f17479x = new ArrayList(this.f17479x);
                    this.o |= 256;
                }
                this.f17479x.addAll(c1579y.f17497w);
            }
        }
        if (!c1579y.f17498x.isEmpty()) {
            if (this.f17480y.isEmpty()) {
                this.f17480y = c1579y.f17498x;
                this.o &= -513;
            } else {
                if ((this.o & 512) != 512) {
                    this.f17480y = new ArrayList(this.f17480y);
                    this.o |= 512;
                }
                this.f17480y.addAll(c1579y.f17498x);
            }
        }
        if (!c1579y.f17500z.isEmpty()) {
            if (this.f17481z.isEmpty()) {
                this.f17481z = c1579y.f17500z;
                this.o &= -1025;
            } else {
                if ((this.o & 1024) != 1024) {
                    this.f17481z = new ArrayList(this.f17481z);
                    this.o |= 1024;
                }
                this.f17481z.addAll(c1579y.f17500z);
            }
        }
        if ((c1579y.f17489n & 128) == 128) {
            X x9 = c1579y.f17484A;
            if ((this.o & 2048) != 2048 || (x8 = this.f17469A) == X.f17240r) {
                this.f17469A = x9;
            } else {
                C1561f c1561fI = X.i(x8);
                c1561fI.l(x9);
                this.f17469A = c1561fI.h();
            }
            this.o |= 2048;
        }
        if (!c1579y.B.isEmpty()) {
            if (this.B.isEmpty()) {
                this.B = c1579y.B;
                this.o &= -4097;
            } else {
                if ((this.o & 4096) != 4096) {
                    this.B = new ArrayList(this.B);
                    this.o |= 4096;
                }
                this.B.addAll(c1579y.B);
            }
        }
        if ((c1579y.f17489n & 256) == 256) {
            C1569n c1569n2 = c1579y.f17485C;
            if ((this.o & 8192) != 8192 || (c1569n = this.f17470C) == C1569n.f17419p) {
                this.f17470C = c1569n2;
            } else {
                C1568m c1568m = new C1568m(0);
                c1568m.o = Collections.emptyList();
                c1568m.j(c1569n);
                c1568m.j(c1569n2);
                this.f17470C = c1568m.f();
            }
            this.o |= 8192;
        }
        f(c1579y);
        this.l = this.l.d(c1579y.f17488m);
    }
}
