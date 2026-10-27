package com.amazon.avod.content.image;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ImageQualityLevelInterface extends QualityLevel {
    int getAvailabilityTimeOffsetSeconds();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ int getBitrate();

    long getChunkIndexFromNanos(long j);

    int getChunkNumCol();

    int getChunkNumRow();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ float getFrameRate();

    long getImageDurationNanos();

    @Nullable
    String getImageMediaUrl(int i);

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* bridge */ /* synthetic */ default int getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* bridge */ /* synthetic */ default int getMaxWidth() {
        return super.getMaxWidth();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ int getNalUnitlengthField();

    long getThumbnailDurationNanos();

    int getThumbnailHeightInPixels();

    int getThumbnailIndexFromNanos(long j);

    long getThumbnailTimeInNanos(long j, int i);

    int getThumbnailWidthInPixels();

    int getTotalThumbnailPerChunk();

    boolean isInitialized();
}
