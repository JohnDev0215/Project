package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.classification.Classifier;
import com.amazon.avod.content.smoothstream.quality.MidstreamSwitchingBitrateSelectionComponent;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentSet;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import net.jcip.annotations.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class MidstreamSwitchingController {
    private Classifier mClassifier;
    private ContentSessionContext mContentSessionContext;
    private String mCurrentClusterLabel;
    private QualityLevelClamperFactory mQualityLevelClamperFactory;
    private StreamingBitrateSelectionComponentSet mStreamingBitrateSelectionComponentSet;
    private Map<String, String> playbackSettingsOverride;

    public MidstreamSwitchingController(@Nonnull ContentSessionContext contentSessionContext, @Nonnull StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSet, @Nonnull QualityLevelClamperFactory qualityLevelClamperFactory, @Nonnull Classifier classifier, @Nonnull String str) {
        this.mStreamingBitrateSelectionComponentSet = (StreamingBitrateSelectionComponentSet) Preconditions.checkNotNull(streamingBitrateSelectionComponentSet, "bitrateSelectionComponentSet");
        this.mQualityLevelClamperFactory = (QualityLevelClamperFactory) Preconditions.checkNotNull(qualityLevelClamperFactory, "qualityLevelClamperFactory");
        this.mContentSessionContext = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "context");
        this.mCurrentClusterLabel = (String) Preconditions.checkNotNull(str, "currentClusterLabel");
        this.mClassifier = (Classifier) Preconditions.checkNotNull(classifier, "classifier");
        this.playbackSettingsOverride = contentSessionContext.getAudioVideoUrls().getPlaybackSettingsOverride();
    }

    public void reportDownload(long j, long j2) {
        String strReportDownload = this.mClassifier.reportDownload(DataUnit.BYTES.toMegaBytes(j), TimeUnit.MILLISECONDS.convert(j2, TimeUnit.NANOSECONDS));
        String str = String.format("playbackSettingsMap_%s_filters_maxBitrateKbps", strReportDownload);
        if (strReportDownload.equals(this.mCurrentClusterLabel) || !this.playbackSettingsOverride.containsKey(str)) {
            return;
        }
        String str2 = this.playbackSettingsOverride.get(str);
        Objects.requireNonNull(str2);
        QualityLevelClamper qualityLevelClamperNewServerConfigurableQualityLevelClamper = this.mQualityLevelClamperFactory.newServerConfigurableQualityLevelClamper(this.mContentSessionContext.getManifest(), Integer.parseInt(str2), 0);
        for (StreamingBitrateSelectionComponent streamingBitrateSelectionComponent : this.mStreamingBitrateSelectionComponentSet.getComponents()) {
            if (streamingBitrateSelectionComponent instanceof MidstreamSwitchingBitrateSelectionComponent) {
                ((MidstreamSwitchingBitrateSelectionComponent) streamingBitrateSelectionComponent).updateClamper(qualityLevelClamperNewServerConfigurableQualityLevelClamper);
            }
        }
        this.mCurrentClusterLabel = strReportDownload;
    }
}
