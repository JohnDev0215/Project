package com.google.firebase.messaging;

import android.content.Context;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes6.dex */
class TopicsSubscriber {
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private final Context context;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;

    @GuardedBy("pendingOperations")
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new ArrayMap();

    @GuardedBy("this")
    private boolean syncScheduledOrRunning = false;

    @VisibleForTesting
    static Task<TopicsSubscriber> createInstance(final FirebaseMessaging firebaseMessaging, final Metadata metadata, final GmsRpc gmsRpc, final Context context, @NonNull final ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new Callable() { // from class: com.google.firebase.messaging.TopicsSubscriber$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return TopicsSubscriber.lambda$createInstance$0(context, scheduledExecutorService, firebaseMessaging, metadata, gmsRpc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ TopicsSubscriber lambda$createInstance$0(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging, Metadata metadata, GmsRpc gmsRpc) throws Exception {
        return new TopicsSubscriber(firebaseMessaging, metadata, TopicsStore.getInstance(context, scheduledExecutorService), gmsRpc, context, scheduledExecutorService);
    }

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging, Metadata metadata, TopicsStore topicsStore, GmsRpc gmsRpc, Context context, @NonNull ScheduledExecutorService scheduledExecutorService) {
        this.firebaseMessaging = firebaseMessaging;
        this.metadata = metadata;
        this.store = topicsStore;
        this.rpc = gmsRpc;
        this.context = context;
        this.syncExecutor = scheduledExecutorService;
    }

    boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    void startTopicsSyncIfNecessary() {
        if (hasPendingOperation()) {
            startSync();
        }
    }

    private void startSync() {
        if (isSyncScheduledOrRunning()) {
            return;
        }
        syncWithDelaySecondsInternal(0L);
    }

    void syncWithDelaySecondsInternal(long j) {
        scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30L, 2 * j), MAX_DELAY_SEC)), j);
        setSyncScheduledOrRunning(true);
    }

    void scheduleSyncTaskWithDelaySeconds(Runnable runnable, long j) {
        this.syncExecutor.schedule(runnable, j, TimeUnit.SECONDS);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0018, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000d, code lost:
    
        if (isDebugLogEnabled() == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        android.util.Log.d("FirebaseMessaging", "topic sync succeeded");
     */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean syncTopics() throws java.io.IOException {
        /*
            r2 = this;
        L0:
            monitor-enter(r2)
            com.google.firebase.messaging.TopicsStore r0 = r2.store     // Catch: java.lang.Throwable -> L2b
            com.google.firebase.messaging.TopicOperation r0 = r0.getNextTopicOperation()     // Catch: java.lang.Throwable -> L2b
            if (r0 != 0) goto L19
            boolean r0 = isDebugLogEnabled()     // Catch: java.lang.Throwable -> L2b
            if (r0 == 0) goto L16
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L2b
        L16:
            r0 = 1
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            return r0
        L19:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            boolean r1 = r2.performTopicOperation(r0)
            if (r1 != 0) goto L22
            r0 = 0
            return r0
        L22:
            com.google.firebase.messaging.TopicsStore r1 = r2.store
            r1.removeTopicOperation(r0)
            r2.markCompletePendingOperation(r0)
            goto L0
        L2b:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.syncTopics():boolean");
    }

    private void markCompletePendingOperation(TopicOperation topicOperation) {
        synchronized (this.pendingOperations) {
            String strSerialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(strSerialize)) {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque = this.pendingOperations.get(strSerialize);
                TaskCompletionSource<Void> taskCompletionSourcePoll = arrayDeque.poll();
                if (taskCompletionSourcePoll != null) {
                    taskCompletionSourcePoll.setResult(null);
                }
                if (arrayDeque.isEmpty()) {
                    this.pendingOperations.remove(strSerialize);
                }
            }
        }
    }

    @WorkerThread
    boolean performTopicOperation(TopicOperation topicOperation) throws IOException {
        try {
            String operation = topicOperation.getOperation();
            byte b = -1;
            int iHashCode = operation.hashCode();
            if (iHashCode != 83) {
                if (iHashCode == 85 && operation.equals("U")) {
                    b = 1;
                }
            } else if (operation.equals("S")) {
                b = 0;
            }
            if (b == 0) {
                blockingSubscribeToTopic(topicOperation.getTopic());
                if (isDebugLogEnabled()) {
                    Log.d("FirebaseMessaging", "Subscribe to topic: " + topicOperation.getTopic() + " succeeded.");
                }
            } else if (b == 1) {
                blockingUnsubscribeFromTopic(topicOperation.getTopic());
                if (isDebugLogEnabled()) {
                    Log.d("FirebaseMessaging", "Unsubscribe from topic: " + topicOperation.getTopic() + " succeeded.");
                }
            } else if (isDebugLogEnabled()) {
                Log.d("FirebaseMessaging", "Unknown topic operation" + topicOperation + ".");
            }
            return true;
        } catch (IOException e) {
            if ("SERVICE_NOT_AVAILABLE".equals(e.getMessage()) || "INTERNAL_SERVER_ERROR".equals(e.getMessage())) {
                Log.e("FirebaseMessaging", "Topic operation failed: " + e.getMessage() + ". Will retry Topic operation.");
                return false;
            }
            if (e.getMessage() == null) {
                Log.e("FirebaseMessaging", "Topic operation failed without exception message. Will retry Topic operation.");
                return false;
            }
            throw e;
        }
    }

    @WorkerThread
    private void blockingSubscribeToTopic(String str) throws IOException {
        awaitTask(this.rpc.subscribeToTopic(this.firebaseMessaging.blockingGetToken(), str));
    }

    @WorkerThread
    private void blockingUnsubscribeFromTopic(String str) throws IOException {
        awaitTask(this.rpc.unsubscribeFromTopic(this.firebaseMessaging.blockingGetToken(), str));
    }

    @WorkerThread
    private static <T> void awaitTask(Task<T> task) throws IOException {
        try {
            Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e2);
        } catch (TimeoutException e3) {
            e = e3;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        }
    }

    synchronized boolean isSyncScheduledOrRunning() {
        return this.syncScheduledOrRunning;
    }

    synchronized void setSyncScheduledOrRunning(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseMessaging", 3);
    }
}
