package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HeuristicAlgorithm.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicAlgorithm;", "", "(Ljava/lang/String;I)V", "MAGNITUDE", "SHORT_BUFFER", "LEGACY_SBH", "BOLA_V1", "Companion", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum HeuristicAlgorithm {
    MAGNITUDE,
    SHORT_BUFFER,
    LEGACY_SBH,
    BOLA_V1;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: HeuristicAlgorithm.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicAlgorithm$Companion;", "", "()V", "lookupHeuristicAlgorithm", "Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicAlgorithm;", "algorithm", "", "isLiveStream", "", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HeuristicAlgorithm lookupHeuristicAlgorithm(String algorithm, boolean isLiveStream) {
            if (algorithm != null) {
                try {
                    return HeuristicAlgorithm.valueOf(algorithm);
                } catch (Throwable unused) {
                    DLog.warnf("Unable to map " + ((Object) algorithm) + " string to HeuristicAlgorithm enum, returning default");
                }
            }
            return isLiveStream ? HeuristicAlgorithm.SHORT_BUFFER : HeuristicAlgorithm.MAGNITUDE;
        }
    }
}
