package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes6.dex */
public final class MimeTypes {
    private MimeTypes() {
    }

    public static boolean isAudio(String str) {
        return voMimeTypes.VOBASE_TYPE_AUDIO.equals(getTopLevelType(str));
    }

    public static boolean isVideo(String str) {
        return voMimeTypes.VOBASE_TYPE_VIDEO.equals(getTopLevelType(str));
    }

    public static boolean isText(String str) {
        return "text".equals(getTopLevelType(str));
    }

    public static String getVideoMediaMimeType(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : str.split(",")) {
            String mediaMimeType = getMediaMimeType(str2);
            if (mediaMimeType != null && isVideo(mediaMimeType)) {
                return mediaMimeType;
            }
        }
        return null;
    }

    public static String getAudioMediaMimeType(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : str.split(",")) {
            String mediaMimeType = getMediaMimeType(str2);
            if (mediaMimeType != null && isAudio(mediaMimeType)) {
                return mediaMimeType;
            }
        }
        return null;
    }

    public static String getMediaMimeType(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("avc1") || strTrim.startsWith("avc3")) {
            return "video/avc";
        }
        if (strTrim.startsWith("hev1") || strTrim.startsWith("hvc1")) {
            return "video/hevc";
        }
        if (strTrim.startsWith("vp9") || strTrim.startsWith("vp09")) {
            return voMimeTypes.VOVIDEO_VP9;
        }
        if (strTrim.startsWith("vp8") || strTrim.startsWith("vp08")) {
            return voMimeTypes.VOVIDEO_VP8;
        }
        if (strTrim.startsWith("mp4a")) {
            return voMimeTypes.VOAUDIO_AAC;
        }
        if (strTrim.startsWith("ac-3") || strTrim.startsWith("dac3")) {
            return voMimeTypes.VOAUDIO_AC3;
        }
        if (strTrim.startsWith("ec-3") || strTrim.startsWith("dec3")) {
            return voMimeTypes.VOAUDIO_E_AC3;
        }
        if (strTrim.startsWith("dtsc") || strTrim.startsWith("dtse")) {
            return voMimeTypes.VOAUDIO_DTS;
        }
        if (strTrim.startsWith("dtsh") || strTrim.startsWith("dtsl")) {
            return voMimeTypes.VOAUDIO_DTS_HD;
        }
        if (strTrim.startsWith("opus")) {
            return voMimeTypes.VOAUDIO_OPUS;
        }
        if (strTrim.startsWith("vorbis")) {
            return voMimeTypes.VOAUDIO_VORBIS;
        }
        return null;
    }

    public static int getTrackType(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isAudio(str)) {
            return 1;
        }
        if (isVideo(str)) {
            return 2;
        }
        if (isText(str) || "application/cea-608".equals(str) || "application/cea-708".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/x-subrip".equals(str) || "application/ttml+xml".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-rawcc".equals(str) || "application/vobsub".equals(str) || "application/pgs".equals(str) || "application/dvbsubs".equals(str)) {
            return 3;
        }
        return ("application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-camera-motion".equals(str)) ? 4 : -1;
    }

    public static int getTrackTypeOfCodec(String str) {
        return getTrackType(getMediaMimeType(str));
    }

    private static String getTopLevelType(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Invalid mime type: " + str);
        }
        return str.substring(0, iIndexOf);
    }
}
