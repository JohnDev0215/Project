package com.amazon.mp3.task;

import com.amazon.mp3.concurrent.PrioritizedPSTPExecutor;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class ExecutionController {
    ConnectStrategy mConnectStrategy;
    ExceptionHandlingStrategy mExceptionHandlingStrategy;
    PrioritizedPSTPExecutor mExecutorService;
    RecurrenceStrategy mRecurrenceStrategy;
    RetryStrategy mRetryStrategy;
    String mTAG;

    void handleCompletion(Task task) {
    }

    public ExecutionController() {
        this(null, null, null, null, null);
    }

    public ExecutionController(RetryStrategy retryStrategy, ExceptionHandlingStrategy exceptionHandlingStrategy, ConnectStrategy connectStrategy) {
        this(null, null, retryStrategy, exceptionHandlingStrategy, connectStrategy);
    }

    public ExecutionController(PrioritizedPSTPExecutor executorService, RecurrenceStrategy recurrenceStrategy, RetryStrategy retryStrategy, ExceptionHandlingStrategy exceptionHandlingStrategy, ConnectStrategy connectStrategy) {
        this.mTAG = getClass().getSimpleName();
        if (executorService == null) {
            this.mExecutorService = new PrioritizedPSTPExecutor(1);
        } else {
            this.mExecutorService = executorService;
        }
        if (exceptionHandlingStrategy == null) {
            this.mExceptionHandlingStrategy = new DefaultExceptionHandlingStrategy();
        } else {
            this.mExceptionHandlingStrategy = exceptionHandlingStrategy;
        }
        if (recurrenceStrategy == null) {
            this.mRecurrenceStrategy = new RandomizedRecurrenceStrategy();
        }
        if (retryStrategy == null) {
            this.mRetryStrategy = new DefaultRetryStrategy();
        } else {
            this.mRetryStrategy = retryStrategy;
        }
        if (connectStrategy == null) {
            this.mConnectStrategy = new NullConnectStrategy();
        } else {
            this.mConnectStrategy = connectStrategy;
        }
    }

    private void scheduleTask(Task task) {
        DelayInfo retryDelay = this.mRetryStrategy.getRetryDelay(task.getRunCount());
        if (retryDelay == null) {
            Log.warning(this.mTAG, "Null retry delay provided - not resubmitting the task");
        } else if (task.isPreemptive()) {
            this.mExecutorService.schedulePreempt(task, retryDelay.getDelay(), retryDelay.getTimeUnit());
        } else {
            this.mExecutorService.schedule(task, retryDelay.getDelay(), retryDelay.getTimeUnit());
        }
    }

    public void submitTask(Task task) {
        task.setExecutionController(this);
        scheduleTask(task);
    }

    void handleException(Task task, Throwable t) {
        if (this.mExceptionHandlingStrategy.retryOnException(t)) {
            Log.warning(this.mTAG, "retrying task due to exception", t);
            scheduleTask(task);
        } else {
            Log.warning(this.mTAG, "Encountered exception - not resubmiting the task", t);
        }
    }

    public Object getConnection() {
        return this.mConnectStrategy.getConnection();
    }
}
