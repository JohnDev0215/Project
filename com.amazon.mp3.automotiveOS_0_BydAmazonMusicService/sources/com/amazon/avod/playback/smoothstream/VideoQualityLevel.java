package com.amazon.avod.playback.smoothstream;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface VideoQualityLevel extends QualityLevel {
    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    /* synthetic */ int getBitrate();

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    @Deprecated
    /* synthetic */ int getIndex();

    int getMaxHeight();

    int getMaxWidth();

    @Override // com.amazon.avod.playback.smoothstream.QualityLevel
    /* synthetic */ int getNalUnitlengthField();
}
