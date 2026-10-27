package com.amazon.video.sdk;

import amazon.android.di.AppInitializationTracker;
import com.amazon.avod.core.AppInitializationListener;
import com.amazon.avod.core.InitializationErrorCode;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: Futures.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001,B\u0015\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a¢\u0006\u0004\b*\u0010+J\u001e\u0010\b\u001a\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0017\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000f\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u000eH\u0016R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR(\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010$\u001a\u00020#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006-"}, d2 = {"Lcom/amazon/video/sdk/ListenableFutureWrappingObservableFuture;", "V", "Lcom/amazon/video/sdk/ObservableFuture;", "Lcom/amazon/video/sdk/ObservableFutureCallback;", "callback", "Ljava/util/concurrent/Executor;", "executor", "", "addListener", "Lcom/amazon/video/sdk/FailureErrorCode;", "errorCode", "onFailure$AmazonAndroidVideoPlayer_release", "(Lcom/amazon/video/sdk/FailureErrorCode;)V", "onFailure", "", "isDone", "get", "()Ljava/lang/Object;", "", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "mayInterruptIfRunning", "cancel", "isCancelled", "Lcom/google/common/util/concurrent/ListenableFuture;", "listenableFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "observableFutureCallback", "Lcom/amazon/video/sdk/ObservableFutureCallback;", "getObservableFutureCallback", "()Lcom/amazon/video/sdk/ObservableFutureCallback;", "setObservableFutureCallback", "(Lcom/amazon/video/sdk/ObservableFutureCallback;)V", "Ljava/util/concurrent/atomic/AtomicBoolean;", "callbackResponseSent", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getCallbackResponseSent", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setCallbackResponseSent", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "<init>", "(Lcom/google/common/util/concurrent/ListenableFuture;)V", "ObservableFutureFailureErrorCode", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class ListenableFutureWrappingObservableFuture<V> implements ObservableFuture<V> {
    private AtomicBoolean callbackResponseSent;
    private final ListenableFuture<V> listenableFuture;
    public ObservableFutureCallback<V> observableFutureCallback;

    public ListenableFutureWrappingObservableFuture(ListenableFuture<V> listenableFuture) {
        Intrinsics.checkNotNullParameter(listenableFuture, "listenableFuture");
        this.listenableFuture = listenableFuture;
        this.callbackResponseSent = new AtomicBoolean(false);
    }

    public final ObservableFutureCallback<V> getObservableFutureCallback() {
        ObservableFutureCallback<V> observableFutureCallback = this.observableFutureCallback;
        if (observableFutureCallback != null) {
            return observableFutureCallback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("observableFutureCallback");
        return null;
    }

    public final void setObservableFutureCallback(ObservableFutureCallback<V> observableFutureCallback) {
        Intrinsics.checkNotNullParameter(observableFutureCallback, "<set-?>");
        this.observableFutureCallback = observableFutureCallback;
    }

    public final AtomicBoolean getCallbackResponseSent() {
        return this.callbackResponseSent;
    }

    @Override // com.amazon.video.sdk.ObservableFuture
    public void addListener(ObservableFutureCallback<V> callback, Executor executor) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(executor, "executor");
        setObservableFutureCallback(callback);
        AppInitializationTracker.getInstance().addForegroundInitErrorCallback(new AppInitializationListener() { // from class: com.amazon.video.sdk.ListenableFutureWrappingObservableFuture$$ExternalSyntheticLambda0
            @Override // com.amazon.avod.core.AppInitializationListener
            public final void onInitializationError(InitializationErrorCode initializationErrorCode) {
                ListenableFutureWrappingObservableFuture.m2474addListener$lambda0(this.f$0, initializationErrorCode);
            }
        });
        Futures.addCallback(this.listenableFuture, new FutureCallback<V>(this) { // from class: com.amazon.video.sdk.ListenableFutureWrappingObservableFuture.addListener.2
            final /* synthetic */ ListenableFutureWrappingObservableFuture<V> this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(V v) {
                if (this.this$0.getCallbackResponseSent().getAndSet(true)) {
                    return;
                }
                if (v != null) {
                    this.this$0.getObservableFutureCallback().onSuccess(v);
                } else {
                    this.this$0.getObservableFutureCallback().onFailure(new Exception("Async computation returned a null result"), ObservableFutureFailureErrorCode.NULL_RESULT);
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable t) {
                if (this.this$0.getCallbackResponseSent().getAndSet(true)) {
                    return;
                }
                this.this$0.getObservableFutureCallback().onFailure(t, ObservableFutureFailureErrorCode.UNEXPECTED_EXCEPTION);
            }
        }, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addListener$lambda-0, reason: not valid java name */
    public static final void m2474addListener$lambda0(ListenableFutureWrappingObservableFuture this$0, InitializationErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this$0.onFailure$AmazonAndroidVideoPlayer_release(PlayerSdkInitializationErrorCode.INSTANCE.fromInitializationErrorCode$AmazonAndroidVideoPlayer_release(errorCode));
        this$0.cancel(true);
    }

    /* JADX INFO: compiled from: Futures.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/ListenableFutureWrappingObservableFuture$ObservableFutureFailureErrorCode;", "", "Lcom/amazon/video/sdk/FailureErrorCode;", "(Ljava/lang/String;I)V", "errorCode", "", "NULL_RESULT", "UNEXPECTED_EXCEPTION", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ObservableFutureFailureErrorCode implements FailureErrorCode {
        NULL_RESULT,
        UNEXPECTED_EXCEPTION;

        public String errorCode() {
            return name();
        }
    }

    public final void onFailure$AmazonAndroidVideoPlayer_release(FailureErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (this.callbackResponseSent.getAndSet(true)) {
            return;
        }
        ObservableFutureCallback<V> observableFutureCallback = getObservableFutureCallback();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "Failed with errorCode: %s", Arrays.copyOf(new Object[]{errorCode}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        observableFutureCallback.onFailure(new Exception(str), errorCode);
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.listenableFuture.isDone();
    }

    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException, InterruptedException {
        V v = this.listenableFuture.get();
        Intrinsics.checkNotNull(v);
        return v;
    }

    @Override // java.util.concurrent.Future
    public V get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        Intrinsics.checkNotNullParameter(unit, "unit");
        V v = this.listenableFuture.get(timeout, unit);
        Intrinsics.checkNotNull(v);
        return v;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.listenableFuture.cancel(mayInterruptIfRunning);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.listenableFuture.isCancelled();
    }
}
