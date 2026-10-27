package com.amazon.music.metrics;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsHolder {
    private static MetricsManager sManager;

    public static synchronized MetricsManager getManager() {
        if (sManager == null) {
            sManager = new MetricsManager();
        }
        return sManager;
    }
}
