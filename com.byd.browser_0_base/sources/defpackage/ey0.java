package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.incognito.IncognitoNotificationService;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: IncognitoNotificationPresenceController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ey0 implements hz0 {
    @Override // defpackage.hz0
    public final void f() {
        Context context = xz.a;
        String string = context.getResources().getString(R$string.close_all_incognito_notification);
        String string2 = context.getResources().getString(R$string.close_all_incognito_notification_title);
        qq qqVarD = n3.D("incognito", new o8(2, 100, "incognito_tabs_open"));
        qqVarD.k(string2);
        Intent intent = new Intent(context, (Class<?>) IncognitoNotificationService.class);
        intent.setAction("com.google.android.apps.chrome.incognito.CLOSE_ALL_INCOGNITO");
        int iD = q11.d(false) | 134217728;
        qqVarD.c(new o8(PendingIntent.getService(context, 0, intent, iD), iD, 0));
        qq qqVar = qqVarD;
        qqVar.j(string);
        qqVar.o(true);
        NotificationCompat$Builder notificationCompat$Builder = qqVar.a;
        notificationCompat$Builder.v = -1;
        qqVar.p(R$drawable.incognito_simple);
        notificationCompat$Builder.j = false;
        notificationCompat$Builder.r = true;
        notificationCompat$Builder.p = "Incognito";
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(context);
        lp1 lp1VarF = qqVar.f();
        notificationManagerProxyImpl.c(lp1VarF);
        kp1.a.a.b(2, lp1VarF.a);
        vd1.j.E();
    }

    @Override // defpackage.hz0
    public final void q() {
        if (n3.J()) {
            return;
        }
        n3.F();
    }
}
