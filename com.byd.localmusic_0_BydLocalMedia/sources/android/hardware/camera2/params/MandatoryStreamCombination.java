package android.hardware.camera2.params;

import android.graphics.ImageFormat;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class MandatoryStreamCombination {
    private static final String TAG = "MandatoryStreamCombination";
    private final String mDescription;
    private final boolean mIsReprocessable;
    private final ArrayList<MandatoryStreamInformation> mStreamsInformation = new ArrayList<>();
    private static StreamCombinationTemplate[] sLegacyCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.MAXIMUM)}, "Simple preview, GPU video processing, or no-preview video recording"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "No-viewfinder still image capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "In-application video/image processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Standard still imaging"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "In-app processing plus still capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Standard recording"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Preview plus in-app processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Still capture plus in-app processing")};
    private static StreamCombinationTemplate[] sLimitedCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD)}, "High-resolution video recording with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "High-resolution in-app video processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "Two-input in-app video processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD), new StreamTemplate(256, SizeThreshold.RECORD)}, "High-resolution recording with video snapshot"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD), new StreamTemplate(256, SizeThreshold.RECORD)}, "High-resolution in-app processing with video snapshot"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Two-input in-app processing with still capture")};
    private static StreamCombinationTemplate[] sBurstCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.MAXIMUM)}, "Maximum-resolution GPU processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution in-app processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution two-input in-app processsing")};
    private static StreamCombinationTemplate[] sFullCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.MAXIMUM), new StreamTemplate(34, SizeThreshold.MAXIMUM)}, "Maximum-resolution GPU processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.MAXIMUM), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution in-app processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.MAXIMUM), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution two-input in-app processsing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Video recording with maximum-size video snapshot"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.VGA), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Standard video recording plus maximum-resolution in-app processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.VGA), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Preview plus two-input maximum-resolution in-app processing")};
    private static StreamCombinationTemplate[] sRawCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "No-preview DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Standard DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app processing plus DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Video recording with DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Preview with in-app processing and DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Two-input in-app processing plus DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Still capture with simultaneous JPEG and DNG"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app processing with simultaneous JPEG and DNG")};
    private static StreamCombinationTemplate[] sLevel3Combinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(35, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with dynamic selection of output format"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with dynamic selection of output format")};
    private static StreamCombinationTemplate[] sLimitedPrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "No-viewfinder still image reprocessing", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL(Zero-Shutter-Lag) still imaging", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still and in-app processing imaging", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL in-app processing with still capture", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sLimitedYUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "No-viewfinder still image reprocessing", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL(Zero-Shutter-Lag) still imaging", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still and in-app processing imaging", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL in-app processing with still capture", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sFullPrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "High-resolution ZSL in-app video processing with regular preview", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution ZSL in-app processing with regular preview", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution two-input ZSL in-app processing", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still capture and in-app processing", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sFullYUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Maximum-resolution multi-frame image fusion in-app processing with regular preview", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Maximum-resolution multi-frame image fusion two-input in-app processing", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "High-resolution ZSL in-app video processing with regular preview", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still capture and in-app processing", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sRAWPrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and preview with DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL two-input in-app processing and DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL still capture and preview with DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing with still capture and DNG capture", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sRAWYUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and preview with DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL two-input in-app processing and DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL still capture and preview with DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing with still capture and DNG capture", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sLevel3PrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(32, SizeThreshold.MAXIMUM), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with ZSL, RAW, and JPEG reprocessing output", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sLevel3YUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with ZSL and RAW", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(32, SizeThreshold.MAXIMUM), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with ZSL, RAW, and JPEG reprocessing output", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sConcurrentStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s1440p)}, "In-app video / image processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s1440p)}, "preview / preview to GPU"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.s1440p)}, "No view-finder still image capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(35, SizeThreshold.s1440p)}, "Two-input in app video / image processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(34, SizeThreshold.s1440p)}, "High resolution video recording with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(35, SizeThreshold.s1440p)}, "In-app video / image processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(34, SizeThreshold.s1440p)}, "In-app video / image processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(256, SizeThreshold.s1440p)}, "Standard stil image capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(256, SizeThreshold.s1440p)}, "Standard still image capture")};
    private static StreamCombinationTemplate[] sConcurrentDepthOnlyStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(ImageFormat.DEPTH16, SizeThreshold.VGA)}, "Depth capture for mesh based object rendering")};

    private enum ReprocessType {
        NONE,
        PRIVATE,
        YUV
    }

    private enum SizeThreshold {
        VGA,
        PREVIEW,
        RECORD,
        MAXIMUM,
        s720p,
        s1440p
    }

    public static final class MandatoryStreamInformation {
        private final ArrayList<Size> mAvailableSizes;
        private final int mFormat;
        private final boolean mIsInput;

        public MandatoryStreamInformation(List<Size> list, int i) {
            this(list, i, false);
        }

        public MandatoryStreamInformation(List<Size> list, int i, boolean z) {
            this.mAvailableSizes = new ArrayList<>();
            if (list.isEmpty()) {
                throw new IllegalArgumentException("No available sizes");
            }
            this.mAvailableSizes.addAll(list);
            this.mFormat = StreamConfigurationMap.checkArgumentFormat(i);
            this.mIsInput = z;
        }

        public boolean isInput() {
            return this.mIsInput;
        }

        public List<Size> getAvailableSizes() {
            return Collections.unmodifiableList(this.mAvailableSizes);
        }

        public int getFormat() {
            return this.mFormat;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof MandatoryStreamInformation) {
                MandatoryStreamInformation mandatoryStreamInformation = (MandatoryStreamInformation) obj;
                if (this.mFormat == mandatoryStreamInformation.mFormat && this.mIsInput == mandatoryStreamInformation.mIsInput && this.mAvailableSizes.size() == mandatoryStreamInformation.mAvailableSizes.size()) {
                    return this.mAvailableSizes.equals(mandatoryStreamInformation.mAvailableSizes);
                }
            }
            return false;
        }

        public int hashCode() {
            return HashCodeHelpers.hashCode(this.mFormat, Boolean.hashCode(this.mIsInput), this.mAvailableSizes.hashCode());
        }
    }

    public MandatoryStreamCombination(List<MandatoryStreamInformation> list, String str, boolean z) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Empty stream information");
        }
        this.mStreamsInformation.addAll(list);
        this.mDescription = str;
        this.mIsReprocessable = z;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public boolean isReprocessable() {
        return this.mIsReprocessable;
    }

    public List<MandatoryStreamInformation> getStreamsInformation() {
        return Collections.unmodifiableList(this.mStreamsInformation);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof MandatoryStreamCombination) {
            MandatoryStreamCombination mandatoryStreamCombination = (MandatoryStreamCombination) obj;
            if (this.mDescription == mandatoryStreamCombination.mDescription && this.mIsReprocessable == mandatoryStreamCombination.mIsReprocessable && this.mStreamsInformation.size() == mandatoryStreamCombination.mStreamsInformation.size()) {
                return this.mStreamsInformation.equals(mandatoryStreamCombination.mStreamsInformation);
            }
        }
        return false;
    }

    public int hashCode() {
        return HashCodeHelpers.hashCode(Boolean.hashCode(this.mIsReprocessable), this.mDescription.hashCode(), this.mStreamsInformation.hashCode());
    }

    private static final class StreamTemplate {
        public int mFormat;
        public boolean mIsInput;
        public SizeThreshold mSizeThreshold;

        public StreamTemplate(int i, SizeThreshold sizeThreshold) {
            this(i, sizeThreshold, false);
        }

        public StreamTemplate(int i, SizeThreshold sizeThreshold, boolean z) {
            this.mFormat = i;
            this.mSizeThreshold = sizeThreshold;
            this.mIsInput = z;
        }
    }

    private static final class StreamCombinationTemplate {
        public String mDescription;
        public ReprocessType mReprocessType;
        public StreamTemplate[] mStreamTemplates;

        public StreamCombinationTemplate(StreamTemplate[] streamTemplateArr, String str) {
            this(streamTemplateArr, str, ReprocessType.NONE);
        }

        public StreamCombinationTemplate(StreamTemplate[] streamTemplateArr, String str, ReprocessType reprocessType) {
            this.mStreamTemplates = streamTemplateArr;
            this.mReprocessType = reprocessType;
            this.mDescription = str;
        }
    }

    public static final class Builder {
        private final Size kPreviewSizeBound = new Size(1920, 1088);
        private int mCameraId;
        private List<Integer> mCapabilities;
        private Size mDisplaySize;
        private int mHwLevel;
        private boolean mIsHiddenPhysicalCamera;
        private StreamConfigurationMap mStreamConfigMap;

        /* JADX INFO: Access modifiers changed from: private */
        public static int compareSizes(int i, int i2, int i3, int i4) {
            long j = i;
            long j2 = ((long) i2) * j;
            long j3 = i3;
            long j4 = ((long) i4) * j3;
            if (j2 != j4) {
                j = j2;
                j3 = j4;
            }
            if (j < j3) {
                return -1;
            }
            return j > j3 ? 1 : 0;
        }

        public Builder(int i, int i2, Size size, List<Integer> list, StreamConfigurationMap streamConfigurationMap) {
            this.mCameraId = i;
            this.mDisplaySize = size;
            this.mCapabilities = list;
            this.mStreamConfigMap = streamConfigurationMap;
            this.mHwLevel = i2;
            this.mIsHiddenPhysicalCamera = CameraManager.isHiddenPhysicalCamera(Integer.toString(i));
        }

        public List<MandatoryStreamCombination> getAvailableMandatoryConcurrentStreamCombinations() {
            Builder builder;
            StreamCombinationTemplate[] streamCombinationTemplateArr;
            Size size;
            StreamCombinationTemplate[] streamCombinationTemplateArr2 = MandatoryStreamCombination.sConcurrentStreamCombinations;
            if (!isColorOutputSupported()) {
                Log.v(MandatoryStreamCombination.TAG, "Device is not backward compatible, depth streams are mandatory!");
                streamCombinationTemplateArr2 = MandatoryStreamCombination.sConcurrentDepthOnlyStreamCombinations;
            }
            Size size2 = new Size(640, 480);
            Size size3 = new Size(1280, 720);
            Size size4 = new Size(1920, 1440);
            ArrayList arrayList = new ArrayList();
            arrayList.ensureCapacity(streamCombinationTemplateArr2.length);
            int length = streamCombinationTemplateArr2.length;
            int i = 0;
            while (i < length) {
                StreamCombinationTemplate streamCombinationTemplate = streamCombinationTemplateArr2[i];
                ArrayList arrayList2 = new ArrayList();
                arrayList2.ensureCapacity(streamCombinationTemplate.mStreamTemplates.length);
                StreamTemplate[] streamTemplateArr = streamCombinationTemplate.mStreamTemplates;
                int length2 = streamTemplateArr.length;
                int i2 = 0;
                while (i2 < length2) {
                    StreamTemplate streamTemplate = streamTemplateArr[i2];
                    ArrayList arrayList3 = new ArrayList();
                    int i3 = AnonymousClass1.$SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold[streamTemplate.mSizeThreshold.ordinal()];
                    if (i3 == 1) {
                        builder = this;
                        streamCombinationTemplateArr = streamCombinationTemplateArr2;
                        size = size4;
                    } else if (i3 != 2) {
                        builder = this;
                        streamCombinationTemplateArr = streamCombinationTemplateArr2;
                        size = size3;
                    } else {
                        builder = this;
                        streamCombinationTemplateArr = streamCombinationTemplateArr2;
                        size = size2;
                    }
                    Size size5 = size2;
                    arrayList3.add(getMinSize(size, getMaxSize(builder.mStreamConfigMap.getOutputSizes(streamTemplate.mFormat))));
                    try {
                        arrayList2.add(new MandatoryStreamInformation(arrayList3, streamTemplate.mFormat));
                        i2++;
                        streamCombinationTemplateArr2 = streamCombinationTemplateArr;
                        size2 = size5;
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("No available sizes found for format: " + streamTemplate.mFormat + " size threshold: " + streamTemplate.mSizeThreshold + " combination: " + streamCombinationTemplate.mDescription, e);
                    }
                }
                StreamCombinationTemplate[] streamCombinationTemplateArr3 = streamCombinationTemplateArr2;
                Size size6 = size2;
                try {
                    arrayList.add(new MandatoryStreamCombination(arrayList2, streamCombinationTemplate.mDescription, false));
                    i++;
                    streamCombinationTemplateArr2 = streamCombinationTemplateArr3;
                    size2 = size6;
                } catch (IllegalArgumentException e2) {
                    throw new RuntimeException("No stream information for mandatory combination: " + streamCombinationTemplate.mDescription, e2);
                }
            }
            return Collections.unmodifiableList(arrayList);
        }

        public List<MandatoryStreamCombination> getAvailableMandatoryStreamCombinations() {
            if (!isColorOutputSupported()) {
                Log.v(MandatoryStreamCombination.TAG, "Device is not backward compatible!");
                return null;
            }
            if (this.mCameraId < 0 && !isExternalCamera()) {
                Log.i(MandatoryStreamCombination.TAG, "Invalid camera id");
                return null;
            }
            ArrayList<StreamCombinationTemplate> arrayList = new ArrayList<>();
            if (isHardwareLevelAtLeastLegacy()) {
                arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLegacyCombinations));
            }
            if (isHardwareLevelAtLeastLimited() || isExternalCamera()) {
                arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedCombinations));
                if (isPrivateReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedPrivateReprocCombinations));
                }
                if (isYUVReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedYUVReprocCombinations));
                }
            }
            if (isCapabilitySupported(6)) {
                arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sBurstCombinations));
            }
            if (isHardwareLevelAtLeastFull()) {
                arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sFullCombinations));
                if (isPrivateReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sFullPrivateReprocCombinations));
                }
                if (isYUVReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sFullYUVReprocCombinations));
                }
            }
            if (isCapabilitySupported(3)) {
                arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sRawCombinations));
                if (isPrivateReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sRAWPrivateReprocCombinations));
                }
                if (isYUVReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sRAWYUVReprocCombinations));
                }
            }
            if (isHardwareLevelAtLeastLevel3()) {
                arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3Combinations));
                if (isPrivateReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3PrivateReprocCombinations));
                }
                if (isYUVReprocessingSupported()) {
                    arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3YUVReprocCombinations));
                }
            }
            return generateAvailableCombinations(arrayList);
        }

        private List<MandatoryStreamCombination> generateAvailableCombinations(ArrayList<StreamCombinationTemplate> arrayList) {
            StreamTemplate[] streamTemplateArr;
            Size size;
            List<Size> list;
            int i;
            if (arrayList.isEmpty()) {
                Log.e(MandatoryStreamCombination.TAG, "No available stream templates!");
                return null;
            }
            HashMap<Pair<SizeThreshold, Integer>, List<Size>> mapEnumerateAvailableSizes = enumerateAvailableSizes();
            if (mapEnumerateAvailableSizes == null) {
                Log.e(MandatoryStreamCombination.TAG, "Available size enumeration failed!");
                return null;
            }
            int i2 = 32;
            Size[] outputSizes = this.mStreamConfigMap.getOutputSizes(32);
            ArrayList arrayList2 = new ArrayList();
            if (outputSizes != null) {
                arrayList2.ensureCapacity(outputSizes.length);
                arrayList2.addAll(Arrays.asList(outputSizes));
            }
            Size size2 = new Size(0, 0);
            if (isPrivateReprocessingSupported()) {
                size2 = getMaxSize(this.mStreamConfigMap.getInputSizes(34));
            }
            Size size3 = new Size(0, 0);
            if (isYUVReprocessingSupported()) {
                size3 = getMaxSize(this.mStreamConfigMap.getInputSizes(35));
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.ensureCapacity(arrayList.size());
            for (StreamCombinationTemplate streamCombinationTemplate : arrayList) {
                ArrayList arrayList4 = new ArrayList();
                arrayList4.ensureCapacity(streamCombinationTemplate.mStreamTemplates.length);
                boolean z = streamCombinationTemplate.mReprocessType != ReprocessType.NONE;
                if (z) {
                    ArrayList arrayList5 = new ArrayList();
                    if (streamCombinationTemplate.mReprocessType == ReprocessType.PRIVATE) {
                        arrayList5.add(size2);
                        i = 34;
                    } else {
                        arrayList5.add(size3);
                        i = 35;
                    }
                    arrayList4.add(new MandatoryStreamInformation(arrayList5, i, true));
                    arrayList4.add(new MandatoryStreamInformation(arrayList5, i));
                }
                StreamTemplate[] streamTemplateArr2 = streamCombinationTemplate.mStreamTemplates;
                int length = streamTemplateArr2.length;
                int i3 = 0;
                while (i3 < length) {
                    StreamTemplate streamTemplate = streamTemplateArr2[i3];
                    if (streamTemplate.mFormat == i2) {
                        streamTemplateArr = streamTemplateArr2;
                        size = size2;
                        list = arrayList2;
                    } else {
                        streamTemplateArr = streamTemplateArr2;
                        size = size2;
                        list = mapEnumerateAvailableSizes.get(new Pair(streamTemplate.mSizeThreshold, new Integer(streamTemplate.mFormat)));
                    }
                    try {
                        arrayList4.add(new MandatoryStreamInformation(list, streamTemplate.mFormat));
                        i3++;
                        streamTemplateArr2 = streamTemplateArr;
                        size2 = size;
                        i2 = 32;
                    } catch (IllegalArgumentException unused) {
                        Log.e(MandatoryStreamCombination.TAG, "No available sizes found for format: " + streamTemplate.mFormat + " size threshold: " + streamTemplate.mSizeThreshold + " combination: " + streamCombinationTemplate.mDescription);
                        return null;
                    }
                }
                Size size4 = size2;
                try {
                    arrayList3.add(new MandatoryStreamCombination(arrayList4, streamCombinationTemplate.mDescription, z));
                    size2 = size4;
                    i2 = 32;
                } catch (IllegalArgumentException unused2) {
                    Log.e(MandatoryStreamCombination.TAG, "No stream information for mandatory combination: " + streamCombinationTemplate.mDescription);
                    return null;
                }
            }
            return Collections.unmodifiableList(arrayList3);
        }

        private HashMap<Pair<SizeThreshold, Integer>, List<Size>> enumerateAvailableSizes() {
            Size maxCameraRecordingSize;
            int[] iArr = {34, 35, 256};
            new Size(0, 0);
            new Size(0, 0);
            Size size = new Size(640, 480);
            if (isExternalCamera() || this.mIsHiddenPhysicalCamera) {
                maxCameraRecordingSize = getMaxCameraRecordingSize();
            } else {
                maxCameraRecordingSize = getMaxRecordingSize();
            }
            if (maxCameraRecordingSize == null) {
                Log.e(MandatoryStreamCombination.TAG, "Failed to find maximum recording size!");
                return null;
            }
            HashMap map = new HashMap();
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                map.put(new Integer(i2), this.mStreamConfigMap.getOutputSizes(i2));
            }
            List<Size> sizesWithinBound = getSizesWithinBound((Size[]) map.get(new Integer(34)), this.kPreviewSizeBound);
            if (sizesWithinBound == null || sizesWithinBound.isEmpty()) {
                Log.e(MandatoryStreamCombination.TAG, "No preview sizes within preview size bound!");
                return null;
            }
            Size maxPreviewSize = getMaxPreviewSize(getAscendingOrderSizes(sizesWithinBound, false));
            HashMap<Pair<SizeThreshold, Integer>, List<Size>> map2 = new HashMap<>();
            for (int i3 = 0; i3 < 3; i3++) {
                Integer num = new Integer(iArr[i3]);
                Size[] sizeArr = (Size[]) map.get(num);
                map2.put(new Pair<>(SizeThreshold.VGA, num), getSizesWithinBound(sizeArr, size));
                map2.put(new Pair<>(SizeThreshold.PREVIEW, num), getSizesWithinBound(sizeArr, maxPreviewSize));
                map2.put(new Pair<>(SizeThreshold.RECORD, num), getSizesWithinBound(sizeArr, maxCameraRecordingSize));
                map2.put(new Pair<>(SizeThreshold.MAXIMUM, num), Arrays.asList(sizeArr));
            }
            return map2;
        }

        private static List<Size> getSizesWithinBound(Size[] sizeArr, Size size) {
            ArrayList arrayList = new ArrayList();
            for (Size size2 : sizeArr) {
                if (size2.getWidth() <= size.getWidth() && size2.getHeight() <= size.getHeight()) {
                    arrayList.add(size2);
                }
            }
            return arrayList;
        }

        public static Size getMinSize(Size size, Size size2) {
            if (size == null || size2 == null) {
                throw new IllegalArgumentException("sizes was empty");
            }
            return size.getWidth() * size.getHeight() < size2.getHeight() * size2.getWidth() ? size : size2;
        }

        public static Size getMaxSize(Size... sizeArr) {
            if (sizeArr == null || sizeArr.length == 0) {
                throw new IllegalArgumentException("sizes was empty");
            }
            Size size = sizeArr[0];
            for (Size size2 : sizeArr) {
                if (size2.getWidth() * size2.getHeight() > size.getWidth() * size.getHeight()) {
                    size = size2;
                }
            }
            return size;
        }

        private boolean isHardwareLevelAtLeast(int i) {
            int[] iArr = {2, 4, 0, 1, 3};
            if (i == this.mHwLevel) {
                return true;
            }
            for (int i2 = 0; i2 < 5; i2++) {
                int i3 = iArr[i2];
                if (i3 == i) {
                    return true;
                }
                if (i3 == this.mHwLevel) {
                    return false;
                }
            }
            return false;
        }

        private boolean isExternalCamera() {
            return this.mHwLevel == 4;
        }

        private boolean isHardwareLevelAtLeastLegacy() {
            return isHardwareLevelAtLeast(2);
        }

        private boolean isHardwareLevelAtLeastLimited() {
            return isHardwareLevelAtLeast(0);
        }

        private boolean isHardwareLevelAtLeastFull() {
            return isHardwareLevelAtLeast(1);
        }

        private boolean isHardwareLevelAtLeastLevel3() {
            return isHardwareLevelAtLeast(3);
        }

        private boolean isCapabilitySupported(int i) {
            return this.mCapabilities.contains(Integer.valueOf(i));
        }

        private boolean isColorOutputSupported() {
            return isCapabilitySupported(0);
        }

        private boolean isPrivateReprocessingSupported() {
            return isCapabilitySupported(4);
        }

        private boolean isYUVReprocessingSupported() {
            return isCapabilitySupported(7);
        }

        private Size getMaxRecordingSize() {
            int i = 8;
            if (!CamcorderProfile.hasProfile(this.mCameraId, 8)) {
                if (CamcorderProfile.hasProfile(this.mCameraId, 6)) {
                    i = 6;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 5)) {
                    i = 5;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 4)) {
                    i = 4;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 7)) {
                    i = 7;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 3)) {
                    i = 3;
                } else {
                    i = CamcorderProfile.hasProfile(this.mCameraId, 2) ? 2 : -1;
                }
            }
            if (i < 0) {
                return null;
            }
            CamcorderProfile camcorderProfile = CamcorderProfile.get(this.mCameraId, i);
            return new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
        }

        private Size getMaxCameraRecordingSize() {
            Size size = new Size(1920, 1080);
            Size[] outputSizes = this.mStreamConfigMap.getOutputSizes(MediaRecorder.class);
            ArrayList arrayList = new ArrayList();
            for (Size size2 : outputSizes) {
                if (size2.getWidth() <= size.getWidth() && size2.getHeight() <= size.getHeight()) {
                    arrayList.add(size2);
                }
            }
            for (Size size3 : getAscendingOrderSizes(arrayList, false)) {
                if (this.mStreamConfigMap.getOutputMinFrameDuration(MediaRecorder.class, size3) > 3.3222591362126246E7d) {
                    Log.i(MandatoryStreamCombination.TAG, "External camera " + this.mCameraId + " has max video size:" + size3);
                    return size3;
                }
            }
            Log.w(MandatoryStreamCombination.TAG, "Camera " + this.mCameraId + " does not support any 30fps video output");
            return size;
        }

        private Size getMaxPreviewSize(List<Size> list) {
            if (list != null) {
                for (Size size : list) {
                    if (this.mDisplaySize.getWidth() >= size.getWidth() && this.mDisplaySize.getHeight() >= size.getHeight()) {
                        return size;
                    }
                }
            }
            Log.w(MandatoryStreamCombination.TAG, "Camera " + this.mCameraId + " maximum preview size search failed with display size " + this.mDisplaySize);
            return this.kPreviewSizeBound;
        }

        public static class SizeComparator implements Comparator<Size> {
            @Override // java.util.Comparator
            public int compare(Size size, Size size2) {
                return Builder.compareSizes(size.getWidth(), size.getHeight(), size2.getWidth(), size2.getHeight());
            }
        }

        private static List<Size> getAscendingOrderSizes(List<Size> list, boolean z) {
            SizeComparator sizeComparator = new SizeComparator();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list);
            Collections.sort(arrayList, sizeComparator);
            if (!z) {
                Collections.reverse(arrayList);
            }
            return arrayList;
        }
    }

    /* JADX INFO: renamed from: android.hardware.camera2.params.MandatoryStreamCombination$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold;

        static {
            int[] iArr = new int[SizeThreshold.values().length];
            $SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold = iArr;
            try {
                iArr[SizeThreshold.s1440p.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold[SizeThreshold.VGA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
