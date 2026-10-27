package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;

/* JADX INFO: loaded from: classes2.dex */
public class FixedBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private ContentSessionContext mContext;
    private final DefaultQualityConfiguration mQualityConfig;

    public FixedBitrateSelectionComponent(DefaultQualityConfiguration defaultQualityConfiguration) {
        this.mQualityConfig = defaultQualityConfiguration;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        this.mContext = contentSessionContext;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        return this.mQualityConfig.getDefaultQualityLevel(this.mContext, streamingBitrateSelectionState.getStream());
    }
}
