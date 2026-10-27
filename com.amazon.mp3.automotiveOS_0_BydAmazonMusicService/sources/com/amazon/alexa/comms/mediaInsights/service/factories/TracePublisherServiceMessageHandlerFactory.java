package com.amazon.alexa.comms.mediaInsights.service.factories;

import com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceLogic;
import com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceMessageHandler;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TracePublisherServiceMessageHandlerFactory {
    public static TracePublisherServiceMessageHandler create(@NonNull TracePublisherServiceLogic tracePublisherServiceLogic) {
        if (tracePublisherServiceLogic == null) {
            throw new IllegalArgumentException("serviceLogic is null");
        }
        return new TracePublisherServiceMessageHandler(tracePublisherServiceLogic);
    }
}
