package p;

import android.app.Notification;

/* JADX INFO: loaded from: classes.dex */
public abstract class ao1 {
    public static Notification a(Notification.Builder builder) {
        return builder.build();
    }

    public static Notification.Builder b(Notification.Builder builder, int i) {
        return builder.setPriority(i);
    }

    public static Notification.Builder c(Notification.Builder builder, CharSequence charSequence) {
        return builder.setSubText(charSequence);
    }

    public static Notification.Builder d(Notification.Builder builder, boolean z) {
        return builder.setUsesChronometer(z);
    }
}
