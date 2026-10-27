package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: WebApkSplashNetworkErrorObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xp3 extends nh0 {
    public final Activity e;
    public jp3 f;
    public final String g;
    public boolean h;
    public boolean i;

    public xp3(String str, Activity activity) {
        this.e = activity;
        this.g = str;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        int i = navigationHandle.l;
        if (i != -21) {
            String string = null;
            if (i != 0) {
                Context context = xz.a;
                if (i == -111) {
                    string = context.getString(R$string.webapk_network_error_message_tunnel_connection_failed);
                } else if (i == -106) {
                    sk skVar = bq.a;
                    if (!N.M09VlOh_("PWAsDefaultOfflinePage")) {
                        string = context.getString(R$string.webapk_offline_dialog, this.g);
                    }
                } else if (i == -105) {
                    string = context.getString(R$string.webapk_cannot_connect_to_site);
                }
                if (string != null && !this.h) {
                    this.h = true;
                    NetworkChangeNotifier.f.b.a(new wp3(this, tabImpl));
                    jp3 jp3Var = new jp3();
                    this.f = jp3Var;
                    int i2 = R$style.ThemeOverlay_BrowserUI_AlertDialog;
                    Activity activity = this.e;
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity, i2);
                    builder.a.f = string;
                    builder.f(R$string.ok, new ip3(activity));
                    AlertDialog alertDialogA = builder.a();
                    jp3Var.a = alertDialogA;
                    alertDialogA.setCanceledOnTouchOutside(false);
                    jp3Var.a.show();
                }
            } else {
                jp3 jp3Var2 = this.f;
                if (jp3Var2 != null) {
                    jp3Var2.a.cancel();
                    this.f = null;
                }
            }
        } else if (this.i) {
            tabImpl.z();
            this.i = false;
        }
        oj2.a0(-(-navigationHandle.l), "WebApk.Launch.NetworkError");
    }
}
