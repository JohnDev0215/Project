package com.amazon.avod.perf.internal;

import android.content.Context;
import com.amazon.avod.perf.CounterMetric;
import com.amazon.avod.perf.Extra;
import com.amazon.avod.perf.Marker;
import com.amazon.avod.perf.TimerMetric;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ProfilerListener {
    void onCounterMetric(CounterMetric counterMetric);

    void onMarker(Marker marker, Extra extra);

    void onProfilerStart();

    void onProfilerStop(@Nullable Context context);

    void onTimerMetric(TimerMetric timerMetric);

    void onTraceBegin(Trace trace);

    void onTraceEnd(Trace trace);
}
