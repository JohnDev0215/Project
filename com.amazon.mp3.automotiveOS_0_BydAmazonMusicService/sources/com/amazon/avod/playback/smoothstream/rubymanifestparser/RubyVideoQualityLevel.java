package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.VideoQualityLevel;

/* JADX INFO: loaded from: classes2.dex */
class RubyVideoQualityLevel extends RubyQualityLevel implements VideoQualityLevel {
    private final int mMaxHeight;
    private final int mMaxWidth;

    RubyVideoQualityLevel(int i, int i2, String str, String str2, int i3, int i4, int i5) {
        super(i, i2, str, str2, i3);
        this.mMaxWidth = i4;
        this.mMaxHeight = i5;
    }

    static RubyVideoQualityLevel newQualityLevelFromHandle(long j, RubyManifestParserJni rubyManifestParserJni) {
        int[] qualityLevelIntMetaData = rubyManifestParserJni.getQualityLevelIntMetaData(j);
        String[] qualityLevelStrMetaData = rubyManifestParserJni.getQualityLevelStrMetaData(j);
        int[] videoQualityLevelMetaData = rubyManifestParserJni.getVideoQualityLevelMetaData(j);
        return new RubyVideoQualityLevel(qualityLevelIntMetaData[0], qualityLevelIntMetaData[1], qualityLevelStrMetaData[0], qualityLevelStrMetaData[1], qualityLevelIntMetaData[2], videoQualityLevelMetaData[0], videoQualityLevelMetaData[1]);
    }

    @Override // com.amazon.avod.playback.smoothstream.VideoQualityLevel
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override // com.amazon.avod.playback.smoothstream.VideoQualityLevel
    public int getMaxHeight() {
        return this.mMaxHeight;
    }
}
