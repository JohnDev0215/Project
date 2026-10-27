package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesV2Config extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mIsPrsV2CallEnabled = newBooleanConfigValue("playbackResourcesV2_isPrsV2CallEnabled", true);
    private final ConfigurationValue<Boolean> mIsPrsV2CallEnabledInBeta = newBooleanConfigValue("playbackResourcesV2_isPrsV2CallEnabledBeta", true);
    private final ConfigurationValue<Boolean> mIsPrsV2ForClientResourcesEnabled = newBooleanConfigValue("playbackResourcesV2_isPrsV2ForClientResourcesEnabled", false);
    private final ConfigurationValue<Boolean> mIsPrsV2ForClientResourcesEnabledInBeta = newBooleanConfigValue("playbackResourcesV2_isPrsV2ForClientResourcesEnabledInBeta", true);
    private final ConfigurationValue<Boolean> mIsPrsV2ForClientResourcesWeblabEnabled = newBooleanConfigValue("playbackResourcesV2_isPrsV2ForClientResourcesWeblabEnabled", true);
    private final ConfigurationValue<Set<String>> mVideoMaterialTypeSupportSet = newSemicolonDelimitedStringSetConfigurationValue("playbackResourcesV2_videoMaterialTypeSupportSet", new String[]{VideoMaterialType.LiveStreaming.toString()});
    private final ConfigurationValue<List<String>> mSupportedStreamingTechnologies = newSemicolonDelimitedStringListConfigurationValue("playbackResourcesV2_supportedStreamingTechnologies", new String[]{"DASH"});
    private final ConfigurationValue<String> mHdcpLevelForNoHdcpInputDevice = newStringConfigValue("playbackResourcesV2_hdcpLevelForNoHdcpInputDevice", "2.3");
    private final ConfigurationValue<String> mDefaultDashBitrateAdaptation = newStringConfigValue("playbackResourcesV2_defaultDashBitrateAdaptation", "CBR");
    private final ConfigurationValue<String> mLiveManifestCapabilities = newStringConfigValue("playbackResourcesV2_liveManifestCapabilities", "patternTemplate,accumulating,live");
    private final ConfigurationValue<Boolean> mIsHFREnabled = newBooleanConfigValue("playbackResourcesV2_isHFREnabled", false);
    private final ConfigurationValue<Boolean> mIsPreparingStateValidationPathEnabled = newBooleanConfigValue("playbackResourcesV2_isPreparingStateValidationPathEnabled", true);
    private final ConfigurationValue<Boolean> mShouldCallPRSV1ForUnsupportedResources = newBooleanConfigValue("playbackResourcesV2_shouldCallPRSV1ForUnsupportedResources", true);
    private final ConfigurationValue<Set<String>> mPRSV2UnsupportedResourceSet = newSemicolonDelimitedStringSetConfigurationValue("playbackResourcesV2_unsupportedResourceSet", new String[]{Resource.SubtitlePresets.name()});
    private final ConfigurationValue<Long> mPRSV1ExecutorTimeoutMilliseconds = newLongConfigValue("playbackResourcesV2_v1ExecutorTimeoutMilliseconds", 1000);
    private final ConfigurationValue<Long> mPRSV2ExecutorTimeoutMilliseconds = newLongConfigValue("playbackResourcesV2_v2ExecutorTimeoutMilliseconds", 20000);
    private final ConfigurationValue<Boolean> mIsPRSv2ResponseOverrideEnabled = newBooleanConfigValue("playbackResourcesV2_isPRSv2ResponseOverrideEnabled", false);
    private final ConfigurationValue<String> mPRSv2ResponseOverrideFilepath = newStringConfigValue("playbackResourcesV2_PRSv2ResponseOverrideFilepath", "");
    private final ConfigurationValue<Boolean> mIsSoftwareVersionEnabledForSye = newBooleanConfigValue("playbackResourcesV2_mIsSoftwareVersionEnabledForSye", true);
    private final ConfigurationValue<Boolean> mShouldRequestRapidRecapResourcesFromPRS = newBooleanConfigValue("playbackResourcesV2_mShouldRequestRapidRecapResourcesFromPRS", false);

    private static final class SingletonHolder {
        private static final PlaybackResourcesV2Config INSTANCE = new PlaybackResourcesV2Config();

        private SingletonHolder() {
        }
    }

    public static PlaybackResourcesV2Config getInstance() {
        return SingletonHolder.INSTANCE;
    }

    PlaybackResourcesV2Config() {
    }

    public boolean isPrsV2CallEnabled() {
        return this.mIsPrsV2CallEnabled.getValue().booleanValue() || (this.mIsPrsV2CallEnabledInBeta.getValue().booleanValue() && BetaConfigProvider.getInstance().provideBetaConfig().isBeta());
    }

    @Nonnull
    private Set<VideoMaterialType> getSupportedVideoMaterialTypes() {
        return translateStringSetToEnumSet(VideoMaterialType.class, this.mVideoMaterialTypeSupportSet);
    }

    public boolean shouldCallPrsV2Service(@Nullable PlaybackEnvelope playbackEnvelope, @Nonnull ContentType contentType) {
        return isPrsV2CallEnabled() && getSupportedVideoMaterialTypes().contains(VideoMaterialTypeUtils.fromPlayersContentType(contentType)) && playbackEnvelope != null;
    }

    public boolean isPreparingStateValidationPathEnabled(@Nonnull ContentType contentType) {
        return ContentType.isLive(contentType) && this.mIsPreparingStateValidationPathEnabled.getValue().booleanValue();
    }

    public List<String> getSupportedStreamingTechnologies() {
        return this.mSupportedStreamingTechnologies.getValue();
    }

    @Nonnull
    public String getHdcpLevelForNoHdcpInputDevice() {
        return this.mHdcpLevelForNoHdcpInputDevice.getValue();
    }

    @Nonnull
    public String getDefaultDashBitrateAdaptation() {
        return this.mDefaultDashBitrateAdaptation.getValue();
    }

    @Nonnull
    public String getLiveManifestCapabilities() {
        return this.mLiveManifestCapabilities.getValue();
    }

    public boolean isHighFrameRateEnabled() {
        return this.mIsHFREnabled.getValue().booleanValue();
    }

    @Nonnull
    public Set<Resource> getPRSV2UnsupportedResourceSet() {
        return translateStringSetToEnumSet(Resource.class, this.mPRSV2UnsupportedResourceSet);
    }

    public boolean shouldCallPRSV1ForUnsupportedResources() {
        return this.mShouldCallPRSV1ForUnsupportedResources.getValue().booleanValue();
    }

    public long getPRSV1ExecutorTimeoutMilliseconds() {
        return this.mPRSV1ExecutorTimeoutMilliseconds.getValue().longValue();
    }

    public long getPRSV2ExecutorTimeoutMilliseconds() {
        return this.mPRSV2ExecutorTimeoutMilliseconds.getValue().longValue();
    }

    public boolean isPRSv2ResponseOverrideEnabled() {
        return this.mIsPRSv2ResponseOverrideEnabled.getValue().booleanValue() && !getPRSv2ResponseOverrideFilepath().isEmpty();
    }

    @Nonnull
    public String getPRSv2ResponseOverrideFilepath() {
        return this.mPRSv2ResponseOverrideFilepath.getValue();
    }

    @Nonnull
    public boolean shouldRequestRapidRecapResourcesFromPRS() {
        return this.mShouldRequestRapidRecapResourcesFromPRS.getValue().booleanValue();
    }
}
