package com.amazon.avod.smoothstream.dash;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DashManifest implements Manifest {
    private final long mAvailabilityStartTimeMillis;
    private final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final TimeSpan mDuration;
    private boolean mHasStitchedAds;
    private final boolean mIsDynamic;
    private final boolean mIsEncrypted;
    private final boolean mIsHdr;
    private final boolean mIsPatternTemplateManifest;
    final boolean mIsSegmentTemplateBased;
    private final TimeSpan mManifestEndTime;
    private long mManifestHandle;
    private final DashManifestJni mManifestJni;
    private final TimeSpan mManifestStartTime;
    private final TimeSpan mMinimumUpdatePeriod;
    private final DashProtectionHeader mProtectionHeader;
    final boolean mShouldBeMultiPeriod;
    private final boolean mShouldValidateMinimalStreamCountPerPeriod;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;
    private StreamIndex[] mStreams;
    private final TimeSpan mSuggestedFrontMargin;
    private final TimeSpan mTimeshiftBufferDuration;

    /* JADX WARN: Multi-variable type inference failed */
    public DashManifest(@Nonnull DashManifestJni dashManifestJni, long j, @Nonnull DrmScheme drmScheme, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) throws ContentException {
        long j2;
        long j3;
        long j4;
        long j5;
        int i;
        int i2;
        DrmScheme drmScheme2;
        int i3;
        long j6;
        long j7;
        DrmScheme drmScheme3 = drmScheme;
        this.mManifestHandle = j;
        DashManifestJni dashManifestJni2 = (DashManifestJni) Preconditions.checkNotNull(dashManifestJni, "manifestJni");
        this.mManifestJni = dashManifestJni2;
        Preconditions.checkNotNull(drmScheme3, "drmScheme");
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig2 = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mSmoothStreamingPlaybackConfig = smoothStreamingPlaybackConfig2;
        this.mShouldValidateMinimalStreamCountPerPeriod = smoothStreamingPlaybackConfig2.shouldValidateMinimalStreamCountPerPeriod();
        long manifestTimeScale = dashManifestJni2.getManifestTimeScale();
        TimeSpan timeSpan = new TimeSpan(dashManifestJni2.getMinimumUpdatePeriod(this.mManifestHandle), manifestTimeScale);
        this.mMinimumUpdatePeriod = timeSpan;
        boolean zIsDynamic = dashManifestJni2.isDynamic(this.mManifestHandle);
        this.mIsDynamic = zIsDynamic;
        this.mIsPatternTemplateManifest = dashManifestJni2.isPattermTemplateManifest(this.mManifestHandle);
        long minUpdatePeriodToEnableManifestRefreshThresholdMillis = smoothStreamingPlaybackConfig.getMinUpdatePeriodToEnableManifestRefreshThresholdMillis();
        boolean multiPeriodManifestSupportForLegacyLiveStreamsEnabled = smoothStreamingPlaybackConfig.getMultiPeriodManifestSupportForLegacyLiveStreamsEnabled();
        long totalMilliseconds = timeSpan.getTotalMilliseconds();
        int i4 = 0;
        boolean zIsSegmentTemplateBased = dashManifestJni2.isSegmentTemplateBased(dashManifestJni2.getAvailableStream(this.mManifestHandle, 0, 0));
        this.mIsSegmentTemplateBased = zIsSegmentTemplateBased;
        int periodCount = dashManifestJni2.getPeriodCount(this.mManifestHandle);
        long j8 = manifestTimeScale;
        this.mShouldBeMultiPeriod = isMultiPeriod(totalMilliseconds, zIsDynamic, minUpdatePeriodToEnableManifestRefreshThresholdMillis, multiPeriodManifestSupportForLegacyLiveStreamsEnabled, zIsSegmentTemplateBased, periodCount);
        int availableStreamCount = dashManifestJni2.getAvailableStreamCount(this.mManifestHandle, 0);
        int i5 = 0;
        for (int i6 = 0; i6 < periodCount; i6++) {
            int availableStreamCount2 = this.mManifestJni.getAvailableStreamCount(this.mManifestHandle, i6);
            if (availableStreamCount2 > availableStreamCount) {
                i5 = i6;
            }
            availableStreamCount = Math.max(availableStreamCount, availableStreamCount2);
            validateMinimalStreamCountPerPeriod(i6, availableStreamCount2, this.mShouldValidateMinimalStreamCountPerPeriod);
        }
        this.mStreams = new StreamIndex[availableStreamCount];
        long j9 = Long.MAX_VALUE;
        long j10 = 0;
        int i7 = 0;
        ImageStreamIndex imageStreamIndexCreateImageStreamIndex = null;
        while (i7 < availableStreamCount) {
            long j11 = j10;
            long availableStream = this.mManifestJni.getAvailableStream(this.mManifestHandle, i5, i7);
            StreamType streamIndexType = this.mManifestJni.getStreamIndexType(availableStream);
            long periodHandle = this.mManifestJni.getPeriodHandle(this.mManifestHandle, i4);
            if (this.mShouldBeMultiPeriod) {
                int i8 = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[streamIndexType.ordinal()];
                if (i8 == 1) {
                    j2 = availableStream;
                    j3 = j9;
                    j4 = j11;
                    j5 = j8;
                    this.mStreams[i7] = SubtitlesMultiPeriodStreamIndex.createMultiPeriodDashIndex(this.mManifestJni, this.mManifestHandle, i7, j5, drmScheme);
                } else if (i8 == 2) {
                    j2 = availableStream;
                    j3 = j9;
                    j4 = j11;
                    j5 = j8;
                    this.mStreams[i7] = ImageMultiPeriodStreamIndex.createMultiPeriodDashIndex(this.mManifestJni, this.mManifestHandle, i7, j5, drmScheme);
                } else {
                    j2 = availableStream;
                    j4 = j11;
                    j3 = j9;
                    this.mStreams[i7] = MultiPeriodStreamIndex.createMultiPeriodDashIndex(this.mManifestJni, this.mManifestHandle, i7, this.mIsDynamic, j8, drmScheme);
                    j5 = j8;
                }
                i = i5;
                i2 = i7;
                drmScheme2 = drmScheme3;
                i3 = i4;
            } else {
                j2 = availableStream;
                j3 = j9;
                j4 = j11;
                if (streamIndexType == StreamType.IMAGE) {
                    j5 = j8;
                    i = i5;
                    i2 = i7;
                    drmScheme2 = drmScheme3;
                    i3 = i4;
                    imageStreamIndexCreateImageStreamIndex = ImageStreamIndex.createImageStreamIndex(this.mManifestJni, this.mManifestHandle, periodHandle, -1L, j2, i2, true, j5, drmScheme, periodCount);
                    this.mStreams[i2] = imageStreamIndexCreateImageStreamIndex;
                } else {
                    j5 = j8;
                    i = i5;
                    i2 = i7;
                    drmScheme2 = drmScheme3;
                    i3 = i4;
                    this.mStreams[i2] = DashStreamIndex.createDashIndex(this.mManifestJni, this.mManifestHandle, periodHandle, j2, i2, this.mIsDynamic, j5, drmScheme);
                }
            }
            if (StreamType.isEssential(this.mStreams[i2].getType())) {
                long streamEndTimestampNanos = this.mStreams[i2].getStreamEndTimestampNanos();
                j7 = j3;
                j7 = streamEndTimestampNanos < j7 ? streamEndTimestampNanos : j7;
                if (this.mIsSegmentTemplateBased) {
                    long streamChunkTimeStampFromSegmentTemplate = this.mManifestJni.getStreamChunkTimeStampFromSegmentTemplate(j2, i3);
                    j6 = j4;
                    if (streamChunkTimeStampFromSegmentTemplate > j6) {
                        j6 = streamChunkTimeStampFromSegmentTemplate;
                    }
                } else {
                    j6 = j4;
                }
            } else {
                j6 = j4;
                j7 = j3;
            }
            i4 = i3;
            j8 = j5;
            j9 = j7;
            i5 = i;
            long j12 = j6;
            i7 = i2 + 1;
            drmScheme3 = drmScheme2;
            j10 = j12;
        }
        long j13 = j9;
        boolean z = i4;
        long j14 = j10;
        DrmScheme drmScheme4 = drmScheme3;
        long j15 = j8;
        StreamIndex[] streamIndexArr = this.mStreams;
        if (streamIndexArr.length == 0) {
            this.mHasStitchedAds = z;
        } else {
            String baseUrl = ((DashQualityLevel) streamIndexArr[z ? 1 : 0].getQualityLevel(z ? 1 : 0, z ? 1 : 0)).getBaseUrl();
            this.mHasStitchedAds = (baseUrl == null || !baseUrl.startsWith("2@")) ? z ? 1 : 0 : true;
        }
        this.mManifestStartTime = new TimeSpan(j14, j15);
        this.mManifestEndTime = new TimeSpan(j13);
        if (imageStreamIndexCreateImageStreamIndex != null) {
            imageStreamIndexCreateImageStreamIndex.initializeIndex();
        }
        this.mDuration = new TimeSpan(this.mManifestJni.getManifestDuration(this.mManifestHandle), j15);
        this.mTimeshiftBufferDuration = new TimeSpan(this.mManifestJni.getTimeShiftBufferDepth(this.mManifestHandle), j15);
        this.mAvailabilityStartTimeMillis = getAvailabilityStartTimeFromManifest(this.mManifestHandle);
        this.mSuggestedFrontMargin = new TimeSpan(this.mManifestJni.getFrontMargin(this.mManifestHandle), j15);
        DeviceResources.getInstance().setMinimumUpdatePeriodInSeconds(this.mMinimumUpdatePeriod.getTotalSeconds());
        this.mIsEncrypted = this.mManifestJni.isEncrypted(this.mManifestHandle);
        StreamIndex videoStream = getVideoStream();
        this.mIsHdr = (videoStream == null || !videoStream.isHdr()) ? z ? 1 : 0 : true;
        String protectionHeader = this.mManifestJni.getProtectionHeader(this.mManifestHandle, drmScheme4);
        this.mProtectionHeader = !Strings.isNullOrEmpty(protectionHeader) ? new DashProtectionHeader(protectionHeader, drmScheme4) : null;
    }

    /* JADX INFO: renamed from: com.amazon.avod.smoothstream.dash.DashManifest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;

        static {
            int[] iArr = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr;
            try {
                iArr[StreamType.SUBTITLES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @VisibleForTesting
    void validateMinimalStreamCountPerPeriod(int i, int i2, boolean z) throws ContentException {
        if (z) {
            String periodIdString = this.mManifestJni.getPeriodIdString(this.mManifestJni.getPeriodHandle(this.mManifestHandle, i));
            boolean z2 = true;
            boolean z3 = true;
            for (int i3 = 0; i3 < i2; i3++) {
                StreamType streamIndexType = this.mManifestJni.getStreamIndexType(this.mManifestJni.getAvailableStream(this.mManifestHandle, i, i3));
                if (streamIndexType == StreamType.VIDEO) {
                    z2 = false;
                } else if (streamIndexType == StreamType.AUDIO) {
                    z3 = false;
                }
            }
            if (z2 || z3) {
                ContentException.ContentError contentError = ContentException.ContentError.MALFORMED_MANIFEST;
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = periodIdString;
                objArr[1] = z2 ? "Video" : "Audio";
                throw new ContentException(contentError, String.format(locale, "Period %s: missing %s track", objArr));
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public TimeSpan getDuration() {
        return this.mDuration;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public int getNumStreams() {
        return this.mStreams.length;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public List<StreamIndex> getStreams() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (StreamIndex streamIndex : this.mStreams) {
            builder.add(streamIndex);
        }
        return builder.build();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isDynamic() {
        return this.mIsDynamic;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean hasMultiPeriodSupport() {
        return this.mShouldBeMultiPeriod;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public Boolean isPatternTemplateManifest() {
        return Boolean.valueOf(this.mIsPatternTemplateManifest);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public StreamIndex getVideoStream() {
        for (StreamIndex streamIndex : this.mStreams) {
            if (streamIndex.isVideo()) {
                return streamIndex;
            }
        }
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public StreamIndex getAudioStream(@Nonnull String str, String str2) {
        Preconditions.checkNotNull(str, "fourCC");
        int numStreams = getNumStreams();
        int i = -1;
        int bitrate = Integer.MIN_VALUE;
        int bitrate2 = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < numStreams; i3++) {
            StreamIndex streamIndex = this.mStreams[i3];
            if (streamIndex.getType() == StreamType.AUDIO) {
                QualityLevel[] sortedQualityLevels = streamIndex.getSortedQualityLevels(0);
                if (str.equalsIgnoreCase(sortedQualityLevels[0].getFourCC()) && (str2 == null || str2.equalsIgnoreCase(streamIndex.getLanguage()))) {
                    QualityLevel qualityLevel = sortedQualityLevels[0];
                    QualityLevel qualityLevel2 = sortedQualityLevels[sortedQualityLevels.length - 1];
                    if (qualityLevel.getBitrate() < bitrate2) {
                        bitrate2 = qualityLevel.getBitrate();
                        i2 = i3;
                    }
                    if (qualityLevel2.getBitrate() > bitrate) {
                        bitrate = qualityLevel2.getBitrate();
                        i = i3;
                    }
                }
            }
        }
        if (SmoothStreamingPlaybackConfig.INSTANCE.shouldSelectLowerAudioBitrates()) {
            i = i2;
        }
        if (i < 0 && str2 != null) {
            return getAudioStream(str, null);
        }
        if (i < 0) {
            return null;
        }
        return this.mStreams[i];
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public StreamIndex getSubtitleStream() {
        for (StreamIndex streamIndex : this.mStreams) {
            if (streamIndex.getType() == StreamType.SUBTITLES) {
                return streamIndex;
            }
        }
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public StreamIndex getImageStream() {
        for (StreamIndex streamIndex : this.mStreams) {
            if (streamIndex.getType() == StreamType.IMAGE) {
                return streamIndex;
            }
        }
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isHD() {
        for (QualityLevel qualityLevel : getVideoStream().getSortedQualityLevels(0)) {
            if (((VideoQualityLevel) qualityLevel).isHD()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isHdr() {
        return this.mIsHdr;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isEncrypted() {
        return this.mIsEncrypted;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public ProtectionHeader getProtectionHeader() {
        return this.mProtectionHeader;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public void release() {
        if (this.mManifestHandle != 0) {
            for (StreamIndex streamIndex : this.mStreams) {
                streamIndex.release();
            }
            this.mManifestJni.releaseManifest(this.mManifestHandle);
            this.mManifestHandle = 0L;
            return;
        }
        DLog.warnf("Manifest was released before!");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public TimeSpan getTimeshiftBufferDuration() {
        return this.mTimeshiftBufferDuration;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public Set<String> getAvailableAudioLanguages(String str) {
        Preconditions.checkNotNull(str, "fourCC");
        HashSet hashSetNewHashSet = Sets.newHashSet();
        for (StreamIndex streamIndex : this.mStreams) {
            if (streamIndex.getType() == StreamType.AUDIO && str.equalsIgnoreCase(streamIndex.getSortedQualityLevels(0)[0].getFourCC())) {
                hashSetNewHashSet.add(streamIndex.getLanguage().toLowerCase(Locale.US));
            }
        }
        return hashSetNewHashSet;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public Set<String> getAvailableAudioLanguagesAcrossAudioStreams() {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        for (StreamIndex streamIndex : this.mStreams) {
            if (streamIndex.getType() == StreamType.AUDIO) {
                hashSetNewHashSet.add(streamIndex.getLanguage().toLowerCase(Locale.US));
            }
        }
        return hashSetNewHashSet;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public long getAvailabilityStartTimeMillis() {
        return this.mAvailabilityStartTimeMillis;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getManifestStartTime() {
        return this.mManifestStartTime;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getManifestEndTime() {
        return this.mManifestEndTime;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getSuggestedFrontMargin() {
        return this.mSuggestedFrontMargin;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getMinimumUpdatePeriod() {
        return this.mMinimumUpdatePeriod;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public List<StreamIndex> getSupportedAudioStreams(@Nullable String str, @Nonnull List<String> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (StreamIndex streamIndex : this.mStreams) {
            if (streamIndex != null && streamIndex.getType() == StreamType.AUDIO) {
                QualityLevel[] sortedQualityLevels = streamIndex.getSortedQualityLevels(0);
                if (sortedQualityLevels.length > 0 && list.contains(sortedQualityLevels[0].getFourCC()) && (str == null || str.equalsIgnoreCase(streamIndex.getLanguage()))) {
                    builder.add(streamIndex);
                }
            }
        }
        ImmutableList immutableListBuild = builder.build();
        return (!immutableListBuild.isEmpty() || str == null) ? immutableListBuild : getSupportedAudioStreams(null, list);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public long getManifestHandle() {
        if (this.mIsSegmentTemplateBased) {
            return 0L;
        }
        return this.mManifestHandle;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public QualityLevel getHighestVideoQualityLevel() {
        StreamIndex videoStream = getVideoStream();
        if (videoStream == null) {
            return null;
        }
        return videoStream.getSortedQualityLevels(0)[r0.length - 1];
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean hasStitchedAds() {
        return this.mHasStitchedAds;
    }

    @VisibleForTesting
    public boolean isMultiPeriod(long j, boolean z, long j2, boolean z2, boolean z3, long j3) {
        return z ? (j > 0 && j < j2) || z2 : this.mSmoothStreamingPlaybackConfig.useMultiPeriodForSegmentTemplateBasedManifest() ? z3 : z3 && j3 > 1;
    }

    private long getAvailabilityStartTimeFromManifest(long j) throws ContentException {
        String availabilityStartTimeUTC = this.mManifestJni.getAvailabilityStartTimeUTC(j);
        if (Strings.isNullOrEmpty(availabilityStartTimeUTC)) {
            return -1L;
        }
        this.mDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return this.mDateFormat.parse(availabilityStartTimeUTC).getTime();
        } catch (ParseException e) {
            throw new ContentException(String.format(Locale.US, "Error parsing availabilityStartTime %s : %s", availabilityStartTimeUTC, e));
        }
    }
}
