package org.chromium.chrome.browser.offlinepages;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.oj2;
import defpackage.q11;
import defpackage.qq;
import defpackage.sp;
import defpackage.wz;
import defpackage.xz;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.offlinepages.AutoFetchNotifier;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: loaded from: classes2.dex */
public class AutoFetchNotifier {

    public static class CompleteNotificationReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            int iL = q11.l(intent, "notification_action", 5);
            if (iL == 4 || iL == 3) {
                oj2.U(iL, 5, "OfflinePages.AutoFetch.CompleteNotificationAction");
                if (iL != 4) {
                    return;
                }
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(q11.r(intent, "org.chromium.chrome.browser.offlinepages.URL")));
                intent2.putExtras(intent);
                intent2.setPackage(context.getPackageName());
                intent2.setFlags(268435456);
                org.chromium.chrome.browser.b.A(null, intent2, null);
            }
        }
    }

    public static class InProgressCancelReceiver extends BroadcastReceiver {
        public static final /* synthetic */ int a = 0;

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            int iL = q11.l(intent, "notification_action", 5);
            if (iL == 2 || iL == 3) {
                SharedPreferencesManager.getInstance().m(iL, "offline_auto_fetch_user_cancel_action_in_progress");
                sp.a().f(new wz(17));
                ((NotificationManager) xz.a.getSystemService("notification")).cancel("OfflinePageAutoFetchInProgressNotification", 0);
                SharedPreferencesManager.getInstance().l("offline_auto_fetch_showing_in_progress", false);
            }
        }
    }

    @CalledByNative
    public static boolean autoFetchInProgressNotificationCanceled() {
        return SharedPreferencesManager.getInstance().d(5, "offline_auto_fetch_user_cancel_action_in_progress") != 5;
    }

    @CalledByNative
    public static void cancellationComplete() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        int iD = sharedPreferencesManager.d(5, "offline_auto_fetch_user_cancel_action_in_progress");
        if (iD == 5) {
            return;
        }
        oj2.U(iD, 5, "OfflinePages.AutoFetch.InProgressNotificationAction");
        sharedPreferencesManager.removeKey("offline_auto_fetch_user_cancel_action_in_progress");
    }

    @CalledByNative
    public static void showCompleteNotification(final String str, final String str2, final String str3, final int i, final long j) {
        d.b(j, 1, new Callback() { // from class: y9
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                Context context = xz.a;
                Intent intent = new Intent(context, (Class<?>) AutoFetchNotifier.CompleteNotificationReceiver.class);
                intent.putExtra("org.chromium.chrome.browser.offlinepages.URL", str3);
                intent.putExtra("REUSE_TAB_ORIGINAL_URL", str2);
                b.y(((LoadUrlParams) obj).f, intent);
                intent.putExtra("REUSE_TAB_MATCHING_ID", i);
                intent.putExtra("com.android.browser.application_id", context.getPackageName());
                intent.putExtra("notification_action", 4);
                intent.putExtra("org.chromium.chrome.browser.tab_launch_type", 2);
                intent.setPackage(context.getPackageName());
                int i2 = (int) j;
                o8 o8VarB = o8.b(context, i2, intent, 0, false);
                Intent intent2 = new Intent(context, (Class<?>) AutoFetchNotifier.CompleteNotificationReceiver.class);
                intent2.putExtra("com.android.browser.application_id", context.getPackageName());
                intent2.putExtra("notification_action", 3);
                intent2.setPackage(context.getPackageName());
                qq qqVarD = n3.D("downloads", new o8(14, i2, "OfflinePageAutoFetchNotification"));
                qqVarD.h(true);
                qqVarD.c(o8VarB);
                qq qqVar = qqVarD;
                qqVar.k(str);
                qqVar.j(context.getString(R$string.offline_pages_auto_fetch_ready_notification_text));
                NotificationCompat$Builder notificationCompat$Builder = qqVar.a;
                notificationCompat$Builder.p = "OfflinePageAutoFetchNotification";
                notificationCompat$Builder.i = -1;
                qqVar.p(R$drawable.ic_chrome);
                lp1 lp1VarF = ((np1) qqVar.a(o8.b(context, 0, intent2, 0, false))).f();
                new NotificationManagerProxyImpl(context).c(lp1VarF);
                kp1.a.a.b(14, lp1VarF.a);
                oj2.U(0, 5, "OfflinePages.AutoFetch.CompleteNotificationAction");
            }
        }, Profile.d());
    }

    @CalledByNative
    public static void showInProgressNotification(int i) {
        Context context = xz.a;
        Intent intent = new Intent(context, (Class<?>) InProgressCancelReceiver.class);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("notification_action", 2);
        intent.setPackage(context.getPackageName());
        Intent intent2 = new Intent(context, (Class<?>) InProgressCancelReceiver.class);
        intent2.putExtra("com.android.browser.application_id", context.getPackageName());
        intent2.putExtra("notification_action", 3);
        intent2.setPackage(context.getPackageName());
        String quantityString = context.getResources().getQuantityString(R$plurals.offline_pages_auto_fetch_in_progress_notification_text, i);
        qq qqVarD = n3.D("downloads", new o8(14, 0, "OfflinePageAutoFetchInProgressNotification"));
        qqVarD.k(quantityString);
        NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
        notificationCompat$Builder.p = "OfflinePageAutoFetchNotification";
        notificationCompat$Builder.i = -1;
        qqVarD.p(R$drawable.ic_chrome);
        qqVarD.b(0, context.getString(R$string.cancel), o8.b(context, 0, intent, 0, false), 16);
        qqVarD.a(o8.b(context, 0, intent2, 0, false));
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(context);
        lp1 lp1VarF = qqVarD.f();
        notificationManagerProxyImpl.c(lp1VarF);
        kp1.a.a.b(14, lp1VarF.a);
        oj2.U(0, 5, "OfflinePages.AutoFetch.InProgressNotificationAction");
    }

    @CalledByNative
    public static void updateInProgressNotificationCountIfShowing(int i) {
        if (i != 0) {
            if (SharedPreferencesManager.getInstance().readBoolean("offline_auto_fetch_showing_in_progress", false)) {
                showInProgressNotification(i);
            }
        } else {
            if (SharedPreferencesManager.getInstance().readBoolean("offline_auto_fetch_showing_in_progress", false)) {
                oj2.U(1, 5, "OfflinePages.AutoFetch.InProgressNotificationAction");
            }
            ((NotificationManager) xz.a.getSystemService("notification")).cancel("OfflinePageAutoFetchInProgressNotification", 0);
            SharedPreferencesManager.getInstance().l("offline_auto_fetch_showing_in_progress", false);
        }
    }
}
