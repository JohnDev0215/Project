package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;

/* JADX INFO: loaded from: classes2.dex */
public interface StreamingBitrateSelectionComponent {
    void initialize(ContentSessionContext contentSessionContext);

    QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel);
}
