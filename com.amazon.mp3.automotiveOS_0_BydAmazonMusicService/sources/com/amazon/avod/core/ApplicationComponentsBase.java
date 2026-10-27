package com.amazon.avod.core;

import amazon.android.di.AppInitializationTracker;
import android.content.Context;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ApplicationComponentsBase {

    @Nullable
    protected CountDownLatch mBlockingInitializationCallsFinished;

    @Nullable
    protected CountDownLatch mDeferrableInitializationCallsFinished;

    @Nullable
    protected CountDownLatch mNonBlockingInitializationCallsFinished;
    protected final List<InitializationTask> mBlockingCalls = Lists.newLinkedList();
    protected final List<InitializationTask> mNonBlockingCalls = Lists.newLinkedList();
    protected final List<InitializationTask> mDeferrableCalls = Lists.newLinkedList();
    protected final AtomicBoolean mInitializationStarted = new AtomicBoolean(false);
    protected final CountDownLatch mWaitForAsyncInitializationToStart = new CountDownLatch(1);

    public interface InitParams {
        @Nonnull
        Context getContext();
    }

    public interface InitializationTask {
        void initialize() throws InitializationException;
    }

    protected abstract ImmutableList<InitializationTask> initializeCoreTasks(@Nonnull InitParams initParams);

    protected abstract void preInitialize(@Nonnull InitParams initParams);

    public void addBlockingInitializationCall(@Nonnull InitializationTask initializationTask, @Nonnull String str) {
        Preconditions.checkNotNull(initializationTask, "blockingCall");
        Preconditions.checkNotNull(str, "componentName");
        if (this.mInitializationStarted.get()) {
            throw new IllegalStateException("Can't add new calls after initialization has started");
        }
        this.mBlockingCalls.add(new ProfiledInitializationTask(initializationTask, "ApplicationComponents-BlockingInit", str));
    }

    public void addBackgroundInitializationCall(@Nonnull InitializationTask initializationTask, @Nonnull String str) {
        Preconditions.checkNotNull(initializationTask, "nonBlockingCall");
        Preconditions.checkNotNull(str, "componentName");
        if (this.mInitializationStarted.get()) {
            throw new IllegalStateException("Can't add new calls after initialization has started");
        }
        this.mNonBlockingCalls.add(new ProfiledInitializationTask(initializationTask, "ApplicationComponents-BackgroundInit", str));
    }

    public void startFullInitializationAsync(@Nonnull InitParams initParams) {
        Preconditions.checkNotNull(initParams, "initParams");
        preInitialize(initParams);
        if (this.mInitializationStarted.getAndSet(true)) {
            throw new IllegalStateException("Async initialization can only be called once");
        }
        ImmutableList<InitializationTask> immutableListInitializeCoreTasks = initializeCoreTasks(initParams);
        CountDownLatch countDownLatch = new CountDownLatch(immutableListInitializeCoreTasks.size() + this.mBlockingCalls.size());
        ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(4).build();
        try {
            UnmodifiableIterator<InitializationTask> it = immutableListInitializeCoreTasks.iterator();
            while (it.hasNext()) {
                threadPoolExecutorBuild.execute(new InitializeRunnable(it.next(), Optional.of(countDownLatch)));
            }
            this.mBlockingInitializationCallsFinished = countDownLatch;
            Iterator<InitializationTask> it2 = this.mBlockingCalls.iterator();
            while (it2.hasNext()) {
                threadPoolExecutorBuild.execute(new InitializeRunnable(it2.next(), Optional.of(countDownLatch)));
            }
            startFullInitializationAsyncInner(threadPoolExecutorBuild, countDownLatch);
        } finally {
            threadPoolExecutorBuild.shutdown();
            this.mWaitForAsyncInitializationToStart.countDown();
        }
    }

    void startFullInitializationAsyncInner(@Nonnull ExecutorService executorService, @Nonnull CountDownLatch countDownLatch) {
        try {
            this.mNonBlockingInitializationCallsFinished = new CountDownLatch(this.mNonBlockingCalls.size());
            DLog.devf("AppInit: Initializing nonBlocking background tasks");
            Iterator<InitializationTask> it = this.mNonBlockingCalls.iterator();
            while (it.hasNext()) {
                executorService.execute(new WaitOnCore(countDownLatch, new InitializeRunnable(it.next(), Optional.of(this.mNonBlockingInitializationCallsFinished)), AppInitializationTracker.getInstance(), true, false));
            }
            DLog.devf("AppInit: Initializing deferrable tasks");
            this.mDeferrableInitializationCallsFinished = new CountDownLatch(this.mDeferrableCalls.size());
            Iterator<InitializationTask> it2 = this.mDeferrableCalls.iterator();
            while (it2.hasNext()) {
                executorService.execute(new WaitOnCore(countDownLatch, new InitializeRunnable(it2.next(), Optional.of(this.mDeferrableInitializationCallsFinished)), AppInitializationTracker.getInstance(), true, true));
            }
        } finally {
            executorService.shutdown();
            this.mWaitForAsyncInitializationToStart.countDown();
        }
    }

    public void waitForFullInitialization() {
        Uninterruptibles.awaitUninterruptibly(this.mWaitForAsyncInitializationToStart);
        Uninterruptibles.awaitUninterruptibly(this.mBlockingInitializationCallsFinished);
    }

    static final class InitializeRunnable implements Runnable {
        private final InitializationTask mInitializationTask;
        private final Optional<CountDownLatch> mOnInitializationComplete;

        public InitializeRunnable(@Nonnull InitializationTask initializationTask, @Nonnull Optional<CountDownLatch> optional) {
            this.mInitializationTask = initializationTask;
            this.mOnInitializationComplete = optional;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.mInitializationTask.initialize();
                if (this.mOnInitializationComplete.isPresent()) {
                    this.mOnInitializationComplete.get().countDown();
                }
            } catch (InitializationException e) {
                AppInitializationTracker.getInstance().notifyInitializationError(e);
            }
        }
    }

    private static class ProfiledInitializationTask implements InitializationTask {
        private final InitializationTask mInitializationTask;
        private final String mName;
        private final String mType;

        ProfiledInitializationTask(@Nonnull InitializationTask initializationTask, @Nonnull String str, @Nonnull String str2) {
            this.mInitializationTask = (InitializationTask) Preconditions.checkNotNull(initializationTask, "initializationTask");
            this.mType = (String) Preconditions.checkNotNull(str, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            this.mName = (String) Preconditions.checkNotNull(str2, "name");
        }

        @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
        public void initialize() throws InitializationException {
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:%s", this.mType, this.mName);
            try {
                this.mInitializationTask.initialize();
            } finally {
                Profiler.endTrace(traceKeyBeginTrace);
            }
        }
    }

    static class WaitOnCore implements Runnable {
        private final AppInitializationTracker mAppInitializationTracker;
        private final Runnable mDelegate;
        private final boolean mShouldWaitOnActivityInit;
        private final boolean mShouldWaitOnAppInit;
        private final CountDownLatch mWaitOnCore;

        WaitOnCore(@Nonnull CountDownLatch countDownLatch, @Nonnull Runnable runnable, @Nonnull AppInitializationTracker appInitializationTracker, boolean z, boolean z2) {
            this.mDelegate = (Runnable) Preconditions.checkNotNull(runnable, "runnable");
            this.mWaitOnCore = (CountDownLatch) Preconditions.checkNotNull(countDownLatch, "waitOnCore");
            this.mAppInitializationTracker = (AppInitializationTracker) Preconditions.checkNotNull(appInitializationTracker, "appInitializationTracker");
            this.mShouldWaitOnAppInit = z;
            this.mShouldWaitOnActivityInit = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.VERBOSE, "WaitOnCore");
            try {
                try {
                    this.mWaitOnCore.await();
                    if (this.mShouldWaitOnAppInit) {
                        DLog.devf("AppInit: Waiting for Application Initialization");
                        this.mAppInitializationTracker.waitUntilAfterApplicationInitializationUninterruptibly("ApplicationComponents");
                        DLog.devf("AppInit: Finished waiting for Application Initialization");
                    }
                    if (this.mShouldWaitOnActivityInit) {
                        DLog.devf("AppInit: Waiting for Activity Initialization");
                        this.mAppInitializationTracker.waitUntilAfterActivityInitializationUninterruptibly("ApplicationComponents");
                        DLog.devf("AppInit: Finished waiting for Activity Initialization");
                    }
                    Profiler.endTrace(traceKeyBeginTrace);
                    this.mDelegate.run();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    Profiler.endTrace(traceKeyBeginTrace);
                }
            } catch (Throwable th) {
                Profiler.endTrace(traceKeyBeginTrace);
                throw th;
            }
        }
    }

    public static class ServiceTypesFrameworkProxy implements ServiceTypesProxy.ServiceTypesProxyDelegate {
        @Override // com.amazon.avod.servicetypes.ServiceTypesProxy.ServiceTypesProxyDelegate
        public Object beginTrace(@Nullable String str) {
            return Profiler.beginTrace(Profiler.TraceLevel.DEBUG, str);
        }

        @Override // com.amazon.avod.servicetypes.ServiceTypesProxy.ServiceTypesProxyDelegate
        public Object beginTrace(@Nullable String str, @Nullable Object obj) {
            return Profiler.beginTrace(Profiler.TraceLevel.DEBUG, str, obj);
        }

        @Override // com.amazon.avod.servicetypes.ServiceTypesProxy.ServiceTypesProxyDelegate
        public void endTrace(@Nullable Object obj) {
            if (obj instanceof TraceKey) {
                Profiler.endTrace((TraceKey) obj);
            }
        }

        @Override // com.amazon.avod.servicetypes.ServiceTypesProxy.ServiceTypesProxyDelegate
        public void exception(@Nonnull Throwable th, @Nullable String str, @Nullable Object... objArr) {
            DLog.exceptionf(th, str, objArr);
        }
    }
}
