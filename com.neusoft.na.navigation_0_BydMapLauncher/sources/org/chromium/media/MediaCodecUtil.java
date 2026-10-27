package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import java.util.Locale;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class MediaCodecUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MEDIA_CODEC_DECODER = 0;
    static final int MEDIA_CODEC_ENCODER = 1;
    private static final String TAG = "MediaCodecUtil";

    public static class CodecCreationInfo {
        public MediaCodec mediaCodec = null;
        public boolean supportsAdaptivePlayback = false;
    }

    public static final class MimeTypes {
        public static final String VIDEO_H264 = "video/avc";
        public static final String VIDEO_H265 = "video/hevc";
        public static final String VIDEO_MP4 = "video/mp4";
        public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
        public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
        public static final String VIDEO_WEBM = "video/webm";
    }

    MediaCodecUtil() {
    }

    private static class MediaCodecListHelper {
        private MediaCodecInfo[] mCodecList;

        private boolean hasNewMediaCodecList() {
            return true;
        }

        public MediaCodecListHelper() {
            if (hasNewMediaCodecList()) {
                this.mCodecList = new MediaCodecList(1).getCodecInfos();
            }
        }

        public int getCodecCount() {
            return hasNewMediaCodecList() ? this.mCodecList.length : MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            return hasNewMediaCodecList() ? this.mCodecList[i] : MediaCodecList.getCodecInfoAt(i);
        }
    }

    public static boolean isSoftwareCodec(String str) {
        return str.startsWith("OMX.google.") || !str.startsWith("OMX.");
    }

    private static String getDefaultCodecName(String str, int i, boolean z) {
        MediaCodecListHelper mediaCodecListHelper = new MediaCodecListHelper();
        int codecCount = mediaCodecListHelper.getCodecCount();
        for (int i2 = 0; i2 < codecCount; i2++) {
            MediaCodecInfo codecInfoAt = mediaCodecListHelper.getCodecInfoAt(i2);
            if (codecInfoAt.isEncoder() == i && (!z || isSoftwareCodec(codecInfoAt.getName()))) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return codecInfoAt.getName();
                    }
                }
            }
        }
        Log.e(TAG, "Decoder for type %s is not supported on this device", str);
        return "";
    }

    private static int[] getEncoderColorFormatsForMime(String str) {
        MediaCodecListHelper mediaCodecListHelper = new MediaCodecListHelper();
        int codecCount = mediaCodecListHelper.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = mediaCodecListHelper.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (int i2 = 0; i2 < supportedTypes.length; i2++) {
                    if (supportedTypes[i2].equalsIgnoreCase(str)) {
                        return codecInfoAt.getCapabilitiesForType(supportedTypes[i2]).colorFormats;
                    }
                }
            }
        }
        return null;
    }

    private static boolean canDecode(String str, boolean z) {
        CodecCreationInfo codecCreationInfoCreateDecoder = createDecoder(str, z, false);
        if (codecCreationInfoCreateDecoder.mediaCodec == null) {
            return false;
        }
        try {
            codecCreationInfoCreateDecoder.mediaCodec.release();
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot release media codec", e);
        }
        return true;
    }

    static CodecCreationInfo createDecoder(String str, boolean z, boolean z2) {
        CodecCreationInfo codecCreationInfo = new CodecCreationInfo();
        if (!isDecoderSupportedForDevice(str)) {
            Log.e(TAG, "Decoder for type %s is not supported on this device", str);
            return codecCreationInfo;
        }
        try {
            if (str.startsWith("video") && z) {
                String defaultCodecName = getDefaultCodecName(str, 0, z2);
                if (defaultCodecName.equals("")) {
                    return null;
                }
                MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(defaultCodecName);
                codecCreationInfo.supportsAdaptivePlayback = codecSupportsAdaptivePlayback(mediaCodecCreateByCodecName, str);
                mediaCodecCreateByCodecName.release();
                codecCreationInfo.mediaCodec = MediaCodec.createByCodecName(defaultCodecName + ".secure");
            } else {
                if (z2) {
                    codecCreationInfo.mediaCodec = MediaCodec.createByCodecName(getDefaultCodecName(str, 0, z2));
                } else {
                    codecCreationInfo.mediaCodec = MediaCodec.createDecoderByType(str);
                }
                codecCreationInfo.supportsAdaptivePlayback = codecSupportsAdaptivePlayback(codecCreationInfo.mediaCodec, str);
            }
        } catch (Exception e) {
            Object[] objArr = new Object[4];
            objArr[0] = str;
            objArr[1] = Boolean.valueOf(z);
            objArr[2] = z2 ? "yes" : "no";
            objArr[3] = e;
            Log.e(TAG, "Failed to create MediaCodec: %s, isSecure: %s, requireSoftwareCodec: %s", objArr);
            codecCreationInfo.mediaCodec = null;
        }
        return codecCreationInfo;
    }

    static boolean isDecoderSupportedForDevice(String str) {
        if (str.equals(MimeTypes.VIDEO_VP8)) {
            return ((Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung") && (Build.MODEL.startsWith("GT-I9190") || Build.MODEL.startsWith("GT-I9195"))) || Build.HARDWARE.startsWith("mt")) ? false : true;
        }
        if (str.equals(MimeTypes.VIDEO_VP9)) {
            return true;
        }
        str.equals("audio/opus");
        return true;
    }

    private static boolean isAdaptivePlaybackBlacklisted(String str) {
        if ((str.equals(MimeTypes.VIDEO_H264) || str.equals("video/avc1")) && Build.VERSION.RELEASE.equals("4.4.2") && Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
            return Build.MODEL.startsWith("GT-I9300") || Build.MODEL.startsWith("SCH-I535");
        }
        return false;
    }

    private static boolean codecSupportsAdaptivePlayback(MediaCodec mediaCodec, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        if (mediaCodec == null) {
            return false;
        }
        try {
            MediaCodecInfo codecInfo = mediaCodec.getCodecInfo();
            if (codecInfo.isEncoder() || isAdaptivePlaybackBlacklisted(str) || (capabilitiesForType = codecInfo.getCapabilitiesForType(str)) == null) {
                return false;
            }
            return capabilitiesForType.isFeatureSupported("adaptive-playback");
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Cannot retrieve codec information", e);
            return false;
        }
    }
}
