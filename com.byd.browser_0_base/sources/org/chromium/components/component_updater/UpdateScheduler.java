package org.chromium.components.component_updater;

import defpackage.a8;
import defpackage.fc;
import defpackage.jc;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateScheduler {
    public static UpdateScheduler d;
    public fc.a a;
    public long b;
    public long c;

    @CalledByNative
    public static UpdateScheduler getInstance() {
        if (d == null) {
            d = new UpdateScheduler();
        }
        return d;
    }

    public final void a(long j) {
        if (this.a != null) {
            return;
        }
        TaskInfo.a aVarA = TaskInfo.a(2, j, 2147483647L);
        aVarA.f = true;
        aVarA.c = 2;
        aVarA.e = true;
        TaskInfo taskInfoA = aVarA.a();
        ((jc) a8.E()).b(xz.a, taskInfoA);
    }

    @CalledByNative
    public final void cancelTask() {
        ((jc) a8.E()).a(xz.a, 2);
    }

    @CalledByNative
    public final void finishTask(boolean z) {
        this.a.b(false);
        this.a = null;
        if (z) {
            a(this.c);
        }
    }

    @CalledByNative
    public final void schedule(long j, long j2) {
        this.c = j2;
        a(j);
    }

    @CalledByNative
    public final void setNativeScheduler(long j) {
        this.b = j;
    }
}
