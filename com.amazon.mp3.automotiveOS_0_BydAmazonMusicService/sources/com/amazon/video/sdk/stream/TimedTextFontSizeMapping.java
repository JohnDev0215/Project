package com.amazon.video.sdk.stream;

import kotlin.Metadata;

/* JADX INFO: compiled from: TimedTextUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextFontSizeMapping;", "", "size", "", "(Ljava/lang/String;IF)V", "getSize$AmazonAndroidVideoPlayer_release", "()F", "SMALL", "MEDIUM", "LARGE", "EXTRALARGE", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
enum TimedTextFontSizeMapping {
    SMALL(0.5f),
    MEDIUM(1.0f),
    LARGE(1.5f),
    EXTRALARGE(2.0f);

    private final float size;

    TimedTextFontSizeMapping(float f) {
        this.size = f;
    }

    /* JADX INFO: renamed from: getSize$AmazonAndroidVideoPlayer_release, reason: from getter */
    public final float getSize() {
        return this.size;
    }
}
