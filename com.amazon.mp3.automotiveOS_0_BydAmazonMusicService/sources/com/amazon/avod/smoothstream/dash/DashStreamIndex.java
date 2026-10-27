package com.amazon.avod.smoothstream.dash;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.smoothstream.DashManifestUtil;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.BaseEncoding;
import com.google.common.primitives.Ints;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DashStreamIndex implements StreamIndex {
    protected final long mAdaptationSetHandle;
    private String mAudioTrackId;
    private String mBaseUrlOverride;
    protected int mChunkCount;
    private byte[] mDefaultKeyId;
    private final double mDisplayAspectRatio;
    private final DrmScheme mDrmScheme;
    private long mDurationsHandle;
    private String mFourCC;
    private final int mIndex;
    private final boolean mIsContentEncrypted;
    protected final boolean mIsDynamic;
    protected boolean mIsSegmentListBased;
    private JSONObject mJsonRepresentation;
    private String mLanguage;
    private volatile boolean mManifestAvailable = true;
    private final long mManifestHandle;
    protected final DashManifestJni mManifestJni;
    protected final long mManifestTimescale;
    private float mMaxFrameRate;
    private int mMaxHeight;
    private int mMaxWidth;
    private long mPeriodEncodeTimeEpochMillis;
    protected final long mPeriodHandle;
    protected final long mPeriodStartTimeNanos;
    private final PlaybackPmetMetricReporter mPmetMetricReporter;
    protected DashQualityLevel[] mQualityLevels;
    private boolean mRequiresInitFragments;
    private final boolean mShouldUseHierarchicalBaseURLResolver;
    private final boolean mShouldUseURIBasedBaseURLResolution;
    private DashQualityLevel[] mSortedQualityLevels;
    protected int mStartSegmentIndex;
    private StreamType mType;

    DashStreamIndex(@Nonnull DashManifestJni dashManifestJni, long j, long j2, long j3, int i, boolean z, long j4, @Nonnull DrmScheme drmScheme, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        DashManifestJni dashManifestJni2 = (DashManifestJni) Preconditions.checkNotNull(dashManifestJni, "manifestJni");
        this.mManifestJni = dashManifestJni2;
        this.mManifestHandle = j;
        this.mPeriodHandle = j2;
        this.mAdaptationSetHandle = j3;
        this.mIndex = i;
        this.mIsDynamic = z;
        this.mManifestTimescale = j4;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mDisplayAspectRatio = dashManifestJni2.getStreamDisplayAspectRatio(j3);
        this.mFourCC = dashManifestJni2.getQualityLevelFourCC(dashManifestJni2.getStreamQualityLevel(j3, 0));
        this.mIsSegmentListBased = dashManifestJni2.isSegmentListBased(j3);
        if (AudioStreamType.ATMOS.getFourCC().equals(this.mFourCC)) {
            this.mFourCC = AudioStreamType.DDP.getFourCC();
        }
        this.mIsContentEncrypted = dashManifestJni2.isEncrypted(j);
        this.mPeriodStartTimeNanos = TimeSpan.nanosecondsFromTimeScale(dashManifestJni2.getPeriodStartTime(j2), j4);
        this.mShouldUseURIBasedBaseURLResolution = ((SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "SmoothStreamingPlaybackConfig")).shouldUseURIBasedBaseURLResolution();
        this.mShouldUseHierarchicalBaseURLResolver = smoothStreamingPlaybackConfig.shouldUseHierarchicalBaseURLResolver();
        this.mPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "PlaybackPmetMetricReporter");
    }

    public static DashStreamIndex createDashIndex(@Nonnull DashManifestJni dashManifestJni, long j, long j2, long j3, int i, boolean z, long j4, @Nonnull DrmScheme drmScheme) throws ContentException {
        DashStreamIndex dashStreamIndex = new DashStreamIndex(dashManifestJni, j, j2, j3, i, z, j4, drmScheme, SmoothStreamingPlaybackConfig.INSTANCE, PlaybackPmetMetricReporter.getInstance());
        dashStreamIndex.initializeIndex();
        return dashStreamIndex;
    }

    public void initializeIndex() throws ContentException {
        int streamChunkCountFromSegmentTemplate;
        this.mBaseUrlOverride = this.mManifestJni.getBaseUrl(this.mManifestHandle, this.mPeriodHandle, this.mAdaptationSetHandle);
        this.mPeriodEncodeTimeEpochMillis = this.mManifestJni.getPeriodEncodeTimeEpochMillis(this.mPeriodHandle);
        this.mType = this.mManifestJni.getStreamIndexType(this.mAdaptationSetHandle);
        int streamQualityLevelCount = this.mManifestJni.getStreamQualityLevelCount(this.mAdaptationSetHandle);
        this.mQualityLevels = new DashQualityLevel[streamQualityLevelCount];
        this.mSortedQualityLevels = new DashQualityLevel[streamQualityLevelCount];
        for (int i = 0; i < streamQualityLevelCount; i++) {
            this.mQualityLevels[i] = newTypedQualityLevel(i);
            DashQualityLevel[] dashQualityLevelArr = this.mSortedQualityLevels;
            DashQualityLevel[] dashQualityLevelArr2 = this.mQualityLevels;
            dashQualityLevelArr[i] = dashQualityLevelArr2[i];
            this.mMaxFrameRate = Math.max(this.mMaxFrameRate, dashQualityLevelArr2[i].getFrameRate());
            this.mMaxWidth = Math.max(this.mMaxWidth, this.mQualityLevels[i].getMaxWidth());
            this.mMaxHeight = Math.max(this.mMaxHeight, this.mQualityLevels[i].getMaxHeight());
        }
        Arrays.sort(this.mSortedQualityLevels, new Comparator<DashQualityLevel>() { // from class: com.amazon.avod.smoothstream.dash.DashStreamIndex.1
            @Override // java.util.Comparator
            public int compare(DashQualityLevel dashQualityLevel, DashQualityLevel dashQualityLevel2) {
                return Ints.compare(dashQualityLevel.getBitrate(), dashQualityLevel2.getBitrate());
            }
        });
        if (this.mIsSegmentListBased) {
            streamChunkCountFromSegmentTemplate = this.mManifestJni.getStreamChunkCountFromSegmentList(this.mAdaptationSetHandle);
        } else {
            streamChunkCountFromSegmentTemplate = this.mManifestJni.getStreamChunkCountFromSegmentTemplate(this.mAdaptationSetHandle);
        }
        this.mChunkCount = streamChunkCountFromSegmentTemplate;
        DLog.devf("take mChunkCount from segment list when there is one. chunkcount = %d, adaptationset = %d", Integer.valueOf(streamChunkCountFromSegmentTemplate), Long.valueOf(this.mAdaptationSetHandle));
        this.mStartSegmentIndex = this.mManifestJni.getQualityLevelChunkStartNumber(this.mManifestJni.getStreamQualityLevel(this.mAdaptationSetHandle, 0));
        this.mDurationsHandle = this.mManifestJni.getSegmentDurationsHandle(this.mAdaptationSetHandle);
        String defaultKeyId = this.mManifestJni.getDefaultKeyId(this.mAdaptationSetHandle);
        this.mDefaultKeyId = defaultKeyId == null ? null : BaseEncoding.base16().decode(defaultKeyId.replace("-", ""));
        if (this.mIsSegmentListBased && this.mDurationsHandle == 0) {
            throw new ContentException("Bad Dash Manifest - Invalid segment durations handle.");
        }
        this.mLanguage = this.mManifestJni.getStreamLanguage(this.mAdaptationSetHandle);
        this.mAudioTrackId = this.mManifestJni.getStreamAudioTrackId(this.mAdaptationSetHandle);
        this.mRequiresInitFragments = areInitFragmentsRequired();
        this.mJsonRepresentation = getJson();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getLanguage() {
        return this.mLanguage;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getAudioTrackId() {
        return this.mAudioTrackId;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isAudio() {
        return this.mType == StreamType.AUDIO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isVideo() {
        return this.mType == StreamType.VIDEO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public DashQualityLevel[] getSortedQualityLevels(int i) {
        return this.mSortedQualityLevels;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public DashQualityLevel getQualityLevel(int i, int i2) {
        return this.mQualityLevels[i2];
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, QualityLevel qualityLevel) {
        return getClosestQualityLevel(i, qualityLevel.getBitrate());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public DashQualityLevel getQualityLevelLessThanEqual(int i, int i2) {
        return getQualityLevel(0, DashManifestUtil.getQualityLevelIndexLessThanEqual(this.mQualityLevels, i2));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public DashQualityLevel getQualityLevelGreaterThanEqual(int i, int i2) {
        return getQualityLevel(i, DashManifestUtil.getQualityLevelIndexGreaterThanEqual(this.mQualityLevels, i2));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public DashQualityLevel getClosestQualityLevel(int i, int i2) {
        return getQualityLevel(0, DashManifestUtil.findClosestQualityIndex(this.mQualityLevels, i2));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution) {
        Preconditions.checkNotNull(videoResolution, "maxResolution");
        Preconditions.checkState(this.mType.equals(StreamType.VIDEO), "getClosestQualityLevelWithResolutionCap called on non-video stream");
        int width = videoResolution.getWidth();
        int height = videoResolution.getHeight();
        int iFindClosestQualityIndex = DashManifestUtil.findClosestQualityIndex(this.mQualityLevels, i2);
        VideoQualityLevel videoQualityLevel = (VideoQualityLevel) getQualityLevel(0, iFindClosestQualityIndex);
        int maxWidth = videoQualityLevel.getMaxWidth();
        int maxHeight = videoQualityLevel.getMaxHeight();
        while (true) {
            if ((maxWidth <= width && maxHeight <= height) || iFindClosestQualityIndex <= 0) {
                break;
            }
            iFindClosestQualityIndex--;
            videoQualityLevel = (VideoQualityLevel) getQualityLevel(0, iFindClosestQualityIndex);
            maxWidth = videoQualityLevel.getMaxWidth();
            maxHeight = videoQualityLevel.getMaxHeight();
        }
        return videoQualityLevel;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeInNanos(int i) {
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getChunkTimeInNanos when the manifest isn't available.");
        if (this.mIsSegmentListBased) {
            return TimeSpan.nanosecondsFromTimeScale(this.mManifestJni.getStreamChunkTimestamp(this.mDurationsHandle, i), this.mManifestTimescale);
        }
        return TimeSpan.nanosecondsFromTimeScale(this.mManifestJni.getStreamChunkTimeStampFromSegmentTemplate(this.mAdaptationSetHandle, DashManifestUtil.convertAbsoluteToRelativeIndex(this.mManifestJni, this.mAdaptationSetHandle, i, true)), this.mManifestTimescale);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeOffsetInNanos(int i) {
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getChunkTimeOffsetInNanos when the manifest isn't available.");
        if (this.mIsSegmentListBased) {
            return 0L;
        }
        return TimeSpan.nanosecondsFromTimeScale(this.mManifestJni.getStreamChunkTimeOffsetFromSegmentTemplate(this.mAdaptationSetHandle, DashManifestUtil.convertAbsoluteToRelativeIndex(this.mManifestJni, this.mAdaptationSetHandle, i, true)), this.mManifestTimescale);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getChunkIndexFromNanos(long j) {
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getChunkIndexFromNanos when the manifest isn't available.");
        return DashManifestUtil.getAbsoluteChunkIndexFromNanos(this.mManifestTimescale, this.mManifestJni, this.mAdaptationSetHandle, this.mDurationsHandle, this.mChunkCount, j, !this.mIsSegmentListBased);
    }

    public long getChunksDurationInNanos(int i, int i2) {
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getChunkDurationInNanos when the manifest isn't available.");
        return DashManifestUtil.getChunksDurationInNanosFromAbsoluteIndices(this.mManifestTimescale, this.mManifestJni, this.mAdaptationSetHandle, this.mDurationsHandle, this.mChunkCount, i, i2, !this.mIsSegmentListBased);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkDurationInNanos(int i) {
        return getChunksDurationInNanos(i, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastPlayableChunk(int i) {
        if (this.mIsDynamic) {
            return false;
        }
        boolean z = this.mIsSegmentListBased;
        if (z) {
            return DashManifestUtil.isLastChunk(this.mChunkCount, i);
        }
        return DashManifestUtil.convertAbsoluteToRelativeIndex(this.mManifestJni, this.mAdaptationSetHandle, i, z ^ true) + 1 == this.mChunkCount;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastDownloadableChunk(int i) {
        return isLastPlayableChunk(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getNumChunks() {
        if (this.mIsDynamic) {
            return Integer.MAX_VALUE;
        }
        return this.mChunkCount;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean requiresInitFragments() {
        return this.mRequiresInitFragments;
    }

    private boolean areInitFragmentsRequired() {
        StreamType streamType = this.mType;
        if (streamType == StreamType.SUBTITLES || streamType == StreamType.IMAGE) {
            return false;
        }
        if (this.mIsContentEncrypted && Strings.isNullOrEmpty(this.mManifestJni.getProtectionHeader(this.mManifestHandle, this.mDrmScheme))) {
            return true;
        }
        long streamQualityLevel = this.mManifestJni.getStreamQualityLevel(this.mAdaptationSetHandle, 0);
        if (DashManifestUtil.isCodecPrivateDataOptional(this.mManifestJni.getQualityLevelFourCC(streamQualityLevel))) {
            return false;
        }
        return Strings.isNullOrEmpty(this.mManifestJni.getQualityLevelCodecData(streamQualityLevel));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getInitUrl(String str, QualityLevel qualityLevel, int i) {
        Preconditions.checkNotNull(qualityLevel, "quality");
        DashQualityLevel dashQualityLevel = (DashQualityLevel) qualityLevel;
        return getUrl(str, dashQualityLevel.getBaseUrl(), dashQualityLevel.getFragmentInitialization());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getUrl(@Nullable String str, QualityLevel qualityLevel, int i) {
        Preconditions.checkNotNull(qualityLevel, "quality");
        DashQualityLevel dashQualityLevel = (DashQualityLevel) qualityLevel;
        return getUrl(str, dashQualityLevel.getBaseUrl(), dashQualityLevel.getFragmentMedia(i));
    }

    String getUrl(String str, String str2, String str3) {
        if (!Strings.isNullOrEmpty(str3) && URI.create(str3).isAbsolute()) {
            return str3;
        }
        if (this.mShouldUseHierarchicalBaseURLResolver) {
            return getUrl(str, this.mManifestJni.getBaseUrl(str, this.mManifestHandle, this.mPeriodHandle, this.mAdaptationSetHandle), str2, str3);
        }
        if (Strings.isNullOrEmpty(str2)) {
            str2 = this.mBaseUrlOverride;
        }
        if (!Strings.isNullOrEmpty(str2)) {
            if (URI.create(str2).isAbsolute()) {
                str = str2;
            } else if (this.mShouldUseURIBasedBaseURLResolution) {
                str = resolveFragmentUrl(str, str2);
            } else if (str2.startsWith("..")) {
                str = resolveRelativePath(str, str2);
            } else {
                str = constructFragmentUrl(str, str2);
            }
        }
        if (this.mShouldUseURIBasedBaseURLResolution) {
            return resolveFragmentUrl(str, str3);
        }
        return constructFragmentUrl(str, str3);
    }

    @Nullable
    @VisibleForTesting
    String getUrl(String str, String str2, String str3, String str4) {
        try {
            URI uri = new URI(str2);
            if (!Strings.isNullOrEmpty(str3)) {
                uri = uri.resolve(str3);
            }
            if (!Strings.isNullOrEmpty(str4)) {
                uri = uri.resolve(str4);
            }
            return uri.toString();
        } catch (IllegalArgumentException | URISyntaxException unused) {
            DLog.errorf("Encountered exception while resolving fragmentUrl, Manifest: %s, representationBaseUrl: %s, fragmentFile: %s", str, str3, str4);
            this.mPmetMetricReporter.reportFragmentURLResolutionErrorMetrics(FragmentURLResolutionPmetMetrics.toReportableString(FragmentURLResolutionPmetMetrics.FRAGMENT_URL_RESOLUTION_ERROR));
            return str2;
        }
    }

    private String resolveRelativePath(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.substring(0, str.lastIndexOf("/")));
        for (String str3 : str2.split("/")) {
            if ("..".equals(str3)) {
                int iLastIndexOf = sb.lastIndexOf("/");
                if (iLastIndexOf >= 0) {
                    sb.delete(iLastIndexOf, sb.length());
                }
            } else {
                sb.append("/");
                sb.append(str3);
            }
        }
        sb.append("/");
        return sb.toString();
    }

    private String constructFragmentUrl(String str, String str2) {
        Preconditions.checkNotNull(str, "baseUrl");
        if (Strings.isNullOrEmpty(str2)) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf >= 0) {
            str = str.substring(0, iLastIndexOf);
        }
        return str + "/" + str2;
    }

    @VisibleForTesting
    String resolveFragmentUrl(@Nonnull String str, String str2) {
        Preconditions.checkNotNull(str, "baseUrl");
        if (Strings.isNullOrEmpty(str2)) {
            return str;
        }
        try {
            return new URI(str).resolve(str2).toString();
        } catch (IllegalArgumentException | URISyntaxException e) {
            DLog.errorf("Encountered exception while resolving fragmentUrl: %s from baseUrl: %s, exception: %s", str, str2, e.getMessage());
            this.mPmetMetricReporter.reportFragmentURLResolutionErrorMetrics(FragmentURLResolutionPmetMetrics.toReportableString(FragmentURLResolutionPmetMetrics.FRAGMENT_URL_RESOLUTION_ERROR));
            return str;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public Map<String, String> getInitHeaders(QualityLevel qualityLevel) {
        if (!this.mIsSegmentListBased) {
            return Collections.emptyMap();
        }
        return getFragmentHeaders(((DashQualityLevel) qualityLevel).getInitRange());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public Map<String, String> getHeaders(QualityLevel qualityLevel, int i) {
        if (!this.mIsSegmentListBased) {
            return Collections.emptyMap();
        }
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getHeaders when the manifest isn't available.");
        return getFragmentHeaders(this.mManifestJni.getStreamChunkRange(this.mAdaptationSetHandle, i, qualityLevel.getBitrate()));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel) {
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getExpectedInitChunkSizeInBytes when the manifest isn't available.");
        return ((DashQualityLevel) qualityLevel).getInitSize();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i) {
        return this.mManifestJni.getStreamChunkSize(this.mAdaptationSetHandle, getRelativeChunkIndex(i), qualityLevel.getBitrate());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i) {
        return this.mManifestJni.getChunkQualityScore(this.mAdaptationSetHandle, getRelativeChunkIndex(i), qualityLevel.getBitrate());
    }

    private int getRelativeChunkIndex(int i) {
        Preconditions.checkArgument(this.mManifestAvailable, "Manifest isn't available.");
        Preconditions.checkArgument(this.mIsDynamic || !this.mIsSegmentListBased || i < this.mChunkCount, "chunkIndex must be less than mChunkCount except for livestreaming");
        return DashManifestUtil.convertAbsoluteToRelativeIndex(this.mManifestJni, this.mAdaptationSetHandle, i, this.mIsDynamic);
    }

    private Map<String, String> getFragmentHeaders(String str) {
        return ImmutableMap.of("Range", "bytes=" + str);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public StreamType getType() {
        return this.mType;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public JSONObject toJson() {
        return this.mJsonRepresentation;
    }

    private JSONObject getJson() {
        HashMap mapNewHashMap = Maps.newHashMap();
        mapNewHashMap.put("Type", this.mType.name());
        mapNewHashMap.put("Index", Integer.valueOf(this.mIndex));
        mapNewHashMap.put("StreamDurationInNanos", Long.valueOf(getStreamDurationInNanos()));
        mapNewHashMap.put("FourCC", this.mFourCC);
        mapNewHashMap.put("IsContentEncrypted", Boolean.valueOf(this.mIsContentEncrypted));
        mapNewHashMap.put("DefaultKeyId", this.mDefaultKeyId);
        mapNewHashMap.put("NumFragments", Integer.valueOf(this.mChunkCount));
        mapNewHashMap.put("PeriodStartTimeNanos", Long.valueOf(this.mPeriodStartTimeNanos));
        mapNewHashMap.put("StartSegmentIndex", Integer.valueOf(this.mStartSegmentIndex));
        mapNewHashMap.put("IsSegmentListBased", Boolean.valueOf(this.mIsSegmentListBased));
        if (isAudio()) {
            mapNewHashMap.put("Language", this.mLanguage);
            mapNewHashMap.put("AudioTrackId", this.mAudioTrackId);
            mapNewHashMap.put("AudioFormat", getAudioFormat().name());
        }
        if (isVideo()) {
            mapNewHashMap.put("DisplayAspectRatio", Double.valueOf(this.mDisplayAspectRatio));
            mapNewHashMap.put("MaxFrameRate", Float.valueOf(this.mMaxFrameRate));
            mapNewHashMap.put("IsHdr", Boolean.valueOf(isHdr()));
            int length = this.mQualityLevels.length;
            if (length > 0) {
                mapNewHashMap.put("NumQualityLevels", Integer.valueOf(length));
                mapNewHashMap.put("MinQualityLevel", this.mSortedQualityLevels[0].toString());
                mapNewHashMap.put("MaxQualityLevel", this.mSortedQualityLevels[length - 1].toString());
            }
        }
        return new JSONObject((Map<?, ?>) mapNewHashMap);
    }

    public String toString() {
        JSONObject jSONObject = this.mJsonRepresentation;
        return jSONObject != null ? jSONObject.toString() : "Not yet initialized";
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamDurationInNanos() {
        if (this.mIsDynamic) {
            return TimeSpan.MAX_VALUE.getTotalNanoSeconds();
        }
        if (!this.mIsSegmentListBased) {
            return (this.mManifestJni.getStreamChunkTimeStampFromSegmentTemplate(this.mAdaptationSetHandle, this.mChunkCount - 1) + this.mManifestJni.getStreamChunkDurationFromSegmentTemplate(this.mAdaptationSetHandle, this.mChunkCount - 1)) - this.mManifestJni.getStreamChunkTimeStampFromSegmentTemplate(this.mAdaptationSetHandle, 0);
        }
        Preconditions.checkArgument(this.mManifestAvailable, "Calling getStreamDurationInNanos when the manifest isn't available.");
        return TimeSpan.nanosecondsFromTimeScale(this.mManifestJni.getStreamChunkTimestamp(this.mDurationsHandle, this.mChunkCount - 1) + this.mManifestJni.getStreamChunkDurationFromSegmentDurations(this.mDurationsHandle, this.mChunkCount - 1), this.mManifestTimescale);
    }

    protected DashQualityLevel newTypedQualityLevel(int i) {
        long streamQualityLevel = this.mManifestJni.getStreamQualityLevel(this.mAdaptationSetHandle, i);
        StreamType streamType = this.mType;
        if (streamType == null) {
            return new DashVideoQualityLevel(this.mManifestJni, streamQualityLevel, true ^ this.mIsSegmentListBased);
        }
        int i2 = AnonymousClass2.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[streamType.ordinal()];
        if (i2 == 1) {
            return new DashVideoQualityLevel(this.mManifestJni, streamQualityLevel, true ^ this.mIsSegmentListBased);
        }
        if (i2 == 2) {
            return new DashAudioQualityLevel(this.mManifestJni, streamQualityLevel, true ^ this.mIsSegmentListBased);
        }
        return new DashVideoQualityLevel(this.mManifestJni, streamQualityLevel, true ^ this.mIsSegmentListBased);
    }

    /* JADX INFO: renamed from: com.amazon.avod.smoothstream.dash.DashStreamIndex$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;

        static {
            int[] iArr = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr;
            try {
                iArr[StreamType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamHandle() {
        return this.mAdaptationSetHandle;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isOutOfWindow(int i) {
        return DashManifestUtil.convertAbsoluteToRelativeIndex(this.mManifestJni, this.mAdaptationSetHandle, i, this.mIsDynamic) < 0;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getDisplayAspectRatio() {
        return this.mDisplayAspectRatio;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isHdr() {
        Preconditions.checkState(this.mQualityLevels.length > 0, "Hdr cannot be determined as number of quality levels is not bigger than 0");
        return this.mQualityLevels[0].isHdr();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public AudioFormat getAudioFormat() {
        if (!isAudio()) {
            return AudioFormat.STEREO;
        }
        Preconditions.checkState(this.mQualityLevels.length > 0, "Surround cannot be determined as number of quality levels is not bigger than 0");
        return ((AudioQualityLevel) this.mQualityLevels[0]).getAudioFormat();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public void release() {
        this.mManifestAvailable = false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamEndTimestampNanos() {
        int lastSegmentIndex = getLastSegmentIndex();
        return getChunkTimeInNanos(lastSegmentIndex) + getChunkDurationInNanos(lastSegmentIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamStartTimestampNanos() {
        return getChunkTimeInNanos(getFirstSegmentIndex());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getEncodeTimeMillis(long j) {
        if (this.mPeriodEncodeTimeEpochMillis == -1) {
            return -1L;
        }
        return (j - TimeUnit.NANOSECONDS.toMillis(this.mPeriodStartTimeNanos)) + this.mPeriodEncodeTimeEpochMillis;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getFourCC() {
        return this.mFourCC;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public byte[] getDefaultKeyId() {
        return this.mDefaultKeyId;
    }

    public int getFirstSegmentIndex() {
        return this.mStartSegmentIndex;
    }

    public int getLastSegmentIndex() {
        return (this.mStartSegmentIndex + this.mChunkCount) - 1;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public float getMaxFrameRate() {
        return this.mMaxFrameRate;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxHeight() {
        return this.mMaxHeight;
    }
}
