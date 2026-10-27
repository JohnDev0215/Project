package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.content.ContentException;

/* JADX INFO: loaded from: classes2.dex */
class RubyManifestParserJni {
    native boolean[] getAudioQualityLevelBooleanMetadata(long j);

    native int[] getAudioQualityLevelIntMetaData(long j);

    native long[] getChunkMetaData(long j, int i);

    native int getDisplayHeight(long j);

    native int getDisplayWidth(long j);

    native String getLibraryVersion();

    native long[] getManifestMetaData(long j);

    native int getNumAvailableStreams(long j);

    native int getNumChunks(long j);

    native int getNumQualityLevels(long j);

    native long getProtectionHeaderHandle(long j);

    native String[] getProtectionHeaderMetaData(long j);

    native long getQualityLevelHandleAtIndex(long j, int i);

    native int[] getQualityLevelIntMetaData(long j);

    native String[] getQualityLevelStrMetaData(long j);

    native long getStreamIndexHandleAtIndex(long j, int i);

    native String[] getStreamIndexMetaData(long j);

    native int[] getVideoQualityLevelMetaData(long j);

    native long parseBufferModed(byte[] bArr, int i, int i2, boolean z) throws ContentException;

    native void release(long j);

    RubyManifestParserJni() {
    }
}
