package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.configuration.CustomTracePublisherServiceConfig;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfigFactory;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;

/* JADX INFO: loaded from: classes2.dex */
public class TracePublisherServiceConfigProvider {
    public static TracePublisherServiceConfiguration provideConfig() {
        TracePublisherServiceConfiguration configuration = CustomTracePublisherServiceConfig.getConfiguration();
        if (configuration != null) {
            Log.i("MediaInsightsPublisher", "Custom config being used");
            return configuration;
        }
        TracePublisherServiceConfiguration tracePublisherServiceConfigurationCreateReleaseConfig = TracePublisherServiceConfigFactory.createReleaseConfig();
        Log.i("MediaInsightsPublisher", String.format("release configs loaded. AMZ_STAGE %s", "release"));
        return tracePublisherServiceConfigurationCreateReleaseConfig;
    }
}
