package org.chromium.chrome.browser.share.send_tab_to_self;

import J.N;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.q11;
import defpackage.qq;
import defpackage.xz;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationManager {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean hideNotification(java.lang.String r8) {
        /*
            org.chromium.chrome.browser.share.send_tab_to_self.a$a r0 = org.chromium.chrome.browser.share.send_tab_to_self.a.a(r8)
            r1 = 0
            if (r8 != 0) goto L8
            goto L12
        L8:
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r2 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            org.chromium.chrome.browser.share.send_tab_to_self.a$a r8 = org.chromium.chrome.browser.share.send_tab_to_self.a.a(r8)
            if (r8 != 0) goto L14
        L12:
            r8 = r1
            goto L52
        L14:
            r3 = 0
            java.lang.String r4 = "send_tab_to_self.notification.active"
            java.util.Set r3 = r2.i(r4, r3)
            if (r3 != 0) goto L23
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            goto L29
        L23:
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>(r3)
            r3 = r5
        L29:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int r6 = r8.c
            r5.append(r6)
            java.lang.String r6 = "_"
            r5.append(r6)
            int r7 = r8.a
            r5.append(r7)
            r5.append(r6)
            java.lang.String r8 = r8.b
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            boolean r8 = r3.remove(r8)
            if (r8 == 0) goto L52
            r2.p(r4, r3)
        L52:
            if (r8 != 0) goto L55
            return r1
        L55:
            android.content.Context r8 = defpackage.xz.a
            org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl r1 = new org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl
            r1.<init>(r8)
            java.lang.String r8 = "SendTabToSelf"
            int r0 = r0.a
            r1.d(r0, r8)
            r8 = 1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.share.send_tab_to_self.NotificationManager.hideNotification(java.lang.String):boolean");
    }

    @CalledByNative
    public static boolean showNotification(String str, String str2, String str3, String str4, long j, Class<? extends BroadcastReceiver> cls) {
        if (a.a(str) != null) {
            return false;
        }
        Context context = xz.a;
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(context);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        int iD = sharedPreferencesManager.d(-1, "send_tab_to_self.notification.next_id");
        if (iD >= 2147483646) {
            iD = -1;
        }
        int i = iD + 1;
        sharedPreferencesManager.m(i, "send_tab_to_self.notification.next_id");
        Uri uri = Uri.parse(str2);
        o8 o8VarB = o8.b(context, i, new Intent(context, cls).setData(uri).setAction("send_tab_to_self.tap").putExtra("send_tab_to_self.notification.guid", str), 0, false);
        o8 o8VarB2 = o8.b(context, i, new Intent(context, cls).setData(uri).setAction("send_tab_to_self.dismiss").putExtra("send_tab_to_self.notification.guid", str), 0, false);
        String string = context.getResources().getString(R$string.send_tab_to_self_notification_context_text, uri.getHost(), str4);
        qq qqVarD = n3.D("sharing", new o8(15, i, "SendTabToSelf"));
        qqVarD.c(o8VarB);
        qqVarD.a(o8VarB2);
        qqVarD.k(str3);
        qqVarD.j(string);
        NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
        notificationCompat$Builder.p = "SendTabToSelf";
        notificationCompat$Builder.i = 1;
        notificationCompat$Builder.A.vibrate = new long[0];
        qqVarD.p(R$drawable.ic_chrome);
        qqVarD.l(-1);
        lp1 lp1VarF = qqVarD.f();
        notificationManagerProxyImpl.c(lp1VarF);
        kp1.a.a.b(15, lp1VarF.a);
        SharedPreferencesManager sharedPreferencesManager2 = SharedPreferencesManager.getInstance();
        Set<String> setI = sharedPreferencesManager2.i("send_tab_to_self.notification.active", null);
        HashSet hashSet = setI == null ? new HashSet() : new HashSet(setI);
        if (hashSet.add("1_" + i + "_" + str)) {
            sharedPreferencesManager2.p("send_tab_to_self.notification.active", hashSet);
        }
        if (j != Long.MAX_VALUE) {
            ((AlarmManager) context.getSystemService("alarm")).set(1, j, PendingIntent.getBroadcast(context, i, new Intent(context, cls).setData(Uri.parse(str2)).setAction("send_tab_to_self.timeout").putExtra("send_tab_to_self.notification.guid", str), 134217728 | q11.d(false)));
        }
        N.MMVA7qry();
        return true;
    }
}
