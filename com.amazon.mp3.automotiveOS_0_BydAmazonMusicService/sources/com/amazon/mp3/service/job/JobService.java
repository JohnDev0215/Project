package com.amazon.mp3.service.job;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.amazon.mp3.service.job.DispatchTable;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class JobService extends Service {
    private static final String TAG = JobService.class.getSimpleName();
    private int mLastStartId;
    private RpcThread mRcpThread = null;
    private ExecutorService mExecutorService = null;
    private HashMap<Long, SessionStruct> mSessions = null;
    private DispatchTable mDt = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mDt = DispatchTable.getInstance(this);
        this.mSessions = new HashMap<>();
        this.mRcpThread = new RpcThread();
        this.mExecutorService = Executors.newCachedThreadPool();
        this.mRcpThread.start();
        this.mRcpThread.waitUntilInitialized();
        postDelayedStop();
        Log.verbose(TAG, "JobService created");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postDelayedStop() {
        this.mRcpThread.enqueueDelayed(new Intent("com.amazon.mp3.library.service.job.ACTION_CHECK_SHUTDOWN"), 10000L);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mRcpThread.cancel();
        this.mExecutorService.shutdownNow();
        Log.verbose(TAG, "JobService destroyed");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        this.mRcpThread.enqueue(intent);
        this.mLastStartId = startId;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processIncomingIntent(Intent intent) {
        String action = intent.getAction();
        long longExtra = intent.getLongExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", -1L);
        long longExtra2 = intent.getLongExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", -1L);
        int intExtra = intent.getIntExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_RESULT_CODE", 0);
        if ("com.amazon.mp3.library.service.job.ACTION_START_SESSION".equals(action)) {
            if (startSession(longExtra) == 0) {
                stopIfIdle();
                return;
            }
            return;
        }
        if ("com.amazon.mp3.library.service.job.ACTION_STOP_SESSION".equals(action)) {
            stopSession(longExtra);
            postDelayedStop();
            return;
        }
        if ("com.amazon.mp3.library.service.job.ACTION_SUSPEND_SESSION".equals(action)) {
            suspendSession(longExtra);
            postDelayedStop();
            return;
        }
        if ("com.amazon.mp3.library.service.job.ACTION_JOB_STARTED".equals(action)) {
            onJobStarted(longExtra, longExtra2);
            sendBroadcast(intent);
            return;
        }
        if ("com.amazon.mp3.library.service.job.ACTION_JOB_FINISHED".equals(action)) {
            onJobFinished(longExtra, longExtra2, intExtra);
            sendBroadcast(intent);
            postDelayedStop();
        } else {
            if ("com.amazon.mp3.library.service.job.ACTION_JOB_ADDED".equals(action)) {
                SessionStruct sessionStruct = this.mSessions.get(Long.valueOf(longExtra));
                if (sessionStruct == null || longExtra2 == -1) {
                    return;
                }
                addJobToSession(sessionStruct.mSessionId, longExtra2);
                return;
            }
            if ("com.amazon.mp3.library.service.job.ACTION_CANCEL_JOB".equals(action)) {
                cancelJob(longExtra, longExtra2);
            } else if ("com.amazon.mp3.library.service.job.ACTION_CHECK_SHUTDOWN".equals(action)) {
                stopIfIdle();
            }
        }
    }

    private void stopIfIdle() {
        if (this.mRcpThread.hasMessagesOutstanding()) {
            return;
        }
        if (this.mSessions.size() == 0 || allSessionsSuspended()) {
            stopSelf(this.mLastStartId);
        }
    }

    private boolean allSessionsSuspended() {
        for (SessionStruct sessionStruct : this.mSessions.values()) {
            if (!sessionStruct.mSuspended || sessionStruct.mWorkItems.size() > 0) {
                return false;
            }
        }
        return true;
    }

    private void cancelJob(long sessionId, long jobId) {
        SessionStruct sessionStruct;
        JobCallable jobCallable;
        DispatchTable.JobEntry job = this.mDt.getJob(Long.valueOf(jobId));
        if (job != null) {
            int state = job.getState();
            if (state == 1 || state == 0) {
                job.setState(5);
                Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_JOB_FINISHED");
                intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", jobId);
                intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", sessionId);
                intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_RESULT_CODE", 2);
                this.mRcpThread.enqueue(intent);
                return;
            }
            if (state != 2 || (sessionStruct = this.mSessions.get(Long.valueOf(sessionId))) == null || (jobCallable = sessionStruct.mWorkItems.get(Long.valueOf(jobId))) == null) {
                return;
            }
            job.setState(3);
            jobCallable.cancel();
        }
    }

    private void suspendJob(JobCallable callable) {
        callable.suspend();
        DispatchTable.JobEntry job = this.mDt.getJob(Long.valueOf(callable.mJobId));
        if (job != null) {
            job.setState(4);
        }
    }

    private void addJobToSession(long sessionId, long jobId) {
        DispatchTable.JobEntry job;
        SessionStruct sessionStruct = this.mSessions.get(Long.valueOf(sessionId));
        if (sessionStruct == null || sessionStruct.mSuspended || (job = this.mDt.getJob(Long.valueOf(jobId))) == null) {
            return;
        }
        int state = job.getState();
        if ((state != 0 && state != 4) || this.mExecutorService.isShutdown() || this.mExecutorService.isTerminated()) {
            return;
        }
        JobCallable jobCallable = new JobCallable(sessionId, jobId);
        try {
            this.mExecutorService.submit(jobCallable);
            sessionStruct.mWorkItems.put(Long.valueOf(jobId), jobCallable);
            job.setState(1);
        } catch (RejectedExecutionException unused) {
            job.setState(3);
            jobCallable.cancel();
        }
    }

    private void onJobStarted(long sessionId, long jobId) {
        DispatchTable.JobEntry job;
        if (jobId == -1 || (job = this.mDt.getJob(Long.valueOf(jobId))) == null) {
            return;
        }
        boolean z = job.getState() == 5;
        job.setState(2);
        if (z) {
            cancelJob(sessionId, jobId);
        }
    }

    private void onJobFinished(long sessionId, long jobId, long jobResult) {
        DispatchTable.JobEntry job = this.mDt.getJob(Long.valueOf(jobId));
        Job job2 = Session.getJob(sessionId, jobId);
        SessionStruct sessionStruct = this.mSessions.get(Long.valueOf(sessionId));
        if (sessionStruct != null) {
            sessionStruct.mWorkItems.remove(Long.valueOf(jobId));
        }
        if (job == null || job2 == null) {
            return;
        }
        if (jobResult == 1) {
            job.setState(5);
        } else if (jobResult == 2 && job.getState() == 4 && job2.restartable()) {
            job.setState(0);
            if (sessionStruct == null || sessionStruct.mSuspended) {
                return;
            }
            addJobToSession(sessionId, jobId);
            return;
        }
        job.setState(5);
    }

    private void suspendSession(long sessionId) {
        SessionStruct sessionStruct = this.mSessions.get(Long.valueOf(sessionId));
        if (sessionStruct == null) {
            return;
        }
        Iterator<Map.Entry<Long, JobCallable>> it = sessionStruct.mWorkItems.entrySet().iterator();
        while (it.hasNext()) {
            suspendJob(it.next().getValue());
        }
        sessionStruct.mSuspended = true;
        Log.verbose(TAG, "session: " + sessionId + " suspended");
    }

    private void stopSession(long sessionId) {
        suspendSession(sessionId);
        this.mSessions.remove(Long.valueOf(sessionId));
        Log.verbose(TAG, "session: " + sessionId + " stopped. remaining: " + this.mSessions.size());
    }

    private int startSession(long sessionId) {
        SessionStruct sessionStruct = this.mSessions.get(Long.valueOf(sessionId));
        if (sessionStruct == null) {
            sessionStruct = new SessionStruct(sessionId);
            this.mSessions.put(Long.valueOf(sessionId), sessionStruct);
        }
        sessionStruct.mSuspended = false;
        DispatchTable.JobEntry[] allReadyJobsForSession = this.mDt.getAllReadyJobsForSession(sessionId);
        for (DispatchTable.JobEntry jobEntry : allReadyJobsForSession) {
            addJobToSession(sessionId, jobEntry.getId().longValue());
        }
        Log.verbose(TAG, "session: " + sessionId + " started. total: " + this.mSessions.size());
        return allReadyJobsForSession.length;
    }

    private static class SessionStruct {
        final long mSessionId;
        boolean mSuspended = false;
        final HashMap<Long, JobCallable> mWorkItems = new HashMap<>();

        SessionStruct(long sessionId) {
            this.mSessionId = sessionId;
        }
    }

    private class JobCallable implements Callable<Void> {
        private final Job mJob;
        private final long mJobId;
        private final long mSessionId;
        private boolean mSuspended = false;

        public JobCallable(long sessionId, long jobId) {
            this.mJobId = jobId;
            this.mSessionId = sessionId;
            this.mJob = Session.getJob(sessionId, jobId);
        }

        public void suspend() {
            this.mSuspended = true;
            cancel();
        }

        public void cancel() {
            Job job = this.mJob;
            if (job != null) {
                job.cancel();
            }
        }

        /* JADX WARN: Finally extract failed */
        @Override // java.util.concurrent.Callable
        public Void call() {
            int iRun;
            this.mSuspended = false;
            broadcastStarted();
            try {
                Thread.currentThread().setName(this.mJob.toString());
                Thread.currentThread().setPriority(this.mJob.getDefaultThreadPriority());
                this.mJob.setContext(JobService.this.getApplicationContext());
                this.mJob.setSessionId(this.mSessionId);
                this.mJob.setJobId(this.mJobId);
                this.mJob.clearResult();
                iRun = this.mJob.run();
            } catch (Throwable th) {
                th = th;
                iRun = 1;
            }
            try {
                Bundle result = this.mJob.getResult();
                Job job = this.mJob;
                if (job != null) {
                    job.setPaused(isPaused(job));
                }
                broadcastFinished(iRun, result);
            } catch (Throwable th2) {
                th = th2;
                try {
                    String str = JobService.TAG;
                    Object[] objArr = new Object[2];
                    Job job2 = this.mJob;
                    objArr[0] = job2 != null ? job2.getClass().getName() : "null job";
                    objArr[1] = th.getClass().toString();
                    Log.warning(str, "Unhandled Exception during Job %s run: %s", objArr);
                    Log.warning(JobService.TAG, "trace: ", th);
                    iRun = 3;
                    Job job3 = this.mJob;
                    if (job3 != null) {
                        job3.setFailureCause(th);
                    }
                    Job job4 = this.mJob;
                    if (job4 != null) {
                        job4.setPaused(isPaused(job4));
                    }
                    broadcastFinished(3, null);
                } catch (Throwable th3) {
                    Job job5 = this.mJob;
                    if (job5 != null) {
                        job5.setPaused(isPaused(job5));
                    }
                    broadcastFinished(iRun, null);
                    JobService.this.postDelayedStop();
                    throw th3;
                }
            }
            JobService.this.postDelayedStop();
            Thread.currentThread().setName("Idle JobService thread");
            return null;
        }

        private boolean isPaused(Job job) {
            return this.mSuspended && job.restartable();
        }

        private void broadcastStarted() {
            Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_JOB_STARTED");
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", this.mJobId);
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
            JobService.this.mRcpThread.enqueue(intent);
        }

        private void broadcastFinished(int jobResult, Bundle resultBundle) {
            Intent intent = new Intent("com.amazon.mp3.library.service.job.ACTION_JOB_FINISHED");
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", this.mJobId);
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_SESSION_ID", this.mSessionId);
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_RESULT_CODE", jobResult);
            intent.putExtra("com.amazon.mp3.library.service.job.EXTRA_JOB_RESULT_BUNDLE", resultBundle);
            JobService.this.mRcpThread.enqueue(intent);
        }
    }

    private class RpcThread extends Thread {
        private Handler mHandler;
        private Looper mLooper;
        private volatile boolean mQuit = false;
        private CountDownLatch mInitializedLatch = new CountDownLatch(1);

        public RpcThread() {
            setName(JobService.TAG + ".RpcMessageQueue");
        }

        public void cancel() {
            this.mQuit = true;
            Message messageObtainMessage = this.mHandler.obtainMessage();
            messageObtainMessage.what = -559038737;
            this.mHandler.sendMessage(messageObtainMessage);
        }

        public void enqueue(Intent intent) {
            enqueueDelayed(intent, 0L);
        }

        public void enqueueDelayed(Intent intent, long delay) {
            if (this.mQuit || intent == null) {
                return;
            }
            Message messageObtainMessage = this.mHandler.obtainMessage();
            messageObtainMessage.what = -889258259;
            messageObtainMessage.obj = intent;
            this.mHandler.sendMessageDelayed(messageObtainMessage, delay);
        }

        public void waitUntilInitialized() {
            try {
                this.mInitializedLatch.await();
            } catch (InterruptedException unused) {
            }
        }

        public boolean hasMessagesOutstanding() {
            return this.mHandler.hasMessages(-889258259);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.mQuit = false;
            Looper.prepare();
            this.mLooper = Looper.myLooper();
            this.mHandler = new Handler() { // from class: com.amazon.mp3.service.job.JobService.RpcThread.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    int i = msg.what;
                    if (i != -889258259) {
                        if (i != -559038737) {
                            return;
                        }
                        RpcThread.this.mLooper.quit();
                    } else {
                        if (RpcThread.this.mQuit) {
                            return;
                        }
                        JobService.this.processIncomingIntent((Intent) msg.obj);
                    }
                }
            };
            this.mInitializedLatch.countDown();
            Looper.loop();
        }
    }
}
