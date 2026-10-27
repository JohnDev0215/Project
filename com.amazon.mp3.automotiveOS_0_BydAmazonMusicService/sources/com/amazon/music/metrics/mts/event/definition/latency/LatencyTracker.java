package com.amazon.music.metrics.mts.event.definition.latency;

import androidx.core.app.NotificationCompat;
import com.amazon.music.metrics.mts.event.definition.LatencyInfoEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.perf.ktx.PerformanceKt;
import com.google.firebase.perf.metrics.Trace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt___RangesKt;
import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: LatencyTracker.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bF\u0010GJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001a\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0002J8\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\bH\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J$\u0010\n\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001aJ7\u0010\n\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001a2\b\b\u0002\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ-\u0010#\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001aH\u0000¢\u0006\u0004\b!\u0010\"J-\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001aH\u0000¢\u0006\u0004\b$\u0010%J6\u0010'\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010J\u000e\u0010(\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010+\u001a\u00020\u00042\u0006\u0010*\u001a\u00020)J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\bJ\u0014\u00100\u001a\u00020\u00042\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0.J\u0014\u00102\u001a\u00020\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u00020\b0.R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u00103RB\u00107\u001a*\u0012\u0004\u0012\u00020\u0018\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001f05j\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001f`6048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R0\u0010<\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020;05j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020;`68\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0017\u0010?\u001a\u00020>8\u0006¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u001e\u0010/\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u0010CR\u001e\u00101\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u0010CR\u0016\u0010D\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010E¨\u0006H"}, d2 = {"Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyTracker;", "", "", "traceName", "", "startFirebasePerfTracking", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;", "latencyEventDescriptor", "", "isForTesting", "track", "latencyCode", "operation", "subType", "", "duration", "", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyDuration;", "pieceDurations", "Lcom/amazon/music/metrics/mts/event/definition/LatencyInfoEvent;", "createLatencyEvent", NotificationCompat.CATEGORY_EVENT, "emitEventToMTS", "shouldThrottleMetricsEvent", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "pageLoadLatencyCode", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "latencies", "track$DMMMetricsMTSEvents_release", "(Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;Ljava/util/Map;Z)V", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyTrackingEvent;", "latencyLegs", "createLatencyEventFromLegs$DMMMetricsMTSEvents_release", "(Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;Ljava/util/Map;)Lcom/amazon/music/metrics/mts/event/definition/LatencyInfoEvent;", "createLatencyEventFromLegs", "convertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release", "(Ljava/util/Map;)Ljava/util/List;", "convertLatencyTrackingEventsToPieceDurations", "emitLatencyEvent", "clearLatencyTracker", "", "samplingRate", "setPageLatencySamplingRate", "isInitialized", "setIsFirebaseInitialized", "Lkotlin/Function0;", "isNetworkAvailableCheck", "registerIsNetworkAvailableCheck", "isWifiAvailableCheck", "registerIsWifiAvailableCheck", "I", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "latencyTrackingMap", "Ljava/util/concurrent/ConcurrentHashMap;", "getLatencyTrackingMap$DMMMetricsMTSEvents_release", "()Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/google/firebase/perf/metrics/Trace;", "firebasePerfTrackingMap", "Ljava/util/HashMap;", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "getLogger", "()Lorg/slf4j/Logger;", "Lkotlin/jvm/functions/Function0;", "isFirebaseInitialized", "Z", "<init>", "()V", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0})
public final class LatencyTracker {
    public static final LatencyTracker INSTANCE;
    private static final HashMap<String, Trace> firebasePerfTrackingMap;
    private static boolean isFirebaseInitialized;
    private static Function0<Boolean> isNetworkAvailableCheck;
    private static Function0<Boolean> isWifiAvailableCheck;
    private static final ConcurrentHashMap<PageLoadLatencyCode, HashMap<LatencyTrackingLeg, LatencyTrackingEvent>> latencyTrackingMap;
    private static final Logger logger;
    private static int samplingRate;

    private LatencyTracker() {
    }

    static {
        LatencyTracker latencyTracker = new LatencyTracker();
        INSTANCE = latencyTracker;
        samplingRate = 10;
        latencyTrackingMap = new ConcurrentHashMap<>(PageLoadLatencyCode.values().length);
        firebasePerfTrackingMap = new HashMap<>();
        Logger logger2 = LoggerFactory.getLogger(latencyTracker.getClass().getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(javaClass.simpleName)");
        logger = logger2;
    }

    public final void track(LatencyEventDescriptor latencyEventDescriptor) {
        Intrinsics.checkNotNullParameter(latencyEventDescriptor, "latencyEventDescriptor");
        track(latencyEventDescriptor, false);
    }

    private final void startFirebasePerfTracking(String traceName) {
        if (isFirebaseInitialized) {
            HashMap<String, Trace> map = firebasePerfTrackingMap;
            if (map.containsKey(traceName)) {
                return;
            }
            Trace traceNewTrace = PerformanceKt.getPerformance(Firebase.INSTANCE).newTrace(traceName);
            Intrinsics.checkNotNullExpressionValue(traceNewTrace, "Firebase.performance.newTrace(traceName)");
            traceNewTrace.start();
            map.put(traceName, traceNewTrace);
        }
    }

    public final void track(PageLoadLatencyCode pageLoadLatencyCode, Map<LatencyTrackingLeg, Long> latencies) {
        Intrinsics.checkNotNullParameter(pageLoadLatencyCode, "pageLoadLatencyCode");
        track$DMMMetricsMTSEvents_release(pageLoadLatencyCode, latencies, false);
    }

    public final void track$DMMMetricsMTSEvents_release(PageLoadLatencyCode pageLoadLatencyCode, Map<LatencyTrackingLeg, Long> latencies, boolean isForTesting) {
        Map<LatencyTrackingLeg, LatencyTrackingEvent> map;
        Intrinsics.checkNotNullParameter(pageLoadLatencyCode, "pageLoadLatencyCode");
        LatencyEventDescriptor latencyEventDescriptor = new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, pageLoadLatencyCode);
        if (latencies == null) {
            map = null;
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(latencies.size()));
            Iterator<T> it = latencies.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                linkedHashMap.put(entry.getKey(), new LatencyTrackingEvent(((Number) entry.getValue()).longValue(), new LatencyEventDescriptor((LatencyTrackingLeg) entry.getKey(), pageLoadLatencyCode)));
            }
            map = linkedHashMap;
        }
        if (isFirebaseInitialized) {
            HashMap<String, Trace> map2 = firebasePerfTrackingMap;
            if (map2.containsKey(pageLoadLatencyCode.getLatencyCode())) {
                Trace trace = map2.get(pageLoadLatencyCode.getLatencyCode());
                List<LatencyDuration> listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release = convertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release(map);
                if (listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release != null) {
                    for (LatencyDuration latencyDuration : listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release) {
                        if (trace != null) {
                            trace.putMetric(latencyDuration.getLatencyLeg().getLatencyTrackingLeg(), latencyDuration.getDuration());
                        }
                    }
                }
                if (trace != null) {
                    trace.stop();
                }
                firebasePerfTrackingMap.remove(pageLoadLatencyCode.getLatencyCode());
            }
        }
        LatencyInfoEvent latencyInfoEventCreateLatencyEventFromLegs$DMMMetricsMTSEvents_release = createLatencyEventFromLegs$DMMMetricsMTSEvents_release(latencyEventDescriptor, map);
        if (isForTesting || shouldThrottleMetricsEvent()) {
            return;
        }
        emitEventToMTS(latencyInfoEventCreateLatencyEventFromLegs$DMMMetricsMTSEvents_release);
    }

    private final void track(LatencyEventDescriptor latencyEventDescriptor, boolean isForTesting) {
        HashMap<LatencyTrackingLeg, LatencyTrackingEvent> map;
        Set<LatencyTrackingLeg> setKeySet;
        long jCurrentTimeMillis = System.currentTimeMillis();
        ConcurrentHashMap<PageLoadLatencyCode, HashMap<LatencyTrackingLeg, LatencyTrackingEvent>> concurrentHashMap = latencyTrackingMap;
        if (concurrentHashMap.containsKey(latencyEventDescriptor.getPageLoadLatencyCode())) {
            map = concurrentHashMap.get(latencyEventDescriptor.getPageLoadLatencyCode());
        } else {
            map = new HashMap<>(LatencyTrackingLeg.values().length);
            concurrentHashMap.put(latencyEventDescriptor.getPageLoadLatencyCode(), map);
            if (!isForTesting) {
                startFirebasePerfTracking(latencyEventDescriptor.getPageLoadLatencyCode().getLatencyCode());
            }
        }
        if (map != null) {
            map.put(latencyEventDescriptor.getLatencyLeg(), new LatencyTrackingEvent(jCurrentTimeMillis, latencyEventDescriptor));
        }
        if (latencyEventDescriptor.getLatencyLeg() == LatencyTrackingLeg.LOAD_COMPLETE) {
            int size = 0;
            if (map != null && (setKeySet = map.keySet()) != null) {
                size = setKeySet.size();
            }
            if (size >= 2) {
                LatencyInfoEvent latencyInfoEventCreateLatencyEventFromLegs$DMMMetricsMTSEvents_release = createLatencyEventFromLegs$DMMMetricsMTSEvents_release(latencyEventDescriptor, map);
                if (isFirebaseInitialized) {
                    HashMap<String, Trace> map2 = firebasePerfTrackingMap;
                    if (map2.containsKey(latencyEventDescriptor.getPageLoadLatencyCode().getLatencyCode())) {
                        Trace trace = map2.get(latencyEventDescriptor.getPageLoadLatencyCode().getLatencyCode());
                        List<LatencyDuration> listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release = convertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release(map);
                        if (listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release != null) {
                            for (LatencyDuration latencyDuration : listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release) {
                                if (trace != null) {
                                    trace.putMetric(latencyDuration.getLatencyLeg().getLatencyTrackingLeg(), latencyDuration.getDuration());
                                }
                            }
                        }
                        if (trace != null) {
                            trace.stop();
                        }
                        firebasePerfTrackingMap.remove(latencyEventDescriptor.getPageLoadLatencyCode().getLatencyCode());
                    }
                }
                if (isForTesting) {
                    return;
                }
                if (!shouldThrottleMetricsEvent()) {
                    emitEventToMTS(latencyInfoEventCreateLatencyEventFromLegs$DMMMetricsMTSEvents_release);
                }
                latencyTrackingMap.remove(latencyEventDescriptor.getPageLoadLatencyCode());
                return;
            }
            concurrentHashMap.clear();
        }
    }

    private final LatencyInfoEvent createLatencyEvent(String latencyCode, String operation, String subType, long duration, List<LatencyDuration> pieceDurations) {
        Function0<Boolean> function0;
        LatencyTrackerLogger.INSTANCE.printLatencyInformation(latencyCode, operation, subType, duration, pieceDurations);
        LatencyInfoEvent.Builder builder = LatencyInfoEvent.builder(latencyCode, operation, subType, String.valueOf(duration), pieceDurations);
        Function0<Boolean> function02 = isNetworkAvailableCheck;
        boolean z = false;
        if (function02 != null && function02.invoke().booleanValue()) {
            z = true;
        }
        if (z && (function0 = isWifiAvailableCheck) != null) {
            builder.withActiveNetworkType(function0 == null ? null : function0.invoke());
        }
        LatencyInfoEvent latencyInfoEventBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(latencyInfoEventBuild, "builder(\n            lat…   }\n            .build()");
        return latencyInfoEventBuild;
    }

    public final LatencyInfoEvent createLatencyEventFromLegs$DMMMetricsMTSEvents_release(LatencyEventDescriptor latencyEventDescriptor, Map<LatencyTrackingLeg, LatencyTrackingEvent> latencyLegs) {
        Intrinsics.checkNotNullParameter(latencyEventDescriptor, "latencyEventDescriptor");
        List<LatencyDuration> listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release = convertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release(latencyLegs);
        long duration = 0;
        if (listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release != null) {
            Iterator<T> it = listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release.iterator();
            while (it.hasNext()) {
                duration += ((LatencyDuration) it.next()).getDuration();
            }
        }
        return createLatencyEvent(latencyEventDescriptor.getPageLoadLatencyCode().getLatencyCode(), latencyEventDescriptor.getPageLoadLatencyCode().getOperation(), latencyEventDescriptor.getPageLoadLatencyCode().getSubtype(), duration, listConvertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release);
    }

    public final List<LatencyDuration> convertLatencyTrackingEventsToPieceDurations$DMMMetricsMTSEvents_release(Map<LatencyTrackingLeg, LatencyTrackingEvent> latencyLegs) {
        Set<LatencyTrackingLeg> setKeySet;
        List mutableList;
        ArrayList arrayList = null;
        if (latencyLegs == null || (setKeySet = latencyLegs.keySet()) == null || (mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) setKeySet)) == null) {
            mutableList = null;
        } else if (mutableList.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(mutableList, new Comparator() { // from class: com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker$convertLatencyTrackingEventsToPieceDurations$lambda-4$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((LatencyTrackingLeg) t).getOrder()), Integer.valueOf(((LatencyTrackingLeg) t2).getOrder()));
                }
            });
        }
        if (mutableList != null && mutableList.size() > 1) {
            arrayList = new ArrayList(LatencyTrackingLeg.values().length);
            int i = 0;
            int size = mutableList.size() - 1;
            while (i < size) {
                int i2 = i + 1;
                LatencyTrackingEvent latencyTrackingEvent = latencyLegs.get(mutableList.get(i));
                LatencyTrackingEvent latencyTrackingEvent2 = latencyLegs.get(mutableList.get(i2));
                if (latencyTrackingEvent != null && latencyTrackingEvent2 != null) {
                    arrayList.add(new LatencyDuration(latencyTrackingEvent.getTimestamp(), latencyTrackingEvent2.getTimestamp(), latencyTrackingEvent.getLatencyEventDescriptor().getLatencyLeg(), null, 8, null));
                }
                i = i2;
            }
        }
        return arrayList;
    }

    private final void emitEventToMTS(LatencyInfoEvent event) {
        EventBus.getDefault().post(event);
    }

    public final void emitLatencyEvent(String latencyCode, String operation, String subType, long duration, List<LatencyDuration> pieceDurations) {
        Intrinsics.checkNotNullParameter(latencyCode, "latencyCode");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Intrinsics.checkNotNullParameter(subType, "subType");
        emitEventToMTS(createLatencyEvent(latencyCode, operation, subType, duration, pieceDurations));
    }

    public final void clearLatencyTracker(PageLoadLatencyCode pageLoadLatencyCode) {
        Intrinsics.checkNotNullParameter(pageLoadLatencyCode, "pageLoadLatencyCode");
        ConcurrentHashMap<PageLoadLatencyCode, HashMap<LatencyTrackingLeg, LatencyTrackingEvent>> concurrentHashMap = latencyTrackingMap;
        if (concurrentHashMap.containsKey(pageLoadLatencyCode)) {
            concurrentHashMap.remove(pageLoadLatencyCode);
        }
    }

    public final void setPageLatencySamplingRate(int samplingRate2) {
        samplingRate = samplingRate2;
    }

    public final void setIsFirebaseInitialized(boolean isInitialized) {
        isFirebaseInitialized = isInitialized;
    }

    private final boolean shouldThrottleMetricsEvent() {
        return RangesKt___RangesKt.random(RangesKt___RangesKt.until(0, 100), Random.INSTANCE) > samplingRate;
    }

    public final void registerIsNetworkAvailableCheck(Function0<Boolean> isNetworkAvailableCheck2) {
        Intrinsics.checkNotNullParameter(isNetworkAvailableCheck2, "isNetworkAvailableCheck");
        isNetworkAvailableCheck = isNetworkAvailableCheck2;
    }

    public final void registerIsWifiAvailableCheck(Function0<Boolean> isWifiAvailableCheck2) {
        Intrinsics.checkNotNullParameter(isWifiAvailableCheck2, "isWifiAvailableCheck");
        isWifiAvailableCheck = isWifiAvailableCheck2;
    }
}
