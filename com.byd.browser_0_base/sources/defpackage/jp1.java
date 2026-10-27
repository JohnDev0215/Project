package defpackage;

import J.N;
import android.content.Context;
import defpackage.fc;
import org.chromium.chrome.browser.notifications.NotificationTriggerScheduler;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: NotificationTriggerBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jp1 extends fk1 {
    public boolean f = true;

    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        boolean z;
        NotificationTriggerScheduler notificationTriggerScheduler = NotificationTriggerScheduler.getInstance();
        long j = a73Var.b.getLong("Timestamp");
        notificationTriggerScheduler.getClass();
        if (SharedPreferencesManager.getInstance().e(Long.MAX_VALUE, "notification_trigger_scheduler.next_trigger") != j) {
            z = false;
        } else {
            SharedPreferencesManager.getInstance().removeKey("notification_trigger_scheduler.next_trigger");
            z = true;
        }
        this.f = z;
        return z ? 0 : 2;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        NotificationTriggerScheduler.getInstance().getClass();
        N.M2E1scwJ();
        this.f = false;
        aVar.b(false);
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return this.f;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        return this.f;
    }
}
