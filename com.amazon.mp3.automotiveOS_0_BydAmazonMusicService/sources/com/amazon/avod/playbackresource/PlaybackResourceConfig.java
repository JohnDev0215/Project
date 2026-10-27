package com.amazon.avod.playbackresource;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.BetaConfigProvider;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourceConfig extends ConfigBase {
    private final ConfigurationValue<Integer> mCacheSize;
    private final ConfigurationValue<Long> mCacheToLiveTimeMinutes;
    private final ConfigurationValue<String> mDAILiveManifestCapabilities;
    private final ConfigurationValue<Boolean> mIsLiveLinearRestrictionEnabled;
    private final TimeConfigurationValue mLinearRestrictionEndOffset;
    private final TimeConfigurationValue mLinearRestrictionStartOffset;
    private final ConfigurationValue<String> mLiveManifestCapabilities;
    private final ConfigurationValue<String> mLiveScheduleConfigToken;
    private final ConfigurationValue<Long> mPlayerResourcesCacheToLiveTimeMinutes;
    private final ConfigurationValue<Boolean> mShouldAppendChipsetInfo;
    private final ConfigurationValue<Boolean> mShouldAppendGDPRCookieConsent;
    private final ConfigurationValue<Boolean> mShouldAppendManufacturerInfo;
    private final ConfigurationValue<Boolean> mShouldAppendModelInfo;
    private final ConfigurationValue<Boolean> mShouldAppendOSVersionInfo;
    private final ConfigurationValue<Boolean> mShouldAppendPlayerVersionParams;
    private final ConfigurationValue<Boolean> mShouldRequestPlaybackSettingsInVDSM;
    private final ConfigurationValue<Boolean> mUseDAILiveManifestCapabilities;
    private final ConfigurationValue<Boolean> mUseDAILiveManifestCapabilitiesInBeta;

    private static class SingletonHolder {
        private static final PlaybackResourceConfig INSTANCE = new PlaybackResourceConfig();

        private SingletonHolder() {
        }
    }

    public static final PlaybackResourceConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    PlaybackResourceConfig() {
        super("PlaybackResourceConfig");
        ConfigType configType = ConfigType.SERVER;
        this.mLiveScheduleConfigToken = newStringConfigValue("playbackResource_liveScheduleConfigToken", "kindle-player-1", configType);
        this.mCacheSize = newIntConfigValue("playbackResources_cacheSize", 20, configType);
        ConfigurationValue<Long> configurationValueNewLongConfigValue = newLongConfigValue("playbackResources_linearRestrictionStartOffsetMinutes", 0L, configType);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.mLinearRestrictionStartOffset = new TimeConfigurationValue(configurationValueNewLongConfigValue, timeUnit);
        this.mLinearRestrictionEndOffset = new TimeConfigurationValue(newLongConfigValue("playbackResources_linearRestrictionEndOffsetMinutes", 960L, configType), timeUnit);
        this.mCacheToLiveTimeMinutes = newLongConfigValue("playbackResources_cacheToLiveTimeMinutes", 10L, configType);
        this.mIsLiveLinearRestrictionEnabled = newBooleanConfigValue("playbackResources_isLiveLinearRestrictionEnabled", false, configType);
        this.mLiveManifestCapabilities = newStringConfigValue("playbackResources_liveManifestCapabilities", "live,accumulating,patternTemplate", configType);
        this.mUseDAILiveManifestCapabilities = newBooleanConfigValue("playbackResources_useDaiLiveManifestCapabilities", true, configType);
        this.mUseDAILiveManifestCapabilitiesInBeta = newBooleanConfigValue("playbackResources_useDaiLiveManifestCapabilitiesInBeta", true, configType);
        this.mDAILiveManifestCapabilities = newStringConfigValue("playbackResources_daiLiveManifestCapabilities", "live,accumulating,patternTemplate", configType);
        this.mPlayerResourcesCacheToLiveTimeMinutes = newLongConfigValue("playerResources_cacheToLiveTimeMinutes", 10L, configType);
        this.mShouldAppendManufacturerInfo = newBooleanConfigValue("playback_shouldAppendManufacturerInfo", true, configType);
        this.mShouldAppendModelInfo = newBooleanConfigValue("playback_shouldAppendModelInfo", true, configType);
        this.mShouldAppendChipsetInfo = newBooleanConfigValue("playback_shouldAppendChipsetInfo", true, configType);
        this.mShouldAppendOSVersionInfo = newBooleanConfigValue("playback_shouldAppendOSVersionInfo", true, configType);
        this.mShouldAppendPlayerVersionParams = newBooleanConfigValue("playback_shouldAppendPlayerVersionParams", true, configType);
        this.mShouldRequestPlaybackSettingsInVDSM = newBooleanConfigValue("playback_shouldRequestPlaybackSettingsInVDSM", true, configType);
        this.mShouldAppendGDPRCookieConsent = newBooleanConfigValue("playback_shouldAppendGDPRCookieConsent", true, configType);
    }

    public int getCacheSize() {
        return this.mCacheSize.getValue().intValue();
    }

    public int getLinearRestrictionStartOffsetMinutes() {
        return this.mLinearRestrictionStartOffset.getValue().getTotalMinutes();
    }

    public int getLinearRestrictionEndOffsetMinutes() {
        return this.mLinearRestrictionEndOffset.getValue().getTotalMinutes();
    }

    public boolean shouldUseDynamicAdInsertedLiveManifestCapabilities() {
        return shouldUseDynamicAdInsertedLiveManifestCapabilitiesInBeta() || this.mUseDAILiveManifestCapabilities.getValue().booleanValue();
    }

    private boolean shouldUseDynamicAdInsertedLiveManifestCapabilitiesInBeta() {
        return this.mUseDAILiveManifestCapabilitiesInBeta.getValue().booleanValue() && BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }

    @Nonnull
    public String getDynamicAdInsertedLiveManifestCapabilities() {
        return this.mDAILiveManifestCapabilities.getValue();
    }

    public int getPlayerResourcesCacheSize() {
        return this.mCacheSize.getValue().intValue();
    }

    public long getPlayerResourcesCacheToLiveTimeMillis() {
        return TimeUnit.MINUTES.toMillis(this.mPlayerResourcesCacheToLiveTimeMinutes.getValue().longValue());
    }

    public boolean shouldAppendManufacturerInfo() {
        return this.mShouldAppendManufacturerInfo.getValue().booleanValue();
    }

    public boolean shouldAppendModelInfo() {
        return this.mShouldAppendModelInfo.getValue().booleanValue();
    }

    public boolean shouldAppendChipsetInfo() {
        return this.mShouldAppendChipsetInfo.getValue().booleanValue();
    }

    public boolean shouldAppendOSVersionInfo() {
        return this.mShouldAppendOSVersionInfo.getValue().booleanValue();
    }

    public boolean shouldAppendPlayerVersionParams() {
        return this.mShouldAppendPlayerVersionParams.getValue().booleanValue();
    }

    public boolean shouldAppendGDPRCookieConsent() {
        return this.mShouldAppendGDPRCookieConsent.getValue().booleanValue();
    }
}
