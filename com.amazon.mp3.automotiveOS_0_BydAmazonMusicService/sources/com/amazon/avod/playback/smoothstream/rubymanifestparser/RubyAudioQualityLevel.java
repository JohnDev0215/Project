package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.AudioQualityLevel;

/* JADX INFO: loaded from: classes2.dex */
class RubyAudioQualityLevel extends RubyQualityLevel implements AudioQualityLevel {
    private final int mAudioTag;
    private final int mBitsPerSample;
    private final boolean mIsAtmos;
    private final int mNumChannels;
    private final int mPackageSize;
    private final int mSampleRate;

    RubyAudioQualityLevel(int i, int i2, String str, String str2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        super(i, i2, str, str2, i3);
        this.mAudioTag = i4;
        this.mNumChannels = i5;
        this.mSampleRate = i6;
        this.mBitsPerSample = i7;
        this.mPackageSize = i8;
        this.mIsAtmos = z;
    }

    static RubyAudioQualityLevel newQualityLevelFromHandle(long j, RubyManifestParserJni rubyManifestParserJni) {
        int[] qualityLevelIntMetaData = rubyManifestParserJni.getQualityLevelIntMetaData(j);
        String[] qualityLevelStrMetaData = rubyManifestParserJni.getQualityLevelStrMetaData(j);
        int[] audioQualityLevelIntMetaData = rubyManifestParserJni.getAudioQualityLevelIntMetaData(j);
        return new RubyAudioQualityLevel(qualityLevelIntMetaData[0], qualityLevelIntMetaData[1], qualityLevelStrMetaData[0], qualityLevelStrMetaData[1], qualityLevelIntMetaData[2], audioQualityLevelIntMetaData[0], audioQualityLevelIntMetaData[1], audioQualityLevelIntMetaData[2], audioQualityLevelIntMetaData[3], audioQualityLevelIntMetaData[4], rubyManifestParserJni.getAudioQualityLevelBooleanMetadata(j)[0]);
    }

    @Override // com.amazon.avod.playback.smoothstream.AudioQualityLevel
    public int getAudioTag() {
        return this.mAudioTag;
    }

    @Override // com.amazon.avod.playback.smoothstream.AudioQualityLevel
    public int getNumChannels() {
        return this.mNumChannels;
    }

    @Override // com.amazon.avod.playback.smoothstream.AudioQualityLevel
    public int getSampleRate() {
        return this.mSampleRate;
    }

    @Override // com.amazon.avod.playback.smoothstream.AudioQualityLevel
    public int getBitsPerSample() {
        return this.mBitsPerSample;
    }

    @Override // com.amazon.avod.playback.smoothstream.AudioQualityLevel
    public int getPackageSize() {
        return this.mPackageSize;
    }

    @Override // com.amazon.avod.playback.smoothstream.AudioQualityLevel
    public boolean isAtmos() {
        return this.mIsAtmos;
    }
}
