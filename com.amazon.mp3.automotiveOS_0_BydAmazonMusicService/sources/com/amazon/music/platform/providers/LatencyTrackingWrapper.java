package com.amazon.music.platform.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: LatencyTrackingWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/platform/providers/LatencyTrackingWrapper;", "", "processing", "", "request", "response", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface LatencyTrackingWrapper {
    void processing();

    void request();

    void response();
}
