package com.amazon.avod.media.framework.retry;

import com.amazon.avod.util.DLog;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public class RetriableCall<T> {
    private final Callable<T> mCallable;
    private final int mRetryDelayBackoff;
    private final RetryListener<T> mRetryNotifier;
    private final RetryPolicy<T> mRetryPolicy;
    private final int mTryCount;
    private final Object mSyncObject = new Object();
    private boolean mIsCancelled = false;

    public enum RetryCode {
        CONTINUE,
        STOP
    }

    public RetriableCall(Callable<T> callable, RetryPolicy<T> retryPolicy, int i, RetryListener<T> retryListener, int i2) {
        this.mRetryPolicy = retryPolicy;
        this.mCallable = callable;
        this.mTryCount = i;
        this.mRetryNotifier = retryListener;
        this.mRetryDelayBackoff = i2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public T call() throws Exception {
        T tPerformCall;
        int i = 0;
        while (true) {
            Exception exc = null;
            if (this.mIsCancelled) {
                return null;
            }
            i++;
            try {
                tPerformCall = performCall();
            } catch (Exception e) {
                tPerformCall = null;
                exc = e;
            }
            if (i >= this.mTryCount) {
                if (exc != null) {
                    DLog.warnf("Caught exception during retriable operation (%s), (%d/%d) tries completed, aborting", exc, Integer.valueOf(i), Integer.valueOf(this.mTryCount));
                    throw exc;
                }
                DLog.logf("Returning last result (%s) from retriable operation, (%d/%d) tries completed", tPerformCall, Integer.valueOf(i), Integer.valueOf(this.mTryCount));
                return tPerformCall;
            }
            if (exc != null) {
                if (this.mRetryPolicy.shouldRetryOnException(exc)) {
                    DLog.warnf("Caught exception during retriable operation (%s), try (%d/%d), retrying", exc, Integer.valueOf(i), Integer.valueOf(this.mTryCount));
                } else {
                    DLog.warnf("Unhandled exception type caught during retriable operation (%s), aborting", exc);
                    throw exc;
                }
            } else {
                if (!this.mRetryPolicy.shouldRetryWithResult(tPerformCall)) {
                    return tPerformCall;
                }
                DLog.warnf("Policy dictates retry for retriable operation based on result: %s, try (%d/%d), retrying", tPerformCall, Integer.valueOf(i), Integer.valueOf(this.mTryCount));
            }
            if (RetryCode.STOP.equals(onRetry(tPerformCall, exc, i))) {
                DLog.warnf("Received stop retry code, aborting retries");
                throw new RetryStopException("Received stop retry code, aborting retries");
            }
            delayForTry(i);
        }
    }

    protected void delayForTry(int i) throws InterruptedException {
        int i2 = this.mRetryDelayBackoff * i;
        synchronized (this.mSyncObject) {
            this.mSyncObject.wait(i2);
        }
    }

    void delayForTry(long j) throws InterruptedException {
        synchronized (this.mSyncObject) {
            this.mSyncObject.wait(j);
        }
    }

    protected RetryCode onRetry(T t, Exception exc, int i) {
        RetryListener<T> retryListener = this.mRetryNotifier;
        if (retryListener != null) {
            return retryListener.onRetry(t, exc, i);
        }
        return RetryCode.CONTINUE;
    }

    protected T performCall() throws Exception {
        return this.mCallable.call();
    }
}
