package defpackage;

import J.N;
import android.content.Intent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: compiled from: TrustedWebActivityCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class xf3 {
    public final il2 a;
    public final h20 b;
    public final pk c;
    public final eu d;

    /* JADX INFO: compiled from: TrustedWebActivityCoordinator.java */
    public static class a implements hk1 {
        public final CustomTabsConnection e;
        public final oj f;

        public a(oj ojVar, CustomTabsConnection customTabsConnection) {
            this.e = customTabsConnection;
            this.f = ojVar;
        }

        @Override // defpackage.hk1
        public final void G0() {
            sk skVar = bq.a;
            if (N.M09VlOh_("TrustedWebActivityPostMessage")) {
                return;
            }
            this.e.c.f(this.f.z(), null);
        }
    }

    public xf3(il2 il2Var, h20 h20Var, p61 p61Var, oj ojVar, jg3 jg3Var, q2 q2Var, pk pkVar, eu euVar, CustomTabsConnection customTabsConnection) {
        this.a = il2Var;
        this.b = h20Var;
        this.c = pkVar;
        this.d = euVar;
        Intent intentT = ojVar.t();
        final boolean z = false;
        boolean zI = q11.i(intentT, "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", false);
        boolean z2 = q11.n(intentT, "androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS") != null;
        if (zI && z2) {
            z = true;
        }
        if (z) {
            p61Var.get();
        }
        jg3Var.a.a(new Runnable() { // from class: hg3
            @Override // java.lang.Runnable
            public final void run() {
                PostTask.c(d73.f, new ig3(0, z));
            }
        });
        h20Var.i.a(new wq0(20, this));
        q2Var.f(new a(ojVar, customTabsConnection));
    }
}
