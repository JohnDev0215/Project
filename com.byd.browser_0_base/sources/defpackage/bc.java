package defpackage;

import android.os.PersistableBundle;
import org.chromium.chrome.browser.offlinepages.TriggerConditions;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: compiled from: BackgroundScheduler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bc {
    public static void a(TriggerConditions triggerConditions, long j, boolean z) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putLong("ScheduleTime", System.currentTimeMillis());
        persistableBundle.putBoolean("PowerConnected", triggerConditions.a);
        persistableBundle.putInt("BatteryPercentage", triggerConditions.b);
        boolean z2 = triggerConditions.c;
        persistableBundle.putBoolean("UnmeteredNetwork", z2);
        TaskInfo.a aVarA = TaskInfo.a(77, j, 604800000L);
        aVarA.c = z2 ? 2 : 1;
        aVarA.f = z;
        aVarA.e = true;
        aVarA.b = persistableBundle;
        aVarA.d = triggerConditions.a;
        ((jc) a8.E()).b(xz.a, new TaskInfo(aVarA));
    }
}
