package com.amazon.appmanager.lib.metrics;

import com.amazon.maft.metrics.PreloadMetrics;
import com.amazon.maft.metrics.PreloadMetricsFactory;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpMaftPreloadMetricsFactory implements PreloadMetricsFactory {
    @Override // com.amazon.maft.metrics.PreloadMetricsFactory
    public PreloadMetrics newPreloadMetrics(String... strArr) {
        return new PreloadMetrics() { // from class: com.amazon.appmanager.lib.metrics.NoOpMaftPreloadMetricsFactory.1
            @Override // com.amazon.maft.metrics.PreloadMetrics
            public PreloadMetrics addCount(String str, double d) {
                return this;
            }

            @Override // com.amazon.maft.metrics.PreloadMetrics
            public PreloadMetrics addProperty(String str, String str2) {
                return this;
            }

            @Override // com.amazon.maft.metrics.PreloadMetrics
            public void record() {
            }
        };
    }
}
