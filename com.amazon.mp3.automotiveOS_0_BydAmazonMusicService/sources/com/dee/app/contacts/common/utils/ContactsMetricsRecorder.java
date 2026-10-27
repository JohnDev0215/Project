package com.dee.app.contacts.common.utils;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsMetricsRecorder {
    private final AlexaCommsCoreMetricsService metricsService;

    public ContactsMetricsRecorder(AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.metricsService = alexaCommsCoreMetricsService;
    }

    public void recordCounter(String str, Class cls) {
        this.metricsService.recordCounter(this.metricsService.createCounter(str, cls.getSimpleName(), new HashMap()));
    }
}
