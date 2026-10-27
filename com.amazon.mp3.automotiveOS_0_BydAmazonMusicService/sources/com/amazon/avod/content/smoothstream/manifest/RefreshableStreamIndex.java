package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.PeriodAwareStreamIndex;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class RefreshableStreamIndex implements StreamIndex {
    protected StreamIndex mLatestStreamIndex;
    private final ReadWriteLock mLock = new ReentrantReadWriteLock();

    public RefreshableStreamIndex(@Nonnull StreamIndex streamIndex) {
        this.mLatestStreamIndex = (StreamIndex) Preconditions.checkNotNull(streamIndex, "originalStreamIndex");
    }

    public void refresh(@Nonnull StreamIndex streamIndex) throws ContentException {
        this.mLock.writeLock().lock();
        try {
            Preconditions.checkNotNull(streamIndex, "streamIndex");
            if (streamIndex instanceof PeriodAwareStreamIndex) {
                StreamIndex streamIndex2 = this.mLatestStreamIndex;
                if (streamIndex2 instanceof PeriodAwareStreamIndex) {
                    ((PeriodAwareStreamIndex) streamIndex).applyPeriodView(((PeriodAwareStreamIndex) streamIndex2).getPeriodView());
                }
            }
            this.mLatestStreamIndex = streamIndex;
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getIndex() {
        readLock();
        try {
            return this.mLatestStreamIndex.getIndex();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isAudio() {
        readLock();
        try {
            return this.mLatestStreamIndex.isAudio();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isVideo() {
        readLock();
        try {
            return this.mLatestStreamIndex.isVideo();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel[] getSortedQualityLevels(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getSortedQualityLevels(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, int i2) {
        readLock();
        try {
            return this.mLatestStreamIndex.getQualityLevel(i, i2);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, QualityLevel qualityLevel) {
        readLock();
        try {
            return this.mLatestStreamIndex.getQualityLevel(i, qualityLevel);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevelLessThanEqual(int i, int i2) {
        readLock();
        try {
            return this.mLatestStreamIndex.getQualityLevelLessThanEqual(i, i2);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevelGreaterThanEqual(int i, int i2) {
        readLock();
        try {
            return this.mLatestStreamIndex.getQualityLevelGreaterThanEqual(i, i2);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getClosestQualityLevel(int i, int i2) {
        readLock();
        try {
            return this.mLatestStreamIndex.getClosestQualityLevel(i, i2);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution) {
        readLock();
        try {
            return this.mLatestStreamIndex.getClosestQualityLevelWithResolutionCap(i, i2, videoResolution);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeInNanos(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getChunkTimeInNanos(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeOffsetInNanos(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getChunkTimeOffsetInNanos(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getChunkIndexFromNanos(long j) {
        readLock();
        try {
            return this.mLatestStreamIndex.getChunkIndexFromNanos(j);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkDurationInNanos(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getChunkDurationInNanos(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastPlayableChunk(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.isLastPlayableChunk(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastDownloadableChunk(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.isLastDownloadableChunk(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getNumChunks() {
        readLock();
        try {
            return this.mLatestStreamIndex.getNumChunks();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean requiresInitFragments() {
        readLock();
        try {
            return this.mLatestStreamIndex.requiresInitFragments();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getInitUrl(String str, QualityLevel qualityLevel, int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getInitUrl(str, qualityLevel, i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getUrl(String str, QualityLevel qualityLevel, int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getUrl(str, qualityLevel, i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getInitHeaders(@Nonnull QualityLevel qualityLevel) {
        readLock();
        try {
            return this.mLatestStreamIndex.getInitHeaders(qualityLevel);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getHeaders(@Nonnull QualityLevel qualityLevel, int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getHeaders(qualityLevel, i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel) {
        readLock();
        try {
            return this.mLatestStreamIndex.getExpectedInitChunkSizeInBytes(qualityLevel);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getExpectedChunkSizeInBytes(qualityLevel, i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.getChunkQualityScore(qualityLevel, i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public StreamType getType() {
        readLock();
        try {
            return this.mLatestStreamIndex.getType();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamDurationInNanos() {
        readLock();
        try {
            return this.mLatestStreamIndex.getStreamDurationInNanos();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getLanguage() {
        readLock();
        try {
            return this.mLatestStreamIndex.getLanguage();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getAudioTrackId() {
        readLock();
        try {
            return this.mLatestStreamIndex.getAudioTrackId();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamHandle() {
        readLock();
        try {
            return this.mLatestStreamIndex.getStreamHandle();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isOutOfWindow(int i) {
        readLock();
        try {
            return this.mLatestStreamIndex.isOutOfWindow(i);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getDisplayAspectRatio() {
        readLock();
        try {
            return this.mLatestStreamIndex.getDisplayAspectRatio();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isHdr() {
        readLock();
        try {
            return this.mLatestStreamIndex.isHdr();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public AudioFormat getAudioFormat() {
        readLock();
        try {
            return this.mLatestStreamIndex.getAudioFormat();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getFourCC() {
        readLock();
        try {
            return this.mLatestStreamIndex.getFourCC();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public byte[] getDefaultKeyId() {
        readLock();
        try {
            return this.mLatestStreamIndex.getDefaultKeyId();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public void release() {
        this.mLatestStreamIndex.release();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamEndTimestampNanos() {
        readLock();
        try {
            return this.mLatestStreamIndex.getStreamEndTimestampNanos();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamStartTimestampNanos() {
        readLock();
        try {
            return this.mLatestStreamIndex.getStreamStartTimestampNanos();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getEncodeTimeMillis(long j) {
        readLock();
        try {
            return this.mLatestStreamIndex.getEncodeTimeMillis(j);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public float getMaxFrameRate() {
        readLock();
        try {
            return this.mLatestStreamIndex.getMaxFrameRate();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxWidth() {
        readLock();
        try {
            return this.mLatestStreamIndex.getMaxWidth();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxHeight() {
        readLock();
        try {
            return this.mLatestStreamIndex.getMaxHeight();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public JSONObject toJson() {
        readLock();
        try {
            return this.mLatestStreamIndex.toJson();
        } finally {
            unlock();
        }
    }

    public String toString() {
        return toJson().toString();
    }

    protected void readLock() {
        this.mLock.readLock().lock();
    }

    protected void unlock() {
        this.mLock.readLock().unlock();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public String getPeriodIdWithTimestampNanos(long j) {
        readLock();
        try {
            return this.mLatestStreamIndex.getPeriodIdWithTimestampNanos(j);
        } finally {
            unlock();
        }
    }
}
