package com.amazon.mp3.service.job;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.service.job.Session;

/* JADX INFO: loaded from: classes4.dex */
public abstract class JobSessionService extends Service {
    private Session mSession = null;
    private Session.Connection mConnection = new Session.Connection() { // from class: com.amazon.mp3.service.job.JobSessionService.1
        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onStopped() {
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onSuspended() {
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobStarted(long jobId, Job job) {
            JobSessionService.this.onJobStarted(jobId, job);
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobProgress(long jobId, Job job, Bundle progress) {
            JobSessionService.this.onJobProgress(jobId, job, progress);
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
            JobSessionService.this.onJobFinished(jobId, job, resultCode, resultBundle);
        }
    };

    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
    }

    protected void onJobProgress(long jobId, Job job, Bundle progress) {
    }

    protected void onJobStarted(long jobId, Job job) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 2;
    }

    @Override // android.app.Service
    public void onCreate() {
        Session session = new Session(this, this.mConnection);
        this.mSession = session;
        session.resume();
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mSession.disconnect(this.mConnection);
    }

    protected void finalize() throws Throwable {
        this.mSession.disconnect(this.mConnection);
        super.finalize();
    }

    protected long addJob(Job job) {
        return this.mSession.addJob(job);
    }
}
