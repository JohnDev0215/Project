package com.amazon.video.sdk.stream;

import kotlin.Metadata;

/* JADX INFO: compiled from: TimedTextUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextOpacityMapping;", "", "opacity", "", "(Ljava/lang/String;IF)V", "getOpacity$AmazonAndroidVideoPlayer_release", "()F", "SEMITRANSPARENT", "NORMAL", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
enum TimedTextOpacityMapping {
    SEMITRANSPARENT(0.5f),
    NORMAL(1.0f);

    private final float opacity;

    TimedTextOpacityMapping(float f) {
        this.opacity = f;
    }

    /* JADX INFO: renamed from: getOpacity$AmazonAndroidVideoPlayer_release, reason: from getter */
    public final float getOpacity() {
        return this.opacity;
    }
}
