package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.Model;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes5.dex */
interface MutationOutbox {

    public enum OutboxEvent {
        CONTENT_AVAILABLE
    }

    @NonNull
    <T extends Model> Completable enqueue(@NonNull PendingMutation<T> pendingMutation);

    @NonNull
    Observable<OutboxEvent> events();

    boolean hasPendingMutation(@NonNull String str);

    @NonNull
    Completable load();

    Completable markInFlight(@NonNull TimeBasedUuid timeBasedUuid);

    @Nullable
    PendingMutation<? extends Model> peek();

    @NonNull
    Completable remove(@NonNull TimeBasedUuid timeBasedUuid);
}
