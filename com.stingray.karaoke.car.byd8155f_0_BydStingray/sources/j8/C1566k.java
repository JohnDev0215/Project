package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1566k extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17406p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f17407q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f17408r;

    public static C1566k h() {
        C1566k c1566k = new C1566k();
        c1566k.f17406p = 6;
        c1566k.f17407q = Collections.emptyList();
        c1566k.f17408r = Collections.emptyList();
        return c1566k;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        C1567l c1567lG = g();
        if (c1567lG.b()) {
            return c1567lG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1566k c1566kH = h();
        c1566kH.i(g());
        return c1566kH;
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
            j8.a r1 = j8.C1567l.f17410u     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.l r1 = new j8.l     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.l r4 = (j8.C1567l) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.C1566k.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((C1567l) abstractC2139n);
        return this;
    }

    public final C1567l g() {
        C1567l c1567l = new C1567l(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        c1567l.o = this.f17406p;
        if ((i7 & 2) == 2) {
            this.f17407q = Collections.unmodifiableList(this.f17407q);
            this.o &= -3;
        }
        c1567l.f17413p = this.f17407q;
        if ((this.o & 4) == 4) {
            this.f17408r = Collections.unmodifiableList(this.f17408r);
            this.o &= -5;
        }
        c1567l.f17414q = this.f17408r;
        c1567l.f17412n = i10;
        return c1567l;
    }

    public final void i(C1567l c1567l) {
        if (c1567l == C1567l.f17409t) {
            return;
        }
        if ((c1567l.f17412n & 1) == 1) {
            int i7 = c1567l.o;
            this.o = 1 | this.o;
            this.f17406p = i7;
        }
        if (!c1567l.f17413p.isEmpty()) {
            if (this.f17407q.isEmpty()) {
                this.f17407q = c1567l.f17413p;
                this.o &= -3;
            } else {
                if ((this.o & 2) != 2) {
                    this.f17407q = new ArrayList(this.f17407q);
                    this.o |= 2;
                }
                this.f17407q.addAll(c1567l.f17413p);
            }
        }
        if (!c1567l.f17414q.isEmpty()) {
            if (this.f17408r.isEmpty()) {
                this.f17408r = c1567l.f17414q;
                this.o &= -5;
            } else {
                if ((this.o & 4) != 4) {
                    this.f17408r = new ArrayList(this.f17408r);
                    this.o |= 4;
                }
                this.f17408r.addAll(c1567l.f17414q);
            }
        }
        f(c1567l);
        this.l = this.l.d(c1567l.f17411m);
    }
}
