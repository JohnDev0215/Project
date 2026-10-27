package com.amazon.avod.playbackclient.presentation;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PresentationCacheConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mPreferContentIdForVideoSpecMatch;
    private final ConfigurationValue<Boolean> mShouldDisableWhisperCacheDuringPreinit;
    private final TimeConfigurationValue mSpecStartTimeMatchThreshold;

    private static final class SingletonHolder {
        private static final PresentationCacheConfig INSTANCE = new PresentationCacheConfig();

        private SingletonHolder() {
        }
    }

    private PresentationCacheConfig() {
        this.mShouldDisableWhisperCacheDuringPreinit = newBooleanConfigValue("shouldDisableWhisperCacheDuringPreinit", false);
        this.mPreferContentIdForVideoSpecMatch = newBooleanConfigValue("preferContentIdForVideoSpecMatch", true);
        this.mSpecStartTimeMatchThreshold = newTimeConfigurationValue("playback_specStartTimeMatchThresholdMillis", TimeSpan.fromSeconds(2.0d), TimeUnit.MILLISECONDS, ConfigType.SERVER);
    }

    public static PresentationCacheConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean shouldDisableWhisperCacheDuringPreinit() {
        return this.mShouldDisableWhisperCacheDuringPreinit.getValue().booleanValue();
    }

    public boolean preferContentIdForVideoSpecMatch() {
        return this.mPreferContentIdForVideoSpecMatch.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getSpecStartTimeMatchThreshold() {
        return this.mSpecStartTimeMatchThreshold.getValue();
    }
}
