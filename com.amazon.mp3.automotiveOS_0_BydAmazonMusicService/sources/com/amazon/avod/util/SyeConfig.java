package com.amazon.avod.util;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityConfig;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* JADX INFO: loaded from: classes2.dex */
public class SyeConfig extends ConfigBase {
    private final BetaConfig mBetaConfig;
    private final ConfigurationValue<Boolean> mContinuePlayInBackground;
    private final ConfigurationValue<Integer> mDefaultFrameRate;
    private final ConfigurationValue<Boolean> mDynamicAudioLatencyCompensation;
    private final ConfigurationValue<Integer> mEgressContactTimeThresholdMillis;
    private final ConfigurationValue<Long> mLiveRangeMillis;
    private final ConfigurationValue<Integer> mMaxHFRDisplayHeight;
    private final ConfigurationValue<Integer> mMaxHFRDisplayWidth;
    private final ConfigurationValue<Integer> mMaxResolutionHeightMediaQualityLow;
    private final ConfigurationValue<Integer> mMaxResolutionHeightMediaQualityLowest;
    private final ConfigurationValue<Integer> mMaxResolutionHeightMediaQualityMedium;
    private final ConfigurationValue<Integer> mMaxRetryTimeoutMillis;
    private final ConfigurationValue<Integer> mPlayingStallThresholdMillis;
    private final ConfigurationValue<Integer> mPreferredDelayMillis;
    private final ConfigurationValue<Boolean> mRenderLatencyCompensation;
    private final ConfigurationValue<Integer> mRetryIntervalMillis;
    private final ConfigurationValue<Long> mSeekPaddingMillis;
    private final ConfigurationValue<Boolean> mSmoothFrameRendering;
    private final ConfigurationValue<Integer> mStallPlayingThresholdMillis;
    private final ConfigurationValue<String> mStaticCloudFrontChannelId;
    private final ConfigurationValue<String> mStaticCloudFrontUrl;
    private final ConfigurationValue<Integer> mStatisticsNotificationIntervalMillis;
    private final ConfigurationValue<Boolean> mSwitchFrontendUrlEnabledForRetryableError;
    private final ConfigurationValue<SyeSwitch> mSyePlayerEnabled;
    private final WhitelistBlacklistAvailabilityConfig mSyePlayerEnabledWhiteList;
    private final ConfigurationValue<Boolean> mUseStaticCloudFrontUrl;

    private enum SyeSwitch {
        OFF,
        BETA,
        WEB_LAB,
        ON
    }

    private static class SingletonHolder {
        private static final SyeConfig INSTANCE = new SyeConfig();

        private SingletonHolder() {
        }
    }

    public static SyeConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    protected SyeConfig() {
        super("SyeConfig");
        this.mBetaConfig = BetaConfigProvider.getInstance().provideBetaConfig();
        SyeSwitch syeSwitch = SyeSwitch.OFF;
        ConfigType configType = ConfigType.SERVER;
        this.mSyePlayerEnabled = newEnumConfigValue("SyeConfig_SyePlayerEnabled_4", syeSwitch, SyeSwitch.class, configType);
        this.mDefaultFrameRate = newIntConfigValue("SyeConfig_DefaultFrameRate", 60, configType);
        this.mRetryIntervalMillis = newIntConfigValue("SyeConfig_RetryIntervalMillis", 500, configType);
        this.mStatisticsNotificationIntervalMillis = newIntConfigValue("SyeConfig_StatisticsNotificationInterval", DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT, configType);
        this.mPreferredDelayMillis = newIntConfigValue("SyeConfig_PreferredDelayMillis", 0, configType);
        this.mSeekPaddingMillis = newLongConfigValue("SyeConfig_SeekPaddingMillis", CoroutineLiveDataKt.DEFAULT_TIMEOUT, configType);
        this.mLiveRangeMillis = newLongConfigValue("SyeConfig_LiveRangeMillis", CoroutineLiveDataKt.DEFAULT_TIMEOUT, configType);
        this.mEgressContactTimeThresholdMillis = newIntConfigValue("SyeConfig_EgressContactTimeThresholdMillis", 500, configType);
        this.mSyePlayerEnabledWhiteList = new WhitelistBlacklistAvailabilityConfig("SyeConfig_SyePlayerEnabled", false);
        this.mContinuePlayInBackground = newBooleanConfigValue("SyeConfig_ContinuePlayInBackground", true, configType);
        this.mDynamicAudioLatencyCompensation = newBooleanConfigValue("SyeConfig_DynamicAudioLatencyCompensation", true, configType);
        this.mSmoothFrameRendering = newBooleanConfigValue("SyeConfig_SmoothFrameRendering", true, configType);
        this.mRenderLatencyCompensation = newBooleanConfigValue("SyeConfig_RenderLatencyCompensation", true, configType);
        this.mMaxHFRDisplayWidth = newIntConfigValue("SyeConfig_MaxHFRWidth", 1920, configType);
        this.mMaxHFRDisplayHeight = newIntConfigValue("SyeConfig_MaxHFRHeight", 1080, configType);
        this.mPlayingStallThresholdMillis = newIntConfigValue("SyeConfig_PlayingStallThresholdMillis", 0, configType);
        this.mStallPlayingThresholdMillis = newIntConfigValue("SyeConfig_StallPlayingThresholdMillis", 1000, configType);
        this.mMaxResolutionHeightMediaQualityLowest = newIntConfigValue("SyeConfig_MaxResolutionHeightMediaQualityLowest", 396, configType);
        this.mMaxResolutionHeightMediaQualityLow = newIntConfigValue("SyeConfig_MaxResolutionHeightMediaQualityLow", 540, configType);
        this.mMaxResolutionHeightMediaQualityMedium = newIntConfigValue("SyeConfig_MaxResolutionHeightMediaQualityMedium", 720, configType);
        this.mUseStaticCloudFrontUrl = newBooleanConfigValue("SyeConfig_UseStaticCloudFrontUrl", false, configType);
        this.mStaticCloudFrontUrl = newStringConfigValue("SyeConfig_StaticCloudFrontUrl", "", configType);
        this.mStaticCloudFrontChannelId = newStringConfigValue("SyeConfig_StaticCloudChannelId", "", configType);
        this.mMaxRetryTimeoutMillis = newIntConfigValue("SyeConfig_MaxRetryTimeoutMillis", 15000, configType);
        this.mSwitchFrontendUrlEnabledForRetryableError = newBooleanConfigValue("SyeConfig_SwitchFrontendUrlEnabledForRetryableError", true, configType);
    }

    public boolean isPlayerEnabled() {
        if (this.mSyePlayerEnabledWhiteList.isAvailableForDevice()) {
            return true;
        }
        SyeSwitch value = this.mSyePlayerEnabled.getValue();
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$util$SyeConfig$SyeSwitch[value.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return this.mBetaConfig.isInternalBeta();
        }
        if (i == 3) {
            MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_12263_177208");
            return mobileWeblab != null && mobileWeblab.getCurrentTreatment() == WeblabTreatment.T1;
        }
        if (i == 4) {
            return true;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Please handle new SyeSwitch state: %s", value.name()));
    }

    /* JADX INFO: renamed from: com.amazon.avod.util.SyeConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$util$SyeConfig$SyeSwitch;

        static {
            int[] iArr = new int[SyeSwitch.values().length];
            $SwitchMap$com$amazon$avod$util$SyeConfig$SyeSwitch = iArr;
            try {
                iArr[SyeSwitch.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$util$SyeConfig$SyeSwitch[SyeSwitch.BETA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$util$SyeConfig$SyeSwitch[SyeSwitch.WEB_LAB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$util$SyeConfig$SyeSwitch[SyeSwitch.ON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int getDefaultFrameRate() {
        return this.mDefaultFrameRate.getValue().intValue();
    }

    public int getRetryIntervalMillis() {
        return this.mRetryIntervalMillis.getValue().intValue();
    }

    public int getStatisticsNotificationIntervalMillis() {
        return this.mStatisticsNotificationIntervalMillis.getValue().intValue();
    }

    public int getPreferredDelayMillis() {
        return this.mPreferredDelayMillis.getValue().intValue();
    }

    public long getSeekPaddingMillis() {
        return this.mSeekPaddingMillis.getValue().longValue();
    }

    public long getLiveRangeMillis() {
        return this.mLiveRangeMillis.getValue().longValue();
    }

    public int getEgressContactTimeThresholdMillis() {
        return this.mEgressContactTimeThresholdMillis.getValue().intValue();
    }

    public boolean getContinuePlayInBackground() {
        return this.mContinuePlayInBackground.getValue().booleanValue();
    }

    public boolean getDynamicAudioLatencyCompensation() {
        return this.mDynamicAudioLatencyCompensation.getValue().booleanValue();
    }

    public boolean getSmoothFrameRendering() {
        return this.mSmoothFrameRendering.getValue().booleanValue();
    }

    public boolean getRenderLatencyCompensation() {
        return this.mRenderLatencyCompensation.getValue().booleanValue();
    }

    public int getMaxHFRDisplayWidth() {
        return this.mMaxHFRDisplayWidth.getValue().intValue();
    }

    public int getMaxHFRDisplayHeight() {
        return this.mMaxHFRDisplayHeight.getValue().intValue();
    }

    public int getPlayingStallThresholdMillis() {
        return this.mPlayingStallThresholdMillis.getValue().intValue();
    }

    public int getStallPlayingThresholdMillis() {
        return this.mStallPlayingThresholdMillis.getValue().intValue();
    }

    public int getMaxResolutionHeightMediaQualityLowest() {
        return this.mMaxResolutionHeightMediaQualityLowest.getValue().intValue();
    }

    public int getMaxResolutionHeightMediaQualityLow() {
        return this.mMaxResolutionHeightMediaQualityLow.getValue().intValue();
    }

    public int getMaxResolutionHeightMediaQualityMedium() {
        return this.mMaxResolutionHeightMediaQualityMedium.getValue().intValue();
    }

    public boolean getUseStaticCloudFrontUrl() {
        return this.mUseStaticCloudFrontUrl.getValue().booleanValue();
    }

    public String getStaticCloudFrontUrl() {
        return this.mStaticCloudFrontUrl.getValue();
    }

    public String getStaticCloudFrontChannelId() {
        return this.mStaticCloudFrontChannelId.getValue();
    }

    public int getMaxRetryTimeoutMillis() {
        return this.mMaxRetryTimeoutMillis.getValue().intValue();
    }

    public boolean getSwitchFrontendUrlEnabledForRetryableError() {
        return this.mSwitchFrontendUrlEnabledForRetryableError.getValue().booleanValue();
    }
}
