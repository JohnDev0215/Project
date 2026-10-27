package com.amazon.avod.smoothstream.dash;

import androidx.core.util.Pair;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex;
import com.amazon.avod.content.smoothstream.manifest.MultiPeriodStreamIndexSynchronizer;
import com.amazon.avod.content.smoothstream.manifest.PeriodView;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifestValidator;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamIndexIdentity;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class MultiPeriodStreamIndex implements PeriodAwareStreamIndex {
    private int mAdjustedStreamIndex;
    private final List<DashStreamIndex> mDashStreamIndices = Lists.newLinkedList();
    private final String mDefaultAudioLanguage = SmoothStreamingPlaybackConfig.INSTANCE.getDefaultAudioLanguageForMultiPeriod();
    private double mDisplayAspectRatio;
    private final DrmScheme mDrmScheme;
    private long mFirstPeriodHandle;
    private String mFourCC;
    private final boolean mIsDynamic;
    private JSONObject mJsonRepresentation;
    private final long mManifestHandle;
    private final DashManifestJni mManifestJni;
    private final long mManifestTimescale;
    private float mMaxFrameRate;
    private int mMaxHeight;
    private int mMaxWidth;
    private PeriodView mPeriodView;
    private final int mRequestedStreamIndex;

    private MultiPeriodStreamIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, boolean z, long j2, @Nonnull DrmScheme drmScheme) {
        this.mManifestJni = (DashManifestJni) Preconditions.checkNotNull(dashManifestJni, "manifestJni");
        this.mManifestHandle = j;
        this.mRequestedStreamIndex = i;
        this.mIsDynamic = z;
        this.mManifestTimescale = j2;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
    }

    private void initialize(@Nonnull PeriodView periodView) throws ContentException {
        StreamIndexIdentity streamIndexId;
        PeriodView.Builder builder;
        int i;
        MultiPeriodStreamIndexSynchronizer streamIndexSynchronizer = periodView.getStreamIndexSynchronizer();
        this.mAdjustedStreamIndex = getAdjustedIndexIfMissingAudio(this.mRequestedStreamIndex, this.mManifestJni, this.mManifestHandle, this.mDefaultAudioLanguage);
        int i2 = 0;
        if (!streamIndexSynchronizer.containsIndex(this.mRequestedStreamIndex)) {
            long availableStream = this.mManifestJni.getAvailableStream(this.mManifestHandle, 0, this.mAdjustedStreamIndex);
            this.mFourCC = this.mManifestJni.getQualityLevelFourCC(this.mManifestJni.getStreamQualityLevel(availableStream, 0));
            streamIndexId = new StreamIndexIdentity(this.mManifestJni.getStreamIndexType(availableStream), this.mManifestJni.getStreamLanguage(availableStream), this.mFourCC);
            streamIndexSynchronizer.putIdentityAtIndex(streamIndexId, this.mRequestedStreamIndex);
        } else {
            streamIndexId = streamIndexSynchronizer.getStreamIndexId(this.mRequestedStreamIndex);
            this.mFourCC = streamIndexId.getFourCC();
        }
        int periodCount = this.mManifestJni.getPeriodCount(this.mManifestHandle);
        if (periodCount < 1) {
            throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, "period count can't be zero or negative");
        }
        PeriodView.Builder builder2 = new PeriodView.Builder(RefreshableManifestValidator.RefreshableManifestValidatorConfig.getInstance(), periodView);
        int i3 = 0;
        while (i3 < periodCount) {
            long periodHandle = this.mManifestJni.getPeriodHandle(this.mManifestHandle, i3);
            String periodIdString = this.mManifestJni.getPeriodIdString(periodHandle);
            int i4 = -1;
            int availableStreamCount = this.mManifestJni.getAvailableStreamCount(this.mManifestHandle, i3);
            int i5 = i2;
            while (i5 < availableStreamCount) {
                StreamIndexIdentity streamIndexIdentity = streamIndexId;
                long availableStream2 = this.mManifestJni.getAvailableStream(this.mManifestHandle, i3, i5);
                long streamQualityLevel = this.mManifestJni.getStreamQualityLevel(availableStream2, i2);
                StreamType streamIndexType = this.mManifestJni.getStreamIndexType(availableStream2);
                String streamLanguage = this.mManifestJni.getStreamLanguage(availableStream2);
                String qualityLevelFourCC = this.mManifestJni.getQualityLevelFourCC(streamQualityLevel);
                if (streamIndexType == streamIndexIdentity.getStreamType() && Objects.equal(streamLanguage, streamIndexIdentity.getLanguage()) && Objects.equal(qualityLevelFourCC, streamIndexIdentity.getFourCC())) {
                    i4 = i5;
                }
                i5++;
                streamIndexId = streamIndexIdentity;
                i2 = 0;
            }
            StreamIndexIdentity streamIndexIdentity2 = streamIndexId;
            if (i4 < 0) {
                throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, String.format(Locale.US, "Period: %s stream index not found for %s", periodIdString, streamIndexIdentity2));
            }
            long availableStream3 = this.mManifestJni.getAvailableStream(this.mManifestHandle, i3, i4);
            this.mDisplayAspectRatio = this.mManifestJni.getStreamDisplayAspectRatio(availableStream3);
            if (availableStream3 != 0) {
                builder = builder2;
                i = i3;
                DashStreamIndex dashStreamIndexCreateDashIndex = DashStreamIndex.createDashIndex(this.mManifestJni, this.mManifestHandle, periodHandle, availableStream3, i4, this.mIsDynamic, this.mManifestTimescale, this.mDrmScheme);
                this.mMaxFrameRate = Math.max(this.mMaxFrameRate, dashStreamIndexCreateDashIndex.getMaxFrameRate());
                this.mMaxWidth = Math.max(this.mMaxWidth, dashStreamIndexCreateDashIndex.getMaxWidth());
                this.mMaxHeight = Math.max(this.mMaxHeight, dashStreamIndexCreateDashIndex.getMaxHeight());
                this.mDashStreamIndices.add(dashStreamIndexCreateDashIndex);
                int firstSegmentIndex = dashStreamIndexCreateDashIndex.getFirstSegmentIndex();
                int lastSegmentIndex = dashStreamIndexCreateDashIndex.getLastSegmentIndex();
                if (periodIdString == null) {
                    periodIdString = "";
                }
                builder.withPeriod(periodIdString, firstSegmentIndex, lastSegmentIndex, dashStreamIndexCreateDashIndex);
            } else {
                builder = builder2;
                i = i3;
            }
            i3 = i + 1;
            builder2 = builder;
            streamIndexId = streamIndexIdentity2;
            i2 = 0;
        }
        this.mPeriodView = builder2.build();
        this.mFirstPeriodHandle = this.mDashStreamIndices.get(i2).getStreamHandle();
        JSONArray jSONArray = new JSONArray();
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        this.mJsonRepresentation = new JSONObject((Map<?, ?>) Collections.singletonMap("StreamIndices", jSONArray));
    }

    private int getAdjustedIndexIfMissingAudio(int i, DashManifestJni dashManifestJni, long j, String str) {
        if (dashManifestJni.getAvailableStream(j, 0, i) == 0) {
            i = -1;
            int availableStreamCount = dashManifestJni.getAvailableStreamCount(j, 0);
            for (int i2 = 0; i2 < availableStreamCount; i2++) {
                long availableStream = dashManifestJni.getAvailableStream(j, 0, i2);
                StreamType streamIndexType = dashManifestJni.getStreamIndexType(availableStream);
                String streamLanguage = dashManifestJni.getStreamLanguage(availableStream);
                if ("AUDIO".equalsIgnoreCase(String.valueOf(streamIndexType)) && str.equalsIgnoreCase(String.valueOf(streamLanguage))) {
                    i = i2;
                }
            }
        }
        return i;
    }

    static MultiPeriodStreamIndex createMultiPeriodDashIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, boolean z, long j2, @Nonnull DrmScheme drmScheme) throws ContentException {
        MultiPeriodStreamIndex multiPeriodStreamIndex = new MultiPeriodStreamIndex(dashManifestJni, j, i, z, j2, drmScheme);
        multiPeriodStreamIndex.initialize(PeriodView.createEmptyPeriod());
        return multiPeriodStreamIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex
    public void applyPeriodView(@Nonnull PeriodView periodView) throws ContentException {
        Preconditions.checkNotNull(periodView, "periodView");
        initialize(periodView);
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex
    @Nonnull
    public PeriodView getPeriodView() {
        Preconditions.checkNotNull(this.mPeriodView, "mPeriodView");
        return this.mPeriodView;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getIndex() {
        return this.mRequestedStreamIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isAudio() {
        return this.mDashStreamIndices.get(0).isAudio();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isVideo() {
        return this.mDashStreamIndices.get(0).isVideo();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel[] getSortedQualityLevels(int i) {
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
        return this.mPeriodView.lookupVirtualIndex(j);
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

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getNumChunks() {
        if (this.mIsDynamic) {
            return Integer.MAX_VALUE;
        }
        int numChunks = 0;
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        while (it.hasNext()) {
            numChunks += it.next().getNumChunks();
        }
        return numChunks;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean requiresInitFragments() {
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        while (it.hasNext()) {
            if (it.next().requiresInitFragments()) {
                return true;
            }
        }
        return false;
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
        return this.mDashStreamIndices.get(0).getInitHeaders(qualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getHeaders(@Nonnull QualityLevel qualityLevel, int i) {
        if (this.mIsDynamic) {
            return Collections.emptyMap();
        }
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getHeaders(qualityLevel, pairLookupManifestIndexAndStream.first.intValue());
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
        Pair<Integer, StreamIndex> pairLookupManifestIndexAndStream = this.mPeriodView.lookupManifestIndexAndStream(i);
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.first, "manifestIndex");
        Preconditions.checkNotNull(pairLookupManifestIndexAndStream.second, "streamIndex");
        return pairLookupManifestIndexAndStream.second.getChunkQualityScore(qualityLevel, pairLookupManifestIndexAndStream.first.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public StreamType getType() {
        return this.mDashStreamIndices.get(0).getType();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamDurationInNanos() {
        if (this.mIsDynamic) {
            return TimeSpan.MAX_VALUE.getTotalNanoSeconds();
        }
        return getStreamEndTimestampNanos() - getStreamStartTimestampNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getLanguage() {
        long availableStream = 0;
        for (int i = 0; i < this.mManifestJni.getPeriodCount(this.mManifestHandle); i++) {
            availableStream = this.mManifestJni.getAvailableStream(this.mManifestHandle, i, this.mRequestedStreamIndex);
            if (availableStream != 0) {
                break;
            }
        }
        Preconditions.checkArgument(availableStream != 0, "no language found");
        return this.mManifestJni.getStreamLanguage(availableStream);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getAudioTrackId() {
        return this.mManifestJni.getStreamAudioTrackId(this.mFirstPeriodHandle);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamHandle() {
        return this.mFirstPeriodHandle;
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

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getDisplayAspectRatio() {
        return this.mDisplayAspectRatio;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isHdr() {
        return this.mDashStreamIndices.get(0).isHdr();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public AudioFormat getAudioFormat() {
        return this.mDashStreamIndices.get(0).getAudioFormat();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getFourCC() {
        return this.mFourCC;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public byte[] getDefaultKeyId() {
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        byte[] defaultKeyId = null;
        while (it.hasNext() && (defaultKeyId = it.next().getDefaultKeyId()) == null) {
        }
        return defaultKeyId;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public void release() {
        Iterator<DashStreamIndex> it = this.mDashStreamIndices.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamEndTimestampNanos() {
        int latestVirtualIndex = this.mPeriodView.getLatestVirtualIndex();
        return getChunkTimeInNanos(latestVirtualIndex) + getChunkDurationInNanos(latestVirtualIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamStartTimestampNanos() {
        return this.mDashStreamIndices.get(0).getStreamStartTimestampNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getEncodeTimeMillis(long j) {
        return this.mPeriodView.convertMediaTimeToEncodeTimeMillis(j);
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public float getMaxFrameRate() {
        return this.mMaxFrameRate;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public JSONObject toJson() {
        return this.mJsonRepresentation;
    }

    public String toString() {
        return this.mJsonRepresentation.toString();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public String getPeriodIdWithTimestampNanos(long j) {
        return this.mPeriodView.lookupPeriodIdWithTimestamp(j);
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override // com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxHeight() {
        return this.mMaxHeight;
    }
}
