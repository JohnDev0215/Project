package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1557b extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17270m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public EnumC1558c f17271n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f17272p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public double f17273q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17274r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17275s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17276t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1562g f17277u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f17278v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17279w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f17280x;

    public static C1557b g() {
        C1557b c1557b = new C1557b();
        c1557b.f17271n = EnumC1558c.BYTE;
        c1557b.f17277u = C1562g.f17338r;
        c1557b.f17278v = Collections.emptyList();
        return c1557b;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C1559d c1559dF = f();
        if (c1559dF.b()) {
            return c1559dF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1557b c1557bG = g();
        c1557bG.h(f());
        return c1557bG;
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
            j8.a r1 = j8.C1559d.B     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.d r1 = new j8.d     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.d r4 = (j8.C1559d) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.C1557b.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((C1559d) abstractC2139n);
        return this;
    }

    public final C1559d f() {
        C1559d c1559d = new C1559d(this);
        int i7 = this.f17270m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1559d.f17302n = this.f17271n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        c1559d.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        c1559d.f17303p = this.f17272p;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        c1559d.f17304q = this.f17273q;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        c1559d.f17305r = this.f17274r;
        if ((i7 & 32) == 32) {
            i10 |= 32;
        }
        c1559d.f17306s = this.f17275s;
        if ((i7 & 64) == 64) {
            i10 |= 64;
        }
        c1559d.f17307t = this.f17276t;
        if ((i7 & 128) == 128) {
            i10 |= 128;
        }
        c1559d.f17308u = this.f17277u;
        if ((i7 & 256) == 256) {
            this.f17278v = Collections.unmodifiableList(this.f17278v);
            this.f17270m &= -257;
        }
        c1559d.f17309v = this.f17278v;
        if ((i7 & 512) == 512) {
            i10 |= 256;
        }
        c1559d.f17310w = this.f17279w;
        if ((i7 & 1024) == 1024) {
            i10 |= 512;
        }
        c1559d.f17311x = this.f17280x;
        c1559d.f17301m = i10;
        return c1559d;
    }

    public final void h(C1559d c1559d) {
        C1562g c1562g;
        if (c1559d == C1559d.f17300A) {
            return;
        }
        if ((c1559d.f17301m & 1) == 1) {
            EnumC1558c enumC1558c = c1559d.f17302n;
            enumC1558c.getClass();
            this.f17270m = 1 | this.f17270m;
            this.f17271n = enumC1558c;
        }
        int i7 = c1559d.f17301m;
        if ((i7 & 2) == 2) {
            long j3 = c1559d.o;
            this.f17270m |= 2;
            this.o = j3;
        }
        if ((i7 & 4) == 4) {
            float f10 = c1559d.f17303p;
            this.f17270m = 4 | this.f17270m;
            this.f17272p = f10;
        }
        if ((i7 & 8) == 8) {
            double d = c1559d.f17304q;
            this.f17270m |= 8;
            this.f17273q = d;
        }
        if ((i7 & 16) == 16) {
            int i10 = c1559d.f17305r;
            this.f17270m = 16 | this.f17270m;
            this.f17274r = i10;
        }
        if ((i7 & 32) == 32) {
            int i11 = c1559d.f17306s;
            this.f17270m = 32 | this.f17270m;
            this.f17275s = i11;
        }
        if ((i7 & 64) == 64) {
            int i12 = c1559d.f17307t;
            this.f17270m = 64 | this.f17270m;
            this.f17276t = i12;
        }
        if ((i7 & 128) == 128) {
            C1562g c1562g2 = c1559d.f17308u;
            if ((this.f17270m & 128) != 128 || (c1562g = this.f17277u) == C1562g.f17338r) {
                this.f17277u = c1562g2;
            } else {
                C1561f c1561f = new C1561f(0);
                c1561f.o = Collections.emptyList();
                c1561f.k(c1562g);
                c1561f.k(c1562g2);
                this.f17277u = c1561f.g();
            }
            this.f17270m |= 128;
        }
        if (!c1559d.f17309v.isEmpty()) {
            if (this.f17278v.isEmpty()) {
                this.f17278v = c1559d.f17309v;
                this.f17270m &= -257;
            } else {
                if ((this.f17270m & 256) != 256) {
                    this.f17278v = new ArrayList(this.f17278v);
                    this.f17270m |= 256;
                }
                this.f17278v.addAll(c1559d.f17309v);
            }
        }
        int i13 = c1559d.f17301m;
        if ((i13 & 256) == 256) {
            int i14 = c1559d.f17310w;
            this.f17270m |= 512;
            this.f17279w = i14;
        }
        if ((i13 & 512) == 512) {
            int i15 = c1559d.f17311x;
            this.f17270m |= 1024;
            this.f17280x = i15;
        }
        this.l = this.l.d(c1559d.l);
    }
}
