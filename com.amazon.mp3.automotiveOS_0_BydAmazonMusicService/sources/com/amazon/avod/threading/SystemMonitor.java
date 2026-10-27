package com.amazon.avod.threading;

import android.os.Looper;
import android.os.SystemClock;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.threading.internal.NamedThreadFactory;
import com.amazon.avod.util.ReflectionUtils$FieldWrapper;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SystemMonitor {
    private final AtomicBoolean mIsInitialized;
    private ScheduledExecutorService mPollForStopTheWorldGCsExecutor;
    private ScheduledExecutorService mPollThreadStateExecutor;

    private SystemMonitor() {
        this.mIsInitialized = new AtomicBoolean(false);
    }

    static class SingletonHolder {
        static final SystemMonitor INSTANCE = new SystemMonitor();

        SingletonHolder() {
        }
    }

    @Nonnull
    public static SystemMonitor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialize() {
        if (this.mIsInitialized.compareAndSet(false, true)) {
            if (Profiler.isTraceLevelEnabled(Profiler.TraceLevel.INFO)) {
                startPollingForStopTheWorldGCs();
            }
            if (Profiler.isTraceLevelEnabled(Profiler.TraceLevel.VERBOSE)) {
                startPollingActiveSystemThreadStates();
            }
        }
    }

    private synchronized void startPollingForStopTheWorldGCs() {
        ScheduledExecutorService scheduledExecutorService = this.mPollForStopTheWorldGCsExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mPollForStopTheWorldGCsExecutor = null;
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(this, "PollForGCs").withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.TMI).build();
        this.mPollForStopTheWorldGCsExecutor = scheduledThreadPoolExecutorBuild;
        scheduledThreadPoolExecutorBuild.scheduleAtFixedRate(new PollForStopTheWorldGCs(), 0L, 10L, TimeUnit.MILLISECONDS);
    }

    private synchronized void startPollingActiveSystemThreadStates() {
        ScheduledExecutorService scheduledExecutorService = this.mPollThreadStateExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mPollThreadStateExecutor = null;
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(this, "PollThreadState").withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.TMI).build();
        this.mPollThreadStateExecutor = scheduledThreadPoolExecutorBuild;
        scheduledThreadPoolExecutorBuild.scheduleAtFixedRate(new PollActiveSystemThreadStates(), 0L, 10L, TimeUnit.MILLISECONDS);
    }

    private static class PollActiveSystemThreadStates implements Runnable {
        private static final ReflectionUtils$FieldWrapper<ThreadGroup> SYSTEM_THREAD_GROUP = new ReflectionUtils$FieldWrapper<>(ThreadGroup.class, null, "systemThreadGroup", true);
        private final Thread.State[] PROFILED_STATES;
        private Thread[] mDummyBackingArray;
        private final Thread mMainThread;
        private final Map<Thread, Thread.State> mThreadState;
        private final Map<Thread, TraceKey> mThreadTraceKey;

        private PollActiveSystemThreadStates() {
            this.mThreadState = Maps.newHashMap();
            this.mThreadTraceKey = Maps.newHashMap();
            this.mMainThread = Looper.getMainLooper().getThread();
            this.PROFILED_STATES = new Thread.State[]{Thread.State.BLOCKED, Thread.State.RUNNABLE};
            this.mDummyBackingArray = new Thread[100];
        }

        @Override // java.lang.Runnable
        public void run() {
            Set<Thread> activeThreads = getActiveThreads();
            Set<Thread> allNamedThreads = NamedThreadFactory.getAllNamedThreads();
            for (Thread thread : activeThreads) {
                if (thread != this.mMainThread && !(thread instanceof ProfiledThread) && !allNamedThreads.contains(thread)) {
                    handleStateTransition(thread);
                }
            }
            for (Thread thread2 : ImmutableSet.copyOf((Collection) Sets.difference(this.mThreadState.keySet(), activeThreads))) {
                this.mThreadState.remove(thread2);
                Profiler.endTrace(this.mThreadTraceKey.remove(thread2));
            }
        }

        private void handleStateTransition(@Nonnull Thread thread) {
            Thread.State state = thread.getState();
            Thread.State statePut = this.mThreadState.put(thread, state);
            Thread.State[] stateArr = this.PROFILED_STATES;
            int length = stateArr.length;
            for (int i = 0; i < length; i++) {
                Thread.State state2 = stateArr[i];
                boolean z = statePut == state2;
                boolean z2 = state == state2;
                if (z && !z2) {
                    Profiler.endTrace(this.mThreadTraceKey.remove(thread));
                } else if (!z && z2) {
                    this.mThreadTraceKey.put(thread, Profiler.beginTrace(Profiler.TraceLevel.VERBOSE, thread, "Thread:%s", state2.name()));
                }
            }
        }

        private Set<Thread> getActiveThreads() {
            ThreadGroup threadGroup = SYSTEM_THREAD_GROUP.get();
            if (threadGroup == null) {
                return new HashSet();
            }
            long jActiveCount = threadGroup.activeCount();
            long j = jActiveCount + (jActiveCount / 2);
            Thread[] threadArr = this.mDummyBackingArray;
            if (threadArr.length < j) {
                this.mDummyBackingArray = new Thread[(int) Math.max(j, threadArr.length * 2)];
            }
            int iEnumerate = threadGroup.enumerate(this.mDummyBackingArray);
            ImmutableSet set = FluentIterable.from(Arrays.asList(this.mDummyBackingArray)).limit(iEnumerate).toSet();
            for (int i = 0; i < iEnumerate; i++) {
                this.mDummyBackingArray[i] = null;
            }
            return set;
        }
    }

    private static class PollForStopTheWorldGCs implements Runnable {
        private long mLastExecutionTime;
        private TraceKey mPendingKey;

        private PollForStopTheWorldGCs() {
            this.mLastExecutionTime = -1L;
        }

        @Override // java.lang.Runnable
        public void run() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.mLastExecutionTime;
            if (j != -1 && jElapsedRealtime - j > 15) {
                Profiler.endTrace(this.mPendingKey);
            } else {
                Profiler.cancelTrace(this.mPendingKey);
            }
            this.mLastExecutionTime = SystemClock.elapsedRealtime();
            this.mPendingKey = Profiler.beginTrace(Profiler.TraceLevel.INFO, "%s:PossibleGC", SystemMonitor.class.getSimpleName());
        }
    }
}
