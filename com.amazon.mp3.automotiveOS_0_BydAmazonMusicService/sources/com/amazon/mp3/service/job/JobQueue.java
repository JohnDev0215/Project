package com.amazon.mp3.service.job;

import android.content.Context;
import android.os.Bundle;
import com.amazon.mp3.service.job.Session;
import java.io.Closeable;

/* JADX INFO: loaded from: classes4.dex */
public class JobQueue implements Closeable {
    private final Session.Connection mConnection;
    private int mJobCount;
    private final Session mSession;

    static /* synthetic */ int access$006(JobQueue jobQueue) {
        int i = jobQueue.mJobCount - 1;
        jobQueue.mJobCount = i;
        return i;
    }

    public JobQueue(final Context context) {
        Session.Connection connection = new Session.Connection() { // from class: com.amazon.mp3.service.job.JobQueue.1
            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onJobProgress(final long jobId, final Job job, final Bundle progress) {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onJobStarted(final long jobId, final Job job) {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onStopped() {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onSuspended() {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
                if (JobQueue.access$006(JobQueue.this) == 0) {
                    JobQueue.this.mSession.suspend();
                }
            }
        };
        this.mConnection = connection;
        this.mSession = new Session(context, connection);
    }

    public void addJob(Job job) {
        this.mSession.addJob(job);
        int i = this.mJobCount;
        this.mJobCount = i + 1;
        if (i == 0) {
            this.mSession.resume();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mSession.disconnect(this.mConnection);
    }

    protected void finalize() throws Throwable {
        this.mSession.disconnect(this.mConnection);
        super.finalize();
    }
}
