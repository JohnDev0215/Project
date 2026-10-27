package com.amazon.avod.playback.renderer.mediacodec;

import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.MediaCodecEnumerator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.visualon.OSMPUtils.voMimeTypes;
import java.nio.ByteBuffer;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class MediaFormatFactory {
    private static final String CSD_0 = "csd-0";
    private static final String CSD_1 = "csd-1";
    private final MediaCodecDeviceCapabilityDetector mCapabilityDetector;
    private final MediaCodecEnumerator mCodecEnumerator;
    private final MediaDefaultConfiguration mMediaConfig;
    private final PlaybackConfig mPlaybackConfig;

    public MediaFormatFactory(@Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        this(mediaCodecDeviceCapabilityDetector, PlaybackConfig.getInstance(), MediaDefaultConfiguration.getInstance(), MediaCodecEnumerator.getInstance());
    }

    @VisibleForTesting
    MediaFormatFactory(@Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, @Nonnull PlaybackConfig playbackConfig, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaCodecEnumerator mediaCodecEnumerator) {
        this.mCapabilityDetector = (MediaCodecDeviceCapabilityDetector) Preconditions.checkNotNull(mediaCodecDeviceCapabilityDetector, "capabilityDetector");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mMediaConfig = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaConfig");
        this.mCodecEnumerator = (MediaCodecEnumerator) Preconditions.checkNotNull(mediaCodecEnumerator, "codecEnumerator");
    }

    @Nonnull
    public MediaFormat newVideoFormat(@Nonnull VideoConfig videoConfig) throws PlaybackException {
        Preconditions.checkNotNull(videoConfig, "videoConfig");
        String supportedMimeType = getSupportedMimeType(videoConfig.getFourCC());
        VideoResolution videoResolution = videoConfig.getVideoResolution();
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(supportedMimeType, videoResolution.getWidth(), videoResolution.getHeight());
        VideoResolution maxVideoResolution = videoConfig.getMaxVideoResolution();
        mediaFormatCreateVideoFormat.setInteger("max-width", maxVideoResolution.getWidth());
        mediaFormatCreateVideoFormat.setInteger("max-height", maxVideoResolution.getHeight());
        setCodecSpecificData(mediaFormatCreateVideoFormat, videoConfig.getSampleCodecData());
        setMaxInputSize(mediaFormatCreateVideoFormat);
        if (videoConfig.isHdrEnabled()) {
            mediaFormatCreateVideoFormat.setString("HDR-ENABLE", "AMAZON");
        }
        return mediaFormatCreateVideoFormat;
    }

    private void setCodecSpecificData(@Nonnull MediaFormat mediaFormat, @Nullable SampleCodecData sampleCodecData) {
        String string;
        if (this.mPlaybackConfig.isAmazonDevice() || sampleCodecData == null) {
            return;
        }
        string = mediaFormat.getString("mime");
        string.hashCode();
        switch (string) {
            case "video/dolby-vision":
            case "video/hevc":
                mediaFormat.setByteBuffer(CSD_0, ByteBuffer.wrap(sampleCodecData.getRawBytes()));
                return;
            case "video/av01":
                return;
            case "video/avc":
                mediaFormat.setByteBuffer(CSD_0, ByteBuffer.wrap(sampleCodecData.getSpsBytes()));
                mediaFormat.setByteBuffer(CSD_1, ByteBuffer.wrap(sampleCodecData.getPpsBytes()));
                return;
            default:
                throw new UnsupportedOperationException("setCodecSpecificData mime type unexpected: " + string);
        }
    }

    private void setMaxInputSize(@Nonnull MediaFormat mediaFormat) {
        int integer;
        int integer2;
        String string;
        int minCompressionRatioHEVC;
        integer = mediaFormat.getInteger("max-width");
        integer2 = mediaFormat.getInteger("max-height");
        string = mediaFormat.getString("mime");
        string.hashCode();
        switch (string) {
            case "video/dolby-vision":
            case "video/hevc":
                minCompressionRatioHEVC = ((integer * integer2) * 3) / (this.mMediaConfig.getMinCompressionRatioHEVC() * 2);
                break;
            case "video/av01":
                minCompressionRatioHEVC = (((integer * integer2) * 15) >> 3) / this.mMediaConfig.getMinCompressionRatioAV1();
                break;
            case "video/avc":
                minCompressionRatioHEVC = ((((((integer + 15) / 16) * ((integer2 + 15) / 16)) * 16) * 16) * 3) / (this.mMediaConfig.getMinCompressionRatioAVC() * 2);
                break;
            default:
                throw new UnsupportedOperationException("setMaxInputSize mime type unexpected: " + string);
        }
        mediaFormat.setInteger("max-input-size", minCompressionRatioHEVC);
    }

    @Nonnull
    public MediaFormat newAudioFormat(@Nonnull AudioConfig audioConfig) throws PlaybackException {
        Preconditions.checkNotNull(audioConfig, "audioConfig");
        return MediaFormat.createAudioFormat(getSupportedMimeType(audioConfig.getFourCC()), audioConfig.getSampleRate(), audioConfig.getChannels());
    }

    @Nonnull
    private String getSupportedMimeType(@Nonnull String str) throws PlaybackException {
        List<String> mimeTypesByFourCC = this.mMediaConfig.getMimeTypesByFourCC(str);
        if (mimeTypesByFourCC.isEmpty()) {
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "Unrecognized fourCC " + str);
        }
        Pair<String, MediaCodecInfo> supportedCodec = this.mCodecEnumerator.getSupportedCodec(mimeTypesByFourCC);
        if (supportedCodec == null) {
            if (str.equalsIgnoreCase("ec-3") || (str.equalsIgnoreCase("atmo") && this.mCapabilityDetector.isDolbyDigitalPlusSupported())) {
                DLog.warnf("No supported on device codec found for fourCC=%s; support detected via passthrough using mimeType=%s.", str, voMimeTypes.VOAUDIO_E_AC3);
                return voMimeTypes.VOAUDIO_E_AC3;
            }
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, String.format("Couldn't find supported codec for fourCC=%s, mimeTypes=%s", str, mimeTypesByFourCC));
        }
        return (String) supportedCodec.first;
    }
}
