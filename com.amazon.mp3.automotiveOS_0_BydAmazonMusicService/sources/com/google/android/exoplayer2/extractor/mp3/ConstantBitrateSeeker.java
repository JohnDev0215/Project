package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: loaded from: classes6.dex */
final class ConstantBitrateSeeker implements Mp3Extractor.Seeker {
    private final int bitrate;
    private final long durationUs;
    private final long firstFramePosition;

    public ConstantBitrateSeeker(long j, int i, long j2) {
        this.firstFramePosition = j;
        this.bitrate = i;
        this.durationUs = j2 == -1 ? -9223372036854775807L : getTimeUs(j2);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.durationUs != -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getPosition(long j) {
        long j2 = this.durationUs;
        if (j2 == -9223372036854775807L) {
            return 0L;
        }
        return this.firstFramePosition + ((Util.constrainValue(j, 0L, j2) * ((long) this.bitrate)) / 8000000);
    }

    @Override // com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j) {
        return ((Math.max(0L, j - this.firstFramePosition) * 1000000) * 8) / ((long) this.bitrate);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }
}
