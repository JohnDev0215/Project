package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1575u extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17448m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17449n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public EnumC1576v f17450p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Q f17451q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17452r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17453s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17454t;

    public static C1575u g() {
        C1575u c1575u = new C1575u();
        c1575u.f17450p = EnumC1576v.TRUE;
        c1575u.f17451q = Q.f17178E;
        c1575u.f17453s = Collections.emptyList();
        c1575u.f17454t = Collections.emptyList();
        return c1575u;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C1577w c1577wF = f();
        if (c1577wF.b()) {
            return c1577wF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1575u c1575uG = g();
        c1575uG.h(f());
        return c1575uG;
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
            j8.a r1 = j8.C1577w.f17459x     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.w r1 = new j8.w     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.w r4 = (j8.C1577w) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.C1575u.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((C1577w) abstractC2139n);
        return this;
    }

    public final C1577w f() {
        C1577w c1577w = new C1577w(this);
        int i7 = this.f17448m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1577w.f17461n = this.f17449n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        c1577w.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        c1577w.f17462p = this.f17450p;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        c1577w.f17463q = this.f17451q;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        c1577w.f17464r = this.f17452r;
        if ((i7 & 32) == 32) {
            this.f17453s = Collections.unmodifiableList(this.f17453s);
            this.f17448m &= -33;
        }
        c1577w.f17465s = this.f17453s;
        if ((this.f17448m & 64) == 64) {
            this.f17454t = Collections.unmodifiableList(this.f17454t);
            this.f17448m &= -65;
        }
        c1577w.f17466t = this.f17454t;
        c1577w.f17460m = i10;
        return c1577w;
    }

    public final void h(C1577w c1577w) {
        Q q4;
        if (c1577w == C1577w.f17458w) {
            return;
        }
        int i7 = c1577w.f17460m;
        if ((i7 & 1) == 1) {
            int i10 = c1577w.f17461n;
            this.f17448m = 1 | this.f17448m;
            this.f17449n = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = c1577w.o;
            this.f17448m = 2 | this.f17448m;
            this.o = i11;
        }
        if ((i7 & 4) == 4) {
            EnumC1576v enumC1576v = c1577w.f17462p;
            enumC1576v.getClass();
            this.f17448m = 4 | this.f17448m;
            this.f17450p = enumC1576v;
        }
        if ((c1577w.f17460m & 8) == 8) {
            Q q10 = c1577w.f17463q;
            if ((this.f17448m & 8) != 8 || (q4 = this.f17451q) == Q.f17178E) {
                this.f17451q = q10;
            } else {
                P pR = Q.r(q4);
                pR.i(q10);
                this.f17451q = pR.g();
            }
            this.f17448m |= 8;
        }
        if ((c1577w.f17460m & 16) == 16) {
            int i12 = c1577w.f17464r;
            this.f17448m = 16 | this.f17448m;
            this.f17452r = i12;
        }
        if (!c1577w.f17465s.isEmpty()) {
            if (this.f17453s.isEmpty()) {
                this.f17453s = c1577w.f17465s;
                this.f17448m &= -33;
            } else {
                if ((this.f17448m & 32) != 32) {
                    this.f17453s = new ArrayList(this.f17453s);
                    this.f17448m |= 32;
                }
                this.f17453s.addAll(c1577w.f17465s);
            }
        }
        if (!c1577w.f17466t.isEmpty()) {
            if (this.f17454t.isEmpty()) {
                this.f17454t = c1577w.f17466t;
                this.f17448m &= -65;
            } else {
                if ((this.f17448m & 64) != 64) {
                    this.f17454t = new ArrayList(this.f17454t);
                    this.f17448m |= 64;
                }
                this.f17454t.addAll(c1577w.f17466t);
            }
        }
        this.l = this.l.d(c1577w.l);
    }
}
