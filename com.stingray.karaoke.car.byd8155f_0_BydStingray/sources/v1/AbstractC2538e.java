package v1;

import android.app.Notification;

/* JADX INFO: renamed from: v1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2538e {
    public static void a(Notification.Action.Builder builder) {
        builder.setAuthenticationRequired(false);
    }

    public static void b(Notification.Builder builder, int i7) {
        builder.setForegroundServiceBehavior(i7);
    }
}
