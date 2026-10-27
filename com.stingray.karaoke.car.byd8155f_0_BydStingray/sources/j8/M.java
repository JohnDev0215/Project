package j8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class M extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17151m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public N f17152n;
    public Q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17153p;

    public static M g() {
        M m10 = new M();
        m10.f17152n = N.INV;
        m10.o = Q.f17178E;
        return m10;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        O oF = f();
        if (oF.b()) {
            return oF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        M mG = g();
        mG.h(f());
        return mG;
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
            j8.a r1 = j8.O.f17159t     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.O r1 = new j8.O     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.O r4 = (j8.O) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.M.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((O) abstractC2139n);
        return this;
    }

    public final O f() {
        O o = new O(this);
        int i7 = this.f17151m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        o.f17161n = this.f17152n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        o.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        o.f17162p = this.f17153p;
        o.f17160m = i10;
        return o;
    }

    public final void h(O o) {
        Q q4;
        if (o == O.f17158s) {
            return;
        }
        if ((o.f17160m & 1) == 1) {
            N n10 = o.f17161n;
            n10.getClass();
            this.f17151m = 1 | this.f17151m;
            this.f17152n = n10;
        }
        if ((o.f17160m & 2) == 2) {
            Q q10 = o.o;
            if ((this.f17151m & 2) != 2 || (q4 = this.o) == Q.f17178E) {
                this.o = q10;
            } else {
                P pR = Q.r(q4);
                pR.i(q10);
                this.o = pR.g();
            }
            this.f17151m |= 2;
        }
        if ((o.f17160m & 4) == 4) {
            int i7 = o.f17162p;
            this.f17151m = 4 | this.f17151m;
            this.f17153p = i7;
        }
        this.l = this.l.d(o.l);
    }
}
