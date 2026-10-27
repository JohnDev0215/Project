package com.amazon.coral.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class RetryingCallable<CONTEXT, T> implements Callable<T> {
    private static final Logger LOG = Logger.getLogger(RetryingCallable.class.getName());
    private static final AtomicBoolean LOGGED_SHADOW_WARNING = new AtomicBoolean();
    private CallListener callListener;
    private final Callable<T> callable;
    private final CONTEXT ctx;
    private boolean recordSuccess;
    private final RetryContext retryCtx;
    private final RetryStrategy<CONTEXT> retryStrategy;
    private final CONTEXT shadowCtx;
    private final CallListener shadowListener;
    private final RetryContext shadowRetryCtx;
    private int shadowRetryStop;
    private final RetryStrategy<CONTEXT> shadowStrategy;

    public RetryingCallable(RetryStrategy<CONTEXT> retryStrategy, Callable<T> callable) {
        this(retryStrategy, null, callable);
    }

    public RetryingCallable(RetryStrategy<CONTEXT> retryStrategy, CONTEXT context, Callable<T> callable) {
        this(retryStrategy, retryStrategy instanceof CallListener ? (CallListener) retryStrategy : null, context, callable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RetryingCallable(RetryStrategy<CONTEXT> retryStrategy, CallListener callListener, CONTEXT context, Callable<T> callable) {
        this.recordSuccess = true;
        this.callable = callable;
        this.retryStrategy = retryStrategy;
        this.shadowStrategy = null;
        this.shadowListener = null;
        this.callListener = callListener;
        this.ctx = context;
        this.shadowCtx = null;
        this.retryCtx = context instanceof RetryContext ? (RetryContext) context : null;
        this.shadowRetryCtx = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x008a, code lost:
    
        return r2;
     */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public T call() throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.coral.retry.RetryingCallable.call():java.lang.Object");
    }
}
