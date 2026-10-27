package com.android.providers.downloads;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.database.ContentObserver;
import android.provider.Downloads;
import android.util.Log;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public class DownloadJobService extends JobService {
    private SparseArray<DownloadThread> mActiveThreads = new SparseArray<>();
    private ContentObserver mObserver = new ContentObserver(Helpers.getAsyncHandler()) { // from class: com.android.providers.downloads.DownloadJobService.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Helpers.getDownloadNotifier(DownloadJobService.this).update();
        }
    };

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        getContentResolver().registerContentObserver(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, true, this.mObserver);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(this.mObserver);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        int jobId = jobParameters.getJobId();
        DownloadInfo downloadInfoQueryDownloadInfo = DownloadInfo.queryDownloadInfo(this, jobId);
        if (downloadInfoQueryDownloadInfo == null) {
            Log.w("DownloadManager", "Odd, no details found for download " + jobId);
            return false;
        }
        synchronized (this.mActiveThreads) {
            if (this.mActiveThreads.indexOfKey(jobId) >= 0) {
                Log.w("DownloadManager", "Odd, already running download " + jobId);
                return false;
            }
            DownloadThread downloadThread = new DownloadThread(this, jobParameters, downloadInfoQueryDownloadInfo);
            this.mActiveThreads.put(jobId, downloadThread);
            downloadThread.start();
            return true;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        DownloadThread downloadThread;
        int jobId = jobParameters.getJobId();
        Log.d("DownloadManager", "onStopJob id=" + jobId + ", reason=" + jobParameters.getDebugStopReason());
        synchronized (this.mActiveThreads) {
            downloadThread = (DownloadThread) this.mActiveThreads.removeReturnOld(jobId);
        }
        if (downloadThread == null) {
            return false;
        }
        downloadThread.requestShutdown();
        Helpers.scheduleJob(this, DownloadInfo.queryDownloadInfo(this, jobId));
        return false;
    }

    public void jobFinishedInternal(JobParameters jobParameters, boolean z) {
        int jobId = jobParameters.getJobId();
        synchronized (this.mActiveThreads) {
            this.mActiveThreads.remove(jobParameters.getJobId());
        }
        if (z) {
            Helpers.scheduleJob(this, DownloadInfo.queryDownloadInfo(this, jobId));
        }
        this.mObserver.onChange(false);
        jobFinished(jobParameters, false);
    }
}
