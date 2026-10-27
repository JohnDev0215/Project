package com.amazon.mp3.service.job;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.mp3.activity.settings.ClearCacheActivity;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.service.job.Session;
import com.amazon.mp3.util.ExitCheck;

/* JADX INFO: loaded from: classes4.dex */
public abstract class JobSessionActivity extends AppCompatActivity {
    private Session mSession;
    private boolean mPaused = true;
    private long mLastSessionId = -1;
    private final Session.Connection mConnection = new Session.Connection() { // from class: com.amazon.mp3.service.job.JobSessionActivity.1
        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onStopped() {
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onSuspended() {
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobStarted(long jobId, Job job) {
            JobSessionActivity.this.onJobStarted(jobId, job);
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobProgress(long jobId, Job job, Bundle progress) {
            JobSessionActivity.this.onJobProgress(jobId, job, progress);
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
            JobSessionActivity.this.onJobFinished(jobId, job, resultCode, resultBundle);
        }
    };

    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
    }

    protected void onJobProgress(long jobId, Job job, Bundle progress) {
    }

    protected void onJobStarted(long jobId, Job job) {
    }

    protected void onSessionConnected(long sessionId) {
    }

    protected void onSessionCreated(long sessionId) {
    }

    protected void onSessionDisconnected(long sessionId) {
    }

    protected void onSessionResumed(long sessionId) {
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    long getSessionId() {
        Session session = this.mSession;
        if (session == null) {
            return -1L;
        }
        return session.getId();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mLastSessionId = savedInstanceState.getLong("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", -1L);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createSessionIfNecessary();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putLong("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", getSessionId());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.mPaused = true;
        Session session = this.mSession;
        if (session != null) {
            session.suspend();
        }
        ExitCheck.exitActivity(this);
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Session session = this.mSession;
        if (session != null) {
            session.disconnect(this.mConnection);
            onSessionDisconnected(this.mSession.getId());
        }
    }

    protected void checkClearCache() {
        if (ClearCacheService.isRunning()) {
            startActivity(ClearCacheActivity.getStartIntent(this));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        try {
            super.onResume();
            this.mPaused = false;
            Session session = this.mSession;
            if (session != null) {
                session.resume();
            }
            ExitCheck.enterActivity(this);
            checkClearCache();
        } catch (Exception e) {
            throw new RuntimeException("Error while resuming activity: " + getIntentName() + " Calling Activity: " + getCallingActivityName() + " Exception: " + e.toString(), e);
        }
    }

    private String getIntentName() {
        return (getIntent() == null || getIntent().getComponent() == null) ? "Null Intent" : getIntent().getComponent().toShortString();
    }

    private String getCallingActivityName() {
        return getCallingActivity() != null ? getCallingActivity().toShortString() : "No Calling Activity";
    }

    protected void finalize() throws Throwable {
        Session session = this.mSession;
        if (session != null) {
            session.disconnect(this.mConnection);
        }
        super.finalize();
    }

    protected long addJob(Job job) {
        createSessionIfNecessary();
        return this.mSession.addJob(job);
    }

    protected void cancelJob(long jobId) {
        Session session = this.mSession;
        if (session != null) {
            session.cancelJob(jobId);
        }
    }

    @Deprecated
    protected synchronized void createSessionIfNecessary() {
        if (this.mSession == null) {
            initSession();
        }
    }

    protected synchronized Session getSession() {
        createSessionIfNecessary();
        return this.mSession;
    }

    private void initSession() {
        long j = this.mLastSessionId;
        if (j != -1) {
            this.mSession = Session.get(j);
        }
        Session session = this.mSession;
        if (session != null) {
            session.connect(this.mConnection);
            onSessionResumed(this.mSession.getId());
            onSessionConnected(this.mSession.getId());
        } else {
            Session session2 = new Session(this, this.mConnection);
            this.mSession = session2;
            onSessionCreated(session2.getId());
            onSessionConnected(this.mSession.getId());
        }
    }
}
