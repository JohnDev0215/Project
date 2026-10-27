package org.chromium.components.background_task_scheduler.internal;

import android.os.PersistableBundle;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class TaskInfoBridge {
    @CalledByNative
    public static TaskInfo.TimingInfo createOneOffInfo(long j, long j2, boolean z) {
        TaskInfo.b.a aVar = new TaskInfo.b.a();
        aVar.b = j2;
        aVar.d = z;
        if (j > 0) {
            aVar.a = j;
            aVar.c = true;
        }
        return new TaskInfo.b(aVar);
    }

    @CalledByNative
    public static TaskInfo.TimingInfo createPeriodicInfo(long j, long j2, boolean z) {
        TaskInfo.c.a aVar = new TaskInfo.c.a();
        aVar.a = j;
        aVar.d = z;
        if (j2 > 0) {
            aVar.b = j2;
            aVar.c = true;
        }
        return new TaskInfo.c(aVar);
    }

    @CalledByNative
    public static TaskInfo createTaskInfo(int i, TaskInfo.TimingInfo timingInfo, String str, int i2, boolean z, boolean z2, boolean z3) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("serialized_task_extras", str);
        TaskInfo.a aVar = new TaskInfo.a(i);
        aVar.g = timingInfo;
        aVar.c = i2;
        aVar.d = z;
        aVar.f = z3;
        aVar.e = z2;
        aVar.b = persistableBundle;
        return new TaskInfo(aVar);
    }
}
