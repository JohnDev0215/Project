package com.amazon.mp3.ui.interaction;

import kotlin.Metadata;

/* JADX INFO: compiled from: UiClickActionType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/ui/interaction/UiClickActionType;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "SELECT_WAZE_SETTING_ON", "SELECT_WAZE_SETTING_OFF", "SELECT_WAZE_BANNER", "SELECT_WAZE_BANNER_CLOSE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum UiClickActionType {
    SELECT_WAZE_SETTING_ON("selectWazeSettingOn"),
    SELECT_WAZE_SETTING_OFF("selectWazeSettingOff"),
    SELECT_WAZE_BANNER("selectWazeBanner"),
    SELECT_WAZE_BANNER_CLOSE("selectWazeBannerClose");

    private final String key;

    UiClickActionType(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
