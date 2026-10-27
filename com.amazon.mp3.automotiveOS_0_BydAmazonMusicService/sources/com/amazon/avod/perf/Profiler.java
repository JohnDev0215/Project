package com.amazon.avod.perf;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import com.amazon.avod.core.Framework;
import com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.metrics.pmet.internal.ValidatedCounterMetric;
import com.amazon.avod.perf.internal.FileReporter;
import com.amazon.avod.perf.internal.LogcatReporter;
import com.amazon.avod.perf.internal.PerfSettings;
import com.amazon.avod.perf.internal.ProfilerListener;
import com.amazon.avod.perf.internal.SystraceReporter;
import com.amazon.avod.perf.internal.Trace;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes2.dex */
public final class Profiler {

    @GuardedBy("Profiler.class")
    static Extra mCurrentExtra = null;

    @GuardedBy("Profiler.class")
    static Marker mCurrentMarker = null;
    private static final AtomicBoolean mInitializeCalled;
    private static volatile Set<ProfilerListener> mProfilerListeners = null;
    private static volatile long mRecordingStartTime = 0;
    private static boolean sIsFileLoggingEnabled = false;
    private static int sTraceLevel;
    private static String sFileLoggingPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "aivProfiler.csv";
    private static boolean sIsFileLoggingExternal = true;
    private static boolean sIsLazyFormattingEnabled = true;
    private static volatile boolean mIsRecording = false;
    private static volatile long mFirstTraceTime = -2147483648L;
    private static final Map<TraceKey, Trace> mTraces = Maps.newConcurrentMap();
    private static List<MarkerMetric> mMetrics = Lists.newLinkedList();

    static {
        mProfilerListeners = Collections.emptySet();
        if (Framework.isDebugConfigurationEnabled()) {
            mProfilerListeners = ImmutableSet.of((SystraceReporter) new LogcatReporter(), new SystraceReporter());
        } else {
            mProfilerListeners = ImmutableSet.of(new LogcatReporter());
        }
        mInitializeCalled = new AtomicBoolean(false);
    }

    public enum TraceLevel {
        CRITICAL(1),
        INFO(2),
        DEBUG(3),
        VERBOSE(4),
        TMI(100);

        final int mTraceLvl;

        TraceLevel(int i) {
            this.mTraceLvl = i;
        }
    }

    public static void addListener(@Nonnull ProfilerListener profilerListener) {
        Preconditions.checkNotNull(profilerListener, "profilerListener");
        addListeners(Sets.newHashSet(profilerListener));
    }

    public static void addListeners(@Nonnull Set<ProfilerListener> set) {
        Preconditions.checkNotNull(set, "profilerListeners");
        mProfilerListeners = ImmutableSet.builder().addAll((Iterable) mProfilerListeners).addAll((Iterable) set).build();
    }

    public static synchronized void start() {
        start(SystemClock.elapsedRealtime());
    }

    public static synchronized void start(long j) {
        mRecordingStartTime = j;
        mIsRecording = true;
        Iterator<ProfilerListener> it = mProfilerListeners.iterator();
        while (it.hasNext()) {
            it.next().onProfilerStart();
        }
    }

    public static synchronized void stop(@Nullable Context context) {
        mIsRecording = false;
        Iterator<Map.Entry<TraceKey, Trace>> it = mTraces.entrySet().iterator();
        while (it.hasNext()) {
            Trace value = it.next().getValue();
            value.endPrematurely(null, mRecordingStartTime);
            Iterator<ProfilerListener> it2 = mProfilerListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onTraceEnd(value);
            }
        }
        Iterator<ProfilerListener> it3 = mProfilerListeners.iterator();
        while (it3.hasNext()) {
            it3.next().onProfilerStop(context);
        }
    }

    public static synchronized void trigger(Marker marker) {
        trigger(marker, null);
    }

    public static synchronized void trigger(Marker marker, Extra extra) {
        mCurrentMarker = marker;
        mCurrentExtra = extra;
        if (mIsRecording) {
            Iterator<ProfilerListener> it = mProfilerListeners.iterator();
            while (it.hasNext()) {
                it.next().onMarker(mCurrentMarker, mCurrentExtra);
            }
        }
        for (MarkerMetric markerMetric : mMetrics) {
            if (markerMetric.onMarker()) {
                if (mIsRecording) {
                    reportMetric(markerMetric);
                }
                markerMetric.reset();
            }
        }
    }

    private static synchronized void reportMetric(MarkerMetric markerMetric) {
        if (markerMetric instanceof TimerMetric) {
            reportTimerMetric((TimerMetric) markerMetric);
        } else if (markerMetric instanceof CounterMetric) {
            reportCounterMetric((CounterMetric) markerMetric);
        } else {
            throw new IllegalArgumentException("Metric type is invalid.");
        }
    }

    public static synchronized void reportTimerMetric(TimerMetric timerMetric) {
        if (mIsRecording) {
            Iterator<ProfilerListener> it = mProfilerListeners.iterator();
            while (it.hasNext()) {
                it.next().onTimerMetric(timerMetric);
            }
        }
    }

    @Deprecated
    public static synchronized void reportCounterMetric(CounterMetric counterMetric) {
        if (mIsRecording) {
            Iterator<ProfilerListener> it = mProfilerListeners.iterator();
            while (it.hasNext()) {
                it.next().onCounterMetric(counterMetric);
            }
        }
    }

    public static synchronized void reportCounterWithNameParameters(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate, @Nonnull ImmutableList<MetricParameter> immutableList) {
        reportCounterWithParameters(enumeratedCounterMetricTemplate, immutableList, ImmutableList.of());
    }

    public static synchronized void reportCounterWithValueParameters(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate, @Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList) {
        reportCounterWithParameters(enumeratedCounterMetricTemplate, ImmutableList.of(), immutableList);
    }

    public static synchronized void reportCounterWithValueParameters(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate, @Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList, long j) {
        reportCounterWithParameters(enumeratedCounterMetricTemplate, ImmutableList.of(), immutableList, j);
    }

    public static synchronized void reportCounterWithParameters(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate, @Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList2) {
        Preconditions.checkNotNull(enumeratedCounterMetricTemplate, "metric");
        Preconditions.checkNotNull(immutableList, "nameParameters");
        Preconditions.checkNotNull(immutableList2, "valueParameters");
        reportCounter(enumeratedCounterMetricTemplate.format(immutableList, immutableList2));
    }

    public static synchronized void reportCounterWithParameters(@Nonnull EnumeratedCounterMetricTemplate enumeratedCounterMetricTemplate, @Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList2, long j) {
        Preconditions.checkNotNull(enumeratedCounterMetricTemplate, "metric");
        Preconditions.checkNotNull(immutableList, "nameParameters");
        Preconditions.checkNotNull(immutableList2, "valueParameters");
        reportCounter(enumeratedCounterMetricTemplate.format(immutableList, immutableList2, j));
    }

    public static synchronized void reportCounterFromValidatedCounterMetric(@Nonnull ValidatedCounterMetric validatedCounterMetric) {
        Preconditions.checkNotNull(validatedCounterMetric, "metric");
        reportCounter(validatedCounterMetric);
    }

    private static synchronized void reportCounter(@Nonnull CounterMetric counterMetric) {
        Preconditions.checkNotNull(counterMetric, "metric");
        if (mIsRecording) {
            Iterator<ProfilerListener> it = mProfilerListeners.iterator();
            while (it.hasNext()) {
                it.next().onCounterMetric(counterMetric);
            }
        }
    }

    @Deprecated
    public static synchronized void incrementCounter(String str) {
        if (mIsRecording) {
            reportCounterMetric(new SimpleCounterMetric(str));
        }
    }

    @Nullable
    public static TraceKey beginTrace(@Nonnull TraceLevel traceLevel, @Nullable String str) {
        if (traceLevel.mTraceLvl > sTraceLevel || str == null) {
            return null;
        }
        return beginTraceInternal(str, new Object[0]);
    }

    @Nullable
    public static TraceKey beginTrace(@Nonnull TraceLevel traceLevel, @Nullable String str, @Nullable Object obj) {
        if (traceLevel.mTraceLvl > sTraceLevel || str == null) {
            return null;
        }
        return beginTraceInternal(str, obj);
    }

    @Nullable
    public static TraceKey beginTrace(@Nonnull TraceLevel traceLevel, @Nullable String str, @Nullable Object obj, @Nullable Object obj2) {
        if (traceLevel.mTraceLvl > sTraceLevel || str == null) {
            return null;
        }
        return beginTraceInternal(str, obj, obj2);
    }

    @Nullable
    public static TraceKey beginTrace(@Nonnull TraceLevel traceLevel, @Nullable String str, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        if (traceLevel.mTraceLvl > sTraceLevel || str == null) {
            return null;
        }
        return beginTraceInternal(str, obj, obj2, obj3);
    }

    @Nullable
    public static TraceKey beginTrace(@Nonnull TraceLevel traceLevel, @Nullable String str, @Nullable Object... objArr) {
        if (traceLevel.mTraceLvl > sTraceLevel || str == null) {
            return null;
        }
        return beginTraceInternal(str, objArr);
    }

    public static TraceKey beginTrace(@Nonnull TraceLevel traceLevel, @Nonnull Thread thread, String str, @Nullable Object... objArr) {
        if (traceLevel.mTraceLvl > sTraceLevel || str == null) {
            return null;
        }
        return beginTraceInternal(thread, str, objArr);
    }

    private static TraceKey beginTraceInternal(@Nonnull String str, @Nullable Object... objArr) {
        return beginTraceInternal(Thread.currentThread(), str, objArr);
    }

    private static TraceKey beginTraceInternal(@Nonnull Thread thread, @Nonnull String str, @Nullable Object... objArr) {
        if (!sIsLazyFormattingEnabled && objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
            objArr = null;
        }
        TraceKey traceKey = new TraceKey(thread, str, objArr);
        Trace trace = new Trace(traceKey);
        trace.begin();
        mTraces.put(traceKey, trace);
        if (mFirstTraceTime == -2147483648L) {
            mFirstTraceTime = SystemClock.elapsedRealtime();
        }
        if (mIsRecording) {
            Iterator<ProfilerListener> it = mProfilerListeners.iterator();
            while (it.hasNext()) {
                it.next().onTraceBegin(trace);
            }
        }
        return traceKey;
    }

    public static void endTrace(@Nullable TraceKey traceKey) {
        endTrace(traceKey, null);
    }

    public static void endTrace(@Nullable TraceKey traceKey, @Nullable String str) {
        Trace traceRemove;
        if (traceKey == null || (traceRemove = mTraces.remove(traceKey)) == null || !mIsRecording) {
            return;
        }
        traceRemove.end(str, mRecordingStartTime);
        Iterator<ProfilerListener> it = mProfilerListeners.iterator();
        while (it.hasNext()) {
            it.next().onTraceEnd(traceRemove);
        }
    }

    private static void endTraceAtTime(@Nullable TraceKey traceKey, @Nullable String str, long j) {
        Trace traceRemove;
        if (traceKey == null || (traceRemove = mTraces.remove(traceKey)) == null || !mIsRecording) {
            return;
        }
        traceRemove.endRetroactively(str, mRecordingStartTime, j);
        Iterator<ProfilerListener> it = mProfilerListeners.iterator();
        while (it.hasNext()) {
            it.next().onTraceEnd(traceRemove);
        }
    }

    public static void cancelTrace(@Nullable TraceKey traceKey) {
        if (sTraceLevel <= 0 || traceKey == null) {
            return;
        }
        mTraces.remove(traceKey);
    }

    public static final void initialize(@Nonnull PerfSettings perfSettings) {
        if (mInitializeCalled.getAndSet(true)) {
            return;
        }
        Preconditions.checkNotNull(perfSettings, "perfSettings");
        sTraceLevel = perfSettings.getTraceLevel();
        boolean zIsPerfLoggingEnabled = perfSettings.isPerfLoggingEnabled();
        sIsFileLoggingEnabled = zIsPerfLoggingEnabled;
        if (zIsPerfLoggingEnabled) {
            addListener(new FileReporter(perfSettings.getMemoryBufferMaxSize()));
        }
        sIsLazyFormattingEnabled = perfSettings.isLazyFormattingEnabled();
        if (sTraceLevel >= TraceLevel.CRITICAL.mTraceLvl) {
            TraceKey traceKey = new TraceKey(Thread.currentThread(), "VirtualTrace:TimeBeforeFirstTrace", new Object[0]);
            mTraces.put(traceKey, new Trace(traceKey));
            endTraceAtTime(traceKey, null, mFirstTraceTime);
        }
    }

    public static boolean isIsFileLoggingEnabled() {
        return sIsFileLoggingEnabled;
    }

    public static String getFileLoggingPath() {
        return sFileLoggingPath;
    }

    public static boolean isFileLoggingPathExternal() {
        return sIsFileLoggingExternal;
    }

    public static boolean isTraceLevelEnabled(TraceLevel traceLevel) {
        return sTraceLevel >= traceLevel.mTraceLvl;
    }

    public static final void setTraceLevel(int i) {
        sTraceLevel = i;
    }
}
