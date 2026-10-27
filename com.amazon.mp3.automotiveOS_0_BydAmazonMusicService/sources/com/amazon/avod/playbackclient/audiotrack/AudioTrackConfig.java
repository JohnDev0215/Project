package com.amazon.avod.playbackclient.audiotrack;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.AudioFormat;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackConfig extends ConfigBase {
    private final ConfigurationValue<String> mAccessibilityAudioDescriptionsPlatformSettingsKey;
    private final ConfigurationValue<String> mAccessibilitySettingsValueForAudioTrackPreference;
    private final ConfigurationValue<String> mAudioSubTypePreferenceBasedOnAccessibilitySettings;
    private final ConfigurationValue<AudioFormat> mCustomerSurroundSoundPreference;
    private final ConfigurationValue<Boolean> mEnableCustomerSurroundSoundPreference;
    private final ConfigurationValue<String> mIndexPreference;
    private final ConfigurationValue<Boolean> mIsMultiTrackAudioEnabled;
    private final ConfigurationValue<String> mLanguagePreference;
    private final ConfigurationValue<Long> mLastTimeUnavailableToastShownMillis;
    private final ConfigurationValue<String> mLiveAudioLanguage;
    private final ConfigurationValue<Boolean> mOverrideUserAudioPreferenceBasedOnPlatformConfig;
    private final ConfigurationValue<Boolean> mSkipAudioLanguageXFormatInAccessibility;
    private final ConfigurationValue<String> mSubtypePreference;

    private static class SingletonHolder {
        private static AudioTrackConfig sInstance = new AudioTrackConfig();

        private SingletonHolder() {
        }
    }

    public static AudioTrackConfig getInstance() {
        return SingletonHolder.sInstance;
    }

    AudioTrackConfig() {
        super("com.amazon.avod.config.AudioTrackConfig");
        ConfigType configType = ConfigType.SERVER;
        this.mIsMultiTrackAudioEnabled = newBooleanConfigValue("isMultiTrackAudioEnabled", true, configType);
        this.mLiveAudioLanguage = newStringConfigValue("live_defaultAudioLanguage", "eng", configType);
        this.mAccessibilityAudioDescriptionsPlatformSettingsKey = newStringConfigValue("accessibilityAudioDescriptionsPlatformSettingsKey", "accessibility_audio_descriptions_enabled", configType);
        this.mAccessibilitySettingsValueForAudioTrackPreference = newStringConfigValue("valueForAccessibilitySettingsAudioDescriptiveFeatureKey", "1", configType);
        this.mAudioSubTypePreferenceBasedOnAccessibilitySettings = newStringConfigValue("audioSubTypePreferenceBasedOnPlatformPreference", "descriptive", configType);
        this.mOverrideUserAudioPreferenceBasedOnPlatformConfig = newBooleanConfigValue("overrideUserAudioPreferenceBasedOnPlatformConfig", false, configType);
        this.mLastTimeUnavailableToastShownMillis = newLongConfigValue("audio_preference_unavailable_last_toast_time", 0L, ConfigType.INTERNAL);
        ConfigType configType2 = ConfigType.ACCOUNT;
        this.mLanguagePreference = newStringConfigValue("audio_language", null, configType2);
        this.mSubtypePreference = newStringConfigValue("audio_track_subtype", null, configType2);
        this.mIndexPreference = newStringConfigValue("audio_track_index", null, configType2);
        this.mSkipAudioLanguageXFormatInAccessibility = newBooleanConfigValue("audio_skipAudioLanguageXFormatInAccessibility", true, configType);
        this.mCustomerSurroundSoundPreference = newEnumConfigValue("customer_surround_sound_preference", AudioFormat.AC_3_7_1, AudioFormat.class, configType2);
        this.mEnableCustomerSurroundSoundPreference = newBooleanConfigValue("enable_customer_surround_sound_preference", false, configType);
    }

    @Nonnull
    public AudioFormat getCustomerPreferredAudioFormat() {
        if (this.mEnableCustomerSurroundSoundPreference.getValue().booleanValue()) {
            return this.mCustomerSurroundSoundPreference.getValue();
        }
        return this.mCustomerSurroundSoundPreference.getDefaultValue();
    }

    @Nonnull
    public String getLiveAudioLanguage() {
        return this.mLiveAudioLanguage.getValue();
    }
}
