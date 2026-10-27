package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda11;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.events.OutboxStatusEvent;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.datastore.syncengine.MutationOutbox;
import com.amplifyframework.datastore.syncengine.PendingMutation;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes5.dex */
final class PersistentMutationOutbox implements MutationOutbox {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final PendingMutation.Converter converter;
    private final Subject<MutationOutbox.OutboxEvent> events;
    private final Set<TimeBasedUuid> inFlightMutations;
    private final MutationQueue mutationQueue;
    private final Semaphore semaphore;
    private final LocalStorageAdapter storage;

    PersistentMutationOutbox(@NonNull LocalStorageAdapter localStorageAdapter) {
        this(localStorageAdapter, new MutationQueue());
    }

    @VisibleForTesting
    PersistentMutationOutbox(@NonNull LocalStorageAdapter localStorageAdapter, @NonNull MutationQueue mutationQueue) {
        Objects.requireNonNull(localStorageAdapter);
        this.storage = localStorageAdapter;
        this.mutationQueue = mutationQueue;
        this.inFlightMutations = new HashSet();
        this.converter = new GsonPendingMutationConverter();
        this.events = PublishSubject.create().toSerialized();
        this.semaphore = new Semaphore(1);
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    public boolean hasPendingMutation(@NonNull String str) {
        Objects.requireNonNull(str);
        return this.mutationQueue.nextMutationForModelId(str) != null;
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    @NonNull
    public <T extends Model> Completable enqueue(@NonNull final PendingMutation<T> pendingMutation) {
        Objects.requireNonNull(pendingMutation);
        Completable completableDoOnSubscribe = Completable.defer(new Supplier() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return this.f$0.lambda$enqueue$0(pendingMutation);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$enqueue$1((Disposable) obj);
            }
        });
        Semaphore semaphore = this.semaphore;
        semaphore.getClass();
        return completableDoOnSubscribe.doOnTerminate(new PersistentMutationOutbox$$ExternalSyntheticLambda2(semaphore));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$enqueue$0(PendingMutation pendingMutation) throws Throwable {
        PendingMutation<? extends Model> pendingMutationNextMutationForModelId = this.mutationQueue.nextMutationForModelId(pendingMutation.getMutatedItem().getPrimaryKeyString());
        if (pendingMutationNextMutationForModelId == null || this.inFlightMutations.contains(pendingMutationNextMutationForModelId.getMutationId())) {
            return save(pendingMutation).andThen(notifyContentAvailable());
        }
        return resolveConflict(pendingMutationNextMutationForModelId, pendingMutation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$enqueue$1(Disposable disposable) throws Throwable {
        this.semaphore.acquire();
    }

    private <T extends Model> Completable resolveConflict(@NonNull PendingMutation<T> pendingMutation, @NonNull PendingMutation<T> pendingMutation2) {
        return new IncomingMutationConflictHandler(this, pendingMutation, pendingMutation2, null).resolve();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends Model> Completable save(final PendingMutation<T> pendingMutation) {
        final PendingMutation.PersistentRecord record = this.converter.toRecord(pendingMutation);
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$save$3(record, pendingMutation, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$save$3(PendingMutation.PersistentRecord persistentRecord, final PendingMutation pendingMutation, final CompletableEmitter completableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.storage;
        StorageItemChange.Initiator initiator = StorageItemChange.Initiator.SYNC_ENGINE;
        QueryPredicate queryPredicateAll = QueryPredicates.all();
        com.amplifyframework.core.Consumer consumer = new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda15
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$2(pendingMutation, completableEmitter, (StorageItemChange) obj);
            }
        };
        completableEmitter.getClass();
        localStorageAdapter.save(persistentRecord, initiator, queryPredicateAll, consumer, new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$2(PendingMutation pendingMutation, CompletableEmitter completableEmitter, StorageItemChange storageItemChange) {
        this.mutationQueue.updateExistingQueueItemOrAppendNew(pendingMutation.getMutationId(), pendingMutation);
        LOG.info("Successfully enqueued " + pendingMutation);
        announceEventEnqueued(pendingMutation);
        publishCurrentOutboxStatus();
        completableEmitter.onComplete();
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    @NonNull
    public Completable remove(@NonNull TimeBasedUuid timeBasedUuid) {
        Completable completableDoOnSubscribe = removeNotLocking(timeBasedUuid).doOnSubscribe(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$remove$4((Disposable) obj);
            }
        });
        Semaphore semaphore = this.semaphore;
        semaphore.getClass();
        return completableDoOnSubscribe.doOnTerminate(new PersistentMutationOutbox$$ExternalSyntheticLambda2(semaphore));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$remove$4(Disposable disposable) throws Throwable {
        this.semaphore.acquire();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public Completable removeNotLocking(@NonNull final TimeBasedUuid timeBasedUuid) {
        Objects.requireNonNull(timeBasedUuid);
        return Completable.defer(new Supplier() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return this.f$0.lambda$removeNotLocking$8(timeBasedUuid);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$removeNotLocking$8(final TimeBasedUuid timeBasedUuid) throws Throwable {
        final PendingMutation<? extends Model> mutationById = this.mutationQueue.getMutationById(timeBasedUuid);
        if (mutationById == null) {
            throw new DataStoreException("Outbox was asked to remove a mutation with ID = " + timeBasedUuid + ". However, there was no mutation with that ID in the outbox, to begin with.", AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
        return Maybe.create(new MaybeOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.core.MaybeOnSubscribe
            public final void subscribe(MaybeEmitter maybeEmitter) throws Throwable {
                this.f$0.lambda$null$6(mutationById, timeBasedUuid, maybeEmitter);
            }
        }).flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$null$7((MutationOutbox.OutboxEvent) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$6(final PendingMutation pendingMutation, final TimeBasedUuid timeBasedUuid, final MaybeEmitter maybeEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.storage;
        PendingMutation.PersistentRecord record = this.converter.toRecord(pendingMutation);
        StorageItemChange.Initiator initiator = StorageItemChange.Initiator.SYNC_ENGINE;
        QueryPredicate queryPredicateAll = QueryPredicates.all();
        com.amplifyframework.core.Consumer consumer = new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda13
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$5(pendingMutation, timeBasedUuid, maybeEmitter, (StorageItemChange) obj);
            }
        };
        maybeEmitter.getClass();
        localStorageAdapter.delete(record, initiator, queryPredicateAll, (com.amplifyframework.core.Consumer<StorageItemChange<PendingMutation.PersistentRecord>>) consumer, new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda14
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                maybeEmitter.onError((DataStoreException) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$5(PendingMutation pendingMutation, TimeBasedUuid timeBasedUuid, MaybeEmitter maybeEmitter, StorageItemChange storageItemChange) {
        this.mutationQueue.removeById(pendingMutation.getMutationId());
        this.inFlightMutations.remove(timeBasedUuid);
        LOG.info("Successfully removed from mutations outbox" + pendingMutation);
        if (!this.mutationQueue.isEmpty()) {
            maybeEmitter.onSuccess(MutationOutbox.OutboxEvent.CONTENT_AVAILABLE);
        } else {
            maybeEmitter.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$null$7(MutationOutbox.OutboxEvent outboxEvent) throws Throwable {
        return notifyContentAvailable();
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    @NonNull
    public Completable load() {
        Completable completableDoOnSubscribe = Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$load$10(completableEmitter);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$load$11((Disposable) obj);
            }
        });
        Semaphore semaphore = this.semaphore;
        semaphore.getClass();
        return completableDoOnSubscribe.doOnTerminate(new PersistentMutationOutbox$$ExternalSyntheticLambda2(semaphore));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$10(final CompletableEmitter completableEmitter) throws Throwable {
        this.inFlightMutations.clear();
        this.mutationQueue.clear();
        LocalStorageAdapter localStorageAdapter = this.storage;
        QueryOptions queryOptionsMatchesAll = Where.matchesAll();
        com.amplifyframework.core.Consumer consumer = new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda12
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$9(completableEmitter, (Iterator) obj);
            }
        };
        completableEmitter.getClass();
        localStorageAdapter.query(PendingMutation.PersistentRecord.class, queryOptionsMatchesAll, consumer, new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$9(CompletableEmitter completableEmitter, Iterator it) {
        while (it.hasNext()) {
            try {
                this.mutationQueue.add(this.converter.fromRecord((PendingMutation.PersistentRecord) it.next()));
            } catch (Throwable th) {
                completableEmitter.onError(th);
                return;
            }
        }
        publishCurrentOutboxStatus();
        completableEmitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$11(Disposable disposable) throws Throwable {
        this.semaphore.acquire();
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    @NonNull
    public Observable<MutationOutbox.OutboxEvent> events() {
        return this.events;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyContentAvailable$12() throws Throwable {
        this.events.onNext(MutationOutbox.OutboxEvent.CONTENT_AVAILABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable notifyContentAvailable() {
        return Completable.fromAction(new Action() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$notifyContentAvailable$12();
            }
        });
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    @Nullable
    public PendingMutation<? extends Model> peek() {
        return this.mutationQueue.peek();
    }

    @Override // com.amplifyframework.datastore.syncengine.MutationOutbox
    @NonNull
    public Completable markInFlight(@NonNull final TimeBasedUuid timeBasedUuid) {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$markInFlight$13(timeBasedUuid, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$markInFlight$13(TimeBasedUuid timeBasedUuid, CompletableEmitter completableEmitter) throws Throwable {
        PendingMutation<? extends Model> mutationById = this.mutationQueue.getMutationById(timeBasedUuid);
        if (mutationById != null) {
            this.inFlightMutations.add(mutationById.getMutationId());
            completableEmitter.onComplete();
            return;
        }
        completableEmitter.onError(new DataStoreException("Outbox was asked to mark a mutation with ID = " + timeBasedUuid + " as in-flight. However, there was no mutation with that ID in the outbox, to begin with.", AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION));
    }

    private <T extends Model> void announceEventEnqueued(PendingMutation<T> pendingMutation) {
        Amplify.Hub.publish(HubChannel.DATASTORE, OutboxMutationEvent.fromPendingMutation(pendingMutation).toHubEvent());
    }

    private void publishCurrentOutboxStatus() {
        Amplify.Hub.publish(HubChannel.DATASTORE, new OutboxStatusEvent(this.mutationQueue.isEmpty()).toHubEvent());
    }

    private final class IncomingMutationConflictHandler<T extends Model> {
        private final PendingMutation<T> existing;
        private final PendingMutation<T> incoming;

        /* synthetic */ IncomingMutationConflictHandler(PersistentMutationOutbox persistentMutationOutbox, PendingMutation pendingMutation, PendingMutation pendingMutation2, AnonymousClass1 anonymousClass1) {
            this(pendingMutation, pendingMutation2);
        }

        private IncomingMutationConflictHandler(@NonNull PendingMutation<T> pendingMutation, @NonNull PendingMutation<T> pendingMutation2) {
            this.existing = pendingMutation;
            this.incoming = pendingMutation2;
        }

        Completable resolve() {
            PersistentMutationOutbox.LOG.debug("IncomingMutationConflict -  existing " + this.existing.getMutationType() + " incoming " + this.incoming.getMutationType());
            int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[this.incoming.getMutationType().ordinal()];
            if (i == 1) {
                return handleIncomingCreate();
            }
            if (i == 2) {
                return handleIncomingUpdate();
            }
            if (i == 3) {
                return handleIncomingDelete();
            }
            return unknownMutationType(this.existing.getMutationType());
        }

        private Completable handleIncomingCreate() {
            if (AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[this.existing.getMutationType().ordinal()] == 1) {
                return conflictingCreationError();
            }
            return unexpectedMutationScenario();
        }

        private Completable handleIncomingUpdate() {
            int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[this.existing.getMutationType().ordinal()];
            if (i == 1) {
                if ((this.incoming.getMutatedItem() instanceof SerializedModel) && (this.existing.getMutatedItem() instanceof SerializedModel)) {
                    return PersistentMutationOutbox.this.removeNotLocking(this.existing.getMutationId()).andThen(saveAndNotify(mergeAndCreatePendingMutation((SerializedModel) this.incoming.getMutatedItem(), (SerializedModel) this.existing.getMutatedItem(), this.incoming.getModelSchema(), PendingMutation.Type.CREATE)));
                }
                return overwriteExistingAndNotify(PendingMutation.Type.CREATE, QueryPredicates.all());
            }
            if (i != 2) {
                if (i == 3) {
                    return modelAlreadyScheduledForDeletion();
                }
                return unexpectedMutationScenario();
            }
            if (QueryPredicates.all().equals(this.incoming.getPredicate())) {
                if (!(this.incoming.getMutatedItem() instanceof SerializedModel) || !(this.existing.getMutatedItem() instanceof SerializedModel)) {
                    return PersistentMutationOutbox.this.removeNotLocking(this.existing.getMutationId()).andThen(saveAndNotify(this.incoming));
                }
                return PersistentMutationOutbox.this.removeNotLocking(this.existing.getMutationId()).andThen(saveAndNotify(mergeAndCreatePendingMutation((SerializedModel) this.incoming.getMutatedItem(), (SerializedModel) this.existing.getMutatedItem(), this.incoming.getModelSchema(), PendingMutation.Type.UPDATE)));
            }
            return saveAndNotify(this.incoming);
        }

        private Completable handleIncomingDelete() {
            int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[this.existing.getMutationType().ordinal()];
            if (i == 1) {
                return PersistentMutationOutbox.this.inFlightMutations.contains(this.existing.getMutationId()) ? PersistentMutationOutbox.this.save(this.incoming) : PersistentMutationOutbox.this.removeNotLocking(this.existing.getMutationId());
            }
            if (i == 2 || i == 3) {
                return overwriteExistingAndNotify(PendingMutation.Type.DELETE, this.incoming.getPredicate());
            }
            return unexpectedMutationScenario();
        }

        private Completable overwriteExistingAndNotify(@NonNull PendingMutation.Type type, @NonNull QueryPredicate queryPredicate) {
            return PersistentMutationOutbox.this.save(PendingMutation.instance(this.existing.getMutationId(), this.incoming.getMutatedItem(), this.incoming.getModelSchema(), type, queryPredicate)).andThen(PersistentMutationOutbox.this.notifyContentAvailable());
        }

        private Completable saveAndNotify(PendingMutation<T> pendingMutation) {
            return PersistentMutationOutbox.this.save(pendingMutation).andThen(PersistentMutationOutbox.this.notifyContentAvailable());
        }

        private Completable conflictingCreationError() {
            return Completable.error(new DataStoreException("Attempted to enqueue a model creation, but there is already a pending creation for that model ID.", "Please report at https://github.com/aws-amplify/amplify-android/issues."));
        }

        private Completable modelAlreadyScheduledForDeletion() {
            return Completable.error(new DataStoreException("Attempted to enqueue a model mutation, but that model already had a delete mutation pending.", "This should not be possible. Please report on GitHub issues."));
        }

        private Completable unknownMutationType(PendingMutation.Type type) {
            return Completable.error(new DataStoreException("Existing mutation of unknown type = " + type, "Please report at https://github.com/aws-amplify/amplify-android/issues."));
        }

        private Completable unexpectedMutationScenario() {
            return Completable.error(new DataStoreException("Unable to handle existing mutation of type = " + this.existing.getMutationType() + " and incoming mutation of type = " + this.incoming.getMutationType(), "Please report at https://github.com/aws-amplify/amplify-android/issues."));
        }

        private PendingMutation<T> mergeAndCreatePendingMutation(SerializedModel serializedModel, SerializedModel serializedModel2, ModelSchema modelSchema, PendingMutation.Type type) {
            return PendingMutation.instance(SerializedModel.merge(serializedModel, serializedModel2, modelSchema), modelSchema, type, QueryPredicates.all());
        }
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.syncengine.PersistentMutationOutbox$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type;

        static {
            int[] iArr = new int[PendingMutation.Type.values().length];
            $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type = iArr;
            try {
                iArr[PendingMutation.Type.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[PendingMutation.Type.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[PendingMutation.Type.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
