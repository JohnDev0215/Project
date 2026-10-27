package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackEvent implements NamedEnum {
    WATCHED_UNTIL_CREDITS("WATCHED_UNTIL_CREDITS");

    private final String strValue;

    PlaybackEvent(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static PlaybackEvent forValue(String str) {
        Preconditions.checkNotNull(str);
        for (PlaybackEvent playbackEvent : values()) {
            if (playbackEvent.strValue.equals(str)) {
                return playbackEvent;
            }
        }
        return null;
    }
}
