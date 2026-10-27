package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.QualityLevel;

/* JADX INFO: loaded from: classes2.dex */
class RubyQualityLevel implements QualityLevel {
    private final int mBitrate;
    private final String mCodecData;
    private final String mFourCC;
    private final int mIndex;
    private final int mNalUnitLengthField;

    RubyQualityLevel(int i, int i2, String str, String str2, int i3) {
        this.mIndex = i;
        this.mBitrate = i2;
        this.mFourCC = str;
        this.mCodecData = str2;
        this.mNalUnitLengthField = i3;
    }

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    public int getBitrate() {
        return this.mBitrate;
    }

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    public String getFourCC() {
        return this.mFourCC;
    }

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    public String getCodecData() {
        return this.mCodecData;
    }

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    public int getNalUnitlengthField() {
        return this.mNalUnitLengthField;
    }
}
