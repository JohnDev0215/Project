package com.amazon.video.sdk;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import kotlin.Metadata;

/* JADX INFO: compiled from: Futures.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/ObservableFuture;", "V", "Ljava/util/concurrent/Future;", "addListener", "", "callback", "Lcom/amazon/video/sdk/ObservableFutureCallback;", "executor", "Ljava/util/concurrent/Executor;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ObservableFuture<V> extends Future<V> {
    void addListener(ObservableFutureCallback<V> callback, Executor executor);
}
