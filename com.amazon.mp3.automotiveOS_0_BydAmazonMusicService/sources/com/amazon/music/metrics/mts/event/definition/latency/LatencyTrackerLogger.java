package com.amazon.music.metrics.mts.event.definition.latency;

import com.amazon.music.metrics.mts.event.types.launchlatencyinfo.LaunchLatencyCode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: LatencyTrackerLogger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018J6\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyTrackerLogger;", "", "", "latencyCode", "operation", "subType", "", "duration", "", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyDuration;", "pieceDurations", "", "printLatencyInformation", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "getLogger", "()Lorg/slf4j/Logger;", "coldStartDuration", "J", "", "isFirstTimeColdStart", "Z", "<init>", "()V", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0})
public final class LatencyTrackerLogger {
    public static final LatencyTrackerLogger INSTANCE;
    private static long coldStartDuration;
    private static boolean isFirstTimeColdStart;
    private static final Logger logger;

    private LatencyTrackerLogger() {
    }

    static {
        LatencyTrackerLogger latencyTrackerLogger = new LatencyTrackerLogger();
        INSTANCE = latencyTrackerLogger;
        Logger logger2 = LoggerFactory.getLogger(latencyTrackerLogger.getClass().getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(javaClass.simpleName)");
        logger = logger2;
        isFirstTimeColdStart = true;
    }

    public final void printLatencyInformation(String latencyCode, String operation, String subType, long duration, List<LatencyDuration> pieceDurations) {
        Intrinsics.checkNotNullParameter(latencyCode, "latencyCode");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Intrinsics.checkNotNullParameter(subType, "subType");
        if (Intrinsics.areEqual(latencyCode, LaunchLatencyCode.COLD_START.getLatencyCode())) {
            coldStartDuration = duration;
            return;
        }
        if (isFirstTimeColdStart) {
            isFirstTimeColdStart = false;
            logger.debug("Cold Start duration: " + coldStartDuration + "ms | Landing Page latencyCode: duration: " + duration + "ms | Total User Perceived Cold Start duration: " + (coldStartDuration + duration) + "ms");
        }
        Logger logger2 = logger;
        StringBuilder sb = new StringBuilder();
        sb.append("latencyCode: [");
        sb.append(latencyCode);
        sb.append("] operation: [");
        sb.append(operation);
        sb.append("] subType: [");
        sb.append(subType);
        sb.append("] duration = ");
        sb.append(duration);
        sb.append("ms | [pieceDurations: ");
        sb.append((Object) (pieceDurations == null ? null : CollectionsKt___CollectionsKt.joinToString$default(pieceDurations, null, null, null, 0, null, new Function1<LatencyDuration, CharSequence>() { // from class: com.amazon.music.metrics.mts.event.definition.latency.LatencyTrackerLogger.printLatencyInformation.1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(LatencyDuration it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getLatencyLeg() + ": " + it.getDuration() + "ms";
            }
        }, 31, null)));
        sb.append(']');
        logger2.debug(sb.toString());
    }
}
