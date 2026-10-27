package com.amazon.bolthttp.policy;

import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RetryLimitException extends IOException {
    public RetryLimitException(@Nonnull String str, @Nonnull Throwable th) {
        super(str, th);
        Preconditions.checkNotNull(str, "message");
        Preconditions.checkNotNull(th, "cause");
    }

    @Override // java.lang.Throwable
    @Nonnull
    public String getMessage() {
        return String.format("%s (%s)", super.getMessage(), getCause().getMessage());
    }

    @Override // java.lang.Throwable
    @Nonnull
    public Throwable getCause() {
        return super.getCause();
    }
}
