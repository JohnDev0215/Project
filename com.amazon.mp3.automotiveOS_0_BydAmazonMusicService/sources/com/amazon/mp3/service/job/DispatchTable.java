package com.amazon.mp3.service.job;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes4.dex */
final class DispatchTable {
    private static DispatchTable sInstance;
    private AtomicLong mNextJobId = new AtomicLong();
    private HashMap<Long, JobEntry> mAllJobs = new HashMap<>();
    private HashMap<Long, HashSet<JobEntry>> mSessions = new HashMap<>();

    private DispatchTable(Context context) {
    }

    public static synchronized DispatchTable getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DispatchTable(context);
        }
        return sInstance;
    }

    public synchronized long addJob(Long sessionId) {
        Long lValueOf;
        HashSet<JobEntry> hashSet = this.mSessions.get(sessionId);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.mSessions.put(sessionId, hashSet);
        }
        lValueOf = Long.valueOf(this.mNextJobId.incrementAndGet());
        JobEntry jobEntry = new JobEntry(lValueOf, sessionId);
        this.mAllJobs.put(lValueOf, jobEntry);
        hashSet.add(jobEntry);
        return lValueOf.longValue();
    }

    public synchronized void removeSession(Long sessionId) {
        HashSet<JobEntry> hashSet = this.mSessions.get(sessionId);
        if (hashSet != null) {
            Iterator<JobEntry> it = hashSet.iterator();
            while (it.hasNext()) {
                this.mAllJobs.remove(it.next().mJobId);
            }
            this.mSessions.remove(sessionId);
        }
    }

    public synchronized JobEntry[] getAllReadyJobsForSession(long sessionId) {
        ArrayList arrayList;
        HashSet<JobEntry> hashSet = this.mSessions.get(Long.valueOf(sessionId));
        arrayList = new ArrayList();
        if (hashSet != null) {
            for (JobEntry jobEntry : hashSet) {
                if (jobEntry.mState == 0) {
                    arrayList.add(jobEntry);
                }
            }
        }
        return (JobEntry[]) arrayList.toArray(new JobEntry[arrayList.size()]);
    }

    public synchronized JobEntry getJob(Long id) {
        return this.mAllJobs.get(id);
    }

    class JobEntry {
        private final Long mJobId;
        private final Long mSessionId;
        private int mState = 0;

        public JobEntry(Long jobId, Long sessionId) {
            this.mJobId = jobId;
            this.mSessionId = sessionId;
        }

        public Long getId() {
            return this.mJobId;
        }

        public int getState() {
            return this.mState;
        }

        public void setState(int state) {
            this.mState = state;
            if (state == 5) {
                synchronized (DispatchTable.this) {
                    HashSet hashSet = (HashSet) DispatchTable.this.mSessions.get(this.mSessionId);
                    if (hashSet != null) {
                        hashSet.remove(this);
                        if (hashSet.size() == 0) {
                            DispatchTable.this.mSessions.remove(this.mSessionId);
                        }
                    }
                    DispatchTable.this.mAllJobs.remove(this.mJobId);
                }
            }
        }
    }
}
