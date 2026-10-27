package defpackage;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.Service;
import android.util.Log;

/* JADX INFO: compiled from: ApiHelperForS.java */
/* JADX INFO: loaded from: classes.dex */
public final class g6 {
    public static void a(Service service, int i, Notification notification, int i2) {
        try {
            service.startForeground(i, notification, i2);
        } catch (ForegroundServiceStartNotAllowedException e) {
            Log.e("cr_ApiHelperForS", "Cannot run service as foreground: " + e + " for notification channel " + notification.getChannelId() + " notification id " + i);
        }
    }
}
