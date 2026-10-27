package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Maps;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingURI {
    private String mCachedAbsoluteUrl;
    private Map<String, String> mCachedDownloadHeaders;
    private long mCachedDurationNanos;
    private int mCachedHashCode;
    private SmoothStreamingURI mCachedInitFragmentURI;
    private SmoothStreamingURI mCachedNextURI;
    private long mCachedPresentationTimeNanos;
    private long mCachedPresentationTimeOffsetNanos;
    private String mCachedToString;
    private final int mChunkIndex;
    private final long mDownloadChunkSize;
    private final FragmentType mFragmentType;
    private boolean mIsDurationNanosCached;
    private boolean mIsHashCodeCached;
    private boolean mIsPresentationTimeNanosCached;
    private boolean mIsPresentationTimeOffsetNanosCached;
    private final boolean mIsPsshFragment;

    @Nullable
    private final QualityLevel mQuality;
    private final double mQualityScore;
    private final int mRelatedChunkIndex;
    private final StreamIndex mStream;

    public SmoothStreamingURI(StreamIndex streamIndex, @Nullable QualityLevel qualityLevel, int i, int i2, boolean z, FragmentType fragmentType) {
        long expectedChunkSizeInBytes;
        this.mStream = streamIndex;
        this.mChunkIndex = i;
        this.mRelatedChunkIndex = i2;
        this.mIsPsshFragment = z;
        this.mFragmentType = fragmentType;
        if (i != -1 && qualityLevel != null) {
            this.mQuality = streamIndex.getQualityLevel(i, qualityLevel);
        } else {
            this.mQuality = qualityLevel;
        }
        if (this.mQuality == null) {
            expectedChunkSizeInBytes = -1;
        } else if (isInitFragment()) {
            expectedChunkSizeInBytes = streamIndex.getExpectedInitChunkSizeInBytes(this.mQuality);
        } else {
            expectedChunkSizeInBytes = streamIndex.getExpectedChunkSizeInBytes(this.mQuality, i);
        }
        this.mDownloadChunkSize = expectedChunkSizeInBytes;
        this.mQualityScore = (this.mQuality == null || isInitFragment()) ? 0.0d : streamIndex.getChunkQualityScore(qualityLevel, i);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, QualityLevel qualityLevel, int i, boolean z, FragmentType fragmentType) {
        this(streamIndex, qualityLevel, i, i, z, fragmentType);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, QualityLevel qualityLevel, int i, boolean z) {
        this(streamIndex, qualityLevel, i, i, z, FragmentType.DEFAULT);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, QualityLevel qualityLevel, int i, int i2, boolean z) {
        this(streamIndex, qualityLevel, i, i2, z, FragmentType.DEFAULT);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, QualityLevel qualityLevel, int i) {
        this(streamIndex, qualityLevel, i, false);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, QualityLevel qualityLevel, int i, int i2) {
        this(streamIndex, qualityLevel, i, i2, false, FragmentType.DEFAULT);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, int i, boolean z) {
        this(streamIndex, (QualityLevel) null, i, z);
    }

    public SmoothStreamingURI(StreamIndex streamIndex, int i) {
        this(streamIndex, i, false);
    }

    public long getPresentationTimeInNanos() {
        if (isInitFragment()) {
            return 0L;
        }
        if (!this.mIsPresentationTimeNanosCached) {
            this.mIsPresentationTimeNanosCached = true;
            this.mCachedPresentationTimeNanos = this.mStream.getChunkTimeInNanos(getChunkIndex());
        }
        return this.mCachedPresentationTimeNanos;
    }

    public long getPresentationTimeOffsetInNanos() {
        if (isInitFragment()) {
            return 0L;
        }
        if (!this.mIsPresentationTimeOffsetNanosCached) {
            this.mIsPresentationTimeOffsetNanosCached = true;
            this.mCachedPresentationTimeOffsetNanos = this.mStream.getChunkTimeOffsetInNanos(getChunkIndex());
        }
        return this.mCachedPresentationTimeOffsetNanos;
    }

    public SmoothStreamingURI getNextURI() {
        if (this.mCachedNextURI == null) {
            if (isLastInStream()) {
                this.mCachedNextURI = null;
            } else {
                this.mCachedNextURI = new SmoothStreamingURI(getStream(), getQualityLevel(), getChunkIndex() + 1);
            }
        }
        return this.mCachedNextURI;
    }

    public SmoothStreamingURI getInitFragmentUri() {
        if (this.mCachedInitFragmentURI == null) {
            this.mCachedInitFragmentURI = new SmoothStreamingURI(getStream(), getQualityLevel(), -1, getChunkIndex());
        }
        return this.mCachedInitFragmentURI;
    }

    public boolean isLastInStream() {
        if (isInitFragment()) {
            return false;
        }
        return getStream().isLastPlayableChunk(getChunkIndex());
    }

    public SmoothStreamingURI getSmoothStreamingUriForQuality(QualityLevel qualityLevel) {
        return new SmoothStreamingURI(getStream(), qualityLevel, getChunkIndex());
    }

    public long getDurationInNanos() {
        if (isInitFragment()) {
            return 0L;
        }
        if (!this.mIsDurationNanosCached) {
            this.mIsDurationNanosCached = true;
            this.mCachedDurationNanos = this.mStream.getChunkDurationInNanos(getChunkIndex());
        }
        return this.mCachedDurationNanos;
    }

    public boolean isAudio() {
        return this.mStream.isAudio();
    }

    public boolean isVideo() {
        return this.mStream.isVideo();
    }

    public StreamIndex getStream() {
        return this.mStream;
    }

    @Nullable
    public QualityLevel getQualityLevel() {
        return this.mQuality;
    }

    public int getChunkIndex() {
        return this.mChunkIndex;
    }

    public boolean isInitFragment() {
        return this.mChunkIndex == -1;
    }

    @Nullable
    public String getAbsoluteUrl(@Nullable String str) {
        if (this.mCachedAbsoluteUrl == null) {
            if (this.mQuality == null) {
                return null;
            }
            try {
                if (isInitFragment()) {
                    this.mCachedAbsoluteUrl = this.mStream.getInitUrl(str, this.mQuality, this.mRelatedChunkIndex);
                } else {
                    this.mCachedAbsoluteUrl = this.mStream.getUrl(str, this.mQuality, this.mChunkIndex);
                }
            } catch (IndexOutOfBoundsException e) {
                DLog.exceptionf(e, "Got IOO while retrieving absolute url. falling back to cached url", new Object[0]);
            }
        }
        return this.mCachedAbsoluteUrl;
    }

    @Nonnull
    public Map<String, String> getDownloadHeaders() {
        if (this.mCachedDownloadHeaders == null) {
            if (this.mQuality == null) {
                this.mCachedDownloadHeaders = Maps.newHashMap();
            } else if (isInitFragment()) {
                this.mCachedDownloadHeaders = this.mStream.getInitHeaders(this.mQuality);
            } else {
                this.mCachedDownloadHeaders = this.mStream.getHeaders(this.mQuality, this.mChunkIndex);
            }
        }
        return this.mCachedDownloadHeaders;
    }

    public long getDownloadChunkSize() {
        return this.mDownloadChunkSize;
    }

    public FragmentType getFragmentType() {
        return this.mFragmentType;
    }

    public String toString() {
        if (this.mCachedToString == null) {
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = isInitFragment() ? "INIT" : Integer.valueOf(this.mChunkIndex);
            objArr[1] = this.mStream;
            objArr[2] = this.mQuality;
            this.mCachedToString = String.format(locale, "(%s - %s - %s)", objArr);
        }
        return this.mCachedToString;
    }

    public boolean equals(Object obj) {
        QualityLevel qualityLevel;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SmoothStreamingURI smoothStreamingURI = (SmoothStreamingURI) obj;
        if (smoothStreamingURI.getChunkIndex() == getChunkIndex() && smoothStreamingURI.mStream.getIndex() == this.mStream.getIndex()) {
            if (smoothStreamingURI.getBitrate() != getBitrate() || (qualityLevel = this.mQuality) == null) {
                if (this.mQuality == smoothStreamingURI.mQuality) {
                    return true;
                }
            } else if (qualityLevel.equals(smoothStreamingURI.mQuality)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (!this.mIsHashCodeCached) {
            this.mIsHashCodeCached = true;
            int iHashCode = ((((Integer.valueOf(getChunkIndex()).hashCode() + 31) * 31) + Integer.valueOf(this.mStream.getIndex()).hashCode()) * 31) + Integer.valueOf(getBitrate()).hashCode();
            QualityLevel qualityLevel = this.mQuality;
            if (qualityLevel != null) {
                iHashCode = (iHashCode * 31) + qualityLevel.hashCode();
            }
            this.mCachedHashCode = iHashCode;
        }
        return this.mCachedHashCode;
    }

    private int getBitrate() {
        QualityLevel qualityLevel = this.mQuality;
        if (qualityLevel == null) {
            return 0;
        }
        return qualityLevel.getBitrate();
    }

    public boolean isPsshFragment() {
        return this.mIsPsshFragment;
    }

    public boolean isExpired() {
        return this.mStream.isOutOfWindow(this.mChunkIndex);
    }

    public double getQualityScore() {
        return this.mQualityScore;
    }
}
