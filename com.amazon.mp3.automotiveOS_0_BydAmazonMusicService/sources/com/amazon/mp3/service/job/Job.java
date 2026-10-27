package com.amazon.mp3.service.job;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Job {
    public static final long INVALID = -1;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_ERROR = 3;
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_UNKNOWN = 0;
    private Throwable mFailureCause;
    private Bundle mResult;
    private long mSessionId = -1;
    private long mJobId = -1;
    private Application mContext = null;
    private boolean mPaused = false;

    public void cancel() {
    }

    public int getDefaultThreadPriority() {
        return 5;
    }

    protected void onFinished(int resultCode, Bundle resultBundle) {
    }

    protected void onStarted() {
    }

    public boolean restartable() {
        return false;
    }

    public abstract int run() throws Exception;

    public final Throwable getFailureCause() {
        return this.mFailureCause;
    }

    public final boolean isPaused() {
        return this.mPaused;
    }

    final void setPaused(boolean paused) {
        this.mPaused = paused;
    }

    final void setJobId(long jobId) {
        this.mJobId = jobId;
    }

    final void setSessionId(long sessionId) {
        this.mSessionId = sessionId;
    }

    final void setFailureCause(Throwable ex) {
        this.mFailureCause = ex;
    }

    final void setContext(Context context) {
        this.mContext = (Application) context.getApplicationContext();
    }

    final void clearResult() {
        setResult(null);
    }

    final Bundle getResult() {
        return this.mResult;
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        return simpleName.length() == 0 ? "Job (anonymous)" : simpleName;
    }

    protected final void setResult(Bundle result) {
        this.mResult = result;
    }

    protected final Application getContext() {
        return this.mContext;
    }

    protected final void broadcastProgress(Bundle bundle) {
        Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_JOB_PROGRESS");
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", this.mJobId);
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_PROGRESS", bundle);
        this.mContext.sendBroadcast(intent);
    }
}
