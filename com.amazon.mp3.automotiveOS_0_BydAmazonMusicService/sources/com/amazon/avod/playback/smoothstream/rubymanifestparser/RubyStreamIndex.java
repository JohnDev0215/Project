package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.Chunk;
import com.amazon.avod.playback.smoothstream.QualityLevel;
import com.amazon.avod.playback.smoothstream.StreamIndex;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes2.dex */
class RubyStreamIndex implements StreamIndex {
    private final RubyManifestParserJni mJni;
    private final String mLanguage;
    private final String mName;
    private final long mStreamHandle;
    private final long mTimeScale;
    private final String mType;
    private final String mUrl;
    private Chunk[] mChunks = null;
    private QualityLevel[] mQualityLevels = null;

    RubyStreamIndex(long j, RubyManifestParserJni rubyManifestParserJni, long j2) {
        this.mStreamHandle = j;
        this.mJni = rubyManifestParserJni;
        String[] streamIndexMetaData = rubyManifestParserJni.getStreamIndexMetaData(j);
        this.mLanguage = streamIndexMetaData[0];
        this.mName = streamIndexMetaData[1];
        this.mUrl = streamIndexMetaData[2];
        this.mType = streamIndexMetaData[3];
        this.mTimeScale = j2;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public String getLanguage() {
        return this.mLanguage;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public String getUrl() {
        return this.mUrl;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public String getType() {
        return this.mType;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public Chunk[] getChunks() {
        if (this.mChunks == null) {
            int numChunks = this.mJni.getNumChunks(this.mStreamHandle);
            this.mChunks = new Chunk[numChunks];
            for (int i = 0; i < numChunks; i++) {
                this.mChunks[i] = RubyChunk.newChunkFromHandle(this.mStreamHandle, i, this.mJni);
            }
        }
        return this.mChunks;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public QualityLevel[] getQualityLevels() {
        if (this.mQualityLevels == null) {
            int numQualityLevels = this.mJni.getNumQualityLevels(this.mStreamHandle);
            this.mQualityLevels = new QualityLevel[numQualityLevels];
            for (int i = 0; i < numQualityLevels; i++) {
                long qualityLevelHandleAtIndex = this.mJni.getQualityLevelHandleAtIndex(this.mStreamHandle, i);
                if (this.mType.equalsIgnoreCase(voMimeTypes.VOBASE_TYPE_AUDIO)) {
                    this.mQualityLevels[i] = RubyAudioQualityLevel.newQualityLevelFromHandle(qualityLevelHandleAtIndex, this.mJni);
                } else if (this.mType.equalsIgnoreCase(voMimeTypes.VOBASE_TYPE_VIDEO)) {
                    this.mQualityLevels[i] = RubyVideoQualityLevel.newQualityLevelFromHandle(qualityLevelHandleAtIndex, this.mJni);
                }
            }
        }
        return this.mQualityLevels;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public long getTimeScale() {
        return this.mTimeScale;
    }

    @Override // com.amazon.avod.playback.smoothstream.StreamIndex
    public double getDisplayAspectRatio() {
        int displayWidth = this.mJni.getDisplayWidth(this.mStreamHandle);
        int displayHeight = this.mJni.getDisplayHeight(this.mStreamHandle);
        if (displayWidth == 0 || displayHeight == 0) {
            return -1.0d;
        }
        return ((double) displayWidth) / ((double) displayHeight);
    }
}
