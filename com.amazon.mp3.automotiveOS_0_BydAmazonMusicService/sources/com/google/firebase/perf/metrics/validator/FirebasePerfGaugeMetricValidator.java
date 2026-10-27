package com.google.firebase.perf.metrics.validator;

import com.google.firebase.perf.v1.GaugeMetric;

/* JADX INFO: loaded from: classes6.dex */
final class FirebasePerfGaugeMetricValidator extends PerfMetricValidator {
    private final GaugeMetric gaugeMetric;

    FirebasePerfGaugeMetricValidator(GaugeMetric gaugeMetric) {
        this.gaugeMetric = gaugeMetric;
    }

    @Override // com.google.firebase.perf.metrics.validator.PerfMetricValidator
    public boolean isValidPerfMetric() {
        return this.gaugeMetric.hasSessionId() && (this.gaugeMetric.getCpuMetricReadingsCount() > 0 || this.gaugeMetric.getAndroidMemoryReadingsCount() > 0 || (this.gaugeMetric.hasGaugeMetadata() && this.gaugeMetric.getGaugeMetadata().hasMaxAppJavaHeapMemoryKb()));
    }
}
