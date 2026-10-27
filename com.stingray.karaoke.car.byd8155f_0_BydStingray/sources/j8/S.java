package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class S extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17196p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17197q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f17198r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Q f17199s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17200t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Q f17201u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f17202v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17203w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17204x;

    public static S h() {
        S s6 = new S();
        s6.f17196p = 6;
        s6.f17198r = Collections.emptyList();
        Q q4 = Q.f17178E;
        s6.f17199s = q4;
        s6.f17201u = q4;
        s6.f17203w = Collections.emptyList();
        s6.f17204x = Collections.emptyList();
        return s6;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        T tG = g();
        if (tG.b()) {
            return tG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        S sH = h();
        sH.i(g());
        return sH;
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
            j8.a r1 = j8.T.f17205A     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.T r1 = new j8.T     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.T r4 = (j8.T) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.S.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((T) abstractC2139n);
        return this;
    }

    public final T g() {
        T t10 = new T(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        t10.o = this.f17196p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        t10.f17209p = this.f17197q;
        if ((i7 & 4) == 4) {
            this.f17198r = Collections.unmodifiableList(this.f17198r);
            this.o &= -5;
        }
        t10.f17210q = this.f17198r;
        if ((i7 & 8) == 8) {
            i10 |= 4;
        }
        t10.f17211r = this.f17199s;
        if ((i7 & 16) == 16) {
            i10 |= 8;
        }
        t10.f17212s = this.f17200t;
        if ((i7 & 32) == 32) {
            i10 |= 16;
        }
        t10.f17213t = this.f17201u;
        if ((i7 & 64) == 64) {
            i10 |= 32;
        }
        t10.f17214u = this.f17202v;
        if ((this.o & 128) == 128) {
            this.f17203w = Collections.unmodifiableList(this.f17203w);
            this.o &= -129;
        }
        t10.f17215v = this.f17203w;
        if ((this.o & 256) == 256) {
            this.f17204x = Collections.unmodifiableList(this.f17204x);
            this.o &= -257;
        }
        t10.f17216w = this.f17204x;
        t10.f17208n = i10;
        return t10;
    }

    public final void i(T t10) {
        Q q4;
        Q q10;
        if (t10 == T.f17206z) {
            return;
        }
        int i7 = t10.f17208n;
        if ((i7 & 1) == 1) {
            int i10 = t10.o;
            this.o = 1 | this.o;
            this.f17196p = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = t10.f17209p;
            this.o = 2 | this.o;
            this.f17197q = i11;
        }
        if (!t10.f17210q.isEmpty()) {
            if (this.f17198r.isEmpty()) {
                this.f17198r = t10.f17210q;
                this.o &= -5;
            } else {
                if ((this.o & 4) != 4) {
                    this.f17198r = new ArrayList(this.f17198r);
                    this.o |= 4;
                }
                this.f17198r.addAll(t10.f17210q);
            }
        }
        if ((t10.f17208n & 4) == 4) {
            Q q11 = t10.f17211r;
            if ((this.o & 8) != 8 || (q10 = this.f17199s) == Q.f17178E) {
                this.f17199s = q11;
            } else {
                P pR = Q.r(q10);
                pR.i(q11);
                this.f17199s = pR.g();
            }
            this.o |= 8;
        }
        int i12 = t10.f17208n;
        if ((i12 & 8) == 8) {
            int i13 = t10.f17212s;
            this.o |= 16;
            this.f17200t = i13;
        }
        if ((i12 & 16) == 16) {
            Q q12 = t10.f17213t;
            if ((this.o & 32) != 32 || (q4 = this.f17201u) == Q.f17178E) {
                this.f17201u = q12;
            } else {
                P pR2 = Q.r(q4);
                pR2.i(q12);
                this.f17201u = pR2.g();
            }
            this.o |= 32;
        }
        if ((t10.f17208n & 32) == 32) {
            int i14 = t10.f17214u;
            this.o |= 64;
            this.f17202v = i14;
        }
        if (!t10.f17215v.isEmpty()) {
            if (this.f17203w.isEmpty()) {
                this.f17203w = t10.f17215v;
                this.o &= -129;
            } else {
                if ((this.o & 128) != 128) {
                    this.f17203w = new ArrayList(this.f17203w);
                    this.o |= 128;
                }
                this.f17203w.addAll(t10.f17215v);
            }
        }
        if (!t10.f17216w.isEmpty()) {
            if (this.f17204x.isEmpty()) {
                this.f17204x = t10.f17216w;
                this.o &= -257;
            } else {
                if ((this.o & 256) != 256) {
                    this.f17204x = new ArrayList(this.f17204x);
                    this.o |= 256;
                }
                this.f17204x.addAll(t10.f17216w);
            }
        }
        f(t10);
        this.l = this.l.d(t10.f17207m);
    }
}
