package com.amazon.avod.media.service.prsv2;

import android.os.Build;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$VideoFrameRate;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$VideoQuality;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackUrlsCommonParams {
    private static final Map<HdcpLevelProvider.HdcpLevel, String> HDCP_LEVEL_MAP = buildHdcpLevelMap();
    private final AVODServiceConfig mAVODServiceConfig;
    private final ContentType mContentType;
    private final DeviceIdentity mDeviceIdentity;
    private final DrmFramework mDrmFramework;
    private final HdcpLevelProvider mHdcpLevelProvider;
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private final RendererScheme mRendererScheme;
    private final RendererSchemeType mRendererSchemeType;
    private final boolean mSupportsEmbeddedTrickplay;

    public PlaybackUrlsCommonParams(@Nonnull DeviceIdentity deviceIdentity, @Nonnull HdcpLevelProvider hdcpLevelProvider, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull RendererScheme rendererScheme, @Nonnull ContentType contentType, @Nonnull AVODServiceConfig aVODServiceConfig) {
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mHdcpLevelProvider = (HdcpLevelProvider) Preconditions.checkNotNull(hdcpLevelProvider, "hdcpLevelProvider");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        RendererScheme rendererScheme2 = (RendererScheme) Preconditions.checkNotNull(rendererScheme, "rendererScheme");
        this.mRendererScheme = rendererScheme2;
        this.mRendererSchemeType = rendererScheme2.getSchemeType();
        this.mDrmFramework = rendererScheme2.getDrmFramework();
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
        this.mAVODServiceConfig = (AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig, "avodServiceConfig");
        this.mSupportsEmbeddedTrickplay = aVODServiceConfig.supportsEmbeddedTrickplay();
    }

    @Nonnull
    public String getDeviceCategory() {
        this.mDeviceIdentity.waitOnInitialized();
        return this.mDeviceIdentity.isThirdParty() ? "Phone" : this.mDeviceIdentity.getAppVersionName().startsWith("FireTv") ? "Room" : this.mDeviceIdentity.getAppVersionName().startsWith("FireTablet") ? "Tablet" : "Other";
    }

    @Nonnull
    public String getFirmware() {
        return "fmw:" + Build.VERSION.SDK + "-app:" + this.mDeviceIdentity.getAppVersionName();
    }

    @Nonnull
    public String getHdcpLevel() {
        return HDCP_LEVEL_MAP.get(this.mHdcpLevelProvider.getCurrentHdcpLevel());
    }

    @Nonnull
    public static Map<HdcpLevelProvider.HdcpLevel, String> buildHdcpLevelMap() {
        String hdcpLevelForNoHdcpInputDevice = PlaybackResourcesV2Config.getInstance().getHdcpLevelForNoHdcpInputDevice();
        return (ImmutableMap) Preconditions2.checkFullKeyMapping(HdcpLevelProvider.HdcpLevel.class, new ImmutableMap.Builder().put(HdcpLevelProvider.HdcpLevel.NO_HDCP_SUPPORT, hdcpLevelForNoHdcpInputDevice).put(HdcpLevelProvider.HdcpLevel.LOCAL_DISPLAY, hdcpLevelForNoHdcpInputDevice).put(HdcpLevelProvider.HdcpLevel.HDCP_1_0, "1.1").put(HdcpLevelProvider.HdcpLevel.HDCP_2_0, "2.0").put(HdcpLevelProvider.HdcpLevel.HDCP_2_1, "2.1").put(HdcpLevelProvider.HdcpLevel.HDCP_2_2, "2.2").build());
    }

    @Nonnull
    public String getOperatingSystem() {
        return "Android" + Build.VERSION.RELEASE;
    }

    @Nonnull
    public List<String> getLiveManifestTypes() {
        return Arrays.asList(this.mPlaybackResourcesV2Config.getLiveManifestCapabilities().split(","));
    }

    @Nonnull
    public String getMaxPlaybackResolution() {
        return this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType) ? "2160p" : this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType) ? "1080p" : "576p";
    }

    @Nonnull
    public List<String> getDashBitrateAdaptations() {
        List<String> deviceBitrateAdaptationsOverride = this.mPlaybackSupportEvaluator.getDeviceBitrateAdaptationsOverride(this.mRendererSchemeType);
        if (deviceBitrateAdaptationsOverride.isEmpty()) {
            deviceBitrateAdaptationsOverride.add(this.mPlaybackResourcesV2Config.getDefaultDashBitrateAdaptation());
        }
        return deviceBitrateAdaptationsOverride;
    }

    @Nonnull
    public List<String> getCodecs() {
        return this.mPlaybackSupportEvaluator.getDeviceVideoCodecOverride(this.mRendererSchemeType);
    }

    @Nonnull
    public String getDrmKeyScheme() {
        return this.mPlaybackSupportEvaluator.isMultiKeyDecryptionSupported(this.mRendererSchemeType, this.mContentType) ? "DualKey" : "SingleKey";
    }

    @Nonnull
    public String getDrmStrength(@Nonnull String str) {
        Preconditions.checkNotNull(str, "capVideoDefinition");
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[this.mDrmFramework.getDrmSecurityLevel().ordinal()];
        return (i == 4 || i == 5 || i == 6) ? PlaybackResourceServiceConstants$VideoQuality.UHD.name().equals(str) ? "L40" : "L30" : "L10";
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.service.prsv2.PlaybackUrlsCommonParams$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel;

        static {
            int[] iArr = new int[DrmSecurityLevel.values().length];
            $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel = iArr;
            try {
                iArr[DrmSecurityLevel.SL_150.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.LEVEL_3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.LEVEL_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.LEVEL_1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.SL_2000.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$drm$DrmSecurityLevel[DrmSecurityLevel.SL_3000.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Nonnull
    public String getDrmType() {
        return this.mDrmFramework.getCurrentDrmScheme().name();
    }

    @Nonnull
    public String getCapVideoDefinition() {
        if (this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType)) {
            return PlaybackResourceServiceConstants$VideoQuality.UHD.name();
        }
        if (this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType)) {
            return PlaybackResourceServiceConstants$VideoQuality.HD.name();
        }
        return PlaybackResourceServiceConstants$VideoQuality.SD.name();
    }

    @Nonnull
    public List<String> getThumbnailRepresentations() {
        ArrayList arrayList = new ArrayList();
        if (this.mSupportsEmbeddedTrickplay) {
            arrayList.add("InManifest");
        } else {
            arrayList.add("None");
        }
        return arrayList;
    }

    @Nonnull
    public List<String> getFragmentRepresentations() {
        return Arrays.asList("ByteOffsetRange", "SeparateFile");
    }

    @Nonnull
    public List<PlaybackResourceServiceConstants$HdrFormat> getDynamicRangeFormats() {
        if (this.mPlaybackSupportEvaluator.isHdrSupported(this.mRendererSchemeType)) {
            return this.mPlaybackSupportEvaluator.getSupportedHdrFormats(this.mRendererSchemeType);
        }
        return ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.None);
    }

    @Nonnull
    public List<String> getFrameRates() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PlaybackResourceServiceConstants$VideoFrameRate.Standard.toString());
        if ((this.mAVODServiceConfig.isVideoFrameRateOverrideEnabled() || this.mPlaybackResourcesV2Config.isHighFrameRateEnabled()) && (this.mPlaybackSupportEvaluator.isUhdSupported(this.mRendererSchemeType) || this.mPlaybackSupportEvaluator.isHdrSupported(this.mRendererSchemeType) || this.mPlaybackSupportEvaluator.isHdSupported(this.mRendererSchemeType))) {
            arrayList.add(PlaybackResourceServiceConstants$VideoFrameRate.High.toString());
        }
        return arrayList;
    }
}
