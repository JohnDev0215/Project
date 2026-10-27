package org.chromium.base;

import android.os.Handler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Promise<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FULFILLED = 1;
    private static final int REJECTED = 2;
    private static final int UNFULFILLED = 0;
    private Exception mRejectReason;
    private T mResult;
    private boolean mThrowingRejectionHandler;
    private int mState = 0;
    private final List<Callback<T>> mFulfillCallbacks = new LinkedList();
    private final List<Callback<Exception>> mRejectCallbacks = new LinkedList();
    private final Thread mThread = Thread.currentThread();
    private final Handler mHandler = new Handler();

    public interface AsyncFunction<A, R> {
        Promise<R> apply(A a);
    }

    public interface Function<A, R> {
        R apply(A a);
    }

    private void checkThread() {
    }

    public static class UnhandledRejectionException extends RuntimeException {
        public UnhandledRejectionException(String str, Throwable th) {
            super(str, th);
        }
    }

    public void then(Callback<T> callback) {
        checkThread();
        if (this.mThrowingRejectionHandler) {
            thenInner(callback);
        } else {
            then(callback, new Callback<Exception>() { // from class: org.chromium.base.Promise.1
                @Override // org.chromium.base.Callback
                public void onResult(Exception exc) {
                    throw new UnhandledRejectionException("Promise was rejected without a rejection handler.", exc);
                }
            });
            this.mThrowingRejectionHandler = true;
        }
    }

    public void then(Callback<T> callback, Callback<Exception> callback2) {
        checkThread();
        thenInner(callback);
        exceptInner(callback2);
    }

    public void except(Callback<Exception> callback) {
        checkThread();
        exceptInner(callback);
    }

    public Callback<T> fulfillmentCallback() {
        return new Callback<T>() { // from class: org.chromium.base.Promise.2
            @Override // org.chromium.base.Callback
            public void onResult(T t) {
                Promise.this.fulfill(t);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void thenInner(Callback<T> callback) {
        int i = this.mState;
        if (i == 1) {
            postCallbackToLooper(callback, this.mResult);
        } else if (i == 0) {
            this.mFulfillCallbacks.add(callback);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void exceptInner(Callback<Exception> callback) {
        int i = this.mState;
        if (i == 2) {
            postCallbackToLooper(callback, this.mRejectReason);
        } else if (i == 0) {
            this.mRejectCallbacks.add(callback);
        }
    }

    public <R> Promise<R> then(final Function<T, R> function) {
        checkThread();
        final Promise<R> promise = new Promise<>();
        thenInner(new Callback<T>() { // from class: org.chromium.base.Promise.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.chromium.base.Callback
            public void onResult(T t) {
                try {
                    promise.fulfill(function.apply(t));
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        exceptInner(rejectPromiseCallback(promise));
        return promise;
    }

    public <R> Promise<R> then(final AsyncFunction<T, R> asyncFunction) {
        checkThread();
        final Promise<R> promise = new Promise<>();
        thenInner(new Callback<T>() { // from class: org.chromium.base.Promise.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.chromium.base.Callback
            public void onResult(T t) {
                try {
                    asyncFunction.apply(t).then(new Callback<R>() { // from class: org.chromium.base.Promise.4.1
                        @Override // org.chromium.base.Callback
                        public void onResult(R r) {
                            promise.fulfill(r);
                        }
                    }, Promise.rejectPromiseCallback(promise));
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        exceptInner(rejectPromiseCallback(promise));
        return promise;
    }

    public void fulfill(T t) {
        checkThread();
        this.mState = 1;
        this.mResult = t;
        Iterator<Callback<T>> it = this.mFulfillCallbacks.iterator();
        while (it.hasNext()) {
            postCallbackToLooper(it.next(), t);
        }
        this.mFulfillCallbacks.clear();
    }

    public void reject(Exception exc) {
        checkThread();
        this.mState = 2;
        this.mRejectReason = exc;
        Iterator<Callback<Exception>> it = this.mRejectCallbacks.iterator();
        while (it.hasNext()) {
            postCallbackToLooper((Callback) it.next(), exc);
        }
        this.mRejectCallbacks.clear();
    }

    public void reject() {
        reject(null);
    }

    public boolean isFulfilled() {
        checkThread();
        return this.mState == 1;
    }

    public boolean isRejected() {
        checkThread();
        return this.mState == 2;
    }

    public static <T> Promise<T> fulfilled(T t) {
        Promise<T> promise = new Promise<>();
        promise.fulfill(t);
        return promise;
    }

    private <S> void postCallbackToLooper(final Callback<S> callback, final S s) {
        this.mHandler.post(new Runnable() { // from class: org.chromium.base.Promise.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                callback.onResult(s);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> Callback<Exception> rejectPromiseCallback(Promise<T> promise) {
        return new Callback<Exception>() { // from class: org.chromium.base.Promise.6
            @Override // org.chromium.base.Callback
            public void onResult(Exception exc) {
                Promise.this.reject(exc);
            }
        };
    }
}
