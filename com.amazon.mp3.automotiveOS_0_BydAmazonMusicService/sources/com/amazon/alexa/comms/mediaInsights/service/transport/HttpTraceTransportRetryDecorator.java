package com.amazon.alexa.comms.mediaInsights.service.transport;

import android.util.Log;
import java.io.IOException;
import java.net.HttpRetryException;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class HttpTraceTransportRetryDecorator implements TraceTransport {
    private final int delayInMillis;
    private final int exponent;
    private final int maxNumberOfRetries;

    @NonNull
    private final TraceTransport traceTransport;

    public static class HttpTraceTransportRetryDecoratorBuilder {
        private int delayInMillis;
        private int exponent;
        private int maxNumberOfRetries;
        private TraceTransport traceTransport;

        HttpTraceTransportRetryDecoratorBuilder() {
        }

        public HttpTraceTransportRetryDecorator build() {
            return new HttpTraceTransportRetryDecorator(this.traceTransport, this.maxNumberOfRetries, this.exponent, this.delayInMillis);
        }

        public HttpTraceTransportRetryDecoratorBuilder delayInMillis(int i) {
            this.delayInMillis = i;
            return this;
        }

        public HttpTraceTransportRetryDecoratorBuilder exponent(int i) {
            this.exponent = i;
            return this;
        }

        public HttpTraceTransportRetryDecoratorBuilder maxNumberOfRetries(int i) {
            this.maxNumberOfRetries = i;
            return this;
        }

        public String toString() {
            return "HttpTraceTransportRetryDecorator.HttpTraceTransportRetryDecoratorBuilder(traceTransport=" + this.traceTransport + ", maxNumberOfRetries=" + this.maxNumberOfRetries + ", exponent=" + this.exponent + ", delayInMillis=" + this.delayInMillis + ")";
        }

        public HttpTraceTransportRetryDecoratorBuilder traceTransport(TraceTransport traceTransport) {
            this.traceTransport = traceTransport;
            return this;
        }
    }

    HttpTraceTransportRetryDecorator(@NonNull TraceTransport traceTransport, int i, int i2, int i3) {
        if (traceTransport == null) {
            throw new IllegalArgumentException("traceTransport is null");
        }
        this.traceTransport = traceTransport;
        this.maxNumberOfRetries = i;
        this.exponent = i2;
        this.delayInMillis = i3;
    }

    public static HttpTraceTransportRetryDecoratorBuilder builder() {
        return new HttpTraceTransportRetryDecoratorBuilder();
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.transport.TraceTransport
    public TransportResult send(TransportPayload transportPayload) throws IOException {
        try {
            return this.traceTransport.send(transportPayload);
        } catch (HttpRetryException e) {
            return retry(transportPayload, e);
        }
    }

    private TransportResult retry(TransportPayload transportPayload, HttpRetryException e) throws IOException {
        long j = this.delayInMillis;
        for (int i = 1; i <= this.maxNumberOfRetries; i++) {
            Log.e("MediaInsightsPublisher", String.format("retry http request: retry no %s, retry reason: %s %s", Integer.valueOf(i), e.getReason(), Integer.valueOf(e.responseCode())));
            sleep(j);
            try {
                return this.traceTransport.send(transportPayload);
            } catch (HttpRetryException e2) {
                e = e2;
                j *= (long) this.exponent;
            }
        }
        throw e;
    }

    private void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }
}
