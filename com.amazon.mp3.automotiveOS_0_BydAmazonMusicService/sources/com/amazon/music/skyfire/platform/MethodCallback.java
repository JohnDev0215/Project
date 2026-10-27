package com.amazon.music.skyfire.platform;

import kotlin.Metadata;

/* JADX INFO: compiled from: MethodCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/skyfire/platform/MethodCallback;", "T", "", "executeMethod", "", "method", "ownerId", "", "onMethodExecutedListener", "Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;", "(Ljava/lang/Object;Ljava/lang/String;Lcom/amazon/music/skyfire/platform/OnMethodExecutedListener;)V", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface MethodCallback<T> {
    void executeMethod(T method, String ownerId, OnMethodExecutedListener<T> onMethodExecutedListener);
}
