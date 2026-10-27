package com.facebook.react.bridge;

import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public interface PerformanceCounter {
    Map<String, Long> getPerformanceCounters();

    void profileNextBatch();
}
