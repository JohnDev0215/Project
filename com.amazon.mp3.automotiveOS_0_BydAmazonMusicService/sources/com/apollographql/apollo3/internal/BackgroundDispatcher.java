package com.apollographql.apollo3.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* JADX INFO: compiled from: dispatchers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo3/internal/BackgroundDispatcher;", "", "()V", "_dispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "coroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getCoroutineDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "disposed", "", "dispose", "", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BackgroundDispatcher {
    private final ExecutorCoroutineDispatcher _dispatcher;
    private boolean disposed;

    public BackgroundDispatcher() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this._dispatcher = ExecutorsKt.from(executorServiceNewSingleThreadExecutor);
    }

    public final CoroutineDispatcher getCoroutineDispatcher() {
        return this._dispatcher;
    }

    public final void dispose() {
        if (this.disposed) {
            return;
        }
        this._dispatcher.close();
    }
}
