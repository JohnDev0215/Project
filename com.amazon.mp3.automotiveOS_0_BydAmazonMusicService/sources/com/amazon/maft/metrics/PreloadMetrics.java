package com.amazon.maft.metrics;

/* JADX INFO: loaded from: classes3.dex */
public interface PreloadMetrics {
    PreloadMetrics addCount(String str, double d);

    PreloadMetrics addProperty(String str, String str2);

    void record();
}
