package org.chromium.components.background_task_scheduler.internal;

import defpackage.a8;
import defpackage.jc;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class NativeTaskScheduler {
    @CalledByNative
    public static void cancel(int i) {
        ((jc) a8.E()).a(xz.a, i);
    }

    @CalledByNative
    public static boolean schedule(TaskInfo taskInfo) {
        return ((jc) a8.E()).b(xz.a, taskInfo);
    }
}
