package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes5.dex */
public interface RxHubCategoryBehavior {
    @NonNull
    Observable<HubEvent<?>> on(@NonNull HubChannel hubChannel);

    @NonNull
    <T> Completable publish(@NonNull HubChannel hubChannel, @NonNull HubEvent<T> hubEvent);
}
