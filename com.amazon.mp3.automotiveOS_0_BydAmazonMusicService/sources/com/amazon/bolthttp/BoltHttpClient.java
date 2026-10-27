package com.amazon.bolthttp;

import com.amazon.bolthttp.internal.Dispatcher;
import com.amazon.bolthttp.internal.ExecutionTracker;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.SyncResponseHandler;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class BoltHttpClient {
    private final Dispatcher mDispatcher;
    private final Logger mLogger;

    public BoltHttpClient(@Nonnull BoltConfig boltConfig) {
        Preconditions.checkNotNull(boltConfig, "config");
        Logger logger = new Logger(boltConfig);
        this.mLogger = logger;
        this.mDispatcher = new Dispatcher(boltConfig, logger);
    }

    public <T> Response<T> executeSync(@Nonnull Request<T> request) {
        SyncResponseHandler syncResponseHandler = new SyncResponseHandler(ExecutionTracker.start());
        this.mDispatcher.initiateRequest((Request) Preconditions.checkNotNull(request, "request"), syncResponseHandler);
        return syncResponseHandler.awaitResponse();
    }
}
