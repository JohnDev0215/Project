package com.facebook.datasource;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractDataSource<T> implements DataSource<T> {

    @Nullable
    private static volatile DataSourceInstrumenter sDataSourceInstrumenter;

    @Nullable
    private Map<String, Object> mExtras;

    @GuardedBy("this")
    @Nullable
    private T mResult = null;

    @GuardedBy("this")
    @Nullable
    private Throwable mFailureThrowable = null;

    @GuardedBy("this")
    private float mProgress = 0.0f;

    @GuardedBy("this")
    private boolean mIsClosed = false;

    @GuardedBy("this")
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue<>();

    public interface DataSourceInstrumenter {
        Runnable decorateRunnable(Runnable runnable, String tag);
    }

    private enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    protected void closeResult(@Nullable T result) {
    }

    @Override // com.facebook.datasource.DataSource
    public boolean hasMultipleResults() {
        return false;
    }

    protected AbstractDataSource() {
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    protected void setExtras(@Nullable Map<String, Object> extras) {
        this.mExtras = extras;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized float getProgress() {
        return this.mProgress;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            this.mIsClosed = true;
            T t = this.mResult;
            this.mResult = null;
            if (t != null) {
                closeResult(t);
            }
            if (!isFinished()) {
                notifyDataSubscribers();
            }
            synchronized (this) {
                this.mSubscribers.clear();
            }
            return true;
        }
    }

    @Override // com.facebook.datasource.DataSource
    public void subscribe(final DataSubscriber<T> dataSubscriber, final Executor executor) {
        Preconditions.checkNotNull(dataSubscriber);
        Preconditions.checkNotNull(executor);
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            if (this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                this.mSubscribers.add(Pair.create(dataSubscriber, executor));
            }
            boolean z = hasResult() || isFinished() || wasCancelled();
            if (z) {
                notifyDataSubscriber(dataSubscriber, executor, hasFailed(), wasCancelled());
            }
        }
    }

    private void notifyDataSubscribers() {
        boolean zHasFailed = hasFailed();
        boolean zWasCancelled = wasCancelled();
        for (Pair<DataSubscriber<T>, Executor> pair : this.mSubscribers) {
            notifyDataSubscriber((DataSubscriber) pair.first, (Executor) pair.second, zHasFailed, zWasCancelled);
        }
    }

    protected void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, final Executor executor, final boolean isFailure, final boolean isCancellation) {
        Runnable runnableDecorateRunnable = new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.1
            @Override // java.lang.Runnable
            public void run() {
                if (isFailure) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (isCancellation) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        };
        DataSourceInstrumenter dataSourceInstrumenter = getDataSourceInstrumenter();
        if (dataSourceInstrumenter != null) {
            runnableDecorateRunnable = dataSourceInstrumenter.decorateRunnable(runnableDecorateRunnable, "AbstractDataSource_notifyDataSubscriber");
        }
        executor.execute(runnableDecorateRunnable);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean wasCancelled() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isClosed()     // Catch: java.lang.Throwable -> L12
            if (r0 == 0) goto Lf
            boolean r0 = r1.isFinished()     // Catch: java.lang.Throwable -> L12
            if (r0 != 0) goto Lf
            r0 = 1
            goto L10
        Lf:
            r0 = 0
        L10:
            monitor-exit(r1)
            return r0
        L12:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.wasCancelled():boolean");
    }

    protected boolean setResult(@Nullable T value, boolean isLast, @Nullable Map<String, Object> extras) {
        setExtras(extras);
        boolean resultInternal = setResultInternal(value, isLast);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    protected boolean setFailure(Throwable throwable) {
        return setFailure(throwable, null);
    }

    protected boolean setFailure(Throwable throwable, @Nullable Map<String, Object> extras) {
        boolean failureInternal = setFailureInternal(throwable, extras);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    protected boolean setProgress(float progress) {
        boolean progressInternal = setProgressInternal(progress);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    private boolean setResultInternal(@Nullable T value, boolean isLast) {
        T t;
        T t2 = null;
        try {
            synchronized (this) {
                try {
                    try {
                        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                            if (isLast) {
                                this.mDataSourceStatus = DataSourceStatus.SUCCESS;
                                this.mProgress = 1.0f;
                            }
                            T t3 = this.mResult;
                            if (t3 != value) {
                                try {
                                    this.mResult = value;
                                    t = t3;
                                } catch (Throwable th) {
                                    th = th;
                                    t2 = t3;
                                    throw th;
                                }
                            } else {
                                t = null;
                            }
                            return true;
                        }
                        if (value != null) {
                            closeResult(value);
                        }
                        return false;
                    } catch (Throwable th2) {
                        t2 = value;
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } finally {
            if (t2 != null) {
                closeResult(t2);
            }
        }
    }

    private synchronized boolean setFailureInternal(Throwable throwable, @Nullable Map<String, Object> extras) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = throwable;
            this.mExtras = extras;
            return true;
        }
        return false;
    }

    private synchronized boolean setProgressInternal(float progress) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            if (progress < this.mProgress) {
                return false;
            }
            this.mProgress = progress;
            return true;
        }
        return false;
    }

    protected void notifyProgressUpdate() {
        for (Pair<DataSubscriber<T>, Executor> pair : this.mSubscribers) {
            final DataSubscriber dataSubscriber = (DataSubscriber) pair.first;
            ((Executor) pair.second).execute(new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.2
                @Override // java.lang.Runnable
                public void run() {
                    dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }

    @Nullable
    public static DataSourceInstrumenter getDataSourceInstrumenter() {
        return sDataSourceInstrumenter;
    }
}
