package com.amazon.mp3.service.job;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.service.job.Session;
import com.amazon.mp3.util.ForegroundLogger;

/* JADX INFO: loaded from: classes4.dex */
public class JobSessionFragment extends Fragment {
    private Context mContext;
    private Session mSession;
    private boolean mPaused = true;
    private long mLastSessionId = -1;
    private Session.Connection mConnection = new Session.Connection() { // from class: com.amazon.mp3.service.job.JobSessionFragment.1
        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onStopped() {
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onSuspended() {
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobStarted(long jobId, Job job) {
            JobSessionFragment.this.onJobStarted(jobId, job);
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobProgress(long jobId, Job job, Bundle progress) {
            JobSessionFragment.this.onJobProgress(jobId, job, progress);
        }

        @Override // com.amazon.mp3.service.job.Session.Connection
        public void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
            JobSessionFragment.this.onJobFinished(jobId, job, resultCode, resultBundle);
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

    protected void onSessionSuspended(long sessionId) {
    }

    protected boolean suspendWhenHidden() {
        return true;
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

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity.getApplicationContext();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mLastSessionId = savedInstanceState.getLong("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", -1L);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSession();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putLong("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", getSessionId());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.mPaused = true;
        suspendSession();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Session session = this.mSession;
        if (session != null) {
            session.disconnect(this.mConnection);
            onSessionDisconnected(this.mSession.getId());
            this.mSession = null;
        }
        this.mConnection = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mPaused = false;
        resumeSession();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden && suspendWhenHidden()) {
            suspendSession();
        } else {
            resumeSession();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ForegroundLogger.log(this);
        }
    }

    protected long addJob(Job job) {
        if (this.mSession == null) {
            initSession();
        }
        return this.mSession.addJob(job);
    }

    private void resumeSession() {
        Session session = this.mSession;
        if (session != null) {
            session.resume();
            onSessionResumed(this.mSession.getId());
        }
    }

    private void suspendSession() {
        Session session = this.mSession;
        if (session != null) {
            session.suspend();
            onSessionSuspended(this.mSession.getId());
        }
    }

    protected synchronized Session getSession() {
        if (this.mSession == null) {
            initSession();
        }
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
            onSessionConnected(this.mSession.getId());
        } else {
            Session session2 = new Session(this.mContext, this.mConnection);
            this.mSession = session2;
            onSessionCreated(session2.getId());
            onSessionConnected(this.mSession.getId());
        }
    }
}
