package com.amplifyframework.storage.options;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageUploadOptions;

/* JADX INFO: loaded from: classes5.dex */
public class StorageUploadFileOptions extends StorageUploadOptions {
    protected StorageUploadFileOptions(Builder<?> builder) {
        super(builder);
    }

    @NonNull
    @SuppressLint({"SyntheticAccessor"})
    public static Builder<?> builder() {
        return new Builder<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static Builder<?> from(@NonNull StorageUploadFileOptions storageUploadFileOptions) {
        return (Builder) ((Builder) ((Builder) ((Builder) builder().accessLevel(storageUploadFileOptions.getAccessLevel())).targetIdentityId(storageUploadFileOptions.getTargetIdentityId())).contentType(storageUploadFileOptions.getContentType())).metadata(storageUploadFileOptions.getMetadata());
    }

    @NonNull
    public static StorageUploadFileOptions defaultInstance() {
        return builder().build();
    }

    @Override // com.amplifyframework.storage.options.StorageUploadOptions
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageUploadFileOptions)) {
            return false;
        }
        StorageUploadFileOptions storageUploadFileOptions = (StorageUploadFileOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageUploadFileOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageUploadFileOptions.getTargetIdentityId()) && ObjectsCompat.equals(getContentType(), storageUploadFileOptions.getContentType()) && ObjectsCompat.equals(getMetadata(), storageUploadFileOptions.getMetadata());
    }

    @Override // com.amplifyframework.storage.options.StorageUploadOptions
    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId(), getContentType(), getMetadata());
    }

    @Override // com.amplifyframework.storage.options.StorageUploadOptions
    @NonNull
    public String toString() {
        return "StorageUploadFileOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + ", contentType=" + getContentType() + ", metadata=" + getMetadata() + '}';
    }

    public static class Builder<B extends Builder<B>> extends StorageUploadOptions.Builder<B, StorageUploadFileOptions> {
        @Override // com.amplifyframework.storage.options.StorageOptions.Builder
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public StorageUploadFileOptions build() {
            return new StorageUploadFileOptions(this);
        }
    }
}
