package com.android.server.connectivity.ipmemorystore;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.net.ipmemorystore.IOnStatusListener;
import android.net.ipmemorystore.Status;
import android.net.ipmemorystore.StatusParcelable;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class RegularMaintenanceJobService extends JobService {
    private static final ArrayList<InterruptMaintenance> sInterruptList = new ArrayList<>();
    private static IpMemoryStoreService sIpMemoryStoreService;

    public static final class InterruptMaintenance {
        private volatile boolean mIsInterrupted = false;
        private final int mJobId;

        public InterruptMaintenance(int i) {
            this.mJobId = i;
        }

        public int getJobId() {
            return this.mJobId;
        }

        public void setInterrupted(boolean z) {
            this.mIsInterrupted = z;
        }

        public boolean isInterrupted() {
            return this.mIsInterrupted;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        if (sIpMemoryStoreService == null) {
            Log.wtf("RegularMaintenanceJobService", "Can not start job because sIpMemoryStoreService is null.");
            return false;
        }
        final InterruptMaintenance interruptMaintenance = new InterruptMaintenance(jobParameters.getJobId());
        sInterruptList.add(interruptMaintenance);
        sIpMemoryStoreService.fullMaintenance(new IOnStatusListener() { // from class: com.android.server.connectivity.ipmemorystore.RegularMaintenanceJobService.1
            @Override // android.os.IInterface
            public IBinder asBinder() {
                return null;
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public void onComplete(StatusParcelable statusParcelable) throws RemoteException {
                Status status = new Status(statusParcelable);
                if (!status.isSuccess()) {
                    Log.e("RegularMaintenanceJobService", "Regular maintenance failed. Error is " + status.resultCode);
                }
                RegularMaintenanceJobService.sInterruptList.remove(interruptMaintenance);
                RegularMaintenanceJobService.this.jobFinished(jobParameters, !status.isSuccess());
            }
        }, interruptMaintenance);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        int jobId = jobParameters.getJobId();
        for (InterruptMaintenance interruptMaintenance : sInterruptList) {
            if (interruptMaintenance.getJobId() == jobId) {
                interruptMaintenance.setInterrupted(true);
            }
        }
        return true;
    }

    static void schedule(Context context, IpMemoryStoreService ipMemoryStoreService) {
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(3345678, new ComponentName(context, (Class<?>) RegularMaintenanceJobService.class)).setRequiresDeviceIdle(true).setRequiresCharging(true).setRequiresBatteryNotLow(true).setPeriodic(TimeUnit.HOURS.toMillis(24L)).build());
        sIpMemoryStoreService = ipMemoryStoreService;
    }
}
