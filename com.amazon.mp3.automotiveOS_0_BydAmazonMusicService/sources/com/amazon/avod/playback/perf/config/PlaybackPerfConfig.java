package com.amazon.avod.playback.perf.config;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPerfConfig extends ServerConfigBase {
    private final ConfigurationValue<Boolean> mIsPlaybackPerfEnabled;

    private static final class SingletonHolder {
        private static final PlaybackPerfConfig INSTANCE = new PlaybackPerfConfig();

        private SingletonHolder() {
        }
    }

    @Nonnull
    public static PlaybackPerfConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlaybackPerfConfig() {
        this.mIsPlaybackPerfEnabled = newBooleanConfigValue(PlaybackConfigKeys.PLAYBACK_PERF_ENABLED_KEY, false);
    }

    public boolean isPlaybackPerfEnabled() {
        return this.mIsPlaybackPerfEnabled.getValue().booleanValue();
    }
}
