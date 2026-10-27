package com.byd.music.utils.task;

import com.byd.music.utils.task.pool.TaskPriority;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Task<ResultType> {
    volatile State state = State.Null;
    TaskProxy taskProxy = null;

    protected abstract ResultType doBackground() throws Exception;

    public Executor getExecutor() {
        return null;
    }

    public TaskPriority getPriority() {
        return null;
    }

    protected void onCancelled(CancelledException cancelledException) {
    }

    protected abstract void onError(Throwable th, boolean z);

    protected abstract void onFinished(ResultType resulttype);

    protected void onStart() {
    }

    protected void onUpdate(int i, Object... objArr) {
    }

    public final void update(int i, Object... objArr) {
        TaskProxy taskProxy = this.taskProxy;
        if (taskProxy != null) {
            taskProxy.onUpdate(i, objArr);
        }
    }

    public final void cancel() {
        this.state = State.Cancelled;
        TaskProxy taskProxy = this.taskProxy;
        if (taskProxy != null) {
            taskProxy.cancel();
        }
    }

    public final State getState() {
        return this.state;
    }

    public final boolean isStopped() {
        return this.state.value() > State.Running.value();
    }

    public static class CancelledException extends RuntimeException {
        public CancelledException(String str) {
            super(str);
        }
    }

    public enum State {
        Null(0),
        Waiting(1),
        Running(2),
        Finished(3),
        Cancelled(4),
        Error(5);

        private final int value;

        State(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }
}
