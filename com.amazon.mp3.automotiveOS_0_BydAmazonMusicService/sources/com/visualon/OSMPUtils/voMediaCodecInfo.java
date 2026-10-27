package com.visualon.OSMPUtils;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.util.Pair;

/* JADX INFO: loaded from: classes7.dex */
@TargetApi(16)
public final class voMediaCodecInfo {
    public static final String TAG = "voMediaCodecInfo";
    public final boolean adaptive;
    private final MediaCodecInfo.CodecCapabilities capabilities;
    private final String mimeType;
    public final String name;
    public final boolean secure;
    public final boolean tunneling;

    public static voMediaCodecInfo newPassthroughInstance(String str) {
        return new voMediaCodecInfo(str, null, null, false, false);
    }

    public static voMediaCodecInfo newInstance(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return new voMediaCodecInfo(str, str2, codecCapabilities, false, false);
    }

    public static voMediaCodecInfo newInstance(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        return new voMediaCodecInfo(str, str2, codecCapabilities, z, z2);
    }

    private voMediaCodecInfo(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        this.name = str;
        this.mimeType = str2;
        this.capabilities = codecCapabilities;
        boolean z3 = true;
        this.adaptive = (z || codecCapabilities == null || !isAdaptive(codecCapabilities)) ? false : true;
        this.tunneling = codecCapabilities != null && isTunneling(codecCapabilities);
        if (!z2 && (codecCapabilities == null || !isSecure(codecCapabilities))) {
            z3 = false;
        }
        this.secure = z3;
    }

    public MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        return (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) ? new MediaCodecInfo.CodecProfileLevel[0] : codecProfileLevelArr;
    }

    public boolean isCodecSupported(String str) {
        String mediaMimeType;
        if (str == null || this.mimeType == null || (mediaMimeType = voMimeTypes.getMediaMimeType(str)) == null) {
            return true;
        }
        if (!this.mimeType.equals(mediaMimeType)) {
            logNoSupport("codec.mime " + str + ", " + mediaMimeType);
            return false;
        }
        Pair<Integer, Integer> codecProfileAndLevel = voMediaCodecUtil.getCodecProfileAndLevel(str);
        if (codecProfileAndLevel == null) {
            return true;
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : getProfileLevels()) {
            if (codecProfileLevel.profile == ((Integer) codecProfileAndLevel.first).intValue() && codecProfileLevel.level >= ((Integer) codecProfileAndLevel.second).intValue()) {
                return true;
            }
        }
        logNoSupport("codec.profileLevel, " + str + ", " + mediaMimeType);
        return true;
    }

    @TargetApi(21)
    public boolean isVideoSizeAndRateSupportedV21(int i, int i2, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        }
        if (areSizeAndRateSupportedV21(videoCapabilities, i, i2, d)) {
            return true;
        }
        if (i >= i2 || !areSizeAndRateSupportedV21(videoCapabilities, i2, i, d)) {
            logNoSupport("sizeAndRate.support, " + i + "x" + i2 + "x" + d);
            return false;
        }
        logAssumedSupport("sizeAndRate.rotated, " + i + "x" + i2 + "x" + d);
        return true;
    }

    public static int ceilDivide(int i, int i2) {
        return ((i + i2) - 1) / i2;
    }

    @TargetApi(21)
    public Point alignVideoSizeV21(int i, int i2) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("align.caps");
            return null;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(ceilDivide(i, widthAlignment) * widthAlignment, ceilDivide(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public boolean isAudioSampleRateSupportedV21(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        }
        if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        }
        logNoSupport("sampleRate.support, " + i);
        return false;
    }

    @TargetApi(21)
    public boolean isAudioChannelCountSupportedV21(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        }
        if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i) {
            return true;
        }
        logNoSupport("channelCount.support, " + i);
        return false;
    }

    private void logNoSupport(String str) {
        voLog.d(TAG, "NoSupport [" + str + "] [" + this.name + ", " + this.mimeType + "] [" + Build.DEVICE + ", " + Build.MODEL + ", " + Build.MANUFACTURER + ", " + Build.VERSION.SDK_INT + "]", new Object[0]);
    }

    private void logAssumedSupport(String str) {
        voLog.d(TAG, "AssumedSupport [" + str + "] [" + this.name + ", " + this.mimeType + "] [" + Build.DEVICE + ", " + Build.MODEL + ", " + Build.MANUFACTURER + ", " + Build.VERSION.SDK_INT + "]", new Object[0]);
    }

    private static int adjustMaxInputChannelCount(String str, String str2, int i) {
        int i2;
        if (i > 1 || ((Build.VERSION.SDK_INT >= 26 && i > 0) || voMimeTypes.VOAUDIO_MPEG.equals(str2) || voMimeTypes.VOAUDIO_AMR_NB.equals(str2) || voMimeTypes.VOAUDIO_AMR_WB.equals(str2) || voMimeTypes.VOAUDIO_AAC.equals(str2) || voMimeTypes.VOAUDIO_VORBIS.equals(str2) || voMimeTypes.VOAUDIO_OPUS.equals(str2) || voMimeTypes.VOAUDIO_RAW.equals(str2) || voMimeTypes.VOAUDIO_FLAC.equals(str2) || voMimeTypes.VOAUDIO_ALAW.equals(str2) || voMimeTypes.VOAUDIO_MLAW.equals(str2) || voMimeTypes.VOAUDIO_MSGSM.equals(str2))) {
            return i;
        }
        if (voMimeTypes.VOAUDIO_AC3.equals(str2)) {
            i2 = 6;
        } else {
            i2 = voMimeTypes.VOAUDIO_E_AC3.equals(str2) ? 16 : 30;
        }
        voLog.w(TAG, "AssumedMaxChannelAdjustment: " + str + ", [" + i + " to " + i2 + "]", new Object[0]);
        return i2;
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return isAdaptiveV19(codecCapabilities);
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private static boolean isTunneling(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return isTunnelingV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isTunnelingV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    private static boolean isSecure(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return isSecureV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isSecureV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    @TargetApi(21)
    private static boolean areSizeAndRateSupportedV21(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }
}
