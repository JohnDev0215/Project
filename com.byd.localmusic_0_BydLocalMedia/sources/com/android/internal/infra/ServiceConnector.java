package com.android.internal.infra;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.DebugUtils;
import android.util.Log;
import com.android.internal.infra.ServiceConnector;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: classes4.dex */
public interface ServiceConnector<I extends IInterface> {

    @FunctionalInterface
    public interface Job<II, R> {
        R run(II ii) throws Exception;
    }

    AndroidFuture<I> connect();

    AndroidFuture<Void> post(VoidJob<I> voidJob);

    <R> AndroidFuture<R> postAsync(Job<I, CompletableFuture<R>> job);

    <R> AndroidFuture<R> postForResult(Job<I, R> job);

    boolean run(VoidJob<I> voidJob);

    void unbind();

    @FunctionalInterface
    public interface VoidJob<II> extends Job<II, Void> {
        void runNoResult(II ii) throws Exception;

        @Override // com.android.internal.infra.ServiceConnector.Job
        default Void run(II ii) throws Exception {
            runNoResult(ii);
            return null;
        }
    }

    public static class Impl<I extends IInterface> extends ArrayDeque<Job<I, ?>> implements ServiceConnector<I>, ServiceConnection, IBinder.DeathRecipient, Runnable {
        static final boolean DEBUG = false;
        private static final long DEFAULT_DISCONNECT_TIMEOUT_MS = 15000;
        private static final long DEFAULT_REQUEST_TIMEOUT_MS = 30000;
        static final String LOG_TAG = "ServiceConnector.Impl";
        private final Function<IBinder, I> mBinderAsInterface;
        private final int mBindingFlags;
        protected final Context mContext;
        private final Intent mIntent;
        private final int mUserId;
        private final Queue<Job<I, ?>> mQueue = this;
        private final List<Impl<I>.CompletionAwareJob<I, ?>> mUnfinishedJobs = new ArrayList();
        private final ServiceConnection mServiceConnection = this;
        private final Runnable mTimeoutDisconnect = this;
        private volatile I mService = null;
        private boolean mBinding = false;
        private boolean mUnbinding = false;
        private Impl<I>.CompletionAwareJob<I, I> mServiceConnectionFutureCache = null;

        static /* synthetic */ IInterface lambda$connect$0(IInterface iInterface) throws Exception {
            return iInterface;
        }

        protected long getAutoDisconnectTimeoutMs() {
            return DEFAULT_DISCONNECT_TIMEOUT_MS;
        }

        protected long getRequestTimeoutMs() {
            return 30000L;
        }

        protected void onServiceConnectionStatusChanged(I i, boolean z) {
        }

        protected void onServiceUnbound() {
        }

        public Impl(Context context, Intent intent, int i, int i2, Function<IBinder, I> function) {
            this.mContext = context;
            this.mIntent = intent;
            this.mBindingFlags = i;
            this.mUserId = i2;
            this.mBinderAsInterface = function;
        }

        protected Handler getJobHandler() {
            return Handler.getMain();
        }

        protected boolean bindService(ServiceConnection serviceConnection, Handler handler) {
            return this.mContext.bindServiceAsUser(this.mIntent, serviceConnection, this.mBindingFlags | 1, handler, UserHandle.of(this.mUserId));
        }

        protected I binderAsInterface(IBinder iBinder) {
            return this.mBinderAsInterface.apply(iBinder);
        }

        @Override // com.android.internal.infra.ServiceConnector
        public boolean run(VoidJob<I> voidJob) {
            return enqueue(voidJob);
        }

        @Override // com.android.internal.infra.ServiceConnector
        public AndroidFuture<Void> post(VoidJob<I> voidJob) {
            return postForResult((Job) voidJob);
        }

        @Override // com.android.internal.infra.ServiceConnector
        public <R> Impl<I>.CompletionAwareJob<I, R> postForResult(Job<I, R> job) {
            Impl<I>.CompletionAwareJob<I, R> completionAwareJob = new CompletionAwareJob<>();
            completionAwareJob.mDelegate = (Job) Objects.requireNonNull(job);
            enqueue((Impl<I>.CompletionAwareJob<I, ?>) completionAwareJob);
            return completionAwareJob;
        }

        @Override // com.android.internal.infra.ServiceConnector
        public <R> AndroidFuture<R> postAsync(Job<I, CompletableFuture<R>> job) {
            Impl<I>.CompletionAwareJob<I, ?> completionAwareJob = new CompletionAwareJob<>();
            completionAwareJob.mDelegate = (Job) Objects.requireNonNull(job);
            completionAwareJob.mAsync = true;
            enqueue(completionAwareJob);
            return completionAwareJob;
        }

        @Override // com.android.internal.infra.ServiceConnector
        public synchronized AndroidFuture<I> connect() {
            if (this.mServiceConnectionFutureCache == null) {
                Impl<I>.CompletionAwareJob<I, I> completionAwareJob = new CompletionAwareJob<>();
                this.mServiceConnectionFutureCache = completionAwareJob;
                completionAwareJob.mDelegate = new Job() { // from class: com.android.internal.infra.-$$Lambda$ServiceConnector$Impl$3vLWxkP1Z6JyExzdZboFFp1zM20
                    @Override // com.android.internal.infra.ServiceConnector.Job
                    public final Object run(Object obj) {
                        return ServiceConnector.Impl.lambda$connect$0((IInterface) obj);
                    }
                };
                I i = this.mService;
                if (i != null) {
                    this.mServiceConnectionFutureCache.complete(i);
                } else {
                    enqueue((Impl<I>.CompletionAwareJob<I, ?>) this.mServiceConnectionFutureCache);
                }
            }
            return this.mServiceConnectionFutureCache;
        }

        private void enqueue(Impl<I>.CompletionAwareJob<I, ?> completionAwareJob) {
            if (enqueue(completionAwareJob)) {
                return;
            }
            completionAwareJob.completeExceptionally(new IllegalStateException("Failed to post a job to handler. Likely " + getJobHandler().getLooper() + " is exiting"));
        }

        private boolean enqueue(Job<I, ?> job) {
            cancelTimeout();
            return getJobHandler().sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.internal.infra.-$$Lambda$T7zIZMFnvwrmtbuTMXLaZHHp-9s
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((ServiceConnector.Impl) obj).enqueueJobThread((ServiceConnector.Job) obj2);
                }
            }, this, job));
        }

        void enqueueJobThread(Job<I, ?> job) {
            cancelTimeout();
            if (this.mUnbinding) {
                completeExceptionally(job, new IllegalStateException("Service is unbinding. Ignoring " + job));
                return;
            }
            if (!this.mQueue.offer(job)) {
                completeExceptionally(job, new IllegalStateException("Failed to add to queue: " + job));
                return;
            }
            if (isBound()) {
                processQueue();
                return;
            }
            if (this.mBinding) {
                return;
            }
            if (bindService(this.mServiceConnection, getJobHandler())) {
                this.mBinding = true;
                return;
            }
            completeExceptionally(job, new IllegalStateException("Failed to bind to service " + this.mIntent));
        }

        private void cancelTimeout() {
            Handler.getMain().removeCallbacks(this.mTimeoutDisconnect);
        }

        void completeExceptionally(Job<?, ?> job, Throwable th) {
            CompletionAwareJob completionAwareJob = (CompletionAwareJob) castOrNull(job, CompletionAwareJob.class);
            if (completionAwareJob != null) {
                completionAwareJob.completeExceptionally(th);
            }
            if (completionAwareJob != null) {
                return;
            }
            Log.e(LOG_TAG, "Job failed: " + job, th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        static <BASE, T extends BASE> T castOrNull(BASE base, Class<T> cls) {
            if (cls.isInstance(base)) {
                return base;
            }
            return null;
        }

        private void processQueue() {
            I i;
            while (true) {
                Job<I, ?> jobPoll = this.mQueue.poll();
                if (jobPoll != null) {
                    Impl<I>.CompletionAwareJob<I, ?> completionAwareJob = (CompletionAwareJob) castOrNull(jobPoll, CompletionAwareJob.class);
                    try {
                        i = this.mService;
                    } catch (Throwable th) {
                        completeExceptionally(jobPoll, th);
                    }
                    if (i == null) {
                        return;
                    }
                    Object objRun = jobPoll.run(i);
                    if (completionAwareJob != null) {
                        if (completionAwareJob.mAsync) {
                            this.mUnfinishedJobs.add(completionAwareJob);
                            ((CompletionStage) objRun).whenComplete(completionAwareJob);
                        } else {
                            completionAwareJob.complete(objRun);
                        }
                    }
                } else {
                    maybeScheduleUnbindTimeout();
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void maybeScheduleUnbindTimeout() {
            if (this.mUnfinishedJobs.isEmpty() && this.mQueue.isEmpty()) {
                scheduleUnbindTimeout();
            }
        }

        private void scheduleUnbindTimeout() {
            long autoDisconnectTimeoutMs = getAutoDisconnectTimeoutMs();
            if (autoDisconnectTimeoutMs > 0) {
                Handler.getMain().postDelayed(this.mTimeoutDisconnect, autoDisconnectTimeoutMs);
            }
        }

        private boolean isBound() {
            return this.mService != null;
        }

        @Override // com.android.internal.infra.ServiceConnector
        public void unbind() {
            this.mUnbinding = true;
            getJobHandler().sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.infra.-$$Lambda$XuWfs8-IsKaNygi8YjlVGjedkIw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ServiceConnector.Impl) obj).unbindJobThread();
                }
            }, this));
        }

        void unbindJobThread() {
            cancelTimeout();
            I i = this.mService;
            boolean z = i != null;
            if (z) {
                onServiceConnectionStatusChanged(i, false);
                this.mContext.unbindService(this.mServiceConnection);
                i.asBinder().unlinkToDeath(this, 0);
                this.mService = null;
            }
            this.mBinding = false;
            this.mUnbinding = false;
            synchronized (this) {
                if (this.mServiceConnectionFutureCache != null) {
                    this.mServiceConnectionFutureCache.cancel(true);
                    this.mServiceConnectionFutureCache = null;
                }
            }
            cancelPendingJobs();
            if (z) {
                onServiceUnbound();
            }
        }

        protected void cancelPendingJobs() {
            while (true) {
                Job<I, ?> jobPoll = this.mQueue.poll();
                if (jobPoll == null) {
                    return;
                }
                CompletionAwareJob completionAwareJob = (CompletionAwareJob) castOrNull(jobPoll, CompletionAwareJob.class);
                if (completionAwareJob != null) {
                    completionAwareJob.cancel(false);
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (this.mUnbinding) {
                Log.i(LOG_TAG, "Ignoring onServiceConnected due to ongoing unbinding: " + this);
                return;
            }
            I i = (I) binderAsInterface(iBinder);
            this.mService = i;
            this.mBinding = false;
            try {
                iBinder.linkToDeath(this, 0);
            } catch (RemoteException e) {
                Log.e(LOG_TAG, "onServiceConnected " + componentName + ": ", e);
            }
            onServiceConnectionStatusChanged(i, true);
            processQueue();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.mBinding = true;
            I i = this.mService;
            if (i != null) {
                onServiceConnectionStatusChanged(i, false);
                this.mService = null;
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            binderDied();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mService = null;
            unbind();
        }

        @Override // java.lang.Runnable
        public void run() {
            onTimeout();
        }

        private void onTimeout() {
            unbind();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder("ServiceConnector@");
            sb.append(System.identityHashCode(this) % 1000);
            sb.append("(");
            sb.append(this.mIntent);
            sb.append(", user: ");
            sb.append(this.mUserId);
            sb.append(")[");
            sb.append(stateToString());
            if (!this.mQueue.isEmpty()) {
                sb.append(", ");
                sb.append(this.mQueue.size());
                sb.append(" pending job(s)");
            }
            if (!this.mUnfinishedJobs.isEmpty()) {
                sb.append(", ");
                sb.append(this.mUnfinishedJobs.size());
                sb.append(" unfinished async job(s)");
            }
            sb.append("]");
            return sb.toString();
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.append((CharSequence) str).append("ServiceConnector:").println();
            printWriter.append((CharSequence) str).append("  ").append((CharSequence) String.valueOf(this.mIntent)).println();
            printWriter.append((CharSequence) str).append("  ").append("userId: ").append((CharSequence) String.valueOf(this.mUserId)).println();
            printWriter.append((CharSequence) str).append("  ").append("State: ").append((CharSequence) stateToString()).println();
            printWriter.append((CharSequence) str).append("  ").append("Pending jobs: ").append((CharSequence) String.valueOf(this.mQueue.size())).println();
            printWriter.append((CharSequence) str).append("  ").append("Unfinished async jobs: ").append((CharSequence) String.valueOf(this.mUnfinishedJobs.size())).println();
        }

        private String stateToString() {
            return this.mBinding ? "Binding..." : this.mUnbinding ? "Unbinding..." : isBound() ? "Bound" : "Unbound";
        }

        private void logTrace() {
            Log.i(LOG_TAG, TextUtils.join(" -> ", DebugUtils.callersWithin(ServiceConnector.class, 1)) + "(" + this + ")");
        }

        class CompletionAwareJob<II, R> extends AndroidFuture<R> implements Job<II, R>, BiConsumer<R, Throwable> {
            boolean mAsync = false;
            private String mDebugName;
            Job<II, R> mDelegate;

            CompletionAwareJob() {
                long requestTimeoutMs = Impl.this.getRequestTimeoutMs();
                if (requestTimeoutMs > 0) {
                    orTimeout(requestTimeoutMs, TimeUnit.MILLISECONDS);
                }
            }

            private static /* synthetic */ boolean lambda$new$0(StackTraceElement stackTraceElement) {
                return !stackTraceElement.getClassName().contains(ServiceConnector.class.getName());
            }

            @Override // com.android.internal.infra.ServiceConnector.Job
            public R run(II ii) throws Exception {
                return this.mDelegate.run(ii);
            }

            @Override // com.android.internal.infra.AndroidFuture, java.util.concurrent.CompletableFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                if (z) {
                    Log.w(Impl.LOG_TAG, "mayInterruptIfRunning not supported - ignoring");
                }
                return super.cancel(z) || Impl.this.mQueue.remove(this);
            }

            @Override // java.util.concurrent.CompletableFuture
            public String toString() {
                return this.mDelegate + " wrapped into " + super.toString();
            }

            @Override // java.util.function.BiConsumer
            public void accept(R r, Throwable th) {
                if (th != null) {
                    completeExceptionally(th);
                } else {
                    complete(r);
                }
            }

            @Override // com.android.internal.infra.AndroidFuture
            protected void onCompleted(R r, Throwable th) {
                super.onCompleted(r, th);
                if (Impl.this.mUnfinishedJobs.remove(this)) {
                    Impl.this.maybeScheduleUnbindTimeout();
                }
            }
        }
    }
}
