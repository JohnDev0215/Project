package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceCapabilityBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private QualityLevelClamper mClamper;
    private final QualityLevelClamperFactory mClamperFactory;

    public DeviceCapabilityBitrateSelectionComponent(QualityLevelClamperFactory qualityLevelClamperFactory) {
        this.mClamperFactory = qualityLevelClamperFactory;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        this.mClamper = this.mClamperFactory.newServerConfigurableQualityLevelClamper(contentSessionContext.getManifest());
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        Preconditions.checkArgument(streamingBitrateSelectionState.getStream().isVideo(), "Only video streams are supported for now.");
        return this.mClamper.clampQualityLevel((VideoQualityLevel) qualityLevel);
    }
}
