package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StreamingBitrateSelectionState {
    private int mBandwidth;
    private long mBufferedContentInNanoseconds;
    private int mChunkIndex;
    private FragmentType mFragmentType;
    private QualityLevel mPreviousQuality;
    private StreamIndex mStream;
    private StreamingState mStreamState;
    private int mTryCount;

    public void set(@Nonnull StreamIndex streamIndex, long j, int i, @Nullable QualityLevel qualityLevel, int i2, int i3, @Nullable StreamingState streamingState, @Nonnull FragmentType fragmentType) {
        Preconditions.checkNotNull(streamIndex, "stream");
        this.mStream = (StreamIndex) Preconditions.checkNotNull(streamIndex, "stream");
        this.mBufferedContentInNanoseconds = j;
        this.mBandwidth = i;
        this.mPreviousQuality = qualityLevel;
        this.mChunkIndex = i2;
        this.mTryCount = i3;
        this.mStreamState = streamingState;
        this.mFragmentType = (FragmentType) Preconditions.checkNotNull(fragmentType, "fragmentType");
    }

    public void set(StreamIndex streamIndex, int i, FragmentType fragmentType) {
        set(streamIndex, 0L, 0, null, i, 0, null, fragmentType);
    }

    @Nonnull
    public StreamIndex getStream() {
        return this.mStream;
    }

    public long getBufferedContentInNanos() {
        return this.mBufferedContentInNanoseconds;
    }

    public int getBandwidth() {
        return this.mBandwidth;
    }

    @Nullable
    public QualityLevel getPreviousQuality() {
        return this.mPreviousQuality;
    }

    public int getChunkIndex() {
        return this.mChunkIndex;
    }

    public int getTryCount() {
        return this.mTryCount;
    }

    @Nonnull
    public FragmentType getFragmentType() {
        return this.mFragmentType;
    }

    @Nullable
    public StreamingState getStreamingState() {
        return this.mStreamState;
    }
}
