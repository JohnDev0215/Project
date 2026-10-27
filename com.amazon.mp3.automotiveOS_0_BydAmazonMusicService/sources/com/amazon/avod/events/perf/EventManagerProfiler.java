package com.amazon.avod.events.perf;

import com.amazon.avod.events.Event;
import com.amazon.avod.perf.DelayedCountersTracker;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class EventManagerProfiler {
    private final Set<EventManagerProfilerListener> mListeners;
    private final DelayedCountersTracker mTracker;
    private static final ImmutableList<String> SUCCESS_TYPES = ImmutableList.of("Success");
    private static final ImmutableList<String> FAILURE_TYPES = ImmutableList.of("Failure");

    private static class Holder {
        private static EventManagerProfiler INSTANCE = new EventManagerProfiler();

        private Holder() {
        }
    }

    @Nonnull
    public static EventManagerProfiler getInstance() {
        return Holder.INSTANCE;
    }

    private EventManagerProfiler() {
        this.mTracker = new DelayedCountersTracker();
        this.mListeners = new HashSet();
    }

    public void onEventQueued(@Nonnull Event event, boolean z) {
        incrementAndLog("EventManager-Queued-" + event.getType().getName(), getResultCounterTypes(z), 1, !z);
        UnmodifiableIterator<EventManagerProfilerListener> it = getListenerCopy().iterator();
        while (it.hasNext()) {
            it.next().onEventQueued(event, z);
        }
    }

    private ImmutableSet<EventManagerProfilerListener> getListenerCopy() {
        ImmutableSet<EventManagerProfilerListener> immutableSetCopyOf;
        synchronized (this.mListeners) {
            immutableSetCopyOf = ImmutableSet.copyOf((Collection) this.mListeners);
        }
        return immutableSetCopyOf;
    }

    private void incrementAndLog(@Nonnull String str, @Nonnull ImmutableList<String> immutableList, int i, boolean z) {
        this.mTracker.incrementCounter(str, immutableList, i);
        if (z) {
            DLog.warnf("%s %s", str, immutableList);
        } else {
            DLog.devf("%s %s", str, immutableList);
        }
    }

    private static ImmutableList<String> getResultCounterTypes(boolean z) {
        return z ? SUCCESS_TYPES : FAILURE_TYPES;
    }
}
