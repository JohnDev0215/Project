package com.amazon.avod.playback.session.workflow.scheduler;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.perf.DurationMetric;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.session.workflow.events.TaskCompletedEvent;
import com.amazon.avod.playback.session.workflow.events.TaskFailedEvent;
import com.amazon.avod.playback.session.workflow.events.TaskStartedEvent;
import com.amazon.avod.playback.session.workflow.tasks.MainPlayerTriggeredOnPrepared;
import com.amazon.avod.playback.session.workflow.tasks.StartPlayback;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseTask implements Task {
    private static String COMPLETED_METRIC_SUFFIX = "-SuccessTimestamp";
    private static String METRIC_PREFIX = "DAG-";
    private final EventBus mEventBus;
    private TraceKey mTraceKey;
    private final Stopwatch mStopwatch = Stopwatch.createUnstarted(Tickers.systemTicker());
    private final String mClassName = getClass().getSimpleName();

    protected abstract void execute() throws MediaException;

    protected BaseTask(@Nonnull EventBus eventBus) {
        this.mEventBus = (EventBus) Preconditions.checkNotNull(eventBus, "eventBus");
    }

    protected final void notifyTaskStarted() {
        startProfiler();
        this.mEventBus.post(new TaskStartedEvent(this));
    }

    protected final void notifyTaskSuccess() {
        stopProfiler("Success");
        this.mEventBus.post(new TaskCompletedEvent(this));
    }

    protected final void notifyTaskFailure() {
        stopProfiler("DAG task Failure");
        this.mEventBus.post(new TaskFailedEvent(this));
    }

    private void startProfiler() {
        if (this.mStopwatch.isRunning()) {
            this.mStopwatch.reset();
        }
        this.mStopwatch.start();
        DLog.logf("DAG notifyTaskStarted: %s", this.mClassName);
        this.mTraceKey = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "Task-%s", this.mClassName);
    }

    private void stopProfiler(String str) {
        if (this.mStopwatch.isRunning()) {
            this.mStopwatch.stop();
        }
        long jElapsed = this.mStopwatch.elapsed(TimeUnit.MILLISECONDS);
        DLog.logf("DAG notifyTask %s: %s duration: %s", str, this.mClassName, Long.valueOf(jElapsed));
        Profiler.reportTimerMetric(new DurationMetric(METRIC_PREFIX + this.mClassName, jElapsed));
        if (this.mClassName.equals(StartPlayback.class.getSimpleName()) || this.mClassName.equals(MainPlayerTriggeredOnPrepared.class.getSimpleName())) {
            Profiler.reportTimerMetric(new SimpleTimerMetric(METRIC_PREFIX + this.mClassName + COMPLETED_METRIC_SUFFIX, System.currentTimeMillis(), 0L));
        }
        Profiler.endTrace(this.mTraceKey);
    }
}
