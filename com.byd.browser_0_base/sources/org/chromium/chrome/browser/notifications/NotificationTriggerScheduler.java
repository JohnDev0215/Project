package org.chromium.chrome.browser.notifications;

import android.util.Log;
import defpackage.i83;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationTriggerScheduler {
    public final a a;

    public interface a {
    }

    public static class b {
        public static final NotificationTriggerScheduler a = new NotificationTriggerScheduler(new i83(5));
    }

    public NotificationTriggerScheduler(i83 i83Var) {
        this.a = i83Var;
    }

    @CalledByNative
    public static NotificationTriggerScheduler getInstance() {
        return b.a;
    }

    @CalledByNative
    public void schedule(long j) {
        ((i83) this.a).getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jE = SharedPreferencesManager.getInstance().e(Long.MAX_VALUE, "notification_trigger_scheduler.next_trigger");
        if (j < jE) {
            SharedPreferencesManager.getInstance().n(j, "notification_trigger_scheduler.next_trigger");
        } else if (jE >= jCurrentTimeMillis) {
            return;
        } else {
            j = jE;
        }
        Math.max(j - jCurrentTimeMillis, 0L);
        Log.e("cr_NotifTrigBT", "Scheduling BackgroundTasks with exact timing is unsupported");
    }
}
