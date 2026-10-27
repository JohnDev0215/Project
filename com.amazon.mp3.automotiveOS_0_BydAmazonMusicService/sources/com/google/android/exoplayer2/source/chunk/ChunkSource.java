package com.google.android.exoplayer2.source.chunk;

import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public interface ChunkSource {
    void getNextChunk(MediaChunk mediaChunk, long j, long j2, ChunkHolder chunkHolder);

    void maybeThrowError() throws IOException;

    void onChunkLoadCompleted(Chunk chunk);

    boolean onChunkLoadError(Chunk chunk, boolean z, Exception exc);
}
