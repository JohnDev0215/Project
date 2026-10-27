package com.amazon.mls.config.internal.core.metrics;

import com.amazon.mls.config.internal.core.metrics.internal.Counter;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class InternalMetrics {
    private static volatile List<Counter> counters = new LinkedList();

    public static synchronized void logCounter(String str, long j) {
        if (counters.size() == 5000) {
            counters.remove(0);
        }
        counters.add(new Counter(str, j));
    }

    public static void logCounter(String str) {
        logCounter(str, 1L);
    }
}
