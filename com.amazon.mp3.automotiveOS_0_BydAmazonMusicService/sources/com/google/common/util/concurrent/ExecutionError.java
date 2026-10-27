package com.google.common.util.concurrent;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public class ExecutionError extends Error {
    protected ExecutionError() {
    }

    public ExecutionError(@CheckForNull Error error) {
        super(error);
    }
}
