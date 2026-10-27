package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public class ThreadHandoffProducerQueueImpl implements ThreadHandoffProducerQueue {
    private final Executor mExecutor;
    private boolean mQueueing = false;
    private final Deque<Runnable> mRunnableList = new ArrayDeque();

    public ThreadHandoffProducerQueueImpl(Executor executor) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized void addToQueueOrExecute(Runnable runnable) {
        if (this.mQueueing) {
            this.mRunnableList.add(runnable);
        } else {
            this.mExecutor.execute(runnable);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized void remove(Runnable runnable) {
        this.mRunnableList.remove(runnable);
    }
}
