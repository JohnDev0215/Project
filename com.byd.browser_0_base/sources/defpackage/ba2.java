package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import defpackage.aa2;
import org.chromium.chrome.browser.c;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: RedirectHandlerTabHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ba2 extends nh0 implements lk3 {
    public Tab e;
    public final aa2 f = new aa2();

    public ba2(Tab tab) {
        this.e = tab;
    }

    public static aa2 y1(Tab tab) {
        mk3 mk3VarB = tab.B();
        ba2 ba2Var = (ba2) mk3VarB.b(ba2.class);
        if (ba2Var == null) {
            ba2Var = new ba2(tab);
            mk3VarB.d(ba2.class, ba2Var);
            tab.x(ba2Var);
        }
        return ba2Var.f;
    }

    public static void z1(Tab tab, Intent intent) {
        aa2 aa2VarY1 = y1(tab);
        boolean zG = c.g(intent);
        boolean zI = q11.i(intent, "android.support.customtabs.extra.SEND_TO_EXTERNAL_HANDLER", false);
        boolean zI2 = q11.i(intent, "org.chromium.chrome.browser.started_chrome_task", false);
        if (intent != null) {
            aa2VarY1.getClass();
            if ("android.intent.action.VIEW".equals(intent.getAction())) {
                String packageName = xz.a.getPackageName();
                boolean z = (zG && zI) ? false : TextUtils.equals(packageName, intent.getPackage()) || TextUtils.equals(packageName, q11.r(intent, "com.android.browser.application_id"));
                Intent intent2 = new Intent(intent);
                hj0.r(intent2);
                aa2VarY1.a = new aa2.b(intent2, z, zG, zI2);
                return;
            }
        }
        aa2VarY1.a = null;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        aa2 aa2Var = this.f;
        aa2Var.a = null;
        aa2Var.c = null;
        aa2Var.b = false;
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this);
        this.e = null;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.q) {
            aa2 aa2Var = this.f;
            aa2Var.a = null;
            aa2Var.c = null;
            aa2Var.b = false;
        }
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
    }
}
