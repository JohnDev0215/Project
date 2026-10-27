package com.amazon.alexa.comms.mediaInsights.service.transport;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration;
import com.amazon.alexa.comms.mediaInsights.service.factories.HttpURLConnectionFactory;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class HttpTraceTransport implements TraceTransport {
    private TracePublisherServiceConfiguration configuration;
    private HttpURLConnectionFactory connectionFactory;
    private PowerManager powerManager;
    private WifiManager wifiManager;

    public HttpTraceTransport(@NonNull Context context, @NonNull TracePublisherServiceConfiguration tracePublisherServiceConfiguration, @NonNull HttpURLConnectionFactory httpURLConnectionFactory) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (tracePublisherServiceConfiguration == null) {
            throw new IllegalArgumentException("config is null");
        }
        if (httpURLConnectionFactory == null) {
            throw new IllegalArgumentException("httpURLConnectionFactory is null");
        }
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.configuration = tracePublisherServiceConfiguration;
        this.connectionFactory = httpURLConnectionFactory;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00f6  */
    @Override // com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.alexa.comms.mediaInsights.service.transport.TransportResult send(@lombok.NonNull com.amazon.alexa.comms.mediaInsights.service.transport.TransportPayload r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.comms.mediaInsights.service.transport.HttpTraceTransport.send(com.amazon.alexa.comms.mediaInsights.service.transport.TransportPayload):com.amazon.alexa.comms.mediaInsights.service.transport.TransportResult");
    }
}
