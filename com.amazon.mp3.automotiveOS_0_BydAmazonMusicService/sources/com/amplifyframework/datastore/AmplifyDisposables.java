package com.amplifyframework.datastore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class AmplifyDisposables {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");

    private AmplifyDisposables() {
    }

    @NonNull
    public static Disposable fromCancelable(@Nullable final Cancelable cancelable) {
        if (cancelable == null) {
            return Disposable.empty();
        }
        return new Disposable() { // from class: com.amplifyframework.datastore.AmplifyDisposables.1
            private final AtomicReference<Boolean> isCanceled = new AtomicReference<>(Boolean.FALSE);

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                synchronized (this.isCanceled) {
                    if (!this.isCanceled.get().booleanValue()) {
                        cancelable.cancel();
                        this.isCanceled.set(Boolean.TRUE);
                    }
                }
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                boolean zBooleanValue;
                synchronized (this.isCanceled) {
                    zBooleanValue = this.isCanceled.get().booleanValue();
                }
                return zBooleanValue;
            }
        };
    }

    @NonNull
    public static <T extends Model, E extends AmplifyException> Consumer<E> onErrorConsumerWrapperFor(@NonNull final ObservableEmitter<GraphQLResponse<ModelWithMetadata<T>>> observableEmitter) {
        return new Consumer() { // from class: com.amplifyframework.datastore.AmplifyDisposables$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AmplifyDisposables.lambda$onErrorConsumerWrapperFor$0(observableEmitter, (AmplifyException) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onErrorConsumerWrapperFor$0(ObservableEmitter observableEmitter, AmplifyException amplifyException) {
        if (observableEmitter.isDisposed()) {
            LOG.warn("Attempted to invoke an emitter that is already disposed", amplifyException);
        } else {
            observableEmitter.onError(amplifyException);
        }
    }
}
