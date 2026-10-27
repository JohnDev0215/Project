package com.amazon.avod.playback.smoothstream;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface StreamIndex {
    Chunk[] getChunks();

    double getDisplayAspectRatio();

    String getLanguage();

    QualityLevel[] getQualityLevels();

    long getTimeScale();

    String getType();

    String getUrl();
}
