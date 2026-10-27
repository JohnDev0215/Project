package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.ThreadingModel;
import com.amazon.bolthttp.internal.Commander;
import com.amazon.bolthttp.internal.Commander.SharedState;
import com.amazon.bolthttp.internal.Logger;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class ExecutorCommander<T, S extends Commander.SharedState<T>> extends Commander<T, S> {
    private final ExecutorService mExecutor;

    ExecutorCommander(@Nonnull S s, @Nonnull ThreadingModel.ExecutorType executorType) {
        super(s);
        Preconditions.checkNotNull(executorType, "executorType");
        this.mExecutor = getExecutor(executorType);
    }

    ThreadPoolExecutor getExecutor(ThreadingModel.ExecutorType executorType) {
        ThreadPoolExecutor executor = getSharedState().getConfig().getThreadingModel().getExecutor(executorType);
        Preconditions.checkNotNull(executor, "executor");
        if (executor.getQueue() instanceof PriorityBlockingQueue) {
            return executor;
        }
        throw new IllegalStateException("Executor " + executorType + " is not using a PriorityBlockingQueue");
    }

    @Override // com.amazon.bolthttp.internal.Commander
    void handleCommand(@Nonnull Command<T, S> command) {
        this.mExecutor.execute(new ExecuteCommandRunnable(command));
    }

    private final class ExecuteCommandRunnable implements Runnable, Comparable {
        private final Command<T, S> mCommand;

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return 0;
        }

        public ExecuteCommandRunnable(Command<T, S> command) {
            this.mCommand = command;
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [com.amazon.bolthttp.internal.Commander$SharedState] */
        @Override // java.lang.Runnable
        public void run() {
            try {
                this.mCommand.execute(ExecutorCommander.this.getSharedState());
            } catch (RuntimeException e) {
                Logger logger = ExecutorCommander.this.getSharedState().getLogger();
                Logger.Type type = Logger.Type.ERROR;
                logger.log(type, "Uncaught exception on thread %s", Thread.currentThread().getName());
                logger.log(type, logger.getStackTraceString(e));
                throw e;
            }
        }

        public String toString() {
            return this.mCommand.getTraceName();
        }
    }
}
