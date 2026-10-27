package com.amazon.coral.retry;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final class RetryContext {
    private final Predicate<Throwable> availabilityInspector;
    private final String callQualifier;
    private final MetricsRecorder metricsRecorder;
    private final Consumer<String> statCounter;

    public MetricsRecorder getMetricsRecorder() {
        return this.metricsRecorder;
    }

    public Predicate<Throwable> getAvailabilityInspector() {
        return this.availabilityInspector;
    }

    public Consumer<String> getStatCounter() {
        return this.statCounter;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RetryContext.class != obj.getClass()) {
            return false;
        }
        return this.callQualifier.equals(((RetryContext) obj).callQualifier);
    }

    public int hashCode() {
        return Objects.hash(this.callQualifier);
    }
}
