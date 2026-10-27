package com.amazon.alexa.comms.mediaInsights.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.factories.TracePublisherServiceConfigProvider;
import com.amazon.alexa.comms.mediaInsights.service.factories.TracePublisherServiceLogicFactory;
import com.amazon.alexa.comms.mediaInsights.service.factories.TracePublisherServiceMessageHandlerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class TracePublisherService extends Service {
    private Messenger messenger;
    private TracePublisherServiceConfiguration serviceConfig;
    private TracePublisherServiceLogic tracePublisherServiceLogic;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d("MediaInsightsPublisher", "TracePublisherService is bound");
        return this.messenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.d("MediaInsightsPublisher", "TracePublisherService is created");
        TracePublisherServiceConfiguration tracePublisherServiceConfigurationProvideConfig = TracePublisherServiceConfigProvider.provideConfig();
        this.serviceConfig = tracePublisherServiceConfigurationProvideConfig;
        Log.i("MediaInsightsPublisher", String.format("Configuration details %s", tracePublisherServiceConfigurationProvideConfig.toString()));
        TracePublisherServiceLogic tracePublisherServiceLogicFactory = TracePublisherServiceLogicFactory.getInstance(this.serviceConfig, getApplicationContext());
        this.tracePublisherServiceLogic = tracePublisherServiceLogicFactory;
        TracePublisherServiceMessageHandler tracePublisherServiceMessageHandlerCreate = TracePublisherServiceMessageHandlerFactory.create(tracePublisherServiceLogicFactory);
        this.messenger = new Messenger(tracePublisherServiceMessageHandlerCreate);
        this.tracePublisherServiceLogic.setServiceHandler(tracePublisherServiceMessageHandlerCreate);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.d("MediaInsightsPublisher", "TracePublisherService is unbound");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d("MediaInsightsPublisher", "TracePublisherService is destroyed");
        this.tracePublisherServiceLogic.forceFlush();
    }
}
