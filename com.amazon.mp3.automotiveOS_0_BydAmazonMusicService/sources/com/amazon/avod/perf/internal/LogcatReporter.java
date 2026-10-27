package com.amazon.avod.perf.internal;

import android.content.Context;
import android.util.Log;
import com.amazon.avod.perf.CounterMetric;
import com.amazon.avod.perf.Extra;
import com.amazon.avod.perf.Marker;
import com.amazon.avod.perf.TimerMetric;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class LogcatReporter implements ProfilerListener {
    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTraceBegin(Trace trace) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTraceEnd(Trace trace) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onProfilerStart() {
        Log.i("AmazonVideo.profiler", "Profiler starting...");
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onProfilerStop(@Nullable Context context) {
        Log.i("AmazonVideo.profiler", "Profiler stopped.");
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTimerMetric(TimerMetric timerMetric) {
        Log.i("AmazonVideo.profiler", timerMetric.toString());
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onCounterMetric(CounterMetric counterMetric) {
        Log.i("AmazonVideo.profiler", counterMetric.toString());
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onMarker(Marker marker, Extra extra) {
        Log.i("AmazonVideo.profiler", String.format(Locale.US, "%s %s", marker, extra));
    }
}
