package defpackage;

import android.content.Intent;
import android.view.View;

/* JADX INFO: compiled from: AppLaunchDrawBlocker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t6 {
    public final q2 a;
    public final r6 b;
    public final s6 c;
    public final nw2<View> d;
    public final nw2<Intent> e;
    public final nw2<Boolean> f;
    public final nw2<Boolean> g;
    public final nw2<Boolean> h;
    public final nw2<Boolean> i;
    public final zy0 j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public long o;

    public t6(r2 r2Var, js jsVar, ks ksVar, ls lsVar, cs csVar, js jsVar2, ks ksVar2, su1 su1Var) {
        this.a = r2Var;
        this.d = jsVar;
        r6 r6Var = new r6(this);
        this.b = r6Var;
        r2Var.f(r6Var);
        s6 s6Var = new s6(this);
        this.c = s6Var;
        r2Var.f(s6Var);
        this.e = ksVar;
        this.f = lsVar;
        this.g = csVar;
        this.h = jsVar2;
        this.i = ksVar2;
        this.j = new zy0((nw2) su1Var.a, (zp1) su1Var.b, ksVar, lsVar, r2Var, new f52(17, this));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(boolean r7, boolean r8) {
        /*
            r6 = this;
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r2 = r6.n
            long r0 = r0 - r2
            r2 = 1
            r3 = 0
            if (r8 != 0) goto L30
            org.chromium.chrome.browser.profiles.Profile r4 = org.chromium.chrome.browser.profiles.Profile.d()
            org.chromium.components.search_engines.TemplateUrlService r4 = defpackage.oj2.q(r4)
            boolean r4 = r4.b()
            nw2<java.lang.Boolean> r5 = r6.g
            java.lang.Object r5 = r5.get()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L29
            if (r4 == 0) goto L29
            r4 = r2
            goto L2a
        L29:
            r4 = r3
        L2a:
            if (r7 == 0) goto L30
            if (r4 == 0) goto L30
            r7 = r2
            goto L31
        L30:
            r7 = r3
        L31:
            if (r7 != 0) goto L35
            if (r8 == 0) goto L36
        L35:
            r3 = r2
        L36:
            boolean r7 = r6.k
            if (r7 != 0) goto L45
            boolean r6 = r6.l
            if (r6 == 0) goto L3f
            goto L45
        L3f:
            if (r3 == 0) goto L43
            r6 = 2
            goto L51
        L43:
            r6 = 3
            goto L51
        L45:
            r6 = r3 ^ 1
            if (r7 == 0) goto L4c
            java.lang.String r7 = "Android.AppLaunch.DurationDrawWasBlocked.OnInitialTab"
            goto L4e
        L4c:
            java.lang.String r7 = "Android.AppLaunch.DurationDrawWasBlocked.OnOverviewPage"
        L4e:
            defpackage.oj2.b0(r0, r7)
        L51:
            r7 = 4
            java.lang.String r8 = "Android.AppLaunch.BlockDrawForInitialTabAccuracy"
            defpackage.oj2.U(r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t6.a(boolean, boolean):void");
    }
}
