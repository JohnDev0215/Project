package com.amazon.mp3.service.job.activity;

import android.content.Intent;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.Session;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ActivityJobWrapper extends JobWrapper {
    private final int mActivityRequestCode;
    private boolean mIsFinished;

    public abstract void onActivityResult(final int resultCode, final Intent data);

    public abstract boolean waitForActivityResult();

    protected ActivityJobWrapper(final Job job, final Session session, final int activityRequestCode) {
        super(job, session);
        this.mActivityRequestCode = activityRequestCode;
    }

    public int getActivityRequestCode() {
        return this.mActivityRequestCode;
    }

    @Override // com.amazon.mp3.service.job.activity.JobWrapper
    public boolean isFinished() {
        return this.mIsFinished || !waitForActivityResult();
    }

    protected void notifyActivityResult(final int resultCode, final Intent data) {
        onActivityResult(resultCode, data);
        this.mIsFinished = true;
    }
}
