package org.chromium.chrome.browser.sharing.sms_fetcher;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import defpackage.C0175b;
import defpackage.bq;
import defpackage.h90;
import defpackage.o8;
import defpackage.oj2;
import defpackage.rl2;
import defpackage.sk;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes2.dex */
public class SmsFetcherMessageHandler {
    public static long a;
    public static String b;
    public static String c;

    public static final class NotificationReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean z = SmsFetcherMessageHandler.a == 0;
            oj2.O("Sharing.SmsFetcherTapWithChromeDestroyed", z);
            new NotificationManagerProxyImpl(xz.a).d(15, "SmsFetcher");
            if (z) {
                return;
            }
            action.getClass();
            if (action.equals("sms_fetcher_notification.confirm")) {
                N.MXRanUGo(SmsFetcherMessageHandler.a, SmsFetcherMessageHandler.b, SmsFetcherMessageHandler.c);
            } else if (action.equals("sms_fetcher_notification.cancel")) {
                N.MOhxjMCQ(SmsFetcherMessageHandler.a, SmsFetcherMessageHandler.b, SmsFetcherMessageHandler.c);
            }
        }
    }

    @CalledByNative
    public static void dismissNotification() {
        new NotificationManagerProxyImpl(xz.a).d(15, "SmsFetcher");
    }

    @CalledByNative
    public static void reset() {
        a = 0L;
        b = null;
        c = null;
    }

    @CalledByNative
    public static void showNotification(String str, String str2, String str3, String str4, long j) {
        String string;
        String str5;
        b = str2;
        c = str3;
        a = j;
        Context context = xz.a;
        oj2.O("Sharing.SmsFetcherScreenOnAndUnlocked", h90.c(context));
        o8 o8VarB = o8.b(context, 0, new Intent(context, (Class<?>) NotificationReceiver.class).setAction("sms_fetcher_notification.confirm"), 134217728, false);
        o8 o8VarB2 = o8.b(context, 0, new Intent(context, (Class<?>) NotificationReceiver.class).setAction("sms_fetcher_notification.cancel"), 134217728, false);
        context.getResources();
        Resources resources = xz.a.getResources();
        sk skVar = bq.a;
        String string2 = N.M09VlOh_("WebOtpCrossDeviceSimpleString") ? str3 == null ? resources.getString(R$string.sms_fetcher_notification_title_simple_string, str, str2) : resources.getString(R$string.sms_fetcher_notification_title_simple_string, str, str3) : resources.getString(R$string.sms_fetcher_notification_title, str, str4);
        Resources resources2 = xz.a.getResources();
        if (!N.M09VlOh_("WebOtpCrossDeviceSimpleString")) {
            string = str3 == null ? resources2.getString(R$string.sms_fetcher_notification_text, str2) : resources2.getString(R$string.sms_fetcher_notification_text_for_embedded_frame, str2, str3);
        } else {
            if (str3 == null) {
                str5 = str4;
                rl2.a(29, "SmsFetcher", 15, null, o8VarB2, o8VarB, o8VarB2, string2, str5, R$drawable.ic_chrome, 0, R$color.default_icon_color_accent1_baseline, false);
            }
            string = C0175b.k(str2, " · ", str4);
        }
        str5 = string;
        rl2.a(29, "SmsFetcher", 15, null, o8VarB2, o8VarB, o8VarB2, string2, str5, R$drawable.ic_chrome, 0, R$color.default_icon_color_accent1_baseline, false);
    }
}
