package com.amazon.avod.media.playback.support;

import android.content.pm.PackageManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.MediaCodecEnumerator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.render.AudioOutputSettings;
import com.amazon.avod.media.playback.render.ReceiverAudioCapabilities;
import com.amazon.avod.media.playback.support.MediaCodecInternalConfig;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaCodecDeviceCapabilityDetector extends DefaultDeviceCapabilityDetector {
    private final AudioOutputSettings mAudioOutputSettings;
    private final DrmFramework mDrmFramework;
    private final MediaCodecInternalConfig mMediaCodecInternalConfig;
    private final MediaCodecList mMediaCodecList;
    private final MediaSystemSharedContext mSharedContext;
    private ImmutableList<PlaybackResourceServiceConstants$HdrFormat> mSupportedHdrFormats;

    public MediaCodecDeviceCapabilityDetector(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull DrmFramework drmFramework) {
        this(mediaSystemSharedContext, MediaDefaultConfiguration.getInstance(), MediaCodecEnumerator.getInstance(), drmFramework, PlaybackConfig.getInstance(), MediaCodecInternalConfig.getInstance(), new MediaCodecList(1), RendererSchemeResolverConfig.getInstance(), AudioOutputSettings.getInstance());
    }

    MediaCodecDeviceCapabilityDetector(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaCodecEnumerator mediaCodecEnumerator, @Nonnull DrmFramework drmFramework, @Nonnull PlaybackConfig playbackConfig, @Nonnull MediaCodecInternalConfig mediaCodecInternalConfig, @Nonnull MediaCodecList mediaCodecList, @Nonnull RendererSchemeResolverConfig rendererSchemeResolverConfig, @Nonnull AudioOutputSettings audioOutputSettings) {
        super(mediaSystemSharedContext, mediaDefaultConfiguration, mediaCodecEnumerator, playbackConfig, rendererSchemeResolverConfig);
        this.mSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        this.mDrmFramework = (DrmFramework) Preconditions.checkNotNull(drmFramework, "drmFramework");
        this.mMediaCodecInternalConfig = (MediaCodecInternalConfig) Preconditions.checkNotNull(mediaCodecInternalConfig, "mediaCodecInternalConfig");
        this.mMediaCodecList = (MediaCodecList) Preconditions.checkNotNull(mediaCodecList, "mediaCodecList");
        this.mAudioOutputSettings = (AudioOutputSettings) Preconditions.checkNotNull(audioOutputSettings, "audioOutputSettings");
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public List<String> getSupportedAudioCodecs() {
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll((Iterable) this.mMediaConfig.getMediaCodecSupportedAudioCodecs());
        if (isDolbyDigitalPlusSupported()) {
            builder.add(AudioStreamType.DDP.getFourCC());
        }
        if (isDolbyDigitalPlusWithAtmosSupported()) {
            builder.add(AudioStreamType.ATMOS.getFourCC());
        }
        return builder.build();
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isUhdSupported() {
        return isHdSupported() && isDisplayUhdCapable() && DisplayModeAndToneMappingEvaluator.isDecoderUhdCapable();
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHdrSupported() {
        return this.mMediaConfig.getOverrideHDRDeviceCapability() || detectHdrCapabilityUsingPackages() || (!DisplayModeAndToneMappingEvaluator.getSupportedHdrFormats(this.mSharedContext.getAppContext()).isEmpty() && isHdSupported());
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    @Nonnull
    public ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats() {
        if (this.mSupportedHdrFormats == null) {
            if (this.mMediaConfig.getOverrideHDRDeviceCapability() || detectHdrCapabilityUsingPackages()) {
                this.mSupportedHdrFormats = ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.Hdr10);
            } else {
                ImmutableList<PlaybackResourceServiceConstants$HdrFormat> supportedHdrFormats = DisplayModeAndToneMappingEvaluator.getSupportedHdrFormats(this.mSharedContext.getAppContext());
                if (supportedHdrFormats.isEmpty()) {
                    this.mSupportedHdrFormats = ImmutableList.of(PlaybackResourceServiceConstants$HdrFormat.None);
                } else {
                    this.mSupportedHdrFormats = supportedHdrFormats;
                }
            }
        }
        return this.mSupportedHdrFormats;
    }

    private boolean detectHdrCapabilityUsingPackages() {
        PackageManager packageManager = this.mSharedContext.getAppContext().getPackageManager();
        if (packageManager != null) {
            Iterator<String> it = this.mMediaConfig.getHdrFeatureNameList().iterator();
            while (it.hasNext()) {
                if (packageManager.hasSystemFeature(it.next())) {
                    return true;
                }
            }
            return false;
        }
        DLog.warnf("packageManager is null.");
        return false;
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isDolbyDigitalPlusSupported() {
        if (!isDolbyOverBluetoothSupported()) {
            return false;
        }
        if (this.mCodecEnumerator.getSupportedCodec(this.mMediaConfig.getDolbyDigitalPlusMimeTypes()) != null) {
            return true;
        }
        return isDolbyPassthroughSupported();
    }

    public boolean isDolbyOverBluetoothSupported() {
        if (this.mMediaConfig.isDolbyOverBluetoothAudioOutputSupported()) {
            return true;
        }
        return !isBluetoothAudioOutputDeviceConnected(Build.VERSION.SDK_INT);
    }

    public boolean isDolbyPassthroughSupported() {
        if (this.mAudioOutputSettings.isOpticalOutputEnabled()) {
            return this.mMediaConfig.isOpticalAudioPassthroughEnabled();
        }
        return this.mMediaConfig.isHDMIAudioPassthroughEnabled() && ReceiverAudioCapabilities.getCapabilities(this.mSharedContext.getAppContext()).supportsEncoding(6);
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isDolbyDigitalPlusWithAtmosSupported() {
        return isDolbyDigitalPlusSupported();
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isHdSupported() {
        DrmSecurityLevel drmSecurityLevel = this.mDrmFramework.getDrmSecurityLevel();
        return drmSecurityLevel == DrmSecurityLevel.LEVEL_1 || drmSecurityLevel == DrmSecurityLevel.SL_2000 || drmSecurityLevel == DrmSecurityLevel.SL_3000;
    }

    public boolean getDeviceTunnelModeCapability(int i, @Nonnull String str) {
        Preconditions.checkNotNull(str, "mimeType");
        if (i >= 21) {
            if (this.mMediaCodecInternalConfig.shouldAnalyzeDeviceTunnelPlaybackSupport(str)) {
                try {
                    String strLookupSecureDecoderWithMimeType = DisplayModeAndToneMappingEvaluator.lookupSecureDecoderWithMimeType(Build.VERSION.SDK_INT, this.mMediaCodecList, str);
                    MediaCodecInfo[] codecInfos = this.mMediaCodecList.getCodecInfos();
                    if (strLookupSecureDecoderWithMimeType != null && codecInfos != null) {
                        for (MediaCodecInfo mediaCodecInfo : codecInfos) {
                            if (strLookupSecureDecoderWithMimeType.equals(mediaCodecInfo.getName())) {
                                boolean zIsFeatureSupported = mediaCodecInfo.getCapabilitiesForType(str).isFeatureSupported("tunneled-playback");
                                this.mMediaCodecInternalConfig.updateCodecTunnelPlaybackSupport(str, zIsFeatureSupported ? MediaCodecInternalConfig.CodecSupport.ANALYZED_TRUE : MediaCodecInternalConfig.CodecSupport.ANALYZED_FALSE);
                                DLog.warnf("Analyzed tunnel mode support, mime: %s -> %s", str, Boolean.valueOf(zIsFeatureSupported));
                                return zIsFeatureSupported;
                            }
                        }
                        DLog.warnf("couldn't get mediaCodecInfo for given decoder, unable to detect tunnel mode support, will retry");
                    } else {
                        Object[] objArr = new Object[2];
                        String str2 = "null";
                        objArr[0] = strLookupSecureDecoderWithMimeType == null ? "null" : "non-null";
                        if (codecInfos != null) {
                            str2 = "non-null";
                        }
                        objArr[1] = str2;
                        DLog.warnf("Unable to detect tunnel mode support, secureDecoderName %s, allMediaCodecInfo %s, will retry", objArr);
                    }
                } catch (MediaCodec.CodecException | IllegalArgumentException e) {
                    DLog.exceptionf(e, "Unable to detect tunnel mode support, mime: %s,", str);
                }
            } else {
                boolean zIsTunneledPlaybackSupported = this.mMediaCodecInternalConfig.isTunneledPlaybackSupported(str);
                DLog.warnf("Cached tunnel mode support, mime: %s -> %s", str, Boolean.valueOf(zIsTunneledPlaybackSupported));
                return zIsTunneledPlaybackSupported;
            }
        }
        return false;
    }

    public boolean isBluetoothAudioOutputDeviceConnected(int i) {
        AudioDeviceInfo[] connectedOutputDevices = getConnectedOutputDevices(i);
        if (connectedOutputDevices != null && i >= 23) {
            for (AudioDeviceInfo audioDeviceInfo : connectedOutputDevices) {
                if (audioDeviceInfo.getType() == 8 || audioDeviceInfo.getType() == 23) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    AudioDeviceInfo[] getConnectedOutputDevices(int i) {
        AudioManager audioManager = (AudioManager) this.mSharedContext.getAppContext().getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
        if (audioManager == null || i < 23) {
            return null;
        }
        return audioManager.getDevices(2);
    }

    @Override // com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector, com.amazon.avod.media.playback.support.DeviceCapabilityDetector
    public boolean isMultiKeyDecryptionSupported(@Nonnull ContentType contentType) {
        return !ContentType.isLive(contentType) && this.mDrmFramework.getCurrentDrmScheme().equals(DrmScheme.WIDEVINE) && this.mMediaConfig.isWidevineMultiKeyDecryptionEnabled();
    }
}
