package com.amplifyframework.api.rest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.core.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public interface RestBehavior {
    @Nullable
    RestOperation delete(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation delete(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation get(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation get(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation head(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation head(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation patch(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation patch(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation post(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation post(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation put(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);

    @Nullable
    RestOperation put(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2);
}
