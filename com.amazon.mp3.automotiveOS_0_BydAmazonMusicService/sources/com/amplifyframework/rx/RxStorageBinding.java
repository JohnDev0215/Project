package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.async.NoOpCancelable;
import com.amplifyframework.datastore.syncengine.RetryHandler$$ExternalSyntheticLambda1;
import com.amplifyframework.rx.RxAdapters;
import com.amplifyframework.storage.StorageCategory;
import com.amplifyframework.storage.StorageCategoryBehavior;
import com.amplifyframework.storage.StorageException;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;
import com.amplifyframework.storage.options.StorageGetUrlOptions;
import com.amplifyframework.storage.options.StorageListOptions;
import com.amplifyframework.storage.options.StorageRemoveOptions;
import com.amplifyframework.storage.options.StorageUploadFileOptions;
import com.amplifyframework.storage.options.StorageUploadInputStreamOptions;
import com.amplifyframework.storage.result.StorageDownloadFileResult;
import com.amplifyframework.storage.result.StorageGetUrlResult;
import com.amplifyframework.storage.result.StorageListResult;
import com.amplifyframework.storage.result.StorageRemoveResult;
import com.amplifyframework.storage.result.StorageTransferProgress;
import com.amplifyframework.storage.result.StorageUploadFileResult;
import com.amplifyframework.storage.result.StorageUploadInputStreamResult;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class RxStorageBinding implements RxStorageCategoryBehavior {
    private final StorageCategoryBehavior storage;

    interface RxStorageTransferCallbackMapper<T> {
        Cancelable emitTo(Consumer<StorageTransferProgress> consumer, Consumer<T> consumer2, Consumer<StorageException> consumer3);
    }

    RxStorageBinding() {
        this(Amplify.Storage);
    }

    @VisibleForTesting
    RxStorageBinding(StorageCategory storageCategory) {
        this.storage = storageCategory;
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public Single<StorageGetUrlResult> getUrl(final String str) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$getUrl$0(str, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$getUrl$0(String str, Consumer consumer, Consumer consumer2) {
        this.storage.getUrl(str, consumer, consumer2);
        return new NoOpCancelable();
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public Single<StorageGetUrlResult> getUrl(@NonNull final String str, @NonNull final StorageGetUrlOptions storageGetUrlOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda4
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$getUrl$1(str, storageGetUrlOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$getUrl$1(String str, StorageGetUrlOptions storageGetUrlOptions, Consumer consumer, Consumer consumer2) {
        this.storage.getUrl(str, storageGetUrlOptions, consumer, consumer2);
        return new NoOpCancelable();
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public RxProgressAwareSingleOperation<StorageDownloadFileResult> downloadFile(@NonNull String str, @NonNull File file) {
        return downloadFile(str, file, StorageDownloadFileOptions.defaultInstance());
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public RxProgressAwareSingleOperation<StorageDownloadFileResult> downloadFile(@NonNull final String str, @NonNull final File file, @NonNull final StorageDownloadFileOptions storageDownloadFileOptions) {
        return new RxProgressAwareSingleOperation<>(new RxStorageTransferCallbackMapper() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.rx.RxStorageBinding.RxStorageTransferCallbackMapper
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2, Consumer consumer3) {
                return this.f$0.lambda$downloadFile$2(str, file, storageDownloadFileOptions, consumer, consumer2, consumer3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$downloadFile$2(String str, File file, StorageDownloadFileOptions storageDownloadFileOptions, Consumer consumer, Consumer consumer2, Consumer consumer3) {
        return this.storage.downloadFile(str, file, storageDownloadFileOptions, consumer, consumer2, consumer3);
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public RxProgressAwareSingleOperation<StorageUploadFileResult> uploadFile(@NonNull String str, @NonNull File file) {
        return uploadFile(str, file, StorageUploadFileOptions.defaultInstance());
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public RxProgressAwareSingleOperation<StorageUploadFileResult> uploadFile(@NonNull final String str, @NonNull final File file, @NonNull final StorageUploadFileOptions storageUploadFileOptions) {
        return new RxProgressAwareSingleOperation<>(new RxStorageTransferCallbackMapper() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda5
            @Override // com.amplifyframework.rx.RxStorageBinding.RxStorageTransferCallbackMapper
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2, Consumer consumer3) {
                return this.f$0.lambda$uploadFile$3(str, file, storageUploadFileOptions, consumer, consumer2, consumer3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$uploadFile$3(String str, File file, StorageUploadFileOptions storageUploadFileOptions, Consumer consumer, Consumer consumer2, Consumer consumer3) {
        return this.storage.uploadFile(str, file, storageUploadFileOptions, consumer, consumer2, consumer3);
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public RxProgressAwareSingleOperation<StorageUploadInputStreamResult> uploadInputStream(@NonNull String str, @NonNull InputStream inputStream) {
        return uploadInputStream(str, inputStream, StorageUploadInputStreamOptions.defaultInstance());
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public RxProgressAwareSingleOperation<StorageUploadInputStreamResult> uploadInputStream(@NonNull final String str, @NonNull final InputStream inputStream, @NonNull final StorageUploadInputStreamOptions storageUploadInputStreamOptions) {
        return new RxProgressAwareSingleOperation<>(new RxStorageTransferCallbackMapper() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.rx.RxStorageBinding.RxStorageTransferCallbackMapper
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2, Consumer consumer3) {
                return this.f$0.lambda$uploadInputStream$4(str, inputStream, storageUploadInputStreamOptions, consumer, consumer2, consumer3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$uploadInputStream$4(String str, InputStream inputStream, StorageUploadInputStreamOptions storageUploadInputStreamOptions, Consumer consumer, Consumer consumer2, Consumer consumer3) {
        return this.storage.uploadInputStream(str, inputStream, storageUploadInputStreamOptions, consumer, consumer2, consumer3);
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public Single<StorageRemoveResult> remove(@NonNull final String str) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$remove$5(str, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$remove$5(String str, Consumer consumer, Consumer consumer2) {
        this.storage.remove(str, consumer, consumer2);
        return new NoOpCancelable();
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public Single<StorageRemoveResult> remove(@NonNull final String str, @NonNull final StorageRemoveOptions storageRemoveOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$remove$6(str, storageRemoveOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$remove$6(String str, StorageRemoveOptions storageRemoveOptions, Consumer consumer, Consumer consumer2) {
        this.storage.remove(str, storageRemoveOptions, consumer, consumer2);
        return new NoOpCancelable();
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public Single<StorageListResult> list(@NonNull final String str) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$list$7(str, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$list$7(String str, Consumer consumer, Consumer consumer2) {
        this.storage.list(str, consumer, consumer2);
        return new NoOpCancelable();
    }

    @Override // com.amplifyframework.rx.RxStorageCategoryBehavior
    @NonNull
    public Single<StorageListResult> list(@NonNull final String str, @NonNull final StorageListOptions storageListOptions) {
        return toSingle(new RxAdapters.CancelableBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxStorageBinding$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.ResultEmitter
            public final Cancelable emitTo(Consumer consumer, Consumer consumer2) {
                return this.f$0.lambda$list$8(str, storageListOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$list$8(String str, StorageListOptions storageListOptions, Consumer consumer, Consumer consumer2) {
        this.storage.list(str, storageListOptions, consumer, consumer2);
        return new NoOpCancelable();
    }

    private <T> Single<T> toSingle(RxAdapters.CancelableBehaviors.ResultEmitter<T, StorageException> resultEmitter) {
        return RxAdapters.CancelableBehaviors.toSingle(resultEmitter);
    }

    public static final class RxProgressAwareSingleOperation<T> implements RxAdapters.RxSingleOperation<T> {
        private final Cancelable amplifyOperation;
        private final PublishSubject<StorageTransferProgress> progressSubject;
        private final ReplaySubject<T> resultSubject;

        RxProgressAwareSingleOperation(RxStorageTransferCallbackMapper<T> rxStorageTransferCallbackMapper) {
            final PublishSubject<StorageTransferProgress> publishSubjectCreate = PublishSubject.create();
            this.progressSubject = publishSubjectCreate;
            final ReplaySubject<T> replaySubjectCreate = ReplaySubject.create();
            this.resultSubject = replaySubjectCreate;
            publishSubjectCreate.getClass();
            Consumer<StorageTransferProgress> consumer = new Consumer() { // from class: com.amplifyframework.rx.RxStorageBinding$RxProgressAwareSingleOperation$$ExternalSyntheticLambda2
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    publishSubjectCreate.onNext((StorageTransferProgress) obj);
                }
            };
            replaySubjectCreate.getClass();
            Consumer<T> consumer2 = new Consumer() { // from class: com.amplifyframework.rx.RxStorageBinding$RxProgressAwareSingleOperation$$ExternalSyntheticLambda3
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    replaySubjectCreate.onNext(obj);
                }
            };
            replaySubjectCreate.getClass();
            this.amplifyOperation = rxStorageTransferCallbackMapper.emitTo(consumer, consumer2, new Consumer() { // from class: com.amplifyframework.rx.RxStorageBinding$RxProgressAwareSingleOperation$$ExternalSyntheticLambda4
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    replaySubjectCreate.onError((StorageException) obj);
                }
            });
        }

        @Override // com.amplifyframework.core.async.Cancelable
        public void cancel() {
            this.amplifyOperation.cancel();
            this.resultSubject.onComplete();
            this.progressSubject.onComplete();
        }

        @Override // com.amplifyframework.rx.RxAdapters.RxSingleOperation
        public Single<T> observeResult() {
            return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.rx.RxStorageBinding$RxProgressAwareSingleOperation$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                    this.f$0.lambda$observeResult$0(singleEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$observeResult$0(final SingleEmitter singleEmitter) throws Throwable {
            ReplaySubject<T> replaySubject = this.resultSubject;
            singleEmitter.getClass();
            singleEmitter.setDisposable(replaySubject.subscribe(new RetryHandler$$ExternalSyntheticLambda1(singleEmitter), new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.rx.RxStorageBinding$RxProgressAwareSingleOperation$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    singleEmitter.tryOnError((Throwable) obj);
                }
            }));
        }

        public Observable<StorageTransferProgress> observeProgress() {
            return this.progressSubject;
        }
    }
}
