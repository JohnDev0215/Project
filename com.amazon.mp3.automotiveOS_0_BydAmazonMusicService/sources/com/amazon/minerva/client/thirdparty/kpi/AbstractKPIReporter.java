package com.amazon.minerva.client.thirdparty.kpi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractKPIReporter {
    protected ConcurrentMap<String, HashMap<String, Long>> mKPIMap = new ConcurrentHashMap();
    protected HashSet<String> mKPIKeys = new HashSet<>();

    protected abstract void initializeKPIKeys();

    public AbstractKPIReporter() {
        initializeKPIKeys();
    }

    public Map<String, HashMap<String, Long>> getKpiMap() {
        return this.mKPIMap;
    }

    public synchronized void report(String str, String str2, long j) {
        Objects.requireNonNull(str, "kpiKey cannot be null in report method.");
        Objects.requireNonNull(str2, "MetricGroupId cannot be null in report method.");
        if (!this.mKPIKeys.contains(str) || str2.isEmpty() || j < 0) {
            throw new IllegalArgumentException("Key, MetricGroupID, or Value is invalid in report method.");
        }
        if (j == 0) {
            return;
        }
        HashMap<String, Long> map = this.mKPIMap.get(str2);
        if (map == null) {
            map = new HashMap<>();
            this.mKPIMap.put(str2, map);
        }
        if (map.containsKey(str)) {
            j += map.get(str).longValue();
        }
        map.put(str, Long.valueOf(j));
    }
}
