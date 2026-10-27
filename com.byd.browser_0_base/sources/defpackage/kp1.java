package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: NotificationUmaTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kp1 {
    public final SharedPreferencesManager a = SharedPreferencesManager.getInstance();
    public final ro1 b = new ro1(xz.a);

    /* JADX INFO: compiled from: NotificationUmaTracker.java */
    public static class a {
        public static final kp1 a = new kp1();
    }

    public static kp1 a() {
        return a.a;
    }

    public static void c(long j, String str) {
        if (j == -1) {
            return;
        }
        oj2.S((int) a8.r((System.currentTimeMillis() - j) / 60000, 0L, 2147483647L), 1, 10080, 50, str);
    }

    public static void d(int i) {
        oj2.U(i, 6, "Mobile.SystemNotification.Permission.StartupState");
    }

    public final void b(int i, Notification notification) {
        if (i == -1 || notification == null) {
            return;
        }
        String channelId = notification.getChannelId();
        ro1 ro1Var = this.b;
        boolean zA = ro1Var.a();
        SharedPreferencesManager sharedPreferencesManager = this.a;
        if (!zA) {
            int iD = sharedPreferencesManager.d(-1, "NotificationUmaTracker.LastShownNotificationType");
            if (iD != -1) {
                sharedPreferencesManager.removeKey("NotificationUmaTracker.LastShownNotificationType");
                if (iD != -1) {
                    oj2.U(iD, 39, "Mobile.SystemNotification.BlockedAfterShown");
                }
            }
            if (i == -1) {
                return;
            }
            oj2.U(i, 39, "Mobile.SystemNotification.Blocked");
            return;
        }
        if (channelId != null) {
            NotificationChannel notificationChannel = ro1Var.b.getNotificationChannel(channelId);
            io1 io1Var = notificationChannel != null ? new io1(notificationChannel) : null;
            if (io1Var != null && io1Var.a == 0) {
                if (i == -1) {
                    return;
                }
                oj2.U(i, 39, "Mobile.SystemNotification.ChannelBlocked");
                return;
            }
        }
        sharedPreferencesManager.m(i, "NotificationUmaTracker.LastShownNotificationType");
        if (i == -1) {
            return;
        }
        oj2.U(i, 39, "Mobile.SystemNotification.Shown");
    }
}
