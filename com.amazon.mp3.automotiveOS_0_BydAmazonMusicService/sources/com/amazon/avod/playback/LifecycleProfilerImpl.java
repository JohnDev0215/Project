package com.amazon.avod.playback;

import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LifecycleProfilerImpl implements LifecycleProfiler {
    private final ConcurrentMap<LifecycleProfilerMetrics, Stopwatch> mTimerMap = Maps.newConcurrentMap();

    protected LifecycleProfilerImpl() {
    }

    @Override // com.amazon.avod.playback.LifecycleProfiler
    public void start(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics) {
        if (this.mTimerMap.containsKey(lifecycleProfilerMetrics)) {
            DLog.warnf("mTimerMap already contains %s", lifecycleProfilerMetrics);
        } else {
            this.mTimerMap.put(lifecycleProfilerMetrics, Stopwatch.createStarted(Tickers.androidTicker()));
        }
    }

    @Override // com.amazon.avod.playback.LifecycleProfiler
    public void end(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics) {
        Stopwatch stopwatch = this.mTimerMap.get(lifecycleProfilerMetrics);
        if (stopwatch != null) {
            if (stopwatch.isRunning()) {
                stopwatch.stop();
                return;
            }
            return;
        }
        DLog.warnf("mTimerMap does not contain %s", lifecycleProfilerMetrics);
    }

    @Override // com.amazon.avod.playback.LifecycleProfiler
    public void reportMetric(@Nonnull List<LifecycleProfilerReporter> list) {
        HashMap map = new HashMap(list.size());
        for (Map.Entry<LifecycleProfilerMetrics, Stopwatch> entry : this.mTimerMap.entrySet()) {
            Stopwatch value = entry.getValue();
            map.put(entry.getKey(), value.isRunning() ? null : Long.valueOf(value.elapsed(TimeUnit.MILLISECONDS)));
        }
        Iterator<LifecycleProfilerReporter> it = list.iterator();
        while (it.hasNext()) {
            it.next().reportMetric(map);
        }
        this.mTimerMap.clear();
    }
}
