package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.Chunk;

/* JADX INFO: loaded from: classes2.dex */
class RubyChunk implements Chunk {
    private final long mDuration;
    private final long mTimeStamp;

    RubyChunk(long j, long j2) {
        this.mTimeStamp = j;
        this.mDuration = j2;
    }

    static RubyChunk newChunkFromHandle(long j, int i, RubyManifestParserJni rubyManifestParserJni) {
        long[] chunkMetaData = rubyManifestParserJni.getChunkMetaData(j, i);
        return new RubyChunk(chunkMetaData[0], chunkMetaData[1]);
    }

    @Override // com.amazon.avod.playback.smoothstream.Chunk
    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    @Override // com.amazon.avod.playback.smoothstream.Chunk
    public long getDuration() {
        return this.mDuration;
    }
}
