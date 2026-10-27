package com.amazon.avod.settings;

import com.amazon.avod.media.MediaQuality;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum StreamingConnectionSetting {
    BEST(MediaQuality.HIGH, 0),
    BETTER(MediaQuality.MEDIUM, 1),
    GOOD(MediaQuality.LOW, 2),
    DATA_SAVER(MediaQuality.LOWEST, 3);

    private final MediaQuality mMediaQuality;
    private final int mValue;

    StreamingConnectionSetting(@Nonnull MediaQuality mediaQuality, int i) {
        this.mMediaQuality = (MediaQuality) Preconditions.checkNotNull(mediaQuality, "mediaQuality");
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    @Nonnull
    public MediaQuality getMediaQuality() {
        return this.mMediaQuality;
    }

    public static StreamingConnectionSetting fromInt(int i) {
        for (StreamingConnectionSetting streamingConnectionSetting : values()) {
            if (streamingConnectionSetting.getValue() == i) {
                return streamingConnectionSetting;
            }
        }
        return GOOD;
    }
}
