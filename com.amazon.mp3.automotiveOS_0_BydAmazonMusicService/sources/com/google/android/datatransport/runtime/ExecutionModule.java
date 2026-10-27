package com.google.android.datatransport.runtime;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes6.dex */
abstract class ExecutionModule {
    ExecutionModule() {
    }

    static Executor executor() {
        return new SafeLoggingExecutor(Executors.newSingleThreadExecutor());
    }
}
