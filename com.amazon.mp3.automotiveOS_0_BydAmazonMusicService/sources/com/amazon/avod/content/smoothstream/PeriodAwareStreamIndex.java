package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.PeriodView;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PeriodAwareStreamIndex extends StreamIndex {
    void applyPeriodView(@Nonnull PeriodView periodView) throws ContentException;

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    /* synthetic */ double getDisplayAspectRatio();

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

    @Nonnull
    PeriodView getPeriodView();
}
