package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackZoomConfig extends ConfigBase {
    private final ConfigurationValue<String> mDefaultPlaybackZoomLevel;
    private final ConfigurationValue<Boolean> mEnableZoomFeature;
    private final ConfigurationValue<Boolean> mInvisibleZoomHasNonZeroSize;
    private ConfigurationValue<String> mPersistentPlaybackZoomLevel;
    private final ConfigurationValue<Boolean> mPersistentZoomEnabled;

    private static final class SingletonHolder {
        private static final PlaybackZoomConfig INSTANCE = new PlaybackZoomConfig();

        private SingletonHolder() {
        }
    }

    public static PlaybackZoomConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    PlaybackZoomConfig() {
        super("com.amazon.avod.media.framework.config");
        ConfigType configType = ConfigType.SERVER;
        this.mDefaultPlaybackZoomLevel = newStringConfigValue("playback_defaultZoomLevel", "NATIVE", configType);
        this.mPersistentZoomEnabled = newBooleanConfigValue("playback_persistentZoomEnabled", false, configType);
        this.mEnableZoomFeature = newBooleanConfigValue("playback_enableZoomFeature", true, configType);
        this.mPersistentPlaybackZoomLevel = newStringConfigValue("playback_persistentZoomLevel", "", ConfigType.INTERNAL);
        this.mInvisibleZoomHasNonZeroSize = newBooleanConfigValue("playback_invisibleZoomHasNonZeroSize", true, configType);
    }

    @Nonnull
    public PlaybackZoomLevel getPlaybackZoomLevel() {
        if (isZoomFeatureEnabled() && isPersistentZoomEnabled() && !Strings.isNullOrEmpty(this.mPersistentPlaybackZoomLevel.getValue())) {
            return getPersistentPlaybackZoomLevel();
        }
        return getDefaultPlaybackZoomLevel();
    }

    public boolean isPersistentZoomEnabled() {
        return this.mPersistentZoomEnabled.getValue().booleanValue();
    }

    public boolean isZoomFeatureEnabled() {
        return this.mEnableZoomFeature.getValue().booleanValue();
    }

    @Nonnull
    public PlaybackZoomLevel getPersistentPlaybackZoomLevel() {
        return PlaybackZoomLevel.getZoomLevelFromName(this.mPersistentPlaybackZoomLevel.getValue());
    }

    @Nonnull
    public PlaybackZoomLevel getDefaultPlaybackZoomLevel() {
        return PlaybackZoomLevel.getZoomLevelFromName(this.mDefaultPlaybackZoomLevel.getValue());
    }

    public void setPersistentPlaybackZoomLevel(@Nullable String str) {
        this.mPersistentPlaybackZoomLevel.updateValueString(str);
    }

    public boolean doesInvisibleZoomHaveNonZeroSize() {
        return this.mInvisibleZoomHasNonZeroSize.getValue().booleanValue();
    }
}
