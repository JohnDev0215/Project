package com.amazon.avod.content.smoothstream.quality.override;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;

/* JADX INFO: loaded from: classes2.dex */
public class OscillatingQualityPicker extends OverrideQualityPickerBase {
    private final int mBitrateSwitchInterval;

    @Override // com.amazon.avod.content.smoothstream.quality.override.OverrideQualityPickerBase, com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public /* bridge */ /* synthetic */ void initialize(ContentSessionContext contentSessionContext) {
        super.initialize(contentSessionContext);
    }

    public OscillatingQualityPicker(QualityLevelClamperFactory qualityLevelClamperFactory, int i) {
        super(qualityLevelClamperFactory);
        this.mBitrateSwitchInterval = Math.max(1, i);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        int iMax = Math.max(streamingBitrateSelectionState.getChunkIndex(), 0) / this.mBitrateSwitchInterval;
        QualityLevel[] qualityLevelArr = this.mFilteredSortedVideoQualityLevels;
        return qualityLevelArr[iMax % qualityLevelArr.length];
    }
}
