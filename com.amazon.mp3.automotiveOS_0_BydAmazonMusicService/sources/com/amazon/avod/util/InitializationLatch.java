package com.amazon.avod.util;

import android.os.SystemClock;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.threading.Watchdog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InitializationLatch {
    private static final TimeUnit MINIMUM_TIMEOUT_UNITS = TimeUnit.SECONDS;
    private final String mComponentName;
    private final CountDownLatch mInitializationLatch;
    private InitializationLatchPromise mInitializationPromise;
    private final AtomicReference<State> mInitializationState;
    private volatile long mInitializationThreadId;
    private TraceKey mInitializeKey;
    private volatile Optional<String> mProgress;
    private TraceKey mProgressKey;
    private final Watchdog mWatchdog;

    private enum State {
        NotInitialized,
        Initializing,
        Initialized
    }

    private static class InitializationLatchPromise extends Watchdog.Promise<InitializationLatch> {
        private final long mStartTimeMillis;

        InitializationLatchPromise(@Nonnull InitializationLatch initializationLatch, @Nonnegative long j, @Nonnull TimeUnit timeUnit) {
            super(initializationLatch, j, timeUnit);
            Preconditions.checkState(initializationLatch.mInitializationState.get() != State.NotInitialized, "You must start initialization of the latch before you watch it or watch may throw false positives");
            this.mStartTimeMillis = SystemClock.elapsedRealtime();
        }

        @Override // com.amazon.avod.threading.Watchdog.Promise
        public boolean isFulfilled(@Nonnull InitializationLatch initializationLatch) {
            return initializationLatch.isInitialized();
        }

        public void logMetricsIfPromiseIsBroken() {
            long millis = this.mTimeoutUnits.toMillis(this.mTimeout);
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = jElapsedRealtime - millis;
            long j2 = this.mStartTimeMillis;
            if (j > j2) {
                long j3 = jElapsedRealtime - j2;
                InitializationLatch initializationLatch = (InitializationLatch) this.mPromisor.get();
                Profiler.reportTimerMetric(new SimpleTimerMetric(initializationLatch != null ? String.format(Locale.US, "InitializationLatchPromiseBroken:%s", initializationLatch.getComponentName()) : "InitializationLatchPromiseBroken", this.mStartTimeMillis, j3));
            }
        }
    }

    public InitializationLatch(@Nonnull String str) {
        this(str, Watchdog.getInstance());
    }

    public InitializationLatch(@Nonnull Object obj) {
        this(obj.getClass().getSimpleName(), Watchdog.getInstance());
    }

    InitializationLatch(@Nonnull String str, @Nonnull Watchdog watchdog) {
        this.mInitializationState = new AtomicReference<>(State.NotInitialized);
        this.mInitializationLatch = new CountDownLatch(1);
        this.mProgress = Optional.absent();
        this.mInitializeKey = null;
        this.mProgressKey = null;
        this.mComponentName = (String) Preconditions.checkNotNull(str, "componentName");
        this.mWatchdog = (Watchdog) Preconditions.checkNotNull(watchdog, "watchdog");
    }

    public void start(@Nonnegative long j, @Nonnull TimeUnit timeUnit) {
        start(j, timeUnit, Profiler.TraceLevel.INFO);
    }

    public void start(@Nonnegative long j, @Nonnull TimeUnit timeUnit, @Nonnull Profiler.TraceLevel traceLevel) {
        Preconditions2.checkNonNegative(j, "timeout");
        Preconditions.checkNotNull(timeUnit, "timeoutUnits");
        Preconditions.checkNotNull(traceLevel, "traceLevel");
        Preconditions.checkState(CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.mInitializationState, State.NotInitialized, State.Initializing), "Initialization of %s cannot be started from %s state", this.mComponentName, this.mInitializationState.get());
        this.mInitializationThreadId = Thread.currentThread().getId();
        DLog.logf("Entered initialize for %s", this.mComponentName);
        this.mInitializeKey = Profiler.beginTrace(traceLevel, "%s:Initialization", this.mComponentName);
        long seconds = timeUnit.toSeconds(j);
        TimeUnit timeUnit2 = MINIMUM_TIMEOUT_UNITS;
        long seconds2 = timeUnit2.toSeconds(30L);
        if (seconds <= seconds2) {
            j = 30;
        }
        if (seconds <= seconds2) {
            timeUnit = timeUnit2;
        }
        InitializationLatchPromise initializationLatchPromise = new InitializationLatchPromise(this, j, timeUnit);
        this.mInitializationPromise = initializationLatchPromise;
        this.mWatchdog.watch(initializationLatchPromise);
    }

    public void complete() {
        checkInitializationThread("complete");
        Preconditions.checkState(CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.mInitializationState, State.Initializing, State.Initialized), "Initialization of %s cannot be completed from %s state", this.mComponentName, this.mInitializationState.get());
        DLog.logf("Completed initialize for %s", this.mComponentName);
        Profiler.endTrace(this.mProgressKey);
        Profiler.endTrace(this.mInitializeKey);
        this.mProgress = Optional.absent();
        this.mInitializationLatch.countDown();
        this.mInitializationPromise.logMetricsIfPromiseIsBroken();
    }

    public void updateProgress(@Nonnull String str) {
        Preconditions.checkNotNull(str, "currentProgress");
        checkInitializationThread("updateProgress");
        Preconditions.checkState(this.mInitializationState.get() == State.Initializing, "Can only update progress while in initialization is running (between start and complete");
        Profiler.endTrace(this.mProgressKey);
        this.mProgressKey = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:Initialization:%s", this.mComponentName, str);
        DLog.devf("%s:Initialization:%s", this.mComponentName, str);
        this.mProgress = Optional.of(str);
        this.mWatchdog.update(this.mInitializationPromise);
    }

    private void checkInitializationThread(@Nonnull String str) {
        Preconditions.checkNotNull(str, "methodName");
        long id = Thread.currentThread().getId();
        Preconditions.checkState(this.mInitializationThreadId == id, "%s: %s must be invoked on thread %s must be invoked the same thread as start (%s)", this.mComponentName, str, Long.valueOf(id), Long.valueOf(this.mInitializationThreadId));
    }

    public void checkInitialized() {
        Preconditions.checkState(isInitialized(), "%s must be initialized prior to use", this.mComponentName);
    }

    public void checkStarted() {
        Preconditions.checkState(this.mInitializationState.get() != State.NotInitialized, "%s cannot be called before initialization has started", this.mComponentName);
    }

    public boolean isInitialized() {
        return this.mInitializationState.get() == State.Initialized;
    }

    public void waitOnInitializationUninterruptibly() {
        State state = this.mInitializationState.get();
        if (state == State.Initialized) {
            return;
        }
        DLog.logf("Waiting for %s to initialize, current state %s", this.mComponentName, state);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.VERBOSE, "%s:WaitOnInitializationUninterruptibly", this.mComponentName);
        try {
            Uninterruptibles.awaitUninterruptibly(this.mInitializationLatch);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    @Nonnull
    public String getComponentName() {
        return this.mComponentName;
    }

    public String toString() {
        Optional<String> optional = this.mProgress;
        return optional.isPresent() ? String.format(Locale.US, "%s:%s", this.mComponentName, optional.get()) : this.mComponentName;
    }
}
