package com.amazon.avod.content.urlvending;

import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QoeEvaluatorFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeEvaluatorFactory;", "", "()V", "newQoeEvaluator", "Lcom/amazon/avod/content/urlvending/QoeEvaluator;", "qoeConfig", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QoeEvaluatorFactory {
    public static final QoeEvaluatorFactory INSTANCE = new QoeEvaluatorFactory();

    private QoeEvaluatorFactory() {
    }

    public final QoeEvaluator newQoeEvaluator(QoeConfigInterface qoeConfig) {
        Intrinsics.checkNotNullParameter(qoeConfig, "qoeConfig");
        if (qoeConfig.getQoeModelType() == QoeModelType.MODEL_USING_PLAYBACK_AND_NETWORK_FEATURES) {
            DLog.logf("QoeEvaluator is created with MODEL_USING_PLAYBACK_AND_NETWORK_FEATURES.");
            return new QoeEvaluatorUsingPlaybackAndNetworkFeatures(qoeConfig);
        }
        DLog.logf("QoeEvaluator is created with MODEL_USING_NETWORK_FEATURES.");
        return new QoeEvaluatorUsingNetworkFeatures(qoeConfig);
    }
}
