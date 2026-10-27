package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.content.Context;
import com.amazon.alexa.comms.mediaInsights.service.TraceFlusher;
import com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceLogic;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetailsFactory;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.NetworkDetailsFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TracePublisherServiceLogicFactory {
    private static TracePublisherServiceLogic tracePublisherServiceLogic;

    public static synchronized TracePublisherServiceLogic getInstance(@NonNull TracePublisherServiceConfiguration tracePublisherServiceConfiguration, @NonNull Context context) {
        if (tracePublisherServiceConfiguration == null) {
            throw new IllegalArgumentException("config is null");
        }
        if (context == null) {
            throw new IllegalArgumentException("appContext is null");
        }
        if (tracePublisherServiceLogic == null) {
            tracePublisherServiceLogic = new TracePublisherServiceLogic(tracePublisherServiceConfiguration, createTraceFlusher(tracePublisherServiceConfiguration, context), context);
        }
        return tracePublisherServiceLogic;
    }

    private static TraceFlusher createTraceFlusher(TracePublisherServiceConfiguration tracePublisherServiceConfiguration, Context context) {
        return new TraceFlusherFactory(createTraceFlusherTransportFactory(tracePublisherServiceConfiguration, context), createDeviceDetailsFactory(context), createExecutorService(tracePublisherServiceConfiguration)).create();
    }

    private static DeviceDetailsFactory createDeviceDetailsFactory(Context context) {
        return new DeviceDetailsFactory(createNetworkDetailsFactory(context));
    }

    private static NetworkDetailsFactory createNetworkDetailsFactory(Context context) {
        return new NetworkDetailsFactory(context);
    }

    private static TraceTransportFactory createTraceFlusherTransportFactory(TracePublisherServiceConfiguration tracePublisherServiceConfiguration, Context context) {
        return new TraceTransportFactory(tracePublisherServiceConfiguration, context);
    }

    private static ExecutorService createExecutorService(TracePublisherServiceConfiguration tracePublisherServiceConfiguration) {
        return Executors.newFixedThreadPool(tracePublisherServiceConfiguration.getFlushThreadPoolSize().intValue());
    }
}
