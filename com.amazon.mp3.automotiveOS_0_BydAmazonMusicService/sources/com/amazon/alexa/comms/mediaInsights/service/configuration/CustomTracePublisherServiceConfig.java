package com.amazon.alexa.comms.mediaInsights.service.configuration;

/* JADX INFO: loaded from: classes2.dex */
public class CustomTracePublisherServiceConfig {
    private static volatile TracePublisherServiceConfiguration configuration;

    private CustomTracePublisherServiceConfig() {
    }

    public static TracePublisherServiceConfiguration getConfiguration() {
        return configuration;
    }
}
