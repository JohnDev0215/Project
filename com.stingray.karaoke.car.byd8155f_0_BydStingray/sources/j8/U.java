package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class U extends AbstractC2135j {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17219p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17220q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f17221r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public V f17222s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17223t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f17224u;

    public static U h() {
        U u4 = new U();
        u4.f17222s = V.INV;
        u4.f17223t = Collections.emptyList();
        u4.f17224u = Collections.emptyList();
        return u4;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        W wG = g();
        if (wG.b()) {
            return wG;
        }
        throw new A9.k();
    }

    public final Object clone() {
        U uH = h();
        uH.i(g());
        return uH;
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
            j8.a r1 = j8.W.f17229y     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.W r1 = new j8.W     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.W r4 = (j8.W) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.U.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((W) abstractC2139n);
        return this;
    }

    public final W g() {
        W w10 = new W(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        w10.o = this.f17219p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        w10.f17232p = this.f17220q;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        w10.f17233q = this.f17221r;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        w10.f17234r = this.f17222s;
        if ((i7 & 16) == 16) {
            this.f17223t = Collections.unmodifiableList(this.f17223t);
            this.o &= -17;
        }
        w10.f17235s = this.f17223t;
        if ((this.o & 32) == 32) {
            this.f17224u = Collections.unmodifiableList(this.f17224u);
            this.o &= -33;
        }
        w10.f17236t = this.f17224u;
        w10.f17231n = i10;
        return w10;
    }

    public final void i(W w10) {
        if (w10 == W.f17228x) {
            return;
        }
        int i7 = w10.f17231n;
        if ((i7 & 1) == 1) {
            int i10 = w10.o;
            this.o = 1 | this.o;
            this.f17219p = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = w10.f17232p;
            this.o = 2 | this.o;
            this.f17220q = i11;
        }
        if ((i7 & 4) == 4) {
            boolean z6 = w10.f17233q;
            this.o = 4 | this.o;
            this.f17221r = z6;
        }
        if ((i7 & 8) == 8) {
            V v10 = w10.f17234r;
            v10.getClass();
            this.o = 8 | this.o;
            this.f17222s = v10;
        }
        if (!w10.f17235s.isEmpty()) {
            if (this.f17223t.isEmpty()) {
                this.f17223t = w10.f17235s;
                this.o &= -17;
            } else {
                if ((this.o & 16) != 16) {
                    this.f17223t = new ArrayList(this.f17223t);
                    this.o |= 16;
                }
                this.f17223t.addAll(w10.f17235s);
            }
        }
        if (!w10.f17236t.isEmpty()) {
            if (this.f17224u.isEmpty()) {
                this.f17224u = w10.f17236t;
                this.o &= -33;
            } else {
                if ((this.o & 32) != 32) {
                    this.f17224u = new ArrayList(this.f17224u);
                    this.o |= 32;
                }
                this.f17224u.addAll(w10.f17236t);
            }
        }
        f(w10);
        this.l = this.l.d(w10.f17230m);
    }
}
