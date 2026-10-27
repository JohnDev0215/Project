package j8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17130m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17131n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public I f17132p;

    public static H g() {
        H h10 = new H();
        h10.f17131n = -1;
        h10.f17132p = I.PACKAGE;
        return h10;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        J jF = f();
        if (jF.b()) {
            return jF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        H hG = g();
        hG.h(f());
        return hG;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r2, p8.C2132g r3) throws java.lang.Throwable {
        /*
            r1 = this;
            r3 = 0
            j8.a r0 = j8.J.f17137t     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.J r0 = new j8.J     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.h(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            p8.a r0 = r2.l     // Catch: java.lang.Throwable -> Lf
            j8.J r0 = (j8.J) r0     // Catch: java.lang.Throwable -> Lf
            throw r2     // Catch: java.lang.Throwable -> L17
        L17:
            r2 = move-exception
            r3 = r0
        L19:
            if (r3 == 0) goto L1e
            r1.h(r3)
        L1e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.H.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((J) abstractC2139n);
        return this;
    }

    public final J f() {
        J j3 = new J(this);
        int i7 = this.f17130m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        j3.f17139n = this.f17131n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        j3.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        j3.f17140p = this.f17132p;
        j3.f17138m = i10;
        return j3;
    }

    public final void h(J j3) {
        if (j3 == J.f17136s) {
            return;
        }
        int i7 = j3.f17138m;
        if ((i7 & 1) == 1) {
            int i10 = j3.f17139n;
            this.f17130m = 1 | this.f17130m;
            this.f17131n = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = j3.o;
            this.f17130m = 2 | this.f17130m;
            this.o = i11;
        }
        if ((i7 & 4) == 4) {
            I i12 = j3.f17140p;
            i12.getClass();
            this.f17130m = 4 | this.f17130m;
            this.f17132p = i12;
        }
        this.l = this.l.d(j3.l);
    }
}
