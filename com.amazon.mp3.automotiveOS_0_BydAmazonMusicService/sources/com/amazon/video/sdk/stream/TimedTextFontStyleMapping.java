package com.amazon.video.sdk.stream;

import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePreset;
import kotlin.Metadata;

/* JADX INFO: compiled from: TimedTextUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextFontStyleMapping;", "", "font", "Lcom/amazon/video/sdk/avod/playbackclient/subtitle/SubtitlePreset$Font;", "(Ljava/lang/String;ILcom/amazon/video/sdk/avod/playbackclient/subtitle/SubtitlePreset$Font;)V", "getFont$AmazonAndroidVideoPlayer_release", "()Lcom/amazon/video/sdk/avod/playbackclient/subtitle/SubtitlePreset$Font;", "DEFAULT", "MONOSPACE_SERIF", "PROPORTIONAL_SERIF", "MONOSPACE_SANS_SERIF", "PROPORTIONAL_SANS_SERIF", "CASUAL", "CURSIVE", "SMALL_CAPITALS", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
enum TimedTextFontStyleMapping {
    DEFAULT(SubtitlePreset.Font.UNDEFINED),
    MONOSPACE_SERIF(SubtitlePreset.Font.MONOSPACE_SERIF),
    PROPORTIONAL_SERIF(SubtitlePreset.Font.PROPORTIONAL_SERIF),
    MONOSPACE_SANS_SERIF(SubtitlePreset.Font.MONOSPACE_SANS_SERIF),
    PROPORTIONAL_SANS_SERIF(SubtitlePreset.Font.PROPORTIONAL_SANS_SERIF),
    CASUAL(SubtitlePreset.Font.CASUAL),
    CURSIVE(SubtitlePreset.Font.CURSIVE),
    SMALL_CAPITALS(SubtitlePreset.Font.SMALL_CAPITALS);

    private final SubtitlePreset.Font font;

    TimedTextFontStyleMapping(SubtitlePreset.Font font) {
        this.font = font;
    }

    /* JADX INFO: renamed from: getFont$AmazonAndroidVideoPlayer_release, reason: from getter */
    public final SubtitlePreset.Font getFont() {
        return this.font;
    }
}
