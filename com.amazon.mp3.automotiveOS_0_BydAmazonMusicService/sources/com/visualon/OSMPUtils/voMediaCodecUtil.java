package com.visualon.OSMPUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes7.dex */
@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class voMediaCodecUtil {
    private static final SparseIntArray AVC_LEVEL_NUMBER_TO_CONST;
    private static final SparseIntArray AVC_PROFILE_NUMBER_TO_CONST;
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final Map<String, Integer> HEVC_CODEC_STRING_TO_PROFILE_LEVEL;
    private static final String MTK_RAW_DECODER_NAME = "OMX.MTK.AUDIO.DECODER.RAW";
    private static final String TAG = "voMediaCodecUtil";
    private static final String GOOGLE_RAW_DECODER_NAME = "OMX.google.raw.decoder";
    private static final voMediaCodecInfo PASSTHROUGH_DECODER_INFO = voMediaCodecInfo.newPassthroughInstance(GOOGLE_RAW_DECODER_NAME);
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<CodecKey, List<voMediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    private interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isSecurePlaybackSupported(String str, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    private static int avcLevelToMaxFrameSize(int i) {
        if (i == 1 || i == 2) {
            return 25344;
        }
        switch (i) {
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            default:
                return -1;
        }
    }

    private static boolean codecNeedsDisableAdaptationWorkaround(String str) {
        return false;
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        AVC_PROFILE_NUMBER_TO_CONST = sparseIntArray;
        sparseIntArray.put(66, 1);
        sparseIntArray.put(77, 2);
        sparseIntArray.put(88, 4);
        sparseIntArray.put(100, 8);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        AVC_LEVEL_NUMBER_TO_CONST = sparseIntArray2;
        sparseIntArray2.put(10, 1);
        sparseIntArray2.put(11, 4);
        sparseIntArray2.put(12, 8);
        sparseIntArray2.put(13, 16);
        sparseIntArray2.put(20, 32);
        sparseIntArray2.put(21, 64);
        sparseIntArray2.put(22, 128);
        sparseIntArray2.put(30, 256);
        sparseIntArray2.put(31, 512);
        sparseIntArray2.put(32, 1024);
        sparseIntArray2.put(40, 2048);
        sparseIntArray2.put(41, 4096);
        sparseIntArray2.put(42, 8192);
        sparseIntArray2.put(50, 16384);
        sparseIntArray2.put(51, 32768);
        sparseIntArray2.put(52, 65536);
        HashMap map = new HashMap();
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL = map;
        map.put("L30", 1);
        map.put("L60", 4);
        map.put("L63", 16);
        map.put("L90", 64);
        map.put("L93", 256);
        map.put("L120", 1024);
        map.put("L123", 4096);
        map.put("L150", 16384);
        map.put("L153", 65536);
        map.put("L156", 262144);
        map.put("L180", 1048576);
        map.put("L183", 4194304);
        map.put("L186", 16777216);
        map.put("H30", 2);
        map.put("H60", 8);
        map.put("H63", 32);
        map.put("H90", 128);
        map.put("H93", 512);
        map.put("H120", 2048);
        map.put("H123", 8192);
        map.put("H150", 32768);
        map.put("H153", 131072);
        map.put("H156", 524288);
        map.put("H180", 2097152);
        map.put("H183", 8388608);
        map.put("H186", Integer.valueOf(voOSType.VOOSMP_SRC_FFAUDIO_WMA));
    }

    private voMediaCodecUtil() {
    }

    public static void warmDecoderInfoCache(String str, boolean z) {
        try {
            getDecoderInfos(str, z);
        } catch (DecoderQueryException e) {
            voLog.e(TAG, "Codec warming failed", e);
        }
    }

    public static voMediaCodecInfo getPassthroughDecoderInfo() {
        return PASSTHROUGH_DECODER_INFO;
    }

    public static voMediaCodecInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException {
        List<voMediaCodecInfo> decoderInfos = getDecoderInfos(str, z);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    public static synchronized List<voMediaCodecInfo> getDecoderInfos(String str, boolean z) throws DecoderQueryException {
        CodecKey codecKey = new CodecKey(str, z);
        HashMap<CodecKey, List<voMediaCodecInfo>> map = decoderInfosCache;
        List<voMediaCodecInfo> list = map.get(codecKey);
        if (list != null) {
            return list;
        }
        MediaCodecListCompatV21 mediaCodecListCompatV21 = new MediaCodecListCompatV21(z);
        ArrayList<voMediaCodecInfo> decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
        if (z) {
            decoderInfosInternal.isEmpty();
        }
        if (voMimeTypes.VOAUDIO_E_AC3_JOC.equals(str)) {
            decoderInfosInternal.addAll(getDecoderInfosInternal(new CodecKey(voMimeTypes.VOAUDIO_E_AC3, codecKey.secure), mediaCodecListCompatV21, str));
        }
        applyWorkarounds(decoderInfosInternal);
        List<voMediaCodecInfo> listUnmodifiableList = Collections.unmodifiableList(decoderInfosInternal);
        map.put(codecKey, listUnmodifiableList);
        return listUnmodifiableList;
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int iMax = 0;
            voMediaCodecInfo decoderInfo = getDecoderInfo("video/avc", false);
            if (decoderInfo != null) {
                MediaCodecInfo.CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int iMax2 = 0;
                while (iMax < length) {
                    iMax2 = Math.max(avcLevelToMaxFrameSize(profileLevels[iMax].level), iMax2);
                    iMax++;
                }
                iMax = Math.max(iMax2, 345600);
            }
            maxH264DecodableFrameSize = iMax;
        }
        return maxH264DecodableFrameSize;
    }

    public static Pair<Integer, Integer> getCodecProfileAndLevel(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        String str2 = strArrSplit[0];
        str2.hashCode();
        switch (str2) {
        }
        return null;
    }

    private static ArrayList<voMediaCodecInfo> getDecoderInfosInternal(CodecKey codecKey, MediaCodecListCompat mediaCodecListCompat, String str) throws DecoderQueryException {
        ArrayList<voMediaCodecInfo> arrayList;
        String str2;
        int codecCount;
        boolean zSecureDecodersExplicit;
        int i;
        int i2;
        MediaCodecListCompat mediaCodecListCompat2 = mediaCodecListCompat;
        try {
            arrayList = new ArrayList<>();
            str2 = codecKey.mimeType;
            codecCount = mediaCodecListCompat.getCodecCount();
            zSecureDecodersExplicit = mediaCodecListCompat.secureDecodersExplicit();
            i = 0;
        } catch (Exception e) {
            throw new DecoderQueryException(e);
        }
        while (i < codecCount) {
            MediaCodecInfo codecInfoAt = mediaCodecListCompat2.getCodecInfoAt(i);
            String name = codecInfoAt.getName();
            if (isCodecUsableDecoder(codecInfoAt, name, zSecureDecodersExplicit, str)) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int length = supportedTypes.length;
                int i3 = 0;
                while (i3 < length) {
                    String str3 = supportedTypes[i3];
                    if (str3.equalsIgnoreCase(str2)) {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str3);
                            boolean zIsSecurePlaybackSupported = mediaCodecListCompat2.isSecurePlaybackSupported(str2, capabilitiesForType);
                            boolean zCodecNeedsDisableAdaptationWorkaround = codecNeedsDisableAdaptationWorkaround(name);
                            if (zSecureDecodersExplicit) {
                                i2 = codecCount;
                                if (codecKey.secure != zIsSecurePlaybackSupported) {
                                }
                                arrayList.add(voMediaCodecInfo.newInstance(name, str2, capabilitiesForType, zCodecNeedsDisableAdaptationWorkaround, false));
                            } else {
                                i2 = codecCount;
                            }
                            if (!zSecureDecodersExplicit && !codecKey.secure) {
                                arrayList.add(voMediaCodecInfo.newInstance(name, str2, capabilitiesForType, zCodecNeedsDisableAdaptationWorkaround, false));
                            } else if (!zSecureDecodersExplicit && zIsSecurePlaybackSupported) {
                                arrayList.add(voMediaCodecInfo.newInstance(name + ".secure", str2, capabilitiesForType, zCodecNeedsDisableAdaptationWorkaround, true));
                                return arrayList;
                            }
                        } catch (Exception e2) {
                            voLog.e(TAG, "Failed to query codec " + name + " (" + str3 + ")", new Object[0]);
                            throw e2;
                        }
                        throw new DecoderQueryException(e);
                    }
                    i2 = codecCount;
                    i3++;
                    mediaCodecListCompat2 = mediaCodecListCompat;
                    codecCount = i2;
                }
            }
            i++;
            mediaCodecListCompat2 = mediaCodecListCompat;
            codecCount = codecCount;
        }
        return arrayList;
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        return (voMimeTypes.VOAUDIO_E_AC3_JOC.equals(str2) && "OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) ? false : true;
    }

    private static void applyWorkarounds(List<voMediaCodecInfo> list) {
        if (Build.VERSION.SDK_INT < 26) {
            if (list.size() <= 1 || !MTK_RAW_DECODER_NAME.equals(list.get(0).name)) {
                return;
            }
            for (int i = 1; i < list.size(); i++) {
                voMediaCodecInfo vomediacodecinfo = list.get(i);
                if (GOOGLE_RAW_DECODER_NAME.equals(vomediacodecinfo.name)) {
                    list.remove(i);
                    list.add(0, vomediacodecinfo);
                    return;
                }
            }
        }
    }

    private static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr) {
        int i;
        if (strArr.length < 4) {
            voLog.w(TAG, "Ignoring malformed HEVC codec string: " + str, new Object[0]);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            voLog.w(TAG, "Ignoring malformed HEVC codec string: " + str, new Object[0]);
            return null;
        }
        String strGroup = matcher.group(1);
        if ("1".equals(strGroup)) {
            i = 1;
        } else {
            if (!"2".equals(strGroup)) {
                voLog.w(TAG, "Unknown HEVC profile string: " + strGroup, new Object[0]);
                return null;
            }
            i = 2;
        }
        Integer num = HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(strArr[3]);
        if (num == null) {
            voLog.w(TAG, "Unknown HEVC level string: " + matcher.group(1), new Object[0]);
            return null;
        }
        return new Pair<>(Integer.valueOf(i), num);
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        Integer numValueOf;
        Integer numValueOf2;
        if (strArr.length < 2) {
            voLog.w(TAG, "Ignoring malformed AVC codec string: " + str, new Object[0]);
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                numValueOf2 = Integer.valueOf(Integer.parseInt(strArr[1].substring(0, 2), 16));
                numValueOf = Integer.valueOf(Integer.parseInt(strArr[1].substring(4), 16));
            } else if (strArr.length >= 3) {
                Integer numValueOf3 = Integer.valueOf(Integer.parseInt(strArr[1]));
                numValueOf = Integer.valueOf(Integer.parseInt(strArr[2]));
                numValueOf2 = numValueOf3;
            } else {
                voLog.w(TAG, "Ignoring malformed AVC codec string: " + str, new Object[0]);
                return null;
            }
            Integer numValueOf4 = Integer.valueOf(AVC_PROFILE_NUMBER_TO_CONST.get(numValueOf2.intValue()));
            if (numValueOf4 == null) {
                voLog.w(TAG, "Unknown AVC profile: " + numValueOf2, new Object[0]);
                return null;
            }
            Integer numValueOf5 = Integer.valueOf(AVC_LEVEL_NUMBER_TO_CONST.get(numValueOf.intValue()));
            if (numValueOf5 == null) {
                voLog.w(TAG, "Unknown AVC level: " + numValueOf, new Object[0]);
                return null;
            }
            return new Pair<>(numValueOf4, numValueOf5);
        } catch (NumberFormatException unused) {
            voLog.w(TAG, "Ignoring malformed AVC codec string: " + str, new Object[0]);
            return null;
        }
    }

    @TargetApi(21)
    private static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private MediaCodecInfo[] MediaCodecInfos;
        private final int codecKind;

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z) {
            this.codecKind = z ? 1 : 0;
        }

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            ensurevoMediaCodecInfosInitialized();
            return this.MediaCodecInfos.length;
        }

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public MediaCodecInfo getCodecInfoAt(int i) {
            ensurevoMediaCodecInfosInitialized();
            return this.MediaCodecInfos[i];
        }

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public boolean isSecurePlaybackSupported(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void ensurevoMediaCodecInfosInitialized() {
            if (this.MediaCodecInfos == null) {
                this.MediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    private static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        @Override // com.visualon.OSMPUtils.voMediaCodecUtil.MediaCodecListCompat
        public boolean isSecurePlaybackSupported(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
            return "video/avc".equals(str);
        }
    }

    private static final class CodecKey {
        public final String mimeType;
        public final boolean secure;

        public CodecKey(String str, boolean z) {
            this.mimeType = str;
            this.secure = z;
        }

        public int hashCode() {
            String str = this.mimeType;
            return (((str == null ? 0 : str.hashCode()) + 31) * 31) + (this.secure ? 1231 : 1237);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            return TextUtils.equals(this.mimeType, codecKey.mimeType) && this.secure == codecKey.secure;
        }
    }
}
