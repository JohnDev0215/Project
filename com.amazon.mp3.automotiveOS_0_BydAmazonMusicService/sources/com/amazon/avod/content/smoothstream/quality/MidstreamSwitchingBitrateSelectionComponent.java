package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MidstreamSwitchingBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private QualityLevelClamper mClamper;
    private final QualityLevelClamperFactory mClamperFactory;

    public MidstreamSwitchingBitrateSelectionComponent(QualityLevelClamperFactory qualityLevelClamperFactory) {
        this.mClamperFactory = qualityLevelClamperFactory;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(@Nonnull ContentSessionContext contentSessionContext) {
        this.mClamper = this.mClamperFactory.newServerConfigurableQualityLevelClamper(contentSessionContext.getManifest());
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    @Nonnull
    public QualityLevel selectQuality(@Nonnull StreamingBitrateSelectionState streamingBitrateSelectionState, @Nonnull QualityLevel qualityLevel) {
        Preconditions.checkArgument(streamingBitrateSelectionState.getStream().isVideo(), "Only video streams are supported for now.");
        return this.mClamper.clampQualityLevel((VideoQualityLevel) qualityLevel);
    }

    public void updateClamper(@Nonnull QualityLevelClamper qualityLevelClamper) {
        this.mClamper = (QualityLevelClamper) Preconditions.checkNotNull(qualityLevelClamper, "clamper");
    }
}
