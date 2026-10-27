package com.amazon.mp3.service.job.activity;

import android.os.Bundle;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.Session;

/* JADX INFO: loaded from: classes4.dex */
public abstract class JobWrapper {
    private boolean mIsFinished;
    private final Job mJob;
    private long mJobId = -1;
    private final Session mSession;

    public abstract void onJobFinished(final int resultCode, final Bundle resultBundle);

    protected JobWrapper(final Job job, final Session activity) {
        this.mJob = job;
        this.mSession = activity;
    }

    public void runJob() {
        this.mJobId = this.mSession.addJob(this.mJob);
    }

    public long getJobId() {
        return this.mJobId;
    }

    public boolean isFinished() {
        return this.mIsFinished;
    }

    protected final void notifyJobFinished(final int resultCode, final Bundle resultBundle) {
        onJobFinished(resultCode, resultBundle);
        this.mIsFinished = true;
    }
}
