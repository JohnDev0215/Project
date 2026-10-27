package com.amazon.avod.core;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InitializationException extends Exception {
    private final InitializationErrorCode mErrorCode;

    public InitializationException(@Nonnull InitializationErrorCode initializationErrorCode, @Nonnull String str) {
        super(str);
        this.mErrorCode = (InitializationErrorCode) Preconditions.checkNotNull(initializationErrorCode, "errorCode");
    }

    public InitializationException(@Nonnull InitializationErrorCode initializationErrorCode, @Nonnull String str, @Nonnull Exception exc) {
        super(str, exc);
        this.mErrorCode = (InitializationErrorCode) Preconditions.checkNotNull(initializationErrorCode, "errorCode");
    }

    @Nonnull
    public InitializationErrorCode getErrorCode() {
        return this.mErrorCode;
    }
}
