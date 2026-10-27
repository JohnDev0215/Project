package com.amazon.avod.media.profiling;

import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PerfTracerMediaProfiler implements MediaProfiler {
    private final ThreadLocal<Map<String, TraceKey>> mTraceKeys = new ThreadLocal<>();

    @Override // com.amazon.avod.media.framework.profiling.MediaProfiler
    public void start(String str) {
        getTraceKeyMap().put(str, Profiler.beginTrace(Profiler.TraceLevel.INFO, str));
    }

    @Override // com.amazon.avod.media.framework.profiling.MediaProfiler
    public void stop(String str) {
        Profiler.endTrace(getTraceKeyMap().remove(str));
    }

    @Nonnull
    private Map<String, TraceKey> getTraceKeyMap() {
        if (this.mTraceKeys.get() == null) {
            this.mTraceKeys.set(Maps.newHashMap());
        }
        return this.mTraceKeys.get();
    }
}
