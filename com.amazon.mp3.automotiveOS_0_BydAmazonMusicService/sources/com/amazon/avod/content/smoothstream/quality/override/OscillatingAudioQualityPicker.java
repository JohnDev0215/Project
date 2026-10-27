package com.amazon.avod.content.smoothstream.quality.override;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;

/* JADX INFO: loaded from: classes2.dex */
public class OscillatingAudioQualityPicker extends OverrideAudioQualityPickerBase {
    @Override // com.amazon.avod.content.smoothstream.quality.override.OverrideAudioQualityPickerBase, com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public /* bridge */ /* synthetic */ void initialize(ContentSessionContext contentSessionContext) {
        super.initialize(contentSessionContext);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        int iMax = Math.max(streamingBitrateSelectionState.getChunkIndex(), 0);
        QualityLevel[] qualityLevelArr = this.mSortedAudioQualityLevels;
        return qualityLevelArr[iMax % qualityLevelArr.length];
    }
}
