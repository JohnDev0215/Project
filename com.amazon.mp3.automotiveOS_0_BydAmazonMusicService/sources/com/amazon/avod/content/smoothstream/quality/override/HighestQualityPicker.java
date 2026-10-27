package com.amazon.avod.content.smoothstream.quality.override;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;

/* JADX INFO: loaded from: classes2.dex */
public class HighestQualityPicker extends OverrideQualityPickerBase {
    @Override // com.amazon.avod.content.smoothstream.quality.override.OverrideQualityPickerBase, com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public /* bridge */ /* synthetic */ void initialize(ContentSessionContext contentSessionContext) {
        super.initialize(contentSessionContext);
    }

    public HighestQualityPicker(QualityLevelClamperFactory qualityLevelClamperFactory) {
        super(qualityLevelClamperFactory);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        return this.mFilteredSortedVideoQualityLevels[r1.length - 1];
    }
}
