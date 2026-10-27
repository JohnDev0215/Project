package com.amazon.avod.media.playback.support;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.app.UiModeManager;
import android.content.Context;
import android.os.SystemProperties;
import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.core.Framework;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RendererSchemeResolverConfig extends MediaConfigBase {
    private static final String CHROME_OS_SYSTEM_FEATURE_NAME = "org.chromium.arc";
    private static final String EMULATOR_SYSTEM_PROPERTY = "ro.kernel.qemu";
    private static final String PORTAL_OS_SYSTEM_FEATURE_NAME = "com.facebook.portal.sdk";
    private final DeviceIdentity mDeviceIdentity;
    private final ConfigurationValue<Boolean> mHandleHardwareToSoftwareRendererSchemeTransition;
    private final ConfigurationValue<Boolean> mHardwareAcceleratedRendererSchemeFlag;
    private final ConfigurationValue<Boolean> mIsAndroidTvDetectionEnabled;
    private final WhitelistBlacklistAvailabilityConfig mIsAutoSchemeResolutionEnabled;
    private final ConfigurationValue<Boolean> mIsAutoSchemeResolutionEnabledForSafelistedDevices;
    private final ConfigurationValue<Boolean> mIsChromeOsDetectionEnabled;
    private final ConfigurationValue<Boolean> mIsDisplayResolutionEvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsDisplayToneMappingEvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsEmulatorSupportEnabled;
    private final ConfigurationValue<Boolean> mIsEncodeDecodeSanityEvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsHardwareAccelerationToggleEnabled;
    private final ConfigurationValue<Boolean> mIsHevcEvaluationForMediaCodecSoftwarePlayreadyEnabled;
    private final ConfigurationValue<Boolean> mIsMediaCodecBasicSanityEvaluationEnabled;
    private final WhitelistBlacklistAvailabilityConfig mIsMediaCodecSoftwarePlayreadyOverrideEnabled;
    private final ConfigurationValue<Boolean> mIsMediaCodecSoftwarePlayreadyOverrideForBetaEnabled;
    private final ConfigurationValue<Boolean> mIsMediaCodecSoftwarePlayreadyOverrideWeblabEnabled;
    private final ConfigurationValue<Boolean> mIsMediaCodecVideoCapabilitiesEvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsMediaDrmSanityEvaluatorEnabled;
    private final ConfigurationValue<Boolean> mIsMultiRendererSchemeResolutionEnabled;
    private final ConfigurationValue<Boolean> mIsPortalOsDetectionEnabled;
    private final ThirdPartyProfileName mProfileAsPerConfig;
    private final ConfigurationValue<Boolean> mShouldConsiderDisplayResolutionForUHDCapabilityDetection;
    private final ConfigurationValue<Boolean> mShouldDirectLoadNativeLibraries;
    private final ConfigurationValue<Boolean> mShouldDisableVisualOnRendererOnAndroidTv;
    private final ConfigurationValue<Boolean> mShouldThrowInitializationExceptionInsteadOfISE;

    public static RendererSchemeResolverConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        public static final RendererSchemeResolverConfig INSTANCE = new RendererSchemeResolverConfig();

        private SingletonHolder() {
        }
    }

    private RendererSchemeResolverConfig() {
        this.mIsMultiRendererSchemeResolutionEnabled = newBooleanConfigValue("playback_isMultiRendererSchemeResolutionEnabled", true);
        ConfigType configType = ConfigType.SERVER;
        this.mIsChromeOsDetectionEnabled = newBooleanConfigValue("playback_isChromeOsDetectionEnabled", true, configType);
        this.mIsPortalOsDetectionEnabled = newBooleanConfigValue("playback_isPortalOsDetectionEnabled", true, configType);
        this.mIsAndroidTvDetectionEnabled = newBooleanConfigValue("playback_isAndroidTvDetectionEnabled", true);
        this.mShouldDisableVisualOnRendererOnAndroidTv = newBooleanConfigValue("playback_shouldDisableVisualOnRendererOnAndroidTv", true);
        this.mIsEmulatorSupportEnabled = newBooleanConfigValue("playback_isEmulatorSupportEnabled", false, configType);
        this.mProfileAsPerConfig = ThirdPartyProfileName.fromProfileString(ThirdPartyConfigurationProfile.getInstance().getDeviceProfileName());
        this.mIsAutoSchemeResolutionEnabled = new WhitelistBlacklistAvailabilityConfig("playback_isAutoSchemeResolutionEnabled", false);
        this.mIsAutoSchemeResolutionEnabledForSafelistedDevices = newBooleanConfigValue("playback_isAutoSchemeResolutionEnabledForSafelistedDevices", false, configType);
        this.mIsDisplayResolutionEvaluationEnabled = newBooleanConfigValue("playback_isDisplayResolutionEvaluationEnabled", false, configType);
        this.mIsDisplayToneMappingEvaluationEnabled = newBooleanConfigValue("playback_isDisplayToneMappingEvaluationEnabled", false, configType);
        this.mIsHardwareAccelerationToggleEnabled = newBooleanConfigValue("playback_isHardwareAccelerationToggleEnabled", true, configType);
        this.mIsMediaCodecBasicSanityEvaluationEnabled = newBooleanConfigValue("playback_isMediaCodecBasicSanityEvaluationEnabled", true, configType);
        this.mIsEncodeDecodeSanityEvaluationEnabled = newBooleanConfigValue("playback_isEncodeDecodeSanityEvaluationEnabled", false, configType);
        this.mIsMediaCodecVideoCapabilitiesEvaluationEnabled = newBooleanConfigValue("playback_isMediaCodecVideoCapabilitiesEvaluationEnabled", false, configType);
        this.mIsMediaDrmSanityEvaluatorEnabled = newBooleanConfigValue("playback_isMediaDrmSanityEvaluatorEnabled", false, configType);
        this.mShouldConsiderDisplayResolutionForUHDCapabilityDetection = newBooleanConfigValue("playback_shouldConsiderDisplayResolutionForUHDCapabilityDetection", true, configType);
        this.mShouldThrowInitializationExceptionInsteadOfISE = newBooleanConfigValue("playback_shouldThrowInitializationExceptionInsteadOfISE", true, configType);
        this.mIsMediaCodecSoftwarePlayreadyOverrideWeblabEnabled = newBooleanConfigValue("playback_isMediaCodecSoftwarePlayreadyOverrideWeblabEnabled", true, configType);
        this.mIsMediaCodecSoftwarePlayreadyOverrideForBetaEnabled = newBooleanConfigValue("playback_isMediaCodecSoftwarePlayreadyOverrideForBetaEnabled", true, configType);
        this.mIsMediaCodecSoftwarePlayreadyOverrideEnabled = new WhitelistBlacklistAvailabilityConfig("playback_isMediaCodecSoftwarePlayreadyOverrideEnabled", false);
        this.mIsHevcEvaluationForMediaCodecSoftwarePlayreadyEnabled = newBooleanConfigValue("playback_isHevcEvaluationForMediaCodecSoftwarePlayreadyEnabled", false);
        this.mShouldDirectLoadNativeLibraries = newBooleanConfigValue("playback_shouldDirectLoadNativeLibraries", false);
        this.mDeviceIdentity = MediaSystemSharedDependencies.getInstance().getDeviceIdentity();
        this.mHandleHardwareToSoftwareRendererSchemeTransition = newBooleanConfigValue("playback_handleHardwareToSoftwareRendererSchemeTransition", true);
        this.mHardwareAcceleratedRendererSchemeFlag = newBooleanConfigValue("playback_hardwareAcceleratedRendererSchemeFlag", false, ConfigType.INTERNAL);
    }

    public boolean isMultiRendererSchemeResolutionEnabled() {
        return !isEmulator() && this.mIsMultiRendererSchemeResolutionEnabled.getValue().booleanValue();
    }

    public boolean isChromeOsDetectionEnabled() {
        return this.mIsChromeOsDetectionEnabled.getValue().booleanValue();
    }

    public boolean isChromeOs(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        try {
            return context.getPackageManager().hasSystemFeature(CHROME_OS_SYSTEM_FEATURE_NAME);
        } catch (Exception e) {
            DLog.warnf("Failed to detect if we are on ChromeOS: %s", e);
            return false;
        }
    }

    public boolean shouldDisableVisualOnRendererOnAndroidTv() {
        return this.mShouldDisableVisualOnRendererOnAndroidTv.getValue().booleanValue();
    }

    public boolean isAndroidTv(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        try {
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
            if (this.mIsAndroidTvDetectionEnabled.getValue().booleanValue() && uiModeManager.getCurrentModeType() == 4) {
                if (!this.mDeviceIdentity.isAmazonDevice()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            DLog.warnf("Failed to detect if we are on AndroidTV: %s", e);
            return false;
        }
    }

    public boolean isPortalOs(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        try {
            return context.getPackageManager().hasSystemFeature(PORTAL_OS_SYSTEM_FEATURE_NAME);
        } catch (Exception e) {
            DLog.warnf("Failed to detect if we are on Portal OS: %s", e);
            return false;
        }
    }

    public boolean isEmulator() {
        return SystemProperties.get(EMULATOR_SYSTEM_PROPERTY, EnvironmentCompat.MEDIA_UNKNOWN).equals("1") && (Framework.isDebugConfigurationEnabled() || this.mIsEmulatorSupportEnabled.getValue().booleanValue());
    }

    public boolean isAutoSchemeResolutionEnabled() {
        return this.mIsAutoSchemeResolutionEnabled.isAvailableForDevice() && (!isAlreadySafelisted() || this.mIsAutoSchemeResolutionEnabledForSafelistedDevices.getValue().booleanValue());
    }

    private boolean isAlreadySafelisted() {
        ThirdPartyProfileName thirdPartyProfileName = this.mProfileAsPerConfig;
        return (thirdPartyProfileName == null || thirdPartyProfileName == ThirdPartyProfileName.SD || thirdPartyProfileName == ThirdPartyProfileName.SD_MEDIACODEC_SOFTWARE_PLAYREADY) ? false : true;
    }

    public boolean isDisplayResolutionEvaluationEnabled() {
        return this.mIsDisplayResolutionEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isDisplayToneMappingEvaluationEnabled() {
        return this.mIsDisplayToneMappingEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isHardwareAccelerationToggleEnabled() {
        return this.mIsHardwareAccelerationToggleEnabled.getValue().booleanValue();
    }

    public boolean isMediaCodecBasicSanityEvaluationEnabled() {
        return this.mIsMediaCodecBasicSanityEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isEncodeDecodeSanityEvaluationEnabled() {
        return this.mIsEncodeDecodeSanityEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isMediaCodecVideoCapabilitiesEvaluationEnabled() {
        return this.mIsMediaCodecVideoCapabilitiesEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isMediaDrmSanityEvaluatorEnabled() {
        return this.mIsMediaDrmSanityEvaluatorEnabled.getValue().booleanValue();
    }

    public boolean shouldConsiderDisplayResolutionForUHDCapabilityDetection() {
        return this.mShouldConsiderDisplayResolutionForUHDCapabilityDetection.getValue().booleanValue();
    }

    public boolean shouldThrowInitializationExceptionInsteadOfISE() {
        return MediaSystemSharedDependencies.getInstance().isSDKPlayer() || this.mShouldThrowInitializationExceptionInsteadOfISE.getValue().booleanValue();
    }

    public boolean isMediaCodecSoftwarePlayreadyOverrideEnabled(@Nonnull DeviceIdentity deviceIdentity) {
        Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        if (deviceIdentity.isAmazonDevice()) {
            return false;
        }
        if (BetaConfigProvider.getInstance().provideBetaConfig().isBeta()) {
            return this.mIsMediaCodecSoftwarePlayreadyOverrideForBetaEnabled.getValue().booleanValue();
        }
        if (this.mIsMediaCodecSoftwarePlayreadyOverrideWeblabEnabled.getValue().booleanValue()) {
            MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_MEDIACODEC_SOFTWAREPLAYREADY_331879");
            if (mobileWeblab == null) {
                return false;
            }
            mobileWeblab.trigger();
            return mobileWeblab.getCurrentTreatment() != WeblabTreatment.C;
        }
        return this.mIsMediaCodecSoftwarePlayreadyOverrideEnabled.isAvailableForDevice();
    }

    public boolean isHevcEvaluationForMediaCodecSoftwarePlayreadyEnabled() {
        return this.mIsHevcEvaluationForMediaCodecSoftwarePlayreadyEnabled.getValue().booleanValue();
    }

    public boolean shouldDirectLoadNativeLibraries() {
        return this.mShouldDirectLoadNativeLibraries.getValue().booleanValue();
    }

    public boolean shouldHandleHardwareToSoftwareRendererSchemeTransition() {
        return this.mHandleHardwareToSoftwareRendererSchemeTransition.getValue().booleanValue();
    }

    @Nonnull
    public ConfigurationValue<Boolean> getHardwareAcceleratedRendererSchemeFlag() {
        return this.mHardwareAcceleratedRendererSchemeFlag;
    }
}
