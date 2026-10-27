package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.util.DLog;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class StreamingBitrateSelectionComponentSet implements StreamingBitrateSelectionComponent {
    public static final StreamingBitrateSelectionComponentSet EMPTY_SET = new StreamingBitrateSelectionComponentSet(Collections.emptyList());
    private final List<StreamingBitrateSelectionComponent> mComponents;

    public StreamingBitrateSelectionComponentSet(List<StreamingBitrateSelectionComponent> list) {
        this.mComponents = list;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        Iterator<StreamingBitrateSelectionComponent> it = this.mComponents.iterator();
        while (it.hasNext()) {
            it.next().initialize(contentSessionContext);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        for (StreamingBitrateSelectionComponent streamingBitrateSelectionComponent : this.mComponents) {
            QualityLevel qualityLevelSelectQuality = streamingBitrateSelectionComponent.selectQuality(streamingBitrateSelectionState, qualityLevel);
            if (qualityLevelSelectQuality != qualityLevel) {
                logQualityChange(streamingBitrateSelectionComponent, qualityLevel.getBitrate(), qualityLevelSelectQuality.getBitrate());
                qualityLevel = qualityLevelSelectQuality;
            }
        }
        return qualityLevel;
    }

    public List<StreamingBitrateSelectionComponent> getComponents() {
        return this.mComponents;
    }

    private void logQualityChange(StreamingBitrateSelectionComponent streamingBitrateSelectionComponent, int i, int i2) {
        DLog.devf("Component %s changed quality %d => %d", streamingBitrateSelectionComponent.getClass().getSimpleName(), Integer.valueOf(i), Integer.valueOf(i2));
    }
}
