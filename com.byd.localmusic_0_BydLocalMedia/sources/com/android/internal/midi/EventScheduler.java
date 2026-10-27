package com.android.internal.midi;

import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public class EventScheduler {
    private static final long NANOS_PER_MILLI = 1000000;
    private boolean mClosed;
    private final Object mLock = new Object();
    private FastEventQueue mEventPool = null;
    private int mMaxPoolSize = 200;
    private volatile SortedMap<Long, FastEventQueue> mEventBuffer = new TreeMap();

    private class FastEventQueue {
        volatile long mEventsAdded = 1;
        volatile long mEventsRemoved = 0;
        volatile SchedulableEvent mFirst;
        volatile SchedulableEvent mLast;

        FastEventQueue(SchedulableEvent schedulableEvent) {
            this.mFirst = schedulableEvent;
            this.mLast = this.mFirst;
        }

        int size() {
            return (int) (this.mEventsAdded - this.mEventsRemoved);
        }

        public SchedulableEvent remove() {
            this.mEventsRemoved++;
            SchedulableEvent schedulableEvent = this.mFirst;
            this.mFirst = schedulableEvent.mNext;
            schedulableEvent.mNext = null;
            return schedulableEvent;
        }

        public void add(SchedulableEvent schedulableEvent) {
            schedulableEvent.mNext = null;
            this.mLast.mNext = schedulableEvent;
            this.mLast = schedulableEvent;
            this.mEventsAdded++;
        }
    }

    public static class SchedulableEvent {
        private volatile SchedulableEvent mNext = null;
        private long mTimestamp;

        public SchedulableEvent(long j) {
            this.mTimestamp = j;
        }

        public long getTimestamp() {
            return this.mTimestamp;
        }

        public void setTimestamp(long j) {
            this.mTimestamp = j;
        }
    }

    public SchedulableEvent removeEventfromPool() {
        FastEventQueue fastEventQueue = this.mEventPool;
        if (fastEventQueue == null || fastEventQueue.size() <= 1) {
            return null;
        }
        return this.mEventPool.remove();
    }

    public void addEventToPool(SchedulableEvent schedulableEvent) {
        FastEventQueue fastEventQueue = this.mEventPool;
        if (fastEventQueue == null) {
            this.mEventPool = new FastEventQueue(schedulableEvent);
        } else if (fastEventQueue.size() < this.mMaxPoolSize) {
            this.mEventPool.add(schedulableEvent);
        }
    }

    public void add(SchedulableEvent schedulableEvent) {
        synchronized (this.mLock) {
            FastEventQueue fastEventQueue = this.mEventBuffer.get(Long.valueOf(schedulableEvent.getTimestamp()));
            if (fastEventQueue == null) {
                long jLongValue = this.mEventBuffer.isEmpty() ? Long.MAX_VALUE : this.mEventBuffer.firstKey().longValue();
                this.mEventBuffer.put(Long.valueOf(schedulableEvent.getTimestamp()), new FastEventQueue(schedulableEvent));
                if (schedulableEvent.getTimestamp() < jLongValue) {
                    this.mLock.notify();
                }
            } else {
                fastEventQueue.add(schedulableEvent);
            }
        }
    }

    private SchedulableEvent removeNextEventLocked(long j) {
        FastEventQueue fastEventQueue = this.mEventBuffer.get(Long.valueOf(j));
        if (fastEventQueue.size() == 1) {
            this.mEventBuffer.remove(Long.valueOf(j));
        }
        return fastEventQueue.remove();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.internal.midi.EventScheduler.SchedulableEvent getNextEvent(long r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.SortedMap<java.lang.Long, com.android.internal.midi.EventScheduler$FastEventQueue> r1 = r3.mEventBuffer     // Catch: java.lang.Throwable -> L23
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L23
            if (r1 != 0) goto L20
            java.util.SortedMap<java.lang.Long, com.android.internal.midi.EventScheduler$FastEventQueue> r1 = r3.mEventBuffer     // Catch: java.lang.Throwable -> L23
            java.lang.Object r1 = r1.firstKey()     // Catch: java.lang.Throwable -> L23
            java.lang.Long r1 = (java.lang.Long) r1     // Catch: java.lang.Throwable -> L23
            long r1 = r1.longValue()     // Catch: java.lang.Throwable -> L23
            int r4 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r4 > 0) goto L20
            com.android.internal.midi.EventScheduler$SchedulableEvent r4 = r3.removeNextEventLocked(r1)     // Catch: java.lang.Throwable -> L23
            goto L21
        L20:
            r4 = 0
        L21:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L23
            return r4
        L23:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L23
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.midi.EventScheduler.getNextEvent(long):com.android.internal.midi.EventScheduler$SchedulableEvent");
    }

    public SchedulableEvent waitNextEvent() throws InterruptedException {
        SchedulableEvent schedulableEventRemoveNextEventLocked;
        synchronized (this.mLock) {
            while (true) {
                if (this.mClosed) {
                    schedulableEventRemoveNextEventLocked = null;
                    break;
                }
                long j = 2147483647L;
                if (!this.mEventBuffer.isEmpty()) {
                    long jNanoTime = System.nanoTime();
                    long jLongValue = this.mEventBuffer.firstKey().longValue();
                    if (jLongValue <= jNanoTime) {
                        schedulableEventRemoveNextEventLocked = removeNextEventLocked(jLongValue);
                        break;
                    }
                    long j2 = ((jLongValue - jNanoTime) / 1000000) + 1;
                    if (j2 <= 2147483647L) {
                        j = j2;
                    }
                }
                this.mLock.wait((int) j);
            }
        }
        return schedulableEventRemoveNextEventLocked;
    }

    protected void flush() {
        this.mEventBuffer = new TreeMap();
    }

    public void close() {
        synchronized (this.mLock) {
            this.mClosed = true;
            this.mLock.notify();
        }
    }
}
