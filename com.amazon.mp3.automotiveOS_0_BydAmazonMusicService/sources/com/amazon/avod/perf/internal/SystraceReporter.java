package com.amazon.avod.perf.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.amazon.avod.perf.CounterMetric;
import com.amazon.avod.perf.Extra;
import com.amazon.avod.perf.Marker;
import com.amazon.avod.perf.TimerMetric;
import javax.annotation.Nullable;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(18)
public class SystraceReporter implements ProfilerListener {
    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onCounterMetric(CounterMetric counterMetric) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onMarker(Marker marker, Extra extra) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onProfilerStop(@Nullable Context context) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTimerMetric(TimerMetric timerMetric) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onProfilerStart() {
        Log.w("AmazonVideo.perf", "Android event tracing requires API version 18. Current version: " + Build.VERSION.SDK_INT);
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTraceBegin(Trace trace) {
        String name = trace.getName();
        android.os.Trace.beginSection(name.substring(0, Math.min(name.length(), WorkQueueKt.MASK)));
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTraceEnd(Trace trace) {
        android.os.Trace.endSection();
    }
}
