package com.amazon.video.sdk.stream;

import com.amazon.video.sdk.stream.StreamEvent;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: StreamFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/stream/EventListener;", "T", "Lcom/amazon/video/sdk/stream/StreamEvent;", "", DebugKt.DEBUG_PROPERTY_VALUE_ON, "", "t", "(Lcom/amazon/video/sdk/stream/StreamEvent;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface EventListener<T extends StreamEvent> {
    void on(T t);
}
