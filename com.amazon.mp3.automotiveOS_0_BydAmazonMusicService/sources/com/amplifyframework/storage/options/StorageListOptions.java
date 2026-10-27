package com.amplifyframework.storage.options;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageOptions;

/* JADX INFO: loaded from: classes5.dex */
public class StorageListOptions extends StorageOptions {
    protected StorageListOptions(Builder<?> builder) {
        super(builder.getAccessLevel(), builder.getTargetIdentityId());
    }

    @NonNull
    public static Builder<?> builder() {
        return new Builder<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static Builder<?> from(@NonNull StorageListOptions storageListOptions) {
        return (Builder) ((Builder) builder().accessLevel(storageListOptions.getAccessLevel())).targetIdentityId(storageListOptions.getTargetIdentityId());
    }

    @NonNull
    public static StorageListOptions defaultInstance() {
        return builder().build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageListOptions)) {
            return false;
        }
        StorageListOptions storageListOptions = (StorageListOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageListOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageListOptions.getTargetIdentityId());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId());
    }

    @NonNull
    public String toString() {
        return "StorageListOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + '}';
    }

    public static class Builder<B extends Builder<B>> extends StorageOptions.Builder<B, StorageListOptions> {
        @Override // com.amplifyframework.storage.options.StorageOptions.Builder
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public StorageListOptions build() {
            return new StorageListOptions(this);
        }
    }
}
