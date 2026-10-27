package com.amplifyframework.storage.options;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageOptions;

/* JADX INFO: loaded from: classes5.dex */
public class StorageRemoveOptions extends StorageOptions {
    protected StorageRemoveOptions(Builder<?> builder) {
        super(builder.getAccessLevel(), builder.getTargetIdentityId());
    }

    @NonNull
    public static Builder<?> builder() {
        return new Builder<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static Builder<?> from(@NonNull StorageRemoveOptions storageRemoveOptions) {
        return (Builder) ((Builder) builder().accessLevel(storageRemoveOptions.getAccessLevel())).targetIdentityId(storageRemoveOptions.getTargetIdentityId());
    }

    @NonNull
    public static StorageRemoveOptions defaultInstance() {
        return builder().build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageRemoveOptions)) {
            return false;
        }
        StorageRemoveOptions storageRemoveOptions = (StorageRemoveOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageRemoveOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageRemoveOptions.getTargetIdentityId());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId());
    }

    @NonNull
    public String toString() {
        return "StorageRemoveOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + '}';
    }

    public static class Builder<B extends Builder<B>> extends StorageOptions.Builder<B, StorageRemoveOptions> {
        @Override // com.amplifyframework.storage.options.StorageOptions.Builder
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public StorageRemoveOptions build() {
            return new StorageRemoveOptions(this);
        }
    }
}
