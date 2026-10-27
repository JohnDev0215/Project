package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class P extends AbstractC2135j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Q f17165A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f17166C;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f17167p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f17168q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17169r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Q f17170s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17171t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17172u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17173v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17174w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f17175x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Q f17176y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f17177z;

    public static P h() {
        P p10 = new P();
        p10.f17167p = Collections.emptyList();
        Q q4 = Q.f17178E;
        p10.f17170s = q4;
        p10.f17176y = q4;
        p10.f17165A = q4;
        return p10;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        Q qG = g();
        if (qG.b()) {
            return qG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        P pH = h();
        pH.i(g());
        return pH;
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
            j8.a r1 = j8.Q.f17179F     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.Q r1 = new j8.Q     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.Q r4 = (j8.Q) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.P.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((Q) abstractC2139n);
        return this;
    }

    public final Q g() {
        Q q4 = new Q(this);
        int i7 = this.o;
        if ((i7 & 1) == 1) {
            this.f17167p = Collections.unmodifiableList(this.f17167p);
            this.o &= -2;
        }
        q4.o = this.f17167p;
        int i10 = (i7 & 2) != 2 ? 0 : 1;
        q4.f17185p = this.f17168q;
        if ((i7 & 4) == 4) {
            i10 |= 2;
        }
        q4.f17186q = this.f17169r;
        if ((i7 & 8) == 8) {
            i10 |= 4;
        }
        q4.f17187r = this.f17170s;
        if ((i7 & 16) == 16) {
            i10 |= 8;
        }
        q4.f17188s = this.f17171t;
        if ((i7 & 32) == 32) {
            i10 |= 16;
        }
        q4.f17189t = this.f17172u;
        if ((i7 & 64) == 64) {
            i10 |= 32;
        }
        q4.f17190u = this.f17173v;
        if ((i7 & 128) == 128) {
            i10 |= 64;
        }
        q4.f17191v = this.f17174w;
        if ((i7 & 256) == 256) {
            i10 |= 128;
        }
        q4.f17192w = this.f17175x;
        if ((i7 & 512) == 512) {
            i10 |= 256;
        }
        q4.f17193x = this.f17176y;
        if ((i7 & 1024) == 1024) {
            i10 |= 512;
        }
        q4.f17194y = this.f17177z;
        if ((i7 & 2048) == 2048) {
            i10 |= 1024;
        }
        q4.f17195z = this.f17165A;
        if ((i7 & 4096) == 4096) {
            i10 |= 2048;
        }
        q4.f17180A = this.B;
        if ((i7 & 8192) == 8192) {
            i10 |= 4096;
        }
        q4.B = this.f17166C;
        q4.f17184n = i10;
        return q4;
    }

    public final P i(Q q4) {
        Q q10;
        Q q11;
        Q q12;
        Q q13 = Q.f17178E;
        if (q4 == q13) {
            return this;
        }
        if (!q4.o.isEmpty()) {
            if (this.f17167p.isEmpty()) {
                this.f17167p = q4.o;
                this.o &= -2;
            } else {
                if ((this.o & 1) != 1) {
                    this.f17167p = new ArrayList(this.f17167p);
                    this.o |= 1;
                }
                this.f17167p.addAll(q4.o);
            }
        }
        int i7 = q4.f17184n;
        if ((i7 & 1) == 1) {
            boolean z6 = q4.f17185p;
            this.o |= 2;
            this.f17168q = z6;
        }
        if ((i7 & 2) == 2) {
            int i10 = q4.f17186q;
            this.o |= 4;
            this.f17169r = i10;
        }
        if ((i7 & 4) == 4) {
            Q q14 = q4.f17187r;
            if ((this.o & 8) != 8 || (q12 = this.f17170s) == q13) {
                this.f17170s = q14;
            } else {
                P pR = Q.r(q12);
                pR.i(q14);
                this.f17170s = pR.g();
            }
            this.o |= 8;
        }
        if ((q4.f17184n & 8) == 8) {
            int i11 = q4.f17188s;
            this.o |= 16;
            this.f17171t = i11;
        }
        if (q4.p()) {
            int i12 = q4.f17189t;
            this.o |= 32;
            this.f17172u = i12;
        }
        int i13 = q4.f17184n;
        if ((i13 & 32) == 32) {
            int i14 = q4.f17190u;
            this.o |= 64;
            this.f17173v = i14;
        }
        if ((i13 & 64) == 64) {
            int i15 = q4.f17191v;
            this.o |= 128;
            this.f17174w = i15;
        }
        if ((i13 & 128) == 128) {
            int i16 = q4.f17192w;
            this.o |= 256;
            this.f17175x = i16;
        }
        if ((i13 & 256) == 256) {
            Q q15 = q4.f17193x;
            if ((this.o & 512) != 512 || (q11 = this.f17176y) == q13) {
                this.f17176y = q15;
            } else {
                P pR2 = Q.r(q11);
                pR2.i(q15);
                this.f17176y = pR2.g();
            }
            this.o |= 512;
        }
        int i17 = q4.f17184n;
        if ((i17 & 512) == 512) {
            int i18 = q4.f17194y;
            this.o |= 1024;
            this.f17177z = i18;
        }
        if ((i17 & 1024) == 1024) {
            Q q16 = q4.f17195z;
            if ((this.o & 2048) != 2048 || (q10 = this.f17165A) == q13) {
                this.f17165A = q16;
            } else {
                P pR3 = Q.r(q10);
                pR3.i(q16);
                this.f17165A = pR3.g();
            }
            this.o |= 2048;
        }
        int i19 = q4.f17184n;
        if ((i19 & 2048) == 2048) {
            int i20 = q4.f17180A;
            this.o |= 4096;
            this.B = i20;
        }
        if ((i19 & 4096) == 4096) {
            int i21 = q4.B;
            this.o |= 8192;
            this.f17166C = i21;
        }
        f(q4);
        this.l = this.l.d(q4.f17183m);
        return this;
    }
}
