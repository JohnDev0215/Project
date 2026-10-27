package defpackage;

import java.util.function.Predicate;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: MinimizeAppAndCloseTabBackPressHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class yh1 implements vb, w80 {
    public final aq1<Boolean> e;
    public final Predicate<Tab> f;
    public final Callback<Tab> g;
    public final c3 h;
    public final zp1<Tab> i;
    public final Runnable j;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public yh1(defpackage.z2 r8, defpackage.wr r9, defpackage.xr r10, defpackage.ms r11) {
        /*
            r7 = this;
            r7.<init>()
            aq1 r0 = new aq1
            r0.<init>()
            r7.e = r0
            c3 r1 = new c3
            r2 = 10
            r1.<init>(r2, r7)
            r7.h = r1
            r7.f = r9
            r7.g = r10
            r7.i = r8
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 0
            r2 = 33
            r3 = 1
            if (r9 < r2) goto L23
            r4 = r3
            goto L24
        L23:
            r4 = r10
        L24:
            java.lang.String r5 = "system_back"
            java.lang.String r6 = "BackGestureRefactorAndroid"
            if (r4 == 0) goto L35
            sk r4 = defpackage.bq.a
            boolean r4 = J.N.M6bsIDpc(r6, r5, r10)
            if (r4 == 0) goto L35
            r4 = r3
            goto L36
        L35:
            r4 = r10
        L36:
            r4 = r4 ^ r3
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r0.r(r4)
            r7.j = r11
            if (r9 < r2) goto L44
            r7 = r3
            goto L45
        L44:
            r7 = r10
        L45:
            if (r7 == 0) goto L50
            sk r7 = defpackage.bq.a
            boolean r7 = J.N.M6bsIDpc(r6, r5, r10)
            if (r7 == 0) goto L50
            r10 = r3
        L50:
            if (r10 == 0) goto L55
            r8.p(r1)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yh1.<init>(z2, wr, xr, ms):void");
    }

    public static void d(int i) {
        oj2.U(i, 3, "Android.BackPress.MinimizeAppAndCloseTab");
    }

    @Override // defpackage.w80
    public final void destroy() {
        this.i.d(this.h);
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.e;
    }

    @Override // defpackage.vb
    public final int o() {
        boolean zTest;
        boolean z;
        Tab tab = this.i.get();
        if (tab == null) {
            z = true;
            zTest = false;
        } else {
            jk1 jk1VarU = tab.u();
            if (jk1VarU != null) {
                jk1VarU.o();
            }
            zTest = this.f.test(tab);
            z = !zTest || jy2.f(tab);
        }
        this.j.run();
        if (z) {
            d(zTest ? 2 : 0);
            if (!zTest) {
                tab = null;
            }
            this.g.onResult(tab);
        } else {
            d(1);
            WebContents webContentsC = tab.c();
            if (webContentsC != null) {
                webContentsC.I();
            }
        }
        return 0;
    }
}
