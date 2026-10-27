package com.google.firebase.perf.config;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
abstract class ConfigurationFlag<T> {
    protected abstract T getDefault();

    @Nullable
    String getDeviceCacheFlag() {
        return null;
    }

    @Nullable
    String getMetadataFlag() {
        return null;
    }

    @Nullable
    String getRemoteConfigFlag() {
        return null;
    }

    ConfigurationFlag() {
    }
}
