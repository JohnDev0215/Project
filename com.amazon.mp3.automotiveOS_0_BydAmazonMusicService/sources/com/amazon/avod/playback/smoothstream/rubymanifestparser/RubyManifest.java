package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.Manifest;
import com.amazon.avod.playback.smoothstream.ProtectionHeader;
import com.amazon.avod.playback.smoothstream.StreamIndex;

/* JADX INFO: loaded from: classes2.dex */
class RubyManifest implements Manifest {
    private final long mDuration;
    private final RubyManifestParserJni mJni;
    private final int mMajorVersion;
    private final long mManifestHandle;
    private final int mMinorVersion;
    private final long mTimeScale;
    private StreamIndex[] mStreamIndices = null;
    private ProtectionHeader mProtectionHeader = null;

    RubyManifest(long j, RubyManifestParserJni rubyManifestParserJni) {
        this.mManifestHandle = j;
        this.mJni = rubyManifestParserJni;
        long[] manifestMetaData = rubyManifestParserJni.getManifestMetaData(j);
        this.mMajorVersion = (int) manifestMetaData[0];
        this.mMinorVersion = (int) manifestMetaData[1];
        this.mDuration = manifestMetaData[2];
        this.mTimeScale = manifestMetaData[3];
    }

    @Override // com.amazon.avod.playback.smoothstream.Manifest
    public int getMajorVersion() {
        return this.mMajorVersion;
    }

    @Override // com.amazon.avod.playback.smoothstream.Manifest
    public int getMinorVersion() {
        return this.mMinorVersion;
    }

    @Override // com.amazon.avod.playback.smoothstream.Manifest
    public long getDuration() {
        return this.mDuration;
    }

    @Override // com.amazon.avod.playback.smoothstream.Manifest
    public StreamIndex[] getAvailableStreams() {
        if (this.mStreamIndices == null) {
            int numAvailableStreams = this.mJni.getNumAvailableStreams(this.mManifestHandle);
            this.mStreamIndices = new StreamIndex[numAvailableStreams];
            for (int i = 0; i < numAvailableStreams; i++) {
                this.mStreamIndices[i] = new RubyStreamIndex(this.mJni.getStreamIndexHandleAtIndex(this.mManifestHandle, i), this.mJni, this.mTimeScale);
            }
        }
        return this.mStreamIndices;
    }

    @Override // com.amazon.avod.playback.smoothstream.Manifest
    public ProtectionHeader getProtectionHeader() {
        if (this.mProtectionHeader == null) {
            this.mProtectionHeader = RubyProtectionHeader.newProtectionHeaderFromHandle(this.mJni.getProtectionHeaderHandle(this.mManifestHandle), this.mJni);
        }
        return this.mProtectionHeader;
    }

    @Override // com.amazon.avod.playback.smoothstream.Manifest
    public long getTimeScale() {
        return this.mTimeScale;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.mJni.release(this.mManifestHandle);
    }
}
