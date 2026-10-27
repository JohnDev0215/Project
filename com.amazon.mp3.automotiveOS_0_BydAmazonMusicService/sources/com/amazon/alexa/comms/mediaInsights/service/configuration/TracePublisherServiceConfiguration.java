package com.amazon.alexa.comms.mediaInsights.service.configuration;

/* JADX INFO: loaded from: classes2.dex */
public interface TracePublisherServiceConfiguration {
    Integer getDeviceDetailsJobIntervalInMillis();

    Integer getExponentForHttpRetries();

    Long getFlushJobIntervalInMillis();

    Integer getFlushThreadPoolSize();

    Integer getHttpConnectTimeout();

    Integer getHttpReadTimeout();

    Integer getMaxNumberOfHttpRetries();

    Integer getMinimumDelayForHttpRetries();

    Integer getTotalTracesCapacityInBytes();

    String getTraceServiceHttpsEndpoint();

    Long getWakeLockTimeout();
}
