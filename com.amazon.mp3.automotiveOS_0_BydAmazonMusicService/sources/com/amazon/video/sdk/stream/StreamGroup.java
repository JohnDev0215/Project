package com.amazon.video.sdk.stream;

import com.amazon.video.sdk.stream.Stream;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: StreamGroup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\b\u0007\bf\u0018\u0000*\u0010\b\u0000\u0010\u0003*\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00012\u00020\u0004R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u000b\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamGroup;", "Lcom/amazon/video/sdk/stream/Stream;", "Lcom/amazon/video/sdk/stream/Variant;", "T", "", "", "getStreams", "()Ljava/util/List;", "streams", "getActiveStream", "()Lcom/amazon/video/sdk/stream/Stream;", "activeStream", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface StreamGroup<T extends Stream<? extends Variant>> {
    T getActiveStream();

    List<T> getStreams();
}
