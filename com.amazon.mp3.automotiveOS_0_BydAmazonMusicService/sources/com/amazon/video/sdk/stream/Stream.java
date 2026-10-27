package com.amazon.video.sdk.stream;

import com.amazon.video.sdk.stream.Variant;
import kotlin.Metadata;

/* JADX INFO: compiled from: Stream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003R\u0014\u0010\u0007\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\n\u001a\u0004\u0018\u00018\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/stream/Stream;", "Lcom/amazon/video/sdk/stream/Variant;", "T", "", "", "getId", "()Ljava/lang/String;", "id", "getActiveVariant", "()Lcom/amazon/video/sdk/stream/Variant;", "activeVariant", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface Stream<T extends Variant> {
    T getActiveVariant();

    String getId();
}
