package com.facebook.imagepipeline.producers;

/* JADX INFO: loaded from: classes5.dex */
public interface ThreadHandoffProducerQueue {
    void addToQueueOrExecute(Runnable runnable);

    void remove(Runnable runnable);
}
