package com.amazon.alexa.comms.mediaInsights.service.transport;

/* JADX INFO: loaded from: classes2.dex */
public class TransportResult {
    private int responseCode;

    public static class TransportResultBuilder {
        private int responseCode;

        TransportResultBuilder() {
        }

        public TransportResult build() {
            return new TransportResult(this.responseCode);
        }

        public TransportResultBuilder responseCode(int i) {
            this.responseCode = i;
            return this;
        }

        public String toString() {
            return "TransportResult.TransportResultBuilder(responseCode=" + this.responseCode + ")";
        }
    }

    TransportResult(int i) {
        this.responseCode = i;
    }

    public static TransportResultBuilder builder() {
        return new TransportResultBuilder();
    }

    public String toString() {
        return "TransportResult(responseCode=" + getResponseCode() + ")";
    }

    public int getResponseCode() {
        return this.responseCode;
    }
}
