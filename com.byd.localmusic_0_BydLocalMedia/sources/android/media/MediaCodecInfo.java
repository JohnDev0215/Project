package android.media;

import android.media.MediaCodecInfo;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import com.android.internal.content.NativeLibraryHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaCodecInfo {
    private static final int DEFAULT_MAX_SUPPORTED_INSTANCES = 32;
    private static final int ERROR_NONE_SUPPORTED = 4;
    private static final int ERROR_UNRECOGNIZED = 1;
    private static final int ERROR_UNSUPPORTED = 2;
    private static final int FLAG_IS_ENCODER = 1;
    private static final int FLAG_IS_HARDWARE_ACCELERATED = 8;
    private static final int FLAG_IS_SOFTWARE_ONLY = 4;
    private static final int FLAG_IS_VENDOR = 2;
    private static final int MAX_SUPPORTED_INSTANCES_LIMIT = 256;
    private static final String TAG = "MediaCodecInfo";
    private String mCanonicalName;
    private Map<String, CodecCapabilities> mCaps = new HashMap();
    private int mFlags;
    private String mName;
    private static final Range<Integer> POSITIVE_INTEGERS = Range.create(1, Integer.MAX_VALUE);
    private static final Range<Long> POSITIVE_LONGS = Range.create(1L, Long.MAX_VALUE);
    private static final Range<Rational> POSITIVE_RATIONALS = Range.create(new Rational(1, Integer.MAX_VALUE), new Rational(Integer.MAX_VALUE, 1));
    private static final Range<Integer> SIZE_RANGE = Range.create(1, 32768);
    private static final Range<Integer> FRAME_RATE_RANGE = Range.create(0, 960);
    private static final Range<Integer> BITRATE_RANGE = Range.create(0, 500000000);

    MediaCodecInfo(String str, String str2, int i, CodecCapabilities[] codecCapabilitiesArr) {
        this.mName = str;
        this.mCanonicalName = str2;
        this.mFlags = i;
        for (CodecCapabilities codecCapabilities : codecCapabilitiesArr) {
            this.mCaps.put(codecCapabilities.getMimeType(), codecCapabilities);
        }
    }

    public final String getName() {
        return this.mName;
    }

    public final String getCanonicalName() {
        return this.mCanonicalName;
    }

    public final boolean isAlias() {
        return !this.mName.equals(this.mCanonicalName);
    }

    public final boolean isEncoder() {
        return (this.mFlags & 1) != 0;
    }

    public final boolean isVendor() {
        return (this.mFlags & 2) != 0;
    }

    public final boolean isSoftwareOnly() {
        return (this.mFlags & 4) != 0;
    }

    public final boolean isHardwareAccelerated() {
        return (this.mFlags & 8) != 0;
    }

    public final String[] getSupportedTypes() {
        Set<String> setKeySet = this.mCaps.keySet();
        String[] strArr = (String[]) setKeySet.toArray(new String[setKeySet.size()]);
        Arrays.sort(strArr);
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int checkPowerOfTwo(int i, String str) {
        if (((i - 1) & i) == 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    private static class Feature {
        public boolean mDefault;
        public String mName;
        public int mValue;

        public Feature(String str, int i, boolean z) {
            this.mName = str;
            this.mValue = i;
            this.mDefault = z;
        }
    }

    public static final class CodecCapabilities {
        public static final int COLOR_Format12bitRGB444 = 3;
        public static final int COLOR_Format16bitARGB1555 = 5;
        public static final int COLOR_Format16bitARGB4444 = 4;
        public static final int COLOR_Format16bitBGR565 = 7;
        public static final int COLOR_Format16bitRGB565 = 6;
        public static final int COLOR_Format18BitBGR666 = 41;
        public static final int COLOR_Format18bitARGB1665 = 9;
        public static final int COLOR_Format18bitRGB666 = 8;
        public static final int COLOR_Format19bitARGB1666 = 10;
        public static final int COLOR_Format24BitABGR6666 = 43;
        public static final int COLOR_Format24BitARGB6666 = 42;
        public static final int COLOR_Format24bitARGB1887 = 13;
        public static final int COLOR_Format24bitBGR888 = 12;
        public static final int COLOR_Format24bitRGB888 = 11;
        public static final int COLOR_Format25bitARGB1888 = 14;
        public static final int COLOR_Format32bitABGR8888 = 2130747392;
        public static final int COLOR_Format32bitARGB8888 = 16;
        public static final int COLOR_Format32bitBGRA8888 = 15;
        public static final int COLOR_Format8bitRGB332 = 2;
        public static final int COLOR_FormatCbYCrY = 27;
        public static final int COLOR_FormatCrYCbY = 28;
        public static final int COLOR_FormatL16 = 36;
        public static final int COLOR_FormatL2 = 33;
        public static final int COLOR_FormatL24 = 37;
        public static final int COLOR_FormatL32 = 38;
        public static final int COLOR_FormatL4 = 34;
        public static final int COLOR_FormatL8 = 35;
        public static final int COLOR_FormatMonochrome = 1;
        public static final int COLOR_FormatRGBAFlexible = 2134288520;
        public static final int COLOR_FormatRGBFlexible = 2134292616;
        public static final int COLOR_FormatRawBayer10bit = 31;
        public static final int COLOR_FormatRawBayer8bit = 30;
        public static final int COLOR_FormatRawBayer8bitcompressed = 32;
        public static final int COLOR_FormatSurface = 2130708361;
        public static final int COLOR_FormatYCbYCr = 25;
        public static final int COLOR_FormatYCrYCb = 26;
        public static final int COLOR_FormatYUV411PackedPlanar = 18;
        public static final int COLOR_FormatYUV411Planar = 17;
        public static final int COLOR_FormatYUV420Flexible = 2135033992;
        public static final int COLOR_FormatYUV420PackedPlanar = 20;
        public static final int COLOR_FormatYUV420PackedSemiPlanar = 39;
        public static final int COLOR_FormatYUV420Planar = 19;
        public static final int COLOR_FormatYUV420SemiPlanar = 21;
        public static final int COLOR_FormatYUV422Flexible = 2135042184;
        public static final int COLOR_FormatYUV422PackedPlanar = 23;
        public static final int COLOR_FormatYUV422PackedSemiPlanar = 40;
        public static final int COLOR_FormatYUV422Planar = 22;
        public static final int COLOR_FormatYUV422SemiPlanar = 24;
        public static final int COLOR_FormatYUV444Flexible = 2135181448;
        public static final int COLOR_FormatYUV444Interleaved = 29;
        public static final int COLOR_QCOM_FormatYUV420SemiPlanar = 2141391872;
        public static final int COLOR_TI_FormatYUV420PackedSemiPlanar = 2130706688;
        public static final String FEATURE_LowLatency = "low-latency";
        private static final String TAG = "CodecCapabilities";
        public int[] colorFormats;
        private AudioCapabilities mAudioCaps;
        private MediaFormat mCapabilitiesInfo;
        private MediaFormat mDefaultFormat;
        private EncoderCapabilities mEncoderCaps;
        int mError;
        private int mFlagsRequired;
        private int mFlagsSupported;
        private int mFlagsVerified;
        private int mMaxSupportedInstances;
        private String mMime;
        private VideoCapabilities mVideoCaps;
        public CodecProfileLevel[] profileLevels;
        public static final String FEATURE_AdaptivePlayback = "adaptive-playback";
        public static final String FEATURE_SecurePlayback = "secure-playback";
        public static final String FEATURE_TunneledPlayback = "tunneled-playback";
        public static final String FEATURE_PartialFrame = "partial-frame";
        public static final String FEATURE_FrameParsing = "frame-parsing";
        public static final String FEATURE_MultipleFrames = "multiple-frames";
        public static final String FEATURE_DynamicTimestamp = "dynamic-timestamp";
        private static final Feature[] decoderFeatures = {new Feature(FEATURE_AdaptivePlayback, 1, true), new Feature(FEATURE_SecurePlayback, 2, false), new Feature(FEATURE_TunneledPlayback, 4, false), new Feature(FEATURE_PartialFrame, 8, false), new Feature(FEATURE_FrameParsing, 16, false), new Feature(FEATURE_MultipleFrames, 32, false), new Feature(FEATURE_DynamicTimestamp, 64, false), new Feature("low-latency", 128, true)};
        public static final String FEATURE_IntraRefresh = "intra-refresh";
        private static final Feature[] encoderFeatures = {new Feature(FEATURE_IntraRefresh, 1, false), new Feature(FEATURE_MultipleFrames, 2, false), new Feature(FEATURE_DynamicTimestamp, 4, false)};

        public CodecCapabilities() {
        }

        public final boolean isFeatureSupported(String str) {
            return checkFeature(str, this.mFlagsSupported);
        }

        public final boolean isFeatureRequired(String str) {
            return checkFeature(str, this.mFlagsRequired);
        }

        public String[] validFeatures() {
            Feature[] validFeatures = getValidFeatures();
            int length = validFeatures.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = validFeatures[i].mName;
            }
            return strArr;
        }

        private Feature[] getValidFeatures() {
            if (!isEncoder()) {
                return decoderFeatures;
            }
            return encoderFeatures;
        }

        private boolean checkFeature(String str, int i) {
            for (Feature feature : getValidFeatures()) {
                if (feature.mName.equals(str)) {
                    return (feature.mValue & i) != 0;
                }
            }
            return false;
        }

        public boolean isRegular() {
            for (Feature feature : getValidFeatures()) {
                if (!feature.mDefault && isFeatureRequired(feature.mName)) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isFormatSupported(MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            String str = (String) map.get(MediaFormat.KEY_MIME);
            if (str != null && !this.mMime.equalsIgnoreCase(str)) {
                return false;
            }
            for (Feature feature : getValidFeatures()) {
                Integer num = (Integer) map.get(MediaFormat.KEY_FEATURE_ + feature.mName);
                if (num != null && ((num.intValue() == 1 && !isFeatureSupported(feature.mName)) || (num.intValue() == 0 && isFeatureRequired(feature.mName)))) {
                    return false;
                }
            }
            Integer num2 = (Integer) map.get(MediaFormat.KEY_PROFILE);
            Integer num3 = (Integer) map.get("level");
            if (num2 != null) {
                if (!supportsProfileLevel(num2.intValue(), num3)) {
                    return false;
                }
                int i = 0;
                for (CodecProfileLevel codecProfileLevel : this.profileLevels) {
                    if (codecProfileLevel.profile == num2.intValue() && codecProfileLevel.level > i && (!this.mMime.equalsIgnoreCase("video/3gpp") || codecProfileLevel.level != 16 || i == 1)) {
                        i = codecProfileLevel.level;
                    }
                }
                CodecCapabilities codecCapabilitiesCreateFromProfileLevel = createFromProfileLevel(this.mMime, num2.intValue(), i);
                HashMap map2 = new HashMap(map);
                map2.remove(MediaFormat.KEY_PROFILE);
                MediaFormat mediaFormat2 = new MediaFormat(map2);
                if (codecCapabilitiesCreateFromProfileLevel != null && !codecCapabilitiesCreateFromProfileLevel.isFormatSupported(mediaFormat2)) {
                    return false;
                }
            }
            AudioCapabilities audioCapabilities = this.mAudioCaps;
            if (audioCapabilities != null && !audioCapabilities.supportsFormat(mediaFormat)) {
                return false;
            }
            VideoCapabilities videoCapabilities = this.mVideoCaps;
            if (videoCapabilities != null && !videoCapabilities.supportsFormat(mediaFormat)) {
                return false;
            }
            EncoderCapabilities encoderCapabilities = this.mEncoderCaps;
            return encoderCapabilities == null || encoderCapabilities.supportsFormat(mediaFormat);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean supportsBitrate(Range<Integer> range, MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            Integer numValueOf = (Integer) map.get(MediaFormat.KEY_MAX_BIT_RATE);
            Integer num = (Integer) map.get(MediaFormat.KEY_BIT_RATE);
            if (num != null) {
                numValueOf = numValueOf != null ? Integer.valueOf(Math.max(num.intValue(), numValueOf.intValue())) : num;
            }
            if (numValueOf == null || numValueOf.intValue() <= 0) {
                return true;
            }
            return range.contains(numValueOf);
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x0083  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean supportsProfileLevel(int r10, java.lang.Integer r11) {
            /*
                r9 = this;
                android.media.MediaCodecInfo$CodecProfileLevel[] r0 = r9.profileLevels
                int r1 = r0.length
                r2 = 0
                r3 = r2
            L5:
                if (r3 >= r1) goto La9
                r4 = r0[r3]
                int r5 = r4.profile
                if (r5 == r10) goto Lf
                goto La4
            Lf:
                r5 = 1
                if (r11 == 0) goto La8
                java.lang.String r6 = r9.mMime
                java.lang.String r7 = "audio/mp4a-latm"
                boolean r6 = r6.equalsIgnoreCase(r7)
                if (r6 == 0) goto L1e
                goto La8
            L1e:
                java.lang.String r6 = r9.mMime
                java.lang.String r7 = "video/3gpp"
                boolean r6 = r6.equalsIgnoreCase(r7)
                if (r6 == 0) goto L3f
                int r6 = r4.level
                int r7 = r11.intValue()
                if (r6 == r7) goto L3f
                int r6 = r4.level
                r7 = 16
                if (r6 != r7) goto L3f
                int r6 = r11.intValue()
                if (r6 <= r5) goto L3f
                goto La4
            L3f:
                java.lang.String r6 = r9.mMime
                java.lang.String r7 = "video/mp4v-es"
                boolean r6 = r6.equalsIgnoreCase(r7)
                if (r6 == 0) goto L5e
                int r6 = r4.level
                int r7 = r11.intValue()
                if (r6 == r7) goto L5e
                int r6 = r4.level
                r7 = 4
                if (r6 != r7) goto L5e
                int r6 = r11.intValue()
                if (r6 <= r5) goto L5e
                goto La4
            L5e:
                java.lang.String r6 = r9.mMime
                java.lang.String r7 = "video/hevc"
                boolean r6 = r6.equalsIgnoreCase(r7)
                if (r6 == 0) goto L83
                int r6 = r4.level
                r7 = 44739242(0x2aaaaaa, float:2.5077211E-37)
                r6 = r6 & r7
                if (r6 == 0) goto L73
                r6 = r5
                goto L74
            L73:
                r6 = r2
            L74:
                int r8 = r11.intValue()
                r7 = r7 & r8
                if (r7 == 0) goto L7d
                r7 = r5
                goto L7e
            L7d:
                r7 = r2
            L7e:
                if (r7 == 0) goto L83
                if (r6 != 0) goto L83
                goto La4
            L83:
                int r6 = r4.level
                int r7 = r11.intValue()
                if (r6 < r7) goto La4
                java.lang.String r0 = r9.mMime
                int r1 = r4.level
                android.media.MediaCodecInfo$CodecCapabilities r0 = createFromProfileLevel(r0, r10, r1)
                if (r0 == 0) goto La3
                java.lang.String r0 = r9.mMime
                int r11 = r11.intValue()
                android.media.MediaCodecInfo$CodecCapabilities r10 = createFromProfileLevel(r0, r10, r11)
                if (r10 == 0) goto La2
                r2 = r5
            La2:
                return r2
            La3:
                return r5
            La4:
                int r3 = r3 + 1
                goto L5
            La8:
                return r5
            La9:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.MediaCodecInfo.CodecCapabilities.supportsProfileLevel(int, java.lang.Integer):boolean");
        }

        public MediaFormat getDefaultFormat() {
            return this.mDefaultFormat;
        }

        public String getMimeType() {
            return this.mMime;
        }

        public int getMaxSupportedInstances() {
            return this.mMaxSupportedInstances;
        }

        private boolean isAudio() {
            return this.mAudioCaps != null;
        }

        public AudioCapabilities getAudioCapabilities() {
            return this.mAudioCaps;
        }

        private boolean isEncoder() {
            return this.mEncoderCaps != null;
        }

        public EncoderCapabilities getEncoderCapabilities() {
            return this.mEncoderCaps;
        }

        private boolean isVideo() {
            return this.mVideoCaps != null;
        }

        public VideoCapabilities getVideoCapabilities() {
            return this.mVideoCaps;
        }

        public CodecCapabilities dup() {
            CodecCapabilities codecCapabilities = new CodecCapabilities();
            CodecProfileLevel[] codecProfileLevelArr = this.profileLevels;
            codecCapabilities.profileLevels = (CodecProfileLevel[]) Arrays.copyOf(codecProfileLevelArr, codecProfileLevelArr.length);
            int[] iArr = this.colorFormats;
            codecCapabilities.colorFormats = Arrays.copyOf(iArr, iArr.length);
            codecCapabilities.mMime = this.mMime;
            codecCapabilities.mMaxSupportedInstances = this.mMaxSupportedInstances;
            codecCapabilities.mFlagsRequired = this.mFlagsRequired;
            codecCapabilities.mFlagsSupported = this.mFlagsSupported;
            codecCapabilities.mFlagsVerified = this.mFlagsVerified;
            codecCapabilities.mAudioCaps = this.mAudioCaps;
            codecCapabilities.mVideoCaps = this.mVideoCaps;
            codecCapabilities.mEncoderCaps = this.mEncoderCaps;
            codecCapabilities.mDefaultFormat = this.mDefaultFormat;
            codecCapabilities.mCapabilitiesInfo = this.mCapabilitiesInfo;
            return codecCapabilities;
        }

        public static CodecCapabilities createFromProfileLevel(String str, int i, int i2) {
            CodecProfileLevel codecProfileLevel = new CodecProfileLevel();
            codecProfileLevel.profile = i;
            codecProfileLevel.level = i2;
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString(MediaFormat.KEY_MIME, str);
            CodecCapabilities codecCapabilities = new CodecCapabilities(new CodecProfileLevel[]{codecProfileLevel}, new int[0], true, mediaFormat, new MediaFormat());
            if (codecCapabilities.mError != 0) {
                return null;
            }
            return codecCapabilities;
        }

        CodecCapabilities(CodecProfileLevel[] codecProfileLevelArr, int[] iArr, boolean z, Map<String, Object> map, Map<String, Object> map2) {
            this(codecProfileLevelArr, iArr, z, new MediaFormat(map), new MediaFormat(map2));
        }

        CodecCapabilities(CodecProfileLevel[] codecProfileLevelArr, int[] iArr, boolean z, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            Map<String, Object> map = mediaFormat2.getMap();
            this.colorFormats = iArr;
            this.mFlagsVerified = 0;
            this.mDefaultFormat = mediaFormat;
            this.mCapabilitiesInfo = mediaFormat2;
            String string = mediaFormat.getString(MediaFormat.KEY_MIME);
            this.mMime = string;
            if (codecProfileLevelArr.length == 0 && string.equalsIgnoreCase(MediaFormat.MIMETYPE_VIDEO_VP9)) {
                CodecProfileLevel codecProfileLevel = new CodecProfileLevel();
                codecProfileLevel.profile = 1;
                codecProfileLevel.level = VideoCapabilities.equivalentVP9Level(mediaFormat2);
                codecProfileLevelArr = new CodecProfileLevel[]{codecProfileLevel};
            }
            this.profileLevels = codecProfileLevelArr;
            if (this.mMime.toLowerCase().startsWith("audio/")) {
                AudioCapabilities audioCapabilitiesCreate = AudioCapabilities.create(mediaFormat2, this);
                this.mAudioCaps = audioCapabilitiesCreate;
                audioCapabilitiesCreate.getDefaultFormat(this.mDefaultFormat);
            } else if (this.mMime.toLowerCase().startsWith("video/") || this.mMime.equalsIgnoreCase(MediaFormat.MIMETYPE_IMAGE_ANDROID_HEIC)) {
                this.mVideoCaps = VideoCapabilities.create(mediaFormat2, this);
            }
            if (z) {
                EncoderCapabilities encoderCapabilitiesCreate = EncoderCapabilities.create(mediaFormat2, this);
                this.mEncoderCaps = encoderCapabilitiesCreate;
                encoderCapabilitiesCreate.getDefaultFormat(this.mDefaultFormat);
            }
            this.mMaxSupportedInstances = Utils.parseIntSafely(MediaCodecList.getGlobalSettings().get("max-concurrent-instances"), 32);
            this.mMaxSupportedInstances = ((Integer) Range.create(1, 256).clamp(Integer.valueOf(Utils.parseIntSafely(map.get("max-concurrent-instances"), this.mMaxSupportedInstances)))).intValue();
            for (Feature feature : getValidFeatures()) {
                String str = MediaFormat.KEY_FEATURE_ + feature.mName;
                Integer num = (Integer) map.get(str);
                if (num != null) {
                    if (num.intValue() > 0) {
                        this.mFlagsRequired |= feature.mValue;
                    }
                    this.mFlagsSupported = feature.mValue | this.mFlagsSupported;
                    this.mDefaultFormat.setInteger(str, 1);
                }
            }
        }
    }

    public static final class AudioCapabilities {
        private static final int MAX_INPUT_CHANNEL_COUNT = 30;
        private static final String TAG = "AudioCapabilities";
        private Range<Integer> mBitrateRange;
        private int mMaxInputChannelCount;
        private CodecCapabilities mParent;
        private Range<Integer>[] mSampleRateRanges;
        private int[] mSampleRates;

        public Range<Integer> getBitrateRange() {
            return this.mBitrateRange;
        }

        public int[] getSupportedSampleRates() {
            int[] iArr = this.mSampleRates;
            if (iArr != null) {
                return Arrays.copyOf(iArr, iArr.length);
            }
            return null;
        }

        public Range<Integer>[] getSupportedSampleRateRanges() {
            Range<Integer>[] rangeArr = this.mSampleRateRanges;
            return (Range[]) Arrays.copyOf(rangeArr, rangeArr.length);
        }

        public int getMaxInputChannelCount() {
            return this.mMaxInputChannelCount;
        }

        private AudioCapabilities() {
        }

        public static AudioCapabilities create(MediaFormat mediaFormat, CodecCapabilities codecCapabilities) {
            AudioCapabilities audioCapabilities = new AudioCapabilities();
            audioCapabilities.init(mediaFormat, codecCapabilities);
            return audioCapabilities;
        }

        private void init(MediaFormat mediaFormat, CodecCapabilities codecCapabilities) {
            this.mParent = codecCapabilities;
            initWithPlatformLimits();
            applyLevelLimits();
            parseFromInfo(mediaFormat);
        }

        private void initWithPlatformLimits() {
            this.mBitrateRange = Range.create(0, Integer.MAX_VALUE);
            this.mMaxInputChannelCount = 30;
            this.mSampleRateRanges = new Range[]{Range.create(Integer.valueOf(SystemProperties.getInt("ro.mediacodec.min_sample_rate", 7350)), Integer.valueOf(SystemProperties.getInt("ro.mediacodec.max_sample_rate", AudioFormat.SAMPLE_RATE_HZ_MAX)))};
            this.mSampleRates = null;
        }

        private boolean supports(Integer num, Integer num2) {
            if (num2 == null || (num2.intValue() >= 1 && num2.intValue() <= this.mMaxInputChannelCount)) {
                return num == null || Utils.binarySearchDistinctRanges(this.mSampleRateRanges, num) >= 0;
            }
            return false;
        }

        public boolean isSampleRateSupported(int i) {
            return supports(Integer.valueOf(i), null);
        }

        private void limitSampleRates(int[] iArr) {
            Arrays.sort(iArr);
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                if (supports(Integer.valueOf(i), null)) {
                    arrayList.add(Range.create(Integer.valueOf(i), Integer.valueOf(i)));
                }
            }
            this.mSampleRateRanges = (Range[]) arrayList.toArray(new Range[arrayList.size()]);
            createDiscreteSampleRates();
        }

        private void createDiscreteSampleRates() {
            this.mSampleRates = new int[this.mSampleRateRanges.length];
            int i = 0;
            while (true) {
                Range<Integer>[] rangeArr = this.mSampleRateRanges;
                if (i >= rangeArr.length) {
                    return;
                }
                this.mSampleRates[i] = ((Integer) rangeArr[i].getLower()).intValue();
                i++;
            }
        }

        private void limitSampleRates(Range<Integer>[] rangeArr) {
            Utils.sortDistinctRanges(rangeArr);
            Range<Integer>[] rangeArrIntersectSortedDistinctRanges = Utils.intersectSortedDistinctRanges(this.mSampleRateRanges, rangeArr);
            this.mSampleRateRanges = rangeArrIntersectSortedDistinctRanges;
            for (Range<Integer> range : rangeArrIntersectSortedDistinctRanges) {
                if (!((Integer) range.getLower()).equals(range.getUpper())) {
                    this.mSampleRates = null;
                    return;
                }
            }
            createDiscreteSampleRates();
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x01de  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01e2  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void applyLevelLimits() {
            /*
                Method dump skipped, instruction units count: 574
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.MediaCodecInfo.AudioCapabilities.applyLevelLimits():void");
        }

        private void applyLimits(int i, Range<Integer> range) {
            this.mMaxInputChannelCount = ((Integer) Range.create(1, Integer.valueOf(this.mMaxInputChannelCount)).clamp(Integer.valueOf(i))).intValue();
            if (range != null) {
                this.mBitrateRange = this.mBitrateRange.intersect(range);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void parseFromInfo(MediaFormat mediaFormat) {
            Range rangeIntersect = MediaCodecInfo.POSITIVE_INTEGERS;
            int intSafely = 0;
            if (mediaFormat.containsKey("sample-rate-ranges")) {
                String[] strArrSplit = mediaFormat.getString("sample-rate-ranges").split(",");
                Range[] rangeArr = new Range[strArrSplit.length];
                for (int i = 0; i < strArrSplit.length; i++) {
                    rangeArr[i] = Utils.parseIntRange(strArrSplit[i], null);
                }
                limitSampleRates((Range<Integer>[]) rangeArr);
            }
            if (mediaFormat.containsKey("max-channel-count")) {
                intSafely = Utils.parseIntSafely(mediaFormat.getString("max-channel-count"), 30);
            } else if ((this.mParent.mError & 2) == 0) {
                intSafely = 30;
            }
            if (mediaFormat.containsKey("bitrate-range")) {
                rangeIntersect = rangeIntersect.intersect(Utils.parseIntRange(mediaFormat.getString("bitrate-range"), rangeIntersect));
            }
            applyLimits(intSafely, rangeIntersect);
        }

        public void getDefaultFormat(MediaFormat mediaFormat) {
            if (((Integer) this.mBitrateRange.getLower()).equals(this.mBitrateRange.getUpper())) {
                mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, ((Integer) this.mBitrateRange.getLower()).intValue());
            }
            if (this.mMaxInputChannelCount == 1) {
                mediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 1);
            }
            int[] iArr = this.mSampleRates;
            if (iArr == null || iArr.length != 1) {
                return;
            }
            mediaFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE, iArr[0]);
        }

        public boolean supportsFormat(MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            return supports((Integer) map.get(MediaFormat.KEY_SAMPLE_RATE), (Integer) map.get(MediaFormat.KEY_CHANNEL_COUNT)) && CodecCapabilities.supportsBitrate(this.mBitrateRange, mediaFormat);
        }
    }

    public static final class VideoCapabilities {
        private static final String TAG = "VideoCapabilities";
        private boolean mAllowMbOverride;
        private Range<Rational> mAspectRatioRange;
        private Range<Integer> mBitrateRange;
        private Range<Rational> mBlockAspectRatioRange;
        private Range<Integer> mBlockCountRange;
        private int mBlockHeight;
        private int mBlockWidth;
        private Range<Long> mBlocksPerSecondRange;
        private Range<Integer> mFrameRateRange;
        private int mHeightAlignment;
        private Range<Integer> mHeightRange;
        private Range<Integer> mHorizontalBlockRange;
        private Map<Size, Range<Long>> mMeasuredFrameRates;
        private CodecCapabilities mParent;
        private List<PerformancePoint> mPerformancePoints;
        private int mSmallerDimensionUpperLimit;
        private Range<Integer> mVerticalBlockRange;
        private int mWidthAlignment;
        private Range<Integer> mWidthRange;

        public Range<Integer> getBitrateRange() {
            return this.mBitrateRange;
        }

        public Range<Integer> getSupportedWidths() {
            return this.mWidthRange;
        }

        public Range<Integer> getSupportedHeights() {
            return this.mHeightRange;
        }

        public int getWidthAlignment() {
            return this.mWidthAlignment;
        }

        public int getHeightAlignment() {
            return this.mHeightAlignment;
        }

        public int getSmallerDimensionUpperLimit() {
            return this.mSmallerDimensionUpperLimit;
        }

        public Range<Integer> getSupportedFrameRates() {
            return this.mFrameRateRange;
        }

        public Range<Integer> getSupportedWidthsFor(int i) {
            try {
                Range<Integer> range = this.mWidthRange;
                if (!this.mHeightRange.contains(Integer.valueOf(i)) || i % this.mHeightAlignment != 0) {
                    throw new IllegalArgumentException("unsupported height");
                }
                int iDivUp = Utils.divUp(i, this.mBlockHeight);
                double d = iDivUp;
                Range rangeIntersect = range.intersect(Integer.valueOf(((Math.max(Utils.divUp(((Integer) this.mBlockCountRange.getLower()).intValue(), iDivUp), (int) Math.ceil(((Rational) this.mBlockAspectRatioRange.getLower()).doubleValue() * d)) - 1) * this.mBlockWidth) + this.mWidthAlignment), Integer.valueOf(Math.min(((Integer) this.mBlockCountRange.getUpper()).intValue() / iDivUp, (int) (((Rational) this.mBlockAspectRatioRange.getUpper()).doubleValue() * d)) * this.mBlockWidth));
                if (i > this.mSmallerDimensionUpperLimit) {
                    rangeIntersect = rangeIntersect.intersect(1, Integer.valueOf(this.mSmallerDimensionUpperLimit));
                }
                double d2 = i;
                return rangeIntersect.intersect(Integer.valueOf((int) Math.ceil(((Rational) this.mAspectRatioRange.getLower()).doubleValue() * d2)), Integer.valueOf((int) (((Rational) this.mAspectRatioRange.getUpper()).doubleValue() * d2)));
            } catch (IllegalArgumentException unused) {
                Log.v(TAG, "could not get supported widths for " + i);
                throw new IllegalArgumentException("unsupported height");
            }
        }

        public Range<Integer> getSupportedHeightsFor(int i) {
            try {
                Range<Integer> range = this.mHeightRange;
                if (!this.mWidthRange.contains(Integer.valueOf(i)) || i % this.mWidthAlignment != 0) {
                    throw new IllegalArgumentException("unsupported width");
                }
                int iDivUp = Utils.divUp(i, this.mBlockWidth);
                double d = iDivUp;
                Range rangeIntersect = range.intersect(Integer.valueOf(((Math.max(Utils.divUp(((Integer) this.mBlockCountRange.getLower()).intValue(), iDivUp), (int) Math.ceil(d / ((Rational) this.mBlockAspectRatioRange.getUpper()).doubleValue())) - 1) * this.mBlockHeight) + this.mHeightAlignment), Integer.valueOf(Math.min(((Integer) this.mBlockCountRange.getUpper()).intValue() / iDivUp, (int) (d / ((Rational) this.mBlockAspectRatioRange.getLower()).doubleValue())) * this.mBlockHeight));
                if (i > this.mSmallerDimensionUpperLimit) {
                    rangeIntersect = rangeIntersect.intersect(1, Integer.valueOf(this.mSmallerDimensionUpperLimit));
                }
                double d2 = i;
                return rangeIntersect.intersect(Integer.valueOf((int) Math.ceil(d2 / ((Rational) this.mAspectRatioRange.getUpper()).doubleValue())), Integer.valueOf((int) (d2 / ((Rational) this.mAspectRatioRange.getLower()).doubleValue())));
            } catch (IllegalArgumentException unused) {
                Log.v(TAG, "could not get supported heights for " + i);
                throw new IllegalArgumentException("unsupported width");
            }
        }

        public Range<Double> getSupportedFrameRatesFor(int i, int i2) {
            if (!supports(Integer.valueOf(i), Integer.valueOf(i2), null)) {
                throw new IllegalArgumentException("unsupported size");
            }
            double dDivUp = Utils.divUp(i, this.mBlockWidth) * Utils.divUp(i2, this.mBlockHeight);
            return Range.create(Double.valueOf(Math.max(((Long) this.mBlocksPerSecondRange.getLower()).longValue() / dDivUp, ((Integer) this.mFrameRateRange.getLower()).intValue())), Double.valueOf(Math.min(((Long) this.mBlocksPerSecondRange.getUpper()).longValue() / dDivUp, ((Integer) this.mFrameRateRange.getUpper()).intValue())));
        }

        private int getBlockCount(int i, int i2) {
            return Utils.divUp(i, this.mBlockWidth) * Utils.divUp(i2, this.mBlockHeight);
        }

        private Size findClosestSize(int i, int i2) {
            int blockCount = getBlockCount(i, i2);
            Size size = null;
            int i3 = Integer.MAX_VALUE;
            for (Size size2 : this.mMeasuredFrameRates.keySet()) {
                int iAbs = Math.abs(blockCount - getBlockCount(size2.getWidth(), size2.getHeight()));
                if (iAbs < i3) {
                    size = size2;
                    i3 = iAbs;
                }
            }
            return size;
        }

        private Range<Double> estimateFrameRatesFor(int i, int i2) {
            Size sizeFindClosestSize = findClosestSize(i, i2);
            Range<Long> range = this.mMeasuredFrameRates.get(sizeFindClosestSize);
            Double dValueOf = Double.valueOf(((double) getBlockCount(sizeFindClosestSize.getWidth(), sizeFindClosestSize.getHeight())) / ((double) Math.max(getBlockCount(i, i2), 1)));
            return Range.create(Double.valueOf(((Long) range.getLower()).longValue() * dValueOf.doubleValue()), Double.valueOf(((Long) range.getUpper()).longValue() * dValueOf.doubleValue()));
        }

        public Range<Double> getAchievableFrameRatesFor(int i, int i2) {
            if (!supports(Integer.valueOf(i), Integer.valueOf(i2), null)) {
                throw new IllegalArgumentException("unsupported size");
            }
            Map<Size, Range<Long>> map = this.mMeasuredFrameRates;
            if (map == null || map.size() <= 0) {
                Log.w(TAG, "Codec did not publish any measurement data.");
                return null;
            }
            return estimateFrameRatesFor(i, i2);
        }

        public static final class PerformancePoint {
            private Size mBlockSize;
            private int mHeight;
            private int mMaxFrameRate;
            private long mMaxMacroBlockRate;
            private int mWidth;
            public static final PerformancePoint SD_24 = new PerformancePoint(720, 480, 24);
            public static final PerformancePoint SD_25 = new PerformancePoint(720, 576, 25);
            public static final PerformancePoint SD_30 = new PerformancePoint(720, 480, 30);
            public static final PerformancePoint SD_48 = new PerformancePoint(720, 480, 48);
            public static final PerformancePoint SD_50 = new PerformancePoint(720, 576, 50);
            public static final PerformancePoint SD_60 = new PerformancePoint(720, 480, 60);
            public static final PerformancePoint HD_24 = new PerformancePoint(1280, 720, 24);
            public static final PerformancePoint HD_25 = new PerformancePoint(1280, 720, 25);
            public static final PerformancePoint HD_30 = new PerformancePoint(1280, 720, 30);
            public static final PerformancePoint HD_50 = new PerformancePoint(1280, 720, 50);
            public static final PerformancePoint HD_60 = new PerformancePoint(1280, 720, 60);
            public static final PerformancePoint HD_100 = new PerformancePoint(1280, 720, 100);
            public static final PerformancePoint HD_120 = new PerformancePoint(1280, 720, 120);
            public static final PerformancePoint HD_200 = new PerformancePoint(1280, 720, 200);
            public static final PerformancePoint HD_240 = new PerformancePoint(1280, 720, 240);
            public static final PerformancePoint FHD_24 = new PerformancePoint(1920, 1080, 24);
            public static final PerformancePoint FHD_25 = new PerformancePoint(1920, 1080, 25);
            public static final PerformancePoint FHD_30 = new PerformancePoint(1920, 1080, 30);
            public static final PerformancePoint FHD_50 = new PerformancePoint(1920, 1080, 50);
            public static final PerformancePoint FHD_60 = new PerformancePoint(1920, 1080, 60);
            public static final PerformancePoint FHD_100 = new PerformancePoint(1920, 1080, 100);
            public static final PerformancePoint FHD_120 = new PerformancePoint(1920, 1080, 120);
            public static final PerformancePoint FHD_200 = new PerformancePoint(1920, 1080, 200);
            public static final PerformancePoint FHD_240 = new PerformancePoint(1920, 1080, 240);
            public static final PerformancePoint UHD_24 = new PerformancePoint(3840, 2160, 24);
            public static final PerformancePoint UHD_25 = new PerformancePoint(3840, 2160, 25);
            public static final PerformancePoint UHD_30 = new PerformancePoint(3840, 2160, 30);
            public static final PerformancePoint UHD_50 = new PerformancePoint(3840, 2160, 50);
            public static final PerformancePoint UHD_60 = new PerformancePoint(3840, 2160, 60);
            public static final PerformancePoint UHD_100 = new PerformancePoint(3840, 2160, 100);
            public static final PerformancePoint UHD_120 = new PerformancePoint(3840, 2160, 120);
            public static final PerformancePoint UHD_200 = new PerformancePoint(3840, 2160, 200);
            public static final PerformancePoint UHD_240 = new PerformancePoint(3840, 2160, 240);

            private int saturateLongToInt(long j) {
                if (j < -2147483648L) {
                    return Integer.MIN_VALUE;
                }
                if (j > 2147483647L) {
                    return Integer.MAX_VALUE;
                }
                return (int) j;
            }

            public int getMaxMacroBlocks() {
                return saturateLongToInt(((long) this.mWidth) * ((long) this.mHeight));
            }

            public int getMaxFrameRate() {
                return this.mMaxFrameRate;
            }

            public long getMaxMacroBlockRate() {
                return this.mMaxMacroBlockRate;
            }

            public String toString() {
                int width = this.mBlockSize.getWidth() * 16;
                int height = this.mBlockSize.getHeight() * 16;
                int iDivUp = (int) Utils.divUp(this.mMaxMacroBlockRate, getMaxMacroBlocks());
                String str = (this.mWidth * 16) + "x" + (this.mHeight * 16) + "@" + iDivUp;
                if (iDivUp < this.mMaxFrameRate) {
                    str = str + ", max " + this.mMaxFrameRate + "fps";
                }
                if (width > 16 || height > 16) {
                    str = str + ", " + width + "x" + height + " blocks";
                }
                return "PerformancePoint(" + str + ")";
            }

            public int hashCode() {
                return this.mMaxFrameRate;
            }

            public PerformancePoint(int i, int i2, int i3, int i4, Size size) {
                MediaCodecInfo.checkPowerOfTwo(size.getWidth(), "block width");
                MediaCodecInfo.checkPowerOfTwo(size.getHeight(), "block height");
                this.mBlockSize = new Size(Utils.divUp(size.getWidth(), 16), Utils.divUp(size.getHeight(), 16));
                this.mWidth = (int) (Utils.divUp(Math.max(1L, i), Math.max(size.getWidth(), 16)) * ((long) this.mBlockSize.getWidth()));
                this.mHeight = (int) (Utils.divUp(Math.max(1L, i2), Math.max(size.getHeight(), 16)) * ((long) this.mBlockSize.getHeight()));
                this.mMaxFrameRate = Math.max(1, Math.max(i3, i4));
                this.mMaxMacroBlockRate = Math.max(1, i3) * getMaxMacroBlocks();
            }

            public PerformancePoint(PerformancePoint performancePoint, Size size) {
                this(performancePoint.mWidth * 16, performancePoint.mHeight * 16, (int) Utils.divUp(performancePoint.mMaxMacroBlockRate, performancePoint.getMaxMacroBlocks()), performancePoint.mMaxFrameRate, new Size(Math.max(size.getWidth(), performancePoint.mBlockSize.getWidth() * 16), Math.max(size.getHeight(), performancePoint.mBlockSize.getHeight() * 16)));
            }

            public PerformancePoint(int i, int i2, int i3) {
                this(i, i2, i3, i3, new Size(16, 16));
            }

            private int align(int i, int i2) {
                return Utils.divUp(i, i2) * i2;
            }

            private void checkPowerOfTwo2(int i, String str) {
                if (i == 0 || ((i - 1) & i) != 0) {
                    throw new IllegalArgumentException(str + " (" + i + ") must be a power of 2");
                }
            }

            public boolean covers(MediaFormat mediaFormat) {
                return covers(new PerformancePoint(mediaFormat.getInteger("width", 0), mediaFormat.getInteger("height", 0), Math.round((float) Math.ceil(mediaFormat.getNumber(MediaFormat.KEY_FRAME_RATE, 0).doubleValue()))));
            }

            public boolean covers(PerformancePoint performancePoint) {
                Size commonBlockSize = getCommonBlockSize(performancePoint);
                PerformancePoint performancePoint2 = new PerformancePoint(this, commonBlockSize);
                PerformancePoint performancePoint3 = new PerformancePoint(performancePoint, commonBlockSize);
                return performancePoint2.getMaxMacroBlocks() >= performancePoint3.getMaxMacroBlocks() && performancePoint2.mMaxFrameRate >= performancePoint3.mMaxFrameRate && performancePoint2.mMaxMacroBlockRate >= performancePoint3.mMaxMacroBlockRate;
            }

            private Size getCommonBlockSize(PerformancePoint performancePoint) {
                return new Size(Math.max(this.mBlockSize.getWidth(), performancePoint.mBlockSize.getWidth()) * 16, Math.max(this.mBlockSize.getHeight(), performancePoint.mBlockSize.getHeight()) * 16);
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof PerformancePoint)) {
                    return false;
                }
                PerformancePoint performancePoint = (PerformancePoint) obj;
                Size commonBlockSize = getCommonBlockSize(performancePoint);
                PerformancePoint performancePoint2 = new PerformancePoint(this, commonBlockSize);
                PerformancePoint performancePoint3 = new PerformancePoint(performancePoint, commonBlockSize);
                return performancePoint2.getMaxMacroBlocks() == performancePoint3.getMaxMacroBlocks() && performancePoint2.mMaxFrameRate == performancePoint3.mMaxFrameRate && performancePoint2.mMaxMacroBlockRate == performancePoint3.mMaxMacroBlockRate;
            }
        }

        public List<PerformancePoint> getSupportedPerformancePoints() {
            return this.mPerformancePoints;
        }

        public boolean areSizeAndRateSupported(int i, int i2, double d) {
            return supports(Integer.valueOf(i), Integer.valueOf(i2), Double.valueOf(d));
        }

        public boolean isSizeSupported(int i, int i2) {
            return supports(Integer.valueOf(i), Integer.valueOf(i2), null);
        }

        private boolean supports(Integer num, Integer num2, Number number) {
            boolean z = false;
            boolean zContains = num == null || (this.mWidthRange.contains(num) && num.intValue() % this.mWidthAlignment == 0);
            if (zContains && num2 != null) {
                zContains = this.mHeightRange.contains(num2) && num2.intValue() % this.mHeightAlignment == 0;
            }
            if (zContains && number != null) {
                zContains = this.mFrameRateRange.contains(Utils.intRangeFor(number.doubleValue()));
            }
            if (!zContains || num2 == null || num == null) {
                return zContains;
            }
            boolean z2 = Math.min(num2.intValue(), num.intValue()) <= this.mSmallerDimensionUpperLimit;
            int iDivUp = Utils.divUp(num.intValue(), this.mBlockWidth);
            int iDivUp2 = Utils.divUp(num2.intValue(), this.mBlockHeight);
            int i = iDivUp * iDivUp2;
            if (z2 && this.mBlockCountRange.contains(Integer.valueOf(i)) && this.mBlockAspectRatioRange.contains(new Rational(iDivUp, iDivUp2)) && this.mAspectRatioRange.contains(new Rational(num.intValue(), num2.intValue()))) {
                z = true;
            }
            if (!z || number == null) {
                return z;
            }
            return this.mBlocksPerSecondRange.contains(Utils.longRangeFor(((double) i) * number.doubleValue()));
        }

        public boolean supportsFormat(MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            return supports((Integer) map.get("width"), (Integer) map.get("height"), (Number) map.get(MediaFormat.KEY_FRAME_RATE)) && CodecCapabilities.supportsBitrate(this.mBitrateRange, mediaFormat);
        }

        private VideoCapabilities() {
        }

        public static VideoCapabilities create(MediaFormat mediaFormat, CodecCapabilities codecCapabilities) {
            VideoCapabilities videoCapabilities = new VideoCapabilities();
            videoCapabilities.init(mediaFormat, codecCapabilities);
            return videoCapabilities;
        }

        private void init(MediaFormat mediaFormat, CodecCapabilities codecCapabilities) {
            this.mParent = codecCapabilities;
            initWithPlatformLimits();
            applyLevelLimits();
            parseFromInfo(mediaFormat);
            updateLimits();
        }

        public Size getBlockSize() {
            return new Size(this.mBlockWidth, this.mBlockHeight);
        }

        public Range<Integer> getBlockCountRange() {
            return this.mBlockCountRange;
        }

        public Range<Long> getBlocksPerSecondRange() {
            return this.mBlocksPerSecondRange;
        }

        public Range<Rational> getAspectRatioRange(boolean z) {
            return z ? this.mBlockAspectRatioRange : this.mAspectRatioRange;
        }

        private void initWithPlatformLimits() {
            this.mBitrateRange = MediaCodecInfo.BITRATE_RANGE;
            this.mWidthRange = MediaCodecInfo.SIZE_RANGE;
            this.mHeightRange = MediaCodecInfo.SIZE_RANGE;
            this.mFrameRateRange = MediaCodecInfo.FRAME_RATE_RANGE;
            this.mHorizontalBlockRange = MediaCodecInfo.SIZE_RANGE;
            this.mVerticalBlockRange = MediaCodecInfo.SIZE_RANGE;
            this.mBlockCountRange = MediaCodecInfo.POSITIVE_INTEGERS;
            this.mBlocksPerSecondRange = MediaCodecInfo.POSITIVE_LONGS;
            this.mBlockAspectRatioRange = MediaCodecInfo.POSITIVE_RATIONALS;
            this.mAspectRatioRange = MediaCodecInfo.POSITIVE_RATIONALS;
            this.mWidthAlignment = 2;
            this.mHeightAlignment = 2;
            this.mBlockWidth = 2;
            this.mBlockHeight = 2;
            this.mSmallerDimensionUpperLimit = ((Integer) MediaCodecInfo.SIZE_RANGE.getUpper()).intValue();
        }

        private List<PerformancePoint> getPerformancePoints(Map<String, Object> map) {
            Size size;
            Range<Long> longRange;
            Vector vector = new Vector();
            for (String str : map.keySet()) {
                if (str.startsWith("performance-point-")) {
                    if (str.substring(18).equals("none") && vector.size() == 0) {
                        return Collections.unmodifiableList(vector);
                    }
                    String[] strArrSplit = str.split(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                    if (strArrSplit.length == 4 && (size = Utils.parseSize(strArrSplit[2], null)) != null && size.getWidth() * size.getHeight() > 0 && (longRange = Utils.parseLongRange(map.get(str), null)) != null && ((Long) longRange.getLower()).longValue() >= 0 && ((Long) longRange.getUpper()).longValue() >= 0) {
                        PerformancePoint performancePoint = new PerformancePoint(size.getWidth(), size.getHeight(), ((Long) longRange.getLower()).intValue(), ((Long) longRange.getUpper()).intValue(), new Size(this.mBlockWidth, this.mBlockHeight));
                        PerformancePoint performancePoint2 = new PerformancePoint(size.getHeight(), size.getWidth(), ((Long) longRange.getLower()).intValue(), ((Long) longRange.getUpper()).intValue(), new Size(this.mBlockWidth, this.mBlockHeight));
                        vector.add(performancePoint);
                        if (!performancePoint.covers(performancePoint2)) {
                            vector.add(performancePoint2);
                        }
                    }
                }
            }
            if (vector.size() == 0) {
                return null;
            }
            vector.sort(new Comparator() { // from class: android.media.-$$Lambda$MediaCodecInfo$VideoCapabilities$DpgwEn-gVFZT9EtP3qcxpiA2G0M
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return MediaCodecInfo.VideoCapabilities.lambda$getPerformancePoints$0((MediaCodecInfo.VideoCapabilities.PerformancePoint) obj, (MediaCodecInfo.VideoCapabilities.PerformancePoint) obj2);
                }
            });
            return Collections.unmodifiableList(vector);
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        static /* synthetic */ int lambda$getPerformancePoints$0(android.media.MediaCodecInfo.VideoCapabilities.PerformancePoint r6, android.media.MediaCodecInfo.VideoCapabilities.PerformancePoint r7) {
            /*
                int r0 = r6.getMaxMacroBlocks()
                int r1 = r7.getMaxMacroBlocks()
                r2 = -1
                r3 = 1
                if (r0 == r1) goto L19
                int r6 = r6.getMaxMacroBlocks()
                int r7 = r7.getMaxMacroBlocks()
                if (r6 >= r7) goto L17
                goto L48
            L17:
                r2 = r3
                goto L48
            L19:
                long r0 = r6.getMaxMacroBlockRate()
                long r4 = r7.getMaxMacroBlockRate()
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 == 0) goto L32
                long r0 = r6.getMaxMacroBlockRate()
                long r6 = r7.getMaxMacroBlockRate()
                int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                if (r6 >= 0) goto L17
                goto L48
            L32:
                int r0 = r6.getMaxFrameRate()
                int r1 = r7.getMaxFrameRate()
                if (r0 == r1) goto L47
                int r6 = r6.getMaxFrameRate()
                int r7 = r7.getMaxFrameRate()
                if (r6 >= r7) goto L17
                goto L48
            L47:
                r2 = 0
            L48:
                int r6 = -r2
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.MediaCodecInfo.VideoCapabilities.lambda$getPerformancePoints$0(android.media.MediaCodecInfo$VideoCapabilities$PerformancePoint, android.media.MediaCodecInfo$VideoCapabilities$PerformancePoint):int");
        }

        private Map<Size, Range<Long>> getMeasuredFrameRates(Map<String, Object> map) {
            Size size;
            Range<Long> longRange;
            HashMap map2 = new HashMap();
            for (String str : map.keySet()) {
                if (str.startsWith("measured-frame-rate-")) {
                    str.substring(20);
                    String[] strArrSplit = str.split(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                    if (strArrSplit.length == 5 && (size = Utils.parseSize(strArrSplit[3], null)) != null && size.getWidth() * size.getHeight() > 0 && (longRange = Utils.parseLongRange(map.get(str), null)) != null && ((Long) longRange.getLower()).longValue() >= 0 && ((Long) longRange.getUpper()).longValue() >= 0) {
                        map2.put(size, longRange);
                    }
                }
            }
            return map2;
        }

        private static Pair<Range<Integer>, Range<Integer>> parseWidthHeightRanges(Object obj) {
            Pair<Size, Size> sizeRange = Utils.parseSizeRange(obj);
            if (sizeRange == null) {
                return null;
            }
            try {
                return Pair.create(Range.create(Integer.valueOf(sizeRange.first.getWidth()), Integer.valueOf(sizeRange.second.getWidth())), Range.create(Integer.valueOf(sizeRange.first.getHeight()), Integer.valueOf(sizeRange.second.getHeight())));
            } catch (IllegalArgumentException unused) {
                Log.w(TAG, "could not parse size range '" + obj + "'");
                return null;
            }
        }

        public static int equivalentVP9Level(MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            Size size = Utils.parseSize(map.get("block-size"), new Size(8, 8));
            int width = size.getWidth() * size.getHeight();
            Range<Integer> intRange = Utils.parseIntRange(map.get("block-count-range"), null);
            int iIntValue = intRange == null ? 0 : ((Integer) intRange.getUpper()).intValue() * width;
            Range<Long> longRange = Utils.parseLongRange(map.get("blocks-per-second-range"), null);
            long jLongValue = longRange == null ? 0L : ((long) width) * ((Long) longRange.getUpper()).longValue();
            Pair<Range<Integer>, Range<Integer>> widthHeightRanges = parseWidthHeightRanges(map.get("size-range"));
            int iMax = widthHeightRanges == null ? 0 : Math.max(((Integer) widthHeightRanges.first.getUpper()).intValue(), ((Integer) widthHeightRanges.second.getUpper()).intValue());
            Range<Integer> intRange2 = Utils.parseIntRange(map.get("bitrate-range"), null);
            int iDivUp = intRange2 != null ? Utils.divUp(((Integer) intRange2.getUpper()).intValue(), 1000) : 0;
            if (jLongValue <= 829440 && iIntValue <= 36864 && iDivUp <= 200 && iMax <= 512) {
                return 1;
            }
            if (jLongValue <= 2764800 && iIntValue <= 73728 && iDivUp <= 800 && iMax <= 768) {
                return 2;
            }
            if (jLongValue <= 4608000 && iIntValue <= 122880 && iDivUp <= 1800 && iMax <= 960) {
                return 4;
            }
            if (jLongValue <= 9216000 && iIntValue <= 245760 && iDivUp <= 3600 && iMax <= 1344) {
                return 8;
            }
            if (jLongValue <= 20736000 && iIntValue <= 552960 && iDivUp <= 7200 && iMax <= 2048) {
                return 16;
            }
            if (jLongValue <= 36864000 && iIntValue <= 983040 && iDivUp <= 12000 && iMax <= 2752) {
                return 32;
            }
            if (jLongValue <= 83558400 && iIntValue <= 2228224 && iDivUp <= 18000 && iMax <= 4160) {
                return 64;
            }
            if (jLongValue <= 160432128 && iIntValue <= 2228224 && iDivUp <= 30000 && iMax <= 4160) {
                return 128;
            }
            if (jLongValue <= 311951360 && iIntValue <= 8912896 && iDivUp <= 60000 && iMax <= 8384) {
                return 256;
            }
            if (jLongValue <= 588251136 && iIntValue <= 8912896 && iDivUp <= 120000 && iMax <= 8384) {
                return 512;
            }
            if (jLongValue <= 1176502272 && iIntValue <= 8912896 && iDivUp <= 180000 && iMax <= 8384) {
                return 1024;
            }
            if (jLongValue <= 1176502272 && iIntValue <= 35651584 && iDivUp <= 180000 && iMax <= 16832) {
                return 2048;
            }
            if (jLongValue <= 2353004544L && iIntValue <= 35651584 && iDivUp <= 240000 && iMax <= 16832) {
                return 4096;
            }
            if (jLongValue > 4706009088L || iIntValue > 35651584 || iDivUp > 480000 || iMax <= 16832) {
            }
            return 8192;
        }

        private void parseFromInfo(MediaFormat mediaFormat) {
            Range<Integer> range;
            Range<Integer> range2;
            Range<Integer> rangeExtend;
            Range<Integer> range3;
            Range<Integer> range4;
            Range range5;
            Map<String, Object> map = mediaFormat.getMap();
            Size size = new Size(this.mBlockWidth, this.mBlockHeight);
            Size size2 = new Size(this.mWidthAlignment, this.mHeightAlignment);
            Size size3 = Utils.parseSize(map.get("block-size"), size);
            Size size4 = Utils.parseSize(map.get("alignment"), size2);
            Range rangeIntersect = null;
            Range<Integer> intRange = Utils.parseIntRange(map.get("block-count-range"), null);
            Range<Long> longRange = Utils.parseLongRange(map.get("blocks-per-second-range"), null);
            this.mMeasuredFrameRates = getMeasuredFrameRates(map);
            this.mPerformancePoints = getPerformancePoints(map);
            Pair<Range<Integer>, Range<Integer>> widthHeightRanges = parseWidthHeightRanges(map.get("size-range"));
            if (widthHeightRanges != null) {
                range2 = widthHeightRanges.first;
                range = widthHeightRanges.second;
            } else {
                range = null;
                range2 = null;
            }
            if (!map.containsKey("feature-can-swap-width-height")) {
                rangeExtend = range;
                range3 = range2;
            } else if (range2 != null) {
                this.mSmallerDimensionUpperLimit = Math.min(((Integer) range2.getUpper()).intValue(), ((Integer) range.getUpper()).intValue());
                rangeExtend = range2.extend(range);
                range3 = rangeExtend;
            } else {
                Log.w(TAG, "feature can-swap-width-height is best used with size-range");
                this.mSmallerDimensionUpperLimit = Math.min(((Integer) this.mWidthRange.getUpper()).intValue(), ((Integer) this.mHeightRange.getUpper()).intValue());
                Range rangeExtend2 = this.mWidthRange.extend(this.mHeightRange);
                this.mHeightRange = rangeExtend2;
                this.mWidthRange = rangeExtend2;
                rangeExtend = range;
                range3 = range2;
            }
            Range<Rational> rationalRange = Utils.parseRationalRange(map.get("block-aspect-ratio-range"), null);
            Range<Rational> rationalRange2 = Utils.parseRationalRange(map.get("pixel-aspect-ratio-range"), null);
            Range<Integer> intRange2 = Utils.parseIntRange(map.get("frame-rate-range"), null);
            if (intRange2 != null) {
                try {
                    intRange2 = intRange2.intersect(MediaCodecInfo.FRAME_RATE_RANGE);
                    range4 = intRange2;
                } catch (IllegalArgumentException unused) {
                    Log.w(TAG, "frame rate range (" + intRange2 + ") is out of limits: " + MediaCodecInfo.FRAME_RATE_RANGE);
                    range4 = null;
                }
            } else {
                range4 = intRange2;
            }
            Range<Integer> intRange3 = Utils.parseIntRange(map.get("bitrate-range"), null);
            if (intRange3 != null) {
                try {
                    rangeIntersect = intRange3.intersect(MediaCodecInfo.BITRATE_RANGE);
                } catch (IllegalArgumentException unused2) {
                    Log.w(TAG, "bitrate range (" + intRange3 + ") is out of limits: " + MediaCodecInfo.BITRATE_RANGE);
                }
                range5 = rangeIntersect;
            } else {
                range5 = intRange3;
            }
            MediaCodecInfo.checkPowerOfTwo(size3.getWidth(), "block-size width must be power of two");
            MediaCodecInfo.checkPowerOfTwo(size3.getHeight(), "block-size height must be power of two");
            MediaCodecInfo.checkPowerOfTwo(size4.getWidth(), "alignment width must be power of two");
            MediaCodecInfo.checkPowerOfTwo(size4.getHeight(), "alignment height must be power of two");
            Range range6 = range5;
            Range<Integer> range7 = range4;
            applyMacroBlockLimits(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, size3.getWidth(), size3.getHeight(), size4.getWidth(), size4.getHeight());
            if ((this.mParent.mError & 2) != 0 || this.mAllowMbOverride) {
                if (range3 != null) {
                    this.mWidthRange = MediaCodecInfo.SIZE_RANGE.intersect(range3);
                }
                if (rangeExtend != null) {
                    this.mHeightRange = MediaCodecInfo.SIZE_RANGE.intersect(rangeExtend);
                }
                if (intRange != null) {
                    this.mBlockCountRange = MediaCodecInfo.POSITIVE_INTEGERS.intersect(Utils.factorRange(intRange, ((this.mBlockWidth * this.mBlockHeight) / size3.getWidth()) / size3.getHeight()));
                }
                if (longRange != null) {
                    this.mBlocksPerSecondRange = MediaCodecInfo.POSITIVE_LONGS.intersect(Utils.factorRange(longRange, ((this.mBlockWidth * this.mBlockHeight) / size3.getWidth()) / size3.getHeight()));
                }
                if (rationalRange2 != null) {
                    this.mBlockAspectRatioRange = MediaCodecInfo.POSITIVE_RATIONALS.intersect(Utils.scaleRange(rationalRange2, this.mBlockHeight / size3.getHeight(), this.mBlockWidth / size3.getWidth()));
                }
                if (rationalRange != null) {
                    this.mAspectRatioRange = MediaCodecInfo.POSITIVE_RATIONALS.intersect(rationalRange);
                }
                if (range7 != null) {
                    this.mFrameRateRange = MediaCodecInfo.FRAME_RATE_RANGE.intersect(range7);
                }
                if (range6 != null) {
                    if ((this.mParent.mError & 2) != 0) {
                        this.mBitrateRange = MediaCodecInfo.BITRATE_RANGE.intersect(range6);
                    } else {
                        this.mBitrateRange = this.mBitrateRange.intersect(range6);
                    }
                }
            } else {
                if (range3 != null) {
                    this.mWidthRange = this.mWidthRange.intersect(range3);
                }
                if (rangeExtend != null) {
                    this.mHeightRange = this.mHeightRange.intersect(rangeExtend);
                }
                if (intRange != null) {
                    this.mBlockCountRange = this.mBlockCountRange.intersect(Utils.factorRange(intRange, ((this.mBlockWidth * this.mBlockHeight) / size3.getWidth()) / size3.getHeight()));
                }
                if (longRange != null) {
                    this.mBlocksPerSecondRange = this.mBlocksPerSecondRange.intersect(Utils.factorRange(longRange, ((this.mBlockWidth * this.mBlockHeight) / size3.getWidth()) / size3.getHeight()));
                }
                if (rationalRange2 != null) {
                    this.mBlockAspectRatioRange = this.mBlockAspectRatioRange.intersect(Utils.scaleRange(rationalRange2, this.mBlockHeight / size3.getHeight(), this.mBlockWidth / size3.getWidth()));
                }
                if (rationalRange != null) {
                    this.mAspectRatioRange = this.mAspectRatioRange.intersect(rationalRange);
                }
                if (range7 != null) {
                    this.mFrameRateRange = this.mFrameRateRange.intersect(range7);
                }
                if (range6 != null) {
                    this.mBitrateRange = this.mBitrateRange.intersect(range6);
                }
            }
            updateLimits();
        }

        private void applyBlockLimits(int i, int i2, Range<Integer> range, Range<Long> range2, Range<Rational> range3) {
            MediaCodecInfo.checkPowerOfTwo(i, "blockWidth must be a power of two");
            MediaCodecInfo.checkPowerOfTwo(i2, "blockHeight must be a power of two");
            int iMax = Math.max(i, this.mBlockWidth);
            int iMax2 = Math.max(i2, this.mBlockHeight);
            int i3 = iMax * iMax2;
            int i4 = (i3 / this.mBlockWidth) / this.mBlockHeight;
            if (i4 != 1) {
                this.mBlockCountRange = Utils.factorRange(this.mBlockCountRange, i4);
                this.mBlocksPerSecondRange = Utils.factorRange(this.mBlocksPerSecondRange, i4);
                this.mBlockAspectRatioRange = Utils.scaleRange(this.mBlockAspectRatioRange, iMax2 / this.mBlockHeight, iMax / this.mBlockWidth);
                this.mHorizontalBlockRange = Utils.factorRange(this.mHorizontalBlockRange, iMax / this.mBlockWidth);
                this.mVerticalBlockRange = Utils.factorRange(this.mVerticalBlockRange, iMax2 / this.mBlockHeight);
            }
            int i5 = (i3 / i) / i2;
            if (i5 != 1) {
                range = Utils.factorRange(range, i5);
                range2 = Utils.factorRange(range2, i5);
                range3 = Utils.scaleRange(range3, iMax2 / i2, iMax / i);
            }
            this.mBlockCountRange = this.mBlockCountRange.intersect(range);
            this.mBlocksPerSecondRange = this.mBlocksPerSecondRange.intersect(range2);
            this.mBlockAspectRatioRange = this.mBlockAspectRatioRange.intersect(range3);
            this.mBlockWidth = iMax;
            this.mBlockHeight = iMax2;
        }

        private void applyAlignment(int i, int i2) {
            MediaCodecInfo.checkPowerOfTwo(i, "widthAlignment must be a power of two");
            MediaCodecInfo.checkPowerOfTwo(i2, "heightAlignment must be a power of two");
            if (i > this.mBlockWidth || i2 > this.mBlockHeight) {
                applyBlockLimits(Math.max(i, this.mBlockWidth), Math.max(i2, this.mBlockHeight), MediaCodecInfo.POSITIVE_INTEGERS, MediaCodecInfo.POSITIVE_LONGS, MediaCodecInfo.POSITIVE_RATIONALS);
            }
            this.mWidthAlignment = Math.max(i, this.mWidthAlignment);
            this.mHeightAlignment = Math.max(i2, this.mHeightAlignment);
            this.mWidthRange = Utils.alignRange(this.mWidthRange, this.mWidthAlignment);
            this.mHeightRange = Utils.alignRange(this.mHeightRange, this.mHeightAlignment);
        }

        private void updateLimits() {
            Range rangeIntersect = this.mHorizontalBlockRange.intersect(Utils.factorRange(this.mWidthRange, this.mBlockWidth));
            this.mHorizontalBlockRange = rangeIntersect;
            this.mHorizontalBlockRange = rangeIntersect.intersect(Range.create(Integer.valueOf(((Integer) this.mBlockCountRange.getLower()).intValue() / ((Integer) this.mVerticalBlockRange.getUpper()).intValue()), Integer.valueOf(((Integer) this.mBlockCountRange.getUpper()).intValue() / ((Integer) this.mVerticalBlockRange.getLower()).intValue())));
            Range rangeIntersect2 = this.mVerticalBlockRange.intersect(Utils.factorRange(this.mHeightRange, this.mBlockHeight));
            this.mVerticalBlockRange = rangeIntersect2;
            this.mVerticalBlockRange = rangeIntersect2.intersect(Range.create(Integer.valueOf(((Integer) this.mBlockCountRange.getLower()).intValue() / ((Integer) this.mHorizontalBlockRange.getUpper()).intValue()), Integer.valueOf(((Integer) this.mBlockCountRange.getUpper()).intValue() / ((Integer) this.mHorizontalBlockRange.getLower()).intValue())));
            this.mBlockCountRange = this.mBlockCountRange.intersect(Range.create(Integer.valueOf(((Integer) this.mHorizontalBlockRange.getLower()).intValue() * ((Integer) this.mVerticalBlockRange.getLower()).intValue()), Integer.valueOf(((Integer) this.mHorizontalBlockRange.getUpper()).intValue() * ((Integer) this.mVerticalBlockRange.getUpper()).intValue())));
            this.mBlockAspectRatioRange = this.mBlockAspectRatioRange.intersect(new Rational(((Integer) this.mHorizontalBlockRange.getLower()).intValue(), ((Integer) this.mVerticalBlockRange.getUpper()).intValue()), new Rational(((Integer) this.mHorizontalBlockRange.getUpper()).intValue(), ((Integer) this.mVerticalBlockRange.getLower()).intValue()));
            this.mWidthRange = this.mWidthRange.intersect(Integer.valueOf(((((Integer) this.mHorizontalBlockRange.getLower()).intValue() - 1) * this.mBlockWidth) + this.mWidthAlignment), Integer.valueOf(((Integer) this.mHorizontalBlockRange.getUpper()).intValue() * this.mBlockWidth));
            this.mHeightRange = this.mHeightRange.intersect(Integer.valueOf(((((Integer) this.mVerticalBlockRange.getLower()).intValue() - 1) * this.mBlockHeight) + this.mHeightAlignment), Integer.valueOf(((Integer) this.mVerticalBlockRange.getUpper()).intValue() * this.mBlockHeight));
            this.mAspectRatioRange = this.mAspectRatioRange.intersect(new Rational(((Integer) this.mWidthRange.getLower()).intValue(), ((Integer) this.mHeightRange.getUpper()).intValue()), new Rational(((Integer) this.mWidthRange.getUpper()).intValue(), ((Integer) this.mHeightRange.getLower()).intValue()));
            this.mSmallerDimensionUpperLimit = Math.min(this.mSmallerDimensionUpperLimit, Math.min(((Integer) this.mWidthRange.getUpper()).intValue(), ((Integer) this.mHeightRange.getUpper()).intValue()));
            Range rangeIntersect3 = this.mBlocksPerSecondRange.intersect(Long.valueOf(((long) ((Integer) this.mBlockCountRange.getLower()).intValue()) * ((long) ((Integer) this.mFrameRateRange.getLower()).intValue())), Long.valueOf(((long) ((Integer) this.mBlockCountRange.getUpper()).intValue()) * ((long) ((Integer) this.mFrameRateRange.getUpper()).intValue())));
            this.mBlocksPerSecondRange = rangeIntersect3;
            this.mFrameRateRange = this.mFrameRateRange.intersect(Integer.valueOf((int) (((Long) rangeIntersect3.getLower()).longValue() / ((long) ((Integer) this.mBlockCountRange.getUpper()).intValue()))), Integer.valueOf((int) (((Long) this.mBlocksPerSecondRange.getUpper()).longValue() / ((double) ((Integer) this.mBlockCountRange.getLower()).intValue()))));
        }

        private void applyMacroBlockLimits(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7) {
            applyMacroBlockLimits(1, 1, i, i2, i3, j, i4, i5, i6, i7);
        }

        private void applyMacroBlockLimits(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
            applyAlignment(i8, i9);
            applyBlockLimits(i6, i7, Range.create(1, Integer.valueOf(i5)), Range.create(1L, Long.valueOf(j)), Range.create(new Rational(1, i4), new Rational(i3, 1)));
            this.mHorizontalBlockRange = this.mHorizontalBlockRange.intersect(Integer.valueOf(Utils.divUp(i, this.mBlockWidth / i6)), Integer.valueOf(i3 / (this.mBlockWidth / i6)));
            this.mVerticalBlockRange = this.mVerticalBlockRange.intersect(Integer.valueOf(Utils.divUp(i2, this.mBlockHeight / i7)), Integer.valueOf(i4 / (this.mBlockHeight / i7)));
        }

        /* JADX WARN: Removed duplicated region for block: B:114:0x0356  */
        /* JADX WARN: Removed duplicated region for block: B:403:0x0d55  */
        /* JADX WARN: Removed duplicated region for block: B:416:0x0da9  */
        /* JADX WARN: Removed duplicated region for block: B:424:0x01ae A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:427:0x0358 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x016c  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x016f  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01aa  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void applyLevelLimits() {
            /*
                Method dump skipped, instruction units count: 3974
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.MediaCodecInfo.VideoCapabilities.applyLevelLimits():void");
        }
    }

    public static final class EncoderCapabilities {
        public static final int BITRATE_MODE_CBR = 2;
        public static final int BITRATE_MODE_CQ = 0;
        public static final int BITRATE_MODE_VBR = 1;
        private static final Feature[] bitrates = {new Feature("VBR", 1, true), new Feature("CBR", 2, false), new Feature("CQ", 0, false)};
        private int mBitControl;
        private Range<Integer> mComplexityRange;
        private Integer mDefaultComplexity;
        private Integer mDefaultQuality;
        private CodecCapabilities mParent;
        private Range<Integer> mQualityRange;
        private String mQualityScale;

        public Range<Integer> getQualityRange() {
            return this.mQualityRange;
        }

        public Range<Integer> getComplexityRange() {
            return this.mComplexityRange;
        }

        private static int parseBitrateMode(String str) {
            for (Feature feature : bitrates) {
                if (feature.mName.equalsIgnoreCase(str)) {
                    return feature.mValue;
                }
            }
            return 0;
        }

        public boolean isBitrateModeSupported(int i) {
            for (Feature feature : bitrates) {
                if (i == feature.mValue) {
                    return ((1 << i) & this.mBitControl) != 0;
                }
            }
            return false;
        }

        private EncoderCapabilities() {
        }

        public static EncoderCapabilities create(MediaFormat mediaFormat, CodecCapabilities codecCapabilities) {
            EncoderCapabilities encoderCapabilities = new EncoderCapabilities();
            encoderCapabilities.init(mediaFormat, codecCapabilities);
            return encoderCapabilities;
        }

        private void init(MediaFormat mediaFormat, CodecCapabilities codecCapabilities) {
            this.mParent = codecCapabilities;
            this.mComplexityRange = Range.create(0, 0);
            this.mQualityRange = Range.create(0, 0);
            this.mBitControl = 2;
            applyLevelLimits();
            parseFromInfo(mediaFormat);
        }

        private void applyLevelLimits() {
            String mimeType = this.mParent.getMimeType();
            if (mimeType.equalsIgnoreCase(MediaFormat.MIMETYPE_AUDIO_FLAC)) {
                this.mComplexityRange = Range.create(0, 8);
                this.mBitControl = 1;
            } else if (mimeType.equalsIgnoreCase("audio/3gpp") || mimeType.equalsIgnoreCase(MediaFormat.MIMETYPE_AUDIO_AMR_WB) || mimeType.equalsIgnoreCase(MediaFormat.MIMETYPE_AUDIO_G711_ALAW) || mimeType.equalsIgnoreCase(MediaFormat.MIMETYPE_AUDIO_G711_MLAW) || mimeType.equalsIgnoreCase(MediaFormat.MIMETYPE_AUDIO_MSGSM)) {
                this.mBitControl = 4;
            }
        }

        private void parseFromInfo(MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            if (mediaFormat.containsKey("complexity-range")) {
                this.mComplexityRange = Utils.parseIntRange(mediaFormat.getString("complexity-range"), this.mComplexityRange);
            }
            if (mediaFormat.containsKey("quality-range")) {
                this.mQualityRange = Utils.parseIntRange(mediaFormat.getString("quality-range"), this.mQualityRange);
            }
            if (mediaFormat.containsKey("feature-bitrate-modes")) {
                for (String str : mediaFormat.getString("feature-bitrate-modes").split(",")) {
                    this.mBitControl = (1 << parseBitrateMode(str)) | this.mBitControl;
                }
            }
            try {
                this.mDefaultComplexity = Integer.valueOf(Integer.parseInt((String) map.get("complexity-default")));
            } catch (NumberFormatException unused) {
            }
            try {
                this.mDefaultQuality = Integer.valueOf(Integer.parseInt((String) map.get("quality-default")));
            } catch (NumberFormatException unused2) {
            }
            this.mQualityScale = (String) map.get("quality-scale");
        }

        private boolean supports(Integer num, Integer num2, Integer num3) {
            boolean zContains = num != null ? this.mComplexityRange.contains(num) : true;
            if (zContains && num2 != null) {
                zContains = this.mQualityRange.contains(num2);
            }
            if (!zContains || num3 == null) {
                return zContains;
            }
            CodecProfileLevel[] codecProfileLevelArr = this.mParent.profileLevels;
            int length = codecProfileLevelArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (codecProfileLevelArr[i].profile == num3.intValue()) {
                    num3 = null;
                    break;
                }
                i++;
            }
            return num3 == null;
        }

        public void getDefaultFormat(MediaFormat mediaFormat) {
            Integer num;
            Integer num2;
            if (!((Integer) this.mQualityRange.getUpper()).equals(this.mQualityRange.getLower()) && (num2 = this.mDefaultQuality) != null) {
                mediaFormat.setInteger(MediaFormat.KEY_QUALITY, num2.intValue());
            }
            if (!((Integer) this.mComplexityRange.getUpper()).equals(this.mComplexityRange.getLower()) && (num = this.mDefaultComplexity) != null) {
                mediaFormat.setInteger(MediaFormat.KEY_COMPLEXITY, num.intValue());
            }
            for (Feature feature : bitrates) {
                if ((this.mBitControl & (1 << feature.mValue)) != 0) {
                    mediaFormat.setInteger(MediaFormat.KEY_BITRATE_MODE, feature.mValue);
                    return;
                }
            }
        }

        public boolean supportsFormat(MediaFormat mediaFormat) {
            Map<String, Object> map = mediaFormat.getMap();
            String mimeType = this.mParent.getMimeType();
            Integer num = (Integer) map.get(MediaFormat.KEY_BITRATE_MODE);
            if (num != null && !isBitrateModeSupported(num.intValue())) {
                return false;
            }
            Integer num2 = (Integer) map.get(MediaFormat.KEY_COMPLEXITY);
            if (MediaFormat.MIMETYPE_AUDIO_FLAC.equalsIgnoreCase(mimeType)) {
                Integer num3 = (Integer) map.get(MediaFormat.KEY_FLAC_COMPRESSION_LEVEL);
                if (num2 == null) {
                    num2 = num3;
                } else if (num3 != null && !num2.equals(num3)) {
                    throw new IllegalArgumentException("conflicting values for complexity and flac-compression-level");
                }
            }
            Integer num4 = (Integer) map.get(MediaFormat.KEY_PROFILE);
            if (MediaFormat.MIMETYPE_AUDIO_AAC.equalsIgnoreCase(mimeType)) {
                Integer num5 = (Integer) map.get(MediaFormat.KEY_AAC_PROFILE);
                if (num4 == null) {
                    num4 = num5;
                } else if (num5 != null && !num5.equals(num4)) {
                    throw new IllegalArgumentException("conflicting values for profile and aac-profile");
                }
            }
            return supports(num2, (Integer) map.get(MediaFormat.KEY_QUALITY), num4);
        }
    }

    public static final class CodecProfileLevel {
        public static final int AACObjectELD = 39;
        public static final int AACObjectERLC = 17;
        public static final int AACObjectERScalable = 20;
        public static final int AACObjectHE = 5;
        public static final int AACObjectHE_PS = 29;
        public static final int AACObjectLC = 2;
        public static final int AACObjectLD = 23;
        public static final int AACObjectLTP = 4;
        public static final int AACObjectMain = 1;
        public static final int AACObjectSSR = 3;
        public static final int AACObjectScalable = 6;
        public static final int AACObjectXHE = 42;
        public static final int AV1Level2 = 1;
        public static final int AV1Level21 = 2;
        public static final int AV1Level22 = 4;
        public static final int AV1Level23 = 8;
        public static final int AV1Level3 = 16;
        public static final int AV1Level31 = 32;
        public static final int AV1Level32 = 64;
        public static final int AV1Level33 = 128;
        public static final int AV1Level4 = 256;
        public static final int AV1Level41 = 512;
        public static final int AV1Level42 = 1024;
        public static final int AV1Level43 = 2048;
        public static final int AV1Level5 = 4096;
        public static final int AV1Level51 = 8192;
        public static final int AV1Level52 = 16384;
        public static final int AV1Level53 = 32768;
        public static final int AV1Level6 = 65536;
        public static final int AV1Level61 = 131072;
        public static final int AV1Level62 = 262144;
        public static final int AV1Level63 = 524288;
        public static final int AV1Level7 = 1048576;
        public static final int AV1Level71 = 2097152;
        public static final int AV1Level72 = 4194304;
        public static final int AV1Level73 = 8388608;
        public static final int AV1ProfileMain10 = 2;
        public static final int AV1ProfileMain10HDR10 = 4096;
        public static final int AV1ProfileMain10HDR10Plus = 8192;
        public static final int AV1ProfileMain8 = 1;
        public static final int AVCLevel1 = 1;
        public static final int AVCLevel11 = 4;
        public static final int AVCLevel12 = 8;
        public static final int AVCLevel13 = 16;
        public static final int AVCLevel1b = 2;
        public static final int AVCLevel2 = 32;
        public static final int AVCLevel21 = 64;
        public static final int AVCLevel22 = 128;
        public static final int AVCLevel3 = 256;
        public static final int AVCLevel31 = 512;
        public static final int AVCLevel32 = 1024;
        public static final int AVCLevel4 = 2048;
        public static final int AVCLevel41 = 4096;
        public static final int AVCLevel42 = 8192;
        public static final int AVCLevel5 = 16384;
        public static final int AVCLevel51 = 32768;
        public static final int AVCLevel52 = 65536;
        public static final int AVCLevel6 = 131072;
        public static final int AVCLevel61 = 262144;
        public static final int AVCLevel62 = 524288;
        public static final int AVCProfileBaseline = 1;
        public static final int AVCProfileConstrainedBaseline = 65536;
        public static final int AVCProfileConstrainedHigh = 524288;
        public static final int AVCProfileExtended = 4;
        public static final int AVCProfileHigh = 8;
        public static final int AVCProfileHigh10 = 16;
        public static final int AVCProfileHigh422 = 32;
        public static final int AVCProfileHigh444 = 64;
        public static final int AVCProfileMain = 2;
        public static final int DolbyVisionLevelFhd24 = 4;
        public static final int DolbyVisionLevelFhd30 = 8;
        public static final int DolbyVisionLevelFhd60 = 16;
        public static final int DolbyVisionLevelHd24 = 1;
        public static final int DolbyVisionLevelHd30 = 2;
        public static final int DolbyVisionLevelUhd24 = 32;
        public static final int DolbyVisionLevelUhd30 = 64;
        public static final int DolbyVisionLevelUhd48 = 128;
        public static final int DolbyVisionLevelUhd60 = 256;
        public static final int DolbyVisionProfileDvav110 = 1024;
        public static final int DolbyVisionProfileDvavPen = 2;
        public static final int DolbyVisionProfileDvavPer = 1;
        public static final int DolbyVisionProfileDvavSe = 512;
        public static final int DolbyVisionProfileDvheDen = 8;
        public static final int DolbyVisionProfileDvheDer = 4;
        public static final int DolbyVisionProfileDvheDtb = 128;
        public static final int DolbyVisionProfileDvheDth = 64;
        public static final int DolbyVisionProfileDvheDtr = 16;
        public static final int DolbyVisionProfileDvheSt = 256;
        public static final int DolbyVisionProfileDvheStn = 32;
        public static final int H263Level10 = 1;
        public static final int H263Level20 = 2;
        public static final int H263Level30 = 4;
        public static final int H263Level40 = 8;
        public static final int H263Level45 = 16;
        public static final int H263Level50 = 32;
        public static final int H263Level60 = 64;
        public static final int H263Level70 = 128;
        public static final int H263ProfileBackwardCompatible = 4;
        public static final int H263ProfileBaseline = 1;
        public static final int H263ProfileH320Coding = 2;
        public static final int H263ProfileHighCompression = 32;
        public static final int H263ProfileHighLatency = 256;
        public static final int H263ProfileISWV2 = 8;
        public static final int H263ProfileISWV3 = 16;
        public static final int H263ProfileInterlace = 128;
        public static final int H263ProfileInternet = 64;
        public static final int HEVCHighTierLevel1 = 2;
        public static final int HEVCHighTierLevel2 = 8;
        public static final int HEVCHighTierLevel21 = 32;
        public static final int HEVCHighTierLevel3 = 128;
        public static final int HEVCHighTierLevel31 = 512;
        public static final int HEVCHighTierLevel4 = 2048;
        public static final int HEVCHighTierLevel41 = 8192;
        public static final int HEVCHighTierLevel5 = 32768;
        public static final int HEVCHighTierLevel51 = 131072;
        public static final int HEVCHighTierLevel52 = 524288;
        public static final int HEVCHighTierLevel6 = 2097152;
        public static final int HEVCHighTierLevel61 = 8388608;
        public static final int HEVCHighTierLevel62 = 33554432;
        private static final int HEVCHighTierLevels = 44739242;
        public static final int HEVCMainTierLevel1 = 1;
        public static final int HEVCMainTierLevel2 = 4;
        public static final int HEVCMainTierLevel21 = 16;
        public static final int HEVCMainTierLevel3 = 64;
        public static final int HEVCMainTierLevel31 = 256;
        public static final int HEVCMainTierLevel4 = 1024;
        public static final int HEVCMainTierLevel41 = 4096;
        public static final int HEVCMainTierLevel5 = 16384;
        public static final int HEVCMainTierLevel51 = 65536;
        public static final int HEVCMainTierLevel52 = 262144;
        public static final int HEVCMainTierLevel6 = 1048576;
        public static final int HEVCMainTierLevel61 = 4194304;
        public static final int HEVCMainTierLevel62 = 16777216;
        public static final int HEVCProfileMain = 1;
        public static final int HEVCProfileMain10 = 2;
        public static final int HEVCProfileMain10HDR10 = 4096;
        public static final int HEVCProfileMain10HDR10Plus = 8192;
        public static final int HEVCProfileMainStill = 4;
        public static final int MPEG2LevelH14 = 2;
        public static final int MPEG2LevelHL = 3;
        public static final int MPEG2LevelHP = 4;
        public static final int MPEG2LevelLL = 0;
        public static final int MPEG2LevelML = 1;
        public static final int MPEG2Profile422 = 2;
        public static final int MPEG2ProfileHigh = 5;
        public static final int MPEG2ProfileMain = 1;
        public static final int MPEG2ProfileSNR = 3;
        public static final int MPEG2ProfileSimple = 0;
        public static final int MPEG2ProfileSpatial = 4;
        public static final int MPEG4Level0 = 1;
        public static final int MPEG4Level0b = 2;
        public static final int MPEG4Level1 = 4;
        public static final int MPEG4Level2 = 8;
        public static final int MPEG4Level3 = 16;
        public static final int MPEG4Level3b = 24;
        public static final int MPEG4Level4 = 32;
        public static final int MPEG4Level4a = 64;
        public static final int MPEG4Level5 = 128;
        public static final int MPEG4Level6 = 256;
        public static final int MPEG4ProfileAdvancedCoding = 4096;
        public static final int MPEG4ProfileAdvancedCore = 8192;
        public static final int MPEG4ProfileAdvancedRealTime = 1024;
        public static final int MPEG4ProfileAdvancedScalable = 16384;
        public static final int MPEG4ProfileAdvancedSimple = 32768;
        public static final int MPEG4ProfileBasicAnimated = 256;
        public static final int MPEG4ProfileCore = 4;
        public static final int MPEG4ProfileCoreScalable = 2048;
        public static final int MPEG4ProfileHybrid = 512;
        public static final int MPEG4ProfileMain = 8;
        public static final int MPEG4ProfileNbit = 16;
        public static final int MPEG4ProfileScalableTexture = 32;
        public static final int MPEG4ProfileSimple = 1;
        public static final int MPEG4ProfileSimpleFBA = 128;
        public static final int MPEG4ProfileSimpleFace = 64;
        public static final int MPEG4ProfileSimpleScalable = 2;
        public static final int VP8Level_Version0 = 1;
        public static final int VP8Level_Version1 = 2;
        public static final int VP8Level_Version2 = 4;
        public static final int VP8Level_Version3 = 8;
        public static final int VP8ProfileMain = 1;
        public static final int VP9Level1 = 1;
        public static final int VP9Level11 = 2;
        public static final int VP9Level2 = 4;
        public static final int VP9Level21 = 8;
        public static final int VP9Level3 = 16;
        public static final int VP9Level31 = 32;
        public static final int VP9Level4 = 64;
        public static final int VP9Level41 = 128;
        public static final int VP9Level5 = 256;
        public static final int VP9Level51 = 512;
        public static final int VP9Level52 = 1024;
        public static final int VP9Level6 = 2048;
        public static final int VP9Level61 = 4096;
        public static final int VP9Level62 = 8192;
        public static final int VP9Profile0 = 1;
        public static final int VP9Profile1 = 2;
        public static final int VP9Profile2 = 4;
        public static final int VP9Profile2HDR = 4096;
        public static final int VP9Profile2HDR10Plus = 16384;
        public static final int VP9Profile3 = 8;
        public static final int VP9Profile3HDR = 8192;
        public static final int VP9Profile3HDR10Plus = 32768;
        public int level;
        public int profile;

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof CodecProfileLevel)) {
                return false;
            }
            CodecProfileLevel codecProfileLevel = (CodecProfileLevel) obj;
            return codecProfileLevel.profile == this.profile && codecProfileLevel.level == this.level;
        }

        public int hashCode() {
            return Long.hashCode((((long) this.profile) << 32) | ((long) this.level));
        }
    }

    public final CodecCapabilities getCapabilitiesForType(String str) {
        CodecCapabilities codecCapabilities = this.mCaps.get(str);
        if (codecCapabilities == null) {
            throw new IllegalArgumentException("codec does not support type");
        }
        return codecCapabilities.dup();
    }

    public MediaCodecInfo makeRegular() {
        ArrayList arrayList = new ArrayList();
        for (CodecCapabilities codecCapabilities : this.mCaps.values()) {
            if (codecCapabilities.isRegular()) {
                arrayList.add(codecCapabilities);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList.size() == this.mCaps.size() ? this : new MediaCodecInfo(this.mName, this.mCanonicalName, this.mFlags, (CodecCapabilities[]) arrayList.toArray(new CodecCapabilities[arrayList.size()]));
    }
}
