package com.amazon.alexa.comms.mediaInsights.service.transport;

import java.util.Arrays;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TransportPayload {

    @NonNull
    private byte[] bytes;

    @NonNull
    private String encoding;

    @NonNull
    private String type;

    public static class TransportPayloadBuilder {
        private byte[] bytes;
        private String encoding;
        private String type;

        TransportPayloadBuilder() {
        }

        public TransportPayload build() {
            return new TransportPayload(this.bytes, this.encoding, this.type);
        }

        public TransportPayloadBuilder bytes(byte[] bArr) {
            this.bytes = bArr;
            return this;
        }

        public TransportPayloadBuilder encoding(String str) {
            this.encoding = str;
            return this;
        }

        public String toString() {
            return "TransportPayload.TransportPayloadBuilder(bytes=" + Arrays.toString(this.bytes) + ", encoding=" + this.encoding + ", type=" + this.type + ")";
        }

        public TransportPayloadBuilder type(String str) {
            this.type = str;
            return this;
        }
    }

    TransportPayload(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2) {
        if (bArr == null) {
            throw new IllegalArgumentException("bytes is null");
        }
        if (str == null) {
            throw new IllegalArgumentException("encoding is null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("type is null");
        }
        this.bytes = bArr;
        this.encoding = str;
        this.type = str2;
    }

    public static TransportPayloadBuilder builder() {
        return new TransportPayloadBuilder();
    }

    @NonNull
    public byte[] getBytes() {
        return this.bytes;
    }

    @NonNull
    public String getEncoding() {
        return this.encoding;
    }

    @NonNull
    public String getType() {
        return this.type;
    }
}
