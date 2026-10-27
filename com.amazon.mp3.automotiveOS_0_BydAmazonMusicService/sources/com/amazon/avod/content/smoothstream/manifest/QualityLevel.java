package com.amazon.avod.content.smoothstream.manifest;

/* JADX INFO: loaded from: classes2.dex */
public interface QualityLevel {
    int getBitrate();

    String getCodecData();

    String getFourCC();

    float getFrameRate();

    default int getMaxHeight() {
        return -1;
    }

    default int getMaxWidth() {
        return -1;
    }

    int getNalUnitlengthField();

    long getTimeScale();
}
