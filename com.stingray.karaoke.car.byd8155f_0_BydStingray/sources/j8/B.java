package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f17070p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f17071q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f17072r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public X f17073s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public e0 f17074t;

    public static B h() {
        B b10 = new B();
        b10.f17070p = Collections.emptyList();
        b10.f17071q = Collections.emptyList();
        b10.f17072r = Collections.emptyList();
        b10.f17073s = X.f17240r;
        b10.f17074t = e0.f17330p;
        return b10;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C cG = g();
        if (cG.b()) {
            return cG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        B bH = h();
        bH.i(g());
        return bH;
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
            j8.a r1 = j8.C.f17076w     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.C r1 = new j8.C     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.C r4 = (j8.C) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.B.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((C) abstractC2139n);
        return this;
    }

    public final C g() {
        C c = new C(this);
        int i7 = this.o;
        if ((i7 & 1) == 1) {
            this.f17070p = Collections.unmodifiableList(this.f17070p);
            this.o &= -2;
        }
        c.o = this.f17070p;
        if ((this.o & 2) == 2) {
            this.f17071q = Collections.unmodifiableList(this.f17071q);
            this.o &= -3;
        }
        c.f17079p = this.f17071q;
        if ((this.o & 4) == 4) {
            this.f17072r = Collections.unmodifiableList(this.f17072r);
            this.o &= -5;
        }
        c.f17080q = this.f17072r;
        int i10 = (i7 & 8) != 8 ? 0 : 1;
        c.f17081r = this.f17073s;
        if ((i7 & 16) == 16) {
            i10 |= 2;
        }
        c.f17082s = this.f17074t;
        c.f17078n = i10;
        return c;
    }

    public final void i(C c) {
        e0 e0Var;
        X x8;
        if (c == C.f17075v) {
            return;
        }
        if (!c.o.isEmpty()) {
            if (this.f17070p.isEmpty()) {
                this.f17070p = c.o;
                this.o &= -2;
            } else {
                if ((this.o & 1) != 1) {
                    this.f17070p = new ArrayList(this.f17070p);
                    this.o |= 1;
                }
                this.f17070p.addAll(c.o);
            }
        }
        if (!c.f17079p.isEmpty()) {
            if (this.f17071q.isEmpty()) {
                this.f17071q = c.f17079p;
                this.o &= -3;
            } else {
                if ((this.o & 2) != 2) {
                    this.f17071q = new ArrayList(this.f17071q);
                    this.o |= 2;
                }
                this.f17071q.addAll(c.f17079p);
            }
        }
        if (!c.f17080q.isEmpty()) {
            if (this.f17072r.isEmpty()) {
                this.f17072r = c.f17080q;
                this.o &= -5;
            } else {
                if ((this.o & 4) != 4) {
                    this.f17072r = new ArrayList(this.f17072r);
                    this.o |= 4;
                }
                this.f17072r.addAll(c.f17080q);
            }
        }
        if ((c.f17078n & 1) == 1) {
            X x9 = c.f17081r;
            if ((this.o & 8) != 8 || (x8 = this.f17073s) == X.f17240r) {
                this.f17073s = x9;
            } else {
                C1561f c1561fI = X.i(x8);
                c1561fI.l(x9);
                this.f17073s = c1561fI.h();
            }
            this.o |= 8;
        }
        if ((c.f17078n & 2) == 2) {
            e0 e0Var2 = c.f17082s;
            if ((this.o & 16) != 16 || (e0Var = this.f17074t) == e0.f17330p) {
                this.f17074t = e0Var2;
            } else {
                C1568m c1568m = new C1568m(2);
                c1568m.o = Collections.emptyList();
                c1568m.m(e0Var);
                c1568m.m(e0Var2);
                this.f17074t = c1568m.i();
            }
            this.o |= 16;
        }
        f(c);
        this.l = this.l.d(c.f17077m);
    }
}
