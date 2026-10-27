package com.amazon.avod.content.smoothstream.quality.override;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class RandomQualityPicker extends OverrideQualityPickerBase {
    private final int mBitrateSwitchInterval;
    private int mLastQualityIndex;
    private final Random mRandomGenerator;

    @Override // com.amazon.avod.content.smoothstream.quality.override.OverrideQualityPickerBase, com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public /* bridge */ /* synthetic */ void initialize(ContentSessionContext contentSessionContext) {
        super.initialize(contentSessionContext);
    }

    public RandomQualityPicker(QualityLevelClamperFactory qualityLevelClamperFactory, int i) {
        super(qualityLevelClamperFactory);
        this.mRandomGenerator = new Random();
        this.mLastQualityIndex = 0;
        this.mBitrateSwitchInterval = Math.max(1, i);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        QualityLevel qualityLevel2;
        int iMax = Math.max(streamingBitrateSelectionState.getChunkIndex(), 0);
        synchronized (this.mRandomGenerator) {
            if (iMax % this.mBitrateSwitchInterval == 0) {
                this.mLastQualityIndex = this.mRandomGenerator.nextInt(this.mFilteredSortedVideoQualityLevels.length);
            }
            qualityLevel2 = this.mFilteredSortedVideoQualityLevels[this.mLastQualityIndex];
        }
        return qualityLevel2;
    }
}
