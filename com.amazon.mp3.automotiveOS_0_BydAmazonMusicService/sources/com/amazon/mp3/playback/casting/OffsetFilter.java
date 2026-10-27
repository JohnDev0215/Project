package com.amazon.mp3.playback.casting;

import com.amazon.music.media.playback.util.Clock;

/* JADX INFO: loaded from: classes3.dex */
public class OffsetFilter {
    private long mBest;
    private final Clock mClock;
    private long mLastTime;
    private int mNextIndex;
    private int mNumNegative;
    private long mNumSamples;
    private final long[] mOffsets = new long[8];

    public OffsetFilter(final Clock clock) {
        this.mClock = clock;
    }

    public void addTimeSample(final long time) {
        if (time == this.mLastTime) {
            return;
        }
        long j = this.mNumSamples;
        if (j < 8) {
            this.mNumSamples = j + 1;
        }
        this.mLastTime = time;
        long jNow = Clock.now() - time;
        long[] jArr = this.mOffsets;
        int i = this.mNextIndex;
        long j2 = jArr[i];
        jArr[i] = jNow;
        if (j2 < 0) {
            this.mNumNegative--;
        }
        if (jNow < 0) {
            this.mNumNegative++;
        }
        int i2 = this.mNumNegative;
        if (i2 > 0 && i2 < 8) {
            this.mBest = 0L;
        } else if (Math.abs(jNow) < Math.abs(this.mBest)) {
            this.mBest = jNow;
        } else {
            long j3 = this.mBest;
            if (j3 == 0 || j3 == j2) {
                this.mBest = this.mOffsets[0];
                for (int i3 = 1; i3 < this.mNumSamples; i3++) {
                    if (Math.abs(this.mOffsets[i3]) < Math.abs(this.mBest)) {
                        this.mBest = this.mOffsets[i3];
                    }
                }
            }
        }
        this.mNextIndex = (this.mNextIndex + 1) % 8;
    }

    public long getTime(final long time) {
        return time + this.mBest;
    }
}
