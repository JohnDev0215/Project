package com.amplifyframework.storage.options;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageUploadOptions;

/* JADX INFO: loaded from: classes5.dex */
public class StorageUploadInputStreamOptions extends StorageUploadOptions {
    protected StorageUploadInputStreamOptions(Builder<?> builder) {
        super(builder);
    }

    @NonNull
    @SuppressLint({"SyntheticAccessor"})
    public static Builder<?> builder() {
        return new Builder<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static Builder<?> from(@NonNull StorageUploadInputStreamOptions storageUploadInputStreamOptions) {
        return (Builder) ((Builder) ((Builder) ((Builder) builder().accessLevel(storageUploadInputStreamOptions.getAccessLevel())).targetIdentityId(storageUploadInputStreamOptions.getTargetIdentityId())).contentType(storageUploadInputStreamOptions.getContentType())).metadata(storageUploadInputStreamOptions.getMetadata());
    }

    @NonNull
    public static StorageUploadInputStreamOptions defaultInstance() {
        return builder().build();
    }

    @Override // com.amplifyframework.storage.options.StorageUploadOptions
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageUploadInputStreamOptions)) {
            return false;
        }
        StorageUploadInputStreamOptions storageUploadInputStreamOptions = (StorageUploadInputStreamOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageUploadInputStreamOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageUploadInputStreamOptions.getTargetIdentityId()) && ObjectsCompat.equals(getContentType(), storageUploadInputStreamOptions.getContentType()) && ObjectsCompat.equals(getMetadata(), storageUploadInputStreamOptions.getMetadata());
    }

    @Override // com.amplifyframework.storage.options.StorageUploadOptions
    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId(), getContentType(), getMetadata());
    }

    @Override // com.amplifyframework.storage.options.StorageUploadOptions
    @NonNull
    public String toString() {
        return "StorageUploadInputStreamOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + ", contentType=" + getContentType() + ", metadata=" + getMetadata() + '}';
    }

    public static class Builder<B extends Builder<B>> extends StorageUploadOptions.Builder<B, StorageUploadInputStreamOptions> {
        @Override // com.amplifyframework.storage.options.StorageOptions.Builder
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public StorageUploadInputStreamOptions build() {
            return new StorageUploadInputStreamOptions(this);
        }
    }
}
