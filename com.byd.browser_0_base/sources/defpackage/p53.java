package defpackage;

import android.app.Activity;
import android.content.Intent;
import org.chromium.chrome.browser.b;

/* JADX INFO: compiled from: TabbedActivityLaunchCauseMetrics.java */
/* JADX INFO: loaded from: classes.dex */
public final class p53 extends s51 {
    public final /* synthetic */ int k = 0;
    public final Object l;

    public p53(Activity activity, ls3 ls3Var) {
        super(activity);
        this.l = ls3Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        if (defpackage.q11.i(r10, "org.chromium.chrome.browser.searchwidget.FROM_SEARCH_WIDGET", false) != false) goto L49;
     */
    @Override // defpackage.s51
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a() {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.p53.a():int");
    }

    @Override // defpackage.s51
    public final int b() {
        switch (this.k) {
            case 0:
                Intent intent = ((Activity) this.l).getIntent();
                if (this.e.a && intent != null) {
                    if (!q11.i(intent, "com.android.chrome.from_open_in_browser", false)) {
                        boolean z = true;
                        if (!intent.hasExtra("org.chromium.chrome.browser.ServiceTabLauncher.LAUNCH_REQUEST_ID")) {
                            int iL = b.f(intent) != -1 ? q11.l(intent, "BRING_TAB_TO_FRONT_SOURCE", -1) : -1;
                            if (1 != iL && iL != 0) {
                                z = false;
                            }
                        }
                        if (z) {
                        }
                    }
                }
                break;
        }
        return 0;
    }

    public p53(Activity activity) {
        super(activity);
        this.l = activity;
    }
}
