package com.amplifyframework.storage.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.storage.options.StorageOptions;
import com.amplifyframework.util.Immutable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StorageUploadOptions extends StorageOptions {
    private final String contentType;
    private final Map<String, String> metadata;

    protected <B extends Builder<B, O>, O extends StorageUploadOptions> StorageUploadOptions(Builder<B, O> builder) {
        super(builder.getAccessLevel(), builder.getTargetIdentityId());
        this.contentType = builder.getContentType();
        this.metadata = builder.getMetadata();
    }

    @Nullable
    public String getContentType() {
        return this.contentType;
    }

    @NonNull
    public Map<String, String> getMetadata() {
        return Immutable.of(this.metadata);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageUploadOptions)) {
            return false;
        }
        StorageUploadOptions storageUploadOptions = (StorageUploadOptions) obj;
        return ObjectsCompat.equals(getAccessLevel(), storageUploadOptions.getAccessLevel()) && ObjectsCompat.equals(getTargetIdentityId(), storageUploadOptions.getTargetIdentityId()) && ObjectsCompat.equals(getContentType(), storageUploadOptions.getContentType()) && ObjectsCompat.equals(getMetadata(), storageUploadOptions.getMetadata());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getAccessLevel(), getTargetIdentityId(), getContentType(), getMetadata());
    }

    @NonNull
    public String toString() {
        return "StorageUploadOptions {accessLevel=" + getAccessLevel() + ", targetIdentityId=" + getTargetIdentityId() + ", contentType=" + getContentType() + ", metadata=" + getMetadata() + '}';
    }

    public static abstract class Builder<B extends Builder<B, O>, O extends StorageOptions> extends StorageOptions.Builder<B, O> {
        private String contentType;
        private Map<String, String> metadata = new HashMap();

        protected Builder() {
        }

        @NonNull
        public final B contentType(@Nullable String str) {
            this.contentType = str;
            return this;
        }

        @NonNull
        public final B metadata(@NonNull Map<String, String> map) {
            Objects.requireNonNull(map);
            this.metadata = new HashMap(map);
            return this;
        }

        @Nullable
        final String getContentType() {
            return this.contentType;
        }

        @NonNull
        final Map<String, String> getMetadata() {
            return Immutable.of(this.metadata);
        }
    }
}
