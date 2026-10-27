package com.amazon.avod.media.drm;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;

/* JADX INFO: loaded from: classes2.dex */
public class MediaDrmConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mGenerateCryptoInfoForClearSamples;
    private final ConfigurationValue<Boolean> mIsMediaDrmChallengeReplayEnabled;
    private final ConfigurationValue<Boolean> mIsMediaDrmMultiSessionEnabled;
    private final ConfigurationValue<Boolean> mMediaDrmReturnNullRightsForIAE;
    private final ConfigurationValue<Boolean> mPreLoadMediaDrmOnStartup;

    public static MediaDrmConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        public static final MediaDrmConfig INSTANCE = new MediaDrmConfig();

        private SingletonHolder() {
        }
    }

    private MediaDrmConfig() {
        this.mGenerateCryptoInfoForClearSamples = newBooleanConfigValue(PlaybackConfigKeys.GENERATE_CRYPTOINFO_FOR_CLEAR_SAMPLES, false);
        this.mPreLoadMediaDrmOnStartup = newBooleanConfigValue(PlaybackConfigKeys.PRELOAD_MEDIADRM_ON_STARTUP, true);
        this.mIsMediaDrmMultiSessionEnabled = newBooleanConfigValue(PlaybackConfigKeys.MEDIADRM_MULTI_SESSION_ENABLED, false);
        ConfigType configType = ConfigType.SERVER;
        this.mIsMediaDrmChallengeReplayEnabled = newBooleanConfigValue(PlaybackConfigKeys.MEDIADRM_CHALLENGE_REPLAY_ENABLED, false, configType);
        this.mMediaDrmReturnNullRightsForIAE = newBooleanConfigValue("playback_mediaDrmReturnNullRightsForIAE", true, configType);
    }

    public boolean shouldGenerateCryptoInfoForClearSamples() {
        return this.mGenerateCryptoInfoForClearSamples.getValue().booleanValue();
    }

    public boolean shouldPreLoadMediaDrmOnStartup() {
        return this.mPreLoadMediaDrmOnStartup.getValue().booleanValue();
    }

    public boolean isMediaDrmMultiSessionEnabled() {
        return this.mIsMediaDrmMultiSessionEnabled.getValue().booleanValue();
    }

    public boolean isMediaDrmChallengeReplayEnabled() {
        return this.mIsMediaDrmChallengeReplayEnabled.getValue().booleanValue();
    }

    public boolean getMediaDrmReturnNullRightsForIAE() {
        return this.mMediaDrmReturnNullRightsForIAE.getValue().booleanValue();
    }
}
