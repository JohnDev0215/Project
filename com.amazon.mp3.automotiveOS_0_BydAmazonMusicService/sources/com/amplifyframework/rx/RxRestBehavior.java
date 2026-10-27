package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.api.rest.RestResponse;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes5.dex */
public interface RxRestBehavior {
    @NonNull
    Single<RestResponse> delete(@NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> delete(@NonNull String str, @NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> get(@NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> get(@NonNull String str, @NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> head(@NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> head(@NonNull String str, @NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> patch(@NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> patch(@NonNull String str, @NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> post(@NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> post(@NonNull String str, @NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> put(@NonNull RestOptions restOptions);

    @NonNull
    Single<RestResponse> put(@NonNull String str, @NonNull RestOptions restOptions);
}
