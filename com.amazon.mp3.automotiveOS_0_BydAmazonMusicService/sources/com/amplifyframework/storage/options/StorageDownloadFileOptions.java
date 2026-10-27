package com.amplifyframework.storage.options;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageOptions;

/* JADX INFO: loaded from: classes5.dex */
public class StorageDownloadFileOptions extends StorageOptions {
    protected StorageDownloadFileOptions(Builder<?> builder) {
        super(builder.getAccessLevel(), builder.getTargetIdentityId());
    }

    @NonNull
    public static Builder<?> builder() {
        return new Builder<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static Builder<?> from(@NonNull StorageDownloadFileOptions storageDownloadFileOptions) {
        return (Builder) ((Builder) builder().accessLevel(storageDownloadFileOptions.getAccessLevel())).targetIdentityId(storageDownloadFileOptions.getTargetIdentityId());
    }

    @NonNull
    public static StorageDownloadFileOptions defaultInstance() {
        return builder().build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageDownloadFileOptions)) {
            return false;
        }
        StorageDownloadFileOptions storageDownloadFileOptions = (StorageDownloadFileOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageDownloadFileOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageDownloadFileOptions.getTargetIdentityId());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId());
    }

    @NonNull
    public String toString() {
        return "StorageDownloadFileOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + '}';
    }

    public static class Builder<B extends Builder<B>> extends StorageOptions.Builder<B, StorageDownloadFileOptions> {
        @Override // com.amplifyframework.storage.options.StorageOptions.Builder
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public StorageDownloadFileOptions build() {
            return new StorageDownloadFileOptions(this);
        }
    }
}
