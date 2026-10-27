package com.amazon.avod.playback.smoothstream;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface AudioQualityLevel extends QualityLevel {
    int getAudioTag();

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    /* synthetic */ int getBitrate();

    int getBitsPerSample();

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    @Deprecated
    /* synthetic */ int getIndex();

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    /* synthetic */ int getNalUnitlengthField();

    int getNumChannels();

    int getPackageSize();

    int getSampleRate();

    boolean isAtmos();
}
