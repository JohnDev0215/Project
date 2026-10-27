package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.webapps.WebappLauncherActivity;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: WebappActionsNotificationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zr3 implements hy1 {
    public final a30 e;
    public final oj f;

    public zr3(a30 a30Var, oj ojVar, q2 q2Var) {
        this.e = a30Var;
        this.f = ojVar;
        q2Var.f(this);
    }

    public static o8 a(Context context, Tab tab, String str) {
        Intent intent = new Intent(str);
        intent.setClass(context, WebappLauncherActivity.class);
        intent.putExtra("com.android.chrome.tab_id", tab.getId());
        q11.a(intent);
        return o8.a(context, 0, intent, 1207959552);
    }

    @Override // defpackage.hy1
    public final void f() {
        Tab tab = this.e.b;
        js3 js3VarM = this.f.M();
        if (tab == null || js3VarM == null || js3VarM.g == 2) {
            return;
        }
        Context context = xz.a;
        o8 o8VarA = a(context, tab, "org.chromium.chrome.browser.webapps.NOTIFICATION_ACTION_FOCUS");
        o8 o8VarA2 = a(context, tab, "org.chromium.chrome.browser.webapps.NOTIFICATION_ACTION_OPEN_IN_CHROME");
        o8 o8VarA3 = a(context, tab, "org.chromium.chrome.browser.webapps.NOTIFICATION_ACTION_SHARE");
        qq qqVarD = n3.D("webapp_actions", new o8(11, 5, (String) null));
        qqVarD.p(R$drawable.ic_chrome);
        qqVarD.k(js3VarM.f);
        qqVarD.j(context.getString(R$string.webapp_tap_to_copy_url));
        NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
        notificationCompat$Builder.j = false;
        qqVarD.h(false);
        qqVarD.o(true);
        notificationCompat$Builder.i = -2;
        qqVarD.c(o8VarA);
        qqVarD.b(R$drawable.ic_share_white_24dp, context.getResources().getString(R$string.share), o8VarA3, 7);
        qqVarD.b(R$drawable.ic_exit_to_app_white_24dp, context.getResources().getString(R$string.menu_open_in_chrome), o8VarA2, 8);
        lp1 lp1VarF = qqVarD.f();
        new NotificationManagerProxyImpl(context).c(lp1VarF);
        kp1.a.a.b(11, lp1VarF.a);
    }

    @Override // defpackage.hy1
    public final void h() {
        ((NotificationManager) xz.a.getSystemService("notification")).cancel(5);
    }
}
