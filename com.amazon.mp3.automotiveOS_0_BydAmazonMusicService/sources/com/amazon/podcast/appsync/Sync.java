package com.amazon.podcast.appsync;

import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.WriteElement;
import android.content.Context;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Networks;
import com.amazon.podcast.appsync.QueryParameters;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class Sync<T extends WriteElement, M extends WriteElement, D, Q extends QueryParameters> {
    private static final Logger logger = LoggerFactory.getLogger("Sync");
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private Future reportingFuture;
    private final SyncData<T, M, D, Q> syncData;
    private final Object operationLock = new Object();
    private final Object reportingFutureLock = new Object();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    protected Sync(final String ownerId, final SyncData<T, M, D, Q> syncData, final MethodsDispatcher dispatcher) {
        this.ownerId = ownerId;
        this.syncData = syncData;
        this.methodsDispatcher = dispatcher;
    }

    public D read(Context context, final String str) {
        try {
            return (D) this.executorService.submit(new Callable<D>() { // from class: com.amazon.podcast.appsync.Sync.1
                @Override // java.util.concurrent.Callable
                public D call() throws Exception {
                    return (D) Sync.this.syncData.read(str);
                }
            }).get();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public LiveData<D> readLive(final Context context, final String id) {
        return this.syncData.readState(id);
    }

    public LiveData<List<D>> readAll(final Context context) {
        return this.syncData.readStates();
    }

    public LiveData<List<D>> readAll(final Set<String> ids) {
        return this.syncData.readStates(ids);
    }

    public List<D> readAll(final Q q) {
        return this.syncData.readStates(q);
    }

    public LiveData<List<D>> readAllLiveData(final Q q) {
        return this.syncData.readStatesLiveData(q);
    }

    public void write(final Context context, final T writeElement, final ConditionElement conditionElement) {
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.appsync.Sync.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (Sync.this.syncData.writeState(writeElement, conditionElement, jCurrentTimeMillis) && conditionElement == null) {
                    Sync.this.writeToOperationsInternal(context, writeElement, jCurrentTimeMillis);
                }
            }
        });
    }

    public void writeMetadata(final M writeElement) {
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.appsync.Sync.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                Sync.this.syncData.writeMetadata(writeElement);
            }
        });
    }

    public void writeLocal(final Context context, final T writeElement, final ConditionElement conditionElement) {
        this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.appsync.Sync.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Sync.logger.debug("Writing element to state and operations table without reporting: {}.", writeElement.getId());
                boolean zWriteState = Sync.this.syncData.writeState(writeElement, conditionElement, jCurrentTimeMillis);
                if (conditionElement != null) {
                    return;
                }
                if (zWriteState) {
                    synchronized (Sync.this.operationLock) {
                        Sync.this.syncData.writeToOperations(writeElement, jCurrentTimeMillis);
                    }
                    return;
                }
                Sync.logger.error("Failed to write element to state table via WriteLocalMethod: {}", writeElement.getId());
            }
        });
    }

    public ClientStates.Entry clientState(final String name) {
        ClientStates.Entry clientState;
        synchronized (this.operationLock) {
            clientState = this.syncData.getClientState(name);
        }
        return clientState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeToOperationsInternal(final Context context, final T writeElement, final long updatedTime) {
        synchronized (this.reportingFutureLock) {
            Future future = this.reportingFuture;
            if (future != null && !future.isDone()) {
                this.reportingFuture.cancel(true);
            }
        }
        synchronized (this.operationLock) {
            this.syncData.writeToOperations(writeElement, updatedTime);
            if (Networks.isNetworkConnected(context)) {
                submitForReporting(context);
            }
        }
    }

    void submitForReporting(final Context context) {
        synchronized (this.reportingFutureLock) {
            this.reportingFuture = this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.appsync.Sync.5
                @Override // java.lang.Runnable
                public void run() {
                    Sync.this.reportOperations(context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportOperations(final Context context) {
        synchronized (this.operationLock) {
            if (Networks.isNetworkConnected(context)) {
                this.methodsDispatcher.dispatch(this.ownerId, this.syncData.reportOperationMethods());
            }
        }
    }

    public void resolveOperations(final List<String> ids) {
        synchronized (this.operationLock) {
            this.executorService.submit(new Runnable() { // from class: com.amazon.podcast.appsync.Sync.6
                @Override // java.lang.Runnable
                public void run() {
                    Sync.this.syncData.deleteOperations(ids);
                }
            });
        }
    }

    public void retryOperations(final List<String> ids) {
        synchronized (this.operationLock) {
            this.methodsDispatcher.dispatch(this.ownerId, this.syncData.reportOperationMethods());
        }
    }
}
