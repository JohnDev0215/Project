package com.amazon.mp3.service.job;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class Session {
    private static final Connection NULL_CONNECTION;
    private static final String TAG = "Session";
    private static final IntentFilter sJobStatusFilter;
    private final Context mApplication;
    private final HashSet<Connection> mConnections;
    private final DispatchTable mDt;
    private final HashMap<Long, Job> mJobMap;
    private final BroadcastReceiver mJobStatusReceiver;
    private final LinkedList<PendingEvent> mPendingEvents;
    private final long mSessionId;
    private static final HashMap<Long, Session> sSessions = new HashMap<>();
    private static final AtomicInteger sNextSessionId = new AtomicInteger();
    private final Handler mHandler = new Handler();
    private final Set<Long> mCurrentJobIds = new HashSet();
    private int mState = -1;
    private final Runnable mStopIfNoConnections = new Runnable() { // from class: com.amazon.mp3.service.job.Session.1
        @Override // java.lang.Runnable
        public void run() {
            if (Session.this.mConnections.isEmpty()) {
                Session.this.stop();
            }
        }
    };

    public interface Connection {
        void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle);

        void onJobProgress(long jobId, Job job, Bundle progress);

        void onJobStarted(long jobId, Job job);

        void onStopped();

        void onSuspended();
    }

    static {
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.library.service.job.ACTION_JOB_STARTED");
        sJobStatusFilter = intentFilter;
        intentFilter.addAction("com.amazon.mp3.library.service.job.ACTION_JOB_PROGRESS");
        intentFilter.addAction("com.amazon.mp3.library.service.job.ACTION_JOB_FINISHED");
        NULL_CONNECTION = new Connection() { // from class: com.amazon.mp3.service.job.Session.3
            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onJobProgress(long jobId, Job job, Bundle progress) {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onJobStarted(long jobId, Job job) {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onStopped() {
            }

            @Override // com.amazon.mp3.service.job.Session.Connection
            public void onSuspended() {
            }
        };
    }

    static class PendingEvent {
        Intent mIntent;
        Job mJob;

        PendingEvent(Intent i, Job j) {
            this.mIntent = i;
            this.mJob = j;
        }
    }

    Session(Context context, Connection connection) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.service.job.Session.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent.getLongExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", -1L) == Session.this.mSessionId) {
                    long longExtra = intent.getLongExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", -1L);
                    if (longExtra != -1) {
                        Job job = (Job) Session.this.mJobMap.get(Long.valueOf(longExtra));
                        if (!Session.this.hasValidConnections() || Session.this.mState != 0) {
                            Session.this.mPendingEvents.add(new PendingEvent(intent, job));
                        } else {
                            Session.this.processJobStatusEvent(intent, job);
                        }
                    }
                }
            }
        };
        this.mJobStatusReceiver = broadcastReceiver;
        Context applicationContext = context.getApplicationContext();
        this.mApplication = applicationContext;
        this.mDt = DispatchTable.getInstance(applicationContext);
        long jIncrementAndGet = sNextSessionId.incrementAndGet();
        this.mSessionId = jIncrementAndGet;
        this.mJobMap = new HashMap<>();
        HashSet<Connection> hashSet = new HashSet<>();
        this.mConnections = hashSet;
        this.mPendingEvents = new LinkedList<>();
        applicationContext.registerReceiver(broadcastReceiver, sJobStatusFilter);
        HashMap<Long, Session> map = sSessions;
        synchronized (map) {
            map.put(Long.valueOf(jIncrementAndGet), this);
            Log.verbose(TAG, "Session created: %d, %d total", Long.valueOf(jIncrementAndGet), Integer.valueOf(map.size()));
        }
        hashSet.add(connection);
    }

    void connect(Connection connection) {
        this.mHandler.removeCallbacks(this.mStopIfNoConnections);
        this.mConnections.add(connection);
        checkBroadcastPending();
    }

    void disconnect(Connection connection) {
        if (this.mConnections.remove(connection) && this.mConnections.isEmpty()) {
            this.mHandler.postDelayed(this.mStopIfNoConnections, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        }
    }

    public long addJob(Job job) {
        long jAddJob = this.mDt.addJob(Long.valueOf(this.mSessionId));
        this.mCurrentJobIds.add(Long.valueOf(jAddJob));
        if (jAddJob == -1) {
            throw new RuntimeException("Couldn't add job to queue!");
        }
        synchronized (this.mJobMap) {
            this.mJobMap.put(Long.valueOf(jAddJob), job);
        }
        Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_JOB_ADDED");
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", jAddJob);
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
        intent.setClass(this.mApplication, JobService.class);
        ServiceUtil.startService(this.mApplication, intent);
        return jAddJob;
    }

    public void cancelJob(long jobId) {
        this.mCurrentJobIds.remove(Long.valueOf(jobId));
        Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_CANCEL_JOB");
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", jobId);
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
        intent.setClass(this.mApplication, JobService.class);
        ServiceUtil.startService(this.mApplication, intent);
    }

    static Job getJob(long sessionId, long jobId) {
        Session session;
        Job job;
        HashMap<Long, Session> map = sSessions;
        synchronized (map) {
            session = map.get(Long.valueOf(sessionId));
        }
        if (session == null) {
            return null;
        }
        synchronized (session.mJobMap) {
            job = session.mJobMap.get(Long.valueOf(jobId));
        }
        return job;
    }

    static Session get(long sessionId) {
        Session session;
        HashMap<Long, Session> map = sSessions;
        synchronized (map) {
            session = map.get(Long.valueOf(sessionId));
        }
        return session;
    }

    void suspend() {
        if (this.mState == 0) {
            Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_SUSPEND_SESSION");
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
            intent.setClass(this.mApplication, JobService.class);
            ServiceUtil.startService(this.mApplication, intent);
            for (Connection connection : this.mConnections) {
                if (connection != NULL_CONNECTION && connection != null) {
                    connection.onSuspended();
                }
            }
            this.mState = 1;
        }
    }

    void resume() {
        int i = this.mState;
        if (i == 2) {
            throw new IllegalStateException("Session.resume() called when (mState == DEAD)");
        }
        if (i == 0) {
            return;
        }
        this.mState = 0;
        checkBroadcastPending();
        Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_START_SESSION");
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
        intent.setClass(this.mApplication, JobService.class);
        ServiceUtil.startService(this.mApplication, intent);
    }

    long getId() {
        return this.mSessionId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        if (this.mState == 2) {
            return;
        }
        this.mState = 2;
        HashMap<Long, Session> map = sSessions;
        synchronized (map) {
            map.remove(Long.valueOf(this.mSessionId));
            Log.verbose(TAG, "Session %d removed: %d total", Long.valueOf(this.mSessionId), Integer.valueOf(map.size()));
        }
        Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_STOP_SESSION");
        intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
        intent.setClass(this.mApplication, JobService.class);
        ServiceUtil.startService(this.mApplication, intent);
        this.mDt.removeSession(Long.valueOf(this.mSessionId));
        this.mJobMap.clear();
        this.mApplication.unregisterReceiver(this.mJobStatusReceiver);
        this.mPendingEvents.clear();
        for (Connection connection : this.mConnections) {
            if (connection != NULL_CONNECTION) {
                connection.onStopped();
            }
        }
    }

    private void checkBroadcastPending() {
        if (!hasValidConnections() || this.mPendingEvents.size() <= 0) {
            return;
        }
        for (PendingEvent pendingEvent : this.mPendingEvents) {
            processJobStatusEvent(pendingEvent.mIntent, pendingEvent.mJob);
        }
        this.mPendingEvents.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasValidConnections() {
        int size = this.mConnections.size();
        return !(size == 1 && this.mConnections.contains(NULL_CONNECTION)) && size > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processJobStatusEvent(Intent intent, Job job) {
        String action = intent.getAction();
        long longExtra = intent.getLongExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", -1L);
        if ("com.amazon.mp3.library.service.job.ACTION_JOB_STARTED".equals(action)) {
            onJobStarted(longExtra, job);
        } else if ("com.amazon.mp3.library.service.job.ACTION_JOB_PROGRESS".equals(action)) {
            onJobProgress(longExtra, job, intent.getBundleExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_PROGRESS"));
        } else if ("com.amazon.mp3.library.service.job.ACTION_JOB_FINISHED".equals(action)) {
            setJobFinished(longExtra, job, intent.getIntExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_RESULT_CODE", 0), intent.getBundleExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_RESULT_BUNDLE"));
        }
    }

    private void onJobStarted(long jobId, Job job) {
        for (Connection connection : this.mConnections) {
            if (connection != NULL_CONNECTION) {
                connection.onJobStarted(jobId, job);
            }
        }
        if (job != null) {
            job.onStarted();
        }
    }

    private void onJobProgress(long jobId, Job job, Bundle progress) {
        for (Connection connection : this.mConnections) {
            if (connection != NULL_CONNECTION) {
                if (job == null) {
                    Log.verbose(TAG, "onJobProgress: (job == null), discarding event");
                    return;
                }
                connection.onJobProgress(jobId, job, progress);
            }
        }
    }

    private void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        this.mCurrentJobIds.remove(Long.valueOf(jobId));
        for (Connection connection : this.mConnections) {
            if (connection != NULL_CONNECTION) {
                connection.onJobFinished(jobId, job, resultCode, resultBundle);
            }
        }
        if (job != null) {
            job.onFinished(resultCode, resultBundle);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016 A[Catch: all -> 0x0024, TryCatch #0 {, blocks: (B:5:0x000f, B:8:0x001f, B:7:0x0016), top: B:14:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void setJobFinished(long r4, com.amazon.mp3.service.job.Job r6, int r7, android.os.Bundle r8) {
        /*
            r3 = this;
            com.amazon.mp3.service.job.DispatchTable r0 = r3.mDt
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            com.amazon.mp3.service.job.DispatchTable$JobEntry r0 = r0.getJob(r1)
            java.util.HashMap<java.lang.Long, com.amazon.mp3.service.job.Job> r1 = r3.mJobMap
            monitor-enter(r1)
            if (r0 == 0) goto L16
            int r0 = r0.getState()     // Catch: java.lang.Throwable -> L24
            r2 = 5
            if (r0 != r2) goto L1f
        L16:
            java.util.HashMap<java.lang.Long, com.amazon.mp3.service.job.Job> r0 = r3.mJobMap     // Catch: java.lang.Throwable -> L24
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L24
            r0.remove(r2)     // Catch: java.lang.Throwable -> L24
        L1f:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L24
            r3.onJobFinished(r4, r6, r7, r8)
            return
        L24:
            r4 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L24
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.service.job.Session.setJobFinished(long, com.amazon.mp3.service.job.Job, int, android.os.Bundle):void");
    }
}
