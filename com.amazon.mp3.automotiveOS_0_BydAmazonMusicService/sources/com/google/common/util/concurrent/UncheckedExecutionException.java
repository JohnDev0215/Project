package com.google.common.util.concurrent;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public class UncheckedExecutionException extends RuntimeException {
    protected UncheckedExecutionException() {
    }

    public UncheckedExecutionException(@CheckForNull Throwable th) {
        super(th);
    }
}
