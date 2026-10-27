package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes6.dex */
final class TopicsStore {

    @GuardedBy("TopicsStore.class")
    private static WeakReference<TopicsStore> topicsStoreWeakReference;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;
    private SharedPreferencesQueue topicOperationsQueue;

    private TopicsStore(SharedPreferences sharedPreferences, Executor executor) {
        this.syncExecutor = executor;
        this.sharedPreferences = sharedPreferences;
    }

    @WorkerThread
    private synchronized void initStore() {
        this.topicOperationsQueue = SharedPreferencesQueue.createInstance(this.sharedPreferences, "topic_operation_queue", ",", this.syncExecutor);
    }

    @WorkerThread
    public static synchronized TopicsStore getInstance(Context context, Executor executor) {
        TopicsStore topicsStore;
        WeakReference<TopicsStore> weakReference = topicsStoreWeakReference;
        topicsStore = weakReference != null ? weakReference.get() : null;
        if (topicsStore == null) {
            topicsStore = new TopicsStore(context.getSharedPreferences("com.google.android.gms.appid", 0), executor);
            topicsStore.initStore();
            topicsStoreWeakReference = new WeakReference<>(topicsStore);
        }
        return topicsStore;
    }

    @Nullable
    synchronized TopicOperation getNextTopicOperation() {
        return TopicOperation.from(this.topicOperationsQueue.peek());
    }

    synchronized boolean removeTopicOperation(TopicOperation topicOperation) {
        return this.topicOperationsQueue.remove(topicOperation.serialize());
    }
}
