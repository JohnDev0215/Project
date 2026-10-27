package com.amplifyframework.storage.result;

import androidx.annotation.NonNull;
import java.net.URL;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageGetUrlResult {
    private final URL url;

    private StorageGetUrlResult(@NonNull URL url) {
        this.url = url;
    }

    @NonNull
    public static StorageGetUrlResult fromUrl(@NonNull URL url) {
        Objects.requireNonNull(url);
        return new StorageGetUrlResult(url);
    }

    @NonNull
    public URL getUrl() {
        return this.url;
    }
}
