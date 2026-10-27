package defpackage;

import J.N;
import android.os.Handler;
import android.os.SystemClock;
import java.util.HashSet;

/* JADX INFO: compiled from: BrowserStateBrowserControlsVisibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class vj extends lj implements w80 {
    public final zp1 j;
    public long k;
    public final Handler i = new Handler();
    public final mb3 h = new mb3(new tj(this, 0));

    public vj(aq1 aq1Var) {
        this.j = aq1Var;
        aq1Var.p(new xr(6, this));
        y();
    }

    @Override // defpackage.w80
    public final void destroy() {
        this.i.removeCallbacksAndMessages(null);
    }

    @Override // defpackage.lj, defpackage.aq1
    public final /* bridge */ /* synthetic */ void r(Integer num) {
        r(num);
    }

    public final void t() {
        if (iv.e().g("disable-minimum-show-duration")) {
            return;
        }
        Handler handler = this.i;
        int i = 0;
        if (handler.hasMessages(0)) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis() - this.k;
        if (jUptimeMillis >= 3000) {
            return;
        }
        handler.postDelayed(new uj(this.h.a(), i, this), 3000 - jUptimeMillis);
    }

    public final void u(int i) {
        mb3 mb3Var = this.h;
        HashSet hashSet = mb3Var.b;
        boolean z = false;
        if (hashSet.size() == 1 && hashSet.contains(Integer.valueOf(i))) {
            if (a8.P()) {
                sk skVar = bq.a;
                if (N.M09VlOh_("SuppressToolbarCaptures")) {
                    z = true;
                }
            }
            if (!z) {
                t();
            }
        }
        mb3Var.c(i);
    }

    public final int v() {
        mb3 mb3Var = this.h;
        if (!mb3Var.b()) {
            this.k = SystemClock.uptimeMillis();
        }
        return mb3Var.a();
    }

    public final int w(int i) {
        int iV = v();
        this.h.c(i);
        return iV;
    }

    public final void x() {
        if (!this.h.b()) {
            this.k = SystemClock.uptimeMillis();
        }
        t();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y() {
        /*
            r1 = this;
            zp1 r0 = r1.j
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L10
            r0 = 2
            goto L2c
        L10:
            mb3 r0 = r1.h
            boolean r0 = r0.b()
            if (r0 == 0) goto L19
            goto L29
        L19:
            boolean r0 = defpackage.a8.Q()
            if (r0 == 0) goto L2b
            sk r0 = defpackage.bq.a
            java.lang.String r0 = "ToolbarScrollAblationAndroid"
            boolean r0 = J.N.M09VlOh_(r0)
            if (r0 == 0) goto L2b
        L29:
            r0 = 1
            goto L2c
        L2b:
            r0 = 3
        L2c:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.r(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vj.y():void");
    }
}
