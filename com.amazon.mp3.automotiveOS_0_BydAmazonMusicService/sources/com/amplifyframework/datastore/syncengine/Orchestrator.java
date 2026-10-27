package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.core.util.Supplier;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda10;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreConfigurationProvider;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.AppSync;
import com.amplifyframework.datastore.events.NetworkStatusEvent;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class Orchestrator {
    private static final long LOCAL_OP_TIMEOUT_SECONDS = 7;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final AtomicReference<State> currentState;
    private final CompositeDisposable disposables;
    private final MutationOutbox mutationOutbox;
    private final MutationProcessor mutationProcessor;
    private final QueryPredicateProvider queryPredicateProvider;
    private final Semaphore startStopSemaphore;
    private final StorageObserver storageObserver;
    private final SubscriptionProcessor subscriptionProcessor;
    private final SyncProcessor syncProcessor;
    private final Supplier<State> targetState;

    public enum State {
        STOPPED,
        LOCAL_ONLY,
        SYNC_VIA_API
    }

    public Orchestrator(@NonNull ModelProvider modelProvider, @NonNull SchemaRegistry schemaRegistry, @NonNull LocalStorageAdapter localStorageAdapter, @NonNull AppSync appSync, @NonNull DataStoreConfigurationProvider dataStoreConfigurationProvider, @NonNull Supplier<State> supplier, boolean z) {
        Objects.requireNonNull(schemaRegistry);
        Objects.requireNonNull(modelProvider);
        Objects.requireNonNull(appSync);
        Objects.requireNonNull(localStorageAdapter);
        PersistentMutationOutbox persistentMutationOutbox = new PersistentMutationOutbox(localStorageAdapter);
        this.mutationOutbox = persistentMutationOutbox;
        VersionRepository versionRepository = new VersionRepository(localStorageAdapter);
        Merger merger = new Merger(persistentMutationOutbox, versionRepository, localStorageAdapter);
        SyncTimeRegistry syncTimeRegistry = new SyncTimeRegistry(localStorageAdapter);
        ConflictResolver conflictResolver = new ConflictResolver(dataStoreConfigurationProvider, appSync);
        QueryPredicateProvider queryPredicateProvider = new QueryPredicateProvider(dataStoreConfigurationProvider);
        this.queryPredicateProvider = queryPredicateProvider;
        this.mutationProcessor = MutationProcessor.builder().merger(merger).versionRepository(versionRepository).schemaRegistry(schemaRegistry).mutationOutbox(persistentMutationOutbox).appSync(appSync).conflictResolver(conflictResolver).retryHandler(new RetryHandler()).build();
        this.syncProcessor = SyncProcessor.builder().modelProvider(modelProvider).schemaRegistry(schemaRegistry).syncTimeRegistry(syncTimeRegistry).appSync(appSync).merger(merger).dataStoreConfigurationProvider(dataStoreConfigurationProvider).queryPredicateProvider(queryPredicateProvider).retryHandler(new RetryHandler()).isSyncRetryEnabled(z).build();
        this.subscriptionProcessor = SubscriptionProcessor.builder().appSync(appSync).modelProvider(modelProvider).schemaRegistry(schemaRegistry).merger(merger).queryPredicateProvider(queryPredicateProvider).onFailure(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda9
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.onApiSyncFailure((Throwable) obj);
            }
        }).build();
        this.storageObserver = new StorageObserver(localStorageAdapter, persistentMutationOutbox);
        this.currentState = new AtomicReference<>(State.STOPPED);
        this.targetState = supplier;
        this.disposables = new CompositeDisposable();
        this.startStopSemaphore = new Semaphore(1);
    }

    public synchronized Completable start() {
        return performSynchronized(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$start$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0() throws Throwable {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State[this.targetState.get().ordinal()];
        if (i == 1) {
            transitionToApiSync();
        } else {
            if (i != 2) {
                return;
            }
            transitionToLocalOnly();
        }
    }

    public synchronized Completable stop() {
        return performSynchronized(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws DataStoreException {
                this.f$0.transitionToStopped();
            }
        });
    }

    private Completable performSynchronized(Action action) {
        boolean z = this.startStopSemaphore.availablePermits() > 0;
        Logger logger = LOG;
        logger.debug("Attempting to acquire lock. Permits available = " + z);
        try {
            if (!this.startStopSemaphore.tryAcquire(LOCAL_OP_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                return Completable.error(new DataStoreException("Timed out acquiring orchestrator lock.", "Retry your request."));
            }
            logger.info("Orchestrator lock acquired.");
            return Completable.fromAction(action).andThen(Completable.fromAction(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda12
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() throws Throwable {
                    this.f$0.lambda$performSynchronized$1();
                }
            }));
        } catch (InterruptedException unused) {
            return Completable.error(new DataStoreException("Interrupted while acquiring orchestrator lock.", "Retry your request."));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performSynchronized$1() throws Throwable {
        this.startStopSemaphore.release();
        LOG.info("Orchestrator lock released.");
    }

    private void unknownState(State state) throws DataStoreException {
        throw new DataStoreException("Orchestrator state machine made reference to unknown state = " + state.name(), AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.syncengine.Orchestrator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State = iArr;
            try {
                iArr[State.SYNC_VIA_API.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State[State.LOCAL_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State[State.STOPPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToStopped() throws DataStoreException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State[this.currentState.get().ordinal()];
        if (i == 1) {
            LOG.info("Orchestrator transitioning from SYNC_VIA_API to STOPPED");
            stopApiSync();
            stopObservingStorageChanges();
        } else if (i == 2) {
            LOG.info("Orchestrator transitioning from LOCAL_ONLY to STOPPED");
            stopObservingStorageChanges();
        } else if (i != 3) {
            unknownState(this.currentState.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToLocalOnly() throws DataStoreException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State[this.currentState.get().ordinal()];
        if (i == 1) {
            LOG.info("Orchestrator transitioning from SYNC_VIA_API to LOCAL_ONLY");
            stopApiSync();
        } else if (i != 2) {
            if (i == 3) {
                LOG.info("Orchestrator transitioning from STOPPED to LOCAL_ONLY");
                startObservingStorageChanges();
                publishReadyEvent();
                return;
            }
            unknownState(this.currentState.get());
        }
    }

    private void transitionToApiSync() throws DataStoreException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$Orchestrator$State[this.currentState.get().ordinal()];
        if (i != 1) {
            if (i == 2) {
                LOG.info("Orchestrator transitioning from LOCAL_ONLY to SYNC_VIA_API");
                startApiSync();
            } else {
                if (i == 3) {
                    LOG.info("Orchestrator transitioning from STOPPED to SYNC_VIA_API");
                    startObservingStorageChanges();
                    startApiSync();
                    return;
                }
                unknownState(this.currentState.get());
            }
        }
    }

    private void startObservingStorageChanges() throws DataStoreException {
        LOG.info("Starting to observe local storage changes.");
        try {
            this.mutationOutbox.load().andThen(Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda8
                @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
                public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                    this.f$0.lambda$startObservingStorageChanges$2(completableEmitter);
                }
            })).blockingAwait();
        } catch (Throwable th) {
            throw new DataStoreException("Timed out while starting to observe storage changes.", th, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startObservingStorageChanges$2(CompletableEmitter completableEmitter) throws Throwable {
        StorageObserver storageObserver = this.storageObserver;
        completableEmitter.getClass();
        storageObserver.startObservingStorageChanges(new AWSDataStorePlugin$$ExternalSyntheticLambda10(completableEmitter));
        LOG.info("Setting currentState to LOCAL_ONLY");
        this.currentState.set(State.LOCAL_ONLY);
    }

    private void stopObservingStorageChanges() {
        Logger logger = LOG;
        logger.info("Stopping observation of local storage changes.");
        this.storageObserver.stopObservingStorageChanges();
        logger.info("Setting currentState to STOPPED");
        this.currentState.set(State.STOPPED);
    }

    private void startApiSync() {
        LOG.info("Setting currentState to SYNC_VIA_API");
        this.currentState.set(State.SYNC_VIA_API);
        this.disposables.add(Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$startApiSync$3(completableEmitter);
            }
        }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                Orchestrator.lambda$startApiSync$4((Throwable) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                Orchestrator.lambda$startApiSync$5();
            }
        }).doOnDispose(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                Orchestrator.lambda$startApiSync$6();
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                this.f$0.publishReadyEvent();
            }
        }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                this.f$0.onApiSyncFailure((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startApiSync$3(CompletableEmitter completableEmitter) throws Throwable {
        Logger logger = LOG;
        logger.info("Starting API synchronization mode.");
        this.queryPredicateProvider.resolvePredicates();
        try {
            this.subscriptionProcessor.startSubscriptions();
            publishNetworkStatusEvent(true);
            long jCurrentTimeMillis = System.currentTimeMillis();
            logger.debug("About to hydrate...");
            try {
                this.syncProcessor.hydrate().blockingAwait();
                logger.debug("Hydration complete in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                logger.debug("Draining outbox...");
                this.mutationProcessor.startDrainingMutationOutbox();
                this.subscriptionProcessor.startDrainingMutationBuffer();
                completableEmitter.onComplete();
            } catch (Throwable th) {
                if (!completableEmitter.isDisposed()) {
                    completableEmitter.onError(new DataStoreException("Initial sync during DataStore initialization failed.", th, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION));
                } else {
                    LOG.warn("Initial sync during DataStore initialization failed.", th);
                    completableEmitter.onComplete();
                }
            }
        } catch (Throwable th2) {
            if (completableEmitter.tryOnError(new DataStoreException("DataStore subscriptionProcessor failed to start.", th2, "Check your internet."))) {
                return;
            }
            LOG.warn("DataStore failed to start after emitter was disposed.", th2);
            completableEmitter.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startApiSync$4(Throwable th) throws Throwable {
        LOG.error("Failure encountered while attempting to start API sync.", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startApiSync$5() throws Throwable {
        LOG.info("Started the orchestrator in API sync mode.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startApiSync$6() throws Throwable {
        LOG.debug("Orchestrator disposed the API sync");
    }

    private void publishNetworkStatusEvent(boolean z) {
        Amplify.Hub.publish(HubChannel.DATASTORE, HubEvent.create(DataStoreChannelEventName.NETWORK_STATUS, new NetworkStatusEvent(z)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishReadyEvent() {
        Amplify.Hub.publish(HubChannel.DATASTORE, HubEvent.create(DataStoreChannelEventName.READY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onApiSyncFailure(Throwable th) {
        if (State.SYNC_VIA_API.equals(this.currentState.get())) {
            LOG.warn("API sync failed - transitioning to LOCAL_ONLY.", th);
            publishNetworkStatusEvent(false);
            Completable.fromAction(new Action() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() throws DataStoreException {
                    this.f$0.transitionToLocalOnly();
                }
            }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.Orchestrator$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) throws Throwable {
                    Orchestrator.lambda$onApiSyncFailure$7((Throwable) obj);
                }
            }).subscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onApiSyncFailure$7(Throwable th) throws Throwable {
        LOG.warn("Transition to LOCAL_ONLY failed.", th);
    }

    private void stopApiSync() {
        LOG.info("Setting currentState to LOCAL_ONLY");
        this.currentState.set(State.LOCAL_ONLY);
        this.disposables.clear();
        this.subscriptionProcessor.stopAllSubscriptionActivity();
        this.mutationProcessor.stopDrainingMutationOutbox();
    }
}
