package com.amazon.music.metrics.technical;

import com.amazon.client.metrics.common.NullMetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class TechnicalMetricsRecorder {
    private static Map<String, TechnicalMetricsCollection> metricsSetsByComponentName;
    private static final Logger LOG = LoggerFactory.getLogger(TechnicalMetricsRecorder.class.getSimpleName());
    private static final long DEFAULT_FREQUENCY_IN_MILLISECONDS = TimeUnit.SECONDS.toMillis(60);
    private static TechnicalMetricsCollectionFactory technicalMetricsCollectionFactory = null;

    private TechnicalMetricsRecorder() {
    }

    private static class TechnicalMetricsCollectionFactory {
        private PeriodicMetricReporter periodicMetricsReporter;
        private String programName;

        private TechnicalMetricsCollectionFactory() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TechnicalMetricsCollection createTechnicalMetricsCollection(String str) {
            return new TechnicalMetricsCollection(this.periodicMetricsReporter.createTrackedMetricEvent(this.programName, str));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TechnicalMetricsCollection createTechnicalMetricsCollectionWithNoOpMethods(String str) {
            return new TechnicalMetricsCollection(new NullMetricEvent("ProgramNameForNullMetricEvent", str));
        }
    }

    public static TechnicalMetricsCollection getTechnicalMetricsCollection(String str) {
        if (technicalMetricsCollectionFactory == null) {
            LOG.warn("TechnicalMetricsRecorder has not been initialized, returning no-op TechnicalMetricsCollection");
            return new TechnicalMetricsCollectionFactory().createTechnicalMetricsCollectionWithNoOpMethods(str);
        }
        TechnicalMetricsCollection technicalMetricsCollection = metricsSetsByComponentName.get(str);
        if (technicalMetricsCollection != null) {
            return technicalMetricsCollection;
        }
        TechnicalMetricsCollection technicalMetricsCollectionCreateTechnicalMetricsCollection = technicalMetricsCollectionFactory.createTechnicalMetricsCollection(str);
        metricsSetsByComponentName.put(str, technicalMetricsCollectionCreateTechnicalMetricsCollection);
        return technicalMetricsCollectionCreateTechnicalMetricsCollection;
    }
}
