package org.chromium.chrome.browser.notifications.scheduler;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import defpackage.kp1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.q11;
import defpackage.qq;
import defpackage.sp;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayAgent {

    public static class NotificationData {
        public final String a;
        public final String b;
        public final HashMap<Integer, b> c = new HashMap<>();
        public final ArrayList<a> d = new ArrayList<>();

        public NotificationData(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    public static final class Receiver extends BroadcastReceiver {

        public class a extends n3 {
            public final /* synthetic */ Intent H;

            public a(Intent intent) {
                this.H = intent;
            }

            @Override // defpackage.n3, defpackage.nj
            public final void K() {
                Intent intent = this.H;
                int iL = q11.l(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_INTENT_TYPE", -1);
                String strR = q11.r(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_GUID");
                int iL2 = q11.l(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_SCHEDULER_CLIENT_TYPE ", 0);
                if (iL == 0) {
                    N.MJnQd5Zg(iL2, 0, strR, 0, null);
                    new NotificationManagerProxyImpl(xz.a).d(strR.hashCode(), "NotificationSchedulerDisplayAgent");
                } else if (iL == 1) {
                    N.MJnQd5Zg(iL2, 1, strR, q11.l(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_TYPE", 0), q11.r(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_ID"));
                    new NotificationManagerProxyImpl(xz.a).d(strR.hashCode(), "NotificationSchedulerDisplayAgent");
                } else {
                    if (iL != 2) {
                        return;
                    }
                    N.MJnQd5Zg(iL2, 2, strR, 0, null);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            a aVar = new a(intent);
            sp.a().c(aVar);
            sp.a().b(true, aVar);
        }
    }

    public static class SystemData {
        public final int a;
        public final String b;

        public SystemData(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    public static class a {
        public final String a;
        public final int b;
        public final String c;

        public a(int i, String str, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }
    }

    public static Intent a(Context context, int i, SystemData systemData) {
        Intent intent = new Intent(context, (Class<?>) Receiver.class);
        intent.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_INTENT_TYPE", i);
        intent.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_SCHEDULER_CLIENT_TYPE ", systemData.a);
        intent.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_GUID", systemData.b);
        return intent;
    }

    @CalledByNative
    public static void addButton(NotificationData notificationData, String str, int i, String str2) {
        notificationData.d.add(new a(i, str, str2));
    }

    @CalledByNative
    public static void addIcon(NotificationData notificationData, int i, Bitmap bitmap, int i2) {
        if (i2 != 0) {
            notificationData.c.put(Integer.valueOf(i), new b(i2));
        } else {
            notificationData.c.put(Integer.valueOf(i), new b(bitmap));
        }
    }

    @CalledByNative
    public static NotificationData buildNotificationData(String str, String str2) {
        return new NotificationData(str, str2);
    }

    @CalledByNative
    public static SystemData buildSystemData(int i, String str) {
        return new SystemData(i, str);
    }

    @CalledByNative
    public static void showNotification(NotificationData notificationData, SystemData systemData) {
        int i = systemData.a;
        String str = i == 5 ? "chrome_tips" : "browser";
        int i2 = i == 5 ? 34 : -1;
        Context context = xz.a;
        String str2 = systemData.b;
        qq qqVarD = n3.D(str, new o8(i2, str2.hashCode(), "NotificationSchedulerDisplayAgent"));
        qqVarD.k(notificationData.a);
        qqVarD.j(notificationData.b);
        HashMap<Integer, b> map = notificationData.c;
        boolean zContainsKey = map.containsKey(1);
        if (!zContainsKey || map.get(1).a == null) {
            int i3 = R$drawable.ic_chrome;
            if (zContainsKey && map.get(1).b != 0) {
                i3 = map.get(1).b;
            }
            qqVarD.p(i3);
        } else {
            qqVarD.q(Icon.createWithBitmap(map.get(1).a));
        }
        if (map.containsKey(2) && map.get(2).a != null) {
            qqVarD.m(map.get(2).a);
        }
        Intent intentA = a(context, 0, systemData);
        int iHashCode = str2.hashCode();
        qqVarD.c(o8.b(context, (iHashCode * 31) + 0 + iHashCode, intentA, 134217728, false));
        Intent intentA2 = a(context, 2, systemData);
        int iHashCode2 = str2.hashCode();
        qqVarD.a(o8.b(context, (iHashCode2 * 31) + 2 + iHashCode2, intentA2, 134217728, false));
        int i4 = 0;
        while (true) {
            ArrayList<a> arrayList = notificationData.d;
            if (i4 >= arrayList.size()) {
                lp1 lp1VarF = qqVarD.f();
                new NotificationManagerProxyImpl(xz.a).c(lp1VarF);
                kp1.a.a.b(i2, lp1VarF.a);
                return;
            } else {
                a aVar = arrayList.get(i4);
                Intent intentA3 = a(context, 1, systemData);
                intentA3.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_TYPE", aVar.b);
                intentA3.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_ID", aVar.c);
                int iHashCode3 = str2.hashCode();
                qqVarD.b(0, aVar.a, o8.b(context, (iHashCode3 * 31) + 1 + iHashCode3, intentA3, 134217728, false), -1);
                i4++;
            }
        }
    }

    public static class b {
        public final Bitmap a;
        public final int b;

        public b() {
            this.a = null;
            this.b = 0;
        }

        public b(Bitmap bitmap) {
            this.a = bitmap;
            this.b = 0;
        }

        public b(int i) {
            this.a = null;
            this.b = i;
        }
    }
}
