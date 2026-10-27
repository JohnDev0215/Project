package org.chromium.chrome.browser.background_sync;

import android.os.PersistableBundle;
import defpackage.a8;
import defpackage.jc;
import defpackage.xz;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundSyncBackgroundTaskScheduler {
    public static BackgroundSyncBackgroundTaskScheduler b;
    public final b<a> a = new b<>();

    public interface a {
        void a();

        void b();
    }

    @CalledByNative
    public static BackgroundSyncBackgroundTaskScheduler getInstance() {
        if (b == null) {
            b = new BackgroundSyncBackgroundTaskScheduler();
        }
        return b;
    }

    @CalledByNative
    public void cancelOneOffTask(int i) {
        ((jc) a8.E()).a(xz.a, i != 0 ? i != 1 ? -1 : 105 : 102);
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }

    @CalledByNative
    public boolean scheduleOneOffTask(int i, long j) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putLong("SoonestWakeupTime", System.currentTimeMillis() + j);
        TaskInfo.b.a aVar = new TaskInfo.b.a();
        aVar.a = j;
        aVar.c = true;
        aVar.b = Long.MAX_VALUE;
        aVar.d = true;
        TaskInfo.b bVar = new TaskInfo.b(aVar);
        TaskInfo.a aVar2 = new TaskInfo.a(i != 0 ? i != 1 ? -1 : 105 : 102);
        aVar2.g = bVar;
        aVar2.c = 1;
        aVar2.f = true;
        aVar2.e = true;
        aVar2.b = persistableBundle;
        boolean zB = ((jc) a8.E()).b(xz.a, new TaskInfo(aVar2));
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar3 = (b.a) it;
            if (!aVar3.hasNext()) {
                return zB;
            }
            ((a) aVar3.next()).b();
        }
    }
}
