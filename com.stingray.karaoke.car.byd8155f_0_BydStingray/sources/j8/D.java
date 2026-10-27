package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;
import p8.C2143r;

/* JADX INFO: loaded from: classes.dex */
public final class D extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public L f17085p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public K f17086q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C f17087r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17088s;

    public static D h() {
        D d = new D();
        d.f17085p = L.f17147p;
        d.f17086q = K.f17143p;
        d.f17087r = C.f17075v;
        d.f17088s = Collections.emptyList();
        return d;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        E eG = g();
        if (eG.b()) {
            return eG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        D dH = h();
        dH.i(g());
        return dH;
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
            j8.a r1 = j8.E.f17090v     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.E r1 = new j8.E     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.E r4 = (j8.E) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.D.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((E) abstractC2139n);
        return this;
    }

    public final E g() {
        E e10 = new E(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        e10.o = this.f17085p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        e10.f17093p = this.f17086q;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        e10.f17094q = this.f17087r;
        if ((i7 & 8) == 8) {
            this.f17088s = Collections.unmodifiableList(this.f17088s);
            this.o &= -9;
        }
        e10.f17095r = this.f17088s;
        e10.f17092n = i10;
        return e10;
    }

    public final void i(E e10) {
        C c;
        K k10;
        L l;
        if (e10 == E.f17089u) {
            return;
        }
        if ((e10.f17092n & 1) == 1) {
            L l10 = e10.o;
            if ((this.o & 1) != 1 || (l = this.f17085p) == L.f17147p) {
                this.f17085p = l10;
            } else {
                C1568m c1568m = new C1568m(3);
                c1568m.o = C2143r.f20990m;
                c1568m.l(l);
                c1568m.l(l10);
                this.f17085p = c1568m.h();
            }
            this.o |= 1;
        }
        if ((e10.f17092n & 2) == 2) {
            K k11 = e10.f17093p;
            if ((this.o & 2) != 2 || (k10 = this.f17086q) == K.f17143p) {
                this.f17086q = k11;
            } else {
                C1568m c1568m2 = new C1568m(1);
                c1568m2.o = Collections.emptyList();
                c1568m2.k(k10);
                c1568m2.k(k11);
                this.f17086q = c1568m2.g();
            }
            this.o |= 2;
        }
        if ((e10.f17092n & 4) == 4) {
            C c10 = e10.f17094q;
            if ((this.o & 4) != 4 || (c = this.f17087r) == C.f17075v) {
                this.f17087r = c10;
            } else {
                B bH = B.h();
                bH.i(c);
                bH.i(c10);
                this.f17087r = bH.g();
            }
            this.o |= 4;
        }
        if (!e10.f17095r.isEmpty()) {
            if (this.f17088s.isEmpty()) {
                this.f17088s = e10.f17095r;
                this.o &= -9;
            } else {
                if ((this.o & 8) != 8) {
                    this.f17088s = new ArrayList(this.f17088s);
                    this.o |= 8;
                }
                this.f17088s.addAll(e10.f17095r);
            }
        }
        f(e10);
        this.l = this.l.d(e10.f17091m);
    }
}
