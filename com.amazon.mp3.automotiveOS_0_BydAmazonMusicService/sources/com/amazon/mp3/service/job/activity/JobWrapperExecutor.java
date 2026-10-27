package com.amazon.mp3.service.job.activity;

import android.os.Bundle;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.activity.JobWrapper;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes4.dex */
public class JobWrapperExecutor<T extends JobWrapper> {
    private T mCurrent;
    private final Queue<T> mJobQueue = new LinkedList();

    public void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        T t = this.mCurrent;
        if (t != null && jobId == t.getJobId()) {
            this.mCurrent.notifyJobFinished(resultCode, resultBundle);
            runNext();
        }
    }

    public void runAllAsync() {
        runNext();
    }

    public void add(final T job) {
        this.mJobQueue.add(job);
    }

    public void dropAll() {
        this.mJobQueue.clear();
        this.mCurrent = null;
    }

    protected T getCurrent() {
        return this.mCurrent;
    }

    protected void runNext() {
        T t = this.mCurrent;
        if (t == null || t.isFinished()) {
            if (this.mJobQueue.isEmpty()) {
                this.mCurrent = null;
                return;
            }
            T tRemove = this.mJobQueue.remove();
            this.mCurrent = tRemove;
            tRemove.runJob();
        }
    }
}
