package com.amazon.alexa.comms.mediaInsights.service.transport;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface TraceTransport {
    TransportResult send(TransportPayload transportPayload) throws IOException;
}
