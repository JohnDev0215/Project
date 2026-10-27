package com.amazon.avod.playback.smoothstream;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface QualityLevel {
    int getBitrate();

    String getCodecData();

    String getFourCC();

    @Deprecated
    int getIndex();

    int getNalUnitlengthField();
}
