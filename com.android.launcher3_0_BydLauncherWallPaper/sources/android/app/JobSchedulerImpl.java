package android.app;

import android.app.job.IJobScheduler;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobSnapshot;
import android.app.job.JobWorkItem;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JobSchedulerImpl extends JobScheduler {
    IJobScheduler mBinder;

    public JobSchedulerImpl(IJobScheduler iJobScheduler) {
        this.mBinder = iJobScheduler;
    }

    @Override // android.app.job.JobScheduler
    public int schedule(JobInfo jobInfo) {
        try {
            return this.mBinder.schedule(jobInfo);
        } catch (RemoteException unused) {
            return 0;
        }
    }

    @Override // android.app.job.JobScheduler
    public int enqueue(JobInfo jobInfo, JobWorkItem jobWorkItem) {
        try {
            return this.mBinder.enqueue(jobInfo, jobWorkItem);
        } catch (RemoteException unused) {
            return 0;
        }
    }

    @Override // android.app.job.JobScheduler
    public int scheduleAsPackage(JobInfo jobInfo, String str, int i, String str2) {
        try {
            return this.mBinder.scheduleAsPackage(jobInfo, str, i, str2);
        } catch (RemoteException unused) {
            return 0;
        }
    }

    @Override // android.app.job.JobScheduler
    public void cancel(int i) {
        try {
            this.mBinder.cancel(i);
        } catch (RemoteException unused) {
        }
    }

    @Override // android.app.job.JobScheduler
    public void cancelAll() {
        try {
            this.mBinder.cancelAll();
        } catch (RemoteException unused) {
        }
    }

    @Override // android.app.job.JobScheduler
    public List<JobInfo> getAllPendingJobs() {
        try {
            return this.mBinder.getAllPendingJobs().getList();
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.app.job.JobScheduler
    public JobInfo getPendingJob(int i) {
        try {
            return this.mBinder.getPendingJob(i);
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.app.job.JobScheduler
    public List<JobInfo> getStartedJobs() {
        try {
            return this.mBinder.getStartedJobs();
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.app.job.JobScheduler
    public List<JobSnapshot> getAllJobSnapshots() {
        try {
            return this.mBinder.getAllJobSnapshots().getList();
        } catch (RemoteException unused) {
            return null;
        }
    }
}
