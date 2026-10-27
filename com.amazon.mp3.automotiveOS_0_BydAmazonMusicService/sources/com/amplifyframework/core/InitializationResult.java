package com.amplifyframework.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class InitializationResult {
    private final Throwable failure;
    private final InitializationStatus initializationStatus;

    private InitializationResult(@NonNull InitializationStatus initializationStatus, @Nullable Throwable th) {
        this.initializationStatus = initializationStatus;
        this.failure = th;
    }

    @NonNull
    public InitializationStatus getInitializationStatus() {
        return this.initializationStatus;
    }

    @Nullable
    public Throwable getFailure() {
        return this.failure;
    }

    @NonNull
    public static InitializationResult failure(@NonNull Throwable th) {
        Objects.requireNonNull(th);
        return new InitializationResult(InitializationStatus.FAILED, th);
    }

    @NonNull
    public static InitializationResult success() {
        return new InitializationResult(InitializationStatus.SUCCEEDED, null);
    }

    public boolean isSuccess() {
        return InitializationStatus.SUCCEEDED.equals(this.initializationStatus);
    }

    public boolean isFailure() {
        return InitializationStatus.FAILED.equals(this.initializationStatus);
    }
}
