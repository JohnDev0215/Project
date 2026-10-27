package com.amazon.avod.media.playback.support;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.MediaCodecEnumerator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(24)
public class DisplayModeAndToneMappingEvaluator {
    public static boolean isDisplayUhdCapable(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            DLog.devf("UHD not supported; Cannot retrieve WindowManager system service!");
            return false;
        }
        for (Display.Mode mode : windowManager.getDefaultDisplay().getSupportedModes()) {
            int physicalWidth = mode.getPhysicalWidth();
            int physicalHeight = mode.getPhysicalHeight();
            if (new VideoResolution(Math.max(physicalWidth, physicalHeight), Math.min(physicalWidth, physicalHeight)).isUltraHDOrHigher()) {
                DLog.devf("UHD supported; Display mode %s supports UHD.", Integer.valueOf(mode.describeContents()));
                return true;
            }
        }
        DLog.devf("UHD not supported; UHD Display mode not found.");
        return false;
    }

    public static boolean isDecoderUhdCapable() {
        Pair<String, MediaCodecInfo> supportedCodec = MediaCodecEnumerator.getInstance().getSupportedCodec(MediaDefaultConfiguration.getInstance().getHevcMimeTypes());
        if (supportedCodec == null) {
            DLog.devf("UHD not supported; no HEVC decoder found");
            return false;
        }
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedCodec.second;
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType((String) supportedCodec.first).profileLevels) {
            if (codecProfileLevel.level >= 16384) {
                DLog.devf("UHD supported; HEVC decoder %s supports HEVCMainTierLevel5.", mediaCodecInfo.getName());
                return true;
            }
        }
        DLog.devf("UHD not supported; HEVC decoder %s does not support HEVCMainTierLevel5", mediaCodecInfo.getName());
        return false;
    }

    @Nonnull
    public static ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats(@Nonnull Context context) {
        return getSupportedHdrFormats(Build.VERSION.SDK_INT, context, MediaDefaultConfiguration.getInstance());
    }

    @Nonnull
    static ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats(int i, @Nonnull Context context, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration) {
        Preconditions.checkNotNull(context, "context");
        Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaConfig");
        if (i >= 24) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i2 : ((DisplayManager) context.getSystemService("display")).getDisplay(0).getHdrCapabilities().getSupportedHdrTypes()) {
                if (i2 == 1 && mediaDefaultConfiguration.isDolbyVisionHdrSupported() && !Strings.isNullOrEmpty(lookupSecureDecoderWithMimeType(Build.VERSION.SDK_INT, new MediaCodecList(1), "video/dolby-vision"))) {
                    DLog.devf("DoVi HDR is supported and enabled by config.");
                    builder.add(PlaybackResourceServiceConstants$HdrFormat.DolbyVision);
                }
                if (i2 == 2 && mediaDefaultConfiguration.isHdr10Supported() && isDecoderHdr10Capable()) {
                    DLog.devf("HDR10 HDR is supported and enabled by config.");
                    builder.add(PlaybackResourceServiceConstants$HdrFormat.Hdr10);
                }
            }
            return builder.build();
        }
        DLog.devf("Display tone mapping evaluation is not supported on this platform.");
        return ImmutableList.of();
    }

    private static boolean isDecoderHdr10Capable() {
        Pair<String, MediaCodecInfo> supportedCodec = MediaCodecEnumerator.getInstance().getSupportedCodec(MediaDefaultConfiguration.getInstance().getHevcMimeTypes());
        if (supportedCodec == null) {
            DLog.devf("HDR10 not supported; no HEVC decoder found");
            return false;
        }
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedCodec.second;
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType((String) supportedCodec.first).profileLevels) {
            if (codecProfileLevel.level >= 4096) {
                DLog.devf("HDR10 is supported: HEVCProfileMain10HDR10 profile is supported");
                return true;
            }
        }
        DLog.devf("HDR10 not supported; HEVC decoder %s does not support HEVCProfileMain10HDR10 profile", mediaCodecInfo.getName());
        return false;
    }

    @Nullable
    public static String lookupSecureDecoderWithMimeType(int i, @Nullable MediaCodecList mediaCodecList, @Nullable String str) {
        if (i < 21 || mediaCodecList == null || Strings.isNullOrEmpty(str)) {
            return null;
        }
        try {
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString("mime", str);
            mediaFormat.setFeatureEnabled("secure-playback", true);
            String strFindDecoderForFormat = mediaCodecList.findDecoderForFormat(mediaFormat);
            DLog.devf("Found decoder %s for mime %s", strFindDecoderForFormat, str);
            return strFindDecoderForFormat;
        } catch (IllegalArgumentException e) {
            DLog.exceptionf(e, "Unable to get decoder for mime: %s,", str);
            return null;
        }
    }
}
