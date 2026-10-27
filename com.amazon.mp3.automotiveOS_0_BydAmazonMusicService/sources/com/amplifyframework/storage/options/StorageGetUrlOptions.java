package com.amplifyframework.storage.options;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageOptions;

/* JADX INFO: loaded from: classes5.dex */
public class StorageGetUrlOptions extends StorageOptions {
    private final int expires;

    protected StorageGetUrlOptions(Builder<?> builder) {
        super(builder.getAccessLevel(), builder.getTargetIdentityId());
        this.expires = builder.getExpires();
    }

    public int getExpires() {
        return this.expires;
    }

    @NonNull
    @SuppressLint({"SyntheticAccessor"})
    public static Builder<?> builder() {
        return new Builder<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static Builder<?> from(@NonNull StorageGetUrlOptions storageGetUrlOptions) {
        return ((Builder) ((Builder) builder().accessLevel(storageGetUrlOptions.getAccessLevel())).targetIdentityId(storageGetUrlOptions.getTargetIdentityId())).expires(storageGetUrlOptions.getExpires());
    }

    @NonNull
    public static StorageGetUrlOptions defaultInstance() {
        return builder().build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageGetUrlOptions)) {
            return false;
        }
        StorageGetUrlOptions storageGetUrlOptions = (StorageGetUrlOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageGetUrlOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageGetUrlOptions.getTargetIdentityId()) && ObjectsCompat.equals(Integer.valueOf(getExpires()), Integer.valueOf(storageGetUrlOptions.getExpires()));
    }

    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId(), Integer.valueOf(getExpires()));
    }

    @NonNull
    public String toString() {
        return "StorageGetUrlOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + ", expires=" + getExpires() + '}';
    }

    public static class Builder<B extends Builder<B>> extends StorageOptions.Builder<B, StorageGetUrlOptions> {
        private int expires;

        @NonNull
        public final B expires(int i) {
            this.expires = i;
            return this;
        }

        final int getExpires() {
            return this.expires;
        }

        @Override // com.amplifyframework.storage.options.StorageOptions.Builder
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public StorageGetUrlOptions build() {
            return new StorageGetUrlOptions(this);
        }
    }
}
