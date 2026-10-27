package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

/* JADX INFO: loaded from: classes6.dex */
public final class SubtitleInputBuffer extends DecoderInputBuffer implements Comparable<SubtitleInputBuffer> {
    public long subsampleOffsetUs;

    public SubtitleInputBuffer() {
        super(1);
    }

    @Override // java.lang.Comparable
    public int compareTo(SubtitleInputBuffer subtitleInputBuffer) {
        if (isEndOfStream() != subtitleInputBuffer.isEndOfStream()) {
            return isEndOfStream() ? 1 : -1;
        }
        long j = this.timeUs - subtitleInputBuffer.timeUs;
        if (j == 0) {
            return 0;
        }
        return j > 0 ? 1 : -1;
    }
}
