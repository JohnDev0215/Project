package com.amazon.mp3.view.lyrics.sharing.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: LyricsSharingMetricsSender.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingMetricsEvent;", "", "metricValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMetricValue", "()Ljava/lang/String;", "EVENT_NAME_LYRICS_SHARE_INTENT_FROM_TRACK_SHARE", "EVENT_NAME_LYRICS_PRESET_SELECTOR_PAGE", "EVENT_NAME_LYRICS_LINE_SELECTION_PAGE", "EVENT_NAME_LYRICS_SHARE_BUTTON_CLICK", "EVENT_NAME_LYRICS_PRESET_SHARE", "EVENT_NAME_LYRICS_SELECTION_NEXT_BUTTON_CLICK", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LyricsSharingMetricsEvent {
    EVENT_NAME_LYRICS_SHARE_INTENT_FROM_TRACK_SHARE("lyricsShareIntentFromTrackShare"),
    EVENT_NAME_LYRICS_PRESET_SELECTOR_PAGE("lyricsPresetSelectorPage"),
    EVENT_NAME_LYRICS_LINE_SELECTION_PAGE("lyricsLineSelectionPage"),
    EVENT_NAME_LYRICS_SHARE_BUTTON_CLICK("lyricsShareButtonClick"),
    EVENT_NAME_LYRICS_PRESET_SHARE("lyricsPresetShare"),
    EVENT_NAME_LYRICS_SELECTION_NEXT_BUTTON_CLICK("lyricsSelectionNextButtonClick");

    private final String metricValue;

    LyricsSharingMetricsEvent(String str) {
        this.metricValue = str;
    }

    public final String getMetricValue() {
        return this.metricValue;
    }
}
