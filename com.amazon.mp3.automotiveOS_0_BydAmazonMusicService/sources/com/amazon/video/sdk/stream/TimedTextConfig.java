package com.amazon.video.sdk.stream;

import kotlin.Metadata;

/* JADX INFO: compiled from: TimedTextConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0011\u001a\u00020\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0004R\u0014\u0010\u0015\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0004¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextConfig;", "", "Lcom/amazon/video/sdk/stream/TimedTextColor;", "getTextColor", "()Lcom/amazon/video/sdk/stream/TimedTextColor;", "textColor", "Lcom/amazon/video/sdk/stream/TimedTextFontSize;", "getFontSize", "()Lcom/amazon/video/sdk/stream/TimedTextFontSize;", "fontSize", "Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;", "getCharacterEdgeType", "()Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;", "characterEdgeType", "Lcom/amazon/video/sdk/stream/TimedTextFontStyle;", "getFontStyle", "()Lcom/amazon/video/sdk/stream/TimedTextFontStyle;", "fontStyle", "getTextBackgroundColor", "textBackgroundColor", "getTextWindowColor", "textWindowColor", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface TimedTextConfig {
    TimedTextCharacterEdgeType getCharacterEdgeType();

    TimedTextFontSize getFontSize();

    TimedTextFontStyle getFontStyle();

    TimedTextColor getTextBackgroundColor();

    TimedTextColor getTextColor();

    TimedTextColor getTextWindowColor();
}
