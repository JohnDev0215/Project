package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

/* JADX INFO: compiled from: TaskQueue.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010!\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0019\u001a\u00020\u0012¢\u0006\u0004\b/\u00100J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004J'\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006J\u000f\u0010\u0011\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0013\u001a\u00020\u0012H\u0016R\u001a\u0010\u0015\u001a\u00020\u00148\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00128\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\"\u0010\u000e\u001a\u00020\b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u001d\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010 R$\u0010!\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020'8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\"\u0010,\u001a\u00020\b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b,\u0010\u001d\u001a\u0004\b-\u0010\u0010\"\u0004\b.\u0010 ¨\u00061"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue;", "", "Lokhttp3/internal/concurrent/Task;", "task", "", "delayNanos", "", "schedule", "", "recurrence", "scheduleAndDecide$okhttp", "(Lokhttp3/internal/concurrent/Task;JZ)Z", "scheduleAndDecide", "cancelAll", "shutdown", "cancelAllAndDecide$okhttp", "()Z", "cancelAllAndDecide", "", "toString", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "name", "Ljava/lang/String;", "getName$okhttp", "()Ljava/lang/String;", "Z", "getShutdown$okhttp", "setShutdown$okhttp", "(Z)V", "activeTask", "Lokhttp3/internal/concurrent/Task;", "getActiveTask$okhttp", "()Lokhttp3/internal/concurrent/Task;", "setActiveTask$okhttp", "(Lokhttp3/internal/concurrent/Task;)V", "", "futureTasks", "Ljava/util/List;", "getFutureTasks$okhttp", "()Ljava/util/List;", "cancelActiveTask", "getCancelActiveTask$okhttp", "setCancelActiveTask$okhttp", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Ljava/lang/String;)V", "okhttp"}, k = 1, mv = {1, 6, 0})
public final class TaskQueue {
    private Task activeTask;
    private boolean cancelActiveTask;
    private final List<Task> futureTasks;
    private final String name;
    private boolean shutdown;
    private final TaskRunner taskRunner;

    public TaskQueue(TaskRunner taskRunner, String name) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(name, "name");
        this.taskRunner = taskRunner;
        this.name = name;
        this.futureTasks = new ArrayList();
    }

    /* JADX INFO: renamed from: getTaskRunner$okhttp, reason: from getter */
    public final TaskRunner getTaskRunner() {
        return this.taskRunner;
    }

    /* JADX INFO: renamed from: getName$okhttp, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: getShutdown$okhttp, reason: from getter */
    public final boolean getShutdown() {
        return this.shutdown;
    }

    public final void setShutdown$okhttp(boolean z) {
        this.shutdown = z;
    }

    /* JADX INFO: renamed from: getActiveTask$okhttp, reason: from getter */
    public final Task getActiveTask() {
        return this.activeTask;
    }

    public final void setActiveTask$okhttp(Task task) {
        this.activeTask = task;
    }

    public final List<Task> getFutureTasks$okhttp() {
        return this.futureTasks;
    }

    /* JADX INFO: renamed from: getCancelActiveTask$okhttp, reason: from getter */
    public final boolean getCancelActiveTask() {
        return this.cancelActiveTask;
    }

    public final void setCancelActiveTask$okhttp(boolean z) {
        this.cancelActiveTask = z;
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, Task task, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        taskQueue.schedule(task, j);
    }

    public final void schedule(Task task, long delayNanos) {
        Intrinsics.checkNotNullParameter(task, "task");
        synchronized (this.taskRunner) {
            if (getShutdown()) {
                if (task.getCancelable()) {
                    if (TaskRunner.INSTANCE.getLogger().isLoggable(Level.FINE)) {
                        TaskLoggerKt.log(task, this, "schedule canceled (queue is shutdown)");
                    }
                    return;
                } else {
                    if (TaskRunner.INSTANCE.getLogger().isLoggable(Level.FINE)) {
                        TaskLoggerKt.log(task, this, "schedule failed (queue is shutdown)");
                    }
                    throw new RejectedExecutionException();
                }
            }
            if (scheduleAndDecide$okhttp(task, delayNanos, false)) {
                getTaskRunner().kickCoordinator$okhttp(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean scheduleAndDecide$okhttp(Task task, long delayNanos, boolean recurrence) {
        String strStringPlus;
        Intrinsics.checkNotNullParameter(task, "task");
        task.initQueue$okhttp(this);
        long jNanoTime = this.taskRunner.getBackend().nanoTime();
        long j = jNanoTime + delayNanos;
        int iIndexOf = this.futureTasks.indexOf(task);
        if (iIndexOf != -1) {
            if (task.getNextExecuteNanoTime() <= j) {
                if (TaskRunner.INSTANCE.getLogger().isLoggable(Level.FINE)) {
                    TaskLoggerKt.log(task, this, "already scheduled");
                }
                return false;
            }
            this.futureTasks.remove(iIndexOf);
        }
        task.setNextExecuteNanoTime$okhttp(j);
        if (TaskRunner.INSTANCE.getLogger().isLoggable(Level.FINE)) {
            if (recurrence) {
                strStringPlus = Intrinsics.stringPlus("run again after ", TaskLoggerKt.formatDuration(j - jNanoTime));
            } else {
                strStringPlus = Intrinsics.stringPlus("scheduled after ", TaskLoggerKt.formatDuration(j - jNanoTime));
            }
            TaskLoggerKt.log(task, this, strStringPlus);
        }
        Iterator<Task> it = this.futureTasks.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (it.next().getNextExecuteNanoTime() - jNanoTime > delayNanos) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = this.futureTasks.size();
        }
        this.futureTasks.add(size, task);
        return size == 0;
    }

    public final boolean cancelAllAndDecide$okhttp() {
        Task task = this.activeTask;
        if (task != null) {
            Intrinsics.checkNotNull(task);
            if (task.getCancelable()) {
                this.cancelActiveTask = true;
            }
        }
        boolean z = false;
        int size = this.futureTasks.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                if (this.futureTasks.get(size).getCancelable()) {
                    Task task2 = this.futureTasks.get(size);
                    if (TaskRunner.INSTANCE.getLogger().isLoggable(Level.FINE)) {
                        TaskLoggerKt.log(task2, this, "canceled");
                    }
                    this.futureTasks.remove(size);
                    z = true;
                }
                if (i < 0) {
                    break;
                }
                size = i;
            }
        }
        return z;
    }

    public String toString() {
        return this.name;
    }

    public final void cancelAll() {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this.taskRunner) {
                if (cancelAllAndDecide$okhttp()) {
                    getTaskRunner().kickCoordinator$okhttp(this);
                }
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
    }

    public final void shutdown() {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this.taskRunner) {
                setShutdown$okhttp(true);
                if (cancelAllAndDecide$okhttp()) {
                    getTaskRunner().kickCoordinator$okhttp(this);
                }
                Unit unit = Unit.INSTANCE;
            }
            return;
        }
        throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
    }
}
