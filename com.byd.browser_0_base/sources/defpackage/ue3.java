package defpackage;

import android.app.Notification;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$drawable;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: TracingNotificationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ue3 {
    public static np1 a;
    public static int b;

    public static np1 a() {
        qq qqVarD = n3.D("browser", null);
        qqVarD.a.v = 1;
        qqVarD.p(R$drawable.ic_chrome);
        NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
        notificationCompat$Builder.j = false;
        notificationCompat$Builder.r = true;
        return qqVarD;
    }

    public static void b(Notification notification) {
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(xz.a);
        if (notification == null) {
            Log.e("cr_NotifManagerProxy", "Failed to create notification.");
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.notify(tag, id, notification)", null);
        try {
            notificationManagerProxyImpl.a.b("tracing_status", 100, notification);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
