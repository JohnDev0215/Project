package com.amazon.avod.content.urlvending;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QoeEvaluatorUsingPlaybackAndNetworkFeatures.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeEvaluatorUsingPlaybackAndNetworkFeatures;", "Lcom/amazon/avod/content/urlvending/QoeEvaluator;", "qoeConfig", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "(Lcom/amazon/avod/content/urlvending/QoeConfigInterface;)V", "reCalculateQoe", "", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QoeEvaluatorUsingPlaybackAndNetworkFeatures extends QoeEvaluator {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QoeEvaluatorUsingPlaybackAndNetworkFeatures(QoeConfigInterface qoeConfig) {
        super(qoeConfig);
        Intrinsics.checkNotNullParameter(qoeConfig, "qoeConfig");
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluator
    public void reCalculateQoe() {
        setQoeValue(((((((((getBitrateInput$ATVPlayerHeuristics_release() + getFrontBufferSizeInput$ATVPlayerHeuristics_release()) + getBandwidthChangeInput$ATVPlayerHeuristics_release()) - getLatencyChangeInput$ATVPlayerHeuristics_release()) - getManifestLatencyChangeInput$ATVPlayerHeuristics_release()) - getTimeSpentBufferingInput$ATVPlayerHeuristics_release()) - getNumOfBufferingEventInput$ATVPlayerHeuristics_release()) + getBandwidthInput$ATVPlayerHeuristics_release()) - getLatencyInput$ATVPlayerHeuristics_release()) - getManifestLatencyInput$ATVPlayerHeuristics_release());
    }
}
