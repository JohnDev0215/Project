package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPerformanceBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private ContentSessionContext mContext;

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        Preconditions.checkNotNull(contentSessionContext);
        this.mContext = contentSessionContext;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        Preconditions.checkNotNull(this.mContext);
        Preconditions.checkNotNull(streamingBitrateSelectionState);
        Preconditions.checkNotNull(streamingBitrateSelectionState.getStream());
        Preconditions.checkArgument(streamingBitrateSelectionState.getStream().isVideo());
        return getMinQualityLevel(qualityLevel, this.mContext.getState() != null ? this.mContext.getState().getSustainableQualityLevel() : null);
    }

    @Nullable
    private QualityLevel getMinQualityLevel(@Nullable QualityLevel qualityLevel, @Nullable QualityLevel qualityLevel2) {
        return qualityLevel == null ? qualityLevel2 : (qualityLevel2 != null && qualityLevel.getBitrate() >= qualityLevel2.getBitrate()) ? qualityLevel2 : qualityLevel;
    }
}
