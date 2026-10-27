package com.amazon.avod.smoothstream.dash;

import androidx.core.util.Pair;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex;
import com.amazon.avod.content.smoothstream.manifest.PeriodView;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifestValidator;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class SubtitlesMultiPeriodStreamIndex implements PeriodAwareStreamIndex {
    private static final QualityLevel[] BLANK_QUALITIES = new QualityLevel[0];
    private final List<DashStreamIndex> mDashStreamIndices = Lists.newLinkedList();
    private final DrmScheme mDrmScheme;
    private final int mIndex;
    private final boolean mIsDynamic;
    private JSONObject mJsonRepresentation;
    private final long mManifestHandle;
    private final DashManifestJni mManifestJni;
    private final long mManifestTimescale;
    private PeriodView mPeriodView;
    private final RefreshableManifestValidator.RefreshableManifestValidatorConfig mRefreshableManifestValidatorConfig;

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getAudioTrackId() {
        return "";
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public byte[] getDefaultKeyId() {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getDisplayAspectRatio() {
        return 0.0d;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public float getMaxFrameRate() {
        return 0.0f;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxHeight() {
        return 0;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxWidth() {
        return 0;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getNumChunks() {
        return Integer.MAX_VALUE;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamEndTimestampNanos() {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamHandle() {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamStartTimestampNanos() {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isAudio() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isHdr() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isVideo() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean requiresInitFragments() {
        return false;
    }

    SubtitlesMultiPeriodStreamIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, long j2, @Nonnull DrmScheme drmScheme, @Nonnull RefreshableManifestValidator.RefreshableManifestValidatorConfig refreshableManifestValidatorConfig) {
        this.mManifestJni = (DashManifestJni) Preconditions.checkNotNull(dashManifestJni, "manifestJni");
        this.mManifestHandle = j;
        this.mIndex = i;
        this.mManifestTimescale = j2;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mIsDynamic = dashManifestJni.isDynamic(j);
        this.mRefreshableManifestValidatorConfig = (RefreshableManifestValidator.RefreshableManifestValidatorConfig) Preconditions.checkNotNull(refreshableManifestValidatorConfig, "refreshableManifestValidatorConfig");
    }

    private void initialize(@Nonnull PeriodView periodView) throws ContentException {
        boolean z;
        int i;
        int periodCount = this.mManifestJni.getPeriodCount(this.mManifestHandle);
        if (periodCount < 1) {
            throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, "period count can't be zero or negative");
        }
        PeriodView.Builder builder = new PeriodView.Builder(this.mRefreshableManifestValidatorConfig, periodView);
        int i2 = 0;
        while (i2 < periodCount) {
            int availableStreamCount = this.mManifestJni.getAvailableStreamCount(this.mManifestHandle, i2);
            long periodHandle = this.mManifestJni.getPeriodHandle(this.mManifestHandle, i2);
            String periodIdString = this.mManifestJni.getPeriodIdString(periodHandle);
            int i3 = 0;
            while (true) {
                if (i3 >= availableStreamCount) {
                    z = false;
                    break;
                }
                if (this.mManifestJni.getStreamIndexType(this.mManifestJni.getAvailableStream(this.mManifestHandle, i2, i3)) == StreamType.SUBTITLES) {
                    z = true;
                    break;
                }
                i3++;
            }
            if (!z) {
                DLog.logf("cant find subtitle the streamNo : %d for periodid : %s", Integer.valueOf(i3), periodIdString);
            } else {
                DLog.logf("found the subtitle streamNo : %d for periodid : %s", Integer.valueOf(i3), periodIdString);
                long availableStream = this.mManifestJni.getAvailableStream(this.mManifestHandle, i2, i3);
                if (availableStream == 0) {
                    DLog.logf("cant find subtitle adaptations set periodIndex : %d, mIndex : %d, periodid: %s", Integer.valueOf(i2), Integer.valueOf(this.mIndex), periodIdString);
                } else {
                    i = i2;
                    DashStreamIndex dashStreamIndexCreateDashIndex = DashStreamIndex.createDashIndex(this.mManifestJni, this.mManifestHandle, periodHandle, availableStream, i3, true, this.mManifestTimescale, this.mDrmScheme);
                    this.mDashStreamIndices.add(dashStreamIndexCreateDashIndex);
                    int firstSegmentIndex = dashStreamIndexCreateDashIndex.getFirstSegmentIndex();
                    int lastSegmentIndex = dashStreamIndexCreateDashIndex.getLastSegmentIndex();
                    if (periodIdString == null) {
                        periodIdString = "";
                    }
                    builder.withPeriod(periodIdString, firstSegmentIndex, lastSegmentIndex, dashStreamIndexCreateDashIndex);
                    i2 = i + 1;
                }
            }
            i = i2;
            i2 = i + 1;
        }
        this.mPeriodView = builder.build();
        JSONArray jSONArray = new JSONArray();
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        this.mJsonRepresentation = new JSONObject((Map<?, ?>) Collections.singletonMap("StreamIndices", jSONArray));
    }

    static SubtitlesMultiPeriodStreamIndex createMultiPeriodDashIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, long j2, @Nonnull DrmScheme drmScheme) throws ContentException {
        SubtitlesMultiPeriodStreamIndex subtitlesMultiPeriodStreamIndex = new SubtitlesMultiPeriodStreamIndex(dashManifestJni, j, i, j2, drmScheme, RefreshableManifestValidator.RefreshableManifestValidatorConfig.getInstance());
        subtitlesMultiPeriodStreamIndex.initialize(PeriodView.createEmptyPeriod());
        return subtitlesMultiPeriodStreamIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex
    public void applyPeriodView(@Nonnull PeriodView periodView) throws ContentException {
        initialize((PeriodView) Preconditions.checkNotNull(periodView, "periodView"));
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex
    @Nonnull
    public PeriodView getPeriodView() {
        Preconditions.checkNotNull(this.mPeriodView, "mPeriodView");
        return this.mPeriodView;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel[] getSortedQualityLevels(int i) {
        if (this.mDashStreamIndices.size() == 0) {
            return BLANK_QUALITIES;
        }
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getSortedQualityLevels(pairLookupManifestIndexAndStream.first.intValue());
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getSortedQualityLevels(i);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, int i2) {
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getQualityLevel(pairLookupManifestIndexAndStream.first.intValue(), i2);
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getQualityLevel(i, i2);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, QualityLevel qualityLevel) {
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getQualityLevel(pairLookupManifestIndexAndStream.first.intValue(), qualityLevel);
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getQualityLevel(i, qualityLevel);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevelLessThanEqual(int i, int i2) {
        checkIfAtLeastOnePeriodHasSubtitleStream();
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getQualityLevelLessThanEqual(pairLookupManifestIndexAndStream.first.intValue(), i2);
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getQualityLevelLessThanEqual(i, i2);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevelGreaterThanEqual(int i, int i2) {
        checkIfAtLeastOnePeriodHasSubtitleStream();
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getQualityLevelGreaterThanEqual(pairLookupManifestIndexAndStream.first.intValue(), i2);
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getQualityLevelGreaterThanEqual(i, i2);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getClosestQualityLevel(int i, int i2) {
        checkIfAtLeastOnePeriodHasSubtitleStream();
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getClosestQualityLevel(pairLookupManifestIndexAndStream.first.intValue(), i2);
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getClosestQualityLevel(i, i2);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution) {
        checkIfAtLeastOnePeriodHasSubtitleStream();
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.getClosestQualityLevelWithResolutionCap(pairLookupManifestIndexAndStream.first.intValue(), i2, videoResolution);
        } catch (IndexOutOfBoundsException unused) {
            return this.mDashStreamIndices.get(0).getClosestQualityLevelWithResolutionCap(i, i2, videoResolution);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeInNanos(int i) {
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getChunkTimeInNanos(pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeOffsetInNanos(int i) {
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getChunkTimeOffsetInNanos(pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getChunkIndexFromNanos(long j) {
        try {
            return this.mPeriodView.lookupVirtualIndex(j);
        } catch (IndexOutOfBoundsException unused) {
            return this.mPeriodView.getLatestVirtualIndex() + 1;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkDurationInNanos(int i) {
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getChunkDurationInNanos(pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastPlayableChunk(int i) {
        return !this.mIsDynamic && this.mPeriodView.getLatestVirtualIndex() == i;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastDownloadableChunk(int i) {
        if (this.mIsDynamic) {
            try {
                this.mPeriodView.lookupManifestIndexAndStream(i + 1);
                return false;
            } catch (IndexOutOfBoundsException unused) {
                return true;
            }
        }
        return isLastPlayableChunk(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getInitUrl(String str, QualityLevel qualityLevel, int i) {
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getInitUrl(str, qualityLevel, pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public String getUrl(String str, QualityLevel qualityLevel, int i) {
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getUrl(str, qualityLevel, pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getInitHeaders(@Nonnull QualityLevel qualityLevel) {
        checkIfAtLeastOnePeriodHasSubtitleStream();
        return this.mDashStreamIndices.get(0).getInitHeaders(qualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getHeaders(@Nonnull QualityLevel qualityLevel, int i) {
        return Collections.emptyMap();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel) {
        return this.mDashStreamIndices.get(0).getExpectedInitChunkSizeInBytes(qualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i) {
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getExpectedChunkSizeInBytes(qualityLevel, pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i) {
        Preconditions.checkNotNull(qualityLevel, "quality");
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getChunkQualityScore(qualityLevel, pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public StreamType getType() {
        return StreamType.SUBTITLES;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamDurationInNanos() {
        return TimeSpan.MAX_VALUE.getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getLanguage() {
        return this.mDashStreamIndices.size() > 0 ? this.mDashStreamIndices.get(0).getLanguage() : "eng";
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isOutOfWindow(int i) {
        try {
            Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
            Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
            return pairLookupManifestIndexAndStream.second.isOutOfWindow(pairLookupManifestIndexAndStream.first.intValue());
        } catch (IndexOutOfBoundsException unused) {
            return true;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public AudioFormat getAudioFormat() {
        return AudioFormat.STEREO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getFourCC() {
        return this.mDashStreamIndices.size() > 0 ? this.mDashStreamIndices.get(0).getFourCC() : "sstp";
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public void release() {
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getEncodeTimeMillis(long j) {
        throw new UnsupportedOperationException("getEncodeTimeMillis is not supported for subtitle streams");
    }

    private void checkIfAtLeastOnePeriodHasSubtitleStream() {
        if (this.mDashStreamIndices.size() == 0) {
            throw new UnsupportedOperationException("There is no subtitle stream in any period!");
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public JSONObject toJson() {
        return this.mJsonRepresentation;
    }

    public String toString() {
        return this.mJsonRepresentation.toString();
    }
}
