package com.amazon.avod.playback.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDebugOverride extends MediaConfigBase {
    private final ConfigurationValue<Long> mLiveEventDurationMillis = newLongConfigValue("playback_liveEventDurationMillis", -1);
    private final ConfigurationValue<Long> mLiveStartTimeMillis = newLongConfigValue("playback_liveEventStartTimeMillis", -1);
    private final ConfigurationValue<Long> mLiveEventLookBackDurationMilliss = newLongConfigValue("playback_liveEventLookBackDurationMillis", -1);

    private static class Holder {
        private static final PlaybackDebugOverride INSTANCE = new PlaybackDebugOverride();

        private Holder() {
        }
    }

    public static PlaybackDebugOverride getInstance() {
        return Holder.INSTANCE;
    }

    public long getLiveEventLookBackDurationMillis() {
        return this.mLiveEventLookBackDurationMilliss.getValue().longValue();
    }
}
