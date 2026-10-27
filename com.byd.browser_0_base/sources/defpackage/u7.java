package defpackage;

import android.content.Context;
import defpackage.bz0;
import defpackage.l61;
import org.chromium.chrome.browser.theme.ThemeColorProvider;

/* JADX INFO: compiled from: AppThemeColorProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u7 extends ThemeColorProvider implements bz0.b {
    public final int j;
    public final int k;
    public bz0 l;
    public l61 m;
    public final a n;
    public boolean o;
    public boolean p;
    public final Context q;

    /* JADX INFO: compiled from: AppThemeColorProvider.java */
    public class a implements l61.a {
        public a() {
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            if (i == 2) {
                u7 u7Var = u7.this;
                u7Var.p = true;
                u7Var.d();
            }
        }

        @Override // l61.a
        public final void i(int i, boolean z, boolean z2) {
            if (i == 2) {
                u7 u7Var = u7.this;
                u7Var.p = false;
                u7Var.d();
            }
        }
    }

    public u7(Context context) {
        super(context);
        this.q = context;
        this.j = yp.a(context, false);
        this.k = yp.a(context, true);
        this.n = new a();
    }

    public final void c() {
        this.h.clear();
        this.i.clear();
        bz0 bz0Var = this.l;
        if (bz0Var != null) {
            bz0Var.a.c(this);
            this.l = null;
        }
        l61 l61Var = this.m;
        if (l61Var != null) {
            l61Var.f(this.n);
            this.m = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            r6 = this;
            boolean r0 = r6.o
            android.content.Context r1 = r6.q
            r2 = 0
            if (r0 == 0) goto L25
            boolean r0 = r6.p
            r3 = 1
            if (r0 == 0) goto L26
            boolean r0 = defpackage.g90.a(r1)
            boolean r4 = defpackage.oj2.z(r1)
            boolean r5 = defpackage.zb2.d(r1)
            if (r0 != 0) goto L21
            if (r4 != 0) goto L21
            if (r5 == 0) goto L1f
            goto L21
        L1f:
            r0 = r2
            goto L22
        L21:
            r0 = r3
        L22:
            if (r0 == 0) goto L25
            goto L26
        L25:
            r3 = r2
        L26:
            if (r3 == 0) goto L2b
            int r0 = r6.k
            goto L2d
        L2b:
            int r0 = r6.j
        L2d:
            r6.a(r0, r2)
            if (r3 == 0) goto L34
            r0 = 2
            goto L35
        L34:
            r0 = 3
        L35:
            android.content.res.ColorStateList r1 = defpackage.n3.y0(r1)
            r6.b(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u7.d():void");
    }

    @Override // bz0.b
    public final void i(boolean z) {
        this.o = z;
        d();
    }
}
