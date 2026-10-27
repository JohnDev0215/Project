package com.amazon.alexa.comms.mediaInsights.service;

import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetailsFactory;
import com.amazon.alexa.comms.mediaInsights.service.factories.TraceTransportFactory;
import com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport;
import com.amazon.alexa.comms.mediaInsights.service.transport.TransportPayload;
import com.amazon.alexa.comms.mediaInsights.service.transport.TransportResult;
import com.amazon.alexa.comms.mediaInsights.service.utils.StreamUtils;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TraceFlusher {

    @NonNull
    private DeviceDetailsFactory deviceDetailsFactory;

    @NonNull
    private ExecutorService executorService;

    @NonNull
    private TraceTransportFactory traceTransportFactory;

    public TraceFlusher(@NonNull DeviceDetailsFactory deviceDetailsFactory, @NonNull TraceTransportFactory traceTransportFactory, @NonNull ExecutorService executorService) {
        if (deviceDetailsFactory == null) {
            throw new IllegalArgumentException("deviceDetailsFactory is null");
        }
        if (traceTransportFactory == null) {
            throw new IllegalArgumentException("traceTransportFactory is null");
        }
        if (executorService == null) {
            throw new IllegalArgumentException("executorService is null");
        }
        this.deviceDetailsFactory = deviceDetailsFactory;
        this.traceTransportFactory = traceTransportFactory;
        this.executorService = executorService;
    }

    public void asyncFlushTraces(Collection<String> collection) {
        try {
            this.executorService.submit(createRunnableForTransport(collection));
        } catch (Throwable th) {
            Log.e("MediaInsightsPublisher", "failed to flush messages", th);
        }
    }

    private Runnable createRunnableForTransport(final Collection<String> collection) {
        final TraceTransport traceTransportCreate = this.traceTransportFactory.create();
        return new Runnable() { // from class: com.amazon.alexa.comms.mediaInsights.service.TraceFlusher.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TransportPayload transportPayloadBuild = TransportPayload.builder().bytes(StreamUtils.gzipCompress(new TraceRequestModel(TraceFlusher.this.deviceDetailsFactory.create()).toJson(collection))).encoding("gzip").type("application/json").build();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    TransportResult transportResultSend = traceTransportCreate.send(transportPayloadBuild);
                    Log.d("MediaInsightsPublisher", String.format("transport time : %s ms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                    Log.d("MediaInsightsPublisher", transportResultSend.toString());
                } catch (Throwable th) {
                    Log.e("MediaInsightsPublisher", String.format("transmission of transport message failed: %s %s", th.getCause(), th.getMessage()), th);
                }
            }
        };
    }
}
