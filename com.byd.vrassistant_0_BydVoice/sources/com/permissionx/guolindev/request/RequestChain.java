package com.permissionx.guolindev.request;

/* JADX INFO: loaded from: classes3.dex */
public class RequestChain {
    private BaseTask headTask;
    private BaseTask tailTask;

    public void addTaskToChain(BaseTask baseTask) {
        if (this.headTask == null) {
            this.headTask = baseTask;
        }
        BaseTask baseTask2 = this.tailTask;
        if (baseTask2 != null) {
            baseTask2.next = baseTask;
        }
        this.tailTask = baseTask;
    }

    public void runTask() {
        this.headTask.request();
    }
}
