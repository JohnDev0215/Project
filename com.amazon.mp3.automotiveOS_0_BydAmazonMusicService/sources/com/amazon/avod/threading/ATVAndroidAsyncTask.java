package com.amazon.avod.threading;

import android.os.AsyncTask;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ATVAndroidAsyncTask<Params, Progress, Result> {
    static final ExecutorService ASYNC_TASK_EXECUTOR;
    private static ExecutorService sAsyncTaskExecutor;
    private final AtomicBoolean mIsCancelled = new AtomicBoolean(false);
    InternalAsyncTask<Params, Progress, Result> mAsyncTaskDelegate = new InternalAsyncTask<>(this);

    protected abstract Result doInBackground(Params... paramsArr);

    protected void onCancelled() {
    }

    protected void onPostExecute(Result result) {
    }

    protected void onPreExecute() {
    }

    protected void onProgressUpdate(Progress... progressArr) {
    }

    static {
        ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(ATVAndroidAsyncTask.class, new String[0]).withFixedThreadPoolSize(5).allowCoreThreadExpiry(1L, TimeUnit.MINUTES).build();
        ASYNC_TASK_EXECUTOR = threadPoolExecutorBuild;
        sAsyncTaskExecutor = threadPoolExecutorBuild;
    }

    public static void cancelAsyncTasksIfNeeded(@Nonnull Iterable<? extends ATVAndroidAsyncTask<?, ?, ?>> iterable) {
        Preconditions.checkNotNull(iterable, "tasksToCancel");
        Iterator<? extends ATVAndroidAsyncTask<?, ?, ?>> it = iterable.iterator();
        while (it.hasNext()) {
            cancelAsyncTaskIfNeeded(it.next());
        }
    }

    public static void cancelAsyncTaskIfNeeded(@Nullable ATVAndroidAsyncTask<?, ?, ?> aTVAndroidAsyncTask) {
        if (aTVAndroidAsyncTask == null) {
            return;
        }
        DLog.logf("Async task (%s) is (%s), cancelling it", aTVAndroidAsyncTask, aTVAndroidAsyncTask.getStatus());
        aTVAndroidAsyncTask.cancel(true);
    }

    public final boolean cancel(boolean z) {
        this.mIsCancelled.set(true);
        return this.mAsyncTaskDelegate.cancel(z);
    }

    public final AsyncTask.Status getStatus() {
        return this.mAsyncTaskDelegate.getStatus();
    }

    public final boolean isCancelled() {
        return this.mIsCancelled.get();
    }

    @Nonnull
    public AsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return this.mAsyncTaskDelegate.executeOnExecutor(sAsyncTaskExecutor, paramsArr);
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    static class InternalAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        private final ATVAndroidAsyncTask<Params, Progress, Result> mATVAndroidAsyncTask;

        InternalAsyncTask(ATVAndroidAsyncTask<Params, Progress, Result> aTVAndroidAsyncTask) {
            this.mATVAndroidAsyncTask = aTVAndroidAsyncTask;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mATVAndroidAsyncTask.onPreExecute();
        }

        @Override // android.os.AsyncTask
        protected Result doInBackground(Params... paramsArr) {
            return this.mATVAndroidAsyncTask.doInBackground(paramsArr);
        }

        @Override // android.os.AsyncTask
        protected void onProgressUpdate(Progress... progressArr) {
            if (this.mATVAndroidAsyncTask.isCancelled()) {
                return;
            }
            this.mATVAndroidAsyncTask.onProgressUpdate(progressArr);
        }

        @Override // android.os.AsyncTask
        protected void onCancelled(Result result) {
            this.mATVAndroidAsyncTask.onCancelled(result);
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            this.mATVAndroidAsyncTask.onCancelled();
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Result result) {
            if (this.mATVAndroidAsyncTask.isCancelled()) {
                onCancelled(result);
            } else {
                this.mATVAndroidAsyncTask.onPostExecute(result);
            }
        }
    }
}
