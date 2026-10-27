package com.amplifyframework.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public enum InitializationStatus {
    SUCCEEDED,
    FAILED;

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return name();
    }

    @NonNull
    public static InitializationStatus fromString(@Nullable String str) {
        for (InitializationStatus initializationStatus : values()) {
            if (initializationStatus.toString().equals(str)) {
                return initializationStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status = " + str);
    }
}
