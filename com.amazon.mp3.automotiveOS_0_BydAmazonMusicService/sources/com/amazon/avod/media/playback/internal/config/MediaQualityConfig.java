package com.amazon.avod.media.playback.internal.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.ExternalFourCCMapper;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.Preconditions2;
import com.google.android.gms.common.util.GmsVersion;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes2.dex */
public class MediaQualityConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mAtmosSelectionMenuEnabled;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mAudioBitrateCaps;
    private final ConfigurationValue<Integer> mAudioDolbyBitrate;
    private final ConfigurationValue<Integer> mAudioStereoBitrate;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mAv1CachingResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mAv1DownloadResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mAv1StreamingResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mAvcCachingResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mAvcDownloadResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mAvcStreamingResolutionBitrateMap;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Float>> mBitsPerSecondOverheadFactors;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingAv1HDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingAv1SDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingAv1UHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingAvcHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingAvcSDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingAvcUHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingHevcHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingHevcSDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mCachingHevcUHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadAv1HDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadAv1SDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadAv1UHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadAvcHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadAvcSDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadAvcUHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadHevcHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadHevcSDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mDownloadHevcUHDVideoBitrates;
    private final ConfigurationValue<VideoResolution.ResolutionBand> mHd1080pManifestResolutionBandCap;
    private final ConfigurationValue<VideoResolution.ResolutionBand> mHdManifestResolutionBandCap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mHevcCachingResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mHevcDownloadResolutionBitrateMap;
    private final ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> mHevcStreamingResolutionBitrateMap;
    private final ConfigurationValue<Boolean> mIsResolutionBandBasedCappingEnabled;
    private final Map<VideoResolution.ResolutionBand, ConfigurationValue<VideoResolution.ResolutionBand>> mManifestTypeToResolutionBandCapMap;
    private final ConfigurationValue<MediaQuality> mQualityCapPrePlayerBind;
    private final ConfigurationValue<VideoResolution.ResolutionBand> mSdManifestResolutionBandCap;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mSecondaryAudioBitrateCaps;
    private final ConfigurationValue<Boolean> mSecondaryAudioTrackQualityConfigEnabled;
    private final ConfigurationValue<Set<String>> mSessionTypesToCapQualityPrePlayerBind;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingAv1HDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingAv1SDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingAv1UHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingAvcHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingAvcSDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingAvcUHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingHevcHDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingHevcSDVideoBitrates;
    private final ImmutableMap<MediaQuality, ConfigurationValue<Integer>> mStreamingHevcUHDVideoBitrates;
    private final ConfigurationValue<VideoResolution.ResolutionBand> mSubSdManifestResolutionBandCap;
    private final ConfigurationValue<VideoResolution.ResolutionBand> mUltraHdManifestResolutionBandCap;
    public static final MediaQualityConfig INSTANCE = new MediaQualityConfig();
    public static final MediaQuality DEFAULT_MEDIA_QUALITY = MediaQuality.MEDIUM;
    private final ExternalFourCCMapper mExternalFourCCMapper = ExternalFourCCMapper.INSTANCE;
    private final ConfigurationValue<Float> mDataConsumptionOverheadFactor = newFloatConfigValue("DataConsumptionOverheadFactor", 0.1f);

    private MediaQualityConfig() {
        MediaQuality mediaQuality = MediaQuality.LOWEST;
        ConfigurationValue<Float> configurationValueNewFloatConfigValue = newFloatConfigValue("LowestQualityOverheadFactor", 0.2f);
        MediaQuality mediaQuality2 = MediaQuality.LOW;
        ConfigurationValue<Float> configurationValueNewFloatConfigValue2 = newFloatConfigValue("LowQualityOverheadFactor", 0.15f);
        MediaQuality mediaQuality3 = MediaQuality.MEDIUM;
        ConfigurationValue<Float> configurationValueNewFloatConfigValue3 = newFloatConfigValue("MediumQualityOverheadFactor", 0.1f);
        MediaQuality mediaQuality4 = MediaQuality.HIGH;
        this.mBitsPerSecondOverheadFactors = (ImmutableMap) Preconditions2.checkFullKeyMappingWithBlacklist(MediaQuality.class, ImmutableMap.of(mediaQuality, configurationValueNewFloatConfigValue, mediaQuality2, configurationValueNewFloatConfigValue2, mediaQuality3, configurationValueNewFloatConfigValue3, mediaQuality4, newFloatConfigValue("HighQualityOverheadFactor", 0.05f)), ImmutableSet.of(MediaQuality.HIGHEST));
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_SDStreamingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_SDStreamingLowVideoBitrate", 500000)).put(mediaQuality3, newIntConfigValue("playback_SDStreamingMediumVideoBitrate", 800000)).put(mediaQuality4, newIntConfigValue("playback_SDStreamingHighVideoBitrate", 2400000)).build();
        this.mStreamingAvcSDVideoBitrates = immutableMapBuild;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild2 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HDStreamingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_HDStreamingLowVideoBitrate", 500000)).put(mediaQuality3, newIntConfigValue("playback_HDStreamingMediumVideoBitrate", 3000000)).put(mediaQuality4, newIntConfigValue("playback_HDStreamingHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mStreamingAvcHDVideoBitrates = immutableMapBuild2;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild3 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_UHDStreamingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_UHDStreamingLowVideoBitrate", 3000000)).put(mediaQuality3, newIntConfigValue("playback_UHDStreamingMediumVideoBitrate", GmsVersion.VERSION_SAGA)).put(mediaQuality4, newIntConfigValue("playback_UHDStreamingHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mStreamingAvcUHDVideoBitrates = immutableMapBuild3;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild4 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcSDStreamingLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcSDStreamingLowVideoBitrate", 450000)).put(mediaQuality3, newIntConfigValue("playback_HevcSDStreamingMediumVideoBitrate", 650000)).put(mediaQuality4, newIntConfigValue("playback_HevcSDStreamingHighVideoBitrate", DurationKt.NANOS_IN_MILLIS)).build();
        this.mStreamingHevcSDVideoBitrates = immutableMapBuild4;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild5 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcHDStreamingLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcHDStreamingLowVideoBitrate", 450000)).put(mediaQuality3, newIntConfigValue("playback_HevcHDStreamingMediumVideoBitrate", 1950000)).put(mediaQuality4, newIntConfigValue("playback_HevcHDStreamingHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mStreamingHevcHDVideoBitrates = immutableMapBuild5;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild6 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcUHDStreamingLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcUHDStreamingLowVideoBitrate", 1950000)).put(mediaQuality3, newIntConfigValue("playback_HevcUHDStreamingMediumVideoBitrate", GmsVersion.VERSION_LONGHORN)).put(mediaQuality4, newIntConfigValue("playback_HevcUHDStreamingHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mStreamingHevcUHDVideoBitrates = immutableMapBuild6;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild7 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1SDStreamingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1SDStreamingLowVideoBitrate", 180000)).put(mediaQuality3, newIntConfigValue("playback_Av1SDStreamingMediumVideoBitrate", 400000)).put(mediaQuality4, newIntConfigValue("playback_Av1SDStreamingHighVideoBitrate", 800000)).build();
        this.mStreamingAv1SDVideoBitrates = immutableMapBuild7;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild8 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1HDStreamingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1HDStreamingLowVideoBitrate", 400000)).put(mediaQuality3, newIntConfigValue("playback_Av1HDStreamingMediumVideoBitrate", DurationKt.NANOS_IN_MILLIS)).put(mediaQuality4, newIntConfigValue("playback_Av1HDStreamingHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mStreamingAv1HDVideoBitrates = immutableMapBuild8;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild9 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1UHDStreamingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1UHDStreamingLowVideoBitrate", 400000)).put(mediaQuality3, newIntConfigValue("playback_Av1UHDStreamingMediumVideoBitrate", DurationKt.NANOS_IN_MILLIS)).put(mediaQuality4, newIntConfigValue("playback_Av1UHDStreamingHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mStreamingAv1UHDVideoBitrates = immutableMapBuild9;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild10 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_SDDownloadLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_SDDownloadLowVideoBitrate", 500000)).put(mediaQuality3, newIntConfigValue("playback_SDDownloadMediumVideoBitrate", 800000)).put(mediaQuality4, newIntConfigValue("playback_SDDownloadHighVideoBitrate", 2400000)).build();
        this.mDownloadAvcSDVideoBitrates = immutableMapBuild10;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild11 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HDDownloadLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_HDDownloadLowVideoBitrate", 500000)).put(mediaQuality3, newIntConfigValue("playback_HDDownloadMediumVideoBitrate", 3000000)).put(mediaQuality4, newIntConfigValue("playback_HDDownloadHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mDownloadAvcHDVideoBitrates = immutableMapBuild11;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild12 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_UHDDownloadLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_UHDDownloadLowVideoBitrate", 3000000)).put(mediaQuality3, newIntConfigValue("playback_UHDDownloadMediumVideoBitrate", GmsVersion.VERSION_SAGA)).put(mediaQuality4, newIntConfigValue("playback_UHDDownloadHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mDownloadAvcUHDVideoBitrates = immutableMapBuild12;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild13 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcSDDownloadLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcSDDownloadLowVideoBitrate", 450000)).put(mediaQuality3, newIntConfigValue("playback_HevcSDDownloadMediumVideoBitrate", 650000)).put(mediaQuality4, newIntConfigValue("playback_HevcSDDownloadHighVideoBitrate", DurationKt.NANOS_IN_MILLIS)).build();
        this.mDownloadHevcSDVideoBitrates = immutableMapBuild13;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild14 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcHDDownloadLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcHDDownloadLowVideoBitrate", 450000)).put(mediaQuality3, newIntConfigValue("playback_HevcHDDownloadMediumVideoBitrate", 1950000)).put(mediaQuality4, newIntConfigValue("playback_HevcHDDownloadHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mDownloadHevcHDVideoBitrates = immutableMapBuild14;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild15 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcUHDDownloadLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcUHDDownloadLowVideoBitrate", 1950000)).put(mediaQuality3, newIntConfigValue("playback_HevcUHDDownloadMediumVideoBitrate", GmsVersion.VERSION_LONGHORN)).put(mediaQuality4, newIntConfigValue("playback_HevcUHDDownloadHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mDownloadHevcUHDVideoBitrates = immutableMapBuild15;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild16 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1SDDownloadLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1SDDownloadLowVideoBitrate", 180000)).put(mediaQuality3, newIntConfigValue("playback_Av1SDDownloadMediumVideoBitrate", 400000)).put(mediaQuality4, newIntConfigValue("playback_Av1SDDownloadHighVideoBitrate", 800000)).build();
        this.mDownloadAv1SDVideoBitrates = immutableMapBuild16;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild17 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1HDDownloadLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1HDDownloadLowVideoBitrate", 400000)).put(mediaQuality3, newIntConfigValue("playback_Av1HDDownloadMediumVideoBitrate", DurationKt.NANOS_IN_MILLIS)).put(mediaQuality4, newIntConfigValue("playback_Av1HDDownloadHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mDownloadAv1HDVideoBitrates = immutableMapBuild17;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild18 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1UHDDownloadLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1UHDDownloadLowVideoBitrate", 400000)).put(mediaQuality3, newIntConfigValue("playback_Av1UHDDownloadMediumVideoBitrate", DurationKt.NANOS_IN_MILLIS)).put(mediaQuality4, newIntConfigValue("playback_Av1UHDDownloadHighVideoBitrate", Integer.MAX_VALUE)).build();
        this.mDownloadAv1UHDVideoBitrates = immutableMapBuild18;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild19 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_SDCachingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_SDCachingLowVideoBitrate", 500000)).put(mediaQuality3, newIntConfigValue("playback_SDCachingMediumVideoBitrate", 800000)).put(mediaQuality4, newIntConfigValue("playback_SDCachingHighVideoBitrate", 800000)).build();
        this.mCachingAvcSDVideoBitrates = immutableMapBuild19;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild20 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HDCachingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_HDCachingLowVideoBitrate", 500000)).put(mediaQuality3, newIntConfigValue("playback_HDCachingMediumVideoBitrate", 3000000)).put(mediaQuality4, newIntConfigValue("playback_HDCachingHighVideoBitrate", 3000000)).build();
        this.mCachingAvcHDVideoBitrates = immutableMapBuild20;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild21 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_UHDCachingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_UHDCachingLowVideoBitrate", 3000000)).put(mediaQuality3, newIntConfigValue("playback_UHDCachingMediumVideoBitrate", 3000000)).put(mediaQuality4, newIntConfigValue("playback_UHDCachingHighVideoBitrate", 3000000)).build();
        this.mCachingAvcUHDVideoBitrates = immutableMapBuild21;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild22 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcSDCachingLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcSDCachingLowVideoBitrate", 450000)).put(mediaQuality3, newIntConfigValue("playback_HevcSDCachingMediumVideoBitrate", 650000)).put(mediaQuality4, newIntConfigValue("playback_HevcSDCachingHighVideoBitrate", 650000)).build();
        this.mCachingHevcSDVideoBitrates = immutableMapBuild22;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild23 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcHDCachingLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcHDCachingLowVideoBitrate", 450000)).put(mediaQuality3, newIntConfigValue("playback_HevcHDCachingMediumVideoBitrate", 1950000)).put(mediaQuality4, newIntConfigValue("playback_HevcHDCachingHighVideoBitrate", 1950000)).build();
        this.mCachingHevcHDVideoBitrates = immutableMapBuild23;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild24 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_HevcUHDCachingLowestVideoBitrate", 120000)).put(mediaQuality2, newIntConfigValue("playback_HevcUHDCachingLowVideoBitrate", 1950000)).put(mediaQuality3, newIntConfigValue("playback_HevcUHDCachingMediumVideoBitrate", 1950000)).put(mediaQuality4, newIntConfigValue("playback_HevcUHDCachingHighVideoBitrate", 1950000)).build();
        this.mCachingHevcUHDVideoBitrates = immutableMapBuild24;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild25 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1SDCachingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1SDCachingLowVideoBitrate", 180000)).put(mediaQuality3, newIntConfigValue("playback_Av1SDCachingMediumVideoBitrate", 400000)).put(mediaQuality4, newIntConfigValue("playback_Av1SDCachingHighVideoBitrate", 400000)).build();
        this.mCachingAv1SDVideoBitrates = immutableMapBuild25;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild26 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1HDCachingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1HDCachingLowVideoBitrate", 400000)).put(mediaQuality3, newIntConfigValue("playback_Av1HDCachingMediumVideoBitrate", DurationKt.NANOS_IN_MILLIS)).put(mediaQuality4, newIntConfigValue("playback_Av1HDCachingHighVideoBitrate", DurationKt.NANOS_IN_MILLIS)).build();
        this.mCachingAv1HDVideoBitrates = immutableMapBuild26;
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMapBuild27 = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_Av1UHDCachingLowestVideoBitrate", 100000)).put(mediaQuality2, newIntConfigValue("playback_Av1UHDCachingLowVideoBitrate", 400000)).put(mediaQuality3, newIntConfigValue("playback_Av1UHDCachingMediumVideoBitrate", DurationKt.NANOS_IN_MILLIS)).put(mediaQuality4, newIntConfigValue("playback_Av1UHDCachingHighVideoBitrate", DurationKt.NANOS_IN_MILLIS)).build();
        this.mCachingAv1UHDVideoBitrates = immutableMapBuild27;
        this.mAudioBitrateCaps = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_LowestAudioBitrateCap", 256000)).put(mediaQuality2, newIntConfigValue("playback_LowAudioBitrateCap", 256000)).put(mediaQuality3, newIntConfigValue("playback_MediumAudioBitrateCap", 256000)).put(mediaQuality4, newIntConfigValue("playback_HighAudioBitrateCap", 448000)).build();
        this.mSecondaryAudioBitrateCaps = ImmutableMap.builder().put(mediaQuality, newIntConfigValue("playback_LowestSecondaryAudioBitrateCap", 64000)).put(mediaQuality2, newIntConfigValue("playback_LowSecondaryAudioBitrateCap", 64000)).put(mediaQuality3, newIntConfigValue("playback_MediumSecondaryAudioBitrateCap", 64000)).put(mediaQuality4, newIntConfigValue("playback_HighSecondaryAudioBitrateCap", 448000)).build();
        ImmutableMap.Builder builder = ImmutableMap.builder();
        VideoResolution.ResolutionBand resolutionBand = VideoResolution.ResolutionBand.SD;
        ImmutableMap.Builder builderPut = builder.put(resolutionBand, immutableMapBuild);
        VideoResolution.ResolutionBand resolutionBand2 = VideoResolution.ResolutionBand.HD;
        ImmutableMap.Builder builderPut2 = builderPut.put(resolutionBand2, immutableMapBuild2);
        VideoResolution.ResolutionBand resolutionBand3 = VideoResolution.ResolutionBand.HD_1080P;
        ImmutableMap.Builder builderPut3 = builderPut2.put(resolutionBand3, immutableMapBuild2);
        VideoResolution.ResolutionBand resolutionBand4 = VideoResolution.ResolutionBand.ULTRA_HD;
        this.mAvcStreamingResolutionBitrateMap = builderPut3.put(resolutionBand4, immutableMapBuild3).build();
        this.mHevcStreamingResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild4).put(resolutionBand2, immutableMapBuild5).put(resolutionBand3, immutableMapBuild5).put(resolutionBand4, immutableMapBuild6).build();
        this.mAv1StreamingResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild7).put(resolutionBand2, immutableMapBuild8).put(resolutionBand3, immutableMapBuild8).put(resolutionBand4, immutableMapBuild9).build();
        this.mAvcCachingResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild19).put(resolutionBand2, immutableMapBuild20).put(resolutionBand3, immutableMapBuild20).put(resolutionBand4, immutableMapBuild21).build();
        this.mHevcCachingResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild22).put(resolutionBand2, immutableMapBuild23).put(resolutionBand3, immutableMapBuild23).put(resolutionBand4, immutableMapBuild24).build();
        this.mAv1CachingResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild25).put(resolutionBand2, immutableMapBuild26).put(resolutionBand3, immutableMapBuild26).put(resolutionBand4, immutableMapBuild27).build();
        this.mAvcDownloadResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild10).put(resolutionBand2, immutableMapBuild11).put(resolutionBand3, immutableMapBuild11).put(resolutionBand4, immutableMapBuild12).build();
        this.mHevcDownloadResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild13).put(resolutionBand2, immutableMapBuild14).put(resolutionBand3, immutableMapBuild14).put(resolutionBand4, immutableMapBuild15).build();
        this.mAv1DownloadResolutionBitrateMap = ImmutableMap.builder().put(resolutionBand, immutableMapBuild16).put(resolutionBand2, immutableMapBuild17).put(resolutionBand3, immutableMapBuild17).put(resolutionBand4, immutableMapBuild18).build();
        this.mAudioStereoBitrate = newIntConfigValue("playback_audioStereoBitrate", 128000);
        this.mAudioDolbyBitrate = newIntConfigValue("playback_audioDolbyBitrate", 256000);
        this.mAtmosSelectionMenuEnabled = newBooleanConfigValue("playback_atmosSelectionMenuEnabled", true);
        this.mSecondaryAudioTrackQualityConfigEnabled = newBooleanConfigValue("playback_secondaryAudioTrackQualityConfigEnabled", true);
        this.mQualityCapPrePlayerBind = newEnumConfigValue("playback_qualityCapPrePlayerBind", mediaQuality3, MediaQuality.class);
        this.mSessionTypesToCapQualityPrePlayerBind = newSemicolonDelimitedStringSetConfigurationValue("playback_sessionTypesToCapQualityPrePlayerBind", new String[]{ContentSessionType.STREAMING.name(), ContentSessionType.STREAMING_CONTINUOUS.name(), ContentSessionType.LIVE_CACHE.name()});
        this.mIsResolutionBandBasedCappingEnabled = newBooleanConfigValue("playback_isResolutionBandBasedCappingEnabled", true);
        ConfigurationValue<VideoResolution.ResolutionBand> configurationValueNewEnumConfigValue = newEnumConfigValue("playback_subSdManifestResolutionBandCap", resolutionBand, VideoResolution.ResolutionBand.class);
        this.mSubSdManifestResolutionBandCap = configurationValueNewEnumConfigValue;
        ConfigurationValue<VideoResolution.ResolutionBand> configurationValueNewEnumConfigValue2 = newEnumConfigValue("playback_sdManifestResolutionBandCap", resolutionBand, VideoResolution.ResolutionBand.class);
        this.mSdManifestResolutionBandCap = configurationValueNewEnumConfigValue2;
        ConfigurationValue<VideoResolution.ResolutionBand> configurationValueNewEnumConfigValue3 = newEnumConfigValue("playback_hdManifestResolutionBandCap", resolutionBand2, VideoResolution.ResolutionBand.class);
        this.mHdManifestResolutionBandCap = configurationValueNewEnumConfigValue3;
        ConfigurationValue<VideoResolution.ResolutionBand> configurationValueNewEnumConfigValue4 = newEnumConfigValue("playback_hd1080pManifestResolutionBandCap", resolutionBand2, VideoResolution.ResolutionBand.class);
        this.mHd1080pManifestResolutionBandCap = configurationValueNewEnumConfigValue4;
        ConfigurationValue<VideoResolution.ResolutionBand> configurationValueNewEnumConfigValue5 = newEnumConfigValue("playback_ultraHdManifestResolutionBandCap", resolutionBand2, VideoResolution.ResolutionBand.class);
        this.mUltraHdManifestResolutionBandCap = configurationValueNewEnumConfigValue5;
        this.mManifestTypeToResolutionBandCapMap = ImmutableMap.builder().put(VideoResolution.ResolutionBand.SUB_SD, configurationValueNewEnumConfigValue).put(resolutionBand, configurationValueNewEnumConfigValue2).put(resolutionBand2, configurationValueNewEnumConfigValue3).put(resolutionBand3, configurationValueNewEnumConfigValue4).put(resolutionBand4, configurationValueNewEnumConfigValue5).build();
    }

    @Nonnull
    public MediaQuality getQualityCapPrePlayerBind() {
        return this.mQualityCapPrePlayerBind.getValue();
    }

    @Nonnull
    public Set<String> getSessionTypesToCapQualityPrePlayerBind() {
        return this.mSessionTypesToCapQualityPrePlayerBind.getValue();
    }

    public boolean isResolutionBandBasedCappingEnabled() {
        return this.mIsResolutionBandBasedCappingEnabled.getValue().booleanValue();
    }

    @Nonnull
    public VideoResolution.ResolutionBand getResolutionBandCapForManifestType(@Nonnull VideoResolution.ResolutionBand resolutionBand) {
        ConfigurationValue<VideoResolution.ResolutionBand> configurationValue = this.mManifestTypeToResolutionBandCapMap.get(resolutionBand);
        if (configurationValue != null) {
            return configurationValue.getValue();
        }
        return VideoResolution.ResolutionBand.UNKNOWN;
    }

    public float getDataConsumptionOverheadFactor() {
        return this.mDataConsumptionOverheadFactor.getValue().floatValue();
    }

    public int getVideoStreamingBitrateCap(@Nullable MediaQuality mediaQuality, @Nullable VideoResolution videoResolution, @Nonnull String str) {
        ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> immutableMap;
        if (mediaQuality == null || mediaQuality == MediaQuality.HIGHEST || videoResolution == null) {
            return Integer.MAX_VALUE;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$VideoStreamType[getVideoStreamType(str).ordinal()];
        if (i == 1) {
            immutableMap = this.mAv1StreamingResolutionBitrateMap;
        } else if (i == 2 || i == 3) {
            immutableMap = this.mHevcStreamingResolutionBitrateMap;
        } else {
            immutableMap = this.mAvcStreamingResolutionBitrateMap;
        }
        return getBitrate(mediaQuality, getResolutionBitrateMap(videoResolution.getResolutionBand(), immutableMap));
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.internal.config.MediaQualityConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$VideoStreamType;

        static {
            int[] iArr = new int[VideoStreamType.values().length];
            $SwitchMap$com$amazon$avod$media$VideoStreamType = iArr;
            try {
                iArr[VideoStreamType.AV01.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoStreamType[VideoStreamType.H265.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoStreamType[VideoStreamType.DVHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoStreamType[VideoStreamType.H264.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int getAudioBitrateCap(@Nonnull MediaQuality mediaQuality) {
        return getBitrate((MediaQuality) Preconditions.checkNotNull(mediaQuality, "quality"), this.mAudioBitrateCaps);
    }

    public int getSecondaryAudioBitrateCap(@Nonnull MediaQuality mediaQuality) {
        return getBitrate((MediaQuality) Preconditions.checkNotNull(mediaQuality, "quality"), this.mSecondaryAudioBitrateCaps);
    }

    public int getVideoDownloadBitrateCap(@Nullable MediaQuality mediaQuality, @Nullable VideoResolution videoResolution, @Nonnull String str) {
        ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> immutableMap;
        if (mediaQuality == null || mediaQuality == MediaQuality.HIGHEST || videoResolution == null) {
            return Integer.MAX_VALUE;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$VideoStreamType[getVideoStreamType(str).ordinal()];
        if (i == 1) {
            immutableMap = this.mAv1DownloadResolutionBitrateMap;
        } else if (i == 2 || i == 3) {
            immutableMap = this.mHevcDownloadResolutionBitrateMap;
        } else {
            immutableMap = this.mAvcDownloadResolutionBitrateMap;
        }
        return getBitrate(mediaQuality, getResolutionBitrateMap(videoResolution.getResolutionBand(), immutableMap));
    }

    public int getVideoCachingBitrateCap(@Nullable MediaQuality mediaQuality, @Nullable VideoResolution videoResolution, @Nonnull String str) {
        ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> immutableMap;
        if (mediaQuality == null || mediaQuality == MediaQuality.HIGHEST || videoResolution == null) {
            return Integer.MAX_VALUE;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$VideoStreamType[getVideoStreamType(str).ordinal()];
        if (i == 1) {
            immutableMap = this.mAv1CachingResolutionBitrateMap;
        } else if (i == 2 || i == 3) {
            immutableMap = this.mHevcCachingResolutionBitrateMap;
        } else {
            immutableMap = this.mAvcCachingResolutionBitrateMap;
        }
        return getBitrate(mediaQuality, getResolutionBitrateMap(videoResolution.getResolutionBand(), immutableMap));
    }

    public boolean getSecondaryAudioTrackQualityConfigEnabled() {
        return this.mSecondaryAudioTrackQualityConfigEnabled.getValue().booleanValue();
    }

    private int getBitrate(MediaQuality mediaQuality, ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMap) {
        ConfigurationValue<Integer> configurationValue = immutableMap.get(mediaQuality);
        if (configurationValue == null) {
            configurationValue = immutableMap.get(MediaQuality.LOW);
        }
        return configurationValue.getValue().intValue();
    }

    private ImmutableMap<MediaQuality, ConfigurationValue<Integer>> getResolutionBitrateMap(VideoResolution.ResolutionBand resolutionBand, ImmutableMap<VideoResolution.ResolutionBand, ImmutableMap<MediaQuality, ConfigurationValue<Integer>>> immutableMap) {
        ImmutableMap<MediaQuality, ConfigurationValue<Integer>> immutableMap2 = immutableMap.get(resolutionBand);
        return immutableMap2 == null ? immutableMap.get(VideoResolution.ResolutionBand.SD) : immutableMap2;
    }

    @Nonnull
    private VideoStreamType getVideoStreamType(@Nonnull String str) {
        try {
            VideoStreamType videoStreamTypeMapVideoCodecToStreamType = this.mExternalFourCCMapper.mapVideoCodecToStreamType(str);
            return videoStreamTypeMapVideoCodecToStreamType == null ? VideoStreamType.H264 : videoStreamTypeMapVideoCodecToStreamType;
        } catch (IllegalArgumentException unused) {
            return VideoStreamType.H264;
        }
    }
}
