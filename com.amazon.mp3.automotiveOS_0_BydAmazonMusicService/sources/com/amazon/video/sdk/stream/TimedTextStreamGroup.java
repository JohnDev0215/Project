package com.amazon.video.sdk.stream;

import kotlin.Metadata;

/* JADX INFO: compiled from: StreamGroup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "Lcom/amazon/video/sdk/stream/StreamGroup;", "Lcom/amazon/video/sdk/stream/TimedTextStream;", "isShowingCaptions", "", "showCaptions", "", "value", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TimedTextStreamGroup extends StreamGroup<TimedTextStream> {
    boolean isShowingCaptions();

    void showCaptions(boolean value);
}
