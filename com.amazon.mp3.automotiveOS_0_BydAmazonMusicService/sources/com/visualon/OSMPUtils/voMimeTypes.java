package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public final class voMimeTypes {
    public static final String VOAUDIO_AAC = "audio/mp4a-latm";
    public static final String VOAUDIO_AC3 = "audio/ac3";
    public static final String VOAUDIO_ALAC = "audio/alac";
    public static final String VOAUDIO_ALAW = "audio/g711-alaw";
    public static final String VOAUDIO_AMR_NB = "audio/3gpp";
    public static final String VOAUDIO_AMR_WB = "audio/amr-wb";
    public static final String VOAUDIO_DTS = "audio/vnd.dts";
    public static final String VOAUDIO_DTS_EXPRESS = "audio/vnd.dts.hd;profile=lbr";
    public static final String VOAUDIO_DTS_HD = "audio/vnd.dts.hd";
    public static final String VOAUDIO_E_AC3 = "audio/eac3";
    public static final String VOAUDIO_E_AC3_JOC = "audio/eac3-joc";
    public static final String VOAUDIO_FLAC = "audio/flac";
    public static final String VOAUDIO_MLAW = "audio/g711-mlaw";
    public static final String VOAUDIO_MP4 = "audio/mp4";
    public static final String VOAUDIO_MPEG = "audio/mpeg";
    public static final String VOAUDIO_MPEG_L1 = "audio/mpeg-L1";
    public static final String VOAUDIO_MPEG_L2 = "audio/mpeg-L2";
    public static final String VOAUDIO_MSGSM = "audio/gsm";
    public static final String VOAUDIO_OPUS = "audio/opus";
    public static final String VOAUDIO_RAW = "audio/raw";
    public static final String VOAUDIO_TRUEHD = "audio/true-hd";
    public static final String VOAUDIO_UNKNOWN = "audio/x-unknown";
    public static final String VOAUDIO_VORBIS = "audio/vorbis";
    public static final String VOAUDIO_WEBM = "audio/webm";
    public static final String VOBASE_TYPE_AUDIO = "audio";
    public static final String VOBASE_TYPE_VIDEO = "video";
    public static final String VOVIDEO_H263 = "video/3gpp";
    public static final String VOVIDEO_H264 = "video/avc";
    public static final String VOVIDEO_H265 = "video/hevc";
    public static final String VOVIDEO_MP4 = "video/mp4";
    public static final String VOVIDEO_MP4V = "video/mp4v-es";
    public static final String VOVIDEO_MPEG = "video/mpeg";
    public static final String VOVIDEO_MPEG2 = "video/mpeg2";
    public static final String VOVIDEO_UNKNOWN = "video/x-unknown";
    public static final String VOVIDEO_VC1 = "video/wvc1";
    public static final String VOVIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String VOVIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final String VOVIDEO_WEBM = "video/webm";

    private voMimeTypes() {
    }

    public static boolean isAudio(String str) {
        return VOBASE_TYPE_AUDIO.equals(getTopLevelType(str));
    }

    public static boolean isVideo(String str) {
        return VOBASE_TYPE_VIDEO.equals(getTopLevelType(str));
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
            return VOVIDEO_VP9;
        }
        if (strTrim.startsWith("vp8") || strTrim.startsWith("vp08")) {
            return VOVIDEO_VP8;
        }
        if (strTrim.startsWith("mp4a")) {
            return VOAUDIO_AAC;
        }
        if (strTrim.startsWith("ac-3") || strTrim.startsWith("dac3")) {
            return VOAUDIO_AC3;
        }
        if (strTrim.startsWith("ec-3") || strTrim.startsWith("dec3")) {
            return VOAUDIO_E_AC3;
        }
        if (strTrim.startsWith("ec+3")) {
            return VOAUDIO_E_AC3_JOC;
        }
        if (strTrim.startsWith("dtsc") || strTrim.startsWith("dtse")) {
            return VOAUDIO_DTS;
        }
        if (strTrim.startsWith("dtsh") || strTrim.startsWith("dtsl")) {
            return VOAUDIO_DTS_HD;
        }
        if (strTrim.startsWith("opus")) {
            return VOAUDIO_OPUS;
        }
        if (strTrim.startsWith("vorbis")) {
            return VOAUDIO_VORBIS;
        }
        return null;
    }

    public static String getDefaultCodecFromMimeType(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("video/avc")) {
            return "avc1";
        }
        if (str.equals("video/hevc")) {
            return "hev1";
        }
        if (str.equals(VOVIDEO_VP9)) {
            return "vp9";
        }
        if (str.equals(VOVIDEO_VP8)) {
            return "vp8";
        }
        if (str.equals(VOAUDIO_AAC)) {
            return "mp4a";
        }
        if (str.equals(VOAUDIO_AC3)) {
            return "ac-3";
        }
        if (str.equals(VOAUDIO_E_AC3)) {
            return "ec-3";
        }
        if (str.equals(VOAUDIO_E_AC3_JOC)) {
            return "ec+3";
        }
        if (str.equals(VOAUDIO_DTS)) {
            return "dtsc";
        }
        if (str.equals(VOAUDIO_DTS_HD)) {
            return "dtsh";
        }
        if (str.equals(VOAUDIO_OPUS)) {
            return "opus";
        }
        if (str.equals(VOAUDIO_VORBIS)) {
            return "vorbis";
        }
        return null;
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
