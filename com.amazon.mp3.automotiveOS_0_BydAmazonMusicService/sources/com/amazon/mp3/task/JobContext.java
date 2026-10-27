package com.amazon.mp3.task;

import com.amazon.mp3.task.AbstractMetadata;
import java.util.Collection;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public class JobContext<T extends AbstractMetadata> implements Comparable<JobContext<T>> {
    protected Collection<JobListener<T>> mListeners;
    protected T mMetadata;
    private int mPriority = Integer.MAX_VALUE;
    protected Status mStatus = Status.INIT;

    public enum Status {
        INIT,
        FAILED,
        FINISHED,
        IN_PROGRESS,
        EXISTS
    }

    public JobContext(T metadata) {
        this.mMetadata = metadata;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
        notifyListeners();
    }

    public T getMetadata() {
        return this.mMetadata;
    }

    public synchronized void addListener(JobListener<T> listener) {
        if (this.mListeners == null) {
            this.mListeners = new HashSet();
        }
        this.mListeners.add(listener);
    }

    private synchronized void notifyListeners() {
        Collection<JobListener<T>> collection = this.mListeners;
        if (collection != null) {
            for (JobListener<T> jobListener : collection) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$task$JobContext$Status[this.mStatus.ordinal()];
                if (i == 1) {
                    jobListener.onComplete(this);
                } else if (i == 2) {
                    jobListener.onFailure(this);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.task.JobContext$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$task$JobContext$Status;

        static {
            int[] iArr = new int[Status.values().length];
            $SwitchMap$com$amazon$mp3$task$JobContext$Status = iArr;
            try {
                iArr[Status.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$task$JobContext$Status[Status.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    @Override // java.lang.Comparable
    public int compareTo(JobContext<T> tJobContext) {
        return this.mPriority - tJobContext.mPriority;
    }

    public boolean equals(Object o) {
        if (o instanceof JobContext) {
            return this.mMetadata.equals(((JobContext) o).mMetadata);
        }
        return false;
    }

    public int hashCode() {
        return this.mMetadata.hashCode();
    }
}
