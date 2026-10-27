package com.byd.music.utils.task.pool;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes5.dex */
public class TaskPriorityRunnable implements Runnable {
    private String name;
    private String oldThreadName;
    public final TaskPriority priority;
    private final Runnable runnable;

    public TaskPriorityRunnable(TaskPriority taskPriority, String str, Runnable runnable) {
        String str2;
        this.priority = taskPriority == null ? TaskPriority.DEFAULT : taskPriority;
        this.runnable = runnable;
        if (str == null) {
            str2 = null;
        } else {
            str2 = "Executor#" + str;
        }
        this.name = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!TextUtils.isEmpty(this.name) && TextUtils.isEmpty(this.oldThreadName)) {
            this.oldThreadName = Thread.currentThread().getName();
            Thread.currentThread().setName(this.name);
        }
        this.runnable.run();
        if (TextUtils.isEmpty(this.oldThreadName)) {
            return;
        }
        Thread.currentThread().setName(this.oldThreadName);
        this.oldThreadName = null;
    }
}
