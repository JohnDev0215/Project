package com.android.car.garagemode;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobSnapshot;
import android.content.Intent;
import android.os.Handler;
import android.util.ArraySet;
import com.android.car.CarLocalServices;
import com.android.car.CarPowerManagementService;
import com.android.car.CarStatsLogHelper;
import com.android.car.user.CarUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
class GarageMode {
    public static final String ACTION_GARAGE_MODE_OFF = "com.android.server.jobscheduler.GARAGE_MODE_OFF";
    public static final String ACTION_GARAGE_MODE_ON = "com.android.server.jobscheduler.GARAGE_MODE_ON";
    private static final int ADDITIONAL_CHECKS_TO_DO = 1;
    static final long JOB_SNAPSHOT_INITIAL_UPDATE_MS = 500;
    private static final long JOB_SNAPSHOT_UPDATE_FREQUENCY_MS = 1000;
    private static final Logger LOG = new Logger("GarageMode");
    private static final long USER_STOP_CHECK_INTERVAL = 10000;
    private CarPowerManagementService mCarPowerManagementService;
    private final Controller mController;
    private CompletableFuture<Void> mFuture;
    private final Handler mHandler;
    private boolean mIdleCheckerIsRunning;
    private final JobScheduler mJobScheduler;
    private final Object mLock = new Object();
    private int mAdditionalChecksToDo = 1;
    private List<String> mPendingJobs = new ArrayList();
    private final Runnable mRunnable = new Runnable() { // from class: com.android.car.garagemode.GarageMode.1
        @Override // java.lang.Runnable
        public void run() {
            int numAdditionalChecks;
            GarageMode.LOG.w("mGarageModeActive= " + GarageMode.this.mGarageModeActive);
            GarageMode.LOG.w("JOB_SNAPSHOT_INITIAL_UPDATE_MS= 500");
            if (!GarageMode.this.mGarageModeActive) {
                GarageMode.LOG.w("Garage Mode is inactive. Stopping the idle-job checker.");
                GarageMode.this.finish();
                return;
            }
            int numberRunning = GarageMode.this.numberOfIdleJobsRunning();
            if (numberRunning > 0) {
                GarageMode.LOG.w("" + numberRunning + " jobs are still running. Need to wait more ...");
                synchronized (GarageMode.this.mLock) {
                    GarageMode.this.mAdditionalChecksToDo = 1;
                }
            } else {
                int numberReadyToRun = GarageMode.this.numberOfPendingJobs();
                GarageMode.LOG.w("has not Running Jobs，numberReadyToRun: " + numberRunning);
                if (numberReadyToRun == 0) {
                    GarageMode.LOG.w("No jobs are running. No jobs are pending. Exiting Garage Mode.");
                    GarageMode.this.finish();
                    return;
                }
                synchronized (GarageMode.this.mLock) {
                    numAdditionalChecks = GarageMode.this.mAdditionalChecksToDo;
                    if (GarageMode.this.mAdditionalChecksToDo > 0) {
                        GarageMode.access$410(GarageMode.this);
                    }
                }
                if (numAdditionalChecks == 0) {
                    GarageMode.LOG.w("No jobs are running. Waited too long for " + numberReadyToRun + " pending jobs. Exiting Garage Mode.");
                    GarageMode.this.finish();
                    return;
                }
                GarageMode.LOG.w("No jobs are running. Waiting " + numAdditionalChecks + " more cycles for " + numberReadyToRun + " pending jobs.");
            }
            GarageMode.this.mHandler.postDelayed(GarageMode.this.mRunnable, GarageMode.JOB_SNAPSHOT_UPDATE_FREQUENCY_MS);
        }
    };
    private final Runnable mStopUserCheckRunnable = new Runnable() { // from class: com.android.car.garagemode.GarageMode.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (GarageMode.this.mLock) {
                int remainingUsersToStop = GarageMode.this.mStartedBackgroundUsers.size();
                if (remainingUsersToStop < 1) {
                    return;
                }
                int userToStop = ((Integer) GarageMode.this.mStartedBackgroundUsers.valueAt(0)).intValue();
                if (GarageMode.this.numberOfIdleJobsRunning() != 0) {
                    GarageMode.LOG.i("Waiting for jobs to finish, remaining users:" + remainingUsersToStop);
                } else {
                    if (userToStop != 0) {
                        ((CarUserService) CarLocalServices.getService(CarUserService.class)).stopBackgroundUser(userToStop);
                        Logger logger = GarageMode.LOG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Stopping background user:");
                        sb.append(userToStop);
                        sb.append(" remaining users:");
                        sb.append(remainingUsersToStop - 1);
                        logger.i(sb.toString());
                    }
                    synchronized (GarageMode.this.mLock) {
                        GarageMode.this.mStartedBackgroundUsers.remove(Integer.valueOf(userToStop));
                        if (GarageMode.this.mStartedBackgroundUsers.size() == 0) {
                            GarageMode.LOG.i("All background users have stopped");
                            return;
                        }
                    }
                }
                GarageMode.this.mHandler.postDelayed(GarageMode.this.mStopUserCheckRunnable, GarageMode.USER_STOP_CHECK_INTERVAL);
            }
        }
    };
    private ArraySet<Integer> mStartedBackgroundUsers = new ArraySet<>();
    private boolean mGarageModeActive = false;

    static /* synthetic */ int access$410(GarageMode x0) {
        int i = x0.mAdditionalChecksToDo;
        x0.mAdditionalChecksToDo = i - 1;
        return i;
    }

    GarageMode(Controller controller) {
        this.mController = controller;
        this.mJobScheduler = controller.getJobSchedulerService();
        this.mHandler = controller.getHandler();
    }

    boolean isGarageModeActive() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mGarageModeActive;
        }
        return z;
    }

    List<String> dump() {
        List<String> outString = new ArrayList<>();
        if (!this.mGarageModeActive) {
            return outString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("GarageMode idle checker is ");
        sb.append(this.mIdleCheckerIsRunning ? "" : "not ");
        sb.append("running");
        outString.add(sb.toString());
        List<String> jobList = new ArrayList<>();
        int numJobs = getListOfIdleJobsRunning(jobList);
        if (numJobs > 0) {
            outString.add("GarageMode is waiting for " + numJobs + " jobs:");
            for (int idx = 0; idx < jobList.size(); idx++) {
                outString.add("   " + (idx + 1) + ": " + jobList.get(idx));
            }
        } else {
            getListOfPendingJobs(jobList);
            outString.add("GarageMode is waiting for " + jobList.size() + " pending idle jobs:");
            for (int idx2 = 0; idx2 < jobList.size(); idx2++) {
                outString.add("   " + (idx2 + 1) + ": " + jobList.get(idx2));
            }
        }
        return outString;
    }

    void enterGarageMode(CompletableFuture<Void> future) {
        LOG.w("Entering GarageMode");
        if (this.mCarPowerManagementService == null) {
            this.mCarPowerManagementService = (CarPowerManagementService) CarLocalServices.getService(CarPowerManagementService.class);
        }
        CarPowerManagementService carPowerManagementService = this.mCarPowerManagementService;
        if (carPowerManagementService != null && carPowerManagementService.garageModeShouldExitImmediately()) {
            if (future != null && !future.isDone()) {
                future.complete(null);
            }
            synchronized (this.mLock) {
                this.mGarageModeActive = false;
                LOG.w("enterGarageMode mGarageModeActive set to false");
            }
            return;
        }
        synchronized (this.mLock) {
            this.mGarageModeActive = true;
            LOG.w("enterGarageMode mGarageModeActive set to true");
        }
        updateFuture(future);
        broadcastSignalToJobScheduler(true);
        CarStatsLogHelper.logGarageModeStart();
        startMonitoringThread();
        ArrayList<Integer> startedUsers = ((CarUserService) CarLocalServices.getService(CarUserService.class)).startAllBackgroundUsers();
        synchronized (this.mLock) {
            this.mStartedBackgroundUsers.addAll(startedUsers);
        }
    }

    void cancel() {
        LOG.w("Cancel Garage Mode.");
        broadcastSignalToJobScheduler(false);
        synchronized (this.mLock) {
            if (this.mFuture == null) {
                cleanupGarageMode();
            } else if (!this.mFuture.isDone()) {
                this.mFuture.cancel(true);
            }
            this.mFuture = null;
            startBackgroundUserStoppingLocked();
        }
    }

    void finish() {
        LOG.w("Finishing Garage Mode.");
        synchronized (this.mLock) {
            if (!this.mIdleCheckerIsRunning) {
                LOG.w("Finishing Garage Mode. Idle checker is not running.");
                return;
            }
            this.mIdleCheckerIsRunning = false;
            broadcastSignalToJobScheduler(false);
            CarStatsLogHelper.logGarageModeStop();
            this.mController.scheduleNextWakeup();
            synchronized (this.mLock) {
                if (this.mFuture == null) {
                    cleanupGarageMode();
                } else if (!this.mFuture.isDone()) {
                    this.mFuture.complete(null);
                }
                this.mFuture = null;
                startBackgroundUserStoppingLocked();
            }
        }
    }

    private void cleanupGarageMode() {
        LOG.w("Cleaning up GarageMode");
        synchronized (this.mLock) {
            this.mGarageModeActive = false;
            LOG.w("cleanupGarageMode mGarageModeActive set to false");
            stopMonitoringThread();
            if (this.mIdleCheckerIsRunning) {
                this.mHandler.post(this.mRunnable);
            }
            startBackgroundUserStoppingLocked();
        }
    }

    private void startBackgroundUserStoppingLocked() {
        if (this.mStartedBackgroundUsers.size() > 0) {
            this.mHandler.postDelayed(this.mStopUserCheckRunnable, USER_STOP_CHECK_INTERVAL);
        }
    }

    private void updateFuture(CompletableFuture<Void> future) {
        synchronized (this.mLock) {
            this.mFuture = future;
            if (future != null) {
                future.whenComplete(new BiConsumer() { // from class: com.android.car.garagemode.-$$Lambda$GarageMode$EJDPPpU8PkORJG5W6FKZ0KO7wbQ
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        this.f$0.lambda$updateFuture$0$GarageMode((Void) obj, (Throwable) obj2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$updateFuture$0$GarageMode(Void result, Throwable exception) {
        if (exception == null) {
            LOG.w("GarageMode completed normally");
        } else if (exception instanceof CancellationException) {
            LOG.w("GarageMode was canceled");
        } else {
            LOG.e("GarageMode ended due to exception: ", exception);
        }
        cleanupGarageMode();
    }

    private void broadcastSignalToJobScheduler(boolean enableGarageMode) {
        Intent i = new Intent();
        i.setAction(enableGarageMode ? ACTION_GARAGE_MODE_ON : ACTION_GARAGE_MODE_OFF);
        i.setFlags(1207959552);
        this.mController.sendBroadcast(i);
    }

    private void startMonitoringThread() {
        LOG.w("startMonitoringThread: ");
        synchronized (this.mLock) {
            this.mIdleCheckerIsRunning = true;
            this.mAdditionalChecksToDo = 1;
        }
        this.mHandler.postDelayed(this.mRunnable, JOB_SNAPSHOT_INITIAL_UPDATE_MS);
    }

    private void stopMonitoringThread() {
        LOG.w("stopMonitoringThread: ");
        this.mHandler.removeCallbacks(this.mRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int numberOfIdleJobsRunning() {
        return getListOfIdleJobsRunning(null);
    }

    private int getListOfIdleJobsRunning(List<String> jobList) {
        if (jobList != null) {
            jobList.clear();
        }
        List<JobInfo> startedJobs = this.mJobScheduler.getStartedJobs();
        if (startedJobs == null) {
            return 0;
        }
        int count = 0;
        for (int idx = 0; idx < startedJobs.size(); idx++) {
            JobInfo jobInfo = startedJobs.get(idx);
            if (jobInfo.isRequireDeviceIdle()) {
                count++;
                if (jobList != null) {
                    jobList.add(jobInfo.toString());
                }
            }
        }
        return count;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int numberOfPendingJobs() {
        return getListOfPendingJobs(null);
    }

    private int getListOfPendingJobs(List<String> jobList) {
        if (jobList != null) {
            jobList.clear();
        }
        List<JobSnapshot> allScheduledJobs = this.mJobScheduler.getAllJobSnapshots();
        if (allScheduledJobs == null) {
            return 0;
        }
        int numberPending = 0;
        for (int idx = 0; idx < allScheduledJobs.size(); idx++) {
            JobSnapshot scheduledJob = allScheduledJobs.get(idx);
            JobInfo jobInfo = scheduledJob.getJobInfo();
            if (scheduledJob.isRunnable() && jobInfo.isRequireDeviceIdle()) {
                numberPending++;
                if (jobList != null) {
                    jobList.add(jobInfo.toString());
                }
            }
        }
        return numberPending;
    }
}
