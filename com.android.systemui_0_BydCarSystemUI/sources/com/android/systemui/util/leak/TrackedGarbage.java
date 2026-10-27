package com.android.systemui.util.leak;

import android.os.SystemClock;
import android.util.ArrayMap;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TrackedGarbage {
    private static final long GARBAGE_COLLECTION_DEADLINE_MILLIS = 60000;
    private final HashSet<LeakReference> mGarbage = new HashSet<>();
    private final ReferenceQueue<Object> mRefQueue = new ReferenceQueue<>();
    private final TrackedCollections mTrackedCollections;

    private boolean isOld(long createdUptimeMillis, long now) {
        return createdUptimeMillis + GARBAGE_COLLECTION_DEADLINE_MILLIS < now;
    }

    public TrackedGarbage(TrackedCollections trackedCollections) {
        this.mTrackedCollections = trackedCollections;
    }

    public synchronized void track(Object o) {
        cleanUp();
        this.mGarbage.add(new LeakReference(o, this.mRefQueue));
        this.mTrackedCollections.track(this.mGarbage, "Garbage");
    }

    private void cleanUp() {
        while (true) {
            Reference<? extends Object> referencePoll = this.mRefQueue.poll();
            if (referencePoll == null) {
                return;
            } else {
                this.mGarbage.remove(referencePoll);
            }
        }
    }

    private static class LeakReference extends WeakReference<Object> {
        private final Class<?> clazz;
        private final long createdUptimeMillis;

        LeakReference(Object t, ReferenceQueue<Object> queue) {
            super(t, queue);
            this.clazz = t.getClass();
            this.createdUptimeMillis = SystemClock.uptimeMillis();
        }
    }

    public synchronized void dump(PrintWriter pw) {
        cleanUp();
        long jUptimeMillis = SystemClock.uptimeMillis();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (LeakReference leakReference : this.mGarbage) {
            arrayMap.put(leakReference.clazz, Integer.valueOf(((Integer) arrayMap.getOrDefault(leakReference.clazz, 0)).intValue() + 1));
            if (isOld(leakReference.createdUptimeMillis, jUptimeMillis)) {
                arrayMap2.put(leakReference.clazz, Integer.valueOf(((Integer) arrayMap2.getOrDefault(leakReference.clazz, 0)).intValue() + 1));
            }
        }
        for (Map.Entry entry : arrayMap.entrySet()) {
            pw.print(((Class) entry.getKey()).getName());
            pw.print(": ");
            pw.print(entry.getValue());
            pw.print(" total, ");
            pw.print(arrayMap2.getOrDefault(entry.getKey(), 0));
            pw.print(" old");
            pw.println();
        }
    }

    public synchronized int countOldGarbage() {
        int i;
        cleanUp();
        long jUptimeMillis = SystemClock.uptimeMillis();
        i = 0;
        Iterator<LeakReference> it = this.mGarbage.iterator();
        while (it.hasNext()) {
            if (isOld(it.next().createdUptimeMillis, jUptimeMillis)) {
                i++;
            }
        }
        return i;
    }
}
