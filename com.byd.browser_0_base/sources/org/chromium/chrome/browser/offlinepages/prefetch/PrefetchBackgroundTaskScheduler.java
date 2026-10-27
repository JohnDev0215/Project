package org.chromium.chrome.browser.offlinepages.prefetch;

import android.os.PersistableBundle;
import defpackage.a8;
import defpackage.jc;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class PrefetchBackgroundTaskScheduler {
    public static void a(int i, boolean z) {
        TaskInfo.a aVarA = TaskInfo.a(78, ((z ? 20L : 900L) + ((long) i)) * 1000, 604800000L);
        aVarA.c = 2;
        aVarA.e = true;
        aVarA.f = true;
        if (z) {
            aVarA.c = 1;
            PersistableBundle persistableBundle = new PersistableBundle(1);
            persistableBundle.putBoolean("limitlessPrefetching", true);
            aVarA.b = persistableBundle;
        }
        ((jc) a8.E()).b(xz.a, aVarA.a());
    }

    @CalledByNative
    public static void cancelTask() {
        ((jc) a8.E()).a(xz.a, 78);
    }

    @CalledByNative
    public static void scheduleTask(int i) {
        a(i, false);
    }

    @CalledByNative
    public static void scheduleTaskLimitless(int i) {
        a(i, true);
    }
}
