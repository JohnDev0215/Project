package com.amazon.avod.media.playback.android;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.util.DataUnit;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidVideoPlayerV2Config extends ServerConfigBase {
    private static AndroidVideoPlayerV2Config INSTANCE = new AndroidVideoPlayerV2Config();
    private final ConfigurationValue<Boolean> mIsAndroidVideoPlayerV2WeblabEnabled = newBooleanConfigValue("playback_isAndroidVideoPlayerV2WeblabEnabled", true);
    private final ConfigurationValue<Boolean> mIsAndroidVideoPlayerV2Enabled = newBooleanConfigValue("playback_isAndroidVideoPlayerV2Enabled", false);
    private final ConfigurationValue<Integer> mPlayerMinBufferMillis = newIntConfigValue("androidVideoPlayerV2_playerMinBufferMillis", 2500);
    private final ConfigurationValue<Integer> mPlayerMaxBufferMillis = newIntConfigValue("androidVideoPlayerV2_playerMaxBufferMillis", 30000);
    private final ConfigurationValue<Integer> mPlayerMinRebufferMillis = newIntConfigValue("androidVideoPlayerV2_playerMinRebufferMillis", DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT);
    private final ConfigurationValue<Integer> mPlayerBufferSegmentSizeBytes = newIntConfigValue("androidVideoPlayerV2_playerBufferSegmentSizeBytes", (int) DataUnit.KILOBYTES.toBytes(64.0f));
    private final ConfigurationValue<Boolean> mIsAndroidVideoPlayerV2CacheEnabled = newBooleanConfigValue("playback_isAndroidVideoPlayerV2CacheEnabled", true);
    private final ConfigurationValue<Integer> mAndroidVideoPlayerV2CacheSizeMb = newIntConfigValue("playback_androidVideoPlayerV2CacheSizeMb", 100);
    private final ConfigurationValue<Integer> mAndroidVideoPlayerV2CacheMaxConcurrentRequests = newIntConfigValue("playback_androidVideoPlayerV2CacheMaxConcurrentRequests", 1);
    private final ConfigurationValue<Boolean> mUseEnhancedUriAsCacheKey = newBooleanConfigValue("playback_useEnhancedUriAsCacheKey", true);

    public static AndroidVideoPlayerV2Config getInstance() {
        return INSTANCE;
    }

    private AndroidVideoPlayerV2Config() {
    }

    public boolean isAndroidVideoPlayerV2Enabled() {
        if (this.mIsAndroidVideoPlayerV2WeblabEnabled.getValue().booleanValue()) {
            MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_ANDROID_VIDEO_PLAYER_V2_276692");
            if (mobileWeblab == null) {
                return false;
            }
            mobileWeblab.trigger();
            return mobileWeblab.getCurrentTreatment() != WeblabTreatment.C;
        }
        return this.mIsAndroidVideoPlayerV2Enabled.getValue().booleanValue();
    }

    public int getPlayerMinBufferMillis() {
        return this.mPlayerMinBufferMillis.getValue().intValue();
    }

    public int getPlayerMaxBufferMillis() {
        return this.mPlayerMaxBufferMillis.getValue().intValue();
    }

    public int getPlayerMinRebufferMillis() {
        return this.mPlayerMinRebufferMillis.getValue().intValue();
    }

    public int getPlayerBufferSegmentSizeBytes() {
        return this.mPlayerBufferSegmentSizeBytes.getValue().intValue();
    }

    public boolean isAndroidVideoPlayerV2CacheEnabled() {
        return isAndroidVideoPlayerV2Enabled() && this.mIsAndroidVideoPlayerV2CacheEnabled.getValue().booleanValue();
    }

    public int getAndroidVideoPlayerV2CacheSizeMb() {
        return this.mAndroidVideoPlayerV2CacheSizeMb.getValue().intValue();
    }

    public int getAndroidVideoPlayerV2MaxConcurrentRequests() {
        return this.mAndroidVideoPlayerV2CacheMaxConcurrentRequests.getValue().intValue();
    }

    public boolean shouldUseEnhancedUriAsCacheKey() {
        return this.mUseEnhancedUriAsCacheKey.getValue().booleanValue();
    }
}
