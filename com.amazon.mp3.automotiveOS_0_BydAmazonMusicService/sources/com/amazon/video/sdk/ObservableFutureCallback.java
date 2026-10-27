package com.amazon.video.sdk;

import kotlin.Metadata;

/* JADX INFO: compiled from: Futures.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/ObservableFutureCallback;", "V", "", "onFailure", "", "t", "", "errorCode", "Lcom/amazon/video/sdk/FailureErrorCode;", "onSuccess", "v", "(Ljava/lang/Object;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ObservableFutureCallback<V> {
    void onFailure(Throwable t, FailureErrorCode errorCode);

    void onSuccess(V v);
}
