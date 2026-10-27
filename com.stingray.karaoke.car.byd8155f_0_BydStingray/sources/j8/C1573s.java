package j8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1573s extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17441p;

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C1574t c1574t = new C1574t(this);
        int i7 = (this.o & 1) != 1 ? 0 : 1;
        c1574t.o = this.f17441p;
        c1574t.f17445n = i7;
        if (c1574t.b()) {
            return c1574t;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1573s c1573s = new C1573s();
        C1574t c1574t = new C1574t(this);
        int i7 = (this.o & 1) != 1 ? 0 : 1;
        c1574t.o = this.f17441p;
        c1574t.f17445n = i7;
        c1573s.g(c1574t);
        return c1573s;
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
            j8.a r1 = j8.C1574t.f17443s     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.t r1 = new j8.t     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.g(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.t r4 = (j8.C1574t) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.g(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.C1573s.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        g((C1574t) abstractC2139n);
        return this;
    }

    public final void g(C1574t c1574t) {
        if (c1574t == C1574t.f17442r) {
            return;
        }
        if ((c1574t.f17445n & 1) == 1) {
            int i7 = c1574t.o;
            this.o = 1 | this.o;
            this.f17441p = i7;
        }
        f(c1574t);
        this.l = this.l.d(c1574t.f17444m);
    }
}
