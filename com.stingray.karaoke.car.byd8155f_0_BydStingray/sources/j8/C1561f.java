package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1561f extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f17334m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17335n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17336p;

    public /* synthetic */ C1561f(int i7) {
        this.f17334m = i7;
    }

    public static C1561f i() {
        C1561f c1561f = new C1561f(1);
        c1561f.o = Collections.emptyList();
        c1561f.f17336p = -1;
        return c1561f;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        switch (this.f17334m) {
            case 0:
                C1562g c1562gG = g();
                if (c1562gG.b()) {
                    return c1562gG;
                }
                throw new A9.k();
            case 1:
                X xH = h();
                if (xH.b()) {
                    return xH;
                }
                throw new A9.k();
            default:
                C1560e c1560eF = f();
                if (c1560eF.b()) {
                    return c1560eF;
                }
                throw new A9.k();
        }
    }

    public final Object clone() {
        switch (this.f17334m) {
            case 0:
                C1561f c1561f = new C1561f(0);
                c1561f.o = Collections.emptyList();
                c1561f.k(g());
                return c1561f;
            case 1:
                C1561f c1561fI = i();
                c1561fI.l(h());
                return c1561fI;
            default:
                C1561f c1561f2 = new C1561f(2);
                c1561f2.o = C1559d.f17300A;
                c1561f2.j(f());
                return c1561f2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x005c  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r3, p8.C2132g r4) throws java.lang.Throwable {
        /*
            r2 = this;
            int r0 = r2.f17334m
            switch(r0) {
                case 0: goto L43;
                case 1: goto L24;
                default: goto L5;
            }
        L5:
            r0 = 0
            j8.a r1 = j8.C1560e.f17325s     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r1.getClass()     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            j8.e r1 = new j8.e     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r2.j(r1)
            return r2
        L14:
            r3 = move-exception
            goto L1e
        L16:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> L14
            j8.e r4 = (j8.C1560e) r4     // Catch: java.lang.Throwable -> L14
            throw r3     // Catch: java.lang.Throwable -> L1c
        L1c:
            r3 = move-exception
            r0 = r4
        L1e:
            if (r0 == 0) goto L23
            r2.j(r0)
        L23:
            throw r3
        L24:
            r0 = 0
            j8.a r1 = j8.X.f17241s     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r1.getClass()     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            j8.X r1 = new j8.X     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r2.l(r1)
            return r2
        L33:
            r3 = move-exception
            goto L3d
        L35:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> L33
            j8.X r4 = (j8.X) r4     // Catch: java.lang.Throwable -> L33
            throw r3     // Catch: java.lang.Throwable -> L3b
        L3b:
            r3 = move-exception
            r0 = r4
        L3d:
            if (r0 == 0) goto L42
            r2.l(r0)
        L42:
            throw r3
        L43:
            r0 = 0
            j8.a r1 = j8.C1562g.f17339s     // Catch: java.lang.Throwable -> L50 p8.C2142q -> L52
            java.lang.Object r3 = r1.a(r3, r4)     // Catch: java.lang.Throwable -> L50 p8.C2142q -> L52
            j8.g r3 = (j8.C1562g) r3     // Catch: java.lang.Throwable -> L50 p8.C2142q -> L52
            r2.k(r3)
            return r2
        L50:
            r3 = move-exception
            goto L5a
        L52:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> L50
            j8.g r4 = (j8.C1562g) r4     // Catch: java.lang.Throwable -> L50
            throw r3     // Catch: java.lang.Throwable -> L58
        L58:
            r3 = move-exception
            r0 = r4
        L5a:
            if (r0 == 0) goto L5f
            r2.k(r0)
        L5f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.C1561f.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        switch (this.f17334m) {
            case 0:
                k((C1562g) abstractC2139n);
                break;
            case 1:
                l((X) abstractC2139n);
                break;
            default:
                j((C1560e) abstractC2139n);
                break;
        }
        return this;
    }

    public C1560e f() {
        C1560e c1560e = new C1560e(this);
        int i7 = this.f17335n;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1560e.f17327n = this.f17336p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        c1560e.o = (C1559d) this.o;
        c1560e.f17326m = i10;
        return c1560e;
    }

    public C1562g g() {
        C1562g c1562g = new C1562g(this);
        int i7 = this.f17335n;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1562g.f17341n = this.f17336p;
        if ((i7 & 2) == 2) {
            this.o = Collections.unmodifiableList((List) this.o);
            this.f17335n &= -3;
        }
        c1562g.o = (List) this.o;
        c1562g.f17340m = i10;
        return c1562g;
    }

    public X h() {
        X x8 = new X(this);
        int i7 = this.f17335n;
        if ((i7 & 1) == 1) {
            this.o = Collections.unmodifiableList((List) this.o);
            this.f17335n &= -2;
        }
        x8.f17243n = (List) this.o;
        int i10 = (i7 & 2) != 2 ? 0 : 1;
        x8.o = this.f17336p;
        x8.f17242m = i10;
        return x8;
    }

    public void j(C1560e c1560e) {
        C1559d c1559d;
        if (c1560e == C1560e.f17324r) {
            return;
        }
        int i7 = c1560e.f17326m;
        if ((i7 & 1) == 1) {
            int i10 = c1560e.f17327n;
            this.f17335n = 1 | this.f17335n;
            this.f17336p = i10;
        }
        if ((i7 & 2) == 2) {
            C1559d c1559d2 = c1560e.o;
            if ((this.f17335n & 2) != 2 || (c1559d = (C1559d) this.o) == C1559d.f17300A) {
                this.o = c1559d2;
            } else {
                C1557b c1557bG = C1557b.g();
                c1557bG.h(c1559d);
                c1557bG.h(c1559d2);
                this.o = c1557bG.f();
            }
            this.f17335n |= 2;
        }
        this.l = this.l.d(c1560e.l);
    }

    public void k(C1562g c1562g) {
        if (c1562g == C1562g.f17338r) {
            return;
        }
        if ((c1562g.f17340m & 1) == 1) {
            int i7 = c1562g.f17341n;
            this.f17335n = 1 | this.f17335n;
            this.f17336p = i7;
        }
        if (!c1562g.o.isEmpty()) {
            if (((List) this.o).isEmpty()) {
                this.o = c1562g.o;
                this.f17335n &= -3;
            } else {
                if ((this.f17335n & 2) != 2) {
                    this.o = new ArrayList((List) this.o);
                    this.f17335n |= 2;
                }
                ((List) this.o).addAll(c1562g.o);
            }
        }
        this.l = this.l.d(c1562g.l);
    }

    public void l(X x8) {
        if (x8 == X.f17240r) {
            return;
        }
        if (!x8.f17243n.isEmpty()) {
            if (((List) this.o).isEmpty()) {
                this.o = x8.f17243n;
                this.f17335n &= -2;
            } else {
                if ((this.f17335n & 1) != 1) {
                    this.o = new ArrayList((List) this.o);
                    this.f17335n |= 1;
                }
                ((List) this.o).addAll(x8.f17243n);
            }
        }
        if ((x8.f17242m & 1) == 1) {
            int i7 = x8.o;
            this.f17335n |= 2;
            this.f17336p = i7;
        }
        this.l = this.l.d(x8.l);
    }
}
