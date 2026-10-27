package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import java.util.Objects;

/* JADX INFO: renamed from: Ad, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0003Ad implements Runnable {
    public final /* synthetic */ C1773xd s;

    public RunnableC0003Ad(C1773xd c1773xd) {
        this.s = c1773xd;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1773xd c1773xd = this.s;
        AbstractC1278oc abstractC1278oc = c1773xd.d;
        Context context = c1773xd.c;
        Objects.requireNonNull(abstractC1278oc);
        if (AbstractC1388qc.b.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(10436);
            }
        } catch (SecurityException unused) {
        }
    }
}
