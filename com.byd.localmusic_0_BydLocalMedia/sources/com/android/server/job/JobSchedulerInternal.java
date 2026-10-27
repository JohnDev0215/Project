package com.android.server.job;

import android.app.job.JobInfo;
import android.util.proto.ProtoOutputStream;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface JobSchedulerInternal {
    void addBackingUpUid(int i);

    void cancelJobsForUid(int i, String str);

    void clearAllBackingUpUids();

    String getMediaBackupPackage();

    JobStorePersistStats getPersistStats();

    List<JobInfo> getSystemScheduledPendingJobs();

    void removeBackingUpUid(int i);

    void reportAppUsage(String str, int i);

    public static class JobStorePersistStats {
        public int countAllJobsLoaded;
        public int countAllJobsSaved;
        public int countSystemServerJobsLoaded;
        public int countSystemServerJobsSaved;
        public int countSystemSyncManagerJobsLoaded;
        public int countSystemSyncManagerJobsSaved;

        public JobStorePersistStats() {
            this.countAllJobsLoaded = -1;
            this.countSystemServerJobsLoaded = -1;
            this.countSystemSyncManagerJobsLoaded = -1;
            this.countAllJobsSaved = -1;
            this.countSystemServerJobsSaved = -1;
            this.countSystemSyncManagerJobsSaved = -1;
        }

        public JobStorePersistStats(JobStorePersistStats jobStorePersistStats) {
            this.countAllJobsLoaded = -1;
            this.countSystemServerJobsLoaded = -1;
            this.countSystemSyncManagerJobsLoaded = -1;
            this.countAllJobsSaved = -1;
            this.countSystemServerJobsSaved = -1;
            this.countSystemSyncManagerJobsSaved = -1;
            this.countAllJobsLoaded = jobStorePersistStats.countAllJobsLoaded;
            this.countSystemServerJobsLoaded = jobStorePersistStats.countSystemServerJobsLoaded;
            this.countSystemSyncManagerJobsLoaded = jobStorePersistStats.countSystemSyncManagerJobsLoaded;
            this.countAllJobsSaved = jobStorePersistStats.countAllJobsSaved;
            this.countSystemServerJobsSaved = jobStorePersistStats.countSystemServerJobsSaved;
            this.countSystemSyncManagerJobsSaved = jobStorePersistStats.countSystemSyncManagerJobsSaved;
        }

        public String toString() {
            return "FirstLoad: " + this.countAllJobsLoaded + "/" + this.countSystemServerJobsLoaded + "/" + this.countSystemSyncManagerJobsLoaded + " LastSave: " + this.countAllJobsSaved + "/" + this.countSystemServerJobsSaved + "/" + this.countSystemSyncManagerJobsSaved;
        }

        public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
            long jStart = protoOutputStream.start(j);
            long jStart2 = protoOutputStream.start(1146756268033L);
            protoOutputStream.write(1120986464257L, this.countAllJobsLoaded);
            protoOutputStream.write(1120986464258L, this.countSystemServerJobsLoaded);
            protoOutputStream.write(1120986464259L, this.countSystemSyncManagerJobsLoaded);
            protoOutputStream.end(jStart2);
            long jStart3 = protoOutputStream.start(1146756268034L);
            protoOutputStream.write(1120986464257L, this.countAllJobsSaved);
            protoOutputStream.write(1120986464258L, this.countSystemServerJobsSaved);
            protoOutputStream.write(1120986464259L, this.countSystemSyncManagerJobsSaved);
            protoOutputStream.end(jStart3);
            protoOutputStream.end(jStart);
        }
    }
}
