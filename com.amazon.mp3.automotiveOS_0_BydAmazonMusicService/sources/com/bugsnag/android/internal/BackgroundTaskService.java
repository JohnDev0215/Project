package com.bugsnag.android.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackgroundTaskService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0001\u001dB9\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H\u0002J\u001a\u0010\n\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007J(\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\fJ\u0006\u0010\u000e\u001a\u00020\u0003R\u001a\u0010\u000f\u001a\u00020\u00028\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u00028\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u00028\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0017\u001a\u00020\u00028\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u00028\u0001X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/bugsnag/android/internal/BackgroundTaskService;", "", "Ljava/util/concurrent/ExecutorService;", "", "awaitTerminationSafe", "Lcom/bugsnag/android/internal/TaskType;", "taskType", "Ljava/lang/Runnable;", "runnable", "Ljava/util/concurrent/Future;", "submitTask", "T", "Ljava/util/concurrent/Callable;", "callable", "shutdown", "errorExecutor", "Ljava/util/concurrent/ExecutorService;", "getErrorExecutor$bugsnag_android_core_release", "()Ljava/util/concurrent/ExecutorService;", "sessionExecutor", "getSessionExecutor$bugsnag_android_core_release", "ioExecutor", "getIoExecutor$bugsnag_android_core_release", "internalReportExecutor", "getInternalReportExecutor$bugsnag_android_core_release", "defaultExecutor", "getDefaultExecutor$bugsnag_android_core_release", "<init>", "(Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/ExecutorService;Ljava/util/concurrent/ExecutorService;)V", "SafeFuture", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class BackgroundTaskService {
    private final ExecutorService defaultExecutor;
    private final ExecutorService errorExecutor;
    private final ExecutorService internalReportExecutor;
    private final ExecutorService ioExecutor;
    private final ExecutorService sessionExecutor;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TaskType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TaskType.ERROR_REQUEST.ordinal()] = 1;
            iArr[TaskType.SESSION_REQUEST.ordinal()] = 2;
            iArr[TaskType.IO.ordinal()] = 3;
            iArr[TaskType.INTERNAL_REPORT.ordinal()] = 4;
            iArr[TaskType.DEFAULT.ordinal()] = 5;
        }
    }

    public BackgroundTaskService() {
        this(null, null, null, null, null, 31, null);
    }

    public BackgroundTaskService(ExecutorService executorService, ExecutorService executorService2, ExecutorService executorService3, ExecutorService executorService4, ExecutorService executorService5) {
        this.errorExecutor = executorService;
        this.sessionExecutor = executorService2;
        this.ioExecutor = executorService3;
        this.internalReportExecutor = executorService4;
        this.defaultExecutor = executorService5;
    }

    public /* synthetic */ BackgroundTaskService(ExecutorService executorService, ExecutorService executorService2, ExecutorService executorService3, ExecutorService executorService4, ExecutorService executorService5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? BackgroundTaskServiceKt.createExecutor("Bugsnag Error thread", TaskType.ERROR_REQUEST, true) : executorService, (i & 2) != 0 ? BackgroundTaskServiceKt.createExecutor("Bugsnag Session thread", TaskType.SESSION_REQUEST, true) : executorService2, (i & 4) != 0 ? BackgroundTaskServiceKt.createExecutor("Bugsnag IO thread", TaskType.IO, true) : executorService3, (i & 8) != 0 ? BackgroundTaskServiceKt.createExecutor("Bugsnag Internal Report thread", TaskType.INTERNAL_REPORT, false) : executorService4, (i & 16) != 0 ? BackgroundTaskServiceKt.createExecutor("Bugsnag Default thread", TaskType.DEFAULT, false) : executorService5);
    }

    public final Future<?> submitTask(TaskType taskType, Runnable runnable) throws RejectedExecutionException {
        Callable<Object> callable = Executors.callable(runnable);
        Intrinsics.checkExpressionValueIsNotNull(callable, "Executors.callable(runnable)");
        return submitTask(taskType, callable);
    }

    public final <T> Future<T> submitTask(TaskType taskType, Callable<T> callable) throws RejectedExecutionException {
        FutureTask futureTask = new FutureTask(callable);
        int i = WhenMappings.$EnumSwitchMapping$0[taskType.ordinal()];
        if (i == 1) {
            this.errorExecutor.execute(futureTask);
        } else if (i == 2) {
            this.sessionExecutor.execute(futureTask);
        } else if (i == 3) {
            this.ioExecutor.execute(futureTask);
        } else if (i == 4) {
            this.internalReportExecutor.execute(futureTask);
        } else if (i == 5) {
            this.defaultExecutor.execute(futureTask);
        }
        return new SafeFuture(futureTask, taskType);
    }

    public final void shutdown() {
        this.internalReportExecutor.shutdownNow();
        this.defaultExecutor.shutdownNow();
        this.errorExecutor.shutdown();
        this.sessionExecutor.shutdown();
        this.ioExecutor.shutdown();
        awaitTerminationSafe(this.errorExecutor);
        awaitTerminationSafe(this.sessionExecutor);
        awaitTerminationSafe(this.ioExecutor);
    }

    private final void awaitTerminationSafe(ExecutorService executorService) {
        try {
            executorService.awaitTermination(1500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
    }

    /* JADX INFO: compiled from: BackgroundTaskService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0096\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\r\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eJ \u0010\r\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0002\u0010\u0013J\t\u0010\u0014\u001a\u00020\tH\u0096\u0001J\t\u0010\u0015\u001a\u00020\tH\u0096\u0001R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/bugsnag/android/internal/BackgroundTaskService$SafeFuture;", "V", "Ljava/util/concurrent/Future;", "delegate", "Ljava/util/concurrent/FutureTask;", "taskType", "Lcom/bugsnag/android/internal/TaskType;", "(Ljava/util/concurrent/FutureTask;Lcom/bugsnag/android/internal/TaskType;)V", "cancel", "", "p0", "ensureTaskGetSafe", "", "get", "()Ljava/lang/Object;", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "isCancelled", "isDone", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    private static final class SafeFuture<V> implements Future<V> {
        private final FutureTask<V> delegate;
        private final TaskType taskType;

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean p0) {
            return this.delegate.cancel(p0);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.delegate.isDone();
        }

        public SafeFuture(FutureTask<V> futureTask, TaskType taskType) {
            this.delegate = futureTask;
            this.taskType = taskType;
        }

        @Override // java.util.concurrent.Future
        public V get() {
            ensureTaskGetSafe();
            return this.delegate.get();
        }

        @Override // java.util.concurrent.Future
        public V get(long timeout, TimeUnit unit) {
            ensureTaskGetSafe();
            return this.delegate.get(timeout, unit);
        }

        private final void ensureTaskGetSafe() {
            if (this.delegate.isDone()) {
                return;
            }
            Thread threadCurrentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "JThread.currentThread()");
            if (BackgroundTaskServiceKt.getTaskType(threadCurrentThread) == this.taskType) {
                this.delegate.run();
            }
        }
    }
}
