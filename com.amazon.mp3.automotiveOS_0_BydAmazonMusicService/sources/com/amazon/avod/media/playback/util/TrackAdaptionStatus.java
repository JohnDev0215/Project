package com.amazon.avod.media.playback.util;

import com.amazon.avod.playback.PlaybackException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TrackAdaptionStatus {
    private long mBytesWritten;
    private long mDstBufferRemaining;
    private long mFragmentBufferSize;
    private long mNALSampleSize;

    @Nullable
    private PlaybackException.PlaybackError mPlaybackError;
    private long mSrcBufferRemaining;

    @Nullable
    public PlaybackException.PlaybackError getError() {
        return this.mPlaybackError;
    }

    public long getFragmentBufferSize() {
        return this.mFragmentBufferSize;
    }

    public long getSrcBufferRemaining() {
        return this.mSrcBufferRemaining;
    }

    public long getDstBufferRemaining() {
        return this.mDstBufferRemaining;
    }

    public long getBytesWritten() {
        return this.mBytesWritten;
    }

    public long getNALSampleSize() {
        return this.mNALSampleSize;
    }

    public TrackAdaptionStatus set(@Nullable PlaybackException.PlaybackError playbackError, long j, long j2, long j3, long j4, long j5) {
        this.mPlaybackError = playbackError;
        this.mBytesWritten = j;
        this.mFragmentBufferSize = j2;
        this.mSrcBufferRemaining = j3;
        this.mDstBufferRemaining = j4;
        this.mNALSampleSize = j5;
        return this;
    }
}
