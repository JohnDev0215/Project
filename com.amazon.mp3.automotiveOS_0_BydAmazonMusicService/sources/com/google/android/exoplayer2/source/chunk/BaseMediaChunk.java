package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseMediaChunk extends MediaChunk {
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;

    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, int i2) {
        super(dataSource, dataSpec, format, i, obj, j, j2, i2);
    }

    public void init(BaseMediaChunkOutput baseMediaChunkOutput) {
        this.output = baseMediaChunkOutput;
        this.firstSampleIndices = baseMediaChunkOutput.getWriteIndices();
    }

    public final int getFirstSampleIndex(int i) {
        return this.firstSampleIndices[i];
    }

    protected final BaseMediaChunkOutput getOutput() {
        return this.output;
    }
}
