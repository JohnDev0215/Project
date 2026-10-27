package j8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17246p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17247q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Q f17248r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17249s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Q f17250t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17251u;

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        Z zG = g();
        if (zG.b()) {
            return zG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        Y y10 = new Y();
        Q q4 = Q.f17178E;
        y10.f17248r = q4;
        y10.f17250t = q4;
        y10.h(g());
        return y10;
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
            j8.a r1 = j8.Z.f17253x     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.Z r1 = new j8.Z     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.Z r4 = (j8.Z) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.h(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.Y.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((Z) abstractC2139n);
        return this;
    }

    public final Z g() {
        Z z6 = new Z(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        z6.o = this.f17246p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        z6.f17256p = this.f17247q;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        z6.f17257q = this.f17248r;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        z6.f17258r = this.f17249s;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        z6.f17259s = this.f17250t;
        if ((i7 & 32) == 32) {
            i10 |= 32;
        }
        z6.f17260t = this.f17251u;
        z6.f17255n = i10;
        return z6;
    }

    public final void h(Z z6) {
        Q q4;
        Q q10;
        if (z6 == Z.f17252w) {
            return;
        }
        int i7 = z6.f17255n;
        if ((i7 & 1) == 1) {
            int i10 = z6.o;
            this.o = 1 | this.o;
            this.f17246p = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = z6.f17256p;
            this.o = 2 | this.o;
            this.f17247q = i11;
        }
        if ((i7 & 4) == 4) {
            Q q11 = z6.f17257q;
            if ((this.o & 4) != 4 || (q10 = this.f17248r) == Q.f17178E) {
                this.f17248r = q11;
            } else {
                P pR = Q.r(q10);
                pR.i(q11);
                this.f17248r = pR.g();
            }
            this.o |= 4;
        }
        int i12 = z6.f17255n;
        if ((i12 & 8) == 8) {
            int i13 = z6.f17258r;
            this.o = 8 | this.o;
            this.f17249s = i13;
        }
        if ((i12 & 16) == 16) {
            Q q12 = z6.f17259s;
            if ((this.o & 16) != 16 || (q4 = this.f17250t) == Q.f17178E) {
                this.f17250t = q12;
            } else {
                P pR2 = Q.r(q4);
                pR2.i(q12);
                this.f17250t = pR2.g();
            }
            this.o |= 16;
        }
        if ((z6.f17255n & 32) == 32) {
            int i14 = z6.f17260t;
            this.o = 32 | this.o;
            this.f17251u = i14;
        }
        f(z6);
        this.l = this.l.d(z6.f17254m);
    }
}
