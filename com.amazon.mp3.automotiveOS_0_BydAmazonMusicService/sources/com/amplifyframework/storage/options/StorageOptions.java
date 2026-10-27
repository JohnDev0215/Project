package com.amplifyframework.storage.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.storage.StorageAccessLevel;

/* JADX INFO: loaded from: classes5.dex */
abstract class StorageOptions {
    private final StorageAccessLevel accessLevel;
    private final String targetIdentityId;

    StorageOptions(StorageAccessLevel storageAccessLevel, String str) {
        this.accessLevel = storageAccessLevel;
        this.targetIdentityId = str;
    }

    @Nullable
    public final StorageAccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    @Nullable
    public final String getTargetIdentityId() {
        return this.targetIdentityId;
    }

    static abstract class Builder<B extends Builder, O extends StorageOptions> {
        private StorageAccessLevel accessLevel;
        private String targetIdentityId;

        @NonNull
        public abstract O build();

        Builder() {
        }

        @NonNull
        public final B accessLevel(@Nullable StorageAccessLevel storageAccessLevel) {
            this.accessLevel = storageAccessLevel;
            return this;
        }

        @NonNull
        public final B targetIdentityId(@Nullable String str) {
            this.targetIdentityId = str;
            return this;
        }

        @Nullable
        public final StorageAccessLevel getAccessLevel() {
            return this.accessLevel;
        }

        @Nullable
        public final String getTargetIdentityId() {
            return this.targetIdentityId;
        }
    }
}
