package com.amazon.avod.content.smoothstream.quality.override;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
abstract class OverrideQualityPickerBase implements StreamingBitrateSelectionComponent {
    private final QualityLevelClamperFactory mClamperFactory;
    protected QualityLevel[] mFilteredSortedVideoQualityLevels;

    protected OverrideQualityPickerBase(QualityLevelClamperFactory qualityLevelClamperFactory) {
        this.mClamperFactory = qualityLevelClamperFactory;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        QualityLevel[] qualityLevelArr;
        QualityLevelClamper qualityLevelClamperNewServerConfigurableQualityLevelClamper = this.mClamperFactory.newServerConfigurableQualityLevelClamper(contentSessionContext.getManifest());
        QualityLevel[] sortedQualityLevels = contentSessionContext.getStreamSelections().getVideoStream().getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(contentSessionContext.getStreamSelections().getVideoStream().getIndex()));
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (QualityLevel qualityLevel : sortedQualityLevels) {
            if (qualityLevelClamperNewServerConfigurableQualityLevelClamper.isValidQualityLevel((VideoQualityLevel) qualityLevel)) {
                linkedListNewLinkedList.add(qualityLevel);
            }
        }
        if (linkedListNewLinkedList.isEmpty()) {
            qualityLevelArr = (QualityLevel[]) Arrays.copyOf(sortedQualityLevels, 1);
        } else {
            qualityLevelArr = (QualityLevel[]) linkedListNewLinkedList.toArray(new QualityLevel[linkedListNewLinkedList.size()]);
        }
        this.mFilteredSortedVideoQualityLevels = qualityLevelArr;
    }
}
