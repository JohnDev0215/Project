package org.chromium.chrome.browser.announcement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.q11;
import defpackage.qq;
import defpackage.sp;
import defpackage.xz;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes.dex */
public class AnnouncementNotificationManager {

    public static final class Receiver extends BroadcastReceiver {

        public class a extends n3 {
            public final /* synthetic */ Intent H;
            public final /* synthetic */ Context I;

            public a(Intent intent, Context context) {
                this.H = intent;
                this.I = context;
            }

            @Override // defpackage.n3, defpackage.nj
            public final void K() {
                Intent intent = this.H;
                int iL = q11.l(intent, "org.chromium.chrome.browser.announcement.EXTRA_INTENT_TYPE", 0);
                String strR = q11.r(intent, "org.chromium.chrome.browser.announcement.EXTRA_URL");
                Context context = this.I;
                if (iL == 1) {
                    CustomTabActivity.i3(context, strR);
                    return;
                }
                if (iL == 3) {
                    new NotificationManagerProxyImpl(xz.a).d(100, "announcement_notification");
                } else {
                    if (iL != 4) {
                        return;
                    }
                    CustomTabActivity.i3(context, strR);
                    new NotificationManagerProxyImpl(xz.a).d(100, "announcement_notification");
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            a aVar = new a(intent, context);
            sp.a().c(aVar);
            sp.a().b(true, aVar);
        }
    }

    public static o8 a(int i, Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) Receiver.class);
        intent.putExtra("org.chromium.chrome.browser.announcement.EXTRA_INTENT_TYPE", i);
        intent.putExtra("org.chromium.chrome.browser.announcement.EXTRA_URL", str);
        return o8.b(context, i, intent, 134217728, false);
    }

    @CalledByNative
    public static boolean isFirstRun() {
        return !z7.t() || z7.p;
    }

    @CalledByNative
    public static void showNotification(String str) {
        Context context = xz.a;
        qq qqVarD = n3.D("announcement", new o8(21, 100, "announcement_notification"));
        qqVarD.k(context.getString(R$string.tos_notification_title));
        qqVarD.c(a(1, context, str));
        qqVarD.a(a(2, context, str));
        qq qqVar = qqVarD;
        qqVar.j(context.getString(R$string.tos_notification_body_text));
        qqVar.p(R$drawable.ic_chrome);
        NotificationCompat$Builder notificationCompat$Builder = qqVar.a;
        notificationCompat$Builder.j = false;
        qqVar.h(true);
        notificationCompat$Builder.r = true;
        qqVar.b(0, context.getString(R$string.tos_notification_ack_button_text), a(3, context, str), 13);
        qqVar.b(0, context.getString(R$string.tos_notification_review_button_text), a(4, context, str), 14);
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(context);
        lp1 lp1VarF = qqVar.f();
        notificationManagerProxyImpl.c(lp1VarF);
        kp1.a.a.b(21, lp1VarF.a);
    }
}
