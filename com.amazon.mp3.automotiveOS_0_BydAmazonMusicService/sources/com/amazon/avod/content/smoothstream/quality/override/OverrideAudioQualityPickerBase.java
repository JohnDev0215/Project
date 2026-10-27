package com.amazon.avod.content.smoothstream.quality.override;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
abstract class OverrideAudioQualityPickerBase implements StreamingBitrateSelectionComponent {
    protected QualityLevel[] mSortedAudioQualityLevels;

    OverrideAudioQualityPickerBase() {
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        QualityLevel[] sortedQualityLevels = contentSessionContext.getStreamSelections().getAudioStream().getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(contentSessionContext.getStreamSelections().getAudioStream().getIndex()));
        this.mSortedAudioQualityLevels = (QualityLevel[]) Arrays.copyOf(sortedQualityLevels, sortedQualityLevels.length);
    }
}
