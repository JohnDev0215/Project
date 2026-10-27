package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class PreviousSessionBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final SmoothStreamingContentStore mContentStore;
    private ContentSessionContext mContext;
    private boolean mIsQualityLevelCached = false;
    private QualityLevel mLastQualityLevel;

    public PreviousSessionBitrateSelectionComponent(SmoothStreamingContentStore smoothStreamingContentStore) {
        this.mContentStore = smoothStreamingContentStore;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        this.mContext = contentSessionContext;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (!this.mIsQualityLevelCached) {
            cacheLastSessionQualityLevel(streamingBitrateSelectionState.getStream());
        }
        QualityLevel qualityLevel2 = this.mLastQualityLevel;
        return qualityLevel2 != null ? qualityLevel2 : qualityLevel;
    }

    private void cacheLastSessionQualityLevel(StreamIndex streamIndex) {
        try {
            try {
                SmoothStreamingURI bestQualityAvailable = this.mContentStore.getBestQualityAvailable(this.mContext, new SmoothStreamingURI(streamIndex, 0));
                if (bestQualityAvailable != null) {
                    this.mLastQualityLevel = bestQualityAvailable.getQualityLevel();
                }
            } catch (ContentException unused) {
                DLog.warnf("Failed to read existing quality level for %s", this.mContext.getContent());
            }
        } finally {
            this.mIsQualityLevelCached = true;
        }
    }
}
