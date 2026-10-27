package com.amazon.scrublib.models;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: TranscriptUpdatedListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/scrublib/models/TranscriptUpdatedListener;", "", "", "Lcom/amazon/scrublib/models/ScrubSegment;", "scrubSegmentList", "", "onTranscriptUpdated", "Lcom/amazon/scrublib/models/TranscriptLoadingType;", "transcriptLoadingType", "onTranscriptLoading", "onTranscriptLoaded", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public interface TranscriptUpdatedListener {
    void onTranscriptLoaded();

    void onTranscriptLoading(TranscriptLoadingType transcriptLoadingType);

    void onTranscriptUpdated(List<ScrubSegment> scrubSegmentList);
}
