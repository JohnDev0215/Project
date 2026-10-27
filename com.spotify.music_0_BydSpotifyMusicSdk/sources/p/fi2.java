package p;

import android.app.Notification;
import android.app.Service;

/* JADX INFO: loaded from: classes.dex */
public abstract class fi2 {
    public static void a(Service service, int i, Notification notification, int i2) {
        service.startForeground(i, notification, i2);
    }
}
