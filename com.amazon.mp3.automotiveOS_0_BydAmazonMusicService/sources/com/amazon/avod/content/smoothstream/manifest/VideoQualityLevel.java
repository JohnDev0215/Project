package com.amazon.avod.content.smoothstream.manifest;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoQualityLevel extends QualityLevel {
    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ int getBitrate();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ float getFrameRate();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    int getMaxHeight();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    int getMaxWidth();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ int getNalUnitlengthField();

    boolean isHD();
}
