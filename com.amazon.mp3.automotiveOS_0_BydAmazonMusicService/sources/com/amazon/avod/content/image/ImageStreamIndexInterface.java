package com.amazon.avod.content.image;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ImageStreamIndexInterface extends StreamIndex {
    @Nonnull
    Set<String> getAllPeriodIds();

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ double getDisplayAspectRatio();

    @Nonnull
    String getImageUrl(@Nullable String str, @Nonnull QualityLevel qualityLevel, int i, @Nonnull String str2);

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ int getIndex();

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ float getMaxFrameRate();

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ int getMaxHeight();

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ int getMaxWidth();

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ int getNumChunks();

    int getNumPeriods();

    @Nonnull
    ImageQualityLevelInterface[] getSortedImageQualityLevels(@Nonnull String str);
}
